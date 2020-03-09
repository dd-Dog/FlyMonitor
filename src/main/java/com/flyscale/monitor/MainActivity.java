package com.flyscale.monitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.flyscale.monitor.net.HttpEngine;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //TODO test for upload function
    public void upload(View view) {
        new Thread() {
            @Override
            public void run() {
                String url = "http://192.168.1.104:8080/FlyMonitor/servlet/upload";
//                String filename = "evo.jpg";
                String filename = "tonghuazhen.mp3";
                // external=/storage/emulated/0
                String path = Environment.getExternalStorageDirectory().getPath() + "/bianjb/" + filename;
                try {
                    HttpEngine.upload(url, path, filename);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void test(View view){
        new Thread(){
            @Override
            public void run() {
                String url = "http://192.168.1.104:8080/FlyMonitor/index.jsp";
                HttpEngine.test(url);
            }
        }.start();
    }

}
