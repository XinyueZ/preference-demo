package com.demo.preference.app.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.demo.preference.R;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void openSettings(@SuppressWarnings("UnusedParameters") View v) {
		SettingsActivity.showInstance(this);
	}
}
