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

package com.liferay.mobile.sample.model;

import android.annotation.SuppressLint;
import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Bruno Farache
 */
@SuppressLint("DefaultLocale")
@SuppressWarnings("serial")
public class UserSite implements Comparable<UserSite>, Serializable {

	public static final String COMPANY_ID = "companyId";

	public static final String FRIENDLY_URL = "friendlyURL";

	public static final String GROUP_ID = "groupId";

	public static final String LAST_SYNC = "lastSync";

	public static final String NAME = "name";

	public static final String ORGANIZATION_NAME_SUFFIX = " LFR_ORGANIZATION";

	public static final String SITE = "site";

	public static final String TYPE = "type";

	public UserSite(JSONObject jsonObj) throws JSONException {
		_companyId = jsonObj.getLong(COMPANY_ID);

		String friendlyUrl = jsonObj.getString(FRIENDLY_URL);

		if (friendlyUrl.startsWith("/")) {
			friendlyUrl = friendlyUrl.substring(1);

			friendlyUrl = friendlyUrl.replaceAll("/", "-");
		}

		_friendlyUrl = friendlyUrl;
		_id = jsonObj.getLong(GROUP_ID);
		_name = jsonObj.getString(NAME);
		_site = jsonObj.getBoolean(SITE);
		_type = jsonObj.getInt(TYPE);

		if (isNumber(_name)) {
			_name = _friendlyUrl;
		}
	}

	public int compareTo(UserSite group) {
		return _name.toLowerCase().compareTo(group.getName().toLowerCase());
	}

	public long getCompanyId() {
		return _companyId;
	}

	public String getFriendlyUrl() {
		return _friendlyUrl;
	}

	public long getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public int getType() {
		return _type;
	}

	public boolean isSite() {
		return _site;
	}

	public void setComapnyId(long companyId) {
		_companyId = companyId;
	}

	public void setFriendlyUrl(String friendlyUrl) {
		_friendlyUrl = friendlyUrl;
	}

	public void setId(long id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setSite(boolean site) {
		_site = site;
	}

	public void setType(int type) {
		_type = type;
	}

	public String toString() {
		return _name;
	}
	
	protected boolean isNumber(String s) {
		try { 
	        Integer.parseInt(s); 
	    }
		catch (NumberFormatException nfe) { 
	        return false; 
	    }

		return true;
	}

	private long _companyId;
	private String _friendlyUrl;
	private long _id = -1;
	private String _name;
	private boolean _site;
	private int _type;

}