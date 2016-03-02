package com.scxh.android1503.http.okhttp;

import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by viktor on 2016/3/2.
 */
public class OKHttpRequest {
    public OKHttpRequest(String url, CacheControl cacheType) {

    }
    private  void request(String url, String method, RequestBody requestBody, final CacheControl cacheControl, Headers headers,Object tag){
        Request.Builder requestBuilder = new Request.Builder().url(url).cacheControl(cacheControl);
        if(headers!=null){
            requestBuilder.headers(headers);
        }
        requestBuilder.method(method,requestBody);
        requestBuilder.tag(tag==null?url:tag);

        final Request request = requestBuilder.build();

    }

}
