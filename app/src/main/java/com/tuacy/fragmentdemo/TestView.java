package com.tuacy.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;


public abstract class TestView {

	/**
	 * 开启一个事务，用于对Fragment操作的一系列处理
	 */
	public abstract FragmentTransaction beginTransaction();

	/**
	 * 立即执行挂起的事物FragmentTransaction里面的，commit()、popBackStack()都不是立即执行的，
	 * 它会被发送到主线程的任务队列当中去, 当主线程准备好执行它的时候执行.
	 * 但是有时候你希望你的操作是立即执行的，在commit()只会调用该函数。
	 */
	public abstract boolean executePendingTransactions();

	/**
	 * 通过Fragment所在的布局id，查找Fragment.查找规则：
	 * 1. 先在add列表里面查找。记住一定是拿最顶上的那个Fragment, (add A、add B. 这个时候就算你把B隐藏了，拿到的还是B).
	 * 2. 第一步没找到的情况下，接着就去回退栈里面查找。
	 */
	public abstract Fragment findFragmentById(@IdRes int id);

	/**
	 * 通过Fragment的Tag找到Fragment(添加Fragment的时候会保证tag唯一)
	 */
	public abstract Fragment findFragmentByTag(String tag);

	/**
	 * 弹出堆栈中顶部的Fragment并且显示，类似按下返回键的操作(不是立即执行的，
	 * 它会被发送到主线程的任务队列当中去, 当主线程准备好执行它的时候执行)
	 */
	public abstract void popBackStack();

	/**
	 * 弹出堆栈中顶部的Fragment并且显示(立即执行)
	 */
	public abstract boolean popBackStackImmediate();

	/**
	 * name可以为null或者相对应的BackStackEntry 的名字(在FragmentTransaction的addToBackStack()可以设置该名字)，flags只有0和1(POP_BACK_STACK_INCLUSIVE)两种情况
	 * 1. 如果name为null，flags为0时，弹出回退栈中最上层的那个fragment。
	 * 2. 如果name为null ，flags为1时，弹出回退栈中所有fragment。
	 * 3. 如果name不为null，flags为0时,那就会找到这个tag所对应的fragment，弹出该fragment以上的Fragment，
	 * 4. 如果name不为null，flag为是1，弹出该fragment（包括该fragment）以上的fragment。
	 */
	public abstract void popBackStack(String name, int flags);

	/**
	 * 同上（唯一的区别就是会立即执行）
	 */
	public abstract boolean popBackStackImmediate(String name, int flags);

	/**
	 * 与popBackStack(String name, int flags)类似，id是BackStackEntry对应的id
	 */
	public abstract void popBackStack(int id, int flags);

	/**
	 * 同上
	 */
	public abstract boolean popBackStackImmediate(int id, int flags);

	/**
	 * 得到回退栈中BackStackEntry的数量
	 */
	public abstract int getBackStackEntryCount();

	/**
	 * 根据序号返回后台堆栈中的BackStackEntry对象(按照添加回退栈的顺序)
	 */
	public abstract FragmentManager.BackStackEntry getBackStackEntryAt(int index);

	/**
	 * 为添加回退堆栈添加一个监听器，用于监听堆栈的改变情况
	 */
	public abstract void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener listener);

	/**
	 * 移除监听堆栈的监听器
	 */
	public abstract void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener listener);

	/**
	 * 把一个Fragment对象放入到bundle中， 和getFragment函数对应。比如你可以在onSaveInstanceState()函数中调用该函数
	 * 保存一个Fragment
	 */
	public abstract void putFragment(Bundle bundle, String key, Fragment fragment);

	/**
	 * 根据key从bundle中取出之前putFragment()的Fragment。比如你可以在onRestoreInstanceState()函数中调用该函数
	 * 恢复之前保存的Fragment
	 */
	public abstract Fragment getFragment(Bundle bundle, String key);

	/**
	 * 得到加入FragmentManager中所有的Fragment(这些Fragment都是通过FragmentTransaction加入)。
	 * 不包括回退栈中，以及已经detached或者removed掉的。
	 */
	public abstract List<Fragment> getFragments();

	/**
	 * 保存给定Fragment的当前实例状态，返回值得到的状态可以用Fragment的
	 * setInitialSavedState()方法设置给新的Fragment实例, 作为初始状态.
	 * 当如这个函数的使用也是有限制的：
	 * 1. 保持状态的Fragment必须attach到FragmentManager中。
	 * 2. 新创建的Fragment必须和状态对应的Fragment相同的class类型。
	 * 3. 保存状态的Fragment不能依赖其他的Fragment，并且不能使用 putFragment(Bundle, String, Fragment)函数
	 */
	public abstract Fragment.SavedState saveFragmentInstanceState(Fragment f);

	/**
	 * Activity的onDestroy()是否调用
	 */
	public abstract boolean isDestroyed();

	/**
	 * 注册监听FragmentManager中所有Fragment的生命周期
	 */
	public abstract void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks cb, boolean recursive);

	/**
	 * 注销监听FragmentManager中所有Fragment的生命周期
	 */
	public abstract void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks cb);

	/**
	 * 返回FragmentManager里面当前活动的主导航Fragment。
	 */
	public abstract Fragment getPrimaryNavigationFragment();

}
