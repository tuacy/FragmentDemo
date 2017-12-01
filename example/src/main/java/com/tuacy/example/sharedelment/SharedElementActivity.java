package com.tuacy.example.sharedelment;

import android.os.Bundle;

import com.tuacy.example.R;
import com.tuacy.example.base.activity.BaseAppActivity;


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
