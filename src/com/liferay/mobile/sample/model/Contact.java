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

package com.liferay.mobile.sample.model;

import java.io.Serializable;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Bruno Farache
 */
@SuppressWarnings("serial")
public class Contact implements Serializable {

	public static final String BIRTHDAY = "birthday";

	public static final String EMAIL_ADDRESS = "emailAddress";

	public Contact(JSONObject jsonObj, ArrayList<String> phones)
		throws JSONException {

		setBirthday(jsonObj.getLong(BIRTHDAY));
		_emailAddress = jsonObj.getString(EMAIL_ADDRESS);
		_phones = phones;
	}

	public String getBirthday() {
		return _birthday;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public ArrayList<String> getPhones() {
		return _phones;
	}

	public void setBirthday(long timestamp) {
		_birthday = getDateFormatter().format(new Date(timestamp));
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setPhones(ArrayList<String> phones) {
		_phones = phones;
	}

	protected DateFormat getDateFormatter() {
		if (_formatter == null) {
			_formatter = DateFormat.getDateInstance(DateFormat.LONG);
		}

		return _formatter;
	}

	private String _birthday;
	private String _emailAddress;
	private transient DateFormat _formatter;
	private ArrayList<String> _phones = new ArrayList<String>();

}