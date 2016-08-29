package com.starry.loadplugin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.starry.loadplugin.androidx.pluginmgr.PluginManager;
import com.starry.loadplugin.androidx.pluginmgr.environment.PlugInfo;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    PluginManager mgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mgr = PluginManager.getSingleton();
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File myPlug = new File("/mnt/sdcard/pluginApk.apk");
                        try {
                            PlugInfo plug = mgr.loadPlugin(myPlug).iterator().next();
                            mgr.startMainActivity(MainActivity.this, plug);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
