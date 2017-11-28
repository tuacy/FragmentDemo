package com.tuacy.fragmentdemo.crumb;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.tuacy.fragmentdemo.R;
import com.tuacy.fragmentdemo.base.activity.BaseAppActivity;

public class CrumbActivity extends BaseAppActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crumb);

		CrumbView crumbView = findViewById(R.id.crumb_view);
		crumbView.setActivity(this);

		int firstLevel = 1;
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.setBreadCrumbTitle(getString(R.string.crumb_title, firstLevel));
		ft.replace(R.id.frag_container, CrumbFragment.getInstance(firstLevel));
		ft.addToBackStack(null);
		ft.commit();

	}
}
