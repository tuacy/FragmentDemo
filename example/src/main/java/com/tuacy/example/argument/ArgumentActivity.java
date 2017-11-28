package com.tuacy.example.argument;

import android.os.Bundle;
import android.util.Log;

import com.tuacy.example.R;
import com.tuacy.example.base.activity.BaseAppActivity;

public class ArgumentActivity extends BaseAppActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_argument);
		if (savedInstanceState == null) {
			Log.d("tuacy", "no save instance state");
			ArgumentFragment fragment = ArgumentFragment.instance("tuacy");
//			fragment.setTitle("tuacy");
			getSupportFragmentManager().beginTransaction().replace(R.id.layout_content_layout, fragment).commit();
		} else {
			Log.d("tuacy", "save instance state");
		}
	}
}
