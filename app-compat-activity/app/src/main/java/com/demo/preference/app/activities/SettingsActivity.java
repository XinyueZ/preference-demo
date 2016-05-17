package com.demo.preference.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.view.MenuItem;

import com.demo.preference.R;

import java.util.List;


public final class SettingsActivity extends AppCompatPreferenceActivity {

	/**
	 * Show single instance of {@link SettingsActivity}
	 *
	 * @param cxt {@link Activity}.
	 */
	public static void showInstance(Activity cxt) {
		Intent intent = new Intent(cxt, SettingsActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		ActivityCompat.startActivity(cxt, intent, null);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.preference_title);
		setUpToolbar(this);
		getListView().setDivider(ResourcesCompat.getDrawable(getResources(), android.R.drawable.divider_horizontal_textfield   , null));
	}


	@Override
	public void onBuildHeaders(List<PreferenceActivity.Header> target) {
		loadHeadersFromResource(R.xml.preferences, target);
		loadHeadersFromResource(R.xml.preferences_debug, target);

	}

	@Override
	protected boolean isValidFragment(String fragmentName) {
		return TextUtils.equals("com.demo.preference.app.fragments.SettingsFragment", fragmentName);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				break;
		}
		return super.onOptionsItemSelected(item);
	}


	private static void setUpToolbar(AppCompatPreferenceActivity activity) {
		android.support.v7.app.ActionBar supportActionBar = activity.getSupportActionBar();
		if (supportActionBar != null) {
			supportActionBar.setDisplayHomeAsUpEnabled(true);
		}
	}
}