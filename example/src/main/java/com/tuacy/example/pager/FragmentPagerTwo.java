package com.tuacy.example.pager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuacy.example.R;


public class FragmentPagerTwo extends LazyFragment {

	public static FragmentPagerTwo instance() {
		return new FragmentPagerTwo();
	}

	private static final String TAG = "vae";


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_pager_two, container, false);
	}

	@Override
	protected void onFirstUserVisible() {
		Log.d(TAG, "two onFirstUserVisible");
	}

	@Override
	protected void onFirstUserInvisible() {
		super.onFirstUserInvisible();
		Log.d(TAG, "two onFirstUserInvisible");
	}

	@Override
	protected void onUserVisible() {
		super.onUserVisible();
		Log.d(TAG, "two onUserVisible");
	}

	@Override
	protected void onUserInvisible() {
		super.onUserInvisible();
		Log.d(TAG, "two onUserInvisible");
	}
}
