package com.tuacy.fragmentdemo.pager;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class TabStateFragmentAdapter extends FragmentStatePagerAdapter {


	public TabStateFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return null;
	}

	@Override
	public int getCount() {
		return 0;
	}
}
