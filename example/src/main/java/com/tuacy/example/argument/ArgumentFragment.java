package com.tuacy.example.argument;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuacy.example.R;

public class ArgumentFragment extends Fragment {

	private static final String TAG         = "tuacy";
	private static final String BUNDLE_FLAG = "flag";

	public static ArgumentFragment instance(String flag) {
		ArgumentFragment fragment = new ArgumentFragment();
		Bundle bundle = new Bundle();
		bundle.putString(BUNDLE_FLAG, flag);
		fragment.setArguments(bundle);
		return fragment;
	}

	private String mTitle;

	public void setTitle(String title) {
		mTitle = title;
	}


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
		return inflater.inflate(R.layout.fragment_argument, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		TextView textFlag = view.findViewById(R.id.text_base_use_flag);
//		textFlag.setText(mTitle != null ? mTitle : "null");
		textFlag.setText(getArguments().getString(BUNDLE_FLAG));
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
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		Log.d(TAG, "one hidden = " + hidden);
	}
}
