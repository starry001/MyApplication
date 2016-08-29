package com.starry.jsontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    String URL = "http://zh.yomobi.net:8080/get/sales_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn);


        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        myThread();

    }

    private void myThread() {


        new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient ok = new OkHttpClient();

                final RequestBody bd = new FormBody.Builder()
                        .add("imsi", "460017186295456")
                        .build();
                Request request = new Request.Builder()
                        .url(URL)
                        .post(bd)
                        .build();

                ok.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(okhttp3.Call call, Response response) throws IOException {
                        Gson gson = new Gson();
                        Bean bean = gson.fromJson(response.body().string(), Bean.class);

                        Log.e("vv", "成功》》" + bean.toString());
                    }
                });


            }
        }).start();
    }
}
