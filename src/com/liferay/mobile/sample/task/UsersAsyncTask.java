/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import android.os.AsyncTask;

import android.util.Log;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v62.group.GroupService;
import com.liferay.mobile.android.v62.user.UserService;
import com.liferay.mobile.sample.activity.MainActivity;
import com.liferay.mobile.sample.model.User;
import com.liferay.mobile.sample.util.SettingsUtil;
import com.liferay.mobile.sample.util.ToastUtil;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Bruno Farache
 */
public class UsersAsyncTask extends AsyncTask<Void, Void, ArrayList<User>> {

	public UsersAsyncTask(MainActivity activity) {
		_activity = activity;
	}

	public ArrayList<User> doInBackground(Void... params) {
		ArrayList<User> users = new ArrayList<User>();

		Session session = SettingsUtil.getSession();
		UserService userService = new UserService(session);

		try {
			long groupId = getGuestGroupId(session);

			JSONArray jsonArray = userService.getGroupUsers(groupId);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);

				users.add(new User(jsonObj));
			}
		}
		catch (Exception e) {
			Log.e(_CLASS_NAME, "Couldn't get users", e);

			_exception = e;

			cancel(true);
		}

		return users;
	}

	public void onCancelled(ArrayList<User> result) {
		String message = "Couldn't get users";

		Log.e(_CLASS_NAME, message, _exception);

		ToastUtil.show(
			_activity, message + ": " + _exception.getMessage(), true);
	}

	public void onPostExecute(ArrayList<User> sites) {
		_activity.updateUsers(sites);
	}

	protected long getGuestGroupId(Session session) throws Exception {
		long groupId = -1;

		GroupService groupService = new GroupService(session);

		JSONArray groups = groupService.getUserSites();

		for (int i = 0; i < groups.length(); i++) {
			JSONObject group = groups.getJSONObject(i);

			String name = group.getString("name");

			if (!name.equals("Guest")) {
				continue;
			}

			groupId = group.getLong("groupId");
		}

		if (groupId == -1) {
			throw new Exception("Couldn't find Guest group.");
		}

		return groupId;
	}

	private static String _CLASS_NAME = UsersAsyncTask.class.getName();

	private MainActivity _activity;
	private Exception _exception;

}