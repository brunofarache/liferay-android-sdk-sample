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

package com.liferay.mobile.sample.task;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.liferay.client.service.ServiceContext;
import com.liferay.client.v62.group.GroupService;
import com.liferay.mobile.sample.activity.MainActivity;
import com.liferay.mobile.sample.model.UserSite;

import android.os.AsyncTask;
import android.util.Log;

/**
 * @author Bruno Farache
 */
public class UserSitesAsyncTask extends
		AsyncTask<Void, Void, ArrayList<UserSite>> {

	public UserSitesAsyncTask(MainActivity activity) {
		_activity = activity;
	}

	public ArrayList<UserSite> doInBackground(Void... params) {
		ArrayList<UserSite> userSites = new ArrayList<UserSite>();

		ServiceContext context = new ServiceContext(
			"http://10.0.2.2:8080", "test@liferay.com", "test");
		
		GroupService service = new GroupService(context);
		
		try {
			JSONArray jsonArray = service.getUserSites();
			
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				
				userSites.add(new UserSite(jsonObj));
			}
		}
		catch (Exception e) {
			Log.e(_TAG, "Couldn't get user sites", e);
		}
		
		return userSites;
	}
	
	public void onPostExecute(ArrayList<UserSite> sites) {
		_activity.updateUserSites(sites);
	}

	private static String _TAG = UserSitesAsyncTask.class.getName();

	private MainActivity _activity;

}