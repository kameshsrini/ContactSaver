package com.contact.saver.ui;

import com.contact.saver.ui.controller.ContactController;
import com.contact.saver.ui.view.ContactUserInterface;

public class ContactSaverMain {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception 
	{
		//Calls ContactController Constructor to establish user control to the UI interface
		ContactController controller = new ContactController();
		//Calls ContactUserInterface Constructor  to provide UserInterface
		ContactUserInterface userInterface = new ContactUserInterface(controller);
		controller.setContactUserInterface(userInterface);
	}
}
