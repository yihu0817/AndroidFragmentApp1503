package com.scxh.android1503;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class LogCatActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.v("tagv", "用来记录详细信息的Log.v()");
		
		Log.d("tagd", "用来记录调式信息的Log.d()");
		
		Log.i("tagi", "用来记录通知信息的Log.i()");
		
		Log.w("tagw", "用来记录警告信息的Log.w()");
		
		Log.e("tage", "用来记录错误信息的Log.e()");
	}
}
