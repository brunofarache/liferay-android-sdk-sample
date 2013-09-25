package com.liferay.mobile.sample.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

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
	private ArrayList<String> _phones = new ArrayList<String>();
	private transient DateFormat _formatter;

}