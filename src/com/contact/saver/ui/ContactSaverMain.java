package com.contact.saver.ui;

import com.contact.saver.ui.controller.ContactController;
import com.contact.saver.ui.view.ContactUserInterface;

public class ContactSaverMain {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		ContactController controller = new ContactController();
		ContactUserInterface userInterface = new ContactUserInterface(controller);
		controller.setContactUserInterface(userInterface);
	}
}
