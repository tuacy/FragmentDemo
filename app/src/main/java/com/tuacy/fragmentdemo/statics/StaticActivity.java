package com.tuacy.fragmentdemo.statics;

import android.os.Bundle;
import android.util.Log;

import com.tuacy.fragmentdemo.R;
import com.tuacy.fragmentdemo.base.activity.BaseAppActivity;


public class StaticActivity extends BaseAppActivity {

	private static final String TAG = "tuacy";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_static);
		Log.d(TAG, "static activity onCreate");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "static activity onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "static activity onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "static activity onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "static activity onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "static activity onDestroy");
	}
}
