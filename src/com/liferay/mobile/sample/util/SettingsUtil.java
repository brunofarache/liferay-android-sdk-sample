package com.liferay.mobile.sample.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;

public class SettingsUtil {

	public static final String LOGIN = "login";

	public static final String PASSWORD = "password";

	public static final String SERVER = "server";

	public static Session getSession() {
		return new SessionImpl(getServer(), getLogin(), getPassword());
	}
	
	public static String getLogin() {
		return _preferences.getString(LOGIN, "test@liferay.com");
	}
	
	public static String getPassword() {
		return _preferences.getString(PASSWORD, "test");
	}
	
	public static String getServer() {
		return _preferences.getString(SERVER, "http://10.0.2.2:8080");
	}
	
	public static void init(Context context) {
		_preferences = PreferenceManager.getDefaultSharedPreferences(context);
	}

	private static SharedPreferences _preferences;

}