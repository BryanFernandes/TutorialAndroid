package com.example.tutorial.test;

import com.example.tutorial.TextPlay;

import android.graphics.Color;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.text.InputType;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlayTestUnit extends
		ActivityInstrumentationTestCase2<TextPlay> {

	private TextPlay tActivity;
	private Button tTryCommand;
	private ToggleButton tPassword;
	private TextView tResults;
	private EditText tCommand;

	@SuppressWarnings("deprecation")
	public TextPlayTestUnit() {
		super("com.example.tutorial", TextPlay.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		tActivity = this.getActivity();
		tTryCommand = (Button) tActivity
				.findViewById(com.example.tutorial.R.id.bResults);
		tPassword = (ToggleButton) tActivity
				.findViewById(com.example.tutorial.R.id.tbPassword);
		tResults = (TextView) tActivity
				.findViewById(com.example.tutorial.R.id.tvResults);
		tCommand = (EditText) tActivity
				.findViewById(com.example.tutorial.R.id.etCommands);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@SmallTest
	public void testViews() {
		assertNotNull(tActivity);
		assertNotNull(tTryCommand);
		assertNotNull(tPassword);
		assertNotNull(tResults);
		assertNotNull(tCommand);
	}

	@SmallTest
	public void testBlueCommand() {
		TouchUtils.clickView(this, tPassword);
		tCommand.clearComposingText();
		TouchUtils.tapView(this, tCommand);

		tActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				tCommand.setText("blue");
			}
		});

		TouchUtils.clickView(this, tTryCommand);

		assertEquals("blue", tResults.getText().toString());
		assertEquals(Color.BLUE, tResults.getCurrentTextColor());
		assertEquals(Gravity.CENTER, tResults.getGravity());
	}

	@SmallTest
	public void testRightCommand() {
		TouchUtils.clickView(this, tPassword);
		tCommand.clearComposingText();
		TouchUtils.tapView(this, tCommand);

		tActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				tCommand.setText("right");
			}
		});

		TouchUtils.clickView(this, tTryCommand);

		assertEquals("right", tResults.getText().toString());
		assertEquals(Color.BLACK, tResults.getCurrentTextColor());
	}

	@SmallTest
	public void testLeftCommand() {
		TouchUtils.clickView(this, tPassword);
		tCommand.clearComposingText();
		TouchUtils.tapView(this, tCommand);

		tActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				tCommand.setText("left");
			}
		});

		TouchUtils.clickView(this, tTryCommand);

		assertEquals("left", tResults.getText().toString());
		assertEquals(Color.BLACK, tResults.getCurrentTextColor());
	}

	@SmallTest
	public void testCenterCommand() {
		TouchUtils.clickView(this, tPassword);
		tCommand.clearComposingText();
		TouchUtils.tapView(this, tCommand);

		tActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				tCommand.setText("center");
			}
		});

		TouchUtils.clickView(this, tTryCommand);

		assertEquals("center", tResults.getText().toString());
		assertEquals(Color.BLACK, tResults.getCurrentTextColor());
	}

	public void testWTFCommand() {
		TouchUtils.clickView(this, tPassword);
		tCommand.clearComposingText();
		TouchUtils.tapView(this, tCommand);

		tActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				tCommand.setText("WTF");
			}
		});

		TouchUtils.clickView(this, tTryCommand);

		assertEquals("WTF!!!!", tResults.getText().toString());
	}

	public void testInvalidCommand() {
		TouchUtils.clickView(this, tPassword);
		tCommand.clearComposingText();
		TouchUtils.tapView(this, tCommand);
		sendKeys("1");
		
		TouchUtils.clickView(this, tTryCommand);
		
		assertEquals("invalid", tResults.getText().toString());
		assertEquals(Color.BLACK, tResults.getCurrentTextColor());
	}

	public void testVisibleEditTextON() {
		tCommand.clearComposingText();
		TouchUtils.tapView(this, tCommand);

		tActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				tCommand.setText("Teste de Visibilidade");
			}
		});
		TouchUtils.clickView(this, tPassword);
		TouchUtils.clickView(this, tPassword);
		TouchUtils.clickView(this, tPassword);

		TouchUtils.clickView(this, tPassword);
		assertEquals(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD,
				tCommand.getInputType());
		assertEquals(true, tPassword.isChecked());
	}

	public void testVisibleEditTextOFF() {
		tCommand.clearComposingText();
		TouchUtils.tapView(this, tCommand);

		tActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				tCommand.setText("Teste de Visibilidade");
			}
		});
		TouchUtils.clickView(this, tPassword);
		TouchUtils.clickView(this, tPassword);

		TouchUtils.clickView(this, tPassword);
		assertEquals(InputType.TYPE_CLASS_TEXT, tCommand.getInputType());
		assertEquals(false, tPassword.isChecked());
	}
}
