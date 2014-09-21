package com.contact.saver.ui.model;


//Class ContactName having AddressLine1, AddressLine2, City State and ZipCode as member variables
public final class ContactAddress {
    public final String mAddressLine1;
    public final String mAddressLine2;
    public final String mCity;
    public final String mState;
    public final Integer mZipCode;
    
    
 //The parameterized constructor gets the appropriate String and Integer values and assigns it to its member variables
    public ContactAddress(String addressLine1, String addressLine2,
    		String city, String state, Integer zipcode) {
    	mAddressLine1 = addressLine1;
    	mAddressLine2 = addressLine2;
    	mCity = city;
    	mState = state;
    	mZipCode = zipcode;
    }
}
