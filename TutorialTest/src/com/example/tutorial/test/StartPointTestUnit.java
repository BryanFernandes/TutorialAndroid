package com.example.tutorial.test;

import com.example.tutorial.StartPoint;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.TextView;

public class StartPointTestUnit extends
		ActivityInstrumentationTestCase2<StartPoint> {

	private StartPoint tActivity;
	private Button tAddOne, tSubOne;
	private TextView tNumber;

	public StartPointTestUnit() {
		super("com.example.tutorial", StartPoint.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		tActivity = this.getActivity();
		tAddOne = (Button) tActivity
				.findViewById(com.example.tutorial.R.id.bAdd_one);
		tSubOne = (Button) tActivity
				.findViewById(com.example.tutorial.R.id.bSub_one);
		tNumber = (TextView) tActivity
				.findViewById(com.example.tutorial.R.id.tvNumber);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@SmallTest
	public void testViews() {
		assertNotNull(tActivity);
		assertNotNull(tAddOne);
		assertNotNull(tSubOne);
		assertNotNull(tNumber);
	}

	@SmallTest
	public void testAddOne() {
		TouchUtils.clickView(this, tAddOne);
		int one;

		try {
			one = Integer.parseInt(tNumber.getText().toString());
		} catch (NumberFormatException e) {
			one = -1;
		}

		assertTrue("Seu Total Ž 1", one == 1);

	}
	
	@SmallTest
	public void testSubOne(){
		TouchUtils.clickView(this, tSubOne);
		int minusOne;
		
		try{
			minusOne = Integer.parseInt(tNumber.getText().toString());
		} catch (NumberFormatException e) {
			minusOne = -2;
		}
		
		assertTrue("Seu Total Ž -1", minusOne == -1);
		
	}

}
