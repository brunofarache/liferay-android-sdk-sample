/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.mobile.sample.activity;

import com.liferay.mobile.sample.R;
import com.liferay.mobile.sample.task.UserSitesAsyncTask;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * @author Bruno Farache
 */
public class MainActivity extends Activity {

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		setContentView(R.layout.main);

		UserSitesAsyncTask task = new UserSitesAsyncTask();
		
		task.execute();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

}