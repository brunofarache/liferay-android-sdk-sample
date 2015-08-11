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

package com.liferay.mobile.sample.activity;

import android.annotation.SuppressLint;

import android.app.ListActivity;

import android.content.Intent;

import android.os.Build;
import android.os.Bundle;

import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liferay.mobile.android.auth.SignIn;
import com.liferay.mobile.android.callback.Callback;
import com.liferay.mobile.android.callback.typed.JSONObjectCallback;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v62.contact.ContactService;
import com.liferay.mobile.sample.R;
import com.liferay.mobile.sample.model.User;
import com.liferay.mobile.sample.task.UsersAsyncTask;
import com.liferay.mobile.sample.task.callback.ContactCallback;
import com.liferay.mobile.sample.util.SettingsUtil;
import com.liferay.mobile.sample.util.ToastUtil;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Bruno Farache
 */
public class MainActivity extends ListActivity {

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		SettingsUtil.init(this);

		ArrayAdapter<User> adapter = new ArrayAdapter<User>(
			this, android.R.layout.simple_list_item_1);

		setListAdapter(adapter);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	public void onListItemClick(
		ListView listView, View view, int position, long id) {

		User user = (User)getListAdapter().getItem(position);

		Session session = SettingsUtil.getSession();
		ContactService contactService = new ContactService(session);

		Callback callback = new ContactCallback(this, user);

		session.setCallback(callback);

		try {
			contactService.getContact(user.getContactId());
		}
		catch (Exception e) {
			Log.e(_CLASS_NAME, "Couldn't get Contact", e);
		}
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.settings:
				Intent intent = new Intent(this, SettingsActivity.class);

				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

				startActivity(intent);

				return true;

			default:
				return false;
		}
	}

	public void onResume() {
		super.onResume();

		UsersAsyncTask task = new UsersAsyncTask(this);
		task.execute();

		signIn();
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("unchecked")
	public void updateUsers(ArrayList<User> users) {
		ArrayAdapter<User> adapter = (ArrayAdapter<User>)getListAdapter();

		adapter.clear();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			adapter.addAll(users);
		}
		else {
			for (User user : users) {
				adapter.add(user);
			}
		}
	}

	protected void signIn() {
		Session session = SettingsUtil.getSession();

		SignIn.signIn(session, new JSONObjectCallback() {

			@Override
			public void onSuccess(JSONObject userJSONObject) {
				try {
					User user = new User(userJSONObject);

					ToastUtil.show(
						MainActivity.this,
						"Authentication worked! User name: " + user.getName());
				}
				catch (JSONException e) {
					onFailure(e);
				}
			}

			@Override
			public void onFailure(Exception e) {
				ToastUtil.show(
					MainActivity.this, "Authentication failed! " +
						e.getMessage());
			}

		});
	}

	private static String _CLASS_NAME = MainActivity.class.getName();

}