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
        Logs.d("RootLayout   dispatchTouchEvent >>>>>>>"+super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Logs.d("RootLayout   onInterceptTouchEvent >>>>>>>" + super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_UP:
//                Logs.i("RootLayout onTouchEvent 事件 ACTION_UP>>>>>>>>  :"+super.onTouchEvent(event));
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Logs.v("RootLayout onTouchEvent 事件   ACTION_MOVE>>>>>>>>  :"+super.onTouchEvent(event));
//                break;
//            case MotionEvent.ACTION_DOWN:
//                Logs.d("RootLayout onTouchEvent 事件  ACTION_DOWN>>>>>>>>  :"+super.onTouchEvent(event));
//                break;
//        }

        return super.onTouchEvent(event);
    }
}