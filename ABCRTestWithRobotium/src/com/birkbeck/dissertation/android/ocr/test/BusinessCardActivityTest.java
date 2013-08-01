package com.birkbeck.dissertation.android.ocr.test;

import com.birkbeck.dissertation.android.ocr.BusinessCardActivity;
import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class BusinessCardActivityTest extends ActivityInstrumentationTestCase2<BusinessCardActivity>{

	private Solo solo;
	
	public BusinessCardActivityTest(){
		super(BusinessCardActivity.class);
	}
	
	@Override
	public void setUp() throws Exception {
		//setUp() is run before a test case is started. 
		//This is where the solo object is created.
		solo = new Solo(getInstrumentation(), getActivity());
	}

	@Override
	public void tearDown() throws Exception {
		//tearDown() is run after a test case has finished. 
		//finishOpenedActivities() will finish all the activities that have been opened during the test execution.
		solo.finishOpenedActivities();
	}
	
	public void testLayout() throws Exception{
		
		// Test 1
		solo.assertCurrentActivity("Expected BusinessCardActivity", "BusinessCardActivity");
		
		boolean expected = true;
		boolean actual = solo.searchText("Capture image of a business card or " +
						"select image from the gallery!");
		//Assert that Text is found
		assertEquals("Text not found", expected, actual);
		
		/*
		int textNameId = com.birkbeck.dissertation.android.ocr.R.id.textName;
		assertNotNull(businessCardActivity.findViewById(textNameId));
				
		TextView textView = (TextView) businessCardActivity.findViewById(textNameId);
		assertEquals("Incorrect text of the textView", "Capture image of a business card or " +
						"select image from the gallery!", textView.getText());
		
		*/
		
	}
}
