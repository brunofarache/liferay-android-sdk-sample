package com.liferay.mobile.sample.model;

import android.annotation.SuppressLint;
import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class User implements Comparable<User>, Serializable {

	public static final String CONTACT_ID = "contactId";

	public static final String EMAIL_ADDRESS = "emailAddress";

	public static final String LAST_NAME = "lastName";

	public static final String NAME = "firstName";

	public static final String USER_ID = "userId";

	public User(JSONObject jsonObj) throws JSONException {
		_userId = jsonObj.getLong(USER_ID);
		_contactId = jsonObj.getLong(CONTACT_ID);
		_name = jsonObj.getString(NAME);
		_lastName = jsonObj.getString(LAST_NAME);
		_emailAddress = jsonObj.getString(EMAIL_ADDRESS);
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
	
	public String getEmailAddress() {
		return _emailAddress;
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
	
	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
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
	private String _emailAddress;
	private String _lastName;
	private String _name;
	private long _userId;

}