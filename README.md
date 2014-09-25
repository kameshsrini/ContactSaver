ContactSaver
============
Since this is a course in user interface design, the first assignment is to write a simple program to maintain a file of data.  This is a very common thing to write in the “real world,” but it’s not as easy as it looks.  Specifications are as follows:
1.	The file (and therefore the screen) will contain the following fields: 
a.	First name (20)
b.	Last name (20)
c.	Middle initial (1)	(may be blank; not required)
d.	Address line 1 (35)
e.	Address line 2 (35) 	(may be blank; not required)
f.	City (25)
g.	State (2)
h.	Zip code (9)
i.	Phone number (21)
j.	Gender (1)		(Must be M or F)
2.	Your program must provide three functions: 
a.	Add a new record to the end of the file.
b.	Modify an existing record and write it back to the file.
c.	Delete a record from the file.
3.	Your program must not be able to add a record that contains the same first name, last name, and middle initial as a record already in the file.
4.	Since this is not a database class, you will use a flat text file with one record per line.  One way to do this is to read the entire file into memory when your program starts and write it back out, with changes, when your program exits.   “But java has database functionality.  Can we use that?” No.
5.	You may not read everything into a datagrid or something like it and treat it as a spreadsheet.  You must have separate fields for the various data elements.
6.	You should show the name and phone number only (not every field) in a multi-column scrollable list. Clicking on a list item should put all of its data elements into the fields for modification.
7.	All three functions must be done from the same main screen.  There should be no menu and no multiple screens.
8.	Your program must be written in Java using Swing.
9.	Do not use any third-party controls that we must load in order to compile and run your program.  Only things that are part of the java and javax packages are allowed.  If we cannot compile and run your program from the command line it will get a maximum of 10 points out of 100.
10.	Your program must apply good object-oriented methodology.  That is, the user interface, application logic (what little there is in this program) and the technical services layer must be separate classes.
11.	Make sure you keep your source code, since this program will continue to evolve iteratively.
12.	This is a user interface assignment, so pay particular attention to how your program looks and how easy it is to use.



Compiling and running the code via cmd prompt

change the path to \ContactManager\src

Compile the following packages
1) controller
2 )model
3 )view

The cmd instructions for the above is as follows
javac com\contact\saver\ui\controller\*.java
javac com\contact\saver\ui\model\*.java
javac com\contact\saver\ui\view\*.java


Now, compile the folder carrying the main java file

javac com\contact\saver\ui\*.java

Now, Run the main java file

java com.contact.saver.ui.ContactSaverMain
