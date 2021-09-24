// The "CustomCharacter" class.
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

public class CustomCharacter extends Applet implements ActionListener
{
    File characteroutput; //a file created that contains character information like health, magic, armour, etc.

    Label name, message, pointsavailable, health, armour, strength,
	agility, NameLabel, Classtype, Weapon, Magic, Points,
	Health_Label, ArmourLbl, AgilityLbl, StrengthLbl, title; //labels used on output for user information

    int characterpoints = 25, Hpamount = 0, Apamount = 0, Armouramount = 0, Weaponselection = 0, Mana_amount,
	classtypeselection = 0, Strengthamount = 0, class_1 = 0, Magicamount = 0, Agilityamount = 0, max_health, max_mana;  //integers that contain information about character data

    String[] Creation_data = new String [25]; //an array for outputting character data

    String Im = "knight"; //string used to set character photo
    String New_Cypher_User = "Tyler", New_Cypher_Pass = ""; //strings used as place holders for username and password

    Button create, addhealth, subhealth, addarmour, subarmour, addstrength, substrength, addagility,
	subagility, addmagic, submagic, addweapon, subweapon, addclass, subclass; //buttons for adding or taking things like health, armour, etc.

    PrintWriter out; //used in the output of the character creation

    Image character_screen_image, background_image; //images for the backgound and current character

    Font LabelFont, titleFont; //font for labels

    File User_Directory;

    TextField CharacterName, ClassName, WeaponName, MagicName; //Textfields that display class name, character name, and magic name to the user

    Date date = new Date (); //variable for current date

    String paint_screen = "Character_Screen"; //string used for current screen


    public void init ()
    {
	new Sizing_Class ();

	//this section instantiates all labels used in this class
	name = new Label ("Name");
	message = new Label ("");
	pointsavailable = new Label ("Points:  " + characterpoints);
	health = new Label ("Health: ");
	armour = new Label ("Armour: ");
	strength = new Label ("Strength: ");
	agility = new Label ("Agility: ");
	NameLabel = new Label ("Name: ");
	Classtype = new Label ("Class: ");
	Weapon = new Label ("Weapon: ");
	Magic = new Label ("");
	Points = new Label ("");
	Health_Label = new Label ("");
	ArmourLbl = new Label ("");
	AgilityLbl = new Label ("");
	StrengthLbl = new Label ("");
	title = new Label ("Character Creation");

	//this section instantiates all buttons used in this class
	create = new Button ("Create Character");
	addhealth = new Button ("+");
	subhealth = new Button ("-");
	addarmour = new Button ("+");
	subarmour = new Button ("-");
	addstrength = new Button ("+");
	substrength = new Button ("-");
	addagility = new Button ("+");
	subagility = new Button ("-");
	addmagic = new Button ("+");
	submagic = new Button ("-");
	addweapon = new Button ("+");
	subweapon = new Button ("-");
	addclass = new Button ("+");
	subclass = new Button ("-");


	CharacterName = new TextField (20); //sets sizes of textfields
	ClassName = new TextField (20);
	WeaponName = new TextField (20);
	MagicName = new TextField (20);

	ClassName.setEditable (false); //sets textfields so that they cannot be changed by the user
	WeaponName.setEditable (false);


	character_screen_image = getImage (getDocumentBase (), "Images//Knight.png"); //images for background and current character
	background_image = getImage (getDocumentBase (), "Battle_of_Waterloo_1815.png");


	buttons (); //calls method that adds buttons to screen (unnecessary, but used to make it look neater)


	create.addActionListener (this); //adds actionlistener to each button the user can interact with
	addhealth.addActionListener (this);
	subhealth.addActionListener (this);
	addarmour.addActionListener (this);
	subarmour.addActionListener (this);
	addstrength.addActionListener (this);
	substrength.addActionListener (this);
	addagility.addActionListener (this);
	subagility.addActionListener (this);
	addclass.addActionListener (this);
	subclass.addActionListener (this);
	addweapon.addActionListener (this);
	subweapon.addActionListener (this);
	addmagic.addActionListener (this);
	submagic.addActionListener (this);
    } // init method


