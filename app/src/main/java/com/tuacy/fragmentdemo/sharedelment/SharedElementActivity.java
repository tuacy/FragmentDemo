package com.tuacy.fragmentdemo.sharedelment;

import android.os.Bundle;
import android.view.Window;

import com.tuacy.fragmentdemo.R;
import com.tuacy.fragmentdemo.base.activity.BaseAppActivity;


public class SharedElementActivity extends BaseAppActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_element);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
									   .add(R.id.layout_container, new SharedElementGridFragment())
									   .commit();
		}
	}
}
