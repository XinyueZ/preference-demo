package com.demo.preference.app.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.demo.preference.R;


public final class SettingsFragment extends PreferenceFragment {


	private static final String INTENT_EXTRA_HEADER = "header";
	private static final String HEADER_DATASTORE = "preferences_datastore";
	private static final String HEADER_TRACKING = "preferences_tracking";
	private static final String HEADER_USERDATA = "preferences_userdata";
	private static final String HEADER_DEBUG = "preferences_debug";
	private static final String HEADER_NOTIFICATIONS = "preferences_notifications";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String settings = getArguments().getString( INTENT_EXTRA_HEADER);
		Activity activity = getActivity();
		if (activity != null) {
			if ( HEADER_DATASTORE.equals(settings)) {
				addPreferencesFromResource(R.xml.preferences_datastore);
				activity.setTitle(R.string.preference_title_datastore);
			} else if ( HEADER_TRACKING.equals(settings)) {
				addPreferencesFromResource(R.xml.preferences_tracking);
				activity.setTitle(R.string.preference_title_tracking);
			} else if (HEADER_USERDATA.equals(settings)) {
				addPreferencesFromResource(R.xml.preferences_userdata);
				activity.setTitle(R.string.preference_title_userdata);
			} else if ( HEADER_DEBUG.equals(settings)) {
				addPreferencesFromResource(R.xml.preferences_debug_detail);
				activity.setTitle(R.string.preference_title_debug);
			} else if ( HEADER_NOTIFICATIONS.equals(settings)) {
				addPreferencesFromResource(R.xml.preferences_notifications);
				activity.setTitle(R.string.preference_title_notifications);
			}
		}
	}

}
