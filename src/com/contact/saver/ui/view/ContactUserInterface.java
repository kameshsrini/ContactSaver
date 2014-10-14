package com.contact.saver.ui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
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
import com.contact.saver.ui.UIServices1;
import com.contact.saver.ui.controller.ContactController;
import com.contact.saver.ui.model.Contact;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import java.awt.Label;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JProgressBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public final class ContactUserInterface {
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
	public JLabel mCountryLabel;
	public JLabel mPhoneNumberLabel;
	public JLabel mEmailAddressLabel;
	public JLabel mGenderLabel;
	public JLabel mStatusLabel;
	public JLabel mStatusDisplayLabel;

	/* Text Fields - User Text Input */
	public JTextField mFirstNameTextField;
	public JTextField mLastNameTextField;
	public JTextField mMiddleInitialTextField;
	public JTextField mAddressLine1TextField;
	public JTextField mAddressLine2TextField;
	public JTextField mCityTextField;
	public JTextField mStateTextField;
	public JTextField mZipCodeTextField;
	public JTextField mCountryTextField;
	public JTextField mPhoneNumberTextField;
	public JTextField mEmailAddressTextField;
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
	public JButton mSaveButton;
	public JButton mDeleteButton;
	public JButton mNewButton;
	public static JButton mSoundButton;

	/**
	 * ScrollPane and ListView - User Display and Selection
	 */
	public JList<Contact> mContactListView;
	public JScrollPane mScrollPane;

	public JProgressBar mProgressBar;
	/**
	 * Creating Object of ContactController class to use its member variables in
	 * other words to provide UserControl with the UserInterface
	 */
	ContactController mContactController;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// to get the screen dimensions
	static int x = 0;
	public int mode = 1;
	static boolean sound = true;
	private JPanel panel;

	/**
	 * Parameterized Constructor with ContactController Class object parameter
	 * and Intializing various User Interface Elements
	 * 
	 * @param controller
	 */
	public ContactUserInterface(ContactController controller) {
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
	 * Initializing ScrollList which is nothing but a JList inside a ScrollPane
	 */
	private void initializeScrollList() {
		panel.setLayout(null);
		panel.add(mScrollPane);

		mContactListView = new JList<Contact>();
		mScrollPane.setViewportView(mContactListView);

		mContactListView.addListSelectionListener(mContactController);
		mContactListView.setVisibleRowCount(10);
		mContactListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mContactListView.setCellRenderer(new MultiCellRenderer());
		mContactListView.setModel(mContactController.getAllContacts());
		

		mProgressBar = new JProgressBar();
		mProgressBar.setBounds(478, 6, 491, 14);
		mProgressBar.setMaximum(90);
		panel.add(mProgressBar);
		mProgressBar.setForeground(new Color(51, 153, 0));		
	}

	/**
	 * Initializing ADD, DELETE, MODIFY, FORMRESET buttons
	 */
	private void initializeButtons() {
		mNewButton = new JButton("New");
		mNewButton.setBounds(819, 402, 123, 68);
		panel.add(mNewButton);
		mNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		mNewButton.setIcon(new ImageIcon(ContactUserInterface.class
				.getResource("refresh.png")));
		mNewButton.setForeground(Color.BLACK);
		mNewButton.setActionCommand(ContactController.RESET_COMMAND);
		mSaveButton = new JButton("SAVE");
		mSaveButton.setBounds(504, 402, 118, 67);
		panel.add(mSaveButton);
		mSaveButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		mSaveButton.setIcon(new ImageIcon(ContactUserInterface.class
				.getResource("add.png")));
		mSaveButton.setActionCommand(ContactController.ADD_COMMAND);

		mDeleteButton = new JButton("DELETE");
		mDeleteButton.setBounds(657, 402, 118, 68);
		panel.add(mDeleteButton);
		mDeleteButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		mDeleteButton.setIcon(new ImageIcon(ContactUserInterface.class
				.getResource("delete.png")));
		mDeleteButton.setForeground(Color.RED);
		mDeleteButton.setActionCommand(ContactController.DELETE_COMMAND);

		mSoundButton = new JButton("");
		mSoundButton.setBounds(931, 516, 39, 33);
		panel.add(mSoundButton);
		mSoundButton.setBackground(Color.LIGHT_GRAY);
		mSoundButton.setIcon(new ImageIcon(ContactUserInterface.class
				.getResource("soundon.png")));

		
		mSoundButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound = !sound;
				if (sound)
					mSoundButton.setIcon(new ImageIcon(
							ContactUserInterface.class
									.getResource("soundon.png")));
				else
					mSoundButton.setIcon(new ImageIcon(
							ContactUserInterface.class
									.getResource("soundoff.png")));

			}
		});
		mDeleteButton.addActionListener(mContactController);
		mSaveButton.addActionListener(mContactController);
		mNewButton.addActionListener(mContactController);
	}

	/**
	 * Initializing Radio buttons
	 */
	private void initializeRadioButtons() {
		mGenderButtonGroup = new ButtonGroup();
		mMaleRadioButton = new JRadioButton("M");
		mMaleRadioButton.setBounds(575, 361, 39, 14);
		panel.add(mMaleRadioButton);
		mMaleRadioButton.setActionCommand(ContactController.SET_MALE_COMMAND);
		mGenderButtonGroup.add(mMaleRadioButton);

		mFemaleRadioButton = new JRadioButton("F");
		mFemaleRadioButton.setBounds(616, 361, 39, 14);
		panel.add(mFemaleRadioButton);
		mFemaleRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		mFemaleRadioButton
				.setActionCommand(ContactController.SET_FEMALE_COMMAND);
		mGenderButtonGroup.add(mFemaleRadioButton);
	}

	/**
	 * Initializing TextFields
	 */
	private void initializeTextFields() {
		
		mNameHeading = new JTextField();
		mNameHeading.setBounds(478, 22, 491, 14);
		panel.add(mNameHeading);
		mNameHeading.setEnabled(false);
		mNameHeading.setForeground(new Color(0, 0, 0));
		mNameHeading.setText("NAME");
		mNameHeading.setToolTipText("");
		mNameHeading.setEditable(false);
		mNameHeading.setBackground(new Color(245, 245, 220));
		mNameHeading.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		mNameHeading.setColumns(10);

		mAddressHeading = new JTextField();
		mAddressHeading.setBounds(478, 88, 491, 14);
		panel.add(mAddressHeading);
		mAddressHeading.setEnabled(false);
		mAddressHeading.setText("ADDRESS");
		mAddressHeading
				.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		mAddressHeading.setEditable(false);
		mAddressHeading.setColumns(10);
		mAddressHeading.setBackground(new Color(245, 245, 220));

		mPhoneNumberHeading = new JTextField();
		mPhoneNumberHeading.setBounds(478, 263, 491, 14);
		panel.add(mPhoneNumberHeading);
		mPhoneNumberHeading.setEnabled(false);
		mPhoneNumberHeading.setText("CONTACT INFO");
		mPhoneNumberHeading.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC,
				11));
		mPhoneNumberHeading.setEditable(false);
		mPhoneNumberHeading.setColumns(10);
		mPhoneNumberHeading.setBackground(new Color(245, 245, 220));

		mSexHeading = new JTextField();
		mSexHeading.setBounds(478, 336, 491, 14);
		panel.add(mSexHeading);
		mSexHeading.setEnabled(false);
		mSexHeading.setText("SEX");
		mSexHeading.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		mSexHeading.setEditable(false);
		mSexHeading.setColumns(10);
		mSexHeading.setBackground(new Color(245, 245, 220));

		mFirstNameTextField = new JTextField();
		mFirstNameTextField.setBounds(589, 38, 237, 20);
		panel.add(mFirstNameTextField);
		mFirstNameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				mContactController.progressbarStatus();
			}
		});
		mFirstNameTextField.setColumns(10);
		mFirstNameTextField.setDocument(new UIServices(20));

		mLastNameTextField = new JTextField();
		mLastNameTextField.setBounds(589, 63, 237, 20);
		panel.add(mLastNameTextField);
		mLastNameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				mContactController.progressbarStatus();
			}
		});
		mLastNameTextField.setColumns(10);
		mLastNameTextField.setDocument(new UIServices(20));
		
		mMiddleInitialLabel = new JLabel("MIDDLE INITIAL");
		mMiddleInitialLabel.setBounds(836, 63, 93, 14);
		panel.add(mMiddleInitialLabel);

		mMiddleInitialTextField = new JTextField();
		mMiddleInitialTextField.setBounds(931, 60, 28, 20);
		panel.add(mMiddleInitialTextField);
		mMiddleInitialTextField.setColumns(10);
		mMiddleInitialTextField.setDocument(new UIServices(1));

		mAddressLine1TextField = new JTextField();
		mAddressLine1TextField.setBounds(589, 113, 380, 20);
		panel.add(mAddressLine1TextField);
		mAddressLine1TextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				mContactController.progressbarStatus();
			}
		});
		mAddressLine1TextField.setColumns(10);
		mAddressLine1TextField.setDocument(new UIServices(35));

		mAddressLine2TextField = new JTextField();
		mAddressLine2TextField.setBounds(589, 138, 380, 20);
		panel.add(mAddressLine2TextField);
		mAddressLine2TextField.setColumns(10);
		mAddressLine2TextField.setDocument(new UIServices(35));

		mCityTextField = new JTextField();
		mCityTextField.setBounds(589, 163, 153, 20);
		panel.add(mCityTextField);
		mCityTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				mContactController.progressbarStatus();
			}
		});
		mCityTextField.setColumns(10);
		mCityTextField.setDocument(new UIServices(25));

		mStateTextField = new JTextField();
		mStateTextField.setBounds(589, 188, 56, 20);
		panel.add(mStateTextField);
		mStateTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				mContactController.progressbarStatus();
			}
		});
		mStateTextField.setColumns(10);
		mStateTextField.setDocument(new UIServices(2));

		mZipCodeTextField = new JTextField();
		mZipCodeTextField.setBounds(589, 213, 153, 20);
		panel.add(mZipCodeTextField);
		mZipCodeTextField.setDocument(new UIServices(9));
		mZipCodeTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				mContactController.progressbarStatus();
				char c = e.getKeyChar();
				if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))
					e.consume();
			}
		});
		mZipCodeTextField.setColumns(10);

		mCountryTextField = new JTextField();
		mCountryTextField.setBounds(589, 238, 153, 20);
		panel.add(mCountryTextField);
		mCountryTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				mContactController.progressbarStatus();
				char c = e.getKeyChar();
				if (Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))
					e.consume();

			}
		});
		mCountryTextField.setColumns(10);
		mCountryTextField.setDocument(new UIServices(30));

		mPhoneNumberTextField = new JTextField();
		mPhoneNumberTextField.setBounds(589, 282, 153, 20);
		panel.add(mPhoneNumberTextField);
		mPhoneNumberTextField.setDocument(new UIServices(10));
		mPhoneNumberTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				mContactController.progressbarStatus();
				char c = e.getKeyChar();
				if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))
					e.consume();
			}
		});
		mPhoneNumberTextField.setColumns(10);

		mEmailAddressTextField = new JTextField();
		mEmailAddressTextField.setBounds(589, 310, 176, 20);
		panel.add(mEmailAddressTextField);
		mEmailAddressTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				mContactController.progressbarStatus();
			}
		});
		mEmailAddressTextField.setColumns(10);
		mEmailAddressTextField.setDocument(new UIServices1(100));
	}

	/**
	 * Initializing Labels
	 */

	private void initializeLabels() {
		mContactUserInterfaceFrame.getContentPane().setLayout(null);
		mContactListHeading = new JTextField();
		mContactListHeading.setBounds(0, 6, 457, 14);
		panel.add(mContactListHeading);
		mContactListHeading
				.setText("FIRST NAME                             LAST NAME                              CONTACT NUMBER              ");
		mContactListHeading.setFont(new Font("Tahoma", Font.BOLD, 11));
		mContactListHeading.setEditable(false);
		mContactListHeading.setColumns(10);
		mContactListHeading.setBackground(new Color(245, 245, 220));
		mScrollPane = new JScrollPane();
		mScrollPane.setBounds(0, 20, 457, 529);
		
		mFirstNameLabel = new JLabel("FIRST NAME*");
		mFirstNameLabel.setBounds(478, 38, 108, 14);
		panel.add(mFirstNameLabel);
		mFirstNameLabel.setBackground(new Color(255, 255, 255));

		mLastNameLabel = new JLabel("LAST NAME*");
		mLastNameLabel.setBounds(478, 63, 108, 14);
		panel.add(mLastNameLabel);
		mLastNameLabel.setBackground(new Color(255, 255, 255));

		mAddressLine1Label = new JLabel("ADDRESS LINE 1*");
		mAddressLine1Label.setBounds(478, 113, 108, 14);
		panel.add(mAddressLine1Label);

		mAddressLine2Label = new JLabel("ADDRESS LINE 2");
		mAddressLine2Label.setBounds(478, 138, 108, 14);
		panel.add(mAddressLine2Label);
		mAddressLine2Label.setForeground(Color.BLACK);

		mCityLabel = new JLabel("CITY*");
		mCityLabel.setBounds(478, 163, 46, 14);
		panel.add(mCityLabel);

		mStateLabel = new JLabel("STATE*");
		mStateLabel.setBounds(478, 188, 69, 14);
		panel.add(mStateLabel);

		mZipCodeLabel = new JLabel("ZIP CODE*");
		mZipCodeLabel.setBounds(478, 213, 69, 14);
		panel.add(mZipCodeLabel);

		mCountryLabel = new JLabel("COUNTRY*");
		mCountryLabel.setBounds(478, 238, 93, 14);
		panel.add(mCountryLabel);

		mPhoneNumberLabel = new JLabel("CONTACT NUMBER*");
		mPhoneNumberLabel.setBounds(478, 286, 118, 14);
		panel.add(mPhoneNumberLabel);

		mEmailAddressLabel = new JLabel("EMAIL ADDRESS*");
		mEmailAddressLabel.setBounds(478, 311, 108, 14);
		panel.add(mEmailAddressLabel);

		mGenderLabel = new JLabel("GENDER*");
		mGenderLabel.setBounds(478, 361, 69, 14);
		panel.add(mGenderLabel);
		
		mStatusLabel = new JLabel("");
		mStatusLabel.setBounds(482, 535, 460, 14);
		panel.add(mStatusLabel);
		mStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mStatusLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		mStatusLabel.setForeground(Color.MAGENTA);
		
		mStatusDisplayLabel = new JLabel("");
		mStatusDisplayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mStatusDisplayLabel.setBounds(478, 510, 491, 14);
		panel.add(mStatusDisplayLabel);
	}

	/**
	 * Initializing JFrame and Jpanel
	 */

	private void intializeFrame() {
		mContactUserInterfaceFrame = new JFrame("Transparent Window");
		Dimension dimension = new Dimension (993,685);
		mContactUserInterfaceFrame.setMinimumSize(dimension);
		mContactUserInterfaceFrame.getContentPane().setForeground(
				new Color(255, 255, 204));
		mContactUserInterfaceFrame.getContentPane().setFont(
				new Font("Tahoma", Font.PLAIN, 11));
		mContactUserInterfaceFrame.setTitle("Contact Manager");
		mContactUserInterfaceFrame.getContentPane().setBackground(Color.WHITE);
		mContactUserInterfaceFrame.setBounds(0, 0, screenSize.width,
				screenSize.height);
		mContactUserInterfaceFrame
				.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mContactUserInterfaceFrame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) 
			{	
				panel.setBounds(((mContactUserInterfaceFrame.getWidth()/2)-486),0, 973, mContactUserInterfaceFrame.getHeight());
				mScrollPane.setBounds(0, 20,457, mContactUserInterfaceFrame.getHeight());		
			}
		});
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(197,11, 973, 550);
		mContactUserInterfaceFrame.getContentPane().add(panel);
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
		mZipCodeTextField.setText("");
		mStateTextField.setText("");
		mCountryTextField.setText("");
		mPhoneNumberTextField.setText("");
		mEmailAddressTextField.setText("");
		mMaleRadioButton.setSelected(true);
		mContactListView.clearSelection();
		mDeleteButton.setEnabled(false);
		mSaveButton.setEnabled(true);
		mProgressBar.setValue(0);
		mode = 1;
		showStatus("        ADD OR VIEW A CONTACT", false);
		colorReset();
	}

	/**
	 * Method to show the persons Contact Information
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
		mCountryTextField.setText(contact.mContactAddress.mCountry);
		mPhoneNumberTextField.setText(contact.mPhoneNumber);
		mEmailAddressTextField.setText(contact.mEmailAddress);
		if (contact.mIsMale) {
			mMaleRadioButton.setSelected(true);
			mFemaleRadioButton.setSelected(false);
		} else {
			mMaleRadioButton.setSelected(false);
			mFemaleRadioButton.setSelected(true);
		}
	}

	/**
	 * Method to show the Status Text Message
	 */
	public void showStatus(String status, boolean error) {
		if (error != true) {
			mStatusLabel.setForeground(Color.MAGENTA);
			mStatusLabel.setText(status);
		} else {
			mStatusLabel.setForeground(Color.RED);
			mStatusLabel.setText(status);
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
		mCountryLabel.setForeground(Color.BLACK);
		mPhoneNumberLabel.setForeground(Color.BLACK);
		mEmailAddressLabel.setForeground(Color.BLACK);
		mGenderLabel.setForeground(Color.BLACK);
	}

	/**
	 * Method to playSound
	 * 
	 * @param url
	 */
	public static synchronized void playSound(final String url) {
		new Thread(new Runnable() {
			public void run() {
				try {
					if (sound) {
						Clip clip = AudioSystem.getClip();
						AudioInputStream inputStream = AudioSystem
								.getAudioInputStream(ContactUserInterface.class
										.getResourceAsStream(url));
						clip.open(inputStream);
						clip.start();
					}

				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}
}