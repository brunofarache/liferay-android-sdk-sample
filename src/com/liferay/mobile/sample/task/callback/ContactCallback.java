package com.liferay.mobile.sample.task.callback;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.task.callback.BaseAsyncTaskCallback;
import com.liferay.mobile.android.v62.phone.PhoneService;
import com.liferay.mobile.sample.activity.DetailsActivity;
import com.liferay.mobile.sample.model.Contact;
import com.liferay.mobile.sample.model.User;
import com.liferay.mobile.sample.util.SettingsUtil;

public class ContactCallback extends BaseAsyncTaskCallback<Contact> {

	public ContactCallback(Context context, User user) {
		_context = context;
		_user = user;
	}

	public JSONArray inBackground(JSONArray array) {
		Session session = SettingsUtil.getSession();
		PhoneService phoneService = new PhoneService(session);

		try {
			JSONArray jsonArray = phoneService.getPhones(
				"com.liferay.portal.model.Contact", _user.getContactId());

			_phones = new ArrayList<String>();

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);

				_phones.add(jsonObj.getString(_NUMBER));
			}
		}
		catch (Exception e) {
			Log.e(_CLASS_NAME, "Couldn't get Phones", e);
		}

		return array;
	}

	public void onFailure(Exception exception) {
		Log.e(_CLASS_NAME, "Couldn't get Contact", exception);
	}

	public void onSuccess(Contact contact) {
		_user.setContact(contact);

		Intent intent = new Intent(_context, DetailsActivity.class);

		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		intent.putExtra(DetailsActivity.EXTRA_USER, _user);

		_context.startActivity(intent);
	}

	public Contact transform(Object obj) {
		Contact contact = null;

		try {
			contact = new Contact((JSONObject)obj, _phones);
		}
		catch (JSONException je) {
			Log.e(_CLASS_NAME, "Couldn't transform JSONObject to Contact", je);
		}

		return contact;
	}

	private static String _CLASS_NAME = ContactCallback.class.getName();
	private static String _NUMBER = "number";

	private Context _context;
	ArrayList<String> _phones;
	private User _user;

}