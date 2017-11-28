package com.tuacy.fragmentdemo;

import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;


public abstract class TranslaterTemp {

	/**
	 * 添加一个Fragment到FragmentManager中（注意这里没写Fragment要显示在那个id上，所以这个Fragment要显示的时候是看不到的）
	 * 同一个Fragment（或者相同的tag）不能多次添加否则会报IllegalStateException
	 */
	public abstract FragmentTransaction add(Fragment fragment, String tag);

	/**
	 * 添加一个Fragment到FragmentManager中（tag = null）
	 */
	public abstract FragmentTransaction add(@IdRes int containerViewId, Fragment fragment);

	/**
	 * 添加一个Fragment到FragmentManager中(containerViewId 表示Fragment要放置在在哪个位置)
	 */
	public abstract FragmentTransaction add(@IdRes int containerViewId, Fragment fragment, @Nullable String tag);

	/**
	 * 替换掉指定位置(containerViewId)上所有的Fragment(记住是containerViewId上所有的)
	 * containerViewId 上加入了两个Fragment A、B。如果用C来replace掉containerViewId上的Fragment。
	 * 那么A,B都会被相当于调用了FragmentTransaction里面的remove()函数。
	 */
	public abstract FragmentTransaction replace(@IdRes int containerViewId, Fragment fragment);

	/**
	 * 替换掉指定位置(containerViewId)上所有的Fragment
	 */
	public abstract FragmentTransaction replace(@IdRes int containerViewId, Fragment fragment, @Nullable String tag);

	/**
	 * 移除掉指定的Fragment
	 */
	public abstract FragmentTransaction remove(Fragment fragment);

	/**
	 * 隐藏指定的Fragment
	 */
	public abstract FragmentTransaction hide(Fragment fragment);

	/**
	 * 显示指定的Fragment（配合hide使用）
	 */
	public abstract FragmentTransaction show(Fragment fragment);

	/**
	 * 会将view与fragment分离，将此将view从view tree中删除（onPause、onStop、onDestroyView）！而且将fragment
	 * 从Activity的ADD队列中移除！所以在使用detach()后，使用fragment::isAdded()
	 * 返回的值是false；但此fragment实例并不会删除，此fragment的状态依然保持着使用，
	 * 所以在fragmentManager中仍然可以找到，即通过FragmentManager::findViewByTag()仍然是会有值的
	 */
	public abstract FragmentTransaction detach(Fragment fragment);

	/**
	 * 显然这个方法与detach()所做的工作相反，它一方面利用fragment的onCreateView()
	 * 来重建视图（onCreateView、onActivityCreate、onStart、onResume），一方面将此fragment添加到ADD队列中;这里最值得注意的地方在这里：
	 * 由于是将fragment添加到ADD队列，所以只能添加到列队头部，所以attach()操作的结果是，
	 * 最新操作的页面始终显示在最前面！
	 */
	public abstract FragmentTransaction attach(Fragment fragment);

	/**
	 * 设置一个当前活动的主导航Fragment。（还没有搞清楚这个东西的作用）
	 */
	public abstract FragmentTransaction setPrimaryNavigationFragment(Fragment fragment);

	/**
	 * 当前事务是否有操作
	 */
	public abstract boolean isEmpty();

	/**
	 * 设置进入/退出的动画效果（资源文件）。这个必须位于replace、add、remove之前，否则效果不起作用。
	 */
	public abstract FragmentTransaction setCustomAnimations(@AnimatorRes @AnimRes int enter, @AnimatorRes @AnimRes int exit);

	/**
	 * 设置进入/退出的动画效果（资源文件）。这个必须位于replace、add、remove之前，否则效果不起作用。
	 * 四个参数分别表示：添加、移除、从BackStack中pop出来、进入的动画效果。
	 */
	public abstract FragmentTransaction setCustomAnimations(@AnimatorRes @AnimRes int enter,
															@AnimatorRes @AnimRes int exit,
															@AnimatorRes @AnimRes int popEnter,
															@AnimatorRes @AnimRes int popExit);

	/**
	 * Fragment切换时, 有一些元素(element)会保持不变, 使用该函数使这些元素切换时, 赋予动画效果。
	 * 关于这部分的内容可以去搜素下Android的共享元素动画，很有意思的一个东西。
	 */
	public abstract FragmentTransaction addSharedElement(View sharedElement, String name);

	/**
	 * 设置切换效果。目前API提供：TRANSIT_NONE、 TRANSIT_FRAGMENT_OPEN、TRANSIT_FRAGMENT_CLOSE三种。
	 */
	public abstract FragmentTransaction setTransition(/*@FragmentTransaction.Transit*/ int transit);

	/**
	 * 设置切换的风格
	 */
	public abstract FragmentTransaction setTransitionStyle(@StyleRes int styleRes);

	/**
	 * 添加commit执行之前的操作到后台堆栈中(对应会生成一个FragmentManager.BackStackEntry对象)
	 */
	public abstract FragmentTransaction addToBackStack(@Nullable String name);

	/**
	 * 是否允许添加到后台堆栈，如果是不允许的状态addToBackStack()会抛异IllegalStateException常
	 */
	public abstract boolean isAddToBackStackAllowed();

	/**
	 * 设置不允许添加后台堆栈
	 */
	public abstract FragmentTransaction disallowAddToBackStack();

	/**
	 * 设置面包屑导航栏的长标题
	 * （你可以认为就是保存了一个标题，然后可以通过FragmentManager.BackStackEntry 的getBreadCrumbTitle()
	 * 获取到该设置的标题）
	 */
	public abstract FragmentTransaction setBreadCrumbTitle(@StringRes int res);

	/**
	 * 设置面包屑导航栏的长标题（可以看FragmentManager.BackStackEntry里面有对应的获取函数）
	 */
	public abstract FragmentTransaction setBreadCrumbTitle(CharSequence text);

	/**
	 * 设置面包屑导航栏的短标题（可以看FragmentManager.BackStackEntry里面有对应的获取函数）
	 */
	public abstract FragmentTransaction setBreadCrumbShortTitle(@StringRes int res);

	/**
	 * 设置面包屑导航栏的短标题（可以看FragmentManager.BackStackEntry里面有对应的获取函数）
	 */
	public abstract FragmentTransaction setBreadCrumbShortTitle(CharSequence text);

	/**
	 * 设置是否优化事务操作的执行，去掉一些冗余的操作，比如这么个情况，两个事务同时执行，一个事务是添加A Fragment，
	 * 另一个事务是用B 去 替换掉A。如果做优化，会跳过A直接添加B.
	 */
	public abstract FragmentTransaction setReorderingAllowed(boolean reorderingAllowed);

	/**
	 * 当事务commit的时候，执行指定的Runnable
	 */
	public abstract FragmentTransaction runOnCommit(Runnable runnable);

	/**
	 * 提交事务(commit并不会立即执行的，系统会在适当的时候执行)
	 */
	public abstract int commit();

	/**
	 * 允许保存在存储状态之后 提交事务（Activity 的onSaveInstanceState()只会还可以提交事务）
	 */
	public abstract int commitAllowingStateLoss();

	/**
	 * 立即提交事务(用这个函数提交事务，不能添加到回退栈)
	 */
	public abstract void commitNow();

	/**
	 * 允许保存在存储状态之后 ，立即提交事务(用这个函数提交事务，不能添加到回退栈)
	 */
	public abstract void commitNowAllowingStateLoss();

}
