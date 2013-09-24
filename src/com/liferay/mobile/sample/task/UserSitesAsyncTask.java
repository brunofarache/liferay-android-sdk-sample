package com.liferay.mobile.sample.task;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.liferay.mobile.android.service.ServiceContext;
import com.liferay.mobile.android.v62.group.GroupService;
import com.liferay.mobile.sample.activity.MainActivity;
import com.liferay.mobile.sample.model.UserSite;

import android.os.AsyncTask;
import android.util.Log;

public class UserSitesAsyncTask extends
		AsyncTask<Void, Void, ArrayList<UserSite>> {

	public UserSitesAsyncTask(MainActivity activity) {
		_activity = activity;
	}

	public ArrayList<UserSite> doInBackground(Void... params) {
		ArrayList<UserSite> userSites = new ArrayList<UserSite>();

		ServiceContext context = new ServiceContext(
			"http://10.0.2.2:8080", "test@liferay.com", "test");
		
		GroupService service = new GroupService(context);
		
		try {
			JSONArray jsonArray = service.getUserSites();
			
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				
				userSites.add(new UserSite(jsonObj));
			}
		}
		catch (Exception e) {
			Log.e(_CLASS_NAME, "Couldn't get user sites", e);
		}
		
		return userSites;
	}
	
	public void onPostExecute(ArrayList<UserSite> sites) {
		_activity.updateUserSites(sites);
	}

	private static String _CLASS_NAME = UserSitesAsyncTask.class.getName();

	private MainActivity _activity;

}