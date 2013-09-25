package com.liferay.mobile.sample.task.callback;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.liferay.mobile.android.task.callback.BaseAsyncTaskCallback;
import com.liferay.mobile.sample.activity.DetailsActivity;
import com.liferay.mobile.sample.model.Contact;
import com.liferay.mobile.sample.model.User;

public class ContactCallback extends BaseAsyncTaskCallback<Contact> {

	public ContactCallback(Context context, User user) {
		_context = context;
		_user = user;
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
			contact = new Contact((JSONObject)obj);
		}
		catch (JSONException je) {
			Log.e(_CLASS_NAME, "Couldn't transform JSONObject to Contact", je);
		}

		return contact;
	}

	private static String _CLASS_NAME = ContactCallback.class.getName();

	private Context _context;
	private User _user;

}