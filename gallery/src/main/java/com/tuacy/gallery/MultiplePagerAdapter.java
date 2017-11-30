package com.tuacy.gallery;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MultiplePagerAdapter extends PagerAdapter {

	private SparseArray<View> mViews;
	private Context           mContext;
	private LayoutInflater    layoutInflater;

	public MultiplePagerAdapter(Context context) {
		super();
		mViews = new SparseArray<>();
		mContext = context;
		layoutInflater = LayoutInflater.from(context);
	}

	public View getMapView(int position) {
		return mViews.get(position);
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	/**
	 * 页面宽度所占ViewPager测量宽度的权重比例，默认为1
	 */
//	@Override
//	public float getPageWidth(int position) {
//		return (float) 0.2;
//	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		View view = (View) object;
		container.removeView(view);
		mViews.remove(position);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = layoutInflater.inflate(R.layout.item_gallery, container, false);
		view.setTag(position);
		((TextView) view.findViewById(R.id.title)).setText("位置 = " + (position % 5));
		container.addView(view);
		mViews.put(position, view);
		return view;
	}


	/**
	 * dp 转 px
	 */
	public int dp2px(int dp) {
		int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
		return px;
	}
}
