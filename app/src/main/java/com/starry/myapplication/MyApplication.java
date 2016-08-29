package com.starry.myapplication;

import android.app.Application;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

/**
 * Created by starry on 2016/7/4.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        WXEnvironment.addCustomOptions("appName", "TBSample");
        WXSDKEngine.initialize(this, new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build());
    }

    public class ImageAdapter implements IWXImgLoaderAdapter {

        public ImageAdapter() {
        }

        @Override
        public void setImage(final String url, final ImageView view,
                             WXImageQuality quality, WXImageStrategy strategy) {

            WXSDKManager.getInstance().postOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (view == null || view.getLayoutParams() == null) {
                        return;
                    }
                    if (TextUtils.isEmpty(url)) {
                        view.setImageBitmap(null);
                        return;
                    }
                    String temp = url;
                    if (url.startsWith("//")) {
                        temp = "http:" + url;
                    }
                    if (view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
                        return;
                    }
                    Picasso.with(WXEnvironment.getApplication())
                            .load(temp)
                            .into(view);
                }
            }, 0);
        }
    }
}
