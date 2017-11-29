package com.tuacy.fragmentdemo.pager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.lang.reflect.Field;

/**
 * 懒加载Fragment
 */

public abstract class LazyFragment extends Fragment {

	protected Context mContext         = null;
	/**
	 * 判断是不是第一次resume
	 */
	private   boolean isFirstResume    = true;
	/**
	 * 判断是不是第一次可见(只会在setUserVisibleHint中判断和改变)
	 */
	private   boolean isFirstVisible   = true;
	/**
	 * 判断是不是第一次不可见(只会在setUserVisibleHint中判断和改变)
	 */
	private   boolean isFirstInvisible = true;
	/**
	 * 标记是否准备加载数据，因为我们不能在setUserVisibleHint马上去加载数据
	 * setUserVisibleHint调用的只会，可能视图都还没有加载出来。
	 */
	private   boolean isPrepared       = false;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mContext = context;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		// for bug ---> java.lang.IllegalStateException: Activity has been destroyed
		try {
			Field childFragmentManager = android.app.Fragment.class.getDeclaredField("mChildFragmentManager");
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


	@Override
	public void onResume() {
		super.onResume();
		if (isFirstResume) {
			isFirstResume = false;
			return;
		}
		/**
		 * 这里的情况是为了避免，比如你锁屏之后再解锁，这个时候也是用户可见的情况
		 * 并且这种情况是不会调用setUserVisibleHint()函数的
		 */
		if (getUserVisibleHint()) {
			onUserVisible();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		/**
		 * 这里的情况是为了避免，比如你锁屏之后载解锁
		 */
		if (getUserVisibleHint()) {
			onUserInvisible();
		}
	}

	/**
	 * setUserVisibleHint 函数第一次调用肯定给的是false，第二次才是true
	 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			if (isFirstVisible) {
				isFirstVisible = false;
				initPrepare();
			} else {
				onUserVisible();
			}
		} else {
			if (isFirstInvisible) {
				isFirstInvisible = false;
				onFirstUserInvisible();
			} else {
				onUserInvisible();
			}
		}
	}

	private synchronized void initPrepare() {
		if (isPrepared) {
			onFirstUserVisible();
		} else {
			isPrepared = true;
		}
	}

	/**
	 * 第一次对用户可见的时候调用，在这里懒加载数据
	 */
	protected abstract void onFirstUserVisible();

	/**
	 * 第二次包括第二次对用户可见的时候调用
	 */
	protected void onUserVisible() {

	}

	/**
	 * 第一次对用户不可见的时候调用
	 */
	protected void onFirstUserInvisible() {

	}

	/**
	 * 第二次包括第二次对用户不可见的时候调用
	 */
	protected void onUserInvisible() {

	}
}
