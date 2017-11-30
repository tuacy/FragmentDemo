package com.tuacy.sharedelement;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
import java.util.Map;

import static com.tuacy.sharedelement.Constants.ALBUM_IMAGE_URLS;
import static com.tuacy.sharedelement.MainActivity.EXTRA_CURRENT_ALBUM_POSITION;
import static com.tuacy.sharedelement.MainActivity.EXTRA_STARTING_ALBUM_POSITION;


public class DetailsActivity extends AppCompatActivity {

	private static final String STATE_CURRENT_PAGE_POSITION = "state_current_page_position";

	private final SharedElementCallback mCallback = new SharedElementCallback() {
		@Override
		public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
			if (mIsReturning) {
				ImageView sharedElement = mCurrentDetailsFragment.getAlbumImage();
				if (sharedElement == null) {
					names.clear();
					sharedElements.clear();
				} else if (mStartingPosition != mCurrentPosition) {
					names.clear();
					names.add(sharedElement.getTransitionName());
					sharedElements.clear();
					sharedElements.put(sharedElement.getTransitionName(), sharedElement);
				}
			}
		}
	};

	private DetailsFragment mCurrentDetailsFragment;
	private int             mCurrentPosition;
	private int             mStartingPosition;
	private boolean         mIsReturning;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		postponeEnterTransition();
		setEnterSharedElementCallback(mCallback);

		mStartingPosition = getIntent().getIntExtra(EXTRA_STARTING_ALBUM_POSITION, 0);
		if (savedInstanceState == null) {
			mCurrentPosition = mStartingPosition;
		} else {
			mCurrentPosition = savedInstanceState.getInt(STATE_CURRENT_PAGE_POSITION);
		}

		ViewPager pager = findViewById(R.id.pager);
		pager.setAdapter(new DetailsFragmentPagerAdapter(getFragmentManager()));
		pager.setCurrentItem(mCurrentPosition);
		pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				mCurrentPosition = position;
			}
		});
	}

	@Override
	protected void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_CURRENT_PAGE_POSITION, mCurrentPosition);
	}

	@Override
	public void finishAfterTransition() {
		mIsReturning = true;
		Intent data = new Intent();
		data.putExtra(EXTRA_STARTING_ALBUM_POSITION, mStartingPosition);
		data.putExtra(EXTRA_CURRENT_ALBUM_POSITION, mCurrentPosition);
		setResult(RESULT_OK, data);
		super.finishAfterTransition();
	}

	private class DetailsFragmentPagerAdapter extends FragmentStatePagerAdapter {

		DetailsFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return DetailsFragment.newInstance(position, mStartingPosition);
		}

		@Override
		public void setPrimaryItem(ViewGroup container, int position, Object object) {
			super.setPrimaryItem(container, position, object);
			mCurrentDetailsFragment = (DetailsFragment) object;
		}

		@Override
		public int getCount() {
			return ALBUM_IMAGE_URLS.length;
		}
	}
}
