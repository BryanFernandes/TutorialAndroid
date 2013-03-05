package com.example.tutorial.test;

import com.example.tutorial.R;
import com.example.tutorial.SharedPrefs;

import android.content.SharedPreferences;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefsUnitTest extends
		ActivityInstrumentationTestCase2<SharedPrefs> {

	public static String filename= "Teste123";
	
	private SharedPrefs tActivity;
	private Button tSave, tLoad;
	private EditText tSetSharedPrefs;
	private TextView tLoadSharedPrefs;

	@SuppressWarnings("deprecation")
	public SharedPrefsUnitTest() {
		super("com.example.tutorial", SharedPrefs.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();

		tActivity = this.getActivity();
		tSave = (Button) tActivity.findViewById(R.id.bSave);
		tLoad = (Button) tActivity.findViewById(R.id.bLoad);
		tSetSharedPrefs = (EditText) tActivity
				.findViewById(R.id.etSharedPrefes);
		tLoadSharedPrefs = (TextView) tActivity
				.findViewById(R.id.tvLoadSharedPrefes);

	}

	public void testViews() {
		assertNotNull(tActivity);
		assertNotNull(tLoad);
		assertNotNull(tSave);
		assertNotNull(tLoadSharedPrefs);
		assertNotNull(tSetSharedPrefs);
	}
	
	public void testSharedPrefs() {
		tSetSharedPrefs.clearComposingText();
		TouchUtils.tapView(this, tSetSharedPrefs);
		
		tActivity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				tSetSharedPrefs.setText("Teste123");
			}
		});
		
		TouchUtils.clickView(this, tSave);
		TouchUtils.clickView(this, tLoad);
		
		assertEquals("Teste123", tLoadSharedPrefs.getText().toString());
	}
}
