package com.demo.preference.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.widget.Toast;

import com.demo.preference.R;
import com.demo.preference.app.App;


public class CacheClearPreferenceDialog extends PreferenceDialogFragmentCompat {

	private static final String KEY = "key";

	public static CacheClearPreferenceDialog newInstance() {
		Bundle args = new Bundle(1);
		args.putString(KEY, App.Instance.getString(R.string.preference_key_datastore_clear_cache));
		return (CacheClearPreferenceDialog) Fragment.instantiate(App.Instance, CacheClearPreferenceDialog.class.getName(), args);
	}

	@Override
	public void onDialogClosed(boolean b) {
		if (b) {
			Toast.makeText(App.Instance, "Cache has been cleared.", Toast.LENGTH_LONG)
			     .show();
		}
	}
}
