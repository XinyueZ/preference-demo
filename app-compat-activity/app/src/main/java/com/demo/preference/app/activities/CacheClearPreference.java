package com.demo.preference.app.activities;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.widget.Toast;

import com.demo.preference.app.App;

public class CacheClearPreference extends DialogPreference {

	public CacheClearPreference(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CacheClearPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDialogClosed(boolean positiveResult) {
		super.onDialogClosed(positiveResult);
		if (positiveResult) {
			Toast.makeText(App.Instance, "Cache has been cleared.", Toast.LENGTH_LONG)
			     .show();
		}
	}

}
