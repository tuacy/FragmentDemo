package com.tuacy.gallery;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class ViewPagerWrap extends FrameLayout {

	private ViewPager mViewPager;

	public ViewPagerWrap(@NonNull Context context) {
		this(context, null);
	}

	public ViewPagerWrap(@NonNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ViewPagerWrap(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_pager_wrap, this, true);
		mViewPager = findViewById(R.id.view_pager_content);

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return mViewPager.dispatchTouchEvent(ev);
	}

	public ViewPager getViewPager() {
		return mViewPager;
	}
}
