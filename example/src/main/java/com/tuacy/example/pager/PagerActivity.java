package com.tuacy.example.pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;


import com.tuacy.example.R;
import com.tuacy.example.base.activity.BaseAppActivity;

import java.util.ArrayList;
import java.util.List;


public class PagerActivity extends BaseAppActivity {

	private ViewPager          mPagerFragment;
	private Button             mButtonOne;
	private Button             mButtonTwo;
	private Button             mButtonThree;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pager);
		initView();
		initEvent();
		initData();
	}

	private void initView() {
		mPagerFragment = findViewById(R.id.pager_fragment);
		mButtonOne = findViewById(R.id.button_page_1);
		mButtonTwo = findViewById(R.id.button_page_2);
		mButtonThree = findViewById(R.id.button_page_3);
	}

	private void initEvent() {
		mPagerFragment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				mButtonOne.setSelected(false);
				mButtonTwo.setSelected(false);
				mButtonThree.setSelected(false);
				switch (position) {
					case 0:
						mButtonOne.setSelected(true);
						break;
					case 1:
						mButtonTwo.setSelected(true);
						break;
					case 2:
						mButtonThree.setSelected(true);
						break;
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		mButtonOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPagerFragment.setCurrentItem(0);
			}
		});
		mButtonTwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPagerFragment.setCurrentItem(1);
			}
		});
		mButtonThree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mPagerFragment.setCurrentItem(2);
			}
		});

	}

	private void initData() {
		mPagerFragment.setOffscreenPageLimit(3);
		mPagerFragment.setAdapter(new TabFragmentAdapter(getSupportFragmentManager(), obtainFragments()));
		mPagerFragment.setCurrentItem(0);
	}

	private List<Fragment> obtainFragments() {
		List<Fragment> fragments = new ArrayList<>();
		fragments.add(FragmentPagerOne.instance());
		fragments.add(FragmentPagerTwo.instance());
		fragments.add(FragmentPagerThree.instance());
		return fragments;
	}
}
