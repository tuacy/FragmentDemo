package com.tuacy.gallery;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tuacy.gallery.transformer.ScaleInTransformer;


public class MainActivity extends AppCompatActivity {

	private ViewPager            mViewPager;
	private MultiplePagerAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initEvent();
		initData();
	}

	private void initView() {
		mViewPager = findViewById(R.id.viewPager);
		mViewPager.setPageMargin(20);
	}

	private void initEvent() {

	}

	private void initData() {
		mViewPager.setOffscreenPageLimit(6);
		mViewPager.setPageTransformer(true, new ScaleInTransformer());
		mAdapter = new MultiplePagerAdapter(this);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setCurrentItem(5 * 100 + 2);
	}
}
