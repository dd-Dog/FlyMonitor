package com.flyscale.monitor.util;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.text.DecimalFormat;

public class SDCardStatusUtil {
    double GB = 1024.0 * 1024.0 * 1024.0;
    double MB = 1024.0 * 1024.0;
    double KB = 1024.0;




    /*
    * SD卡是否存在
    *
    * */
    public static boolean ExistSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }

    /*
    * SDcard路径
    * */
    public static File sdcardDir(){
        return Environment.getExternalStorageDirectory();
    }

    /*
    * StatFs查看文件系统空间使用情况
    * */
    public static StatFs statFs(){
        StatFs statFs=new StatFs(Environment.getDataDirectory().getPath());
        return statFs;
    }

    /*
    * Block的size
    * */
    public static int blockSize(){
        return statFs().getBlockSize();
    }

    /*
    * 总Block的数量
    * */
    public static long totalBlocks(){
        String totalBlocks =getFileSizeDescription(statFs().getBlockCountLong()) ;
        return statFs().getBlockCountLong();
    }

    /*
    * 已使用的Block数量
    * */
    public static long availableBlocks(){
        String availableBlocks = getFileSizeDescription(statFs().getAvailableBlocksLong());
        return statFs().getAvailableBlocksLong();
    }

    /*
    * 总内存大小
    * */
    public static String getMemorySize(){
        String memorySize = getFileSizeDescription(totalBlocks()*blockSize());
        return memorySize;
    }

    /*
    * 已使用的内存大小
    * */
    public static String getUsedMemorySize(){
        String usedMemorySize = getFileSizeDescription(availableBlocks()*blockSize());
        return usedMemorySize;
    }


    /*
    * 将字节转换成对应的单位
    * */

    public static String getFileSizeDescription(long size) {
        //定义GB/MB/KB的计算常量
        double GB = 1024.0 * 1024.0 * 1024.0;
        double MB = 1024.0 * 1024.0;
        double KB = 1024.0;
        StringBuffer bytes = new StringBuffer();
        DecimalFormat df = new DecimalFormat("###.00");
        if (size >= GB) {
            double i = (size / GB);
            bytes.append(df.format(i)).append("GB");
        }
        else if (size >= MB) {
            double i = (size / MB);
            bytes.append(df.format(i)).append("MB");
        }
        else if (size >= KB) {
            double i = (size / KB);
            bytes.append(df.format(i)).append("KB");
        }
        else {
            if (size <= 0) {
                bytes.append("0B");
            }
            else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
    }
}
