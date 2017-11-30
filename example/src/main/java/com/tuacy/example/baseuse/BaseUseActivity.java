package com.tuacy.example.baseuse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.tuacy.example.R;
import com.tuacy.example.base.activity.BaseAppActivity;

import java.util.List;


public class BaseUseActivity extends BaseAppActivity {

	private TextView        mTextCount;
	private BaseUseFragment mFragmentA;
	private BaseUseFragment mFragmentB;
	private BaseUseFragment mFragmentC;
	private BaseUseFragment mFragmentD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_use);
		initView();
		initData();
	}

	private void initView() {
		mTextCount = findViewById(R.id.text_fragment_count);
		findViewById(R.id.button_add_B).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addB();
				updateFragmentCount();
			}
		});

		findViewById(R.id.button_remove_B).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				removeB();
				updateFragmentCount();
			}
		});

		findViewById(R.id.button_hide_B).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				hideB();
				updateFragmentCount();
			}
		});

		findViewById(R.id.button_show_B).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showB();
				updateFragmentCount();
			}
		});

		findViewById(R.id.button_attach_B).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				attachB();
				updateFragmentCount();
			}
		});

		findViewById(R.id.button_detach_B).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				detachB();
				updateFragmentCount();
			}
		});

		findViewById(R.id.button_add_C).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addC();
				updateFragmentCount();
			}
		});

		findViewById(R.id.button_remove_C).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				removeC();
				updateFragmentCount();
			}
		});

		findViewById(R.id.button_hide_C).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				hideC();
				updateFragmentCount();
			}
		});

		findViewById(R.id.button_show_C).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showC();
				updateFragmentCount();
			}
		});

		findViewById(R.id.button_attach_C).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				attachC();
				updateFragmentCount();
			}
		});

		findViewById(R.id.button_detach_C).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				detachC();
				updateFragmentCount();
			}
		});

		findViewById(R.id.button_replace).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				replace();
				updateFragmentCount();
			}
		});
	}

	private void initData() {
		mFragmentA = BaseUseFragment.instance("A");
		mFragmentB = BaseUseFragment.instance("B");
		mFragmentC = BaseUseFragment.instance("C");
		mFragmentD = BaseUseFragment.instance("D");

		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.layout_content_fragment, mFragmentA);
		transaction.add(R.id.layout_content_fragment, mFragmentB);
		transaction.add(R.id.layout_content_fragment, mFragmentC);
		transaction.commitNow();

		updateFragmentCount();
	}

	private void addB() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.layout_content_fragment, mFragmentB);
		transaction.commitNow();
	}

	private void removeB() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.remove(mFragmentB);
		transaction.commitNow();
	}

	private void hideB() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.hide(mFragmentB);
		transaction.commitNow();
	}

	private void showB() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.show(mFragmentB);
		transaction.commitNow();
	}

	private void attachB() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.attach(mFragmentB);
		transaction.commitNow();
	}

	private void detachB() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.detach(mFragmentB);
		transaction.commitNow();
	}

	private void addC() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.layout_content_fragment, mFragmentC);
		transaction.commitNow();
	}

	private void removeC() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.remove(mFragmentC);
		transaction.commitNow();
	}

	private void hideC() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.hide(mFragmentC);
		transaction.commitNow();
	}

	private void showC() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.show(mFragmentC);
		transaction.commitNow();
	}

	private void attachC() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.attach(mFragmentC);
		transaction.commitNow();
	}

	private void detachC() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.detach(mFragmentC);
		transaction.commitNow();
	}

	private void replace() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.layout_content_fragment, mFragmentD);
		transaction.commitNow();
	}

	private void updateFragmentCount() {
		List<Fragment> fragments = getSupportFragmentManager().getFragments();
		int count = fragments == null ? 0 : fragments.size();
		mTextCount.setText("当前Fragment个数 = " + count);
	}
}
