package com.tuacy.fragmentdemo;

import android.support.annotation.StringRes;

/**
 * 回退栈里面的每天记录
 */

public interface BackStackEntryTemp {

	/**
	 * 回退栈对应的id(自动分配)
	 */
	public int getId();

	/**
	 * 回退栈对应名字 FragmentTransaction addToBackStack(String)的时候设置
	 */
	public String getName();

	/**
	 * 面包屑对应的名字的资源id，和FragmentTransaction setBreadCrumbTitle(@StringRes int res) 对应
	 * FragmentTransaction addToBackStack(String) 之前调用FragmentTransaction setBreadCrumbTitle(@StringRes int res)
	 */
	@StringRes
	public int getBreadCrumbTitleRes();

	/**
	 * 面包屑对应的名字的资源id，和FragmentTransaction setBreadCrumbShortTitle(@StringRes int res) 对应
	 */
	@StringRes
	public int getBreadCrumbShortTitleRes();

	/**
	 * 面包屑对应的名字的资源id，和FragmentTransaction setBreadCrumbTitle(CharSequence text) 对应
	 */
	public CharSequence getBreadCrumbTitle();

	/**
	 * 面包屑对应的名字的资源id，和FragmentTransaction setBreadCrumbShortTitle(CharSequence text) 对应
	 */
	public CharSequence getBreadCrumbShortTitle();
}
