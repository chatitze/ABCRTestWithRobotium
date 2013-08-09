package com.birkbeck.dissertation.android.ocr.test;

import com.birkbeck.dissertation.android.ocr.AndroidOCRActivity;
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
		assertEquals("Expected text not found", expected, actual);
		
		
		// Searches for a button with the text in the current user interface 
		actual = solo.searchButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.takebutton_text), 1, true);
		assertEquals("Expected 'Take photo' button is not found!", expected, actual);
		
		actual = solo.searchButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.selectbutton_text), 1, true);
		assertEquals("Expected 'Select photo' button is not found!", expected, actual);
		
		actual = solo.searchButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.execbutton_text), 1, false);
		assertEquals("Expected 'Execute OCR' button is not found!", expected, actual);
		
		solo.goBack();
		solo.goBackToActivity("BusinessCardActivity");
		
		// Click a button which will start a new Activity
		// clicks on a button with the "text" text 
		// Here we use the ID of the string to find the right button
		/*solo.clickOnButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.takebutton_text));
		
		
		solo.clickOnButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.execbutton_text));
		solo.assertCurrentActivity("Wrong activiy! Expected:AndroidOCRActivity", AndroidOCRActivity.class);
		*/
		
		
		
		/*
		int textNameId = com.birkbeck.dissertation.android.ocr.R.id.textName;
		assertNotNull(businessCardActivity.findViewById(textNameId));
				
		TextView textView = (TextView) businessCardActivity.findViewById(textNameId);
		assertEquals("Incorrect text of the textView", "Capture image of a business card or " +
						"select image from the gallery!", textView.getText());
		
		*/
		
	}
}
