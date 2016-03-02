package com.scxh.android1503.http.okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by viktor on 2016/3/2.
 */
 interface OKHttpRequestCallBack{
    void onFailure(Call c,IOException e);

    void onResponse(Response response) throws IOException;
}
