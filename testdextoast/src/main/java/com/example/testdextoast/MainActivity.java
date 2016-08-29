package com.example.testdextoast;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File dexOutputDir = getDir("dex1", 0);
        String dexPath = Environment.getExternalStorageDirectory().toString() + "/output.jar";
//        String path = Environment.getExternalStorageDirectory().toString() + "/test111";
//        File file = new File(path);
//        if (!file.exists()) {
//            boolean sucess = file.mkdirs();
//            Log.e("starry", "success :" + sucess);
//        }
        /**
         * 此处需要注意DexClassLoader的四个参数：
         参数1 dexPath：待加载的dex文件路径，
         参数2 optimizedDirectory：解压后的dex存放位置，此位置一定要是可读写且仅该应用可读写（安全性考虑），所以只能放在data/data下。
         本文getDir("dex1", 0)会在/data/data/**package/下创建一个名叫”app_dex1“的文件夹，其内存放的文件是自动生成output.dex；
         如果不满足条件，Android会报的错误为：
         java.lang.IllegalArgumentException: optimizedDirectory not readable/writable: /storage/sdcard0
         java.lang.IllegalArgumentException: Optimized data directory /storage/sdcard0 is not owned by the current user. Shared storage cannot protect your application from code injection attacks.
         参数3 libraryPath：指向包含本地库(so)的文件夹路径，可以设为null
         参数4 parent：父级类加载器，一般可以通过Context.getClassLoader获取到，也可以通过ClassLoader.getSystemClassLoader()取到。
         */
//        DexClassLoader loader = new DexClassLoader(dexPath,
//                dexOutputDir.getAbsolutePath(),
//                null, getClassLoader());
//        try {
//            Class clz = loader.loadClass("com.example.testdextoast.ShowToastImpl");
//            IShowToast impl = (IShowToast) clz.newInstance();
//            impl.showToast(this);
//        } catch (Exception e) {
//            Log.e("starry", "error happened", e);
//        }

        List<PluginBean> list = findAllPlugin();
    }

    /**
     * 查找手机内所有的插件
     *
     * @return 返回一个插件List
     */
    private List<PluginBean> findAllPlugin() {
        List<PluginBean> plugins = new ArrayList<>();
        PackageManager pm = getPackageManager();
        //通过包管理器查找所有已安装的apk文件
        List<PackageInfo> packageInfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        for (PackageInfo info : packageInfos) {
            //得到当前apk的包名
            String pkgName = info.packageName;
            //得到当前apk的sharedUserId
            String shareUesrId = info.sharedUserId;
            //判断这个apk是否是我们应用程序的插件
            if (shareUesrId != null && shareUesrId.equals("com.sunzxyong.myapp") && !pkgName.equals(this.getPackageName())) {
                String label = pm.getApplicationLabel(info.applicationInfo).toString();//得到插件apk的名称
                PluginBean bean = new PluginBean(label, pkgName);
                plugins.add(bean);
            }
        }
        return plugins;
    }
}
