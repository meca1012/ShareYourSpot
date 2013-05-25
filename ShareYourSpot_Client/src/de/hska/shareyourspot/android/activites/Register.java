package de.hska.shareyourspot.android.activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import de.hska.shareyourspot.android.R;
import de.hska.shareyourspot.android.helper.AlertHelper;

public class Register extends Activity {

	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		final Button registerButton = (Button) findViewById(R.id.registerBtnRegister);

		registerButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText email = (EditText) findViewById(R.id.registerInputEmail);
				String inputEmail = email.getText().toString();

				EditText username = (EditText) findViewById(R.id.registerInputUsername);
				String inputUsername = username.getText().toString();

				EditText passwordFirst = (EditText) findViewById(R.id.registerInputPasswordFirst);
				String pw1 = passwordFirst.getText().toString();

				EditText passworSec = (EditText) findViewById(R.id.registerInputPasswordSecound);
				String pw2 = passworSec.getText().toString();

				if (!inputEmail.isEmpty() && !inputUsername.isEmpty()
						&& pw1.isEmpty() && pw2.isEmpty()) {
					if (pw1.equals(pw2)) {

					} else {
						new AlertHelper(context, R.string.passwordsNotMatchingTitle, R.string.passwordsNotMatchingText).fireAlert();
					}
				} else {
					new AlertHelper(context, R.string.passwordsNotMatchingTitle, R.string.passwordsNotMatchingText).fireAlert();
				}

			}
		});

	}

	public void registerStart(View view) {
		Intent intent = new Intent(this, Register.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
