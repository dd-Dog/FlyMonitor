package com.flyscale.monitor.util;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

public class PhoneManagerUtil {


    /*
    * 获取SDK版本信息
    * */
    public static String getApiVersion(){
        return Build.VERSION.SDK;
    }

    /*
    * 获取系统版本
    * */
    public static String getReleaseVersion(){
        return Build.VERSION.RELEASE;
    }

    /*
    * 获取设备型号
    * */
    public static String getDeviceModel(){
        return Build.MODEL;
    }

    /*
    * 产品内部代码
    * */
    public static String getProduct(){
        return Build.PRODUCT;
    }

    /*
    * 固件编号
    * */
    public static String getDisplay(){
        return Build.DISPLAY;
    }

    /*
    * IMEI
    * */
    public static String getIMEI(Context context){
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI = null;
        try {
            IMEI = telephonyManager.getDeviceId();
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return IMEI;
    }

    /*
    * IMSI
    * */
    public static String getIMSI(Context context){
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String IMSI = null;
        try {
            IMSI = telephonyManager.getSubscriberId();
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return IMSI;
    }


    public static String getDeviceSN(){

        String serial = null;

        try {

            Class<?> c =Class.forName("android.os.SystemProperties");
            Method get =c.getMethod("get", String.class);
            serial = (String)get.invoke(c, "ro.serialno");
        } catch (Exception e) {

            e.printStackTrace();

        }

        return serial;

    }

    public static String getDeviceSN2(){

        String serialNumber = android.os.Build.SERIAL;

        return serialNumber;
    }


}
