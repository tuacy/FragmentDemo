package com.tuacy.fragmentdemo.sharedelment;


import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuacy.fragmentdemo.R;

import java.util.ArrayList;

public class SharedElementGridAdapter extends RecyclerView.Adapter<SharedElementGridAdapter.CustomerViewHolder> {

	private ArrayList<Pair<Integer, Integer>> mData;
	private OnItemClickListener               mListener;

	public interface OnItemClickListener {

		void setOnItemClickListener(CustomerViewHolder holder, int position);
	}

	public SharedElementGridAdapter(ArrayList<Pair<Integer, Integer>> data, OnItemClickListener listener) {
		mData = data;
		mListener = listener;
	}

	@Override
	public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new CustomerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false));
	}

	@Override
	public void onBindViewHolder(final CustomerViewHolder holder, int position) {
		holder.getTextView().setText(mData.get(position).first);
		holder.getImageView().setImageResource(mData.get(position).second);

		// 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱
		// Fragment支持多个View进行变换, 使用适配器时, 需要加以区分
		ViewCompat.setTransitionName(holder.getImageView(), String.valueOf(position) + "_image");

		holder.getImageView().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.setOnItemClickListener(holder, holder.getAdapterPosition());
			}
		});
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}


	static class CustomerViewHolder extends RecyclerView.ViewHolder {

		private ImageView mImageView;
		private TextView  mTextView;

		CustomerViewHolder(View itemView) {
			super(itemView);
			mImageView = itemView.findViewById(R.id.grid_image);
			mTextView = itemView.findViewById(R.id.grid_text);
		}

		ImageView getImageView() {
			return mImageView;
		}

		TextView getTextView() {
			return mTextView;
		}

	}

}
