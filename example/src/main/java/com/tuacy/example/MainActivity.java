package com.tuacy.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tuacy.example.argument.ArgumentActivity;
import com.tuacy.example.backstack.BackStackActivity;
import com.tuacy.example.base.activity.BaseAppActivity;
import com.tuacy.example.baseuse.BaseUseActivity;

public class MainActivity extends BaseAppActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {

		findViewById(R.id.button_base_use).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mContext.startActivity(new Intent(mContext, BaseUseActivity.class));
			}
		});

		findViewById(R.id.button_back_stack).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mContext.startActivity(new Intent(mContext, BackStackActivity.class));
			}
		});

		findViewById(R.id.button_argument).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mContext.startActivity(new Intent(mContext, ArgumentActivity.class));
			}
		});

	}
}
