package com.contact.saver.ui.model;
//Class ContactName having FirstName, LastName, MiddleInitial as member variables
public final class ContactName 
{
	public final String mFirstName;
	public final String mLastName;
	public final Character mMiddleInitial;
	
	// The parameterized constructor gets the appropriate String values assigns it to its member variables
    public ContactName(String firstname, String lastname, Character middleInitial) 
    {
    	mFirstName = firstname;
    	mLastName = lastname;
    	mMiddleInitial = middleInitial;
    }
    
    /** This override method appends all the ContactName information of a person and returns it as a String **/
	@Override
 	public String toString() 
	{
 		StringBuilder builder = new StringBuilder();
 		builder.append(mFirstName.trim());
 		builder.append(mLastName.trim());
 		builder.append(mMiddleInitial.toString().trim());
 		return builder.toString();
 	}
}
