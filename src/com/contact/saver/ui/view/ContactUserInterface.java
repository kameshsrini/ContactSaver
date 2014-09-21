package com.contact.saver.ui.view;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.contact.saver.ui.UIServices;
import com.contact.saver.ui.controller.ContactController;
import com.contact.saver.ui.model.Contact;

public final class ContactUserInterface 
{
	private JFrame mContactUserInterfaceFrame;

	// Text Field Labels - User display
	public JLabel mFirstNameLabel;
	public JLabel mLastNameLabel;
	public JLabel mMiddleInitialLabel;
	public JLabel mAddressLine1Label;
	public JLabel mAddressLine2Label;
	public JLabel mCityLabel;
	public JLabel mStateLabel;
	public JLabel mZipCodeLabel;
	public JLabel mPhoneNumberLabel;
	public JLabel mGenderLabel;
	public JLabel mListViewFirstNameLabel;
	public JLabel mListViewLastNameLabel;
	public JLabel mListViewPhoneNumberLabel;
	public JLabel mStatusLabel;

	// Text Fields - User Text Input
	public JTextField mFirstNameTextField;
	public JTextField mLastNameTextField;
	public JTextField mMiddleInitialTextField;
	public JTextField mAddressLine1TextField;
	public JTextField mAddressLine2TextField;
	public JTextField mCityTextField;
	public JTextField mStateTextField;
	public JTextField mZipCodeTextField;
	public JTextField mPhoneNumberTextField;

	// Radio Buttons - User Select Input
	public JRadioButton mMaleRadioButton;
	public JRadioButton mFemaleRadioButton;
	public ButtonGroup mGenderButtonGroup;

	// Buttons - User Click Input
	public JButton mAddButton;
	public JButton mDeleteButton;
	public JButton mModifyButton;
	public JButton mFormResetButton;

	// ScrollPane and ListView - User Display and Selection
	public JList<Contact> mContactListView;
	public JScrollPane mScrollPane;

	
	// Creating Object of ContactController class to use its member variables in other words to provide UserControl with the UserInterface 
	ContactController mContactController;
	
	
	// Parameterized Constructor with ContactController Class object parameter and Intializing various User Interface Elements
	public ContactUserInterface(ContactController controller) 
	{
		mContactController = controller;

		intializeFrame();
		initializeLabels();
		initializeTextFields();
		initializeRadioButtons();
		initializeButtons();
		initializeScrollList();

		formReset();
		mContactUserInterfaceFrame.setVisible(true);
	}

	/** Initializing ScrollList which is nothing but a JList  inside a ScrollPane **/
	private void initializeScrollList() {
		mScrollPane = new JScrollPane();
		mScrollPane.setBounds(84, 302, 428, 70);
		mContactUserInterfaceFrame.getContentPane().add(mScrollPane);
		
				mContactListView = new JList<Contact>();
				mScrollPane.setViewportView(mContactListView);
				mListViewFirstNameLabel.setLabelFor(mContactListView);
				
						mContactListView.addListSelectionListener(mContactController);
						mContactListView.setVisibleRowCount(10);
						mContactListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						mContactListView.setCellRenderer(new MultiCellRenderer());
						mContactListView.setModel(mContactController.getAllContacts());

	}

