package com.scxh.android1503.event.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.scxh.android1503.util.Logs;


public class TwotLayout extends LinearLayout {

    public TwotLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logs.d("TwoLayout   dispatchTouchEvent >>>>>>>:" + ev.getAction());

        return  super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Logs.d("TwoLayout   onInterceptTouchEvent >>>>>>>:" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Logs.d("TwoLayout   onTouchEvent >>>>>>>:" + ev.getAction());
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }

        return super.onTouchEvent(ev);
    }

}