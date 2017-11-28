package com.tuacy.fragmentdemo.dynamic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tuacy.fragmentdemo.R;
import com.tuacy.fragmentdemo.base.activity.BaseAppActivity;


public class DynamicActivity extends BaseAppActivity {

	private Button               mButtonOne;
	private Button               mButtonTwo;
	private Button               mButtonThree;
	private FragmentDynamicOne   mFragmentDynamicOne;
	private FragmentDynamicTwo   mFragmentDynamicTwo;
	private FragmentDynamicThree mFragmentDynamicThree;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamic);
		initView();
		initEvent();
		initData();
	}

	private void initView() {
		mButtonOne = findViewById(R.id.button_dynamic_1);
		mButtonTwo = findViewById(R.id.button_dynamic_2);
		mButtonThree = findViewById(R.id.button_dynamic_3);
	}

	private void initEvent() {
		mButtonOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getSupportFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.add(R.id.layout_fragment, mFragmentDynamicOne, FragmentDynamicOne.class.getName());
				fragmentTransaction.add(R.id.layout_fragment, mFragmentDynamicTwo, FragmentDynamicTwo.class.getName());
				fragmentTransaction.setPrimaryNavigationFragment(mFragmentDynamicOne);
				fragmentTransaction.commit();
			}
		});
		mButtonTwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		mButtonThree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});

	}

	private void initData() {
		mFragmentDynamicOne = new FragmentDynamicOne();
		mFragmentDynamicTwo = new FragmentDynamicTwo();
		mFragmentDynamicThree = new FragmentDynamicThree();
	}
}
