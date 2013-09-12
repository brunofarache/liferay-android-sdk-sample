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

import java.util.ArrayList;

import com.liferay.mobile.sample.R;
import com.liferay.mobile.sample.model.UserSite;
import com.liferay.mobile.sample.task.UserSitesAsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;

/**
 * @author Bruno Farache
 */
@SuppressLint("NewApi")
@SuppressWarnings("unchecked")
public class MainActivity extends ListActivity {

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		ArrayAdapter<UserSite> adapter = new ArrayAdapter<UserSite>(
			this, android.R.layout.simple_list_item_1);

		setListAdapter(adapter);

		UserSitesAsyncTask task = new UserSitesAsyncTask(this);

		task.execute();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	public void updateUserSites(ArrayList<UserSite> sites) {
		ArrayAdapter<UserSite> adapter = 
			(ArrayAdapter<UserSite>)getListAdapter();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			adapter.addAll(sites);
		}
		else {
			for (UserSite site : sites) {
				adapter.add(site);
			}
		} 
	}

}