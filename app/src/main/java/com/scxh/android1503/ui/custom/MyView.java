package com.scxh.android1503.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Logs;

public class MyView extends View {
    private String content;
    private boolean isShow;
    private int mTextColor;
    private int backgroundres;
    private int myTextSize;
    private int mTextHeight = 20;
    private Paint mTextPaint, mPiePaint, mShadowPaint;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = null;
        try {
            a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyView, 0, 0);
            content = a.getString(R.styleable.MyView_content);
            isShow = a.getBoolean(R.styleable.MyView_isshow, false);
            mTextColor = a.getColor(R.styleable.MyView_textcolor, 0);
            backgroundres = a.getResourceId(R.styleable.MyView_mybackground, 0);
            myTextSize = a.getDimensionPixelSize(R.styleable.MyView_textsize, R.dimen.activity_horizontal_margin);
        } finally {
            a.recycle();
        }


        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);

        mTextPaint.setTextSize(mTextHeight);

        mPiePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPiePaint.setStyle(Paint.Style.FILL);
        mPiePaint.setTextSize(mTextHeight);

        mShadowPaint = new Paint(0);
        mShadowPaint.setColor(0xff101010);
        mShadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(myTextSize);

        Rect srcRt = new Rect();
        Rect endRt = new Rect();
        srcRt.set(40, 50, 400, 300);
        endRt.set(40, 50, 400, 300);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), backgroundres), srcRt, endRt, new Paint());

        canvas.drawText(content, 50, 100, mPaint);
    }

    /**
     * onSizeChanged()，当你的view第一次被赋予一个大小时，或者你的view大小被更改时会被执行
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 精确的控制你的view的大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logs.v("onTouchEvent >>>>>>>");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Logs.v("按下事件");
                break;
            case MotionEvent.ACTION_UP:
                Logs.v("弹起事件");
                break;
            case MotionEvent.ACTION_MOVE:
                Logs.v("移动事件");
                break;
        }
        return true;

    }
}