    public void actionPerformed (ActionEvent e)
    {
	new Cipher ();
	if (e.getSource () == create) //if the create button is pressed
	{
	    if (characterpoints != 0) //if there are still points remaining then remind user they still have points
	    {
		message.setText ("You still have points remaining");
	    }

	    if (CharacterName.getText ().equalsIgnoreCase ("")) //if the character does not have a name tell the user to give themselves one
	    {
		message.setText ("Please give yourself a name");
	    }

	    if (characterpoints == 0 && !CharacterName.getText ().equals ("")) //if everything is complete in customization then begin output data to a save file
	    {
		Mana_amount = Agilityamount;
		max_health = Hpamount * 100;
		max_mana = Mana_amount * 100;

		//stores data in array about current characters abilities and health for output to save files
		Creation_data [0] = "" + Hpamount * 100; //stores current health amount
		Creation_data [1] = "" + max_health; //store maximum health
		Creation_data [2] = "" + Mana_amount;
		Creation_data [3] = "" + max_mana; //stores max mana amount
		Creation_data [4] = "" + Agilityamount * 10; //store current mana amount
		Creation_data [5] = "" + Armouramount; //stores armour amount
		Creation_data [6] = "" + Agilityamount; //stores armour amount
		Creation_data [7] = "" + Strengthamount + 1; //stores strength amount
		Creation_data [8] = CharacterName.getText (); //store name given to character
		Creation_data [9] = ClassName.getText (); //stores class name
		Creation_data [10] = WeaponName.getText ();

		if (MagicName.getText ().equals ("")) //if the character is not a magic character output not applicable on that line
		{
		    Creation_data [11] = "NA";
		}
		else //else print their magic abilites
		{
		    Creation_data [11] = MagicName.getText ();
		}

		characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\" + CharacterName.getText ()); //creates a new file under users name then character name

		characteroutput.mkdirs ();

		characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\" + ClassName.getText () + "\\img.txt"); //creates a new image file of the user

		try
		{
		    out = new PrintWriter (new FileWriter (characteroutput));
		    out.println (ClassName);
		    out.close ();
		}
		catch (IOException t)
		{

		}
		characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\" + CharacterName.getText () + "\\Money.txt");
		try
		{
		    out = new PrintWriter (new FileWriter (characteroutput));
		    out.println (Cipher.encrypt ("" + 0, 2));
		    out.println (Cipher.encrypt ("" + 0, 2));
		    out.println (Cipher.encrypt ("" + 10, 2));
		    out.close ();
		}
		catch (IOException t)
		{

		}
		characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\" + CharacterName.getText () + "\\character_data.txt");
		try
		{
		    out = new PrintWriter (new FileWriter (characteroutput));
		    for (int q = 0 ; q < 12 ; q++)
		    {
			out.println (Cipher.encrypt (Creation_data [q], 2));

		    }
		    out.close ();
		}
		catch (IOException t)
		{

		}
		/*  characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\" + CharacterName.getText () + "\\Inventory.txt");
		  try
		  {
		      out = new PrintWriter (new FileWriter (characteroutput));
		      out.println (Creation_data[10]);
		      out.close ();
		  }
		  catch (IOException t)
		  {

		  }*/
		characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\" + CharacterName.getText () + "\\Magic.txt");
		try
		{
		    out = new PrintWriter (new FileWriter (characteroutput));

		    if (MagicName.getText ().equalsIgnoreCase ("Magic: 1"))
		    {
			out.println (Cipher.encrypt ("Fire ball", 2));
		    }
		    if (MagicName.getText ().equalsIgnoreCase ("Magic: 2"))
		    {
			out.println (Cipher.encrypt ("Stone ball", 2));
		    }
		    if (MagicName.getText ().equalsIgnoreCase ("Magic: 3"))
		    {
			out.println (Cipher.encrypt ("Water ball", 2));
		    }
		    if (MagicName.getText ().equalsIgnoreCase ("Magic: 4"))
		    {
			out.println (Cipher.encrypt ("Air ball", 2));
		    }
		    if (MagicName.getText ().equalsIgnoreCase ("Necromancy"))
		    {
			out.println (Cipher.encrypt ("basic skeleton", 2));
		    }
		    out.close ();
		}
		catch (IOException t)
		{

		}
		//characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\" + CharacterName.getText () + "\\Locations");
		characteroutput.mkdirs ();

	    }
	}


	if (e.getSource () == addclass) //if add button for class is pressed add one to class counter
	{
	    if (class_1 < 3) //if class is less than three add 1
	    {
		class_1++;
	    }
	    else if (class_1 == 3) //if class is greater than three reset it to 0
	    {
		class_1 = class_1 - 3;
	    }
	    switch (class_1) //switch for outputting which class is selected and switches the to the corresponding image
	    {
		case 0:
		    ClassName.setText ("Knight");
		    character_screen_image = getImage (getDocumentBase (), "Images//Knight.png");
		    repaint ();
		    break;
		case 1:
		    ClassName.setText ("Mage");
		    character_screen_image = getImage (getDocumentBase (), "Images//Mage.png");
		    repaint ();
		    break;
		case 2:
		    ClassName.setText ("Archer");
		    character_screen_image = getImage (getDocumentBase (), "Images//Archer.png");
		    repaint ();
		    break;
		case 3:
		    ClassName.setText ("Necro");
		    character_screen_image = getImage (getDocumentBase (), "Images//Necro.png");
		    repaint ();
		    break;
	    }
	}

	if (e.getSource () == addweapon) //if add button for class is pressed add one to weapon selection
	{
	    if (Weaponselection < 3) //if weapon selection is less than three add one to weapon selection
	    {
		Weaponselection++;

	    }
	    else if (Weaponselection == 3) //if weapon selection is greater than three reset it to 0
	    {
		Weaponselection = Weaponselection - 3;

	    }


	}
	if (e.getSource () == addmagic) //if add button for class is pressed add one to magic selection
	{

	    if (Magicamount < 4) //if magic selection is less than four add one to magic selection
	    {
		Magicamount++;
	    }
	    else if (Magicamount == 4) //if magic selection is greater than four reset it to 0
	    {
		Magicamount = Magicamount - 4;
	    }
	}

	if (e.getSource () == subclass) //if subract class is pressed than take away one from class selection
	{
	    if (class_1 > 0) //if class is greater than 0 subract one
	    {
		class_1--;
	    }
	    else if (class_1 == 0) //if class is equal to 0 than add three
	    {
		class_1 = class_1 + 3;
	    }

	    switch (class_1) //switch that changes between selection of class types and sets the corresponding image for current class
	    {
		case 0:
		    ClassName.setText ("Knight");
		    character_screen_image = getImage (getDocumentBase (), "Images//Knight.png");
		    repaint ();
		    break;
		case 1:
		    ClassName.setText ("Mage");
		    character_screen_image = getImage (getDocumentBase (), "Images//Mage.png");
		    repaint ();
		    break;
		case 2:
		    ClassName.setText ("Archer");
		    character_screen_image = getImage (getDocumentBase (), "Images//Archer.png");
		    repaint ();
		    break;
		case 3:
		    ClassName.setText ("Necro");
		    character_screen_image = getImage (getDocumentBase (), "Images//Necro.png");
		    repaint ();
		    break;
	    }
	}

	if (e.getSource () == subweapon) //if the subract button is pressed for weapon
	{
	    if (Weaponselection > 0) //takeaway one in weapon
	    {
		Weaponselection--;
	    }
	    else if (Weaponselection == 0) //if weapon becomes 0 then set it back to three
	    {
		Weaponselection = Weaponselection + 3;
	    }

	}

	if (e.getSource () == submagic) //if the subract button is pressed for magic
	{
	    if (Magicamount > 0) //takeaway one in magic
	    {
		Magicamount--;
	    }
	    else if (Magicamount == 0) //if magic becomes 0 then set it back to three
	    {
		Magicamount = Magicamount + 4;
	    }
	}

	if (e.getSource () == addhealth) //occurs if the add health button is pressed
	{
	    if (characterpoints > 0) //if there is character points available add it to Hp and take away one from points remaining
	    {
		characterpoints = characterpoints - 1;
		Hpamount = Hpamount + 1;
		pointsavailable.setText ("Points: " + characterpoints);
		repaint ();
	    }
	}

	if (e.getSource () == addarmour) //occurs if the add armour button is pressed
	{
	    if (characterpoints > 0) //if there is character points available add it to armour and take away one from points remaining
	    {
		characterpoints = characterpoints - 1;
		Armouramount = Armouramount + 1;
		pointsavailable.setText ("Points: " + characterpoints);
		repaint ();
	    }
	}

	if (e.getSource () == addstrength) //occurs if the add strength button is pressed
	{
	    if (characterpoints > 0) //if there is character points available add it to strength and take away one from points remaining
	    {
		characterpoints = characterpoints - 1;
		Strengthamount = Strengthamount + 1;
		pointsavailable.setText ("Points: " + characterpoints);
		repaint ();

	    }
	}

	if (e.getSource () == addagility) //occurs if the add agility button is pressed
	{
	    if (characterpoints > 0) //if there is character points available add it to agility and take away one from points remaining
	    {
		characterpoints = characterpoints - 1;
		Agilityamount = Agilityamount + 1;
		pointsavailable.setText ("Points: " + characterpoints);
		repaint ();
	    }
	}

	if (e.getSource () == subhealth) //if the subract button is pressed for health
	{
	    if (Hpamount > 0) //if Health is greater than 0, add one back to points available and take away one from HP
	    {
		characterpoints = characterpoints + 1;
		Hpamount = Hpamount - 1;
		pointsavailable.setText ("Points: " + characterpoints);
		repaint ();
	    }
	}

	if (e.getSource () == subarmour) //if the subract button is pressed for armour
	{
	    if (Armouramount > 0) //if armour is greater than 0, add one back to points available and take away one from armour points
	    {
		characterpoints = characterpoints + 1;
		Armouramount = Armouramount - 1;
		pointsavailable.setText ("Points: " + characterpoints);
		repaint ();
	    }

	}

	if (e.getSource () == substrength) //if the subract button is pressed for strenth
	{
	    if (Strengthamount > 0) //if strength is greater than 0, add one back to points available and take away one from strength points
	    {
		characterpoints = characterpoints + 1;
		Strengthamount = Strengthamount - 1;
		pointsavailable.setText ("Points: " + characterpoints);
		repaint ();
	    }
	}

	if (e.getSource () == subagility) //if the subract button is pressed for agility
	{
	    if (Agilityamount > 0) //if agility is greater than 0, add one back to points available and take away one from agility points
	    {
		characterpoints = characterpoints + 1;
		Agilityamount = Agilityamount - 1;
		pointsavailable.setText ("Points: " + characterpoints);
		repaint ();
	    }
	}




	if (ClassName.getText ().equalsIgnoreCase ("Mage") | ClassName.getText ().equalsIgnoreCase ("Necro")) //if class selected is mage or necro then select between these magic abilities
	{
	    switch (Magicamount) //a switch used when selecting what type of magic the user would like wield33
	    {
		case 0:
		    MagicName.setText ("Magic: 1");
		    break;
		case 1:
		    MagicName.setText ("Magic: 2");
		    break;
		case 2:
		    MagicName.setText ("Magic: 3");
		    break;
		case 3:
		    MagicName.setText ("Magic: 4");
		    break;

	    }
	}

	else
	{
	    MagicName.setText ("");
	}


	if (ClassName.getText ().equalsIgnoreCase ("mage")) //if class selected is
	{
	    switch (Weaponselection) //switch for weapon selected when button is pressed
	    {
		case 0:
		    WeaponName.setText ("Weapon: 1");
		    break;
		case 1:
		    WeaponName.setText ("Weapon: 2");
		    break;

		case 2:
		    WeaponName.setText ("Weapon: 3");
		    break;

		case 3:
		    WeaponName.setText ("Weapon: 4");
		    break;

	    }
	}


	if (ClassName.getText ().equalsIgnoreCase ("Knight")) //if class selected is knight then choose between these weapons
	{
	    switch (Weaponselection) //switch for weapon selected when button is pressed
	    {
		case 0:
		    WeaponName.setText ("Weapon: 1");
		    break;
		case 1:
		    WeaponName.setText ("Weapon: 2");
		    break;

		case 2:
		    WeaponName.setText ("Weapon: 3");
		    break;

		case 3:
		    WeaponName.setText ("Weapon: 4");
		    break;

	    }
	}


	if (ClassName.getText ().equalsIgnoreCase ("necro")) //if the class selected is necro it changes the weapons to one of few options
	{
	    switch (Weaponselection) //switch for weapon selected when button is pressed
	    {
		case 0:
		    WeaponName.setText ("Weapon: 1");
		    break;
		case 1:
		    WeaponName.setText ("Weapon: 2");
		    break;

		case 2:
		    WeaponName.setText ("Weapon: 3");
		    break;

		case 3:
		    WeaponName.setText ("Weapon: 4");
		    break;

	    }
	}


	if (ClassName.getText ().equalsIgnoreCase ("archer")) //if the class is archer it changes the weapons textfield to one of a few options
	{
	    switch (Weaponselection) //switch for weapon selected when button is pressed
	    {
		case 0:
		    WeaponName.setText ("Weapon: 1");
		    break;
		case 1:
		    WeaponName.setText ("Weapon: 2");
		    break;

		case 2:
		    WeaponName.setText ("Weapon: 3");
		    break;

		case 3:
		    WeaponName.setText ("Weapon: 4");
		    break;

	    }
	}
    } //end of actionperformed


