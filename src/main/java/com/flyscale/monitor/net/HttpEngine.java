package com.flyscale.monitor.net;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpEngine {
    private static final String TAG = HttpEngine.class.getName();

    private static OkHttpClient client = null;

    static {
        client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(5 * 60, TimeUnit.SECONDS)
                .build();
    }

    /**
     * @param url      上传url
     * @param filePath 本地文件路径
     * @param fileName 文件名
     * @return
     * @throws Exception
     */
    public static void upload(String url, String filePath, String fileName) throws Exception {
        Log.d(TAG, "upload,url=" + url + ",filePath=" + filePath + ",fileName=" + fileName);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", fileName, RequestBody.create(MediaType.parse("multipart/form-data"), new File(filePath)))
                .build();
        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + UUID.randomUUID())
                .url(url)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure:" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse:" + response.toString());
            }
        });

    }

    public static void test(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        //3. 在Okhttp中创建Call 对象，将request和Client进行绑定
        //4. 执行Call对象（call 是interface 实际执行的是RealCall）中的`execute`方法
        try {
            Response response = client.newCall(request).execute();
            Log.d(TAG, response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
