package com.tuacy.fragmentdemo.sharedelment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuacy.fragmentdemo.R;

import java.util.ArrayList;


public class SharedElementGridFragment extends Fragment {

	private ArrayList<Pair<Integer, Integer>> mData;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_shared_element_grid, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initData();
		RecyclerView recycler = view.findViewById(R.id.recycler_grid);
		recycler.setAdapter(new SharedElementGridAdapter(mData, mListener));
		recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
	}

	/**
	 * 点击事件, 转换元素的动画,
	 * 关键addSharedElement(holder.getImageView(), getResources().getString(R.string.image_transition))
	 * 绑定ViewHolder的图片和DetailFragment的跳转.
	 */
	private SharedElementGridAdapter.OnItemClickListener mListener = new SharedElementGridAdapter.OnItemClickListener() {
		@Override
		public void setOnItemClickListener(SharedElementGridAdapter.CustomerViewHolder holder, int position) {
			DetailFragment detailFragment = DetailFragment.newInstance(position);

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				detailFragment.setSharedElementEnterTransition(new DetailTransition());
				setExitTransition(new Fade());
				detailFragment.setEnterTransition(new Fade());
				detailFragment.setSharedElementReturnTransition(new DetailTransition());
			}

			getActivity().getSupportFragmentManager()
						 .beginTransaction()
						 .addSharedElement(holder.getImageView(), getResources().getString(R.string.image_transition))
						 .replace(R.id.layout_container, detailFragment)
						 .addToBackStack(null)
						 .commit();
		}
	};

	private void initData() {
		mData = new ArrayList<>();
		mData.add(Pair.create(R.string.taeyeon, R.drawable.taeyeon));
		mData.add(Pair.create(R.string.jessica, R.drawable.jessica));
		mData.add(Pair.create(R.string.sunny, R.drawable.sunny));
		mData.add(Pair.create(R.string.tiffany, R.drawable.tiffany));
		mData.add(Pair.create(R.string.yuri, R.drawable.yuri));
		mData.add(Pair.create(R.string.yoona, R.drawable.yoona));
	}


}
