package com.liferay.mobile.sample.util;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;

public class ServiceUtil {

	public static Session getSession() {
		return new SessionImpl(
			"http://10.0.2.2:8080", "test@liferay.com", "test");
	}

}