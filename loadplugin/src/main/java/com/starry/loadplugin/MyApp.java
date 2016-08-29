package com.starry.loadplugin;

import android.app.Application;

import com.starry.loadplugin.androidx.pluginmgr.PluginManager;

/**
 * Created by starry on 2016/8/25.
 */
public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        PluginManager.init(this);
    }
}
