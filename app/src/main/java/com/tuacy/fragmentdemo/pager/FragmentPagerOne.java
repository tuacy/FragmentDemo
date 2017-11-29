package com.tuacy.fragmentdemo.pager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuacy.fragmentdemo.R;

public class FragmentPagerOne extends LazyFragment {

	public static FragmentPagerOne instance() {
		return new FragmentPagerOne();
	}

	private static final String TAG = "vae";

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_pager_one, container, false);
	}

	@Override
	protected void onFirstUserVisible() {
		Log.d(TAG, "one onFirstUserVisible");
	}

	@Override
	protected void onFirstUserInvisible() {
		super.onFirstUserInvisible();
		Log.d(TAG, "one onFirstUserInvisible");
	}

	@Override
	protected void onUserVisible() {
		super.onUserVisible();
		Log.d(TAG, "one onUserVisible");
	}

	@Override
	protected void onUserInvisible() {
		super.onUserInvisible();
		Log.d(TAG, "one onUserInvisible");
	}

}
