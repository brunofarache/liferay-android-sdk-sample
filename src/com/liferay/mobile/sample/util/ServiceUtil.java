package com.liferay.mobile.sample.util;

import com.liferay.mobile.android.service.ServiceContext;

public class ServiceUtil {

	public static ServiceContext getServiceContext() {
		return new ServiceContext(
			"http://10.0.2.2:8080", "test@liferay.com", "test");
	}

}