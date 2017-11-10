package com.tuacy.fragmentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tuacy.fragmentdemo.base.activity.BaseAppActivity;
import com.tuacy.fragmentdemo.dynamic.DynamicActivity;
import com.tuacy.fragmentdemo.pager.PagerActivity;
import com.tuacy.fragmentdemo.statics.StaticActivity;

public class MainActivity extends BaseAppActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button_fragment_static).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mContext.startActivity(new Intent(mContext, StaticActivity.class));
			}
		});

		findViewById(R.id.button_fragment_dynamic).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mContext.startActivity(new Intent(mContext, DynamicActivity.class));
			}
		});

		findViewById(R.id.button_fragment_pager).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mContext.startActivity(new Intent(mContext, PagerActivity.class));
			}
		});
	}
}
