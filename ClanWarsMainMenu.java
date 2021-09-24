// The "ClanWarsMainMenu" class
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.Format;
import java.text.DecimalFormat;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import hsa.*;

public class ClanWarsMainMenu extends Applet
{
    Button proceed; //proceeds after mainMenu
    Image image; //
    AudioClip clip; //clip for theme music
    Button login, signup; //login, signup buttons
    TextField username; //username for login
    TextField password; //password for login
    Font f = new Font ("Dialog", Font.BOLD, 30); //custom font
    String paint_screen = "main screen"; //decides which screen is painted for the game
    TextField newUsername; //new username for signup
    TextField newPassword; //new password for signup
    TextField confirmPassword; //confirm new password
    Button confirm; //confirm button for signup
    private String newUser; //string for new username
    private String newPass; //string for new password
    private String confirmPass; //string to confirm new password
    private String user; //string for username input
    private String pass; //string for password input
    Button back; //back button

    public void init ()
    {

	new Sizing_Class (); //resize class

	new Cipher (); //class for encryption

	//proceed button on main menu
	proceed = new Button ("Proceed");
	proceed.setFont (new Font ("Dialog", Font.BOLD, 36));
	proceed.setForeground (Color.white);
	proceed.setBackground (Color.black);

	add (proceed); //adds proceed button

	//gets background image
	image = getImage (getDocumentBase (), "Battle_of_Waterloo_1815.png");

	//gets theme music file
	clip = getAudioClip (getCodeBase (), "themeSong.wav");

	//login menu
	login = new Button ("Login");
	signup = new Button ("Sign Up");
	username = new TextField ();
	password = new TextField ();

	//username and password for signup
	newUsername = new TextField ();
	newPassword = new TextField ();
	confirmPassword = new TextField ();
	confirm = new Button ("Confirm");
	back = new Button ("Back");

	playClip (); //calls method to play music
    } // init method


    public boolean action (Event e, Object o)
    {
	if (e.target == proceed) //if the proceed button is clicked
	{
	    remove (proceed); //remove proceed button

	    //adds login, signup, username, and password
	    add (login);
	    add (signup);
	    add (username);
	    add (password);

	    //paints the screen as the login screen
	    paint_screen = "login screen";
	    repaint ();

	}
	if (e.target == signup) //if the signup button is pressed
	{

	    //removes login, signup, username, and password
	    remove (login);
	    remove (signup);
	    remove (username);
	    remove (password);

	    //adds new username, new password, confirm password, confirm button and back button
	    add (newUsername);
	    add (newPassword);
	    add (confirmPassword);
	    add (confirm);
	    add (back);

	    paint_screen = "sign up"; //paints the screen to the signup screen

	    repaint ();
	}
	if (e.target == login) //if the user presses the login button
	{
	    user = username.getText (); //gets data from the username textfield
	    pass = password.getText (); //gets data from the password textfield
	    pass = Cipher.encrypt (pass, 1); //encrypts the password
	    File User_File = new File ("GameFiles\\Users\\" + user); //file where user data is stored

	    if (User_File.exists () && User_File.isDirectory ()) //if the file exists and it is a directory
	    {
		User_File = new File ("GameFiles\\Users\\" + user + "\\Password.CWF"); //user file finds the password
		if (User_File.exists ()) //if the password file exists
		{

		    try //try
		    {
			BufferedReader in = new BufferedReader (new FileReader (User_File)); //reads the password file
			String temp = in.readLine (); //temp variable reads the line of the file
			if (temp.equals (pass)) //if the read line is equal to the correctly inputted password
			{
			    // call danny's method here

			}
			else
			{
			    new Message ("Error Code : 0001 | Check error manual"); //error message
			}
			in.close (); //closes the file reader
		    }
		    catch (IOException q)  //error message
		    {
			new Message ("Error code : 0002  | Check error manual");
		    }
		}

	    }
	}
	if (e.target == confirm) //if the confirm button is pressed
	{
	    newUser = newUsername.getText (); //gets data from the new username textfield
	    newPass = newPassword.getText (); //gets data from the new password textfield
	    confirmPass = confirmPassword.getText (); //gets data from the confirm password textfield
	}
	if (e.target == back) //if the back button is pressed
	{
	    //adds login, signup, username, and password
	    add (login);
	    add (signup);
	    add (username);
	    add (password);

	    //removes new username, new password, confirm password, confirm button, and back button
	    remove (newUsername);
	    remove (newPassword);
	    remove (confirmPassword);
	    remove (confirm);
	    remove (back);

	    paint_screen = "login screen"; //paints screen to the login screen
	}
	return true; //returns the true pressed value
    } //action method


