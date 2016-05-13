package com.demo.preference.app.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.demo.preference.R;
import com.demo.preference.app.App;


public final class SettingHeadersFragment extends AbstractSettingFragment implements SettingContentFragment.SettingContentCallback {
	private static final String EXTRAS_TABLET_LAYOUT = SettingHeadersFragment.class.getName() + ".EXTRAS.tablet_layout";
	private int mSelectedItemColor;
	private int mUnselectedItemColor;
	private View mSelectedItem;

	public static Fragment newInstance(boolean isTabletLayout) {
		Bundle args = new Bundle(1);
		args.putBoolean(EXTRAS_TABLET_LAYOUT, isTabletLayout);
		return Fragment.instantiate(App.Instance, SettingHeadersFragment.class.getName(), args);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSelectedItemColor = ResourcesCompat.getColor(getResources(), R.color.colorGrey, null);
		mUnselectedItemColor = ResourcesCompat.getColor(getResources(), R.color.colorTransparent, null);
	}

	@Override
	public void onCreatePreferences(Bundle bundle, String s) {
		addPreferencesFromResource(R.xml.preferences);
		addPreferencesFromResource(R.xml.preferences_debug);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		boolean isTabletLayout = getArguments().getBoolean(EXTRAS_TABLET_LAYOUT);
		if (isTabletLayout) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					clickFirst();
				}
			}, 500);
		}
	}


	@Override
	public void onShowSettingContent(int index, String title) {
		selectHeader(index);
		Activity activity = getActivity();
		if (activity != null) {
			activity.setTitle(title);
		}
	}

	private void selectHeader(int index) {
		boolean isTabletLayout = getArguments().getBoolean(EXTRAS_TABLET_LAYOUT);
		if (isTabletLayout) {
			RecyclerView listView = getListView();
			if (listView != null) {
				if (mSelectedItem != null) {
					mSelectedItem.setBackgroundColor(mUnselectedItemColor);
				}
				mSelectedItem = listView.findViewHolderForAdapterPosition(index).itemView;
				mSelectedItem.setBackgroundColor(mSelectedItemColor);
			}
		}
	}

	private void clickFirst() {
		selectHeader(0);
		mSelectedItem.performClick();
	}


}
