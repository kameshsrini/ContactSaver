package com.contact.saver.ui.view;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public final class ContactUserInterface 
{
	private JFrame mContactUserInterfaceFrame;

	/* Text Field Labels - User display */
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
	public JLabel mStatusLabel;

	/* Text Fields - User Text Input */
	public JTextField mFirstNameTextField;
	public JTextField mLastNameTextField;
	public JTextField mMiddleInitialTextField;
	public JTextField mAddressLine1TextField;
	public JTextField mAddressLine2TextField;
	public JTextField mCityTextField;
	public JTextField mStateTextField;
	public JTextField mZipCodeTextField;
	public JTextField mPhoneNumberTextField;
	public JTextField mNameHeading;
	public JTextField mAddressHeading;
	public JTextField mPhoneNumberHeading;
	public JTextField mSexHeading;
	public JTextField mContactListHeading;

	/* Radio Buttons - User Select Input */
	public JRadioButton mMaleRadioButton;
	public JRadioButton mFemaleRadioButton;
	public ButtonGroup mGenderButtonGroup;

	/* Buttons - User Click Input */
	public JButton mAddButton;
	public JButton mDeleteButton;
	public JButton mModifyButton;
	public JButton mFormResetButton;

	/**
	 *  ScrollPane and ListView - User Display and Selection
	 */
	public JList<Contact> mContactListView;
	public JScrollPane mScrollPane;

	
	/**
	 *  Creating Object of ContactController class to use its member variables in other words to provide UserControl with the UserInterface 
	 */
	ContactController mContactController;
	
	/**
	 *  Parameterized Constructor with ContactController Class object parameter and Intializing various User Interface Elements
	 * @param controller
	 */
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

	/**
	 *  Initializing ScrollList which is nothing but a JList  inside a ScrollPane 
	 */
	private void initializeScrollList() {
		mScrollPane = new JScrollPane();
		mScrollPane.setBounds(5, 36, 362, 405);
		mContactUserInterfaceFrame.getContentPane().add(mScrollPane);
		
		mContactListView = new JList<Contact>();
		mScrollPane.setViewportView(mContactListView);
		
		mContactListView.addListSelectionListener(mContactController);
		mContactListView.setVisibleRowCount(10);
		mContactListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mContactListView.setCellRenderer(new MultiCellRenderer());
		mContactListView.setModel(mContactController.getAllContacts());
		
		

	}

	/** 
	 * Initializing ADD, DELETE, MODIFY, FORMRESET buttons 
	 */
	private void initializeButtons() {
		mAddButton = new JButton("ADD");
		mAddButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mAddButton.setIcon(new ImageIcon(ContactUserInterface.class.getResource("add.png")));
		mAddButton.setBounds(543, 379, 100, 37);
		mAddButton.setActionCommand(ContactController.ADD_COMMAND);
		mAddButton.addActionListener(mContactController);
		mContactUserInterfaceFrame.getContentPane().add(mAddButton);

		mDeleteButton = new JButton("DELETE");
		mDeleteButton.setIcon(new ImageIcon(ContactUserInterface.class.getResource("delete.png")));
		mDeleteButton.setBounds(688, 379, 100, 37);
		mDeleteButton.setForeground(Color.RED);
		mDeleteButton.setActionCommand(ContactController.DELETE_COMMAND);
		mDeleteButton.addActionListener(mContactController);
		mContactUserInterfaceFrame.getContentPane().add(mDeleteButton);

		mFormResetButton = new JButton("RESET");
		mFormResetButton.setIcon(new ImageIcon(ContactUserInterface.class.getResource("refresh.png")));
		mFormResetButton.setBounds(688, 331, 100, 37);
		mFormResetButton.setForeground(Color.BLACK);
		mFormResetButton.setActionCommand(ContactController.RESET_COMMAND);
		mFormResetButton.addActionListener(mContactController);
		mContactUserInterfaceFrame.getContentPane().add(mFormResetButton);

		mModifyButton = new JButton("MODIFY");
		mModifyButton.setIcon(new ImageIcon(ContactUserInterface.class.getResource("modify.png")));
		mModifyButton.setBounds(391, 380, 100, 37);
		mModifyButton.setActionCommand(ContactController.MODIFY_COMMAND);
		mModifyButton.addActionListener(mContactController);
		mContactUserInterfaceFrame.getContentPane().add(mModifyButton);
	}
	
	
	/**
	 *  Initializing Radio buttons 
	 */
	private void initializeRadioButtons() {
		mMaleRadioButton = new JRadioButton("M");
		mMaleRadioButton.setBounds(501, 330, 39, 14);
		mMaleRadioButton.setActionCommand(ContactController.SET_MALE_COMMAND);
		mContactUserInterfaceFrame.getContentPane().add(mMaleRadioButton);

		mFemaleRadioButton = new JRadioButton("F");
		mFemaleRadioButton.setBounds(558, 330, 39, 14);
		mFemaleRadioButton.setActionCommand(ContactController.SET_FEMALE_COMMAND);
		mContactUserInterfaceFrame.getContentPane().add(mFemaleRadioButton);
		mGenderButtonGroup = new ButtonGroup();
		mGenderButtonGroup.add(mMaleRadioButton);
		mGenderButtonGroup.add(mFemaleRadioButton);
	}
	
	
	/**
	 *  Initializing TextFields 
	 */
	private void initializeTextFields() {

		mFirstNameTextField = new JTextField();
		mFirstNameTextField.setBounds(502, 39, 153, 20);
		mContactUserInterfaceFrame.getContentPane().add(mFirstNameTextField);
		mFirstNameTextField.setColumns(10);
		mFirstNameTextField.setDocument(new UIServices(20));

		mLastNameTextField = new JTextField();
		mLastNameTextField.setBounds(502, 60, 153, 20);
		mContactUserInterfaceFrame.getContentPane().add(mLastNameTextField);
		mLastNameTextField.setColumns(10);
		mLastNameTextField.setDocument(new UIServices(20));

		mMiddleInitialTextField = new JTextField();
		mMiddleInitialTextField.setBounds(747, 60, 28, 20);
		mContactUserInterfaceFrame.getContentPane().add(mMiddleInitialTextField);
		mMiddleInitialTextField.setColumns(10);
		mMiddleInitialTextField.setDocument(new UIServices(1));

		mAddressLine1TextField = new JTextField();
		mAddressLine1TextField.setBounds(499, 118, 289, 20);
		mContactUserInterfaceFrame.getContentPane().add(mAddressLine1TextField);
		mAddressLine1TextField.setColumns(10);
		mAddressLine1TextField.setDocument(new UIServices(35));

		mAddressLine2TextField = new JTextField();
		mAddressLine2TextField.setBounds(499, 143, 289, 20);
		mContactUserInterfaceFrame.getContentPane().add(mAddressLine2TextField);
		mAddressLine2TextField.setColumns(10);
		mAddressLine2TextField.setDocument(new UIServices(35));

		mCityTextField = new JTextField();
		mCityTextField.setBounds(499, 168, 86, 20);
		mContactUserInterfaceFrame.getContentPane().add(mCityTextField);
		mCityTextField.setColumns(10);
		mCityTextField.setDocument(new UIServices(25));

		mStateTextField = new JTextField();
		mStateTextField.setBounds(501, 193, 39, 20);
		mContactUserInterfaceFrame.getContentPane().add(mStateTextField);
		mStateTextField.setColumns(10);
		mStateTextField.setDocument(new UIServices(2));

		mZipCodeTextField = new JTextField();
		mZipCodeTextField.setBounds(501, 218, 86, 20);
		mZipCodeTextField.setDocument(new UIServices(9));
		
		/* Key Adapter to consume only digits  in ZipCode */ 
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
		mContactUserInterfaceFrame.getContentPane().add(mZipCodeTextField);
		mZipCodeTextField.setColumns(10);

		mPhoneNumberTextField = new JTextField();
		mPhoneNumberTextField.setBounds(502, 273, 153, 20);
		mPhoneNumberTextField.setDocument(new UIServices(10));
		
		/* Key Adapter to consume only digits  in PhoneNumber */
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
		mContactUserInterfaceFrame.getContentPane().add(mPhoneNumberTextField);
		mPhoneNumberTextField.setColumns(10);
		
		mNameHeading = new JTextField();
		mNameHeading.setEnabled(false);
		mNameHeading.setForeground(new Color(0, 0, 0));
		mNameHeading.setText("NAME");
		mNameHeading.setToolTipText("");
		mNameHeading.setBounds(377, 21, 418, 14);
		mNameHeading.setEditable(false);
		mNameHeading.setBackground(new Color(245, 245, 220));
		mNameHeading.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		mContactUserInterfaceFrame.getContentPane().add(mNameHeading);
		mNameHeading.setColumns(10);
		
		mAddressHeading = new JTextField();
		mAddressHeading.setEnabled(false);
		mAddressHeading.setBounds(377, 99, 418, 14);
		mAddressHeading.setText("ADDRESS");
		mAddressHeading.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		mAddressHeading.setEditable(false);
		mAddressHeading.setColumns(10);
		mAddressHeading.setBackground(new Color(245, 245, 220));
		mContactUserInterfaceFrame.getContentPane().add(mAddressHeading);
		
		mPhoneNumberHeading = new JTextField();
		mPhoneNumberHeading.setEnabled(false);
		mPhoneNumberHeading.setBounds(377, 251, 418, 14);
		mPhoneNumberHeading.setText("PHONE NUMBER");
		mPhoneNumberHeading.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		mPhoneNumberHeading.setEditable(false);
		mPhoneNumberHeading.setColumns(10);
		mPhoneNumberHeading.setBackground(new Color(245, 245, 220));
		mContactUserInterfaceFrame.getContentPane().add(mPhoneNumberHeading);
		
		mSexHeading = new JTextField();
		mSexHeading.setEnabled(false);
		mSexHeading.setBounds(376, 309, 419, 14);
		mSexHeading.setText("SEX");
		mSexHeading.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		mSexHeading.setEditable(false);
		mSexHeading.setColumns(10);
		mSexHeading.setBackground(new Color(245, 245, 220));
		mContactUserInterfaceFrame.getContentPane().add(mSexHeading);
		
		mContactListHeading = new JTextField();
		mContactListHeading.setBounds(5, 20, 362, 14);
		mContactListHeading.setText("FIRST NAME                  LAST NAME                  CONTACT NUMBER");
		mContactListHeading.setFont(new Font("Tahoma", Font.BOLD, 11));
		mContactListHeading.setEditable(false);
		mContactListHeading.setColumns(10);
		mContactListHeading.setBackground(new Color(245, 245, 220));
		mContactUserInterfaceFrame.getContentPane().add(mContactListHeading);
	}
	
	/**
	 *  Initializing Labels 
	 */
	                          
	private void initializeLabels() {
		mContactUserInterfaceFrame.getContentPane().setLayout(null);
		mFirstNameLabel = new JLabel("FIRST NAME*");
		mFirstNameLabel.setBounds(384, 42, 108, 14);
		mContactUserInterfaceFrame.getContentPane().add(mFirstNameLabel);

		mLastNameLabel = new JLabel("LAST NAME*");
		mLastNameLabel.setBounds(384, 63, 108, 14);
		mContactUserInterfaceFrame.getContentPane().add(mLastNameLabel);

		mMiddleInitialLabel = new JLabel("MIDDLE INITIAL");
		mMiddleInitialLabel.setBounds(659, 63, 93, 14);
		mContactUserInterfaceFrame.getContentPane().add(mMiddleInitialLabel);

		mAddressLine1Label = new JLabel("ADDRESS LINE 1*");
		mAddressLine1Label.setBounds(383, 121, 108, 14);
		mContactUserInterfaceFrame.getContentPane().add(mAddressLine1Label);

		mAddressLine2Label = new JLabel("ADDRESS LINE 2");
		mAddressLine2Label.setBounds(383, 146, 108, 14);
		mAddressLine2Label.setForeground(Color.BLACK);
		mContactUserInterfaceFrame.getContentPane().add(mAddressLine2Label);

		mCityLabel = new JLabel("CITY*");
		mCityLabel.setBounds(383, 171, 46, 14);
		mContactUserInterfaceFrame.getContentPane().add(mCityLabel);

		mStateLabel = new JLabel("STATE*");
		mStateLabel.setBounds(383, 196, 69, 14);
		mContactUserInterfaceFrame.getContentPane().add(mStateLabel);

		mZipCodeLabel = new JLabel("ZIP CODE*");
		mZipCodeLabel.setBounds(383, 221, 69, 14);
		mContactUserInterfaceFrame.getContentPane().add(mZipCodeLabel);

		mPhoneNumberLabel = new JLabel("CONTACT NUMBER*");
		mPhoneNumberLabel.setBounds(384, 276, 118, 14);
		mContactUserInterfaceFrame.getContentPane().add(mPhoneNumberLabel);

		mGenderLabel = new JLabel("GENDER*");
		mGenderLabel.setBounds(384, 330, 69, 14);
		mContactUserInterfaceFrame.getContentPane().add(mGenderLabel);

		mStatusLabel = new JLabel("        ADD, DELETE OR VIEW A CONTACT");
		mStatusLabel.setBounds(383, 427, 405, 14);
		mStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mStatusLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		mStatusLabel.setForeground(Color.MAGENTA);
		mContactUserInterfaceFrame.getContentPane().add(mStatusLabel);
	}
	
	/** 
	 * Initializing JFrame 
	 */
	
	private void intializeFrame() {
		mContactUserInterfaceFrame = new JFrame();
		mContactUserInterfaceFrame.setResizable(false);
		mContactUserInterfaceFrame.setTitle("ContactSaver");
		mContactUserInterfaceFrame.getContentPane().setBackground(
				Color.WHITE);
		mContactUserInterfaceFrame.setBounds(100, 100, 811, 483);
		mContactUserInterfaceFrame
				.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/** 
	 * Method to reset the ContactForm and Label Colors 
	 */
	
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
		mMaleRadioButton.setSelected(true);
		mContactListView.clearSelection();
		mModifyButton.setEnabled(false);
		mDeleteButton.setEnabled(false);
		mAddButton.setEnabled(true);
		showStatus("        ADD OR VIEW A CONTACT", false);
		colorReset();
	}
	
	/**
	 * Method to show  the persons Contact Information 
	 */
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

	
	/** 
	 * Method to show the Status Text Message  
	 */
		public void showStatus(String status, boolean error) {
			if(error != true)
			{
			mStatusLabel.setForeground(Color.MAGENTA);
			mStatusLabel.setText (status);
			}
			else
			{
			mStatusLabel.setForeground(Color.RED);
			mStatusLabel.setText(status);
			playSound("b1.wav");
			}
		}
	
	/** 
	 * Method to reset Label Color 
	 */

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
	}
	
	/** 
	 * Method to playSound
	 * @param url
	 */
	public static synchronized void playSound(final String url) {
		  new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		        ContactUserInterface.class.getResourceAsStream(url));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}
}