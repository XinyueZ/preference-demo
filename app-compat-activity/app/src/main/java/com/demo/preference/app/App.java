package com.demo.preference.app;


import android.support.multidex.MultiDexApplication;

public final class App extends MultiDexApplication {
	/**
	 * Application's instance.
	 */
	@SuppressWarnings("CanBeFinal") public static App Instance;


	{
		Instance = this;
	}
}
