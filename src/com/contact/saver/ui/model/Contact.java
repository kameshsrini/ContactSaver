package com.contact.saver.ui.model;

import java.util.Comparator;

public final class Contact  {
	 public final ContactName mContactName;
	 public final ContactAddress mContactAddress;
     public final String mPhoneNumber;
     public final Boolean mIsMale;
     
     public Contact(ContactName contactName, ContactAddress contactAddress,
    		 String phoneNumber, Boolean isMale) {
    	 mContactName = contactName;
    	 mContactAddress = contactAddress;
    	 mPhoneNumber = phoneNumber;
    	 mIsMale = isMale;
     }
     
     public String getStringForFile() {
 		StringBuilder builder = new StringBuilder();
 		builder.append(mContactName.mFirstName.trim()).append("|");
 		builder.append(mContactName.mLastName.trim()).append("|");
 		builder.append(mContactName.mMiddleInitial.toString().trim()).append("|");
 		
 		builder.append(mContactAddress.mAddressLine1.trim()).append("|");
 		builder.append(mContactAddress.mAddressLine2.trim()).append("|");
 		builder.append(mContactAddress.mCity.trim()).append("|");
 		builder.append(mContactAddress.mState.trim()).append("|");
 		builder.append(mContactAddress.mZipCode).append("|");
 		
 		builder.append(mPhoneNumber.trim()).append("|");
 		builder.append(mIsMale);
		return builder.toString();
	}

}
