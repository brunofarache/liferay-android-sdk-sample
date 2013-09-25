package com.liferay.mobile.sample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.liferay.mobile.sample.R;
import com.liferay.mobile.sample.model.Contact;
import com.liferay.mobile.sample.model.User;

public class DetailsActivity extends Activity {

	public static final String EXTRA_USER = "user";

	public void finish() {
		super.finish();

		overridePendingTransition(0, 0);
	}

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		setContentView(R.layout.details);

		Bundle extras = getIntent().getExtras();
		
		if (extras != null) {
			_user = (User)extras.getSerializable(EXTRA_USER);
		}
		
		TextView name = (TextView)findViewById(R.id.name_text_view);
		name.setText(_user.getName() + " " + _user.getLastName());

		TextView email = (TextView)findViewById(R.id.email_text_view);
		email.setText(_user.getEmailAddress());

		Contact contact = _user.getContact();

		TextView birthday = (TextView)findViewById(R.id.birthday_text_view);
		birthday.setText(contact.getBirthday());

		if (!contact.getPhones().isEmpty()) {
			TextView phone = (TextView)findViewById(R.id.phone_text_view);
			phone.setText(contact.getPhones().get(0));
		}
	}
	
	private User _user;

}