package com.tuacy.sharedelement;

import android.app.ActivityOptions;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.List;
import java.util.Map;

import static com.tuacy.sharedelement.Constants.ALBUM_NAMES;

public class MainActivity extends AppCompatActivity {

	static final String EXTRA_STARTING_ALBUM_POSITION = "extra_starting_item_position";
	static final String EXTRA_CURRENT_ALBUM_POSITION  = "extra_current_item_position";


	private RecyclerView mRecyclerView;
	private Bundle       mReenterState;

	private final SharedElementCallback mCallback = new SharedElementCallback() {
		@Override
		public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
			if (mReenterState != null) {
				//从别的界面返回当前界面
				int startingPosition = mReenterState.getInt(EXTRA_STARTING_ALBUM_POSITION);
				int currentPosition = mReenterState.getInt(EXTRA_CURRENT_ALBUM_POSITION);
				if (startingPosition != currentPosition) {
					String newTransitionName = ALBUM_NAMES[currentPosition];
					View newSharedElement = mRecyclerView.findViewWithTag(newTransitionName);
					if (newSharedElement != null) {
						names.clear();
						names.add(newTransitionName);
						sharedElements.clear();
						sharedElements.put(newTransitionName, newSharedElement);
					}
				}

				mReenterState = null;
			} else {
				//从当前界面进入到别的界面
				View navigationBar = findViewById(android.R.id.navigationBarBackground);
				View statusBar = findViewById(android.R.id.statusBarBackground);
				if (navigationBar != null) {
					names.add(navigationBar.getTransitionName());
					sharedElements.put(navigationBar.getTransitionName(), navigationBar);
				}
				if (statusBar != null) {
					names.add(statusBar.getTransitionName());
					sharedElements.put(statusBar.getTransitionName(), statusBar);
				}
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setExitSharedElementCallback(mCallback);

		mRecyclerView = findViewById(R.id.recycler_view);
		mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
		mRecyclerView.setAdapter(new RecyclerCardAdapter(this, new RecyclerCardAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
				intent.putExtra(EXTRA_STARTING_ALBUM_POSITION, position);
				startActivity(intent,
							  ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, view, view.getTransitionName())
											 .toBundle());
			}
		}));
	}

	@Override
	public void onActivityReenter(int requestCode, Intent data) {
		super.onActivityReenter(requestCode, data);
		mReenterState = new Bundle(data.getExtras());
		int startingPosition = mReenterState.getInt(EXTRA_STARTING_ALBUM_POSITION);
		int currentPosition = mReenterState.getInt(EXTRA_CURRENT_ALBUM_POSITION);
		if (startingPosition != currentPosition) {
			mRecyclerView.scrollToPosition(currentPosition);
		}
		postponeEnterTransition();
		mRecyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			@Override
			public boolean onPreDraw() {
				mRecyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
				mRecyclerView.requestLayout();
				startPostponedEnterTransition();
				return true;
			}
		});
	}
}
