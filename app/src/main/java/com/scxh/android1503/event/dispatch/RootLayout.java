package com.scxh.android1503.event.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.scxh.android1503.util.Logs;

public class RootLayout extends LinearLayout {

    public RootLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logs.d("RootLayout   dispatchTouchEvent >>>>>>>:" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Logs.d("RootLayout   onInterceptTouchEvent >>>>>>> :" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Logs.d("RootLayout   onTouchEvent >>>>>>>:" + ev.getAction());
        return super.onTouchEvent(ev);
    }
}