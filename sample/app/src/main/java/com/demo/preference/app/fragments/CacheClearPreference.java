package com.demo.preference.app.fragments;


import android.content.Context;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;

final class CacheClearPreference extends DialogPreference {

	public CacheClearPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public CacheClearPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public CacheClearPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CacheClearPreference(Context context) {
		super(context);
	}
}
