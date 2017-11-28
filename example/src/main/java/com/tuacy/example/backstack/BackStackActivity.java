package com.tuacy.example.backstack;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tuacy.example.R;
import com.tuacy.example.base.activity.BaseAppActivity;

public class BackStackActivity extends BaseAppActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_back_stack);
		initView();
	}

	private void initView() {
		RecyclerView recyclerCrumbBar = findViewById(R.id.recycle_crumb_bar);
		recyclerCrumbBar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
		recyclerCrumbBar.setAdapter(new CrumbAdapter(recyclerCrumbBar, mContext, getSupportFragmentManager()));

		//放置一个Fragment
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.setBreadCrumbTitle("Fragment=:1");
		transaction.replace(R.id.layout_content, CrumbFragment.instance(1));
		transaction.addToBackStack(null);
		transaction.commit();
	}
}