    public void paint (Graphics g)
    {
	new Sizing_Class ();

	//finds x and y size of screens
	int x = this.getSize ().width;
	int y = this.getSize ().height;

	//creates fonts that dynamically change size
	Font titleFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (6, y));
	Font LabelFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (5, y));
	Font buttonFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (2, y));

	setBackground (Color.black); //sets background color

	//sets the size of all the buttons and labels, etc
	create.setSize (Sizing_Class.X_Pos (12, x), Sizing_Class.Y_Pos (6, y)); 
	message.setSize (Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (6, y));
	addmagic.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	submagic.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	health.setSize (Sizing_Class.X_Pos (4, x), Sizing_Class.X_Pos (3, x));
	ArmourLbl.setSize (Sizing_Class.X_Pos (4, x), Sizing_Class.X_Pos (3, x));
	AgilityLbl.setSize (Sizing_Class.X_Pos (4, x), Sizing_Class.X_Pos (3, x));
	StrengthLbl.setSize (Sizing_Class.X_Pos (4, x), Sizing_Class.X_Pos (3, x));
	pointsavailable.setSize (Sizing_Class.X_Pos (18, x), Sizing_Class.Y_Pos (5, y));
	addhealth.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	subhealth.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	addarmour.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	subarmour.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	addclass.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	subclass.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	addmagic.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	submagic.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	addweapon.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	subweapon.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	armour.setSize (Sizing_Class.X_Pos (12, x), Sizing_Class.Y_Pos (5, y));
	agility.setSize (Sizing_Class.X_Pos (12, x), Sizing_Class.Y_Pos (6, y));
	strength.setSize (Sizing_Class.X_Pos (13, x), Sizing_Class.Y_Pos (6, y));
	addstrength.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	substrength.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	addagility.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	subagility.setSize (Sizing_Class.X_Pos (2, x), Sizing_Class.X_Pos (2, x));
	NameLabel.setSize (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (5, y));
	title.setSize (Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (10, y));
	Health_Label.setSize (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (5, y));
	CharacterName.setSize (Sizing_Class.X_Pos (16, x), Sizing_Class.Y_Pos (4, y));
	WeaponName.setSize (Sizing_Class.X_Pos (17, x), Sizing_Class.Y_Pos (4, y));
	ClassName.setSize (Sizing_Class.X_Pos (17, x), Sizing_Class.Y_Pos (4, y));
	MagicName.setSize (Sizing_Class.X_Pos (17, x), Sizing_Class.Y_Pos (4, y));

	//sets locations of all buttons, textfields, buttons, etc
	create.setLocation (Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (90, y));
	message.setLocation (Sizing_Class.X_Pos (60, x), Sizing_Class.Y_Pos (85, y));
	addstrength.setLocation (Sizing_Class.X_Pos (24, x), Sizing_Class.Y_Pos (35, y));
	substrength.setLocation (Sizing_Class.X_Pos (24, x), Sizing_Class.Y_Pos (40, y));
	addagility.setLocation (Sizing_Class.X_Pos (24, x), Sizing_Class.Y_Pos (51, y));
	subagility.setLocation (Sizing_Class.X_Pos (24, x), Sizing_Class.Y_Pos (56, y));
	addarmour.setLocation (Sizing_Class.X_Pos (24, x), Sizing_Class.Y_Pos (21, y));
	pointsavailable.setLocation (Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (60, y));
	subarmour.setLocation (Sizing_Class.X_Pos (24, x), Sizing_Class.Y_Pos (26, y));
	addhealth.setLocation (Sizing_Class.X_Pos (24, x), Sizing_Class.Y_Pos (7, y));
	subhealth.setLocation (Sizing_Class.X_Pos (24, x), Sizing_Class.Y_Pos (12, y));
	Health_Label.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (8, y));
	armour.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (20, y));
	strength.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (34, y));
	agility.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (50, y));
	addmagic.setLocation (Sizing_Class.X_Pos (30, x), Sizing_Class.Y_Pos (66, y));
	submagic.setLocation (Sizing_Class.X_Pos (33, x), Sizing_Class.Y_Pos (66, y));
	addweapon.setLocation (Sizing_Class.X_Pos (30, x), Sizing_Class.Y_Pos (71, y));
	subweapon.setLocation (Sizing_Class.X_Pos (33, x), Sizing_Class.Y_Pos (71, y));
	addclass.setLocation (Sizing_Class.X_Pos (30, x), Sizing_Class.Y_Pos (76, y));
	subclass.setLocation (Sizing_Class.X_Pos (33, x), Sizing_Class.Y_Pos (76, y));
	health.setLocation (Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (6, y));
	ArmourLbl.setLocation (Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (20, y));
	StrengthLbl.setLocation (Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (34, y));
	AgilityLbl.setLocation (Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (50, y));
	MagicName.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (66, y));
	WeaponName.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (71, y));
	CharacterName.setLocation (Sizing_Class.X_Pos (19, x), Sizing_Class.Y_Pos (82, y));
	NameLabel.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (81, y));
	ClassName.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (76, y));
	title.setLocation (Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (0, y));

	MagicName.setEditable (false); //sets the three textfields of magic, class, weapon to be false
	ClassName.setEditable (false);
	WeaponName.setEditable (false);


	health.setText ("" + Hpamount); //sets text of label to be that of the amount of points in each category
	ArmourLbl.setText ("" + Armouramount);
	AgilityLbl.setText ("" + Agilityamount);
	StrengthLbl.setText ("" + Strengthamount);
	Points.setText ("" + characterpoints);
	Health_Label.setText ("Health: ");

	//LabelFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (4, y));
	//TitleFont = new Font ("Dialog", Font.BOLD, Sizing_Class.Y_Pos (15, y));

	health.setFont (LabelFont); //sets fonts for all labels
	ArmourLbl.setFont (LabelFont);
	AgilityLbl.setFont (LabelFont);
	StrengthLbl.setFont (LabelFont);
	pointsavailable.setFont (LabelFont);
	Points.setFont (LabelFont);
	Health_Label.setFont (LabelFont);
	health.setFont (LabelFont);
	armour.setFont (LabelFont);
	strength.setFont (LabelFont);
	agility.setFont (LabelFont);
	message.setFont (LabelFont);
	NameLabel.setFont (LabelFont);
	title.setFont (titleFont);
	create.setFont (buttonFont);
	addmagic.setFont (buttonFont);

	name.setForeground (Color.red); //sets all the colors of the textfields to red
	message.setForeground (Color.red);
	health.setForeground (Color.red);
	armour.setForeground (Color.red);
	strength.setForeground (Color.red);
	agility.setForeground (Color.red);
	pointsavailable.setForeground (Color.red);
	Points.setForeground (Color.red);
	Health_Label.setForeground (Color.red);
	health.setForeground (Color.red);
	armour.setForeground (Color.red);
	strength.setForeground (Color.red);
	agility.setForeground (Color.red);
	ArmourLbl.setForeground (Color.red);
	StrengthLbl.setForeground (Color.red);
	AgilityLbl.setForeground (Color.red);
	NameLabel.setForeground (Color.red);
	title.setForeground (Color.red);

	name.setBackground (Color.black); //sets all the colors of the textfields backgrounds to black
	message.setBackground (Color.black);
	health.setBackground (Color.black);
	armour.setBackground (Color.black);
	strength.setBackground (Color.black);
	agility.setBackground (Color.black);
	pointsavailable.setBackground (Color.black);
	Points.setBackground (Color.black);
	Health_Label.setBackground (Color.black);
	armour.setBackground (Color.black);
	strength.setBackground (Color.black);
	agility.setBackground (Color.black);
	ArmourLbl.setBackground (Color.black);
	StrengthLbl.setBackground (Color.black);
	AgilityLbl.setBackground (Color.black);
	NameLabel.setBackground (Color.black);
	title.setBackground (Color.black);

	g.drawImage (character_screen_image, Sizing_Class.X_Pos (60, x), Sizing_Class.Y_Pos (25, y), Sizing_Class.X_Pos (60, y), Sizing_Class.X_Pos (50, y), this); //draws image on output display
    } // paint method


    public void buttons ()  //a method that adds all the buttons onto the display
    {
	add (create);
	add (CharacterName);
	add (ClassName);
	add (message);
	add (pointsavailable);
	add (health);
	add (armour);
	add (strength);
	add (agility);
	add (NameLabel);
	add (Health_Label);
	add (addhealth);
	add (subhealth);
	add (addarmour);
	add (subarmour);
	add (addstrength);
	add (substrength);
	add (addagility);
	add (subagility);
	add (addmagic);
	add (submagic);
	add (addweapon);
	add (subweapon);
	add (addclass);
	add (subclass);
	add (MagicName);
	add (ArmourLbl);
	add (AgilityLbl);
	add (StrengthLbl);
	add (WeaponName);
	add (title);



	ClassName.setText ("Knight"); //sets current class to knight
	WeaponName.setText ("Weapon: 1");

    }
} // CustomCharacter class


