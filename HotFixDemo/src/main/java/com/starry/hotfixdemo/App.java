package com.starry.hotfixdemo;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;

/**
 * Created by starry on 2016/8/29.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 获取补丁，如果存在就执行注入操作
        String dexPath = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/patch_Dex.jar");
        File file = new File(dexPath);
        if (file.exists()) {
            inject(dexPath);
        } else {
            Log.e("hotfix", dexPath + "不存在");
        }
    }

    /**
     * @param dexPath 要注入的dex路径
     */
    private void inject(String dexPath) {
        try {
            // 获取classes的dexElements
            Class<?> clz = Class.forName("dalvik.system.BaseDexClassLoader");
            Object pathList = getField(clz, "pathList", getClassLoader());
            Object baseElements = getField(pathList.getClass(), "dexElements", pathList);

            // 获取patch_Dex的dexElements（需要先加载dex）
            String dexOpt = getDir("dexopt", 0).getAbsolutePath();
            Log.e("hotfix", "dexOpt path = " + dexOpt);

            DexClassLoader dexClassLoader = new DexClassLoader(dexPath, dexOpt, dexOpt, getClassLoader());
            Object dexPathList = getField(clz, "pathList", dexClassLoader);
            Object dexElements = getField(dexPathList.getClass(), "dexElements", dexPathList);

            //合并2个Element
            Object combineElements = combineArray(dexElements, baseElements);

            // 将合并后的Element数组重新赋值给app的classLoader
            setField(pathList.getClass(), "dexElements", pathList, combineElements);

            //======== 测试是否成功注入 =================
            Object object = getField(pathList.getClass(), "dexElements", pathList);
            int length = Array.getLength(object);
            Log.e("hotfix", "length = " + length);//长度为2表示成功

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射获取对象的属性值
     */

    private Object getField(Class<?> cls, String fieldName, Object obj) throws NoSuchFieldException, IllegalAccessException {
        Field field = cls.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    /**
     * 通过反射设置对象的属性值
     */
    private void setField(Class<?> cls, String fieldName, Object obj, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = cls.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    /**
     * 通过反射合并两个数组
     */
    private Object combineArray(Object firstArr, Object secondArr) {
        int firstLength = Array.getLength(firstArr);
        int secondLength = Array.getLength(secondArr);
        int length = firstLength + secondLength;

        Class<?> componentType = firstArr.getClass().getComponentType();
        Object array = Array.newInstance(componentType, length);
        for (int i = 0; i < length; i++) {
            if (i < firstLength) {
                Array.set(array, i, Array.get(firstArr, i));
            } else {
                Array.set(array, i, Array.get(secondArr, i - firstLength));
            }
        }
        return array;
    }
}
