/**
 * Copyright (c) 2000-2014 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mobile.sample.util;

import android.content.Context;
import android.content.SharedPreferences;

import android.preference.PreferenceManager;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;

/**
 * @author Bruno Farache
 */
public class SettingsUtil {

	public static final String LOGIN = "login";

	public static final String PASSWORD = "password";

	public static final String SERVER = "server";

	public static String getLogin() {
		return _preferences.getString(LOGIN, "test@liferay.com");
	}

	public static String getPassword() {
		return _preferences.getString(PASSWORD, "test");
	}

	public static String getServer() {
		return _preferences.getString(SERVER, "http://10.0.2.2:8080");
	}

	public static Session getSession() {
		return new SessionImpl(getServer(), getLogin(), getPassword());
	}

	public static void init(Context context) {
		_preferences = PreferenceManager.getDefaultSharedPreferences(context);
	}

	private static SharedPreferences _preferences;

}