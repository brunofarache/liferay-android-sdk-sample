package com.liferay.mobile.sample.activity;

import java.util.ArrayList;

import com.liferay.mobile.sample.R;
import com.liferay.mobile.sample.model.UserSite;
import com.liferay.mobile.sample.task.UserSitesAsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;

@SuppressLint("NewApi")
@SuppressWarnings("unchecked")
public class MainActivity extends ListActivity {

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		ArrayAdapter<UserSite> adapter = new ArrayAdapter<UserSite>(
			this, android.R.layout.simple_list_item_1);

		setListAdapter(adapter);

		UserSitesAsyncTask task = new UserSitesAsyncTask(this);

		task.execute();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	public void updateUserSites(ArrayList<UserSite> sites) {
		ArrayAdapter<UserSite> adapter = 
			(ArrayAdapter<UserSite>)getListAdapter();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			adapter.addAll(sites);
		}
		else {
			for (UserSite site : sites) {
				adapter.add(site);
			}
		} 
	}

}