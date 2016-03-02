package com.scxh.android1503.event.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.scxh.android1503.util.Logs;

/**
 * Created by Administrator on 2016/2/25.
 */
public class TwotLayout extends LinearLayout {

    public TwotLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logs.d("TwotLayout   dispatchTouchEvent >>>>>>>" + super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Logs.d("TwotLayout   onInterceptTouchEvent >>>>>>>" + super.onInterceptTouchEvent(ev));
        return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Logs.i("TwotLayout onTouchEvent 事件 ACTION_UP>>>>>>>> :"+super.onTouchEvent(event));
                break;
            case MotionEvent.ACTION_MOVE:
                Logs.v("TwotLayout onTouchEvent 事件   ACTION_MOVE>>>>>>>> :"+super.onTouchEvent(event));
                break;
            case MotionEvent.ACTION_DOWN:
                Logs.d("TwotLayout onTouchEvent 事件  ACTION_DOWN>>>>>>>> :"+super.onTouchEvent(event));
                break;
        }

        return false;
    }

}