package com.tuacy.example.baseuse;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuacy.example.R;

public class BaseUseFragment extends Fragment {

	private static final String BUNDLE_FLAG = "flag";

	public static BaseUseFragment instance(String flag) {
		BaseUseFragment fragment = new BaseUseFragment();
		Bundle bundle = new Bundle();
		bundle.putString(BUNDLE_FLAG, flag);
		fragment.setArguments(bundle);
		return fragment;
	}

	public BaseUseFragment() {
		super();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_base_use, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		TextView textFlag = view.findViewById(R.id.text_base_use_flag);
		textFlag.setText(getArguments().getString(BUNDLE_FLAG));
	}
}
