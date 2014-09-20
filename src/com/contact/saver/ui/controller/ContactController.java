package com.contact.saver.ui.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.eclipse.swt.internal.win32.MCHITTESTINFO;

import com.contact.saver.ui.FileHelper;
import com.contact.saver.ui.model.Contact;
import com.contact.saver.ui.model.ContactAddress;
import com.contact.saver.ui.model.ContactName;
import com.contact.saver.ui.view.ContactUserInterface;

public final class ContactController implements ActionListener,
		ListSelectionListener {
	public static final String ADD_COMMAND = "add";
	public static final String DELETE_COMMAND = "delete";
	public static final String RESET_COMMAND = "reset";
	public static final String MODIFY_COMMAND = "modify";
	public static final String SET_MALE_COMMAND = "male";
	public static final String SET_FEMALE_COMMAND = "female";

	FileHelper mFileHelper = new FileHelper();
	DefaultListModel<Contact> mContactList = new DefaultListModel<Contact>();

	ContactUserInterface mUI;

	public void setContactUserInterface(
			ContactUserInterface contactUserInterface) {
		mUI = contactUserInterface;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		mUI.colorReset();
		if (ADD_COMMAND.equals(command)) {
			
			if (validateInputFields() && !isContactAlreadyThere()) {
				// check if the FN, LN, MIDDLE is already there.
				performAdd();
			}
		} else if (DELETE_COMMAND.equals(command)) {
			performDelete();
			mUI.formReset();
		} else if (RESET_COMMAND.equals(command)) {
			mUI.formReset();
		} else if (MODIFY_COMMAND.equals(command)) {
			if (validateInputFields() && !isContactAlreadyThere()) {
				performDelete();
				performAdd();	
			}
		}
	}

	private boolean validateInputFields() {
		boolean validation = true;
		String firstNameText = mUI.mFirstNameTextField.getText().trim();
		if (firstNameText.length() < 1 || firstNameText.contains("|")) {
			
			 mUI.showStatus("Invalid input! Enter fields marked red", true);
			 mUI.mFirstNameLabel.setForeground(Color.red);
			validation = false;
		}
		String lastNameText = mUI.mLastNameTextField.getText().trim();
		if (lastNameText.length() < 1 || firstNameText.contains("|")) {
			 mUI.showStatus("Invalid input! Enter fields marked red", true);
			 mUI.mLastNameLabel.setForeground(Color.red);
			validation = false;
		}
		String addressLine1Text = mUI.mAddressLine1TextField.getText().trim();
		if (addressLine1Text.length() < 1 || firstNameText.contains("|")) {
			 mUI.showStatus("Invalid input! Enter fields marked red", true);
			 mUI.mAddressLine1Label.setForeground(Color.red);
			validation = false;
		}
		String cityText = mUI.mCityTextField.getText().trim();
		if (cityText.length() < 1 || firstNameText.contains("|")) {
			 mUI.showStatus("Invalid input! Enter fields marked red", true);
			 mUI.mCityLabel.setForeground(Color.red);
			validation = false;
		}
		String stateText = mUI.mStateTextField.getText().trim();
		if (stateText.length() < 1 || firstNameText.contains("|")) {
			 mUI.showStatus("Invalid input! Enter fields marked red", true);
			 mUI.mStateLabel.setForeground(Color.red);
			validation = false;
		}
		String zipCodeText = mUI.mZipCodeTextField.getText().trim();
		if (zipCodeText.length() < 1 || firstNameText.contains("|")) {
			 mUI.showStatus("Invalid input! Enter fields marked red", true);
			 mUI.mZipCodeLabel.setForeground(Color.red);
			validation = false;
		}
		String phonenumberText = mUI.mPhoneNumberTextField.getText().trim();
		if (phonenumberText.length() < 1 || firstNameText.contains("|")) {
			// mUI.showStatus(Invalid input! Enter fields marked red, isError)
			 mUI.showStatus("Invalid input! Enter fields marked red", true);
			 mUI.mPhoneNumberLabel.setForeground(Color.red);
			// mUI.mFirstNameTextLabel set color to red
			validation = false;
		}
		try
		{
		if (!mUI.mGenderButtonGroup.getSelection().isEnabled()) {
			mUI.showStatus("Invalid input! Enter fields marked red", true);
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
		
		//Integer zipcode = Integer.parseInt(mUI.mZipCodeTextField.getText());

		for (int i = 0; i < mContactList.getSize(); i++) {
			Contact contact = mContactList.get(i);
			if (contact.mContactName.toString().equals(contactName.toString())) {
				// should not add this contact
				mUI.showStatus("This Person Already Exist!", false);
				mUI.mStatusLabel.setForeground(Color.magenta);
				// mUI.showStatus(This person already exist! Modify instead of
				// adding, isError)
				// mUI.mFirstNameTextLabel set color to red
				return true;
			}
		}
		return false;
	}

	private void performDelete() {
		Contact contact = mUI.mContactListView.getSelectedValue();
		if (contact == null) {
			// mUI.showStatus("select contact to delete", notError)
			return;
		}
		System.out.println("Delete not null contact");
		mContactList.removeElement(contact);
		mFileHelper.deleteContact(contact);
	}

	private void performAdd() {
		String middleInitialText = mUI.mMiddleInitialTextField.getText();
		Character middleInitial = '\u0000';
		if (middleInitialText.length() > 0) {
			middleInitial = middleInitialText.charAt(0);
		}
		ContactName contactName = new ContactName(
				mUI.mFirstNameTextField.getText(),
				mUI.mLastNameTextField.getText(), middleInitial);
		Integer zipcode = Integer.parseInt(mUI.mZipCodeTextField.getText());

		ContactAddress contactAddress = new ContactAddress(
				mUI.mAddressLine1TextField.getText(),
				mUI.mAddressLine2TextField.getText(),
				mUI.mCityTextField.getText(), mUI.mStateTextField.getText(),
				zipcode);

		String phoneNumber = mUI.mPhoneNumberTextField.getText();
		Boolean isMale = false;
		String radioCommand = mUI.mGenderButtonGroup.getSelection()
				.getActionCommand();
		if (SET_MALE_COMMAND.equals(radioCommand)) {
			isMale = true;
		}
		Contact contact = new Contact(contactName, contactAddress, phoneNumber,
				isMale);
		mContactList.addElement(contact);
		sort();
		mFileHelper.addContact(contact);

		/* Reset as we have added this contact. */
		mUI.formReset();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		System.out.println("valueChanged");
		Contact contact = mUI.mContactListView.getSelectedValue();
		if (contact != null) {
			mUI.showContact(contact);
		}
	}

	public ListModel<Contact> getAllContacts() {
		List<Contact> contacts = mFileHelper.getAllContacts();
		mContactList.clear();
		for (Contact contact : contacts) {
			mContactList.addElement(contact);
		}
		sort();
		return mContactList;
	}
	
   void sort() {
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
	   mContactList.clear();
		for (Contact contact : contacts) {
			mContactList.addElement(contact);
		}
	}
}
