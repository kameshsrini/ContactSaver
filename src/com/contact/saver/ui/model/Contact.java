package com.contact.saver.ui.model;

//Class Contact having ContactName, ContactAddress,PhoneNumber,IsMale as member variables

public final class Contact  {
	 public final ContactName mContactName;
	 public final ContactAddress mContactAddress;
     public final String mPhoneNumber;
     public final String mEmailAddress;
     public final Boolean mIsMale;
     
     // The parameterized constructor gets the appropriate object, String and boolean values and assigns it to its member variables
     public Contact(ContactName contactName, ContactAddress contactAddress,
    		 String phoneNumber, String emailAddress, Boolean isMale) 
     {
    	 mContactName = contactName;
    	 mContactAddress = contactAddress;
    	 mPhoneNumber = phoneNumber;
    	 mEmailAddress= emailAddress;
    	 mIsMale = isMale; 
     }
     
     /** This method appends all the contact information of a person and returns it as a String for file storage  **/
     public String getStringForFile() 
     {
 		StringBuilder builder = new StringBuilder();
 		builder.append(mContactName.mFirstName.trim()).append("|");
 		builder.append(mContactName.mLastName.trim()).append("|");
 		builder.append(mContactName.mMiddleInitial.toString().trim()).append("|");
 		
 		builder.append(mContactAddress.mAddressLine1.trim()).append("|");
 		builder.append(mContactAddress.mAddressLine2.trim()).append("|");
 		builder.append(mContactAddress.mCity.trim()).append("|");
 		builder.append(mContactAddress.mState.trim()).append("|");
 		builder.append(mContactAddress.mZipCode).append("|");
 		builder.append(mContactAddress.mCountry.trim()).append("|");
 		
 		builder.append(mPhoneNumber.trim()).append("|");
 		builder.append(mEmailAddress.trim()).append("|");
 		builder.append(mIsMale);
		return builder.toString();
	}

}