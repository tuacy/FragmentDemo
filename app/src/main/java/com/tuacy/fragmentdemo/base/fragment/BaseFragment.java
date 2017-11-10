package com.tuacy.fragmentdemo.base.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment {

	protected Context  mContext;
	protected Activity mActivity;
	protected Fragment mFragment;
	protected boolean  mVisibleRefresh;

	public BaseFragment() {

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
		mActivity = activity;
		mFragment = this;
	}

	protected abstract
	@LayoutRes
	int getLayoutId();

	protected abstract void initViews(View root);

	protected abstract void initListeners();

	protected abstract void initData();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(getLayoutId(), container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViews(view);
		initListeners();
		initData();
	}

	/**
	 * 当fragment已经显示的时候，由于外部作用需要刷新fragment的内容
	 */
	public void onVisibleRefresh(Object... objs) {
		mVisibleRefresh = true;
	}

	private Fragment mCurrentFragment;

	protected void showFragment(int layoutId, Class<? extends Fragment> clz) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		try {
			Fragment f;
			if ((f = fm.findFragmentByTag(clz.getName())) == null) {
				f = clz.newInstance();
				ft.add(layoutId, f, clz.getName());
			}
			ft.show(f).commit();
		} catch (Exception e) {
		}
	}

	protected void replaceFragment(int layoutId, Class<? extends Fragment> clz) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		try {
			Fragment f;
			if ((f = fm.findFragmentByTag(clz.getName())) == null) {
				f = clz.newInstance();
			}
			ft.replace(layoutId, f, clz.getName()).show(f).commit();
		} catch (Exception e) {
		}
	}

	protected void replaceFragment(int layoutId, Class<? extends Fragment> clz, Bundle args) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		try {
			Fragment f;
			if ((f = fm.findFragmentByTag(clz.getName())) == null) {
				f = clz.newInstance();
			}
			f.setArguments(args);
			ft.replace(layoutId, f, clz.getName()).show(f).commit();
		} catch (Exception e) {
		}
	}

	protected void addFragment(int layoutId, Class<? extends Fragment> clz) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		try {
			Fragment f = clz.newInstance();
			ft.add(layoutId, f, clz.getName()).show(f).commit();
		} catch (Exception e) {
		}
	}

	protected void addFragment(int layoutId, Class<? extends Fragment> clz, int enterAnim, int exitAnim) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		try {
			Fragment f = clz.newInstance();
			ft.add(layoutId, f, clz.getName()).setCustomAnimations(enterAnim, exitAnim).show(f).commit();
		} catch (Exception e) {
		}
	}

	protected void removeFragment(int layoutId) {
		FragmentManager fm = getFragmentManager();
		if (fm == null) {
			return;
		}
		FragmentTransaction ft = fm.beginTransaction();
		Fragment f = fm.findFragmentById(layoutId);
		if (f != null) {
			ft.remove(f).commit();
		}
	}

	protected void removeFragment(Class<? extends Fragment> clz) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Fragment f = fm.findFragmentByTag(clz.getName());
		if (f != null) {
			ft.remove(f).commit();
		}
	}

	protected void hideFragment(int layoutId) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Fragment f = fm.findFragmentById(layoutId);
		if (f != null) {
			ft.hide(f).commit();
		}
	}

	protected void hideFragment(Class<? extends Fragment> clz) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Fragment f = fm.findFragmentByTag(clz.getName());
		if (f != null) {
			ft.hide(f).commit();
		}
	}

	protected Fragment getFragmentById(int layoutId) {
		FragmentManager fm = getFragmentManager();
		return fm.findFragmentById(layoutId);
	}

	protected void switchFragment(int layoutId, Fragment fragment) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		if (mCurrentFragment == null) {
			ft.add(layoutId, fragment).commit();
		} else if (mCurrentFragment != fragment) {
			if (!fragment.isAdded()) {
				ft.hide(mCurrentFragment).add(layoutId, fragment).commit();
			} else {
				ft.hide(mCurrentFragment).show(fragment).commit();
			}
		}
		mCurrentFragment = fragment;
	}
}
