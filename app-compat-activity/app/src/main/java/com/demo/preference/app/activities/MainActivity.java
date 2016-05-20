package com.demo.preference.app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.demo.preference.R;
import com.demo.preference.app.activities.SettingsActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void openSettings(View view){
		SettingsActivity.showInstance(this);
	}
}
