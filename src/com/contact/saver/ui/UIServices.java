package com.contact.saver.ui;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * UIservices Class to limit the characters that can be entered in to the TextFields
 * @author kameshsrini
 *
 */
public class UIServices extends PlainDocument
{	private int limit;
		
	public UIServices (int limitation)
	{
		this.limit = limitation;
	}

	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException
	{
		if(str == null)
			return;

		else if((getLength() + str.length()) <= limit)
		{
			str = str.toUpperCase();
			super.insertString(offset, str, set);				
		}
	}
	
}

