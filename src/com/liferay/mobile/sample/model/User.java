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

package com.liferay.mobile.sample.model;

import android.annotation.SuppressLint;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Bruno Farache
 */
@SuppressWarnings("serial")
public class User implements Comparable<User>, Serializable {

	public static final String CONTACT_ID = "contactId";

	public static final String LAST_NAME = "lastName";

	public static final String NAME = "firstName";

	public static final String USER_ID = "userId";

	public User(JSONObject jsonObj) throws JSONException {
		_userId = jsonObj.getLong(USER_ID);
		_contactId = jsonObj.getLong(CONTACT_ID);
		_name = jsonObj.getString(NAME);
		_lastName = jsonObj.getString(LAST_NAME);
	}

	@SuppressLint("DefaultLocale")
	public int compareTo(User user) {
		return _name.toLowerCase().compareTo(user.getName().toLowerCase());
	}

	public Contact getContact() {
		return _contact;
	}

	public long getContactId() {
		return _contactId;
	}

	public String getLastName() {
		return _lastName;
	}

	public String getName() {
		return _name;
	}

	public long getUserId() {
		return _userId;
	}

	public void setContact(Contact contact) {
		_contact = contact;
	}

	public void setContactId(long contactId) {
		_contactId = contactId;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String toString() {
		return _name;
	}

	private Contact _contact;
	private long _contactId;
	private String _lastName;
	private String _name;
	private long _userId;

}