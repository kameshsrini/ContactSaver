package com.contact.saver.ui.model;

public final class ContactName {
	public final String mFirstName;
	public final String mLastName;
	public final Character mMiddleInitial;
    
    public ContactName(String firstname, String lastname, Character middleInitial) {
    	mFirstName = firstname;
    	mLastName = lastname;
    	mMiddleInitial = middleInitial;
    }

	@Override
 	public String toString() {
 		StringBuilder builder = new StringBuilder();
 		builder.append(mFirstName.trim());
 		builder.append(mLastName.trim());
 		builder.append(mMiddleInitial.toString().trim());
 		return builder.toString();
 	}
}
