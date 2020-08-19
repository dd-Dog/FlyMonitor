package com.flyscale.monitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.flyscale.monitor.net.HttpEngine;
import com.flyscale.monitor.util.ADBUtils;
import com.flyscale.monitor.util.CompressUtils;
import com.flyscale.monitor.util.NetworkUtil;
import com.flyscale.monitor.util.PhoneManagerUtil;
import com.flyscale.monitor.util.SDCardStatusUtil;

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
                String url = "http://124.71.115.4/FlyMonitor/servlet/upload";
//                String filename = "evo.jpg";
                String filename = "tonghuazhen.mp3";
                // external=/storage/emulated/0
                String path = "/mnt/sdcard/" + filename;
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

    public void NetworkStatus(View view){
        Log.e("fengpj", NetworkUtil.isNetworkConnected(this)+";"+
                NetworkUtil.isWifiConnected(this)+";"+
                NetworkUtil.isMobileConnected(this));
    }

    public void SDcardStatus(View view){
        Log.e("fengpj", "SD卡是否存在"+SDCardStatusUtil.ExistSDCard()
                +"\r\n 路径："+SDCardStatusUtil.sdcardDir().toString()
                +"\r\n 系统空间使用情况 ：" + SDCardStatusUtil.statFs().toString()
                +"\r\n Block的size："+SDCardStatusUtil.blockSize()
                +"\r\n Block的总数量 ：" + SDCardStatusUtil.totalBlocks()
                +"\r\n Block的剩余数量 ：" + SDCardStatusUtil.availableBlocks()
                +"\r\n 内存总量：" + SDCardStatusUtil.getMemorySize()
                +"\r\n 内存已使用量" + SDCardStatusUtil.getUsedMemorySize());
    }

    public void AboutPhone(View view){
        Log.e("fengpj","SDK版本："+ PhoneManagerUtil.getApiVersion()
                +"\r\n 系统版本：" + PhoneManagerUtil.getReleaseVersion()
                +"\r\n 设备型号：" + PhoneManagerUtil.getDeviceModel()
                +"\r\n 产品内部代码：" + PhoneManagerUtil.getProduct()
                +"\r\n 固件编号：" + PhoneManagerUtil.getDisplay()
                +"\r\n IMEI : " + PhoneManagerUtil.getIMEI(this)
                +"\r\n IMSI : " +PhoneManagerUtil.getIMSI(this)
                +"\r\n SN号 :" + PhoneManagerUtil.getDeviceSN2());
    }

    public void FileState(View view){
        Log.e("fengpj","路径是否存在 ： " + CompressUtils.fileIsExists("/data/slog"));
        if (CompressUtils.fileIsExists("/data/slog")){
            try {
                CompressUtils.zip("/data/slog/","/data/slog/");
            }catch (Exception e){
                Log.e("fengpj","压缩失败" + e + "\r\n" + Log.getStackTraceString(new
                        Throwable()));
            }
        }
    }


}