    public void playClip ()  //plays theme music
    {
	clip.loop (); //loops the background audio music
    }


    public void paint (Graphics g)
    {
	int x = this.getSize ().width; //gets the width of the screen
	int y = this.getSize ().height; //gets the height of the screem
	if (paint_screen.equals ("main screen")) //if the screen is painted to the main screen
	{
	    //main screen picture
	    g.drawImage (image, 0, 0, getSize ().width, getSize ().height, this);

	    //title
	    g.setColor (Color.red);
	    g.setFont (new Font ("Dialog", Font.BOLD, Sizing_Class.Y_Size (17, y)));
	    g.drawString ("Clan Wars", Sizing_Class.X_Pos (30, x), Sizing_Class.Y_Pos (15, y));

	    //proceed button
	    proceed.setSize (200, 100);
	    proceed.setBackground (Color.black);
	    proceed.setForeground (Color.red);
	    proceed.setLocation (Sizing_Class.X_Pos (44, x), Sizing_Class.Y_Pos (20, y));
	}
	else if (paint_screen.equals ("login screen")) //if the screen is painted to the login screen
	{
	    //draws background
	    g.drawImage (image, 0, 0, getSize ().width, getSize ().height, this);

	    g.setFont (f); //set font

	    //username label
	    g.setColor (Color.red);
	    g.drawString ("Username:", Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (5, y));

	    //password label
	    g.setColor (Color.red);
	    g.drawString ("Password:", Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (16, y));

	    //login button
	    login.setFont (f);
	    login.setSize (125, 50);
	    login.setBackground (Color.black);
	    login.setForeground (Color.red);
	    login.setLocation (Sizing_Class.X_Pos (0, x), Sizing_Class.Y_Pos (0, y));

	    //signup button
	    signup.setFont (f);
	    signup.setLocation (Sizing_Class.X_Pos (0, x), Sizing_Class.Y_Pos (10, y));
	    signup.setBackground (Color.black);
	    signup.setForeground (Color.red);
	    signup.setSize (125, 50);

	    //username textfield
	    username.setFont (f);
	    username.setLocation (Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (0, y));
	    username.setSize (670, 50);

	    //password textfield
	    password.setSize (670, 50);
	    password.setFont (f);
	    password.setLocation (Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (10, y));
	}
	else if (paint_screen.equals ("sign up")) //if the screen is painted to the signup screen
	{
	    //paints background
	    g.drawImage (image, 0, 0, getSize ().width, getSize ().height, this);

	    //font
	    g.setFont (f);

	    //new username label
	    g.setColor (Color.red);
	    g.drawString ("New Username:", Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (5, y));

	    //new password label
	    g.setColor (Color.red);
	    g.drawString ("New Password:", Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (16, y));

	    //confirm password label
	    g.setColor (Color.red);
	    g.drawString ("Confirm Password:", Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (27, y));

	    //signup button
	    confirm.setFont (f);
	    confirm.setLocation (0, 0);
	    confirm.setBackground (Color.black);
	    confirm.setForeground (Color.red);
	    confirm.setSize (125, 125);

	    //new username textfield
	    newUsername.setFont (f);
	    newUsername.setLocation (Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (0, y));
	    newUsername.setSize (670, 50);

	    //new password textfield
	    newPassword.setFont (f);
	    newPassword.setLocation (Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (10, y));
	    newPassword.setSize (670, 50);

	    //confirm password texfield
	    confirmPassword.setFont (f);
	    confirmPassword.setLocation (Sizing_Class.X_Pos (37, x), Sizing_Class.Y_Pos (20, y));
	    confirmPassword.setSize (670, 50);

	    //back button
	    back.setFont (f);
	    back.setLocation (0, 140);
	    back.setBackground (Color.black);
	    back.setForeground (Color.red);
	    back.setSize (125, 50);
	}
    } // paint method
} // ClanWarsMainMenu class


