package com.liferay.mobile.sample.activity;

import com.liferay.mobile.sample.R;
import com.liferay.mobile.sample.util.SettingsUtil;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

public class SettingsActivity extends PreferenceActivity
	implements OnSharedPreferenceChangeListener {

	public void finish() {
		super.finish();

		overridePendingTransition(0, 0);
	}

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		addPreferencesFromResource(R.layout.settings);
		
		PreferenceScreen preferenceScreen = getPreferenceScreen();
		
		_loginPreference =
			(EditTextPreference)preferenceScreen.findPreference(
				SettingsUtil.LOGIN);

		_passwordPreference =
			(EditTextPreference)preferenceScreen.findPreference(
				SettingsUtil.PASSWORD);
	
		_serverPreference =
			(EditTextPreference)preferenceScreen.findPreference(
				SettingsUtil.SERVER);
		
		_loginPreference.setText(SettingsUtil.getLogin());
		_loginPreference.setSummary(SettingsUtil.getLogin());
		
		_passwordPreference.setText(SettingsUtil.getPassword());
		
		_serverPreference.setText(SettingsUtil.getServer());
		_serverPreference.setSummary(SettingsUtil.getServer());
		
		SharedPreferences sharedPreferences =
			PreferenceManager.getDefaultSharedPreferences(getBaseContext());

		sharedPreferences.registerOnSharedPreferenceChangeListener(this);
	}
	
	public void onSharedPreferenceChanged(
		SharedPreferences sharedPreferences, String key) {

		if (key.equals(SettingsUtil.LOGIN)) {
			_loginPreference.setSummary(SettingsUtil.getLogin());
		}
		else if (key.equals(SettingsUtil.SERVER)) {
			_serverPreference.setSummary(SettingsUtil.getServer());
		}
	}
	
	private EditTextPreference _loginPreference;
	private EditTextPreference _passwordPreference;
	private EditTextPreference _serverPreference;
	
}