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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;





import com.contact.saver.ui.FileHelper;
import com.contact.saver.ui.model.Contact;
import com.contact.saver.ui.model.ContactAddress;
import com.contact.saver.ui.model.ContactName;
import com.contact.saver.ui.view.ContactUserInterface;

/* Constructor implements JButtons and JRadiobutton ActionListener and JList ListSelectionListener */  
public final class ContactController implements ActionListener,
		ListSelectionListener {
	/* Setting COMMAND value to each action */ 
	public static final String ADD_COMMAND = "add";
	public static final String DELETE_COMMAND = "delete";
	public static final String RESET_COMMAND = "reset";
	public static final String MODIFY_COMMAND = "modify";
	public static final String SET_MALE_COMMAND = "male";
	public static final String SET_FEMALE_COMMAND = "female";

	FileHelper mFileHelper = new FileHelper();
	DefaultListModel<Contact> mContactList = new DefaultListModel<Contact>();
	
	/* Object of ContactUserInterface Class */ 
	ContactUserInterface mUI;

	public void setContactUserInterface(
			ContactUserInterface contactUserInterface) {
		mUI = contactUserInterface;
	}

	/** Override Method to check the ActionListener Command Value and to perform appropriate function **/ 
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		/* Whenever an action is performed the UI label colors are reset */
		mUI.colorReset();
		/* Checks command value for "add" */
		if (ADD_COMMAND.equals(command)) {
			/* check if Valid InputFields and Present and FirstName, LastName and MiddleName of a person doesn't exist to perform add */
			if (validateInputFields() && !isContactAlreadyThere()) {
				// check if the FN, LN, MIDDLE is already there.
				performAdd();
			}
		}
		/* Checks command value for "delete" */ 
		else if (DELETE_COMMAND.equals(command)) {
			performDelete();
			mUI.formReset();
		}
		/* Checks command value for "reset" */ 
		else if (RESET_COMMAND.equals(command)) {
			mUI.formReset();
			mUI.mStatusLabel.setForeground(Color.MAGENTA);
		}
		/* Checks command value for "modify" */ 
		else if (MODIFY_COMMAND.equals(command)) {
			if (validateInputFields()) {
				performDelete();
				performAdd();
			}
		}
	}

	

	
	/** Method to ValidateInputFields to check whether the required TextFields are entered if not to throw a Status  message **/
	private boolean validateInputFields() {
		boolean validation = true;
		String firstNameText = mUI.mFirstNameTextField.getText().trim();
		if (firstNameText.length() < 1 || firstNameText.contains("|")) {
			
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mFirstNameLabel.setForeground(Color.red);
			validation = false;
		}
		String lastNameText = mUI.mLastNameTextField.getText().trim();
		if (lastNameText.length() < 1 || firstNameText.contains("|")) {
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mLastNameLabel.setForeground(Color.red);
			validation = false;
		}
		String addressLine1Text = mUI.mAddressLine1TextField.getText().trim();
		if (addressLine1Text.length() < 1 || firstNameText.contains("|")) {
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mAddressLine1Label.setForeground(Color.red);
			validation = false;
		}
		String cityText = mUI.mCityTextField.getText().trim();
		if (cityText.length() < 1 || firstNameText.contains("|")) {
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mCityLabel.setForeground(Color.red);
			validation = false;
		}
		String stateText = mUI.mStateTextField.getText().trim();
		if (stateText.length() < 1 || firstNameText.contains("|")) {
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mStateLabel.setForeground(Color.red);
			validation = false;
		}
		String zipCodeText = mUI.mZipCodeTextField.getText().trim();
		if (zipCodeText.length() < 1 || firstNameText.contains("|")) {
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mZipCodeLabel.setForeground(Color.red);
			validation = false;
		}
		String phonenumberText = mUI.mPhoneNumberTextField.getText().trim();
		if (phonenumberText.length() < 1 || firstNameText.contains("|")) {
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mPhoneNumberLabel.setForeground(Color.red);
			validation = false;
		}
		try
		{
		if (!mUI.mGenderButtonGroup.getSelection().isEnabled()) {
			 mUI.showStatus("      Invalid input! Enter fields marked red", true);
			 mUI.mGenderLabel.setForeground(Color.red);
			validation = false;}
		} 
		catch(NullPointerException e)
		{
			mUI.mGenderLabel.setForeground(Color.red);
		}
		return validation;
	}

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

	private void performDelete() {
		Contact contact = mUI.mContactListView.getSelectedValue();
		if (contact == null)
		{
			System.out.println("here");
			mUI.showStatus("              SELECT A CONTACT TO DELETE", true);
			//mUI.mStatusLabel.setForeground(Color.magenta);
			return;
		}
		/* remove person from the ContactList */
		mContactList.removeElement(contact);
		/* remove person from the file */
		mFileHelper.deleteContact(contact);
	}

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
				zipcode);
		/* Get the phoneNumber from the TextField */
		String phoneNumber = mUI.mPhoneNumberTextField.getText();
		
		/* Get the Gender from the RadioButton selected */
		Boolean isMale = false;
		String radioCommand = mUI.mGenderButtonGroup.getSelection()
				.getActionCommand();
		if (SET_MALE_COMMAND.equals(radioCommand)) {
			isMale = true;
		}
		
		/* Creates and Contact Class object and class the constructor with required parameters being passed */ 
		Contact contact = new Contact(contactName, contactAddress, phoneNumber,isMale);
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

	/** Override Method to get ListSelectionEvent and to perform appropriate function **/ 
	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		System.out.println("valueChanged");
		Contact contact = mUI.mContactListView.getSelectedValue();
		if (contact != null) 
		mUI.showContact(contact);
	}

	
	/** Method to show Contacts In JList **/
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
		
	
	
	/** Method to Sort the JList content based on FirstName if not then LastName if not then MiddleInitial **/
  
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
	   /** After Sorting Clears the List **/
	   mContactList.clear();
	   
	   /** Adds Sorted List Content Into the JList **/
	   mContactList.clear();
		for (Contact contact : contacts) {
			mContactList.addElement(contact);
		}
	}
}
   
   
   
