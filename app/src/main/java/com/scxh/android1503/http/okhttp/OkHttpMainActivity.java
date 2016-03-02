package com.scxh.android1503.http.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Logs;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_main_layout);
    }

    /**
     * 同步Get请求
     *
     * @param v
     */
    public void onGetRequestClientLisentner(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("http://www.warmtel.com:8080/around")
                            .build();

                    Response response = okHttpClient.newCall(request).execute();

                    if (response.isSuccessful()) {
                        String str = response.body().string();
                        Logs.e("str :" + str);
                    } else {
                        Logs.e("请求出错");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 异步Get请求
     *
     * @param v
     */
    public void onGetRequestAsyncClientLisentner(View v) {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://www.warmtel.com:8080/around")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logs.e("onFailure 请求出错");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Logs.e("onResponse str :" + str);
            }
        });
    }

    /**
     * 异步Post带参请求
     */
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public void onPostRequestAsyncClientLisentner(View v) {
        String url = "http://192.168.2.132/app/jsonurl";
        String json = "{username:admin,password:123456}";

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logs.e("onFailure 请求出错");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Logs.e("onResponse str :" + str);
            }
        });
    }

    /**
     * 异步Post表单提交
     *
     * @param v
     */
    public void onPostFromRequestAsyncClientLisentner(View v) {
        String url = "http://192.168.2.132/app/login";


        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(15000, TimeUnit.MICROSECONDS)
                .build();
        FormBody formBody = new FormBody.Builder()
                .add("user", "admin")
                .add("psw", "654321")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .cacheControl(CacheControl.FORCE_CACHE)
                .post(formBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logs.e("onFailure 请求出错");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Logs.e("onResponse str :" + str);
            }
        });
    }

    public void onPostCacheRequestAsyncClientLisentner(View view) {
        final String url = "http://www.warmtel.com:8080/keyConfig";

        Request request = new Request.Builder().url(url).cacheControl(CacheControl.FORCE_CACHE).build();

        OkHttpManager.getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("oktag", "onFailure>>>> 本地   :" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 504) {
                    //OkHttp如果缓存请求不到是会报504的
//                    Log.e("oktag","OkHttp如果缓存请求不到是会报504的 ");
                    Request request = new Request.Builder().url(url).cacheControl(CacheControl.FORCE_NETWORK).build();
                    OkHttpManager.getOkHttpClient().newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("oktag", "onFailure>>>> 网络  :" + e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.e("oktag", "onResponse>>>>网络  :" + response.body().string());
                        }
                    });
                } else {
                    Log.e("oktag", "onResponse>>>>本地  :" + response.body().string());
                }
            }
        });
    }

    public void onCacheRequestAsyncClientLisentner(View view) {
        String url = "http://www.warmtel.com:8080/around";
        Request.Builder builder = new Request.Builder().url(url);
        OkHttpManager.executeRequest(CacheType.CACHED_ELSE_NETWORK, builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("oktag", "onFailure>>>>   :" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("oktag", "onResponse>>>>  :" + response.body().string());
            }
        });

    }
}
