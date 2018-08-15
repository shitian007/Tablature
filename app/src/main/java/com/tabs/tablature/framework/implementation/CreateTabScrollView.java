package com.tabs.tablature.framework.implementation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class CreateTabScrollView extends ScrollView {

    boolean scrollable = true;

    public CreateTabScrollView(Context context) {
        super(context);
    }

    public CreateTabScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CreateTabScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (scrollable) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (scrollable) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }

    public void enableScrolling() {
        this.scrollable = true;
    }

    public void disableScrolling() {
        this.scrollable = false;
    }

}
