package com.tuacy.example.backstack;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuacy.example.R;

public class CrumbAdapter extends RecyclerView.Adapter<CrumbAdapter.CrumbHolder> {

	private RecyclerView    mRecyclerView;
	private Context         mContext;
	private FragmentManager mFragmentManager;

	public CrumbAdapter(RecyclerView recyclerView, Context context, FragmentManager fragmentManager) {
		mRecyclerView = recyclerView;
		mContext = context;
		mFragmentManager = fragmentManager;
		//设置Fragment回退栈的监听
		mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
			@Override
			public void onBackStackChanged() {
				notifyDataSetChanged();
				mRecyclerView.scrollToPosition(getItemCount() - 1);
			}
		});
	}

	@Override
	public CrumbHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		CrumbHolder holder = new CrumbHolder(LayoutInflater.from(mContext).inflate(R.layout.item_crumb_bar, parent, false));
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager.BackStackEntry backStackEntry = (FragmentManager.BackStackEntry) v.getTag();
				//弹出backStackEntry之上的堆栈
				mFragmentManager.popBackStack(backStackEntry.getId(), 0);
			}
		});
		return holder;
	}

	@Override
	public void onBindViewHolder(CrumbHolder holder, int position) {
		FragmentManager.BackStackEntry backStackEntry = mFragmentManager.getBackStackEntryAt(position);
		holder.itemView.setTag(backStackEntry);
		holder.mCrumbItem.setText(backStackEntry.getBreadCrumbTitle());
		if (position == getItemCount() - 1) {
			holder.mCrumbItem.setTextColor(mContext.getResources().getColor(R.color.colorCrumbItemSelect));
		} else {
			holder.mCrumbItem.setTextColor(mContext.getResources().getColor(R.color.colorCrumbItemNormal));
		}
	}

	@Override
	public int getItemCount() {
		return mFragmentManager.getBackStackEntryCount();
	}

	static class CrumbHolder extends RecyclerView.ViewHolder {

		TextView mCrumbItem;

		CrumbHolder(View itemView) {
			super(itemView);
			mCrumbItem = itemView.findViewById(R.id.text_crumb_name);
		}
	}

}