	/** Initializing ADD, DELETE, MODIFY, FORMRESET buttons **/
	private void initializeButtons() {
		mAddButton = new JButton("ADD");
		mAddButton.setActionCommand(ContactController.ADD_COMMAND);
		mAddButton.addActionListener(mContactController);
		mAddButton.setBounds(255, 373, 89, 37);
		mContactUserInterfaceFrame.getContentPane().add(mAddButton);

		mDeleteButton = new JButton("DELETE");
		mDeleteButton.setForeground(Color.RED);
		mDeleteButton.setActionCommand(ContactController.DELETE_COMMAND);
		mDeleteButton.addActionListener(mContactController);
		mDeleteButton.setBounds(423, 373, 89, 37);
		mContactUserInterfaceFrame.getContentPane().add(mDeleteButton);

		mFormResetButton = new JButton("FORM RESET");
		mFormResetButton.setForeground(Color.BLACK);
		mFormResetButton.setActionCommand(ContactController.RESET_COMMAND);
		mFormResetButton.addActionListener(mContactController);
		mFormResetButton.setBounds(402, 211, 108, 37);
		mContactUserInterfaceFrame.getContentPane().add(mFormResetButton);

		mModifyButton = new JButton("MODIFY");
		mModifyButton.setActionCommand(ContactController.MODIFY_COMMAND);
		mModifyButton.addActionListener(mContactController);
		mModifyButton.setBounds(84, 373, 89, 37);
		mContactUserInterfaceFrame.getContentPane().add(mModifyButton);
	}
	
	
	/** Initializing Radio buttons **/
	private void initializeRadioButtons() {
		mMaleRadioButton = new JRadioButton("M");
		mMaleRadioButton.setActionCommand(ContactController.SET_MALE_COMMAND);
		mMaleRadioButton.setBounds(202, 232, 39, 14);
		mContactUserInterfaceFrame.getContentPane().add(mMaleRadioButton);

		mFemaleRadioButton = new JRadioButton("F");
		mFemaleRadioButton
				.setActionCommand(ContactController.SET_FEMALE_COMMAND);
		mFemaleRadioButton.setBounds(255, 232, 33, 14);
		mContactUserInterfaceFrame.getContentPane().add(mFemaleRadioButton);

		mGenderButtonGroup = new ButtonGroup();
		mGenderButtonGroup.add(mMaleRadioButton);
		mGenderButtonGroup.add(mFemaleRadioButton);
	}
	
	
	/** Initializing TextFields **/
	private void initializeTextFields() {

		mFirstNameTextField = new JTextField();
		mFirstNameTextField.setBounds(202, 33, 153, 20);
		mContactUserInterfaceFrame.getContentPane().add(mFirstNameTextField);
		mFirstNameTextField.setColumns(10);
		mFirstNameTextField.setDocument(new UIServices(20));

		mLastNameTextField = new JTextField();
		mLastNameTextField.setBounds(202, 58, 153, 20);
		mContactUserInterfaceFrame.getContentPane().add(mLastNameTextField);
		mLastNameTextField.setColumns(10);
		mLastNameTextField.setDocument(new UIServices(20));

		mMiddleInitialTextField = new JTextField();
		mMiddleInitialTextField.setBounds(482, 33, 28, 20);
		mContactUserInterfaceFrame.getContentPane()
				.add(mMiddleInitialTextField);
		mMiddleInitialTextField.setColumns(10);
		mMiddleInitialTextField.setDocument(new UIServices(1));

		mAddressLine1TextField = new JTextField();
		mAddressLine1TextField.setBounds(202, 83, 310, 20);
		mContactUserInterfaceFrame.getContentPane().add(mAddressLine1TextField);
		mAddressLine1TextField.setColumns(10);
		mAddressLine1TextField.setDocument(new UIServices(35));

		mAddressLine2TextField = new JTextField();
		mAddressLine2TextField.setBounds(202, 108, 310, 20);
		mContactUserInterfaceFrame.getContentPane().add(mAddressLine2TextField);
		mAddressLine2TextField.setColumns(10);
		mAddressLine2TextField.setDocument(new UIServices(35));

		mCityTextField = new JTextField();
		mCityTextField.setBounds(202, 133, 86, 20);
		mContactUserInterfaceFrame.getContentPane().add(mCityTextField);
		mCityTextField.setColumns(10);
		mCityTextField.setDocument(new UIServices(25));

		mStateTextField = new JTextField();
		mStateTextField.setBounds(202, 158, 39, 20);
		mContactUserInterfaceFrame.getContentPane().add(mStateTextField);
		mStateTextField.setColumns(10);
		mStateTextField.setDocument(new UIServices(2));

		mZipCodeTextField = new JTextField();
		mZipCodeTextField.setDocument(new UIServices(9));
		
		// Key Adapter to consume only digits  in ZipCode 
		mZipCodeTextField.addKeyListener(new KeyAdapter() 
		{	
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))
					e.consume();
			}
		});
		mZipCodeTextField.setBounds(202, 183, 86, 20);
		mContactUserInterfaceFrame.getContentPane().add(mZipCodeTextField);
		mZipCodeTextField.setColumns(10);

		mPhoneNumberTextField = new JTextField();
		mPhoneNumberTextField.setDocument(new UIServices(10));
		
		// Key Adapter to consume only digits  in PhoneNumber
		mPhoneNumberTextField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))
					e.consume();
			}
		});
		mPhoneNumberTextField.setBounds(202, 208, 153, 20);
		mContactUserInterfaceFrame.getContentPane().add(mPhoneNumberTextField);
		mPhoneNumberTextField.setColumns(10);
	}
	
	
	/** Initializing Labels **/
	                          
	private void initializeLabels() {
		mFirstNameLabel = new JLabel("FIRST NAME*");
		mFirstNameLabel.setBounds(84, 36, 108, 14);
		mContactUserInterfaceFrame.getContentPane().add(mFirstNameLabel);

		mLastNameLabel = new JLabel("LAST NAME*");
		mLastNameLabel.setBounds(84, 61, 108, 14);
		mContactUserInterfaceFrame.getContentPane().add(mLastNameLabel);

		mMiddleInitialLabel = new JLabel("MIDDLE INITIAL");
		mMiddleInitialLabel.setBounds(379, 36, 93, 14);
		mContactUserInterfaceFrame.getContentPane().add(mMiddleInitialLabel);

		mAddressLine1Label = new JLabel("ADDRESS LINE 1*");
		mAddressLine1Label.setBounds(84, 86, 108, 14);
		mContactUserInterfaceFrame.getContentPane().add(mAddressLine1Label);

		mAddressLine2Label = new JLabel("ADDRESS LINE 2");
		mAddressLine2Label.setForeground(Color.BLACK);
		mAddressLine2Label.setBounds(84, 111, 108, 14);
		mContactUserInterfaceFrame.getContentPane().add(mAddressLine2Label);

		mCityLabel = new JLabel("CITY*");
		mCityLabel.setBounds(84, 136, 46, 14);
		mContactUserInterfaceFrame.getContentPane().add(mCityLabel);

		mStateLabel = new JLabel("STATE*");
		mStateLabel.setBounds(84, 161, 69, 14);
		mContactUserInterfaceFrame.getContentPane().add(mStateLabel);

		mZipCodeLabel = new JLabel("ZIP CODE*");
		mZipCodeLabel.setBounds(84, 186, 69, 14);
		mContactUserInterfaceFrame.getContentPane().add(mZipCodeLabel);

		mPhoneNumberLabel = new JLabel("CONTACT NUMBER*");
		mPhoneNumberLabel.setBounds(84, 211, 118, 14);
		mContactUserInterfaceFrame.getContentPane().add(mPhoneNumberLabel);

		mGenderLabel = new JLabel("GENDER*");
		mGenderLabel.setBounds(84, 235, 69, 14);
		mContactUserInterfaceFrame.getContentPane().add(mGenderLabel);

		mListViewFirstNameLabel = new JLabel("First Name");
		mListViewFirstNameLabel.setForeground(Color.BLACK);
		mListViewFirstNameLabel.setBounds(84, 286, 103, 14);
		mContactUserInterfaceFrame.getContentPane()
				.add(mListViewFirstNameLabel);

		mListViewLastNameLabel = new JLabel("Last Name");
		mListViewLastNameLabel.setForeground(Color.BLACK);
		mListViewLastNameLabel.setBounds(227, 286, 61, 14);
		mContactUserInterfaceFrame.getContentPane().add(mListViewLastNameLabel);

		mListViewPhoneNumberLabel = new JLabel("Contact Number");
		mListViewPhoneNumberLabel.setForeground(Color.BLACK);
		mListViewPhoneNumberLabel.setBounds(360, 286, 103, 14);
		mContactUserInterfaceFrame.getContentPane().add(mListViewPhoneNumberLabel);

		mStatusLabel = new JLabel("");
		mStatusLabel.setBounds(156, 11, 354, 14);
		mContactUserInterfaceFrame.getContentPane().add(mStatusLabel);
	}
	
	/** Initializing JFrame **/
	
	private void intializeFrame() {
		mContactUserInterfaceFrame = new JFrame();
		mContactUserInterfaceFrame.setResizable(false);
		mContactUserInterfaceFrame.setTitle("ContactSaver");
		mContactUserInterfaceFrame.getContentPane().setBackground(
				Color.LIGHT_GRAY);
		mContactUserInterfaceFrame.setBounds(100, 100, 645, 453);
		mContactUserInterfaceFrame
				.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mContactUserInterfaceFrame.getContentPane().setLayout(null);
	}
	
	/** Method to reset the ContactForm and Label Colors **/
	
	public void formReset() {
		mFirstNameTextField.setText("");
		mLastNameTextField.setText("");
		mMiddleInitialTextField.setText("");
		mAddressLine1TextField.setText("");
		mAddressLine2TextField.setText("");
		mCityTextField.setText("");
		mStateTextField.setText("");
		mZipCodeTextField.setText("");
		mPhoneNumberTextField.setText("");
		mGenderButtonGroup.clearSelection();
		mContactListView.clearSelection();
		colorReset();
	}
	
	/** Method to show  the persons Contact Information **/
	public void showContact(Contact contact) {
		mFirstNameTextField.setText(contact.mContactName.mFirstName);
		mLastNameTextField.setText(contact.mContactName.mLastName);
		mMiddleInitialTextField.setText(contact.mContactName.mMiddleInitial
				.toString().trim());
		mAddressLine1TextField.setText(contact.mContactAddress.mAddressLine1);
		mAddressLine2TextField.setText(contact.mContactAddress.mAddressLine2);
		mCityTextField.setText(contact.mContactAddress.mCity);
		mStateTextField.setText(contact.mContactAddress.mState);
		mZipCodeTextField.setText(contact.mContactAddress.mZipCode.toString());
		mPhoneNumberTextField.setText(contact.mPhoneNumber);
		if (contact.mIsMale) 
		{
			mMaleRadioButton.setSelected(true);
			mFemaleRadioButton.setSelected(false);
		} 
		else 
		{
			mMaleRadioButton.setSelected(false);
			mFemaleRadioButton.setSelected(true);
		}
	}

	
	/** Method to show the Status Text Message **/
	public void showStatus(String status, boolean error) {
		if(error != true)
		{
		mStatusLabel.setForeground(Color.MAGENTA);
		mStatusLabel.setText (status);
		}
		else
		{
		mStatusLabel.setText(status);
		mStatusLabel.setForeground(Color.RED);
		}
	}
	
	/** Method to reset Label Color **/
	public void colorReset() {
		mFirstNameLabel.setForeground(Color.BLACK);
		mLastNameLabel.setForeground(Color.BLACK);
		mMiddleInitialLabel.setForeground(Color.BLACK);
		mAddressLine1Label.setForeground(Color.BLACK);
		mCityLabel.setForeground(Color.BLACK);
		mStateLabel.setForeground(Color.BLACK);
		mZipCodeLabel.setForeground(Color.BLACK);
		mPhoneNumberLabel.setForeground(Color.BLACK);
		mGenderLabel.setForeground(Color.BLACK);
		mStatusLabel.setText(null);
		showStatus("            Add, Delete or Modify a contact!!", false);
		mStatusLabel.setForeground(Color.magenta);
		
	}
}