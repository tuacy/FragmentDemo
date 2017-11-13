package com.tuacy.fragmentdemo.pager;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuacy.fragmentdemo.R;

public class FragmentPagerTwo extends Fragment {

	public static FragmentPagerTwo instance() {
		return new FragmentPagerTwo();
	}

	private static final String TAG = "vae";

	@Override
	public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
		super.onInflate(context, attrs, savedInstanceState);
		Log.d(TAG, "two onInflate");
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Log.d(TAG, "two onAttach");
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "two onCreate");
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "two onCreateView");
		return inflater.inflate(R.layout.fragment_pager_two, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		Log.d(TAG, "two onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "two onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "two onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "two onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "two onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.d(TAG, "two onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "two onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.d(TAG, "two onDetach");
	}
}
