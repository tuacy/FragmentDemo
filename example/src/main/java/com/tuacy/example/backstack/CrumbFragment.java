package com.tuacy.example.backstack;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tuacy.example.R;

import java.util.ArrayList;

public class CrumbFragment extends ListFragment {

	private static final String BUNDLE_LEVEL = "level";

	public static CrumbFragment instance(int level) {
		CrumbFragment fragment = new CrumbFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(BUNDLE_LEVEL, level);
		fragment.setArguments(bundle);
		return fragment;
	}

	private FragmentManager mFragmentManager;
	private int             mLevel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mFragmentManager = getActivity().getSupportFragmentManager();
		mLevel = 1;
		if (getArguments() != null) {
			mLevel = getArguments().getInt(BUNDLE_LEVEL, 1);
		}

		ArrayList<String> arrayList = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			arrayList.add("Fragment:" + mLevel + " item:" + i);
		}
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
		setListAdapter(arrayAdapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		//设置标题
		transaction.setBreadCrumbTitle("Fragment=:" + (mLevel + 1));
		//替换
		transaction.replace(R.id.layout_content, CrumbFragment.instance(mLevel + 1));
		//添加到回退栈中
		transaction.addToBackStack(null);
		//提交事务
		transaction.commitAllowingStateLoss();
	}

}
