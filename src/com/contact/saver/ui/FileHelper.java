package com.contact.saver.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import com.contact.saver.ui.model.Contact;
import com.contact.saver.ui.model.ContactAddress;
import com.contact.saver.ui.model.ContactName;

public class FileHelper {
	File mFile;
	File mTempfile;

	/**
	 * Constructor FileHelper to Check and Create files
	 * Files - Contacts.txt , myTempFile.txt
	 */
	
	public FileHelper() {
		try {
			/* Check and create a file. */
			String filePathString = "./Contacts.txt";
			mFile = new File(filePathString);
			if (mFile.exists() && !mFile.isDirectory()) {
				System.out.println("File exists");
			} else 
			{
				mFile.createNewFile();
			}

			/* Check and create a temp file. */
			String tempFilePathString = "./myTempFile.txt";
			mTempfile = new File(tempFilePathString);
			if (mTempfile.exists() && !mTempfile.isDirectory()) {
				System.out.println("Temp File exists");
			} else {
				mTempfile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function of type List Contact to write all the contacts into the file
	 * @return
	 */
	public List<Contact> getAllContacts() {
		List<Contact> contacts = new ArrayList<Contact>();
		BufferedReader tr;
		String line = null;
		try {
			tr = new BufferedReader(new FileReader(mFile));
			while ((line = tr.readLine()) != null) {
				String[] temp = line.split("\\|");
				if (temp.length == 12) {
					Character middleInitial = '\u0000';
					if (temp[2].length() > 0) {
						middleInitial = temp[2].charAt(0);
					}
					ContactName contactName = new ContactName(temp[0], temp[1],
							middleInitial);
					ContactAddress contactAddress = new ContactAddress(temp[3],
							temp[4], temp[5], temp[6],
							Integer.parseInt(temp[7]), temp[8]);
					Contact contact = new Contact(contactName, contactAddress,
							temp[9], temp[10], Boolean.parseBoolean(temp[11]));
					contacts.add(contact);
				}
			}
			tr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contacts;
	}

	/**
	 *  METHOD TO ADD A CONTACT INTO THE FILE
	 * @param contact
	 */
	public void addContact(Contact contact) {
		String summary = contact.getStringForFile();
		System.out.println(summary);
		try {
			PrintWriter fw = new PrintWriter(new BufferedWriter(new FileWriter(
					mFile, true)));
			fw.println(summary); // stores info in RAM
			fw.flush(); // moves the content to file
			fw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * METHOD TO  TO DELETE A CONTACT FROM THE FILE
	 * @param contact
	 */
	public void deleteContact(Contact contact) {
		try {
			BufferedReader sr = new BufferedReader(new FileReader(mFile));
			PrintWriter sw = new PrintWriter(new FileWriter(mTempfile));
			String line = null;

			while ((line = sr.readLine()) != null) {
				if (line.contains(contact.getStringForFile()))
					continue;
				else {
					sw.println(line);
					sw.flush();
				}
			}
			sw.close();
			sr.close();
			CopyFile(mTempfile, mFile);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 *  FUNCTION TO COPY TEMPFILE INTO CONTACTS FILE
	 * @param sourceFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void CopyFile(File sourceFile, File destFile)
			throws IOException {
		if (!destFile.exists())
			destFile.createNewFile();
		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null)
				source.close();
			if (destination != null)
				destination.close();
		}
	}
}
