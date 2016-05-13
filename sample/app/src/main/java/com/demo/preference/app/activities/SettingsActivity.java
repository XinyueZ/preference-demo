package com.demo.preference.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.demo.preference.R;
import com.demo.preference.app.fragments.SettingContentFragment;
import com.demo.preference.app.fragments.SettingHeadersFragment;


public final class SettingsActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
	private static final int LAYOUT = R.layout.activity_settings;
	private static final int LAYOUT_SETTINGS_CONTAINER = R.id.settings_fragment_container;
	private static final int LAYOUT_HEADERS = R.id.setting_headers_fragment;
	private boolean mTabletLayout;

	public static void showInstance(Activity cxt) {
		Intent intent = new Intent(cxt, SettingsActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		ActivityCompat.startActivity(cxt, intent, null);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(LAYOUT);
		mTabletLayout = findViewById(R.id.div) != null;
		setUpTitle();
		setUpBar();
		setUpHeaders();
	}

	@Override
	public boolean onPreferenceStartFragment(PreferenceFragmentCompat preferenceFragmentCompat, Preference preference) {
		FragmentManager manager = getSupportFragmentManager();
		Fragment contentFrag = SettingContentFragment.newInstance(preference.getExtras());
		Fragment headersFrag = manager.findFragmentById(LAYOUT_HEADERS);
		contentFrag.setTargetFragment(headersFrag, 0);
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(LAYOUT_SETTINGS_CONTAINER, contentFrag);
		if (!mTabletLayout) {
			//Phone needs back-stack, otherwise it'd be strange to exit "setting" after clicking "up" or "back" button.
			transaction.addToBackStack(null);
		}
		transaction.commit();
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				if (!mTabletLayout) {
					//For phone it is easy return to outline page.
					onBackPressed();
				} else {
					//Because tablet version stacks fragments by selecting different outline items,
					//in order to ease users we need quick leaving from setting.
					ActivityCompat.finishAfterTransition(this);
				}
				break;
		}
		return super.onOptionsItemSelected(item);
	}


	private void setUpTitle() {
		setTitle(R.string.preference_title);

	}


	private void setUpBar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if(actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}

	private void setUpHeaders() {
		final FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction()
		       .replace(LAYOUT_HEADERS, SettingHeadersFragment.newInstance(mTabletLayout))
		       .commit();
		manager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
			@Override
			public void onBackStackChanged() {
				if (getSupportFragmentManager().getBackStackEntryCount() < 1) {
					setTitle(R.string.preference_title);
					//When there's empty on stack we just leave the settings, it effects only on tablet version.
					if (mTabletLayout) {
						ActivityCompat.finishAfterTransition(SettingsActivity.this);
					}
				}
			}
		});
	}

}