package com.tuacy.fragmentdemo.dynamic;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuacy.fragmentdemo.R;

public class FragmentDynamicThree extends Fragment {


	private static final String TAG = "vae";

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Log.d(TAG, "three onAttach");
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "three onCreate");
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "three onCreateView");
		return inflater.inflate(R.layout.fragment_dynamic_three, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		Log.d(TAG, "three onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "three onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "three onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "three onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "three onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.d(TAG, "three onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "three onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.d(TAG, "three onDetach");
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		Log.d(TAG, "three hidden = " + hidden);
	}
}
