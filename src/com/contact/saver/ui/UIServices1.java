package com.contact.saver.ui;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 * UIservices Class to limit the characters that can be entered in to the EmailAddress without Uppercasing the content
 * @author kameshsrini
 *
 */
public class UIServices1 extends PlainDocument
{
	private int limit;
	
	public UIServices1 (int limitation) 
	{
		this.limit = limitation;
	}

	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException
	{
		if(str == null)
			return;

		else if((getLength() + str.length()) <= limit)
		{
			super.insertString(offset, str, set);				
		}
	}
	

}
