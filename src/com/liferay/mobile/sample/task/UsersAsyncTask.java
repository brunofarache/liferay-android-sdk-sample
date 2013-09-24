package com.liferay.mobile.sample.task;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.liferay.mobile.android.service.ServiceContext;
import com.liferay.mobile.android.v62.group.GroupService;
import com.liferay.mobile.android.v62.user.UserService;
import com.liferay.mobile.sample.activity.MainActivity;
import com.liferay.mobile.sample.model.User;
import com.liferay.mobile.sample.util.ServiceUtil;

import android.os.AsyncTask;
import android.util.Log;

public class UsersAsyncTask extends AsyncTask<Void, Void, ArrayList<User>> {

	public UsersAsyncTask(MainActivity activity) {
		_activity = activity;
	}

	public ArrayList<User> doInBackground(Void... params) {
		ArrayList<User> users = new ArrayList<User>();

		ServiceContext context = ServiceUtil.getServiceContext();
		UserService userService = new UserService(context);

		try {
			long groupId = getGuestGroupId(context);

			JSONArray jsonArray = userService.getGroupUsers(groupId);
			
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				
				users.add(new User(jsonObj));
			}
		}
		catch (Exception e) {
			Log.e(_CLASS_NAME, "Couldn't get user sites", e);
		}
		
		return users;
	}
	
	public void onPostExecute(ArrayList<User> sites) {
		_activity.updateUsers(sites);
	}
	
	protected long getGuestGroupId(ServiceContext context) throws Exception {
		long groupId = -1;

		GroupService groupService = new GroupService(context);
		
		JSONArray groups = groupService.getUserSites();
		
		for (int i = 0; i < groups.length(); i++) {
			JSONObject group = groups.getJSONObject(i);

			String name = group.getString("name");
			
			if (!name.equals("Guest")) {
				continue;
			}

			groupId = group.getLong("groupId");
		}
		
		if (groupId == -1) {
			throw new Exception("Couldn't find Guest group.");
		}
		
		return groupId;
	}

	private static String _CLASS_NAME = UsersAsyncTask.class.getName();

	private MainActivity _activity;

}