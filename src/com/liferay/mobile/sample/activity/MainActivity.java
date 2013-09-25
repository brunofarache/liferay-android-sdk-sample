package com.liferay.mobile.sample.activity;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liferay.mobile.android.service.ServiceContext;
import com.liferay.mobile.android.task.callback.AsyncTaskCallback;
import com.liferay.mobile.android.v62.contact.ContactService;
import com.liferay.mobile.sample.R;
import com.liferay.mobile.sample.model.User;
import com.liferay.mobile.sample.task.UsersAsyncTask;
import com.liferay.mobile.sample.task.callback.ContactCallback;
import com.liferay.mobile.sample.util.ServiceUtil;

public class MainActivity extends ListActivity {

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		ArrayAdapter<User> adapter = new ArrayAdapter<User>(
			this, android.R.layout.simple_list_item_1);

		setListAdapter(adapter);

		UsersAsyncTask task = new UsersAsyncTask(this);

		task.execute();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	public void onListItemClick(
		ListView listView, View view, int position, long id) {

		User user = (User)getListAdapter().getItem(position);

		ServiceContext context = ServiceUtil.getServiceContext();
		ContactService contactService = new ContactService(context);

		AsyncTaskCallback callback = new ContactCallback(this, user);

		contactService.setCallback(callback);

		try {
			contactService.getContact(user.getContactId());
		}
		catch (Exception e) {
			Log.e(_CLASS_NAME, "Couldn't get Contact", e);
		}
	}

	@SuppressWarnings("unchecked")
	@SuppressLint("NewApi")
	public void updateUsers(ArrayList<User> users) {
		ArrayAdapter<User> adapter = (ArrayAdapter<User>)getListAdapter();

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