package com.tuacy.fragmentdemo.base.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;


import java.lang.reflect.Field;

/**
 * used in pager + fragment
 */
public abstract class BaseLazyFragment extends Fragment {

	protected Context mContext         = null;
	private   boolean isFirstResume    = true;
	private   boolean isFirstVisible   = true;
	private   boolean isFirstInvisible = true;
	private   boolean isPrepared       = false;

	protected boolean mVisible;
	protected boolean mVisibleRefresh;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
		mVisible = false;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		// for bug ---> java.lang.IllegalStateException: Activity has been destroyed
		try {
			Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);

		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initPrepare();
	}

	/**
	 * 当fragment已经显示的时候，由于外部作用需要刷新fragment的内容
	 */
	public void onVisibleRefresh(Object... objs) {
		mVisibleRefresh = true;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (isFirstResume) {
			isFirstResume = false;
			return;
		}
		if (getUserVisibleHint()) {
			onUserVisible();
			mVisible = true;
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (getUserVisibleHint()) {
			onUserInvisible();
			mVisible = false;
		}
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			if (isFirstVisible) {
				isFirstVisible = false;
				initPrepare();
			} else {
				onUserVisible();
				mVisible = true;
			}
		} else {
			if (isFirstInvisible) {
				isFirstInvisible = false;
				onFirstUserInvisible();
				mVisible = false;
			} else {
				onUserInvisible();
				mVisible = false;
			}
		}
	}

	private synchronized void initPrepare() {
		if (isPrepared) {
			onFirstUserVisible();
			mVisible = true;
		} else {
			isPrepared = true;
		}
	}

	/**
	 * when fragment is visible for the first time, here we can do some initialized work or refresh data only once
	 */
	protected abstract void onFirstUserVisible();

	/**
	 * this method like the fragment's lifecycle method onResume()
	 */
	protected abstract void onUserVisible();

	/**
	 * when fragment is invisible for the first time
	 */
	private void onFirstUserInvisible() {
		// here we do not recommend do something
		onUserInvisible();
	}

	/**
	 * this method like the fragment's lifecycle method onPause()
	 */
	protected abstract void onUserInvisible();

	protected void showFragment(int layoutId, Class<? extends Fragment> clz) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		//		ft.setCustomAnimations(R.animator.fragment_left_enter, R.animator.fragment_right_out, R.animator.fragment_pop_right_enter,
		//							   R.animator.fragment_pop_left_out);
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
		//		ft.setCustomAnimations(R.animator.fragment_left_enter, R.animator.fragment_right_out, R.animator.fragment_pop_right_enter,
		//							   R.animator.fragment_pop_left_out);
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
		//		ft.setCustomAnimations(R.animator.fragment_left_enter, R.animator.fragment_right_out, R.animator.fragment_pop_right_enter,
		//							   R.animator.fragment_pop_left_out);
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
		//		ft.setCustomAnimations(R.animator.fragment_left_enter, R.animator.fragment_right_out, R.animator.fragment_pop_right_enter,
		//							   R.animator.fragment_pop_left_out);
		try {
			Fragment f = clz.newInstance();
			ft.add(layoutId, f, clz.getName()).show(f).commit();
		} catch (Exception e) {
		}
	}

	protected void addFragment(int layoutId, Class<? extends Fragment> clz, int enterAnim, int exitAnim) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		//		ft.setCustomAnimations(R.animator.fragment_left_enter, R.animator.fragment_right_out, R.animator.fragment_pop_right_enter,
		//							   R.animator.fragment_pop_left_out);
		try {
			Fragment f = clz.newInstance();
			ft.add(layoutId, f, clz.getName()).setCustomAnimations(enterAnim, exitAnim).show(f).commit();
		} catch (Exception e) {
		}
	}

	protected void removeFragment(int layoutId) {
		FragmentManager fm = getFragmentManager();
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

}
