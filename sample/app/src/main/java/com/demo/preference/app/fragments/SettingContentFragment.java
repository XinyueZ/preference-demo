package com.demo.preference.app.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.text.TextUtils;

import com.demo.preference.R;
import com.demo.preference.app.App;


public final class SettingContentFragment extends AbstractSettingFragment {


	public  static Fragment newInstance(Bundle args) {
		return Fragment.instantiate(App.Instance, SettingContentFragment.class.getName(), args);
	}

	@Override
	public void onCreatePreferences(Bundle bundle, String s) {
		addPreferencesFromResource();
	}


	@Override
	public void onDisplayPreferenceDialog(Preference preference) {
		if (preference instanceof CacheClearPreference) {
			Bundle extras = preference.getExtras();
			if (extras != null) {
				String value = extras.getString(HEADER);
				if (TextUtils.equals(HEADER_PREFERENCE_TITLE_DATASTORE_CLEAR_CACHE, value)) {
					CacheClearPreferenceDialog cacheClearPreferenceDialog = CacheClearPreferenceDialog.newInstance();
					cacheClearPreferenceDialog.setTargetFragment(this, 0);
					cacheClearPreferenceDialog.show(getChildFragmentManager(), null);
					return;
				}
			}
		}
		super.onDisplayPreferenceDialog(preference);
	}


	@Override
	public void onResume() {
		super.onResume();
		notifyShown();
	}


	private void addPreferencesFromResource() {
		Bundle args = getArguments();
		if (args != null && !TextUtils.isEmpty(args.getString(HEADER))) {
			String value = args.getString(HEADER);
			if (value == null) {
				return;
			}
			Activity activity = getActivity();
			if (activity != null) {
				switch (value) {
					case HEADER_DATASTORE:
						addPreferencesFromResource(R.xml.preferences_datastore);
						break;
					case HEADER_TRACKING:
						addPreferencesFromResource(R.xml.preferences_tracking);
						break;
					case HEADER_USERDATA:
						addPreferencesFromResource(R.xml.preferences_userdata);
						break;
					case HEADER_DEBUG:
						addPreferencesFromResource(R.xml.preferences_debug_detail);
						break;
					case HEADER_NOTIFICATIONS:
						addPreferencesFromResource(R.xml.preferences_notifications);
						break;
				}
			}
		}
	}


	private void notifyShown() {
		Bundle args = getArguments();
		if (args != null) {
			int index = args.getInt(INDEX);
			if (getTargetFragment() == null) {
				throw new NullPointerException("Have your lost to call SettingContentFragment$setTargetFragment?");
			}
			if (!(getTargetFragment() instanceof SettingContentCallback)) {
				throw new IllegalArgumentException("You've set target-fragment however it's not a SettingContentFragment$SettingContentCallback.");
			}
			SettingContentCallback targetFragment = (SettingContentCallback) getTargetFragment();
			targetFragment.onShowSettingContent(index, args.getString(TITLE));
		}
	}

	interface SettingContentCallback {
		void onShowSettingContent(int index, String title);
	}
}
