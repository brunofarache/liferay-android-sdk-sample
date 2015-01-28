/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.mobile.sample.util;

import android.content.Context;

import android.util.Log;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.task.callback.UploadProgressAsyncTaskCallback;
import com.liferay.mobile.android.v62.dlapp.DLAppService;

import java.io.InputStream;

import org.apache.http.entity.mime.content.InputStreamBody;

import org.json.JSONObject;

/**
 * @author Bruno Farache
 */
public class UploadFileUtil {

	public static void upload(Context context, long groupId) throws Exception {
		Session session = SettingsUtil.getSession();
		session.setCallback(_getCallback());

		DLAppService service = new DLAppService(session);

		String filename = "logo.png";
		String mimeType = "image/png";

		InputStream is = context.getAssets().open(filename);

		InputStreamBody file = new InputStreamBody(is, mimeType, filename);
		service.addFileEntry(
			groupId, 0, "", mimeType, filename, "", "", file, null);
	}

	private static UploadProgressAsyncTaskCallback<JSONObject> _getCallback() {
		return new UploadProgressAsyncTaskCallback<JSONObject>() {

			@Override
			public void onFailure(Exception exception) {
				Log.d(_CLASS_NAME, "exception = " + exception);
			}

			@Override
			public void onProgress(int totalBytes) {
				Log.d(_CLASS_NAME, "totalBytes = " + totalBytes);
			}

			@Override
			public void onSuccess(JSONObject result) {
				Log.d(_CLASS_NAME, "result = " + result);
			}

			@Override
			public JSONObject transform(Object obj) throws Exception {
				return (JSONObject)obj;
			}

		};
	}

	private static String _CLASS_NAME = UploadFileUtil.class.getName();

}