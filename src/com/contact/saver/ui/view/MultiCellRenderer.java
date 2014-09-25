package com.contact.saver.ui.view;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.contact.saver.ui.model.Contact;

/**
 * Class MultiCellRenderer to split the Jpanel into three column
 */
final class MultiCellRenderer extends JPanel implements
		ListCellRenderer<Contact> {
	/*The columns are FrstName , LastName and PhoneNumber */
	JLabel mFirstName, mLastName, mPhoneNumber;

	/**Constructor to add elements in to the multicolumn List
	 * 
	 */
	MultiCellRenderer() {
		setLayout(new GridLayout(1, 3));
		mFirstName = new JLabel();
		mLastName = new JLabel();
		mPhoneNumber = new JLabel();
		mFirstName.setOpaque(true);
		mLastName.setOpaque(true);
		mPhoneNumber.setOpaque(true);
		add(mFirstName);
		add(mLastName);
		add(mPhoneNumber);
	}
	
	/**
	 * Override method to set and align the content of the list
	 */
	@Override
	public Component getListCellRendererComponent(
			JList<? extends Contact> list, Contact contact, int index,
			boolean isSelected, boolean cellHasFocus) {
		String leftData = contact.mContactName.mFirstName;
		String middleData = contact.mContactName.mLastName;
		String rightData = contact.mPhoneNumber;
		mFirstName.setText(leftData);
		mLastName.setText(middleData);
		mPhoneNumber.setText(rightData);
		if (isSelected) {
			mFirstName.setBackground(list.getSelectionBackground());
			mFirstName.setForeground(list.getSelectionForeground());
			mLastName.setBackground(list.getSelectionBackground());
			mLastName.setForeground(list.getSelectionForeground());
			mPhoneNumber.setBackground(list.getSelectionBackground());
			mPhoneNumber.setForeground(list.getSelectionForeground());
		} else {
			mFirstName.setBackground(list.getBackground());
			mFirstName.setForeground(list.getForeground());
			mLastName.setBackground(list.getBackground());
			mLastName.setForeground(list.getForeground());
			mPhoneNumber.setBackground(list.getBackground());
			mPhoneNumber.setForeground(list.getForeground());
		}
		setEnabled(list.isEnabled());
		setFont(list.getFont());
		return this;
	}
}
