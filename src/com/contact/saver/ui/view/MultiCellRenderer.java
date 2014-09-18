package com.contact.saver.ui.view;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.contact.saver.ui.model.Contact;

final class MultiCellRenderer extends JPanel implements
		ListCellRenderer<Contact> {
	JLabel mFirstName, mLastName, mPhoneNumber;

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
