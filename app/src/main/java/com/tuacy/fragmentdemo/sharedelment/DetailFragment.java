package com.tuacy.fragmentdemo.sharedelment;


import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuacy.fragmentdemo.R;

import java.util.ArrayList;

public class DetailFragment extends Fragment {

	private static final String ARG_NUMBER = "arg_number";
	private ArrayList<DetailData> mDetailDatas;

	ImageView mImage;
	TextView  mHead;
	TextView  mBody;

	/**
	 * 根据选择的number, 选择展示的数据
	 *
	 * @param number 数字
	 * @return 详情页面
	 */
	public static DetailFragment newInstance(int number) {
		Bundle bundle = new Bundle();
		bundle.putInt(ARG_NUMBER, number);

		DetailFragment detailFragment = new DetailFragment();
		detailFragment.setArguments(bundle);

		return detailFragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_detail, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mImage = view.findViewById(R.id.detail_image);
		mHead = view.findViewById(R.id.detail_head);
		mBody = view.findViewById(R.id.detail_body);
		initData();

		int number = getArguments().getInt(ARG_NUMBER);
		mImage.setImageResource(mDetailDatas.get(number).getImage());
		mHead.setText(mDetailDatas.get(number).getHead());
		mBody.setText(mDetailDatas.get(number).getBody());

		mImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().popBackStack();
			}
		});
	}


	// 初始化数据
	private void initData() {
		mDetailDatas = new ArrayList<>();
		mDetailDatas.add(new DetailData(R.drawable.taeyeon, R.string.taeyeon, R.string.taeyeon_detail));
		mDetailDatas.add(new DetailData(R.drawable.jessica, R.string.jessica, R.string.jessica_detail));
		mDetailDatas.add(new DetailData(R.drawable.sunny, R.string.sunny, R.string.sunny_detail));
		mDetailDatas.add(new DetailData(R.drawable.tiffany, R.string.tiffany, R.string.tiffany_detail));
		mDetailDatas.add(new DetailData(R.drawable.yuri, R.string.yuri, R.string.yuri_detail));
		mDetailDatas.add(new DetailData(R.drawable.yoona, R.string.yoona, R.string.yoona_detail));
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	// 定义类
	private class DetailData {

		private int mImage;
		private int mHead;
		private int mBody;

		DetailData(int image, int head, int body) {
			mImage = image;
			mHead = head;
			mBody = body;
		}

		int getImage() {
			return mImage;
		}

		int getHead() {
			return mHead;
		}

		int getBody() {
			return mBody;
		}
	}

}
