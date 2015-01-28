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

package com.liferay.mobile.sample.task.callback;

import android.content.Context;
import android.content.Intent;

import android.util.Log;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.task.callback.typed.GenericAsyncTaskCallback;
import com.liferay.mobile.android.v62.phone.PhoneService;
import com.liferay.mobile.sample.activity.DetailsActivity;
import com.liferay.mobile.sample.model.Contact;
import com.liferay.mobile.sample.model.User;
import com.liferay.mobile.sample.util.SettingsUtil;
import com.liferay.mobile.sample.util.ToastUtil;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Bruno Farache
 */
public class ContactCallback extends GenericAsyncTaskCallback<Contact> {

	public ContactCallback(Context context, User user) {
		_context = context;
		_user = user;
	}

	public JSONArray inBackground(JSONArray array) throws Exception {
		Session session = SettingsUtil.getSession();
		PhoneService phoneService = new PhoneService(session);

		JSONArray jsonArray = phoneService.getPhones(
			"com.liferay.portal.model.Contact", _user.getContactId());

		_phones = new ArrayList<String>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);

			_phones.add(jsonObj.getString(_NUMBER));
		}

		return array;
	}

	public void onFailure(Exception exception) {
		String message = "Couldn't get user details";

		Log.e(_CLASS_NAME, message, exception);

		ToastUtil.show(_context, message + ": " + exception.getMessage(), true);
	}

	public void onSuccess(Contact contact) {
		_user.setContact(contact);

		Intent intent = new Intent(_context, DetailsActivity.class);

		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		intent.putExtra(DetailsActivity.EXTRA_USER, _user);

		_context.startActivity(intent);
	}

	public Contact transform(Object obj) throws Exception {
		Contact contact = new Contact((JSONObject)obj, _phones);

		return contact;
	}

	private static String _CLASS_NAME = ContactCallback.class.getName();

	private static String _NUMBER = "number";

	private Context _context;
	ArrayList<String> _phones;
	private User _user;

}