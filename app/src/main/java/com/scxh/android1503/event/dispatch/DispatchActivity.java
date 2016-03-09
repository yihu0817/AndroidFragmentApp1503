package com.scxh.android1503.event.dispatch;

import android.app.Activity;
import android.os.Bundle;

import com.scxh.android1503.R;

/**
 * android系统中的每个View的子类都具有下面三个和TouchEvent处理密切相关的方法
 * (注：onInterceptTouchEvent方法属于ViewGroup)：

 1）public boolean dispatchTouchEvent(MotionEvent ev)  这个方法用来分发TouchEvent

 2）public boolean onInterceptTouchEvent(MotionEvent ev) 这个方法用来拦截TouchEvent

 3）public boolean onTouchEvent(MotionEvent ev) 这个方法用来处理TouchEvent

 当TouchEvent发生时，首先Activity将TouchEvent传递给最顶层的View， TouchEvent最先到达最顶层
 view 的 dispatchTouchEvent ，然后由  dispatchTouchEvent 方法进行分发，如果dispatchTouchEvent返回true ，
 则执行该View,dispatchTouchEvent方法中下一个TouchEvent事件（down,move,up）(如果该view有父类直接执行父类
 dispatchTouchEvent方法)，如果dispatchTouchEvent返回 false ，
 则交给这个 view 的 interceptTouchEvent 方法来决定是否要拦截这个事件，
 如果 interceptTouchEvent 返回 true ，也就是拦截掉了，则交给它的 onTouchEvent 来处理，
 如果 interceptTouchEvent 返回 false ，那么就传递给子 view ，由子 view 的 dispatchTouchEvent
 再来开始这个事件的分发。如果事件传递到某一层的子 view 的 onTouchEvent 上了，
 这个方法返回了 false ，那么这个事件会从这个 view 往上传递，都是 onTouchEvent 来接收。
 而如果传递到最上面的 onTouchEvent 也返回 false 的话，这个事件就会“消失”，而且接收不到下一次事件。


 dispatchTouchEvent,onInterceptTouchEvent由外向内传
 onTouchEvent由内向外传


 1.理解dispatchTouchEvent,onInterceptTouchEvent ,onTouchEvent
 2.事件传递和处理方向，
 3.dispatchTouchEvent,onInterceptTouchEvent ,onTouchEvent 每个方法具体功能
 */

/**
 一、全部使用默认值情况
 03-08 02:42:59.000 10114-10114/com.scxh.android1503 D/tag: RootLayout   dispatchTouchEvent >>>>>>>:0
 03-08 02:42:59.000 10114-10114/com.scxh.android1503 D/tag: RootLayout   onInterceptTouchEvent >>>>>>> :0
 03-08 02:42:59.000 10114-10114/com.scxh.android1503 D/tag: TwoLayout   dispatchTouchEvent >>>>>>>:0
 03-08 02:42:59.000 10114-10114/com.scxh.android1503 D/tag: TwoLayout   onInterceptTouchEvent >>>>>>>:0
 03-08 02:42:59.000 10114-10114/com.scxh.android1503 D/tag: ChildTextView   dispatchTouchEvent >>>>>>>:0
 03-08 02:42:59.000 10114-10114/com.scxh.android1503 D/tag: ChildTextView   onTouchEvent >>>>>>>:0
 03-08 02:42:59.000 10114-10114/com.scxh.android1503 D/tag: TwoLayout   onTouchEvent >>>>>>>:0
 03-08 02:42:59.000 10114-10114/com.scxh.android1503 D/tag: RootLayout   onTouchEvent >>>>>>>:0

 二、twoLayout onInterceptTouchEvent返回true情况
 03-08 03:01:11.604 10124-10124/com.scxh.android1503 D/tag: RootLayout   dispatchTouchEvent >>>>>>>:0
 03-08 03:01:11.604 10124-10124/com.scxh.android1503 D/tag: RootLayout   onInterceptTouchEvent >>>>>>> :0
 03-08 03:01:11.604 10124-10124/com.scxh.android1503 D/tag: TwoLayout    dispatchTouchEvent >>>>>>>:0
 03-08 03:01:11.604 10124-10124/com.scxh.android1503 D/tag: RootLayout   dispatchTouchEvent >>>>>>>:1
 03-08 03:01:11.604 10124-10124/com.scxh.android1503 D/tag: RootLayout   onInterceptTouchEvent >>>>>>> :1
 03-08 03:01:11.604 10124-10124/com.scxh.android1503 D/tag: TwoLayout   dispatchTouchEvent >>>>>>>:1

 三、twoLayout dispatchTouchEvent返回true情况
 03-08 03:01:11.604 10124-10124/com.scxh.android1503 D/tag: RootLayout   dispatchTouchEvent >>>>>>>:0
 03-08 03:01:11.604 10124-10124/com.scxh.android1503 D/tag: RootLayout   onInterceptTouchEvent >>>>>>> :0
 03-08 03:01:11.604 10124-10124/com.scxh.android1503 D/tag: TwoLayout   dispatchTouchEvent >>>>>>>:0
 03-08 03:01:11.604 10124-10124/com.scxh.android1503 D/tag: TwoLayout   onInterceptTouchEvent >>>>>>>:0
 03-08 03:01:11.604 10124-10124/com.scxh.android1503 D/tag: TwoLayout   onTouchEvent >>>>>>>:0
 03-08 03:01:11.604 10124-10124/com.scxh.android1503 D/tag: RootLayout   onTouchEvent >>>>>>>:0
 */
public class DispatchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_layout);
    }
}
