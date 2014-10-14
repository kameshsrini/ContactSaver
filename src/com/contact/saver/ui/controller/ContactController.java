package com.contact.saver.ui.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;







import com.contact.saver.ui.FileHelper;
import com.contact.saver.ui.model.Contact;
import com.contact.saver.ui.model.ContactAddress;
import com.contact.saver.ui.model.ContactName;
import com.contact.saver.ui.view.ContactUserInterface;

/**
 *  Constructor implements JButtons and JRadiobutton ActionListener and JList ListSelectionListener 
 */  
public final class ContactController implements ActionListener,
		ListSelectionListener {
	/*Setting COMMAND value to each action */ 
	public static final String ADD_COMMAND = "add";
	public static final String DELETE_COMMAND = "delete";
	public static final String RESET_COMMAND = "reset";
	public static final String MODIFY_COMMAND = "modify";
	public static final String SET_MALE_COMMAND = "male";
	public static final String SET_FEMALE_COMMAND = "female";
	
	Timer timer;
	FileHelper mFileHelper = new FileHelper();
	DefaultListModel<Contact> mContactList = new DefaultListModel<Contact>();
	
	/**
	 *  Object of ContactUserInterface Class 
	 */ 
	ContactUserInterface mUI;

	public void setContactUserInterface(
			ContactUserInterface contactUserInterface) {
		    mUI = contactUserInterface;
	}

	/** 
	 * Override Method to check the ActionListener Command Value and to perform appropriate function 
	 */ 
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		/* Whenever an action is performed the UI label colors are reset */
		mUI.colorReset();
		/* Checks command value for "add" */
		if (ADD_COMMAND.equals(command)) {
			/* check if Valid InputFields and Present and FirstName, LastName and MiddleName of a person doesn't exist to perform add */
			if (validateInputFields() && !isContactAlreadyThere() && mUI.mode==1) 
			{
				// check if the FN, LN, MIDDLE is already there.
			    performAdd();
			    timedDisplay("Contact Has Been Added!", new Color(7,48,3));
				ContactUserInterface.playSound("b2.wav");
				}
				else if (mUI.mode==0 && validateInputFields() && !isModifyContactAlreadyThere())
				{
					performDelete();
					performAdd();
					timedDisplay("Contact Has Been Modified", new Color(7,48,3));
					ContactUserInterface.playSound("b2.wav");
				}
		}
		/* Checks command value for "delete" */ 
		else if (DELETE_COMMAND.equals(command)) {
			performDelete();
			timedDisplay("Contact Has Been Deleted!",Color.red);
			ContactUserInterface.playSound("b2.wav");
			mUI.formReset();
		}
		/* Checks command value for "reset" */ 
		else if (RESET_COMMAND.equals(command)) {
			mUI.formReset();
			ContactUserInterface.playSound("b2.wav");
		}
		
	}
	/** 
	 * Timed Status Display
	 * @param display
	 * @param color
	 */
	public void timedDisplay(String display, Color color)
	{
		ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	mUI.mStatusDisplayLabel.setVisible(false);
	        	timer.stop();
	        }
	    };
	    mUI.mStatusDisplayLabel.setText(display);
	    mUI.mStatusDisplayLabel.setVisible(true);
		mUI.mStatusDisplayLabel.setForeground(color);
	    timer = new Timer(2000, actionListener);
	    timer.start();
	}

	

	
	/** 
	 * Method to ValidateInputFields to check whether the required TextFields are entered if not to throw a Status  message 
	 */
	public boolean validateInputFields() {
		boolean validation = true;
		int x=90;
		String firstNameText = mUI.mFirstNameTextField.getText().trim();
		if (firstNameText.length() < 1 || firstNameText.contains("|")) {
			 ContactUserInterface.playSound("b1.wav");
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mFirstNameLabel.setForeground(Color.red);
			validation = false;
			x=x-10;
			mUI.mProgressBar.setValue(x);
		}
		String lastNameText = mUI.mLastNameTextField.getText().trim();
		if (lastNameText.length() < 1 || firstNameText.contains("|")) {
			ContactUserInterface.playSound("b1.wav");
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mLastNameLabel.setForeground(Color.red);
			validation = false;
			x=x-10;
			mUI.mProgressBar.setValue(x);
		}
		String addressLine1Text = mUI.mAddressLine1TextField.getText().trim();
		if (addressLine1Text.length() < 1 || firstNameText.contains("|")) {
			ContactUserInterface.playSound("b1.wav");
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mAddressLine1Label.setForeground(Color.red);
			validation = false;
			x=x-10;
			mUI.mProgressBar.setValue(x);
		}
		String cityText = mUI.mCityTextField.getText().trim();
		if (cityText.length() < 1 || firstNameText.contains("|")) {
			ContactUserInterface.playSound("b1.wav");
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mCityLabel.setForeground(Color.red);
			validation = false;
			x=x-10;
			mUI.mProgressBar.setValue(x);
		}
		String stateText = mUI.mStateTextField.getText().trim();
		if (stateText.length() < 1 || firstNameText.contains("|")) {
			ContactUserInterface.playSound("b1.wav");
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mStateLabel.setForeground(Color.red);
			validation = false;
			x=x-10;
			mUI.mProgressBar.setValue(x);
		}
		String zipCodeText = mUI.mZipCodeTextField.getText().trim();
		if (zipCodeText.length() < 1 || firstNameText.contains("|")) {
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 ContactUserInterface.playSound("b1.wav");
			 mUI.mZipCodeLabel.setForeground(Color.red);
			validation = false;
			x=x-10;
			mUI.mProgressBar.setValue(x);
		}
		
		String countryText = mUI.mCountryTextField.getText().trim();
		if (countryText.length() < 1 || firstNameText.contains("|")) {
			ContactUserInterface.playSound("b1.wav");
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mCountryLabel.setForeground(Color.red);
			validation = false;
			x=x-10;
			mUI.mProgressBar.setValue(x);
		}
		
		String phonenumberText = mUI.mPhoneNumberTextField.getText().trim();
		if (phonenumberText.length() < 1 || firstNameText.contains("|")) {
			ContactUserInterface.playSound("b1.wav");
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mPhoneNumberLabel.setForeground(Color.red);
			validation = false;
			x=x-10;
			mUI.mProgressBar.setValue(x);
		}
		
		String emailAddressText = mUI.mEmailAddressTextField.getText().trim();
		if (emailAddressText.length() < 1 || firstNameText.contains("|")) {
			ContactUserInterface.playSound("b1.wav");
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mEmailAddressLabel.setForeground(Color.red);
			validation = false;
			x=x-10;
			mUI.mProgressBar.setValue(x);
		}
		try
		{
		if (!mUI.mGenderButtonGroup.getSelection().isEnabled()) {
			ContactUserInterface.playSound("b1.wav");

			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mGenderLabel.setForeground(Color.red);
			validation = false;
			}
		} 
		
		catch(NullPointerException e)
		{
			ContactUserInterface.playSound("b1.wav");
			mUI.mGenderLabel.setForeground(Color.red);
		}
		return validation;
	}
	
	/** 
	 * Method to check whether the Contact Person Exist or not
	 */
	private boolean isContactAlreadyThere() {
		String middleInitialText = mUI.mMiddleInitialTextField.getText();
		Character middleInitial = '\u0000';
		if (middleInitialText.length() > 0) {
			middleInitial = middleInitialText.charAt(0);
		}

		ContactName contactName = new ContactName(
				mUI.mFirstNameTextField.getText(),
				mUI.mLastNameTextField.getText(), middleInitial);
		

		for (int i = 0; i < mContactList.getSize(); i++) 
		{
			Contact contact = mContactList.get(i);
			if (contact.mContactName.toString().equals(contactName.toString())) 
			{
				/* should not add this contact */
				mUI.showStatus( "This person already exist! Modify instead of adding", true);
				return true;
			}
		}
		return false;
	}
	
	
	private int ModifyContactNameIndex(Contact contact1) {
		String middleInitialText = mUI.mMiddleInitialTextField.getText();
		Character middleInitial = '\u0000';
		if (middleInitialText.length() > 0) {
			middleInitial = middleInitialText.charAt(0);
		}
		 
		ContactName contactName = new ContactName(
			contact1.mContactName.mFirstName,
				contact1.mContactName.mLastName, contact1.mContactName.mMiddleInitial);
		

		for (int i = 0; i < mContactList.getSize(); i++) 
		{
			Contact contact = mContactList.get(i);
			if (contact.mContactName.toString().equals(contactName.toString())) 
			{
				/* should not add this contact */
				return i;
			}
		}
		return -1;
	}
	
	private boolean isModifyContactAlreadyThere() {
		String middleInitialText = mUI.mMiddleInitialTextField.getText();
		Character middleInitial = '\u0000';
		if (middleInitialText.length() > 0) {
			middleInitial = middleInitialText.charAt(0);
		}

		ContactName contactName = new ContactName(
				mUI.mFirstNameTextField.getText(),
				mUI.mLastNameTextField.getText(), middleInitial);
		
		int x = ModifyContactNameIndex(mUI.mContactListView.getSelectedValue());
		for (int i = 0; i < mContactList.getSize(); i++) 
		{
			if (i!=x)
			{
			Contact contact = mContactList.get(i);
			if (contact.mContactName.toString().equals(contactName.toString())) 
			{
				/* should not add this contact */
				mUI.showStatus( "This person already exist! Modify instead of adding", true);
				return true;
			}
			}
		}
		return false;
	}
	/** 
	 * Method to perform delete of a contact 
	 */

	private void performDelete() {
		Contact contact = mUI.mContactListView.getSelectedValue();
		if (contact == null)
		{
			return;
		}
		/* remove person from the ContactList */
		mContactList.removeElement(contact);
		/* remove person from the file */
		mFileHelper.deleteContact(contact);
	}
	
	/** 
	 * Method to Add a contact 
	 */
	private void performAdd() 
	{
		String middleInitialText = mUI.mMiddleInitialTextField.getText();
		Character middleInitial = '\u0000';
		if (middleInitialText.length() > 0){
			middleInitial = middleInitialText.charAt(0);
		}
		
		/* Gets the ContactName FN,LN,MI from the appropriate TextFields */
		ContactName contactName = new ContactName(
				mUI.mFirstNameTextField.getText(),
				mUI.mLastNameTextField.getText(), middleInitial);
		
		
		Integer zipcode = Integer.parseInt(mUI.mZipCodeTextField.getText());
		/* Gets the ContactAddress ADL1,ADL2,City,State,ZipCode from the appropriate TextFields */
		ContactAddress contactAddress = new ContactAddress(
				mUI.mAddressLine1TextField.getText(),
				mUI.mAddressLine2TextField.getText(),
				mUI.mCityTextField.getText(), mUI.mStateTextField.getText(),
				zipcode,mUI.mCountryTextField.getText() );
		/* Get the phoneNumber from the TextField */
		String phoneNumber = mUI.mPhoneNumberTextField.getText();
		
		/* Get the emailAddres from the TextField */
		String emailAddress = mUI.mEmailAddressTextField.getText();
		
		/* Get the Gender from the RadioButton selected */
		Boolean isMale = false;
		String radioCommand = mUI.mGenderButtonGroup.getSelection()
				.getActionCommand();
		if (SET_MALE_COMMAND.equals(radioCommand)) {
			isMale = true;
		}
		
		/* Creates and Contact Class object and class the constructor with required parameters being passed */ 
		Contact contact = new Contact(contactName, contactAddress, phoneNumber,emailAddress, isMale);
		/* Adds the Contact to the JList */
		mContactList.addElement(contact);
		/** Call for sort **/
		sort();
		/* Adds the Contact to the File */
		sort();
		mFileHelper.addContact(contact);

		/* Reset as we have added this contact. */
		mUI.formReset();
		
	}

	/** 
	 * Override Method to get ListSelectionEvent and to perform appropriate function 
	 */ 
	@Override
	public void valueChanged(ListSelectionEvent e) 
	{	mUI.mode=0;
		mUI.mDeleteButton.setEnabled(true);
		mUI.colorReset();
		progressbarStatus();
		mUI.showStatus("       MODIFY OR DELETE THE CONTACT", false);
		Contact contact = mUI.mContactListView.getSelectedValue();
		if (contact != null) 
		mUI.showContact(contact);
	}

	
	/**
	 *  Method to show Contacts In JList 
	 */
	public ListModel<Contact> getAllContacts() 
	{
		/* Maintains a List getting contents from the File */
		List<Contact> contacts = mFileHelper.getAllContacts();
		/* Clears the List */
		mContactList.clear();
		/* Add Elements to the List */
		for (Contact contact : contacts) {
			mContactList.addElement(contact);
		}
		/** Call for Sort **/
		sort();
		/* Return the List */
		return mContactList;
	}
		
	
	
	/** 
	 * Method to Sort the JList content based on FirstName if not then LastName if not then MiddleInitial 
	 */
  
   void sort() 
   {
	   List<Contact> contacts = new ArrayList<Contact>();
	   for (int i=0; i<mContactList.getSize(); i++) {
			contacts.add(mContactList.get(i));
	   }
	   Collections.sort(contacts, new Comparator<Contact>() {
			public int compare(Contact contact1, Contact contact2) {
				String o1 = contact1.mContactName.mFirstName + contact1.mContactName.mLastName + contact1.mContactName.mMiddleInitial;
				String o2 = contact2.mContactName.mFirstName + contact2.mContactName.mLastName + contact2.mContactName.mMiddleInitial;
				return o1.compareTo(o2);
			}
	   });
	   /** 
	    * After Sorting Clears the List 
	    */
	   mContactList.clear();
	   
	   /**
	    *  Adds Sorted List Content Into the JList   
	    */
	   mContactList.clear();
		for (Contact contact : contacts) {
			mContactList.addElement(contact);
		}
	}
   
   /**
    * Progress Bar Indicator
    * @return
    */
   public boolean progressbarStatus() {
		boolean validation = true;
		int x=0;
		String firstNameText = mUI.mFirstNameTextField.getText().trim();
		if (firstNameText.length() > 0) {
			validation = false;
			x=x+10;
			mUI.mProgressBar.setValue(x);
		}
		String lastNameText = mUI.mLastNameTextField.getText().trim();
		if (lastNameText.length() > 0) {
			x=x+10;
			mUI.mProgressBar.setValue(x);
		}
		String addressLine1Text = mUI.mAddressLine1TextField.getText().trim();
		if (addressLine1Text.length() > 0) {
			x=x+10;
			mUI.mProgressBar.setValue(x);
		}
		String cityText = mUI.mCityTextField.getText().trim();
		if (cityText.length() > 0) {
			validation = false;
			x=x+10;
			mUI.mProgressBar.setValue(x);
		}
		String stateText = mUI.mStateTextField.getText().trim();
		if (stateText.length() > 0) {
			validation = false;
			x=x+10;
			mUI.mProgressBar.setValue(x);
		}
		String zipCodeText = mUI.mZipCodeTextField.getText().trim();
		if (zipCodeText.length() > 0) {
			validation = false;
			x=x+10;
			mUI.mProgressBar.setValue(x);
		}
		
		String countryText = mUI.mCountryTextField.getText().trim();
		if (countryText.length() > 0) {
			validation = false;
			x=x+10;
			mUI.mProgressBar.setValue(x);
		}
		
		String phonenumberText = mUI.mPhoneNumberTextField.getText().trim();
		if (phonenumberText.length() > 0) {
			validation = false;
			x=x+10;
			mUI.mProgressBar.setValue(x);
		}
		
		String emailAddressText = mUI.mEmailAddressTextField.getText().trim();
		if (emailAddressText.length() > 0) {
			
			validation = false;
			x=x+10;
			mUI.mProgressBar.setValue(x);
		}
		try
		{
		if (mUI.mGenderButtonGroup.getSelection().isEnabled()) {
			validation = false;
			}
		} 
		
		catch(NullPointerException e)
		{
		}
		return validation;
	}
}
   
   
   
