package com.starry.apkreaddemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        readString();
        readApk();
    }

    private void readApk() {
        File file = new File(Environment.getExternalStorageDirectory(),"123.apk");
        Log.e("starry","file path = " +file.getAbsolutePath());
        Log.e("starry","file size = " +file.length()/1024.0/1024.0+" MB");
    }

    public void readString() {
        InputStream inputStream;
        try {
            inputStream = this.getResources().getAssets().open("c.apk");
            File file = new File(Environment.getExternalStorageDirectory(),"123.apk");
            Log.e("starry","file path = " +file.getAbsolutePath());

            if(file.exists()){
                file.delete();
            }
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[512];
            int count = 0;
            while ((count = inputStream.read(buffer)) !=-1) {
                fileOutputStream.write(buffer, 0, count);
            }
            Toast.makeText(this, "dddddd", Toast.LENGTH_LONG).show();
            Log.e("starry","file size = " +file.length()/1024.0/1024.0+" MB");

            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();

        } catch (IOException e) {
            Log.e("starry","error = " +e.getMessage());
        }
    }

}
