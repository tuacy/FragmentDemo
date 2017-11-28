package com.tuacy.fragmentdemo.pager;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuacy.fragmentdemo.R;

public class FragmentPagerOne extends Fragment {

	public static FragmentPagerOne instance() {
		return new FragmentPagerOne();
	}

	private static final String TAG = "vae";

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Log.d(TAG, "one onAttach");
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "one onCreate");
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "one onCreateView");
		return inflater.inflate(R.layout.fragment_pager_one, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		Log.d(TAG, "one onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "one onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "one onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "one onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "one onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.d(TAG, "one onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "one onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.d(TAG, "one onDetach");
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		Log.d(TAG, "one visible = " + isVisibleToUser);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		Log.d(TAG, "one hidden = " + hidden);
	}
}
