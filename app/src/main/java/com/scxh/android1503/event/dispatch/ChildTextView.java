package com.scxh.android1503.event.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.scxh.android1503.util.Logs;

//ViewGroup View
public class ChildTextView extends TextView {

    public ChildTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logs.d("ChildTextView   dispatchTouchEvent >>>>>>>:" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Logs.d("ChildTextView   onTouchEvent >>>>>>>:" + ev.getAction());
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                return super.onTouchEvent(ev);
            case MotionEvent.ACTION_UP:
                return true;
        }
        return true;//super.onTouchEvent(ev);
    }

}