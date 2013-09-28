package com.liferay.mobile.sample.activity;

import java.util.ArrayList;

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

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.task.callback.AsyncTaskCallback;
import com.liferay.mobile.android.v62.contact.ContactService;
import com.liferay.mobile.sample.R;
import com.liferay.mobile.sample.model.User;
import com.liferay.mobile.sample.task.UsersAsyncTask;
import com.liferay.mobile.sample.task.callback.ContactCallback;
import com.liferay.mobile.sample.util.SettingsUtil;

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

		AsyncTaskCallback callback = new ContactCallback(this, user);

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
	}

	@SuppressWarnings("unchecked")
	@SuppressLint("NewApi")
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

	private static String _CLASS_NAME = MainActivity.class.getName();

}