package com.starry.greendaodemo;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by starry on 2016/8/29.
 */
public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
