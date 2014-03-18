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

package com.liferay.mobile.sample.activity;

import android.app.Activity;

import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.liferay.mobile.sample.R;
import com.liferay.mobile.sample.model.Contact;
import com.liferay.mobile.sample.model.User;

import java.util.ArrayList;

/**
 * @author Bruno Farache
 */
public class DetailsActivity extends Activity {

	public static final String EXTRA_USER = "user";

	public void finish() {
		super.finish();

		overridePendingTransition(0, 0);
	}

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		setContentView(R.layout.details);

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			_user = (User)extras.getSerializable(EXTRA_USER);
		}

		TextView name = (TextView)findViewById(R.id.name_text_view);
		name.setText(_user.getName() + " " + _user.getLastName());

		Contact contact = _user.getContact();

		TextView email = (TextView)findViewById(R.id.email_text_view);
		email.setText(contact.getEmailAddress());

		TextView birthday = (TextView)findViewById(R.id.birthday_text_view);
		birthday.setText(contact.getBirthday());

		ArrayList<String> phones = contact.getPhones();

		if (!phones.isEmpty()) {
			ListView listView = (ListView)findViewById(R.id.phones_list_view);

			ListAdapter adapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, phones);

			listView.setAdapter(adapter);
		}
	}

	private User _user;

}