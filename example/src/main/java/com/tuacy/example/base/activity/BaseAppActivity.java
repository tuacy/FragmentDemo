package com.tuacy.example.base.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseAppActivity extends AppCompatActivity {

	protected Context  mContext;
	protected Activity mActivity;
	private   Handler  mMainHandler;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		mActivity = this;
	}

	protected Handler getMainHandler() {
		if (mMainHandler == null) {
			mMainHandler = new Handler(Looper.getMainLooper());
		}
		return mMainHandler;
	}

}
