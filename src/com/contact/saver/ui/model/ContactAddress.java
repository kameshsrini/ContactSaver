package com.contact.saver.ui.model;

public final class ContactAddress {
    public final String mAddressLine1;
    public final String mAddressLine2;
    public final String mCity;
    public final String mState;
    public final Integer mZipCode;
    
    public ContactAddress(String addressLine1, String addressLine2,
    		String city, String state, Integer zipcode) {
    	mAddressLine1 = addressLine1;
    	mAddressLine2 = addressLine2;
    	mCity = city;
    	mState = state;
    	mZipCode = zipcode;
    }
}
