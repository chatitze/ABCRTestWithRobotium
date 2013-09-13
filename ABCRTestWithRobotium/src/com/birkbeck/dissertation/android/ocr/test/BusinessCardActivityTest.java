package com.birkbeck.dissertation.android.ocr.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.ImageView;

import com.birkbeck.dissertation.android.ocr.AndroidOCRActivity;
import com.birkbeck.dissertation.android.ocr.BusinessCardActivity;
import com.jayway.android.robotium.solo.Solo;

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
	
	
	public void testGUI() throws Exception{
		
		// Check if the current activity is BusinessCardActivity
		solo.assertCurrentActivity("Expected BusinessCardActivity", "BusinessCardActivity");
		
		boolean expected = true;
		boolean actual = solo.searchText("Capture image of a business card or " +
						"select image from the gallery!");
		//Assert that Text is found
		assertEquals("Expected text not found", expected, actual);
		
		// Check if "Take a photo" button is enabled
		actual = solo.searchButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.takebutton_text), 1, true);
		assertEquals("Expected 'Take photo' button is not found!", expected, actual);
		
		// Check if "Select a photo" button is enabled
		actual = solo.searchButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.selectbutton_text), 1, true);
		assertEquals("Expected 'Select photo' button is not found!", expected, actual);
		
		// Check if "Execute OCR" button is DISABLED
		actual = solo.searchButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.execbutton_text), 1, false);
		assertEquals("Expected 'Execute OCR' button is not found!", expected, actual);
		
		// Select an image from the gallery
		solo.clickOnButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.selectbutton_text));
		solo.waitForView(ImageView.class);
		
		//check if the execute OCR button is ENABLED after an image was selected
		actual = solo.searchButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.execbutton_text), 1, true);
		assertEquals("Expected 'Execute OCR' button is not found!", expected, actual);
		
		// Click on "Execute OCR" button to run OCR
		solo.clickOnButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.execbutton_text));
		
		// Check if the current activity is AndroidOCRActivity
		solo.assertCurrentActivity("Expected AndroidOCRActivity", AndroidOCRActivity.class);
		
		//----------- Name -----------------
		actual = solo.searchText(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.text_name));
		assertEquals("Expected 'Name:' tag is not found!", expected, actual);
		
		actual = solo.searchEditText("Chatitze MOUMTN");
		assertEquals("Expected 'name' edit text is not found!", expected, actual);
		
		//------------ Mobile ----------------
		actual = solo.searchText(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.text_mobilenumber));
		assertEquals("Expected 'Mobile:' tag is not found!", expected, actual);
		
		actual = solo.searchEditText("+9021247a35");
		assertEquals("Expected 'mobile' edit text is not found!", expected, actual);
		
		//------------ Work ----------------
		actual = solo.searchText(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.text_worknumber));
		assertEquals("Expected 'Work:' tag is not found!", expected, actual);
				
		actual = solo.searchEditText("+90657O4");
		assertEquals("Expected 'work' edit text is not found!", expected, actual);
		
		//------------ Email ----------------
		actual = solo.searchText(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.text_email));
		assertEquals("Expected 'E-mail:' tag is not found!", expected, actual);
				
		actual = solo.searchEditText("chatitzem@garanti.com.tr");
		assertEquals("Expected 'email' edit text is not found!", expected, actual);
				
		//------------ Company ----------------
		actual = solo.searchText(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.text_companyname));
		assertEquals("Expected 'Company:' tag is not found!", expected, actual);
						
		actual = solo.searchEditText("enter company");
		assertEquals("Expected 'company' edit text is not found!", expected, actual);
			
		//------------ Title ----------------
		actual = solo.searchText(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.text_title));
		assertEquals("Expected 'Title:' tag is not found!", expected, actual);
								
		actual = solo.searchEditText("` Engineer");
		assertEquals("Expected 'title' edit text is not found!", expected, actual);
		
		//------------ Address ----------------
		actual = solo.searchText("Address:");
		assertEquals("Expected 'Address:' tag is not found!", expected, actual);
							
		actual = solo.searchEditText("enter address");
		assertEquals("Expected 'address' edit text is not found!", expected, actual);
				
		// Check if the "Add Contact" button is found and is Enabled
		actual = solo.searchButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.addbutton_text), 1, true);
		assertEquals("Expected 'Add Contact' button is not found!", expected, actual);
		
		// Check if the "Email" button is found and is Enabled
		actual = solo.searchButton("Email", true);
		assertEquals("Expected 'Email' button is not found!", expected, actual);
		
		// Check if the "Copy to Clipboard" button is found and is Enabled
		actual = solo.searchButton("Copy to Clipboard", true);
		assertEquals("Expected 'Copy to Clipboard' button is not found!", expected, actual);
		
	}

	public void testScanBusinessCardAndAddAsContact() throws Exception {
		
		boolean expected = true;
		boolean actual   = false;
		
		// Check if the current activity is BusinessCardActivity
		solo.assertCurrentActivity("Expected BusinessCardActivity", "BusinessCardActivity");
				
		// Select an image from the gallery
		solo.clickOnButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.selectbutton_text));
		solo.waitForView(ImageView.class);
		
		// Click on "Execute OCR" button to run OCR
		solo.clickOnButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.execbutton_text));
				
		// Check if the current activity is AndroidOCRActivity
		solo.assertCurrentActivity("Expected AndroidOCRActivity", AndroidOCRActivity.class);
		
		//----------- Name -----------------
		EditText editText = solo.getEditText(1);
		solo.clearEditText(editText);
		solo.enterText(editText, "Chatitze MOUMIN");
		
		//------------ Mobile ----------------
		editText = solo.getEditText("+9021247a35");
		solo.clearEditText(editText);
		solo.enterText(editText, "07429602810");
				
		//------------ Work ----------------
		editText = solo.getEditText("+90657O4");
		solo.clearEditText(editText);
		solo.enterText(editText, "0203456565");
		
		//------------ Email ----------------
		actual = solo.searchEditText("chatitzem@garanti.com.tr");
		assertEquals("Expected 'chatitzem@garanti.com.tr' email is not found!", expected, actual);
		
		//------------ Company ----------------
		editText = solo.getEditText("enter company");
		solo.clearEditText(editText);
		solo.enterText(editText, "Garanti Bank");
				
		//------------ Title ----------------
		editText = solo.getEditText("` Engineer");
		solo.clearEditText(editText);
		solo.enterText(editText, "Software Engineer");
					
		//------------ Address ----------------
		editText = solo.getEditText("enter address");							
		solo.clearEditText(editText);
		solo.enterText(editText, "2 Spencer Way London UK");
		
		// Click on "Add Contact" button to add as a new Contact
		solo.clickOnButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.addbutton_text));
		
		solo.waitForText("Chatitze MOUMTN");
		//solo.goBack();
	
	}
	
	public void testScanBusinessCardAndSendAsEmail() throws Exception {
		
		// Check if the current activity is BusinessCardActivity
		solo.assertCurrentActivity("Expected BusinessCardActivity", "BusinessCardActivity");
						
		// Select an image from the gallery
		solo.clickOnButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.selectbutton_text));
		solo.waitForView(ImageView.class);
				
		// Click on "Execute OCR" button to run OCR
		solo.clickOnButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.execbutton_text));
						
		// Check if the current activity is AndroidOCRActivity
		solo.assertCurrentActivity("Expected AndroidOCRActivity", AndroidOCRActivity.class);
				
		//Correct the extracted text
		EditText editText = solo.getEditText("Chatitze MOUMTN"
											 + "\n` Engineer");
		solo.clearEditText(editText);
		solo.enterText(editText, "Chatitze MOUMIN");
		solo.enterText(editText, "\nEngineer" +
				 				 "\nPayment Systems Application Development" +
				 				 "\nPhone : +90 212 478 35 35" +
				 				 "\nExt   : 6327" +
				 				 "\nFax   : +90 212 657 O4 73" +
				 				 "\ne-mail:chatitzem@garanti.com.tr");
		// Click on "Email" button to as an email
		solo.clickOnButton("Email");
				
	}
	public void testScanBusinessCardAndCopyToClipboard() throws Exception {
		
		// Check if the current activity is BusinessCardActivity
		solo.assertCurrentActivity("Expected BusinessCardActivity", "BusinessCardActivity");
								
		// Select an image from the gallery
		solo.clickOnButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.selectbutton_text));
		solo.waitForView(ImageView.class);
						
		// Click on "Execute OCR" button to run OCR
		solo.clickOnButton(solo.getString(com.birkbeck.dissertation.android.ocr.R.string.execbutton_text));
								
		// Check if the current activity is AndroidOCRActivity
		solo.assertCurrentActivity("Expected AndroidOCRActivity", AndroidOCRActivity.class);
						
		//Correct the extracted text
		EditText editText = solo.getEditText(0);
		solo.clearEditText(editText);
		solo.enterText(editText, "Chatitze MOUMIN" +
						 		 "\nEngineer" +
						 		 "\nPayment Systems Application Development" +
						 		 "\nPhone : +90 212 478 35 35" +
						 		 "\nExt   : 6327" +
						 		 "\nFax   : +90 212 657 O4 73" +
						 		 "\ne-mail:chatitzem@garanti.com.tr");
						
		// Click on "Copy to Clipboard" button to copy the phones clipboard
		solo.clickOnButton("Copy to Clipboard");
						
	}
}
