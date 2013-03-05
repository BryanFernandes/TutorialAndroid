package com.example.tutorial;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener {

	EditText sharedData = null;
	TextView dataResults = null;
	public static String filename = "MySharedString";
	public static String error = "N‹o foi poss’vel carregar os dados";
	SharedPreferences someData = null;

	/**
	 * method responsible for reference the XML file containing the layout
	 * definitions and initialize variables
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setupVariables();
		someData = getSharedPreferences(filename, 0);

	}

	/**
	 * Method responsible for initiating variables
	 */
	private void setupVariables() {
		Button save = (Button) findViewById(R.id.bSave);
		Button load = (Button) findViewById(R.id.bLoad);
		sharedData = (EditText) findViewById(R.id.etSharedPrefes);
		dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefes);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
	}

	/**
	 * @param v
	 *            - contains the reference of the application screen
	 */
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSave:
			String stringData = sharedData.getText().toString();
			SharedPreferences.Editor editor = someData.edit();
			editor.putString("sharedString", stringData);
			editor.commit();
			break;

		case R.id.bLoad:
			someData = getSharedPreferences(filename, 0);
			String dataReturned = someData.getString("sharedString", error);
			dataResults.setText(dataReturned);
			break;

		default:
			break;
		}
	}
}
