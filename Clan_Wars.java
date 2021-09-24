/*
*******************************************************************************************************
*********************************** VERSION 1.0.1.1 ***************************************************
*******************************************************************************************************
**** Programmer 1/3 : Tyler Ykema *********************************************************************
**** Programmer 2/3 : Niko Haloulos *******************************************************************
**** Programmer 3/3 : Danny Smith *********************************************************************
**** Institution    : Woodstock Collegiate Institute **************************************************
**** Address        : 35 Riddell St, Woodstock, ON. N4S 6L9 *******************************************
**** Type           : Game ****************************************************************************
**** Teacher        : Mr C. Baxter ********************************************************************
**** Course         : ISC4U Culminating ***************************************************************
**** Description    : This program is an RPG (Role Playing Game) in the Java programming language******
		      using the Ready to Program IDE. Composed of almost 9000 lines,*******************
		      this game is on a screen to screen basis. The user can change between the********
		      general game screen, the store screen, the blacksmith screen, and****************
		      the fight screen using either the keyboard or the buttons on the screen.*********
		      The data for the program was stored as .CWF files and were implemented***********
		      using BufferedReader and FileWriter. In addition, sound files were stored as .wav
		      and imaages were stored as .png files. This program also uses 1 to 4 bit*********
		      substitution encryption along with a sizing class which was in use to return*****
		      percentage sizes for objects such as buttons and textfields.*********************
*******************************************************************************************************

Updates -
Security revisions
Else backup revisions
data review
more messages
*/
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import hsa.*;

public class Clan_Wars extends Applet implements ActionListener, KeyListener, MouseListener
{
    // used to ensure key's always have priority ->
    public void mouseClicked (MouseEvent m)
    {
	this.requestFocus ();
    }


    public void mousePressed (MouseEvent m)
    {
	this.requestFocus ();
    }


    public void mouseReleased (MouseEvent m)
    {
	this.requestFocus ();
    }


    public void mouseEntered (MouseEvent m)
    {
	this.requestFocus ();
    }


    public void mouseExited (MouseEvent m)
    {
	this.requestFocus ();
    }


    // used to ensure key's always have prority <-

    private Thread Periodic = new Thread ()  // used to draw status bars and other user data on screen periodicly
    {

	public void run ()
	{
	    int x = 0; // used for counting
	    Graphics h = getGraphics ();
	    while (true) // repeat update of status bars every second
	    {

		delay (1000);
		x++;
		// save ->
		if (x == 60)
		{
		    try
		    {
			out = new PrintWriter (new FileWriter ("GameFiles//Users//" + name + "//Characters//" + User_Data [5] + "//character_data.CWF"));
			for (int b = 0 ; b < 15 ; b++) // output user data
			{
			    if (b == 7)
			    {
				out.println (User_Data [b]);
			    }
			    else
			    {
				out.println (Cipher.encrypt (User_Data [b], 2));
			    }

			} // for
			out.close ();
			repaint ();
			out = new PrintWriter (new FileWriter ("GameFiles//Users//" + name + "//Characters//" + User_Data [5] + "//Inventory.CWF"));
			for (int i = 0 ; i < 100 ; i++)
			{
			    if (!Inventory [i] [0].equalsIgnoreCase (""))
			    {
				for (int b = 0 ; b < Prices.length ; b++)
				{
				    if (Inventory [i] [0].equalsIgnoreCase (Prices [b] [0]))
				    {
					out.println (Inventory [i] [0]);
					out.println (Cipher.encrypt (Inventory [i] [1], 2));
				    }
				}

			    } // if something
			} // for i

			out.close ();

			repaint ();
			out = new PrintWriter (new FileWriter ("GameFiles//Users//" + name + "//Characters//" + User_Data [5] + "//Magic.CWF"));

			out.close ();
			out = new PrintWriter (new FileWriter ("GameFiles//Users//" + name + "//Characters//" + User_Data [5] + "//Money.CWF"));
			out.println (Cipher.encrypt ("" + Gold, 2));
			out.println (Cipher.encrypt ("" + Silver, 2));
			out.println (Cipher.encrypt ("" + Copper, 2));
			out.close ();
			out = new PrintWriter (new FileWriter ("GameFiles//Users//" + name + "//Worlds//" + WORLD + "_INFO.CWF"));
			temp = formatte.format (coordinate_x) + "" + (formatte.format (coordinate_y));
			out.println (Cipher.encrypt (temp, 2));
			out.println (Cipher.encrypt ("" + castle_pop [0], 2));
			out.println (Cipher.encrypt ("" + castle_pop [1], 2));
			out.println (Cipher.encrypt ("" + castle_pop [2], 2));
			out.println (Cipher.encrypt ("" + castle_pop [3], 2));
			out.close ();

			repaint ();
			out = new PrintWriter (new FileWriter ("GameFiles//Users//" + name + "//Worlds//" + WORLD + ".CWF"));
			for (int c = 0 ; c < 3 ; c++)
			{
			    for (int b = 0 ; b < 300 ; b++) // for y-axis of map
			    {
				for (int z = 0 ; z < 3 ; z++)
				{
				    for (int i = 0 ; i < 100 ; i++) // for x-axis of map
				    {
					out.print (Cipher.encrypt ("" + formatter.format (map [i + (z * 100)] [b] [c]), 2));

				    } // x-axis of map
				    out.println ();
				} // for 3 rounds of output per line

			    } // for y-axis of map

			} // z-axis of map

			// output world <--

			out.close ();

			repaint ();
		    }
		    catch (Throwable cause)
		    {
		    }





		    x = 0;
		} // if



		// <- save



		// Gc ->
		if (x == 10 | x == 20 | x == 30 | x == 40 | x == 50 | x == 60)
		{
		    System.gc ();

		    // garbage collection every 30 seconds
		} // if
		// GC <-




	    } // while
	} // run

    } // garbage collection and save


    ;

    //tyler character creation
    File characteroutput; //a file created that contains character information like health, magic, armour, etc.

    Label Health_Label, lbl_name, message, pointsavailable, health, armour, strength,
	agility, NameLabel, Classtype, Weapon, Magic, Points,
	Healthamount, ArmourLbl, AgilityLbl, StrengthLbl, title; //labels used on output for user information

    int characterpoints = 25, Hpamount = 0, Apamount = 0, Armouramount = 0, Weaponselection = 0,
	classtypeselection = 0, Strengthamount = 0, class_1 = 0, Magicamount = 0, Agilityamount = 0;  //integers that contain information about character data

    String[] Creation_data = new String [15]; //an array for outputting character data

    String Im = "knight"; //string used to set character photo
    String New_Cypher_User = "user", New_Cypher_Pass = ""; //strings used as place holders for username and password

    Button create, addhealth, subhealth, addarmour, subarmour, addstrength, substrength, addagility,
	subagility, addmagic, submagic, addweapon, subweapon, addclass, subclass; //buttons for adding or taking things like health, armour, etc.

    PrintWriter out; //used in the output of the character creation

    Image character_screen_image, background_image; //images for the backgound and current character

    Font LabelFont = new Font ("Dialog", Font.PLAIN, 28); //font for labels
    Font TitleFont = new Font ("Dialog", Font.BOLD, 32);

    File User_Directory;

    TextField CharacterName, ClassName, WeaponName, MagicName; //Textfields that display class name, character name, and magic name to the user

    Date date = new Date (); //variable for current date
    String Password = "";
    boolean NEW = false;

    Button attack, spell, defend, change_weapon, potions, weapon_up, weapon_down, magic_up, magic_down, potion_up, potion_down,
	attack_spell, flee, change_magic, fightbutton, perform_move, next_move, use_potion; //variables for buttons on users display, buttons to change screens, change weapons/magic/potions.

    Label mana, magic, whos_turn, winner, current_user_health, current_enemy_health,
	current_usermana_label, current_enemymana_label, last_move, lastuser_move; //labels that show important information to the user like amount of health, or last enemy move

    int enemy_attack, Magicselection, Potionselection, enemy_move,   //integers that contain information about the users/enemy health, mana, damage, etc.
	user_damage = 10, userHp, enemyHp, enemy_damage = 10, enemy_mana = 100, user_mana = 100, currentweapon, current_enemy_Hp = enemyHp,
	current_user_Hp = userHp, current_user_mana = user_mana, current_enemy_mana = enemy_mana, enemy_magic_damage = 5, user_magic_damage = 5, health_potion = 10, mana_potion = 10, arrows = 3;

    String[] [] user_data = new String [25] [5]; //a string containing data about the users inventory of the user

    boolean turn_counter = true, userattack = false, enemyattack = false, userdefend = false,
	enemydefend = false, userspell = false, enemyspell = false, user_potion = false; //boolean variables for a fighting sequence

    //File User_Directory;

    TextField weapon_selection, magic_selection, potion_selection; //a textfield that tells the user about the current selected item

    Image users_image, enemy_image, health_image, mana_image, weapon_image, magic_image, potion_image; //images that will be used on many different screens

    String current_potion = "Health_potion", enemy_class, latestmove = "nothing", latestusermove = "nothing";  //string variables

    int current_enemy_healthbar = enemyHp, current_enemy_manabar = current_enemy_mana, current_enemy_damage = enemy_damage, current_enemy_magic_damage = enemy_magic_damage; //integers used during the fighting sequence
    int current_user_healthbar = current_user_Hp, current_user_manabar = current_user_mana, current_user_damage = user_damage, current_user_magic_damage = user_magic_damage, spellcost = 10;
    int EXP = 0;
    int enemy_lvl = 0; // determin enemy stats

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
    TextField confirmPassword; //confirm new password                     nico
    Button confirm; //confirm button for signup
    private String newUser; //string for new username
    private String newPass; //string for new password
    private String confirmPass; //string to confirm new password
    private String user; //string for username input
    private String pass; //string for password input
    Button back; //back button
    Button forward, left, right, down, fight, special; //movement buttons

    int coordinate_x = 149, coordinate_y = 149; //map dimensions

    Font fnt; //font

    int clips = 7;

    List LIST = new List (); //list
    File errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
    String Prices[] [] = new String [100] [4]; //inventory items

    String Inventory[] [] = new String [100] [2]; //inventory

    Button info = new Button ("Info"), drop = new Button ("Drop"); //drop button


    String fileName, line; //file name and line for file reading

    BufferedReader in; //reads files

    TextArea information = new TextArea (); //text area for item description

    //images
    Image blacksmith, store, school, castlewall, castlegate, castlegrounds, plains, road, forest, mine, mountain, lake;

    //quantities of inventory items and font
    Font TITLE = new Font ("Serif", Font.BOLD, 32);

    int wood = 0;
    int gold = 0, copper = 0, fish = 0, deerskin = 0, bearskin = 0, iron = 0;
    int luck = 5;
    int Gold = 0, Silver = 0, Copper = 0, InventoryAmount = 60, LEVEL = 2;
    String NAME, WEAPON, CLASS;
    int X, Y;
    //font
    Font font;

    //map coordinates

    //sounds
    AudioClip mouseclick, blacksmithsound, castlesound, forestsound, miningsound, plainssound, riversound;



    boolean exists = false;
    String pre_paint = "";






    AudioClip clip2; //clip for mp3 screen
    String ln1 = ""; // used to read encrypted world line
    String convert = ""; // string to convert
    String lst_String = ""; // get selected string from list
    Image img_Main_Screen; // Main screen image
    int x = 0; // screen width
    int y = 0; // screen height
    String name = "smitdann750"; // username
    int Health = 0; // users health stat                       danny
    int Mana = 0; // users mana stat
    int Wealth = 0; // gold + silver + copper
    String Clan = ""; // holds the clan the charatcer is from
    List lst_Characters = new List (); // list of users characters
    List lst_Worlds = new List (); // list of worlds for user to pick from
    Button cmd_Play = new Button ("Play"); // play world with selected character
    Button cmd_Create = new Button ("Create"); // add character
    Button cmd_Delete = new Button ("Delete"); // delete character
    Button cmd_Reset = new Button ("Reset"); // reset world
    Button Yes = new Button ("Yes"); // used for confirmation
    Button No = new Button ("No"); // used for confirmation
    int Max_Health = 0; // max health of character
    int Max_Mana = 0; // max stamana of character
    Font fnt_Main_Screen_Title = new Font ("MonoSpaced", Font.PLAIN, 24); //title font
    Font fnt_Main_Screen_Writing = new Font ("MonoSpaced", Font.PLAIN, 16); // other writing font
    boolean Check = false; // for checking to delete a character main page
    String error = ""; // holds error messages for screens
    int map[] [] [] = new int [300] [300] [3]; // Map 300 x 300 x 2
    int worlds = 0; // used for checking and creating worlds
    NumberFormat formatter = new DecimalFormat ("00"); // formatting map data for proper reading
    int castle_pop[] = new int [4]; // populations for each castle
    String User_Data[] = new String [15]; // user data for recall
    String img = "";  // users image file
    int Z = 0; // used for loading bar



    List Vendor = new List (); // vendors items
    List Personal = new List (); // users inventory
    List Middle = new List (); // list of transaction items
    TextField VendorGold = new TextField (); // vendors gold count
    TextField VendorSilver = new TextField (); // vendors silver count
    TextField VendorCopper = new TextField (); // vendors copper count
    TextField PersonalGold = new TextField (); // user gold textfield
    TextField PersonalSilver = new TextField (); // user silver textfield
    TextField PersonalCopper = new TextField (); // users copper textfield
    TextField TransactionGold = new TextField (); // middle transaction gold
    TextField TransactionSilver = new TextField (); // middle transaction silver
    Font fnt_Lists = new Font ("MonoSpaced", Font.PLAIN, 12); // list font
    TextField TransactionCopper = new TextField (); // middle copper textfield            // Danny Blacksmith
    int list = 0; // value for font pt size
    String temp = ""; // check item of list vs indexes
    int lvl = 3; // level that alters inventory
    Button SELL = new Button ("SELL"), BUY = new Button ("BUY"), CONFIRM = new Button ("FIN"), REMOVE = new Button ("DEL"), BACK = new Button ("Back"); // onscreen buttons
    int VENDOR_SILVER = 0, VENDOR_COPPER = 0, VENDOR_GOLD = 0; // vendors golds silver copper values
    int TRANSACTION_COPPER = 0, TRANSACTION_SILVER = 0, TRANSACTION_GOLD = 0; // middle gold silver copper values
    String MIDDLE[] [] = new String [100] [2]; // array of items in the transaction log
    boolean CHECK = false, CHECK2 = false; // flagging levels of adding stuff to middle array
    String VENDOR[] [] = new String [100] [3]; // keep track of vendor information
    String temporary; // for aligning text
    String ERROR_MESSAGE = ""; // display error messages
    String WORLD = ""; // used for keeping track of world
    NumberFormat formatte = new DecimalFormat ("000"); // formatting map data for proper reading
    Button PLAY = new Button ("Play");
    List MP3 = new List ();
    int N = 0;

    //school variables (Tyler)
    //buttons to buy, add levels, go back, take away levels, etc.
    Button backbutton, buy, addHealth, continuebutton, subHealth, addMana, subMana, addStamina, subStamina, addStrength, subStrength;

    //integers for keeping track of levels, currency, amounts, prices, etc.
    int healthlvl, manalvl, staminalvl, strengthlvl, healthamount, staminaamount, strengthamount, manaamount,
	goldamount, silveramount, bronzeamount, currency = Wealth, price, addedhealth, addedmana, addedstamina, addedstrength,
	healthprice, manaprice, strengthprice, staminaprice;

    //strings used for switching screens and images
    String userclass = "Knight";

    //images used on display
    Image School_image, user_image;


    //key typed
    public void keyTyped (KeyEvent e)
    {
    }


    public void mp3 ()
    {

	add (MP3);
	add (PLAY);
	MP3.add ("001");

	repaint ();

    }


    //key released
    public void keyReleased (KeyEvent e)
    {
	Graphics g = getGraphics (); //graphics

	char c = e.getKeyChar (); //gets pressed key value

	//calls methods according to which key is pressed
	if (paint_screen.equalsIgnoreCase ("general_Screen") | paint_screen.equalsIgnoreCase ("fight_Screen") | paint_screen.equalsIgnoreCase ("mp3") | paint_screen.equalsIgnoreCase ("school") | paint_screen.equalsIgnoreCase ("blacksmith") | paint_screen.equalsIgnoreCase ("store") | paint_screen.equalsIgnoreCase ("inventory screen") | paint_screen.equalsIgnoreCase ("stats screen") && c == 'i' | c == 's' | c == 'b' | c == 'I' | c == 'S' | c == 'B' | c == 'm' | c == 'M')
	{

	    if (paint_screen.equalsIgnoreCase ("general_Screen"))
	    {
		remove (forward);
		remove (down);
		remove (left);
		remove (right);
		remove (special);
		remove (fight);


	    }

	    if (paint_screen.equalsIgnoreCase ("school"))
	    {
		remove (Vendor);
		remove (Personal);
		remove (Middle);
		remove (VendorGold);
		remove (VendorSilver);
		remove (VendorCopper);
		remove (PersonalGold);
		remove (PersonalSilver);
		remove (PersonalCopper);
		remove (TransactionGold);
		remove (TransactionSilver);
		remove (TransactionCopper);
		remove (BUY);
		remove (SELL);
		remove (CONFIRM);
		remove (REMOVE);
	    }
	    if (paint_screen.equalsIgnoreCase ("blacksmith"))
	    {
		remove (Vendor);
		remove (Personal);
		remove (Middle);
		remove (VendorGold);
		remove (VendorSilver);
		remove (VendorCopper);
		remove (PersonalGold);
		remove (PersonalSilver);
		remove (PersonalCopper);
		remove (TransactionGold);
		remove (TransactionSilver);
		remove (TransactionCopper);
		remove (BUY);
		remove (SELL);
		remove (CONFIRM);
		remove (REMOVE);
	    }
	    if (paint_screen.equalsIgnoreCase ("store"))
	    {
		remove (Vendor);
		remove (Personal);
		remove (Middle);
		remove (VendorGold);
		remove (VendorSilver);
		remove (VendorCopper);
		remove (PersonalGold);
		remove (PersonalSilver);
		remove (PersonalCopper);
		remove (TransactionGold);
		remove (TransactionSilver);
		remove (TransactionCopper);
	    }
	    if (paint_screen.equalsIgnoreCase ("Fight_Screen"))
	    {
		remove (attack);
		remove (spell);
		remove (defend);
		remove (change_weapon);
		remove (flee);
		remove (potions);
		remove (change_magic);
		remove (whos_turn);
		remove (current_user_health);
		remove (current_enemy_health);
		remove (current_usermana_label);
		remove (current_enemymana_label);
		remove (last_move);
		remove (lastuser_move);

	    }


	    if (paint_screen == "Change_Weapon_Screen")
	    {
		remove (fightbutton);
		remove (weapon_selection);
		remove (weapon_up);
		remove (weapon_down);

	    }


	    if (paint_screen == "Magic_Selection_Screen")
	    {
		remove (fightbutton);
		remove (magic_up);
		remove (magic_down);
		remove (magic_selection);
	    }


	    if (paint_screen == "Potion_Screen")
	    {
		remove (fightbutton);
		remove (potion_selection);
		remove (potion_up);
		remove (potion_down);
		remove (use_potion);
	    }

	    if (paint_screen == "Win_Screen")
	    {
		remove (winner);
		remove (next_move);
	    }
	    if (c == 'q' | c == 'Q')
	    {
		saveGame ();
	    }
	    if (c == 'm' | c == 'M')
	    {
		if (!pre_paint.equalsIgnoreCase ("inventory") && !pre_paint.equalsIgnoreCase ("stats") && !pre_paint.equalsIgnoreCase ("mp3") && !pre_paint.equalsIgnoreCase ("save game"))
		{


		    pre_paint = paint_screen;
		    paint_screen = "mp3";
		    mp3 ();
		}
	    }
	    if (c == 'i' | c == 'I')
	    {
		switch (clips)
		{
		    case 0:
			if (blacksmithsound != null)
			{
			    blacksmithsound.stop ();
			    blacksmithsound = null;
			}
			break;

		    case 1:
			if (castlesound != null)
			{
			    castlesound.stop ();
			    castlesound = null;
			}
			break;

		    case 2:
			if (castlesound != null)
			{
			    castlesound.stop ();
			    castlesound = null;
			}
			break;

		    case 3:
			if (plainssound != null)
			{
			    plainssound.stop ();
			    plainssound = null;
			}
			break;

		    case 4:
			if (forestsound != null)
			{
			    forestsound.stop ();
			    forestsound = null;
			}
			break;

		    case 5:
			if (miningsound != null)
			{
			    miningsound.stop ();
			    miningsound = null;
			}
			break;

		    case 6:
			if (riversound != null)
			{
			    riversound.stop ();
			    riversound = null;
			}
			break;

		    default:
			break;
		}
		inventory ();
	    }
	    if (c == 's' | c == 'S')
	    {
		switch (clips)
		{
		    case 0:
			if (blacksmithsound != null)
			{
			    blacksmithsound.stop ();
			    blacksmithsound = null;
			}
			break;

		    case 1:
			if (castlesound != null)
			{
			    castlesound.stop ();
			    castlesound = null;
			}
			break;

		    case 2:
			if (castlesound != null)
			{
			    castlesound.stop ();
			    castlesound = null;
			}
			break;

		    case 3:
			if (plainssound != null)
			{
			    plainssound.stop ();
			    plainssound = null;
			}
			break;

		    case 4:
			if (forestsound != null)
			{
			    forestsound.stop ();
			    forestsound = null;
			}
			break;

		    case 5:
			if (miningsound != null)
			{
			    miningsound.stop ();
			    miningsound = null;
			}
			break;

		    case 6:
			if (riversound != null)
			{
			    riversound.stop ();
			    riversound = null;
			}
			break;

		    default:
			break;
		}
		stats ();
	    }
	    if (paint_screen.equalsIgnoreCase ("MP3"))
	    {
		if (c == 'b' | c == 'B')
		{

		    paint_screen = pre_paint;

		    if (paint_screen.equalsIgnoreCase ("general_Screen"))
		    {
			add (left);
			add (right);
			add (forward);
			add (down);
			add (special);
			add (fight);
		    }
		    if (paint_screen.equalsIgnoreCase ("Fight_Screen"))
		    {
			add (attack);
			add (spell);
			add (defend);
			add (change_weapon);
			add (flee);
			add (potions);
			add (change_magic);
			add (whos_turn);
			add (current_user_health);
			add (current_enemy_health);
			add (current_usermana_label);
			add (current_enemymana_label);
			add (last_move);
			add (lastuser_move);

		    }


		    if (paint_screen == "Change_Weapon_Screen")
		    {
			add (fightbutton);
			add (weapon_selection);
			add (weapon_up);
			add (weapon_down);

		    }


		    if (paint_screen == "Magic_Selection_Screen")
		    {
			add (fightbutton);
			add (magic_up);
			add (magic_down);
			add (magic_selection);
		    }


		    if (paint_screen == "Potion_Screen")
		    {
			add (fightbutton);
			add (potion_selection);
			add (potion_up);
			add (potion_down);
			add (use_potion);
		    }

		    if (paint_screen == "Win_Screen")
		    {
			add (winner);
			add (next_move);
		    }



		    if (paint_screen.equalsIgnoreCase ("school"))
		    {
			add (Vendor);
			add (Personal);
			add (Middle);
			add (VendorGold);
			add (VendorSilver);
			add (VendorCopper);
			add (PersonalGold);
			add (PersonalSilver);
			add (PersonalCopper);
			add (TransactionGold);
			add (TransactionSilver);
			add (TransactionCopper);
			add (BUY);
			add (SELL);
			add (CONFIRM);
			add (REMOVE);
		    }
		    if (paint_screen.equalsIgnoreCase ("blacksmith"))
		    {
			add (Vendor);
			add (Personal);
			add (Middle);
			add (VendorGold);
			add (VendorSilver);
			add (VendorCopper);
			add (PersonalGold);
			add (PersonalSilver);
			add (PersonalCopper);
			add (TransactionGold);
			add (TransactionSilver);
			add (TransactionCopper);
			add (BUY);
			add (SELL);
			add (CONFIRM);
			add (REMOVE);
		    }
		    if (paint_screen.equalsIgnoreCase ("store"))
		    {
			add (Vendor);
			add (Personal);
			add (Middle);
			add (VendorGold);
			add (VendorSilver);
			add (VendorCopper);
			add (PersonalGold);
			add (PersonalSilver);
			add (PersonalCopper);
			add (TransactionGold);
			add (TransactionSilver);
			add (TransactionCopper);
			add (BUY);
			add (SELL);
			add (CONFIRM);
			add (REMOVE);
		    }
		    if (MP3.getSelectedIndex () != -1)
		    {


			if (MP3.getItem (MP3.getSelectedIndex ()).equalsIgnoreCase ("001"))
			{
			    clip2.stop ();
			    clip2 = null;

			}
			else if (MP3.getItem (MP3.getSelectedIndex ()).equalsIgnoreCase ("002"))
			{
			    clip2 = null;
			    clip2.stop ();
			}
			else if (MP3.getItem (MP3.getSelectedIndex ()).equalsIgnoreCase ("003"))
			{
			    clip2 = null;
			    clip2.stop ();
			}
		    }
		    MP3.clear ();
		    remove (MP3);
		    remove (PLAY);
		    repaint ();
		} // if b
		if (c == 's' | c == 'S')
		{
		    stats ();
		    repaint ();
		} //stats
	    } // mp3

	    if (paint_screen.equalsIgnoreCase ("inventory screen")) //inventory screen
	    {
		if (c == 'b' | c == 'B')
		{

		    paint_screen = pre_paint;

		    if (paint_screen.equalsIgnoreCase ("general_Screen"))
		    {
			add (left);
			add (right);
			add (forward);
			add (down);
			add (special);
			add (fight);
		    }
		    if (paint_screen.equalsIgnoreCase ("Fight_Screen"))
		    {
			add (attack);
			add (spell);
			add (defend);
			add (change_weapon);
			add (flee);
			add (potions);
			add (change_magic);
			add (whos_turn);
			add (current_user_health);
			add (current_enemy_health);
			add (current_usermana_label);
			add (current_enemymana_label);
			add (last_move);
			add (lastuser_move);

		    }


		    if (paint_screen == "Change_Weapon_Screen")
		    {
			add (fightbutton);
			add (weapon_selection);
			add (weapon_up);
			add (weapon_down);

		    }


		    if (paint_screen == "Magic_Selection_Screen")
		    {
			add (fightbutton);
			add (magic_up);
			add (magic_down);
			add (magic_selection);
		    }


		    if (paint_screen == "Potion_Screen")
		    {
			add (fightbutton);
			add (potion_selection);
			add (potion_up);
			add (potion_down);
			add (use_potion);
		    }

		    if (paint_screen == "Win_Screen")
		    {
			add (winner);
			add (next_move);
		    }



		    if (paint_screen.equalsIgnoreCase ("school"))
		    {
			add (Vendor);
			add (Personal);
			add (Middle);
			add (VendorGold);
			add (VendorSilver);
			add (VendorCopper);
			add (PersonalGold);
			add (PersonalSilver);
			add (PersonalCopper);
			add (TransactionGold);
			add (TransactionSilver);
			add (TransactionCopper);
			add (BUY);
			add (SELL);
			add (CONFIRM);
			add (REMOVE);
		    }
		    if (paint_screen.equalsIgnoreCase ("blacksmith"))
		    {
			add (Vendor);
			add (Personal);
			add (Middle);
			add (VendorGold);
			add (VendorSilver);
			add (VendorCopper);
			add (PersonalGold);
			add (PersonalSilver);
			add (PersonalCopper);
			add (TransactionGold);
			add (TransactionSilver);
			add (TransactionCopper);
			add (BUY);
			add (SELL);
			add (CONFIRM);
			add (REMOVE);
		    }
		    if (paint_screen.equalsIgnoreCase ("store"))
		    {
			add (Vendor);
			add (Personal);
			add (Middle);
			add (VendorGold);
			add (VendorSilver);
			add (VendorCopper);
			add (PersonalGold);
			add (PersonalSilver);
			add (PersonalCopper);
			add (TransactionGold);
			add (TransactionSilver);
			add (TransactionCopper);
			add (BUY);
			add (SELL);
			add (CONFIRM);
			add (REMOVE);
		    }

		    LIST.clear ();
		    remove (LIST);
		    remove (information);
		    remove (info);
		    remove (drop);
		    repaint ();
		} // if b
		if (c == 's' | c == 'S')
		{

		    LIST.clear ();

		    remove (information);
		    remove (info);
		    remove (drop);
		    stats ();
		    repaint ();
		} //stats
	    } // inventory
	    if (paint_screen.equalsIgnoreCase ("save screen")) //inventory screen
	    {
		if (c == 'b' | c == 'B')
		{

		    paint_screen = pre_paint;
		    if (paint_screen.equalsIgnoreCase ("general_Screen"))
		    {
			add (left);
			add (right);
			add (forward);
			add (down);
			add (special);
			add (fight);
		    }
		    if (paint_screen.equalsIgnoreCase ("Fight_Screen"))
		    {
			add (attack);
			add (spell);
			add (defend);
			add (change_weapon);
			add (flee);
			add (potions);
			add (change_magic);
			add (whos_turn);
			add (current_user_health);
			add (current_enemy_health);
			add (current_usermana_label);
			add (current_enemymana_label);
			add (last_move);
			add (lastuser_move);

		    }


		    if (paint_screen == "Change_Weapon_Screen")
		    {
			add (fightbutton);
			add (weapon_selection);
			add (weapon_up);
			add (weapon_down);

		    }


		    if (paint_screen == "Magic_Selection_Screen")
		    {
			add (fightbutton);
			add (magic_up);
			add (magic_down);
			add (magic_selection);
		    }


		    if (paint_screen == "Potion_Screen")
		    {
			add (fightbutton);
			add (potion_selection);
			add (potion_up);
			add (potion_down);
			add (use_potion);
		    }

		    if (paint_screen == "Win_Screen")
		    {
			add (winner);
			add (next_move);
		    }



		    if (paint_screen.equalsIgnoreCase ("school"))
		    {
			add (Vendor);
			add (Personal);
			add (Middle);
			add (VendorGold);
			add (VendorSilver);
			add (VendorCopper);
			add (PersonalGold);
			add (PersonalSilver);
			add (PersonalCopper);
			add (TransactionGold);
			add (TransactionSilver);
			add (TransactionCopper);
			add (BUY);
			add (SELL);
			add (CONFIRM);
			add (REMOVE);
		    }
		    if (paint_screen.equalsIgnoreCase ("blacksmith"))
		    {
			add (Vendor);
			add (Personal);
			add (Middle);
			add (VendorGold);
			add (VendorSilver);
			add (VendorCopper);
			add (PersonalGold);
			add (PersonalSilver);
			add (PersonalCopper);
			add (TransactionGold);
			add (TransactionSilver);
			add (TransactionCopper);
			add (BUY);
			add (SELL);
			add (CONFIRM);
			add (REMOVE);
		    }
		    if (paint_screen.equalsIgnoreCase ("store"))
		    {
			add (Vendor);
			add (Personal);
			add (Middle);
			add (VendorGold);
			add (VendorSilver);
			add (VendorCopper);
			add (PersonalGold);
			add (PersonalSilver);
			add (PersonalCopper);
			add (TransactionGold);
			add (TransactionSilver);
			add (TransactionCopper);
			add (BUY);
			add (SELL);
			add (CONFIRM);
			add (REMOVE);
		    }

		    LIST.clear ();
		    remove (list);
		    remove (information);
		    remove (info);
		    remove (drop);
		    repaint ();
		} // if b
		if (c == 's' | c == 'S')
		{

		    LIST.clear ();
		    remove (list);
		    remove (information);
		    remove (info);
		    remove (drop);
		    stats ();
		    repaint ();
		} //stats
	    } // save
	    if (paint_screen.equalsIgnoreCase ("stats screen")) //stats screen
	    {
		if (c == 'b' | c == 'B')
		{
		    paint_screen = pre_paint;
		    if (paint_screen.equalsIgnoreCase ("general_Screen"))
		    {
			add (left);
			add (right);
			add (forward);
			add (down);
			add (special);
			add (fight);
		    }
		    if (paint_screen.equalsIgnoreCase ("Fight_Screen"))
		    {
			add (attack);
			add (spell);
			add (defend);
			add (change_weapon);
			add (flee);
			add (potions);
			add (change_magic);
			add (whos_turn);
			add (current_user_health);
			add (current_enemy_health);
			add (current_usermana_label);
			add (current_enemymana_label);
			add (last_move);
			add (lastuser_move);

		    }


		    if (paint_screen == "Change_Weapon_Screen")
		    {
			add (fightbutton);
			add (weapon_selection);
			add (weapon_up);
			add (weapon_down);

		    }


		    if (paint_screen == "Magic_Selection_Screen")
		    {
			add (fightbutton);
			add (magic_up);
			add (magic_down);
			add (magic_selection);
		    }


		    if (paint_screen == "Potion_Screen")
		    {
			add (fightbutton);
			add (potion_selection);
			add (potion_up);
			add (potion_down);
			add (use_potion);
		    }

		    if (paint_screen == "Win_Screen")
		    {
			add (winner);
			add (next_move);
		    }



		    if (paint_screen.equalsIgnoreCase ("school"))
		    {
			add (Vendor);
			add (Personal);
			add (Middle);
			add (VendorGold);
			add (VendorSilver);
			add (VendorCopper);
			add (PersonalGold);
			add (PersonalSilver);
			add (PersonalCopper);
			add (TransactionGold);
			add (TransactionSilver);
			add (TransactionCopper);
			add (BUY);
			add (SELL);
			add (CONFIRM);
			add (REMOVE);
		    }
		    if (paint_screen.equalsIgnoreCase ("blacksmith"))
		    {
			add (Vendor);
			add (Personal);
			add (Middle);
			add (VendorGold);
			add (VendorSilver);
			add (VendorCopper);
			add (PersonalGold);
			add (PersonalSilver);
			add (PersonalCopper);
			add (TransactionGold);
			add (TransactionSilver);
			add (TransactionCopper);
			add (BUY);
			add (SELL);
			add (CONFIRM);
			add (REMOVE);
		    }
		    if (paint_screen.equalsIgnoreCase ("store"))
		    {
			add (Vendor);
			add (Personal);
			add (Middle);
			add (VendorGold);
			add (VendorSilver);
			add (VendorCopper);
			add (PersonalGold);
			add (PersonalSilver);
			add (PersonalCopper);
			add (TransactionGold);
			add (TransactionSilver);
			add (TransactionCopper);
			add (BUY);
			add (SELL);
			add (CONFIRM);
			add (REMOVE);
		    }
		    if (paint_screen.equalsIgnoreCase ("inventory screen"))
		    {

			inventory ();
		    }

		    repaint ();
		}
	    }
	}


	else if (paint_screen.equalsIgnoreCase ("general_Screen"))
	{

	}


	else
	{
	    try
	    {
		errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		out = new PrintWriter (new FileWriter (errorLog));
		out.println ("Error, line 625");
		out.close ();
	    }
	    catch (IOException f)
	    {
	    }

	}
    }




    //key pressed
    public void keyPressed (KeyEvent e)
    {

    }




    public void changescreen (ActionEvent e)  //removes appropiate buttons when a new screen is being called
    {
	if (e.getSource () == change_weapon || e.getSource () == change_magic || e.getSource () == potions || e.getSource () == flee)
	{
	    remove (attack);
	    remove (spell);
	    remove (defend);
	    remove (change_weapon);
	    remove (flee);
	    remove (potions);
	    remove (change_magic);
	    remove (whos_turn);
	    remove (current_user_health);
	    remove (current_enemy_health);
	    remove (current_usermana_label);
	    remove (current_enemymana_label);
	    remove (last_move);
	    remove (lastuser_move);
	    setScreen ();
	}


	else if (e.getSource () == fightbutton || e.getSource () == use_potion)
	{
	    remove (fightbutton);
	    remove (weapon_selection);
	    remove (weapon_up);
	    remove (weapon_down);
	    remove (magic_up);
	    remove (magic_down);
	    remove (magic_selection);
	    remove (potion_up);
	    remove (potion_down);
	    remove (potion_selection);
	    remove (use_potion);

	    paint_screen = "Fight_Screen";

	    setScreen ();
	}


	else if (e.getSource () == next_move)
	{
	    remove (next_move);
	    remove (winner);
	    remove (use_potion);

	    paint_screen = "General_Screen";

	    add (left);
	    add (right);
	    add (forward);
	    add (down);
	    add (special);
	    add (fight);
	    setScreen ();
	}


	else
	{
	    try
	    {
		errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		out = new PrintWriter (new FileWriter (errorLog));
		out.println ("Error, line 897");
		out.close ();
	    }
	    catch (IOException f)
	    {
	    }
	}
    } //end of changescreen


    public void move ()  //a method that performs the moves of the user
    {
	if (userattack == true) //if the selected move was attack
	{
	    if (enemy_move >= 4) //if the enemy move is defend, then damage is halved
	    {
		enemydefend = true;
		current_user_damage = (current_user_damage / 2);
		latestmove = "defend";
	    }

	    current_enemy_Hp = (current_enemy_Hp - current_user_damage); //enemy Hp takeaway how much damage has been done
	    current_enemy_healthbar = current_enemy_Hp; //sets value to the current health bar of enemy
	    current_enemy_damage = enemy_damage; //resets damage of enemy in case last user move being defend
	    latestusermove = "attack"; //used to show the last move performed move on screen

	    enemy_moves (); //calls move for the enemy
	}


	else if (userdefend == true) //if the selected move was defend then next enemy damage is halved
	{
	    if (enemy_move >= 4) //if the enemies move is defend
	    {
		enemydefend = true;
		current_user_damage = (current_user_damage / 2);
		latestmove = "defend";
	    }
	    current_enemy_damage = (enemy_damage / 2); //sets enemy damage to be half of what is supposed to be
	    latestusermove = "defend"; //shows last user move on screen

	    enemy_moves (); //calls method for enemy move
	}


	else if (userspell == true) //if the selected move for user was cast spell
	{
	    if (enemy_move >= 4) //if the enemies move is defend, then damage is halved
	    {
		enemydefend = true;
		current_user_damage = (current_user_damage / 2);
		latestmove = "defend";
	    }

	    current_enemy_Hp = (current_enemy_Hp - current_user_magic_damage); //enemy hp is now enemy hp subtract magic damage
	    current_enemy_healthbar = current_enemy_Hp;

	    current_enemy_magic_damage = enemy_magic_damage; //resets magic damage in case of users previous move being defend
	    current_user_mana = current_user_mana - spellcost; //subtracts the value of spell cost from the mana bar
	    latestusermove = "spell"; //puts latest move on screen

	    enemy_moves (); //calls method for enemy move
	}


	else if (user_potion == true) //if the user decideds to use a potion then,
	{
	    if (current_user_Hp < userHp && current_potion == "Health Potion") //if the potion is a health potion then add health to the user and update the health bar
	    {
		current_user_Hp = current_user_Hp + health_potion;
		current_user_healthbar = current_user_Hp;
	    }
	    else if (current_user_mana < user_mana && current_potion == "Mana Potion") //if the potion is a mana potion then add mana to the user and update the mana bar
	    {
		current_user_mana = current_user_mana + mana_potion;
		current_user_manabar = current_user_mana;
	    }

	    current_user_health.setText (current_user_Hp + "/" + userHp); //sets the label for health under the user health bar
	    latestusermove = "potion"; //puts latest move on screen

	    enemy_moves (); //calls method for enemy move
	}


	else
	{
	    enemy_moves (); //calls method for enemy move
	}


	current_enemy_health.setText (current_enemy_Hp + "/" + enemyHp); //sets the label for health under the user health bar
	current_usermana_label.setText (current_user_mana + "/" + user_mana); //sets the label for mana under the mana bar for user

	if (current_enemy_Hp <= 0) //if the enemy has no health, then user wins the battle, takes user to win screen
	{
	    winner.setText ("YOU HAVE WON THE BATTLE!");
	    Gold = Gold + (int) (Math.random () * (1 + (lvl / 2)));
	    Silver = Silver + (int) (Math.random () * (3 + (lvl / 2)));
	    Copper = Copper + (int) (Math.random () * (5 + (lvl / 2)));
	    EXP = EXP + enemy_lvl * 100;
	    if (EXP >= lvl * 500)
	    {
		lvl++;
	    }
	    paint_screen = "Win_Screen";
	    if (Clan == "AIR")
		map [x] [y] [2] = 0;
	    else if (Clan == "FIRE")
		map [x] [y] [2] = 1;
	    else if (Clan == "WATER")
		map [x] [y] [2] = 2;
	    else if (Clan == "EARTH")
		map [x] [y] [2] = 3;

	}


	else if (current_user_Hp <= 0) //if the user has no health, then the enemy wins the battle
	{
	    winner.setText ("ENEMY HAS WON THE BATTLE!");

	    paint_screen = "Win_Screen";
	}


	else
	{
	    try
	    {
		errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		out = new PrintWriter (new FileWriter (errorLog));
		out.println ("Error, line 625");
		out.close ();
	    }
	    catch (IOException f)
	    {
	    }
	}
    } //end of move method





    public void enemy_moves ()  //a method that randomly selects the move the opponent will be making
    {
	if (turn_counter == false) //if its not the users turn
	{
	    if (enemy_move < 4 && enemy_move != 0)
	    {
		current_user_Hp = (current_user_Hp - current_enemy_damage);
		current_user_healthbar = current_user_Hp;
		current_user_damage = user_damage;
		latestmove = "attack";
	    }
	    else if (enemy_move == 0)
	    {
		current_user_Hp = (current_user_Hp - current_enemy_magic_damage);
		current_user_healthbar = current_user_Hp;
		current_enemy_mana = current_enemy_mana - spellcost;
		latestmove = "cast spell";
	    }
	    current_user_health.setText (current_user_Hp + "/" + userHp);
	    current_enemymana_label.setText (current_enemy_mana + "/" + enemy_mana);
	    last_move.setText ("Enemies move was " + latestmove);
	    lastuser_move.setText ("Your last move was " + latestusermove);


	    turn_counter = true;
	}


	else
	{
	    try
	    {
		errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		out = new PrintWriter (new FileWriter (errorLog));
		out.println ("Error, line 625");
		out.close ();
	    }
	    catch (IOException f)
	    {
	    }
	}
    } //end of enemy_moves method


    public void init ()
    {
	new Sizing_Class (); //resize class

	new Cipher (); //class for encryption

	information.setEditable (false);

	//Periodic.start ();
	for (int i = 0 ; i < 100 ; i++)
	{
	    Inventory [i] [0] = "";
	    Inventory [i] [1] = "0";
	    Prices [i] [0] = "";
	    Prices [i] [1] = "0";
	    Prices [i] [2] = "0";
	} //for i


	//proceed button on main menu
	proceed = new Button ("Proceed");
	proceed.setFont (new Font ("Dialog", Font.BOLD, 36));
	proceed.setForeground (Color.white);
	proceed.setBackground (Color.black);
	addKeyListener (this);
	Prices [0] [3] = "W5"; //W
	Prices [1] [3] = "R5"; //R
	Prices [2] [3] = "R10"; //R
	Prices [3] [3] = "R20"; //R
	Prices [4] [3] = "W15"; //W
	Prices [5] [3] = "W20"; //W
	Prices [6] [3] = "M5"; //M
	Prices [7] [3] = "M10"; //M
	Prices [8] [3] = "M15"; //M
	Prices [9] [3] = "M20"; //M
	Prices [10] [3] = "S10"; //S           // nico
	Prices [11] [3] = "S20"; //S
	Prices [12] [3] = "S30"; //S
	Prices [13] [3] = "S40"; //S
	Prices [14] [3] = "P15"; //P
	Prices [15] [3] = "P20"; //P
	Prices [16] [3] = "A20"; //A
	Prices [17] [3] = "NA5"; //NA
	Prices [18] [3] = "NA10"; //NA
	Prices [19] [3] = "NA20"; //NA
	add (proceed); //adds proceed button

	//gets background image
	image = getImage (getDocumentBase (), "Images\\Battle_of_Waterloo_1815.png");

	//gets music files
	clip = getAudioClip (getCodeBase (), "Sounds\\themeSong.wav");

	clip2 = getAudioClip (getCodeBase (), "Sounds\\hello.wav");

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


    public void Main_Screen ()  // danny
    {
	img_Main_Screen = getImage (getDocumentBase (), "Images\\Main_Screen.png");
	//add buttons -->
	add (cmd_Play);
	add (cmd_Create);
	add (cmd_Delete);
	add (lst_Worlds);
	add (lst_Characters);
	add (cmd_Reset);
	repaint ();
	//strengths for items
	Prices [0] [0] = "           Mace";
	Prices [1] [0] = "            Bow";
	Prices [2] [0] = "          Arrow";
	Prices [3] [0] = "      Cross-Bow";
	Prices [4] [0] = "          Flail";
	Prices [5] [0] = "          Sword";
	Prices [6] [0] = "     Fire-Staff";
	Prices [7] [0] = "      Air-Staff";
	Prices [8] [0] = "    Water-Staff";
	Prices [9] [0] = "    Earth-Staff";
	Prices [10] [0] = " Leather-Shield";
	Prices [11] [0] = "    Iron-Shield";
	Prices [12] [0] = "   Steel-Shield";
	Prices [13] [0] = "    Iron-Armour";
	Prices [14] [0] = "  Health Potion";
	Prices [15] [0] = "    Mana Potion";
	Prices [16] [0] = " Leather-Armour";
	Prices [17] [0] = "           Fish";
	Prices [18] [0] = "      Bear Skin";
	Prices [19] [0] = "      Dear Skin";
	Prices [20] [0] = "           Gold";
	Prices [21] [0] = "           iron";
	Prices [22] [0] = "         Copper";
	Prices [23] [0] = "           Wood";
	// set price index <-- [names]

	//TESTING <--
	// gold = 100 silver = 10   copper = 1

	//set price index values per item -->
	Prices [0] [1] = "150";
	Prices [1] [1] = "200"; // two gold or 20 silver or 200 copper
	Prices [2] [1] = "5";
	Prices [3] [1] = "250";
	Prices [4] [1] = "225";
	Prices [5] [1] = "100";
	Prices [6] [1] = "500";
	Prices [7] [1] = "500";
	Prices [8] [1] = "500";
	Prices [9] [1] = "500";
	Prices [10] [1] = "75";
	Prices [11] [1] = "125";
	Prices [12] [1] = "175";
	Prices [13] [1] = "250";
	Prices [14] [1] = "75";
	Prices [15] [1] = "75";
	Prices [16] [1] = "125";
	Prices [17] [1] = "10";
	Prices [18] [1] = "50";
	Prices [19] [1] = "25";
	Prices [20] [1] = "75";
	Prices [21] [1] = "15";
	Prices [22] [1] = "5";
	Prices [23] [1] = "5";
	Prices [20] [2] = "4";
	Prices [21] [2] = "2";
	Prices [22] [2] = "1";
	Prices [23] [2] = "1";

	// level requirements for price index -->
	Prices [0] [2] = "4";
	Prices [1] [2] = "3"; // level requirements
	Prices [2] [2] = "3";
	Prices [3] [2] = "4";
	Prices [4] [2] = "5";
	Prices [5] [2] = "1";
	Prices [6] [2] = "7";
	Prices [7] [2] = "7";
	Prices [8] [2] = "7";
	Prices [9] [2] = "7";
	Prices [10] [2] = "1";
	Prices [11] [2] = "3";
	Prices [12] [2] = "5";
	Prices [13] [2] = "5";
	Prices [14] [2] = "1";
	Prices [15] [2] = "1";
	Prices [16] [2] = "2";
	Prices [17] [2] = "0";
	Prices [18] [2] = "0";
	Prices [19] [2] = "0";
	Prices [20] [2] = "0";
	Prices [21] [2] = "0";
	Prices [22] [2] = "0";
	Prices [23] [2] = "0";
	Prices [0] [3] = "W05"; //W
	Prices [1] [3] = "R05"; //R
	Prices [2] [3] = "R10"; //R
	Prices [3] [3] = "R20"; //R
	Prices [4] [3] = "W15"; //W
	Prices [5] [3] = "W20"; //W
	Prices [6] [3] = "M05"; //M
	Prices [7] [3] = "M10"; //M
	Prices [8] [3] = "M15"; //M
	Prices [9] [3] = "M20"; //M
	Prices [10] [3] = "S10"; //S
	Prices [11] [3] = "S20"; //S
	Prices [12] [3] = "S30"; //S
	Prices [13] [3] = "S40"; //S
	Prices [14] [3] = "P15"; //P
	Prices [15] [3] = "P20"; //P
	Prices [16] [3] = "A20"; //A
	Prices [17] [3] = "N05"; //NA
	Prices [18] [3] = "N10"; //NA
	Prices [19] [3] = "N20"; //NA
	Prices [20] [1] = "N20";
	Prices [21] [1] = "N20";
	Prices [22] [1] = "N20";
	Prices [23] [1] = "N20";

	LIST.setFont (f); //sets LIST font
	try
	{
	    errorLog.createNewFile ();
	}


	catch (IOException f)
	{

	}


	//sets button font
	info.setFont (f);

	//sets textArea font
	information.setFont (new Font ("Dialog", Font.BOLD, 22));
	// add buttons <--
	paint_screen = "Creating_Worlds";
	repaint ();
	CheckWorlds ();
	// iterate through possible worlds

	File folder = new File ("GameFiles\\Users\\" + name + "\\Characters");
	File[] listOfFiles = folder.listFiles ();
	if (folder.exists ())
	{
	    for (int i = 0 ; i < listOfFiles.length ; i++) // for all files in folder
	    {
		if (listOfFiles [i].isDirectory ())
		{
		    lst_Characters.add (listOfFiles [i].getName ());

		}
	    }
	} // if


	else
	{
	    try
	    {
		errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		out = new PrintWriter (new FileWriter (errorLog));
		out.println ("Error, line 625");
		out.close ();
	    }
	    catch (IOException f)
	    {
	    }
	}


	// iterate through possible worlds

	// iterate through characters

	File folder2 = new File ("GameFiles\\Users\\" + name + "\\Worlds");

	File[] listOfFiles2 = folder2.listFiles ();
	if (folder2.exists ())
	{
	    for (int i = 0 ; i < listOfFiles2.length ; i++) // for all files in folder
	    {
		if (listOfFiles2 [i].isFile ())
		{
		    if (listOfFiles2 [i].getName ().charAt (9) == ('N')) // if info file dont write
		    {
			try
			{
			    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
			    out = new PrintWriter (new FileWriter (errorLog));
			    out.println ("Error, line 1284");
			    out.close ();
			}
			catch (IOException f)
			{
			}

		    }
		    else
		    {
			String World_Name = "";
			World_Name = "" + listOfFiles2 [i].getName ().charAt (0) + listOfFiles2 [i].getName ().charAt (1) + listOfFiles2 [i].getName ().charAt (2) + listOfFiles2 [i].getName ().charAt (3) + listOfFiles2 [i].getName ().charAt (4) + listOfFiles2 [i].getName ().charAt (5) + listOfFiles2 [i].getName ().charAt (6);
			lst_Worlds.add (World_Name);
		    }

		}
	    }
	} // if


	// iterate through characters


    } // main screen


    public void read_World (File file)
    {

	try
	{

	    in = new BufferedReader (new FileReader (file));

	    for (int d = 0 ; d < 3 ; d++) // two layers
	    {

		for (int y = 0 ; y < 300 ; y++) // y
		{

		    for (int c = 0 ; c < 3 ; c++) // 3 lines per 1 decrypted line
		    {

			ln1 = in.readLine ();


			for (int i = 0 ; i < 800 ; i += 8) // x
			{

			    convert = "" + ln1.charAt (i) + ln1.charAt (i + 1) + ln1.charAt (i + 2) + ln1.charAt (i + 3) + ln1.charAt (i + 4) + ln1.charAt (i + 5) + ln1.charAt (i + 6) + ln1.charAt (i + 7); // get 8 bits to make up an encrypted 2 byte number code

			    map [i / 8 + (c * 100)] [y] [d] = Integer.parseInt (Cipher.decrypt (convert, 2));
			    convert = "";
			} // for i
		    } // c

		} // for y

	    } // for d

	    in.close ();
	} //try


	catch (IOException e)
	{
	    try
	    {
		errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		out = new PrintWriter (new FileWriter (errorLog));
		out.println ("Error, line 1350");
		out.close ();
	    }
	    catch (IOException f)
	    {
	    }

	} // catch
    } // read the world file for game


    private void World_Creation (File location)  // create and output the world
    {
	// world generation -->

	for (int b = 0 ; b < 300 ; b++) // for y-axis of map
	{
	    for (int i = 0 ; i < 300 ; i++) // for x-axis of map
	    {
		map [i] [b] [0] = 15; // plains

		map [i] [b] [1] = (int) (Math.random () * 10); // population
		map [i] [b] [2] = (int) (Math.random () * 4) + 1; // clans 1 - 4 (air, water, earth, fire)

	    } // x-axis of map


	} // for y-axis of map





	// set entire world to plains <--



	for (int b = 0 ; b < 300 ; b++) // for y-axis of map
	{
	    for (int i = 0 ; i < 300 ; i++) // for x-axis of map
	    {
		if ((int) (Math.random () * 12) + 1 == 1)
		{

		    map [i] [b] [0] = 17; // forest
		    map [i] [b] [1] = (int) (Math.random () * 30); // population
		} // if 1 - 100 == 1
		else
		{
		    try
		    {
			errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
			out = new PrintWriter (new FileWriter (errorLog));
			out.println ("Error, line 1404");
			out.close ();
		    }
		    catch (IOException f)
		    {
		    }
		}

	    } // x-axis of map


	} // for y-axis of map




	// add forest to map <--




	for (int b = 0 ; b < 300 ; b++) // for y-axis of map
	{
	    for (int i = 0 ; i < 300 ; i++) // for x-axis of map
	    {
		if ((int) (Math.random () * 35) + 1 == 1)
		{

		    map [i] [b] [0] = 18; // mine
		    map [i] [b] [1] = (int) (Math.random () * 15); // population
		} // if 1 - 100 == 1
		else
		{
		    try
		    {
			errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
			out = new PrintWriter (new FileWriter (errorLog));
			out.println ("Error, line 1441");
			out.close ();
		    }
		    catch (IOException f)
		    {
		    }
		}

	    } // x-axis of map


	} // for y-axis of map




	// add mines to map





	for (int b = 0 ; b < 300 ; b++) // for y-axis of map
	{
	    for (int i = 0 ; i < 300 ; i++) // for x-axis of map
	    {
		if ((int) (Math.random () * 55) + 1 == 1)
		{

		    map [i] [b] [0] = 20; // Lake
		    map [i] [b] [1] = (int) (Math.random () * 4); // population
		} // if 1 - 100 == 1
		else
		{
		    try
		    {
			errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
			out = new PrintWriter (new FileWriter (errorLog));
			out.println ("Error, line 1479");
			out.close ();
		    }
		    catch (IOException f)
		    {
		    }
		}

	    } // x-axis of map


	} // for y-axis of map





	// add lakes to map




	for (int b = 0 ; b < 300 ; b++) // for y-axis of map
	{
	    for (int i = 0 ; i < 300 ; i++) // for x-axis of map
	    {
		if ((int) (Math.random () * 40) + 1 == 1)
		{

		    map [i] [b] [0] = 19; // mountain
		    map [i] [b] [1] = (int) (Math.random () * 50) + 1; // population
		} // if 1 - 100 == 1
		else
		{
		    try
		    {
			errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
			out = new PrintWriter (new FileWriter (errorLog));
			out.println ("Error, line 625");
			out.close ();
		    }
		    catch (IOException f)
		    {
		    }
		}

	    } // x-axis of map


	} // for y-axis of map





	// add mountains to map    < --



	// manual add castles to map -- >



	for (int x = 1 ; x < 25 ; x++)
	{
	    for (int y = 1 ; y < 25 ; y++)         // castle 1 castle grounds
	    {
		map [x] [y] [0] = 41;
	    } // y-axis of castle
	} // x-axis of castle


	for (int x = 0 ; x < 26 ; x++)
	{
	    map [x] [0] [0] = 21;
	} // catle wall castle one top


	for (int x = 0 ; x < 26 ; x++)
	{
	    map [x] [25] [0] = 21;
	} // catle wall castle one bottom


	for (int y = 0 ; y < 26 ; y++)
	{
	    map [0] [y] [0] = 21;
	} // catle wall castle one left


	for (int y = 0 ; y < 26 ; y++)
	{
	    map [25] [y] [0] = 21;
	} // catle wall castle one right


	map [25] [12] [0] = 31; // castle gate
	map [2] [2] [0] = 10; // blacksmith
	map [3] [2] [0] = 11; // store
	map [4] [2] [0] = 12; // school



	for (int x = 276 ; x < 300 ; x++)
	{
	    for (int y = 1 ; y < 25 ; y++)         // castle 2 castle grounds
	    {
		map [x] [y] [0] = 42;
	    } // y-axis of castle
	} // x-axis of castle


	for (int x = 275 ; x < 300 ; x++)
	{
	    map [x] [0] [0] = 22;
	} // catle wall castle two top


	for (int x = 275 ; x < 300 ; x++)
	{
	    map [x] [25] [0] = 22;
	} // catle wall castle two bottom


	for (int y = 0 ; y < 26 ; y++)
	{
	    map [275] [y] [0] = 22;
	} // catle wall castle two left


	for (int y = 0 ; y < 26 ; y++)
	{
	    map [299] [y] [0] = 22;
	} // catle wall castle two right


	map [275] [12] [0] = 32; // castle gate

	map [298] [2] [0] = 10; // blacksmith
	map [297] [2] [0] = 11; // store
	map [296] [2] [0] = 12; //school



	for (int x = 1 ; x < 25 ; x++)
	{
	    for (int y = 276 ; y < 299 ; y++)        // castle 3 castle grounds
	    {
		map [x] [y] [0] = 43;
	    } // y-axis of castle
	} // x-axis of castle


	for (int x = 0 ; x < 26 ; x++)
	{
	    map [x] [275] [0] = 23;
	} // catle wall castle three top


	for (int x = 0 ; x < 26 ; x++)
	{
	    map [x] [299] [0] = 23;
	} // catle wall castle three bottom


	for (int y = 275 ; y < 299 ; y++)
	{
	    map [0] [y] [0] = 23;
	} // catle wall castle three left


	for (int y = 275 ; y < 299 ; y++)
	{
	    map [25] [y] [0] = 23;
	} // catle wall castle three right


	map [25] [292] [0] = 33; // castle gate
	map [2] [298] [0] = 10; // blacksmith
	map [3] [298] [0] = 11; // store
	map [4] [298] [0] = 12; // school



	for (int x = 276 ; x < 299 ; x++)
	{
	    for (int y = 276 ; y < 299 ; y++)         // castle 4 castle grounds
	    {
		map [x] [y] [0] = 44;
	    } // y-axis of castle
	} // x-axis of castle


	for (int x = 275 ; x < 300 ; x++)
	{
	    map [x] [275] [0] = 24;
	} // catle wall castle four top


	for (int x = 275 ; x < 300 ; x++)
	{
	    map [x] [299] [0] = 24;
	} // catle wall castle four bottom


	for (int y = 275 ; y < 299 ; y++)
	{
	    map [275] [y] [0] = 24;
	} // catle wall castle four left


	for (int y = 275 ; y < 299 ; y++)
	{
	    map [299] [y] [0] = 24;
	} // catle wall castle four right


	map [275] [292] [0] = 34; // castle gate
	map [298] [298] [0] = 11; // store
	map [297] [298] [0] = 10; // blacksmith
	map [296] [298] [0] = 12; // school

	// manual add castles to map <--



	// castle populations  -- >
	try
	{

	    File outline = new File ("GameFiles\\Users\\" + name + "\\Worlds\\World " + worlds + "_INFO.CWF");

	    if (outline.exists ())
	    {
		outline.delete ();
	    }
	    out = new PrintWriter (new FileWriter ("GameFiles\\Users\\" + name + "\\Worlds\\World " + worlds + "_INFO.CWF"));

	    out.println (Cipher.encrypt ("149149", 2)); // player coordinates
	    out.println (Cipher.encrypt ("" + (int) (Math.random () * 3000) + 1000, 2)); // castle 1 pop
	    out.println (Cipher.encrypt ("" + (int) (Math.random () * 3000) + 1000, 2)); // castle 2 pop
	    out.println (Cipher.encrypt ("" + (int) (Math.random () * 3000) + 1000, 2)); // castle 3 pop
	    out.println (Cipher.encrypt ("" + (int) (Math.random () * 3000) + 1000, 2)); // castle 4 pop
	    out.close ();
	}


	catch (IOException e)
	{

	}


	// castle populations <--

	try // output world
	{
	    out = new PrintWriter (new FileWriter (location));

	    for (int c = 0 ; c < 3 ; c++)
	    {
		for (int b = 0 ; b < 300 ; b++) // for y-axis of map
		{
		    for (int z = 0 ; z < 3 ; z++)
		    {
			for (int i = 0 ; i < 100 ; i++) // for x-axis of map
			{
			    out.print (Cipher.encrypt ("" + formatter.format (map [i + (z * 100)] [b] [c]), 2));

			} // x-axis of map
			out.println ();
		    } // for 3 rounds of output per line

		} // for y-axis of map

	    } // z-axis of map



	    // output world <--

	    out.close ();
	} // try


	catch (IOException e)
	{

	} // catch


	// world generation <--


	// clear map array
	for (int c = 0 ; c < 2 ; c++)
	{
	    for (int b = 0 ; b < 300 ; b++) // for y-axis of map
	    {
		for (int i = 0 ; i < 300 ; i++) // for x-axis of map
		{
		    map [i] [b] [c] = 0;
		} // int i
	    } // int b
	} // int c


	// clear map array

    } // World creation method


    public void CheckWorlds ()  // check if all world files exist
    {
	Graphics g = getGraphics ();


	File temp = new File ("GameFiles\\Users\\" + name + "\\Worlds\\World 1.CWF");
	if (!temp.exists ())
	{
	    worlds = 1;
	    World_Creation (temp);
	} //if 1


	else
	{
	    try
	    {
		errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		out = new PrintWriter (new FileWriter (errorLog));
		out.println ("Error, line 1769");
		out.close ();
	    }
	    catch (IOException f)
	    {
	    }
	}


	Z = 0;
	paint (g);

	temp = new File ("GameFiles\\Users\\" + name + "\\Worlds\\World 2.CWF");
	if (!temp.exists ())
	{
	    worlds = 2;
	    World_Creation (temp);
	} // if 2


	else
	{
	    try
	    {
		errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		out = new PrintWriter (new FileWriter (errorLog));
		out.println ("Error, line 1791");
		out.close ();
	    }
	    catch (IOException f)
	    {
	    }
	}


	Z = 1;
	paint (g);
	temp = new File ("GameFiles\\Users\\" + name + "\\Worlds\\World 3.CWF");
	if (!temp.exists ())
	{
	    worlds = 3;
	    World_Creation (temp);
	} // if 3


	else
	{
	    try
	    {
		errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		out = new PrintWriter (new FileWriter (errorLog));
		out.println ("Error, line 1812");
		out.close ();
	    }
	    catch (IOException f)
	    {
	    }
	}


	Z = 2;
	paint (g);

	paint_screen = "USER";
    } // Check Worlds method



    public void actionPerformed (ActionEvent e)
    {
	if (e.getSource () == change_weapon) //if change_weapon button is pressed then current screen is changed to weapon selection screen
	{
	    paint_screen = "Change_Weapon_Screen";
	    changescreen (e);
	    repaint ();
	}


	else if (e.getSource () == change_magic) //if change_magic button is pressed then current screen is changed to magic selection screen
	{
	    paint_screen = "Magic_Selection_Screen";
	    changescreen (e);
	    repaint ();
	}


	else if (e.getSource () == potions) //if potions button is pressed then current screen is changed to potion screen
	{
	    paint_screen = "Potion_Screen";
	    changescreen (e);
	    repaint ();
	}


	else if (e.getSource () == flee) //if flee button is pressed then change current screen to the general screen
	{
	    paint_screen = "General_Screen";
	    changescreen (e);
	    repaint ();
	}


	else if (e.getSource () == fightbutton) //if the fight button is pressed within one of the subscreens then go back to fight screen
	{
	    paint_screen = "Fight_Screen";
	    changescreen (e);
	    repaint ();
	}


	else if (e.getSource () == next_move) //if next move is hit on win screen go to general screen
	{
	    paint_screen = "General_Screen";
	    changescreen (e);
	    repaint ();
	}


	// attacking************************************************
	else if (e.getSource () == attack) //if attack button is pressed then user attack will be executed
	{
	    if (turn_counter == true) //if it is the users turn then they may attack
	    {
		current_user_damage = user_damage; //resets current damage incase of last enemy move being defend
		userattack = true; //sets attack to true and everything else to false
		userdefend = false;
		userspell = false;
		user_potion = false;

		turn_counter = false; //sets users turn to false since they just took their turn

		move (); //calls move method which performs users move
		repaint (); //redraws canvas

		whos_turn.setText ("It is your turn"); //tells user whos turn it is
		enemy_move = (int) (Math.random () * (5) + 1); //used to make a decision about the enemies next move
	    }
	    else
	    {
		whos_turn.setText ("It's not your turn"); //else print it is not your turn
	    }
	}


	else if (e.getSource () == spell) //if the spell button is pressed then the spell will be executed
	{
	    if (turn_counter == true && current_user_mana >= 0 + spellcost) //if its the users turn and they have enough mana
	    {
		userspell = true; //sets spell to true and everything else to false
		userattack = false;
		userdefend = false;
		user_potion = false;
		turn_counter = false;

		move (); //calls a method to perform users selected move
		repaint (); //redraws the canvas
		enemy_move = (int) (Math.random () * (5) + 1); //selects the enemies next move
	    }

	    else
	    {
		whos_turn.setText ("No mana");
	    }
	}


	else if (e.getSource () == defend) //if defend is selected as the users move
	{
	    if (turn_counter == true) //if its the users turn
	    {
		userdefend = true; //set defend to true and everything false
		userspell = false;
		userattack = false;
		user_potion = false;
		turn_counter = false;

		move (); //calls method to perform users selected move
		repaint (); //redraws canvas
		enemy_move = (int) (Math.random () * (5) + 1); //

	    }
	}


	else
	{
	    try
	    {
		errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		out = new PrintWriter (new FileWriter (errorLog));
		out.println ("Error, line 2065");
		out.close ();
	    }
	    catch (IOException f)
	    {
	    }
	}



	//*********************************************************


	if (paint_screen == "Change_Weapon_Screen")
	{
	    if (e.getSource () == weapon_up) //if weapon up is pressed then add one to weapon selection, integer is used in switch below
	    {
		Weaponselection++;

	    }
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2093");
		    out.close ();
		}
		catch (IOException f)
		{
		}
	    }


	    if (e.getSource () == weapon_down) //if weapon down is pressed then subtract one from weapon selection
	    {
		Weaponselection--;

	    }
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2114");
		    out.close ();
		}
		catch (IOException f)
		{
		}
	    }
	    String weapons[] [] = new String [100] [2];

	    for (int i = 0 ; i < 100 ; i++)
	    {
		for (int b = 0 ; b < Prices.length ; b++)
		{
		    if (Inventory [i] [0].equalsIgnoreCase (Prices [b] [0]))
		    {
			if (Prices [b] [3] != null && Prices [b] [3].charAt (0) == 'W' | Prices [b] [3].charAt (0) == 'R' | Prices [b] [3].charAt (0) == 'M')
			{

			    weapons [i] [0] = Inventory [i] [0];
			    String tmp = "";
			    tmp = "" + Prices [i] [3].charAt (1);
			    tmp = tmp + Prices [i] [3].charAt (2);
			    weapons [i] [1] = "" + Integer.parseInt (tmp);
			}
		    }
		}
	    }


	    int z = 0;
	    // if up ->

	    for (int i = z ; i < 100 ; i++)
	    {
		if (weapons [i] [0] == null)
		{

		}
		else if (weapons [i] [0].equalsIgnoreCase (""))
		{

		}
		else
		{
		    weapon_selection.setText (weapons [i] [0]); //switches to location in users array
		    weapon_image = getImage (getDocumentBase (), "Images//" + weapons [i] [0] + ".png"); //switches weapon image on screen

		    user_damage = Integer.parseInt (weapons [i] [1]);
		}
		if (i >= 100)
		{
		    z = 0;
		}
	    }
	    // if up <-
	    // if down ->
	    for (int i = z ; i > 0 ; i--)
	    {
		if (weapons [i] [0].equalsIgnoreCase ("") | weapons [i] [0].equals (null))
		{

		}
		else
		{
		    weapon_selection.setText (weapons [i] [0]); //switches to location in users array
		    weapon_image = getImage (getDocumentBase (), "Images//" + weapons [i] [0] + ".png"); //switches weapon image on screen

		    user_damage = Integer.parseInt (weapons [i] [1]);
		}
		if (i < 0)
		{
		    z = 99;
		}

	    }
	    // if down <-



	    repaint (); //redraws current screen with updated weapon image
	}


	else
	{
	    try
	    {
		errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		out = new PrintWriter (new FileWriter (errorLog));
		out.println ("Error, line 625");
		out.close ();
	    }
	    catch (IOException f)
	    {
	    }
	}


	currentweapon = Weaponselection; //sets the current weapon to the selected weapon from the switch

	if (paint_screen == "Magic_Selection_Screen")
	{
	    if (e.getSource () == magic_up) //if magic up is pressed then add one to magic selection, integer is used in switch below
	    {
		Magicselection++;
		if (Magicselection >= 5)
		    Magicselection = 0;
	    }
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2186");
		    out.close ();
		}
		catch (IOException f)
		{
		}
	    }


	    if (e.getSource () == magic_down) //if magic down is pressed then subtract one from magic selection
	    {
		Magicselection--;
		if (Magicselection < 0)
		    Magicselection = 5;
	    }
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2207");
		    out.close ();
		}
		catch (IOException f)
		{
		}
	    }


	    switch (Magicselection) //switches between current magic that can be selected and switches to the corresponding image
	    {
		case 0:
		    magic_selection.setText (user_data [5] [0]);
		    magic_image = getImage (getDocumentBase (), "Images//" + user_data [5] [0] + ".jpg ");
		    current_user_magic_damage = 15;
		    break;
		case 1:
		    magic_selection.setText (user_data [6] [0]);
		    magic_image = getImage (getDocumentBase (), "Images//" + user_data [6] [0] + ".jpg ");
		    current_user_magic_damage = 7;
		    break;
		case 2:
		    magic_selection.setText (user_data [7] [0]);
		    magic_image = getImage (getDocumentBase (), "Images//" + user_data [7] [0] + ".jpg ");
		    break;
		case 3:
		    magic_selection.setText (user_data [8] [0]);
		    magic_image = getImage (getDocumentBase (), "Images//" + user_data [8] [0] + ".jpg ");
		    break;
		case 4:
		    magic_selection.setText (user_data [9] [0]);
		    magic_image = getImage (getDocumentBase (), "Images//" + user_data [9] [0] + ".jpg ");
		    break;
	    }
	    repaint ();
	}


	else
	{
	    try
	    {
		errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		out = new PrintWriter (new FileWriter (errorLog));
		out.println ("Error, line 2253");
		out.close ();
	    }
	    catch (IOException f)
	    {
	    }
	}




	if (paint_screen.equalsIgnoreCase ("Potion_Screen"))
	{
	    if (e.getSource () == potion_up) //if potion up is pressed then add one to potion selection, integer is used in switch below
	    {
		Potionselection++;
		if (Potionselection >= 2)
		    Potionselection = 0;
	    }
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2278");
		    out.close ();
		}
		catch (IOException f)
		{
		}
	    }


	    if (e.getSource () == potion_down) //if potion down is pressed then subtract one from potion selection
	    {
		Potionselection--;
		if (Potionselection < 0)
		    Potionselection = 2;
	    }


	    switch (Potionselection) //switches between current potions that can be selected and switches to the corresponding image
	    {
		case 0:
		    potion_selection.setText (user_data [10] [0]);
		    current_potion = user_data [10] [0];
		    potion_image = getImage (getDocumentBase (), "Images//" + user_data [10] [0] + ".png ");
		    break;
		case 1:
		    potion_selection.setText (user_data [11] [0]);
		    potion_image = getImage (getDocumentBase (), "Images//" + user_data [11] [0] + ".png ");
		    current_potion = user_data [11] [0];
		    break;
	    }


	    repaint ();


	    if (e.getSource () == use_potion && current_user_Hp + 24 < userHp) //if the user has selected to use a potion, then call
	    {
		paint_screen = "Fight_Screen";
		//turn_counter = false;
		user_potion = true;
		userattack = false;
		userdefend = false;
		userspell = false;
		//enemy_move = (int) (Math.random () * (5));
		move ();
		changescreen (e);
		repaint ();

	    }
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2334");
		    out.close ();
		}
		catch (IOException f)
		{
		}
	    }
	}


	if (paint_screen == "Win_Screen" || paint_screen == "General_Screen") //if the user has won the fight or has gone back to the general screen, then remove all buttons, labels, etc.
	{
	    remove (attack);
	    remove (spell);
	    remove (defend);
	    remove (change_weapon);
	    remove (flee);
	    remove (potions);
	    remove (change_magic);
	    remove (whos_turn);
	    remove (current_user_health);
	    remove (current_enemy_health);
	    remove (current_usermana_label);
	    remove (current_enemymana_label);
	    remove (last_move);
	    remove (lastuser_move);

	    setScreen ();

	    General_Screen ();
	}


	else
	{
	    try
	    {
		errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		out = new PrintWriter (new FileWriter (errorLog));
		out.println ("Error, line 625");
		out.close ();
	    }
	    catch (IOException f)
	    {
	    }
	}


	if (paint_screen.equalsIgnoreCase ("USER"))
	{
	    try
	    {
		String choice = e.getActionCommand ();
		File temp;
		lst_String = (lst_Characters.getItem (lst_Characters.getSelectedIndex ()));


		temp = new File ("GameFiles\\Users\\" + name + "\\Characters\\" + lst_String + "\\character_data.CWF");
		in = new BufferedReader (new FileReader (temp));



		Health = Integer.parseInt (Cipher.decrypt (in.readLine (), 2));
		Max_Health = Integer.parseInt (Cipher.decrypt (in.readLine (), 2));
		Mana = 500;
		Max_Mana = 500; // read needed data from selected character file
		in.close ();
		temp = new File ("GameFiles\\Users\\" + name + "\\Characters\\" + lst_String + "\\Money.CWF");
		in = new BufferedReader (new FileReader (temp));
		Wealth = Integer.parseInt (Cipher.decrypt (in.readLine (), 2)) * 100 + Integer.parseInt (Cipher.decrypt (in.readLine (), 2)) * 10 + Integer.parseInt (Cipher.decrypt (in.readLine (), 2));
		temp = new File ("GameFiles\\Users\\" + name + "\\Characters\\" + (lst_Characters.getItem (lst_Characters.getSelectedIndex ())) + "\\character_data.CWF");
		in = new BufferedReader (new FileReader (temp));
		String ln = "";
		for (int q = 0 ; q < 15 ; q++)
		{
		    ln = in.readLine ();
		    if (q == 14)
		    {
			User_Data [14] = Cipher.decrypt (ln, 2);

		    }

		}




		in.close ();
		Clan = User_Data [14];
		/*
		if (Clan.length () > 5)
		{
		    Clan = "Error";
		}
		*/
		in.close ();
		repaint ();
	    } // try


	    catch (IOException f)
	    {

	    } // catch
	} // paint screen USER


	if (paint_screen.equalsIgnoreCase ("character_creation"))
	{


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
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2464");
		    out.close ();
		}
		catch (IOException f)
		{
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
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2494");
		    out.close ();
		}
		catch (IOException f)
		{
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
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2521");
		    out.close ();
		}
		catch (IOException f)
		{
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
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2571");
		    out.close ();
		}
		catch (IOException f)
		{
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
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2598");
		    out.close ();
		}
		catch (IOException f)
		{
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
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2624");
		    out.close ();
		}
		catch (IOException f)
		{
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
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2649");
		    out.close ();
		}
		catch (IOException f)
		{
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
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2674");
		    out.close ();
		}
		catch (IOException f)
		{
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
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2700");
		    out.close ();
		}
		catch (IOException f)
		{
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
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2725");
		    out.close ();
		}
		catch (IOException f)
		{
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
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2750");
		    out.close ();
		}
		catch (IOException f)
		{
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
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2776");
		    out.close ();
		}
		catch (IOException f)
		{
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
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2801");
		    out.close ();
		}
		catch (IOException f)
		{
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
	    else
	    {
		try
		{
		    errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		    out = new PrintWriter (new FileWriter (errorLog));
		    out.println ("Error, line 2826");
		    out.close ();
		}
		catch (IOException f)
		{
		}
	    }




	    if (ClassName.getText ().equalsIgnoreCase ("Mage") | ClassName.getText ().equalsIgnoreCase ("Necro")) //if class selected is mage or necro then select between these magic abilities
	    {
		switch (Magicamount) //a switch used when selecting what type of magic the user would like wield33
		{
		    case 0:
			MagicName.setText ("   Fire ball");
			break;
		    case 1:
			MagicName.setText ("  Raise bear");
			break;
		    case 2:
			MagicName.setText ("Create sword");
			break;
		    case 3:
			MagicName.setText ("  Stone Ball");
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
			WeaponName.setText ("     Fire-Staff");
			break;
		    case 1:
			WeaponName.setText ("      Air-Staff");
			break;

		    case 2:
			WeaponName.setText ("    Water-Staff");
			break;

		    case 3:
			WeaponName.setText ("    Earth-Staff");
			break;


		}
	    }


	    if (ClassName.getText ().equalsIgnoreCase ("Knight")) //if class selected is knight then choose between these weapons
	    {
		switch (Weaponselection) //switch for weapon selected when button is pressed
		{
		    case 0:
			WeaponName.setText ("           Mace");
			break;
		    case 1:
			WeaponName.setText ("          Sword");
			break;

		    case 2:
			WeaponName.setText ("          Flail");
			break;

		    case 3:
			WeaponName.setText (" Leather-Shield");
			break;

		}
	    }


	    if (ClassName.getText ().equalsIgnoreCase ("necro")) //if the class selected is necro it changes the weapons to one of few options
	    {
		switch (Weaponselection) //switch for weapon selected when button is pressed
		{
		    case 0:
			WeaponName.setText ("     Fire-Staff");
			break;
		    case 1:
			WeaponName.setText ("      Air-Staff");
			break;

		    case 2:
			WeaponName.setText ("      Bear Skin");
			break;

		    case 3:
			WeaponName.setText ("    Earth-Staff");
			break;

		}
	    }


	    if (ClassName.getText ().equalsIgnoreCase ("archer")) //if the class is archer it changes the weapons textfield to one of a few options
	    {
		switch (Weaponselection) //switch for weapon selected when button is pressed
		{
		    case 0:
			WeaponName.setText ("      Cross-Bow");
			break;
		    case 1:
			WeaponName.setText ("            Bow");
			break;

		    case 2:
			WeaponName.setText ("      Cross-Bow");
			break;

		    case 3:
			WeaponName.setText ("            Bow");
			break;

		}
	    }


	    if (e.getSource () == create) //if the create button is pressed
	    {
		//Periodic.stop ();

		if (characterpoints != 0) //if there are still points remaining then remind user they still have points
		{
		    message.setText ("You still have points remaining");
		}

		else if (CharacterName.getText ().equalsIgnoreCase ("")) //if the character does not have a name tell the user to give themselves one
		{
		    message.setText ("Please give yourself a name");
		}

		else if (characterpoints == 0 && !CharacterName.getText ().equals ("")) //if everything is complete in customization then begin output data to a save file
		{

		    //stores data in array about current characters abilities and health for output to save files
		    Creation_data [0] = "" + Hpamount * 100; //stores health amount
		    Creation_data [1] = "" + Hpamount * 100; // max health
		    Creation_data [2] = "" + Armouramount; //stores ap amount
		    Creation_data [3] = "" + Agilityamount; //stores armour amount
		    Creation_data [4] = "" + (Strengthamount + 1); //stores strength amount
		    Creation_data [5] = CharacterName.getText (); //store name given to character
		    Creation_data [6] = ClassName.getText (); //stores class name
		    Creation_data [7] = WeaponName.getText ();

		    if (MagicName.getText ().equals ("")) //if the character is not a magic character output not applicable on that line
		    {
			Creation_data [8] = "NA";
		    }
		    else //else print their magic abilites
		    {
			Creation_data [8] = MagicName.getText ();
		    }

		    Creation_data [9] = "" + Apamount * 50; //stores ap amount
		    Creation_data [10] = "" + Hpamount * 50;
		    Creation_data [11] = "" + Hpamount * 50;
		    Creation_data [12] = "1";
		    Creation_data [13] = "0";
		    Creation_data [14] = "Fire";
		    characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\Characters\\" + CharacterName.getText ()); //creates a new file under users name then character name

		    characteroutput.mkdirs ();

		    characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\Characters\\" + CharacterName.getText () + "\\img.CWF"); //creates a new image file of the user

		    try
		    {
			out = new PrintWriter (new FileWriter (characteroutput));
			out.println (Cipher.encrypt (ClassName.getText (), 2));
			out.close ();
		    }
		    catch (IOException t)
		    {

		    }
		    characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\Characters\\" + CharacterName.getText () + "\\Money.CWF");
		    try
		    {
			out = new PrintWriter (new FileWriter (characteroutput));
			out.println (Cipher.encrypt ("0", 2));
			out.println (Cipher.encrypt ("0", 2));
			out.println (Cipher.encrypt ("10", 2));
			out.close ();
		    }
		    catch (IOException t)
		    {

		    }
		    characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\Characters\\" + CharacterName.getText () + "\\character_data.CWF");
		    try
		    {
			out = new PrintWriter (new FileWriter (characteroutput));
			for (int q = 0 ; q < 15 ; q++)
			{
			    if (q == 7)
			    {
				out.println (Creation_data [q]);
			    }
			    else
			    {
				out.println (Cipher.encrypt (Creation_data [q], 2));
			    }

			}
			out.close ();
		    }
		    catch (IOException t)
		    {

		    }
		    characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\Characters\\" + CharacterName.getText () + "\\Inventory.CWF");
		    try
		    {
			out = new PrintWriter (new FileWriter (characteroutput));
			out.println (WeaponName.getText ());
			out.println (Cipher.encrypt ("1", 2));
			out.close ();
		    }
		    catch (IOException t)
		    {

		    }
		    characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\Characters\\" + CharacterName.getText () + "\\Magic.CWF");
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
		    if (NEW)
		    {
			characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\Password.CWF");
			try
			{
			    out = new PrintWriter (new FileWriter (characteroutput));
			    out.println (Password);
			    out.close ();
			}
			catch (IOException t)  // password file
			{

			}
			characteroutput = new File ("GameFiles\\Users\\" + New_Cypher_User + "\\Worlds");

			characteroutput.mkdirs (); // worlds folder
		    } // if character is new user

		    remove (create);
		    remove (CharacterName);
		    remove (ClassName);
		    remove (message);
		    remove (pointsavailable);
		    remove (health);
		    remove (armour);
		    remove (strength);
		    remove (agility);
		    remove (NameLabel);
		    remove (Healthamount);
		    remove (addhealth);
		    remove (subhealth);
		    remove (addarmour);
		    remove (subarmour);
		    remove (addstrength);
		    remove (substrength);
		    remove (addagility);
		    remove (subagility);
		    remove (addmagic);
		    remove (submagic);
		    remove (addweapon);
		    remove (subweapon);
		    remove (addclass);
		    remove (subclass);
		    remove (MagicName);
		    remove (ArmourLbl);
		    remove (AgilityLbl);
		    remove (StrengthLbl);
		    remove (Health_Label);
		    remove (WeaponName);
		    remove (title);

		    paint_screen = "USER";
		    Main_Screen ();
		    remove (Health_Label);
		}
	    }
	} // else


	if (paint_screen.equalsIgnoreCase ("School_Screen"))
	{
	    Graphics g = getGraphics (); //allows graphics to be used in this method

	    new Sizing_Class (); //calls the sizing class to dynamically size and set locations

	    int x = this.getSize ().width; //gets width and height of users display size, used in sizing
	    int y = this.getSize ().height;

	    Font labelFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (3, x)); //a new font used for labels

	    if (e.getSource () == backbutton) //brings the user back to the general screen
	    {
		paint_screen = "General_screen";
		addschoolbuttons ();
	    }

	    else if (e.getSource () == continuebutton) //brings the user from welcome screen to the school purchasing screen
	    {
		paint_screen = "School_screen";
		addschoolbuttons ();
	    }

	    else if (e.getSource () == addHealth) //if the user hits the button to add health levels
	    {
		if (price == 0) //if the price is 0, then set the price
		    healthprice = 100 + (int) (healthprice * Math.pow (1.1, addedhealth + healthlvl / 10));
		else
		    healthprice = (int) (healthprice * Math.pow (1.1, addedhealth + healthlvl / 10)); //adds the nexts level price to the total

		addedhealth++; //increases the level of health added
	    }

	    else if (e.getSource () == subHealth && addedhealth > 0)
	    {
		addedhealth--; //take away one each time from added health

		healthprice = (int) (healthprice * Math.pow (0.9, addedhealth + healthlvl)); //subtracts the latest level price from

		if (addedhealth == 0)
		{
		    healthprice = 0; //if added health levels is 0, then price is 0
		}
	    }

	    else if (e.getSource () == addMana) //if mana is added, then set the price and add 1 to mana levels added
	    {
		if (manaprice == 0)
		    manaprice = 100 + (int) (manaprice * Math.pow (1.1, addedmana + manalvl));
		else
		    manaprice = (int) (manaprice * Math.pow (1.1, addedmana + manalvl));

		addedmana++;
	    }

	    else if (e.getSource () == subMana && addedmana > 0) //if mana is taken away, set the price, set the mana level
	    {
		addedmana--; //take away one from mana levels

		manaprice = (int) (manaprice * Math.pow (0.9, addedmana + manalvl));

		if (addedmana == 0)
		{
		    manaprice = 0; //if added mana levels is 0, then mana price is 0
		}
	    }

	    else if (e.getSource () == addStrength) //if strength is added
	    {
		if (strengthprice == 0) //sets the price appropiatly
		    strengthprice = 100 + (int) (strengthprice * Math.pow (1.1, addedstrength + strengthlvl));
		else
		    strengthprice = (int) (strengthprice * Math.pow (1.1, addedstrength + strengthlvl));

		addedstrength++; //add one to strength levels
	    }

	    else if (e.getSource () == subStrength && addedstrength > 0)
	    {
		addedstrength--; //take one away from strength

		strengthprice = (int) (strengthprice * Math.pow (0.9, addedstrength + strengthlvl)); //calculates price

		if (addedstrength == 0)
		{
		    strengthprice = 0; //if added strength levels is 0, then strength price is 0
		}
	    }


	    else if (e.getSource () == addStamina)
	    {
		if (staminaprice == 0)
		    staminaprice = 100 + (int) (staminaprice * Math.pow (1.1, addedstamina + staminalvl)); //calculates price
		else
		    staminaprice = (int) (staminaprice * Math.pow (1.1, addedstamina + staminalvl)); //calculates price

		addedstamina++; //incrase stamina level by 1
	    }

	    else if (e.getSource () == subStamina && addedstamina > 0)
	    {
		addedstamina--; //take one away from stamina

		staminaprice = (int) (staminaprice * Math.pow (0.9, addedstamina + staminalvl)); //calculates price

		if (addedstamina == 0)
		{
		    staminaprice = 0; //if added stamina levels is 0, then staminas price is 0
		}
	    }


	    price = healthprice + manaprice + strengthprice + staminaprice; //sums up all the prices into one total
	    repaint (); //redraws the canvas

	    if (e.getSource () == buy)
	    {
		g.setColor (Color.red); //sets color and fonts
		g.setFont (labelFont);

		if (currency < price) //if you have less money then the price of the purchase,
		{
		    g.drawString ("you broke af", Sizing_Class.X_Pos (75, x), Sizing_Class.Y_Pos (75, y));
		}

		else if (currency > price && price > 0) //if their is enough money for the purchase
		{
		    currency = currency - price; //curreny take away cost
		    healthlvl = healthlvl + addedhealth; //level increases with purchase
		    manalvl = manalvl + addedmana;
		    staminalvl = staminalvl + addedstamina;
		    strengthlvl = strengthlvl + addedstrength;

		    price = 0;
		    addedhealth = 0;
		    addedmana = 0;
		    addedstamina = 0;
		    addedstrength = 0;

		    goldamount = currency / 100;
		    silveramount = (currency - goldamount * 100) / 10;
		    bronzeamount = currency - (goldamount * 100) - (silveramount * 10);

		    healthamount = healthlvl * 100;
		    manaamount = manalvl * 10;
		    staminaamount = staminalvl;
		    strengthamount = strengthlvl + 1;
		}

		else if (price <= 0)
		{
		    g.drawString ("you haven't bought any thing", Sizing_Class.X_Pos (0, x), Sizing_Class.Y_Pos (20, y));
		}
	    }
	    repaint (); //redraws the canvas
	}
    } // action performed



    public void woodcutting ()
    {
	Graphics g = getGraphics ();
	if (InventoryAmount < 100)
	{
	    g.setFont (font);
	    g.setColor (Color.yellow);

	    g.drawString ("You begin to chop wood...", Sizing_Class.X_Pos (350, x), Sizing_Class.Y_Pos (300, y));
	    delay (1500);
	    int chance = 0;

	    chance = (int) (Math.random () * 100);

	    if (chance > (80 - Integer.parseInt (User_Data [3]))) //20% chance for wood
	    {
		for (int i = 0 ; i < 100 ; i++)
		{
		    if (Inventory [i] [0].equalsIgnoreCase ("Wood"))
		    {

			Inventory [i] [1] = "" + (Integer.parseInt (Inventory [i] [1]) + 1);
			i = 100;
			exists = true;
		    } //if
		} //for
		if (!exists)
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (Inventory [i] [0].equalsIgnoreCase (""))
			{
			    Inventory [i] [0] = "Wood";
			    Inventory [i] [1] = "1";
			    i = 100;
			} //if
		    } //for
		} // if couldent find wood
	    }
	    repaint ();
	}
    }


    public void fight ()
    {
	remove (left);
	remove (right);
	remove (forward);
	remove (down);
	remove (special);
	remove (fight);
	enemy_lvl = (int) (Math.random () * (6 + (Integer.parseInt (User_Data [12]) - 3))) + 4;
	paint_screen = "fight_Screen";



	userHp = (Integer.parseInt (User_Data [0]));
	user_mana = (Integer.parseInt (User_Data [10]));


	//creates new buttons and instantiates to display a string
	attack = new Button ("Attack");
	spell = new Button ("Cast Spell");
	defend = new Button ("Defend");
	change_weapon = new Button ("Change Weapon");
	potions = new Button ("Potions");
	flee = new Button ("Flee");
	change_magic = new Button ("Change Magic");
	fightbutton = new Button ("FIGHT!");
	weapon_up = new Button ("+");
	weapon_down = new Button ("-");
	magic_up = new Button ("+");
	magic_down = new Button ("-");
	potion_up = new Button ("+");
	potion_down = new Button ("-");
	next_move = new Button ("Continue");
	use_potion = new Button ("Use Potion");
	enemyHp = enemy_lvl * 100;
	enemy_mana = enemy_lvl * 75;
	current_enemy_Hp = enemyHp;
	current_user_Hp = userHp;
	current_user_mana = user_mana;
	current_user_healthbar = userHp;
	current_user_manabar = user_mana;
	current_enemy_mana = enemy_mana;
	health_potion = 25;
	mana_potion = 25;
	current_enemy_healthbar = current_enemy_Hp;
	current_enemy_manabar = current_enemy_mana;

	//adds textfields
	weapon_selection = new TextField (user_data [0] [0], 20);
	magic_selection = new TextField (20);
	potion_selection = new TextField (20);

	//adds labels and initiates them to display a string
	whos_turn = new Label ("It is your turn");
	winner = new Label ("Nobody has won");
	current_user_health = new Label (current_user_Hp + "/" + userHp);
	current_enemy_health = new Label (current_enemy_Hp + "/" + enemyHp);
	current_usermana_label = new Label (current_user_mana + "/" + user_mana);
	current_enemymana_label = new Label (current_enemy_mana + "/" + enemy_mana);
	last_move = new Label ("Enemies move was " + latestmove);
	lastuser_move = new Label ("Your last move was " + latestusermove);

	//sets textfields to not be editable
	weapon_selection.setEditable (false);
	magic_selection.setEditable (false);
	potion_selection.setEditable (false);

	//sets current enemy class
	switch ((int) (Math.random () * 4) + 1)
	{
	    case 1:
		enemy_class = "Archer";
		break;
	    case 2:
		enemy_class = "Mage";
		break;
	    case 3:
		enemy_class = "Knight";
		break;
	    case 4:
		enemy_class = "Necro";
		break;
	}


	//retrieves images
	enemy_image = getImage (getDocumentBase (), "Images//" + enemy_class + ".png");
	users_image = getImage (getDocumentBase (), "Images//" + User_Data [6] + ".png");
	health_image = getImage (getDocumentBase (), "Images//HealthBar.png");
	magic_image = getImage (getDocumentBase (), "Images//Fire Staff.jpg");
	mana_image = getImage (getDocumentBase (), "Images//ManaBar.png");

	//sets up screen that user will see
	setscreen ();

	//adds actionlistener for buttons so system knows to listen for a button press
	fightbutton.addActionListener (this);
	attack.addActionListener (this);
	spell.addActionListener (this);
	defend.addActionListener (this);
	change_weapon.addActionListener (this);
	flee.addActionListener (this);
	potions.addActionListener (this);
	change_magic.addActionListener (this);
	weapon_up.addActionListener (this);
	weapon_down.addActionListener (this);
	magic_up.addActionListener (this);
	magic_down.addActionListener (this);
	potion_up.addActionListener (this);
	potion_down.addActionListener (this);
	next_move.addActionListener (this);
	use_potion.addActionListener (this);
    }


    public void setscreen ()  //a method to set up the current screen
    {

	if (paint_screen.equalsIgnoreCase ("Fight_Screen"))
	{
	    add (attack);
	    add (spell);
	    add (defend);
	    add (change_weapon);
	    add (flee);
	    add (potions);
	    add (change_magic);
	    add (whos_turn);
	    add (current_user_health);
	    add (current_enemy_health);
	    add (current_usermana_label);
	    add (current_enemymana_label);
	    add (last_move);
	    add (lastuser_move);

	}


	else if (paint_screen.equalsIgnoreCase ("Change_Weapon_Screen"))
	{
	    add (fightbutton);
	    add (weapon_selection);
	    add (weapon_up);
	    add (weapon_down);

	}


	else if (paint_screen.equalsIgnoreCase ("Magic_Selection_Screen"))
	{
	    add (fightbutton);
	    add (magic_up);
	    add (magic_down);
	    add (magic_selection);
	}


	else if (paint_screen.equalsIgnoreCase ("Potion_Screen"))
	{
	    add (fightbutton);
	    add (potion_selection);
	    add (potion_up);
	    add (potion_down);
	    add (use_potion);
	}


	else if (paint_screen.equalsIgnoreCase ("Win_Screen"))
	{
	    if (Clan.equalsIgnoreCase ("fire"))
	    {
		map [x] [y] [2] = 3;
	    }
	    else if (Clan.equalsIgnoreCase ("air"))
	    {
		map [x] [coordinate_y] [2] = 0;
	    }
	    else if (Clan.equalsIgnoreCase ("water"))
	    {
		map [x] [y] [2] = 1;
	    }
	    else if (Clan.equalsIgnoreCase ("earth"))
	    {
		map [x] [y] [2] = 2;
	    }

	    //add (winner);
	    //add (next_move);

	}


	else
	{
	    try
	    {
		errorLog = new File ("GameFiles\\Users\\" + user + "\\ErrorLog.txt");
		out = new PrintWriter (new FileWriter (errorLog));
		out.println ("Error, line 821");
		out.close ();
	    }
	    catch (IOException f)
	    {
	    }
	}
    }


    public void mining ()
    {
	Graphics g = getGraphics ();
	if (InventoryAmount < 100)
	{
	    g.setFont (font);
	    g.setColor (Color.red);

	    g.drawString ("You begin to mine for ore...", Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (30, y));
	    delay (1500);

	    int chance = 0;

	    chance = (int) (Math.random () * 100);

	    if (chance > (95 - Integer.parseInt (User_Data [3]))) //5% chance for gold
	    {
		for (int i = 0 ; i < 100 ; i++)
		{
		    if (Inventory [i] [0].equalsIgnoreCase ("           Gold"))
		    {

			Inventory [i] [1] = "" + (Integer.parseInt (Inventory [i] [1]) + 1);
			i = 100;
			exists = true;
		    } //if
		} //for
		if (!exists)
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (Inventory [i] [0].equalsIgnoreCase (""))
			{
			    Inventory [i] [0] = "           Gold";
			    Inventory [i] [1] = "1";
			    i = 100;
			} //if
		    } //for
		} // if couldent find wood
	    }
	    else if (chance > (85 - Integer.parseInt (User_Data [3]))) //15% for iron
	    {
		for (int i = 0 ; i < 100 ; i++)
		{
		    if (Inventory [i] [0].equalsIgnoreCase ("           iron"))
		    {

			Inventory [i] [1] = "" + (Integer.parseInt (Inventory [i] [1]) + 1);
			i = 100;
			exists = true;
		    } //if
		} //for
		if (!exists)
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (Inventory [i] [0].equalsIgnoreCase (""))
			{
			    Inventory [i] [0] = "           iron";
			    Inventory [i] [1] = "1";
			    i = 100;
			} //if
		    } //for
		} // if couldent find wood

	    }
	    else if (chance > (75 - Integer.parseInt (User_Data [3]))) //25% for copper
	    {
		for (int i = 0 ; i < 100 ; i++)
		{
		    if (Inventory [i] [0].equalsIgnoreCase ("         Copper"))
		    {

			Inventory [i] [1] = "" + (Integer.parseInt (Inventory [i] [1]) + 1);
			i = 100;
			exists = true;
		    } //if
		} //for
		if (!exists)
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (Inventory [i] [0].equalsIgnoreCase (""))
			{
			    Inventory [i] [0] = "         Copper";
			    Inventory [i] [1] = "1";
			    i = 100;
			} //if
		    } //for
		} // if couldent find wood

	    }
	    //65% chance for nothing
	    repaint ();
	}
    }


    public void fishing ()
    {
	Graphics g = getGraphics ();
	if (InventoryAmount < 100)
	{
	    g.setFont (font);
	    g.setColor (Color.red);

	    g.drawString ("You start fishing...", Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (30, y));
	    delay (1500);

	    int chance = 0;

	    chance = (int) (Math.random () * 100);

	    if (chance > (90 - luck)) //10% chance to catch a fish
	    {
		for (int i = 0 ; i < 100 ; i++)
		{
		    if (Inventory [i] [0].equalsIgnoreCase ("           Fish"))
		    {

			Inventory [i] [1] = "" + (Integer.parseInt (Inventory [i] [1]) + 1);
			i = 100;
			exists = true;
		    } //if
		} //for
		if (!exists)
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (Inventory [i] [0].equalsIgnoreCase (""))
			{
			    Inventory [i] [0] = "           Fish";
			    Inventory [i] [1] = "1";
			    i = 100;
			} //if
		    } //for
		} // if couldent find wood

	    }
	    //90% chance for nothing
	    repaint ();
	}
    }


    public void hunting ()
    {
	Graphics g = getGraphics ();

	if (InventoryAmount < 100)
	{
	    exists = false;
	    g.setFont (font);
	    g.setColor (Color.red);

	    g.drawString ("You quietly stalk your prey...", Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (30, y));
	    delay (1500);

	    int chance = 0;

	    chance = (int) (Math.random () * 100);

	    if (chance > (90 - (luck - Integer.parseInt (User_Data [3]))))
	    {
		for (int i = 0 ; i < 100 ; i++)
		{
		    if (Inventory [i] [0].equalsIgnoreCase ("      Bear Skin"))
		    {

			Inventory [i] [1] = "" + (Integer.parseInt (Inventory [i] [1]) + 1);
			i = 100;
			exists = true;
		    } //if
		} //for
		if (!exists)
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (Inventory [i] [0].equalsIgnoreCase (""))
			{
			    Inventory [i] [0] = "      Bear Skin";
			    Inventory [i] [1] = "1";
			    i = 100;
			} //if
		    } //for
		} // if couldent find wood

	    }
	    else if (chance > (80 - (luck - Integer.parseInt (User_Data [3]))))
	    {
		for (int i = 0 ; i < 100 ; i++)
		{
		    if (Inventory [i] [0].equalsIgnoreCase ("      Dear Skin"))
		    {

			Inventory [i] [1] = "" + (Integer.parseInt (Inventory [i] [1]) + 1);
			i = 100;
			exists = true;
		    } //if
		} //for
		if (!exists)
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (Inventory [i] [0].equalsIgnoreCase (""))
			{
			    Inventory [i] [0] = "      Dear Skin";
			    Inventory [i] [1] = "1";
			    i = 100;
			} //if
		    } //for
		} // if couldent find wood

	    }
	    //70% chance for nothing
	    repaint ();
	}
    }


    public void General_Screen ()
    {

	//sets font
	paint_screen = "General_Screen";
	fnt = new Font ("Dialog", Font.BOLD, 22);

	font = new Font ("Dialog", Font.BOLD, 36);

	//import images
	blacksmith = getImage (getDocumentBase (), "Images\\blacksmith.jpg");
	mine = getImage (getDocumentBase (), "Images\\mine.jpg");
	forest = getImage (getDocumentBase (), "Images\\forest.jpg");
	lake = getImage (getDocumentBase (), "Images\\lake.jpg");
	store = getImage (getDocumentBase (), "Images\\store.jpg");
	plains = getImage (getDocumentBase (), "Images\\plains.jpg");
	school = getImage (getDocumentBase (), "Images\\school.jpg");
	castlewall = getImage (getDocumentBase (), "Images\\castlewall.jpg");
	castlegate = getImage (getDocumentBase (), "Images\\castlegate.jpg");
	castlegrounds = getImage (getDocumentBase (), "Images\\castlegrounds.jpg");
	road = getImage (getDocumentBase (), "Images\\road.jpg");
	mountain = getImage (getDocumentBase (), "Images\\mountain.jpg");

	//import sounds
	mouseclick = getAudioClip (getCodeBase (), "Sounds\\mouseclick.wav");

	//declaring buttons
	forward = new Button ("Forward");
	left = new Button ("Left");
	right = new Button ("Right");
	down = new Button ("Down");
	fight = new Button ("Fight!");
	special = new Button ("Special");

	//font set for buttons
	forward.setFont (fnt);
	add (forward);

	left.setFont (fnt);
	add (left);

	right.setFont (fnt);
	add (right);

	down.setFont (fnt);
	add (down);

	fight.setFont (fnt);
	add (fight);

	special.setFont (fnt);
	add (special);




    } // general screen


    public void Create_Character (String password, String username)
    {
	//this section instantiates all labels used in this class
	Password = password;
	name = username;
	New_Cypher_User = username;
	lbl_name = new Label ("Name");
	message = new Label ("");
	Health_Label = new Label ("");
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
	Healthamount = new Label ("");
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
	add (create);

	CharacterName = new TextField (20); //sets sizes of textfields
	ClassName = new TextField (20);
	WeaponName = new TextField (20);
	MagicName = new TextField (20);

	ClassName.setEditable (false); //sets textfields so that they cannot be changed by the user
	WeaponName.setEditable (false);


	character_screen_image = getImage (getDocumentBase (), "Images//Knight.png"); //images for background and current character
	background_image = getImage (getDocumentBase (), "Images//Battle_of_Waterloo_1815.png");
	add (Health_Label);

	buttons (); //calls method that adds buttons to screen (unnecessary, but used to make it look neater)
	repaint ();

	validate ();

    } // create character


    public void blacksmith ()
    {

	for (int i = 0 ; i < 100 ; i++) // set all arrays to empty values to avoid null pointer exceptions
	{

	    MIDDLE [i] [0] = "";
	    MIDDLE [i] [1] = "0";
	    VENDOR [i] [0] = "";
	    VENDOR [i] [1] = "0";
	    Prices [i] [0] = "";
	    Prices [i] [1] = "0";
	    Prices [i] [2] = "0";
	} // for i


	this.requestFocus ();


	image = getImage (getDocumentBase (), "Images\\blacksmith.jpg"); // background
	Prices [0] [0] = "           Mace";
	Prices [1] [0] = "            Bow";
	Prices [2] [0] = "          Arrow";
	Prices [3] [0] = "      Cross-Bow";
	Prices [4] [0] = "          Flail";
	Prices [5] [0] = "          Sword";
	Prices [6] [0] = "     Fire-Staff";
	Prices [7] [0] = "      Air-Staff";
	Prices [8] [0] = "    Water-Staff";
	Prices [9] [0] = "    Earth-Staff";
	Prices [10] [0] = " Leather-Shield";
	Prices [11] [0] = "    Iron-Shield";
	Prices [12] [0] = "   Steel-Shield";
	Prices [13] [0] = "    Iron-Armour";
	Prices [14] [0] = "  Health Potion";
	Prices [15] [0] = "    Mana Potion";
	Prices [16] [0] = " Leather-Armour";
	Prices [17] [0] = "           Fish";
	Prices [18] [0] = "      Bear Skin";
	Prices [19] [0] = "      Dear Skin";
	Prices [20] [0] = "           Gold";
	Prices [21] [0] = "           iron";
	Prices [22] [0] = "         Copper";
	Prices [23] [0] = "           wood";
	// set price index <-- [names]

	//TESTING <--
	// gold = 100 silver = 10   copper = 1

	//set price index values per item -->
	Prices [0] [1] = "150";
	Prices [1] [1] = "200"; // two gold or 20 silver or 200 copper
	Prices [2] [1] = "5";
	Prices [3] [1] = "250";
	Prices [4] [1] = "225";
	Prices [5] [1] = "100";
	Prices [6] [1] = "500";
	Prices [7] [1] = "500";
	Prices [8] [1] = "500";
	Prices [9] [1] = "500";
	Prices [10] [1] = "75";
	Prices [11] [1] = "125";
	Prices [12] [1] = "175";
	Prices [13] [1] = "250";
	Prices [14] [1] = "75";
	Prices [15] [1] = "75";
	Prices [16] [1] = "125";
	Prices [17] [1] = "10";
	Prices [18] [1] = "50";
	Prices [19] [1] = "25";
	Prices [20] [1] = "75";
	Prices [21] [1] = "15";
	Prices [22] [1] = "5";
	Prices [23] [0] = "20";
	// level requirements for price index -->
	Prices [0] [2] = "4";
	Prices [1] [2] = "3"; // level requirements
	Prices [2] [2] = "3";
	Prices [3] [2] = "4";
	Prices [4] [2] = "5";
	Prices [5] [2] = "1";
	Prices [6] [2] = "7";
	Prices [7] [2] = "7";
	Prices [8] [2] = "7";
	Prices [9] [2] = "7";
	Prices [10] [2] = "1";
	Prices [11] [2] = "3";
	Prices [12] [2] = "5";
	Prices [13] [2] = "5";
	Prices [14] [2] = "1";
	Prices [15] [2] = "1";
	Prices [16] [2] = "2";
	Prices [17] [2] = "0";
	Prices [18] [2] = "2";
	Prices [19] [2] = "1";
	Prices [20] [2] = "4";
	Prices [21] [2] = "2";
	Prices [22] [2] = "1";
	Prices [23] [2] = "1";
	//add items to screen -->
	add (Vendor);
	add (Personal);
	add (Middle);
	add (VendorGold);
	add (VendorSilver);
	add (VendorCopper);
	add (PersonalGold);
	add (PersonalSilver);
	add (PersonalCopper);
	add (TransactionGold);
	add (TransactionSilver);
	add (TransactionCopper);
	add (BUY);
	add (SELL);
	add (CONFIRM);
	add (REMOVE);

	// vendors values dependant on user level
	VENDOR_COPPER = (int) (Math.random () * (100 * lvl));
	VENDOR_SILVER = (int) (Math.random () * (15 * lvl));
	VENDOR_GOLD = (int) (Math.random () * (5 * lvl));

	// for every hundred copper add a silver
	if (VENDOR_COPPER >= 100)
	{
	    VENDOR_SILVER = VENDOR_SILVER + (VENDOR_COPPER / 100);
	    VENDOR_COPPER = VENDOR_COPPER - ((VENDOR_COPPER / 100) * 100);
	}


	if (Copper >= 100)
	{
	    Silver = Silver + (Copper / 100);
	    Copper = Copper - ((Silver / 100) * 100);
	}


	if (Silver > 10)
	{
	    Gold = Gold + Silver / 10;

	    Silver = Silver % 10;
	}


	if (VENDOR_SILVER > 10)
	{
	    VENDOR_GOLD = VENDOR_GOLD + VENDOR_SILVER / 10;
	    VENDOR_SILVER = VENDOR_SILVER % 10;
	}

	list = Sizing_Class.X_Size (3, y);
	fnt_Lists = new Font ("MonoSpaced", Font.PLAIN, list); // list font
	Personal.setFont (fnt_Lists);
	Personal.add ("      ITEM            Amt       Price"); // identifiers
	for (int i = 0 ; i < 100 ; i++) // for all possible items in list
	{
	    for (int b = 0 ; b < Prices.length ; b++) // for everything in price index
	    {
		if (Inventory [i] [0].equalsIgnoreCase (Prices [b] [0]) && !Inventory [i] [0].equalsIgnoreCase ("")) // if item exists in price index
		{
		    String temporary = "";
		    temporary = Inventory [i] [0] + "        " + Inventory [i] [1];
		    for (int x = 0 ; x < 10 - Inventory [i] [1].length () ; x++)
		    {
			temporary = temporary + " ";
		    }
		    temporary = temporary + Prices [b] [1];
		    Personal.add (temporary);   // put inventory into personal items list
		} // if
	    } // for b
	} // for i


	Vendor.setFont (fnt_Lists);
	Vendor.add ("      ITEM            Amt       Price"); // identifiers
	for (int i = 0 ; i < 100 ; i++) //
	{

	    if (Integer.parseInt (Prices [i] [2]) <= lvl && !Prices [i] [0].equalsIgnoreCase ("")) // if user is high enough level add item to vendors stock
	    {
		VENDOR [i] [0] = Prices [i] [0];
		VENDOR [i] [1] = "" + (int) (Math.random () * (2 * lvl));
	    } // if


	} // for


	for (int i = 0 ; i < 100 ; i++)
	{
	    for (int b = 0 ; b < Prices.length ; b++) // for everything in price index
	    {
		if (VENDOR [i] [0].equalsIgnoreCase (Prices [b] [0]) && !VENDOR [i] [0].equalsIgnoreCase ("") && Integer.parseInt (VENDOR [i] [1]) != 0) // if item exists in price index
		{
		    temporary = "";
		    temporary = VENDOR [i] [0] + "        " + VENDOR [i] [1];
		    for (int x = 0 ; x < 10 - VENDOR [i] [1].length () ; x++)
		    {
			temporary = temporary + " ";
		    }
		    temporary = temporary + Prices [b] [1];
		    Vendor.add (temporary);
		} // if
	    } // for b
	} // for i


	Middle.setFont (fnt_Lists);
	Middle.add ("      ITEM          Amt      Price");
	Vendor.clear ();
	Vendor.add ("      ITEM            Amt       Price"); // identifiers
	for (int i = 0 ; i < 100 ; i++)
	{
	    for (int b = 0 ; b < Prices.length ; b++) // for everything in price index
	    {
		if (VENDOR [i] [0].equalsIgnoreCase (Prices [b] [0]) && !VENDOR [i] [0].equalsIgnoreCase ("") && !VENDOR [i] [1].equalsIgnoreCase ("0")) // if item exists in price index
		{
		    temporary = "";
		    temporary = VENDOR [i] [0] + "        " + VENDOR [i] [1];
		    for (int x = 0 ; x < 10 - VENDOR [i] [1].length () ; x++)
		    {
			temporary = temporary + " ";
		    }
		    temporary = temporary + Prices [b] [1];
		    Vendor.add (temporary);
		} // if
	    } // for b
	} // for i


	for (int i = 0 ; i < 100 ; i++)
	{
	    if (VENDOR [i] [1].equalsIgnoreCase ("0"))
	    {
		VENDOR [i] [0] = "";
	    }
	}


	add (BACK);
    }


    public void school ()
    {
	new Sizing_Class ();
	//creation of buttons
	addHealth = new Button ("+");
	subHealth = new Button ("-");
	addMana = new Button ("+");
	subMana = new Button ("-");
	addStamina = new Button ("+");
	subStamina = new Button ("-");
	addStrength = new Button ("+");
	subStrength = new Button ("-");
	back = new Button ("back");
	buy = new Button ("Purchase");
	continuebutton = new Button ("Continue");

	currency = (goldamount * 100) + (silveramount * 10) + bronzeamount;

	//adds actionlistener to buttons
	addHealth.addActionListener (this);
	subHealth.addActionListener (this);
	addMana.addActionListener (this);
	subMana.addActionListener (this);
	addStamina.addActionListener (this);
	subStamina.addActionListener (this);
	addStrength.addActionListener (this);
	subStrength.addActionListener (this);
	back.addActionListener (this);
	buy.addActionListener (this);
	continuebutton.addActionListener (this);

	//gets user image and background image
	School_image = getImage (getDocumentBase (), "Images//thYJEFHHH0.jpg");
	user_image = getImage (getDocumentBase (), "Images//" + userclass + ".png");

	//adds buttons to display
	addschoolbuttons ();
    }


    public void store ()
    {

	for (int i = 0 ; i < 100 ; i++) // set all arrays to empty values to avoid null pointer exceptions
	{

	    MIDDLE [i] [0] = "";
	    MIDDLE [i] [1] = "0";
	    VENDOR [i] [0] = "";
	    VENDOR [i] [1] = "0";
	    Prices [i] [0] = "";
	    Prices [i] [1] = "0";
	    Prices [i] [2] = "0";
	} // for i


	new Sizing_Class ();
	this.requestFocus ();


	image = getImage (getDocumentBase (), "Images\\store.jpg"); // background
	Prices [0] [0] = "           Mace";
	Prices [1] [0] = "            Bow";
	Prices [2] [0] = "          Arrow";
	Prices [3] [0] = "      Cross-Bow";
	Prices [4] [0] = "          Flail";
	Prices [5] [0] = "          Sword";
	Prices [6] [0] = "     Fire-Staff";
	Prices [7] [0] = "      Air-Staff";
	Prices [8] [0] = "    Water-Staff";
	Prices [9] [0] = "    Earth-Staff";
	Prices [10] [0] = " Leather-Shield";
	Prices [11] [0] = "    Iron-Shield";
	Prices [12] [0] = "   Steel-Shield";
	Prices [13] [0] = "    Iron-Armour";
	Prices [14] [0] = "  Health Potion";
	Prices [15] [0] = "    Mana Potion";
	Prices [16] [0] = " Leather-Armour";
	Prices [17] [0] = "           Fish";
	Prices [18] [0] = "      Bear Skin";
	Prices [19] [0] = "      Dear Skin";
	// set price index <-- [names]

	//TESTING <--
	// gold = 100 silver = 10   copper = 1

	//set price index values per item -->
	Prices [0] [1] = "150";
	Prices [1] [1] = "200"; // two gold or 20 silver or 200 copper
	Prices [2] [1] = "5";
	Prices [3] [1] = "250";
	Prices [4] [1] = "225";
	Prices [5] [1] = "100";
	Prices [6] [1] = "500";
	Prices [7] [1] = "500";
	Prices [8] [1] = "500";
	Prices [9] [1] = "500";
	Prices [10] [1] = "75";
	Prices [11] [1] = "125";
	Prices [12] [1] = "175";
	Prices [13] [1] = "250";
	Prices [14] [1] = "75";
	Prices [15] [1] = "75";
	Prices [16] [1] = "125";
	Prices [17] [1] = "10";
	Prices [18] [1] = "50";
	Prices [19] [1] = "25";

	// level requirements for price index -->
	Prices [0] [2] = "4";
	Prices [1] [2] = "3"; // level requirements
	Prices [2] [2] = "3";
	Prices [3] [2] = "4";
	Prices [4] [2] = "5";
	Prices [5] [2] = "1";
	Prices [6] [2] = "7";
	Prices [7] [2] = "7";
	Prices [8] [2] = "7";
	Prices [9] [2] = "7";
	Prices [10] [2] = "1";
	Prices [11] [2] = "3";
	Prices [12] [2] = "5";
	Prices [13] [2] = "5";
	Prices [14] [2] = "1";
	Prices [15] [2] = "1";
	Prices [16] [2] = "2";
	Prices [17] [2] = "0";
	//add items to screen -->
	add (Vendor);
	add (Personal);
	add (Middle);
	add (VendorGold);
	add (VendorSilver);
	add (VendorCopper);
	add (PersonalGold);
	add (PersonalSilver);
	add (PersonalCopper);
	add (TransactionGold);
	add (TransactionSilver);
	add (TransactionCopper);
	add (BUY);
	add (SELL);
	add (CONFIRM);
	add (REMOVE);

	// vendors values dependant on user level
	VENDOR_COPPER = (int) (Math.random () * (100 * lvl));
	VENDOR_SILVER = (int) (Math.random () * (15 * lvl));
	VENDOR_GOLD = (int) (Math.random () * (5 * lvl));

	// for every hundred copper add a silver
	if (VENDOR_COPPER >= 100)
	{
	    VENDOR_SILVER = VENDOR_SILVER + (VENDOR_COPPER / 100);
	    VENDOR_COPPER = VENDOR_COPPER - ((VENDOR_COPPER / 100) * 100);
	}


	if (Copper >= 100)
	{
	    Silver = Silver + (Copper / 100);
	    Copper = Copper - ((Silver / 100) * 100);
	}


	if (Silver > 10)
	{
	    Gold = Gold + Silver / 10;

	    Silver = Silver % 10;
	}


	if (VENDOR_SILVER > 10)
	{
	    VENDOR_GOLD = VENDOR_GOLD + VENDOR_SILVER / 10;
	    VENDOR_SILVER = VENDOR_SILVER % 10;
	}

	list = Sizing_Class.X_Size (3, y);
	fnt_Lists = new Font ("MonoSpaced", Font.PLAIN, list); // list font
	Personal.setFont (fnt_Lists);
	Personal.add ("      ITEM            Amt       Price"); // identifiers
	for (int i = 0 ; i < 100 ; i++) // for all possible items in list
	{
	    for (int b = 0 ; b < Prices.length ; b++) // for everything in price index
	    {
		if (Inventory [i] [0].equalsIgnoreCase (Prices [b] [0]) && !Inventory [i] [0].equalsIgnoreCase ("")) // if item exists in price index
		{
		    String temporary = "";
		    temporary = Inventory [i] [0] + "        " + Inventory [i] [1];
		    for (int x = 0 ; x < 10 - Inventory [i] [1].length () ; x++)
		    {
			temporary = temporary + " ";
		    }
		    temporary = temporary + Prices [b] [1];
		    Personal.add (temporary);   // put inventory into personal items list
		} // if
	    } // for b
	} // for i


	Vendor.setFont (fnt_Lists);
	Vendor.add ("      ITEM            Amt       Price"); // identifiers
	for (int i = 0 ; i < 100 ; i++) //
	{

	    if (Integer.parseInt (Prices [i] [2]) <= lvl && !Prices [i] [0].equalsIgnoreCase ("")) // if user is high enough level add item to vendors stock
	    {
		VENDOR [i] [0] = Prices [i] [0];
		VENDOR [i] [1] = "" + (int) (Math.random () * (2 * lvl));
	    } // if


	} // for


	for (int i = 0 ; i < 100 ; i++)
	{
	    for (int b = 0 ; b < Prices.length ; b++) // for everything in price index
	    {
		if (VENDOR [i] [0].equalsIgnoreCase (Prices [b] [0]) && !VENDOR [i] [0].equalsIgnoreCase ("") && Integer.parseInt (VENDOR [i] [1]) != 0) // if item exists in price index
		{
		    temporary = "";
		    temporary = VENDOR [i] [0] + "        " + VENDOR [i] [1];
		    for (int x = 0 ; x < 10 - VENDOR [i] [1].length () ; x++)
		    {
			temporary = temporary + " ";
		    }
		    temporary = temporary + Prices [b] [1];
		    Vendor.add (temporary);
		} // if
	    } // for b
	} // for i


	Middle.setFont (fnt_Lists);
	Middle.add ("      ITEM          Amt      Price");
	Vendor.clear ();
	Vendor.add ("      ITEM            Amt       Price"); // identifiers
	for (int i = 0 ; i < 100 ; i++)
	{
	    for (int b = 0 ; b < Prices.length ; b++) // for everything in price index
	    {
		if (VENDOR [i] [0].equalsIgnoreCase (Prices [b] [0]) && !VENDOR [i] [0].equalsIgnoreCase ("") && !VENDOR [i] [1].equalsIgnoreCase ("0")) // if item exists in price index
		{
		    temporary = "";
		    temporary = VENDOR [i] [0] + "        " + VENDOR [i] [1];
		    for (int x = 0 ; x < 10 - VENDOR [i] [1].length () ; x++)
		    {
			temporary = temporary + " ";
		    }
		    temporary = temporary + Prices [b] [1];
		    Vendor.add (temporary);
		} // if
	    } // for b
	} // for i


	for (int i = 0 ; i < 100 ; i++)
	{
	    if (VENDOR [i] [1].equalsIgnoreCase ("0"))
	    {
		VENDOR [i] [0] = "";
	    }
	}


	add (BACK);
    }


    public void delay (int x)
    {
	try
	{
	    Thread.sleep (x);
	} // try


	catch (InterruptedException e)
	{

	} // catch
    } // delay


 

    public boolean action (Event e, Object o)
    {

	Graphics g = getGraphics ();
	if (e.target == PLAY)
	{

	    switch (clips)
	    {
		case 0:
		    if (blacksmithsound != null)
		    {
			blacksmithsound.stop ();
			blacksmithsound = null;
		    }
		    break;

		case 1:
		    if (castlesound != null)
		    {
			castlesound.stop ();
			castlesound = null;
		    }
		    break;

		case 2:
		    if (castlesound != null)
		    {
			castlesound.stop ();
			castlesound = null;
		    }
		    break;

		case 3:
		    if (plainssound != null)
		    {
			plainssound.stop ();
			plainssound = null;
		    }
		    break;

		case 4:
		    if (forestsound != null)
		    {
			forestsound.stop ();
			forestsound = null;
		    }
		    break;

		case 5:
		    if (miningsound != null)
		    {
			miningsound.stop ();
			miningsound = null;
		    }
		    break;

		case 6:
		    if (riversound != null)
		    {
			riversound.stop ();
			riversound = null;
		    }
		    break;

		default:
		    break;
	    }
	    
	    if (MP3.getSelectedIndex () != -1)
	    {

		if (MP3.getItem (MP3.getSelectedIndex ()).equalsIgnoreCase ("001"))
		{
		    try
		    {
			clip2 = getAudioClip (getCodeBase (), ("Sounds\\001.wav"));
			clip2.play ();
		    }
		    catch (Throwable cause)
		    {
			System.out.println (cause);
		    }
		}
		else if (MP3.getItem (MP3.getSelectedIndex ()).equalsIgnoreCase ("002"))
		{
		    clip2 = getAudioClip (getCodeBase (), ("Sounds\\002.wav"));
		    clip2.play ();
		}
		else if (MP3.getItem (MP3.getSelectedIndex ()).equalsIgnoreCase ("003"))
		{
		    clip2 = getAudioClip (getCodeBase (), ("Sounds\\003.wav"));
		    clip2.play ();
		}
	    }
	}


	if (e.target == drop)
	{
	    information.setText ("");
	    //drops item quantities down from original inventory value if user chooses to
	    String temp = "";

	    if (LIST.getSelectedIndex () != -1)
	    {
		for (int i = 0 ; i < Prices [16] [0].length () ; i++)
		{ // gets name of item without number
		    temp = temp + LIST.getItem (LIST.getSelectedIndex ()).charAt (i);
		}
	    }

	    for (int i = 0 ; i < 100 ; i++)
	    {
		if (Inventory [i] [0].equalsIgnoreCase (temp))
		{
		    Inventory [i] [1] = "" + (Integer.parseInt (Inventory [i] [1]) - 1);
		}
		if (Integer.parseInt (Inventory [i] [1]) < 0)
		{
		    Inventory [i] [0] = "";
		}

	    }
	    LIST.clear ();
	    //adds inventory items to the LIST
	    for (int i = 0 ; i < Inventory.length ; i++)
	    {
		if (!Inventory [i] [0].equalsIgnoreCase ("") && !Inventory [i] [1].equalsIgnoreCase ("0"))
		{
		    LIST.add (Inventory [i] [0] + "       " + Inventory [i] [1]);
		}
	    }
	}


	//if info button is pressed
	if (e.target == info)
	{
	    information.setText ("");
	    for (int i = 0 ; i < 100 ; i++)
	    {
		if (LIST.getSelectedIndex () != -1) //not -1
		{
		    temp = "";
		    for (int b = 0 ; b < 15 ; b++)
		    {
			temp = temp + LIST.getItem (LIST.getSelectedIndex ()).charAt (b);
		    }
		    for (int x = 0 ; x < Prices.length ; x++) //in range of the LIST item length
		    {
			if (temp.equalsIgnoreCase (Prices [x] [0])) //gets LIST index
			{
			    try
			    {
				switch (x)
				{
					//reads through the files depending on which LIST item is pressed
				    case 0:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\mace.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();

					break;

				    case 1:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\bow.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 2:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\arrow.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					LIST.clear ();
					break;

				    case 3:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\crossbow.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 4:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\flail.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 5:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\sword.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					LIST.clear ();
					break;

				    case 6:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\firestaff.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 7:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\airstaff.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 8:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\waterstaff.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 9:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\earthstaff.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 10:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\leathershield.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 11:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\ironshield.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 12:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\steelshield.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 13:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\ironarmor.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 14:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\healthpotion.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 15:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\manapotion.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 16:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\leatherarmor.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 17:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\fish.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 18:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\bearskin.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 19:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\deerskin.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;
				    case 20:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\gold.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;
				    case 21:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\iron.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;
				    case 22:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\copper.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;

				    case 23:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\wood.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;
				    default:
					in = new BufferedReader (new FileReader ("GameFiles\\Files\\error.txt"));
					line = in.readLine ();
					while (line != null)
					{
					    information.setText (information.getText () + line);
					    information.setText (information.getText () + "\n");
					    line = in.readLine ();
					}
					in.close ();
					i = 100;
					x = Prices.length + 1;
					LIST.clear ();
					break;





				}
			    }
			    catch (Throwable Cause)
			    {
			    }
			    //LIST is null
			    LIST.clear ();
			    for (int z = 0 ; z < 100 ; z++)
			    {
				if (!Inventory [z] [0].equalsIgnoreCase (""))
				{
				    LIST.add (Inventory [z] [0] + "       " + Inventory [z] [1]);
				}
			    }

			}
		    }
		}
	    }
	    this.requestFocus ();
	}


	if (paint_screen.equalsIgnoreCase ("Store")) // blacksmith store screen
	{
	    for (int i = 0 ; i < 100 ; i++)
	    {
		if (VENDOR [i] [1].equalsIgnoreCase ("0"))
		{
		    VENDOR [i] [0] = "";
		}
	    }
	    temp = "";
	    if (e.target == BACK)
	    {
		Vendor.clear ();
		Personal.clear ();
		Middle.clear ();
		remove (Vendor);
		remove (Personal);
		remove (Middle);
		remove (VendorGold);
		remove (VendorSilver);
		remove (VendorCopper);
		remove (PersonalGold);
		remove (PersonalSilver);
		remove (PersonalCopper);
		remove (TransactionGold);
		remove (TransactionSilver);
		remove (TransactionCopper);
		remove (BUY);
		remove (SELL);
		remove (CONFIRM);
		remove (REMOVE);
		remove (BACK);
		add (forward);
		add (down);
		add (left);
		add (right);
		add (fight);
		add (special);
		paint_screen = "General_Screen";
		repaint ();
		validate ();
		this.requestFocus ();
	    }
	    if (e.target == SELL && Personal.getSelectedIndex () != -1 && Personal.getItem (Personal.getSelectedIndex ()).charAt (6) != 'I') // if target is sell and an item is selected and the item is not the identifier at the top of the list
	    {
		for (int i = 0 ; i < Prices [16] [0].length () ; i++) // take 16 character long name from list item
		{
		    temp = temp + Personal.getItem (Personal.getSelectedIndex ()).charAt (i);
		} // for


		for (int i = 0 ; i < 100 ; i++)
		{
		    for (int b = 0 ; b < Prices.length ; b++)
		    {
			if (temp.equalsIgnoreCase (Prices [i] [0])) // if the name is one in the price index
			{
			    CHECK = true;
			} // if
		    } // for b
		} // for i
		if (CHECK == true)
		{
		    CHECK = false;
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (temp.equalsIgnoreCase (MIDDLE [i] [0])) // if one is already in middle column increment amount by one
			{

			    MIDDLE [i] [1] = "" + (Integer.parseInt (MIDDLE [i] [1]) + 1);
			    CHECK = true;
			    if (Integer.parseInt (MIDDLE [i] [1]) <= 0)
			    {
				for (int x = 0 ; x < 100 ; x++)
				{
				    if (VENDOR [x] [0].equalsIgnoreCase (MIDDLE [i] [0]))

					{

					    VENDOR [x] [1] = "" + (Integer.parseInt (VENDOR [x] [1]) + (Math.abs (Integer.parseInt (MIDDLE [i] [1])) + 1));
					    CHECK2 = true;

					} // if
				} // for x
				if (CHECK2 != true)
				{
				    for (int z = 0 ; z < 100 ; z++)
				    {
					if (VENDOR [z] [0].equalsIgnoreCase (""))
					{
					    VENDOR [z] [0] = temp;
					    VENDOR [z] [1] = MIDDLE [i] [1];
					}
				    }
				}
				MIDDLE [i] [1] = "1";
			    }
			} // if
		    } // for i
		} // check true
		if (CHECK == false)
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (MIDDLE [i] [0].equalsIgnoreCase ("")) // if didnt exist look for a spot to put it in the array
			{
			    MIDDLE [i] [0] = temp;
			    MIDDLE [i] [1] = "1";

			    i = 101;
			} // if

		    } // for i
		} // check false


		for (int i = 0 ; i < Prices.length ; i++)
		{
		    if (Inventory [i] [0].equalsIgnoreCase (temp))
		    {
			Inventory [i] [1] = "" + (Integer.parseInt (Inventory [i] [1]) - 1); // subtract item amount, if none remove from list
			if (Integer.parseInt (Inventory [i] [1]) <= 0)
			{
			    Inventory [i] [0] = "";
			} // if
		    } // if

		} // for i
		Vendor.clear ();
		Vendor.add ("      ITEM            Amt       Price"); // identifiers
		for (int i = 0 ; i < 100 ; i++)
		{
		    for (int b = 0 ; b < Prices.length ; b++) // for everything in price index
		    {
			if (VENDOR [i] [0].equalsIgnoreCase (Prices [b] [0]) && !VENDOR [i] [0].equalsIgnoreCase ("")) // if item exists in price index
			{
			    temporary = "";
			    temporary = VENDOR [i] [0] + "        " + VENDOR [i] [1];
			    for (int x = 0 ; x < 10 - VENDOR [i] [1].length () ; x++)
			    {
				temporary = temporary + " ";
			    }
			    temporary = temporary + Prices [b] [1];
			    Vendor.add (temporary);
			} // if
		    } // for b
		} // for i
	    } // SELL button



	    if (e.target == BUY && Vendor.getSelectedIndex () != -1 && Vendor.getItem (Vendor.getSelectedIndex ()).charAt (6) != 'I') // if target is sell and an item is selected and the item is not the identifier at the top of the list
	    {

		for (int i = 0 ; i < Prices [16] [0].length () ; i++) // take 16 character long name from list item
		{
		    temp = temp + Vendor.getItem (Vendor.getSelectedIndex ()).charAt (i);
		} // for


		for (int i = 0 ; i < 100 ; i++)
		{
		    for (int b = 0 ; b < Prices.length ; b++)
		    {
			if (temp.equalsIgnoreCase (Prices [i] [0])) // if the name is one in the price index
			{
			    CHECK = true;
			} // if
		    } // for b
		} // for i
		if (CHECK == true)
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (MIDDLE [i] [0].equalsIgnoreCase (temp)) // if in middle
			{

			    if (Integer.parseInt (MIDDLE [i] [1]) >= 0)
			    {


				for (int b = 0 ; b < 100 ; b++) // if in inventory
				{
				    if (Inventory [b] [0].equalsIgnoreCase (temp))
				    {
					Inventory [b] [1] = "" + (Integer.parseInt (Inventory [b] [1]) + Integer.parseInt (MIDDLE [i] [1]));             // if posative add to inv
					MIDDLE [i] [1] = "-1";
					CHECK2 = true;
				    } // if
				} // for b


			    } // if
			    else
			    {

				MIDDLE [i] [1] = "" + (Integer.parseInt (MIDDLE [i] [1]) - 1); // decrement one
			    }


			    if (CHECK2 = false)
			    {
				for (int b = 0 ; b < 100 ; b++)

				    {
					if (Inventory [b] [0].equalsIgnoreCase (""))
					{
					    Inventory [b] [0] = temp;
					    Inventory [b] [1] = "1";
					} // if
				    } // for
			    } // if
			    CHECK = false;
			    VENDOR [Vendor.getSelectedIndex () - 1] [1] = "" + (Integer.parseInt (VENDOR [Vendor.getSelectedIndex () - 1] [1]) - 1);
			    if (MIDDLE [i] [1].equalsIgnoreCase ("0")) // if amt is zero, remove item
			    {
				MIDDLE [i] [0] = temp;
				MIDDLE [i] [1] = "-1";
			    } // if
			} // if
		    } // for
		} // for
		if (CHECK == true) // if not already there
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (MIDDLE [i] [0].equalsIgnoreCase ("")) // if empty spot
			{
			    VENDOR [Vendor.getSelectedIndex () - 1] [1] = "" + (Integer.parseInt (VENDOR [Vendor.getSelectedIndex () - 1] [1]) - 1);

			    MIDDLE [i] [0] = temp; // add item
			    MIDDLE [i] [1] = "-1";
			    i = 101;
			} // if

		    } // for
		} // if
		Vendor.clear ();
		Vendor.add ("      ITEM            Amt       Price"); // identifiers

		for (int z = 0 ; z < 100 ; z++)
		{
		    for (int b = 0 ; b < Prices.length ; b++) // for everything in price index
		    {
			if (VENDOR [z] [0].equalsIgnoreCase (Prices [b] [0]) && !VENDOR [z] [0].equalsIgnoreCase ("") && Integer.parseInt (VENDOR [z] [1]) != 0) // if item exists in price index
			{
			    temporary = "";
			    temporary = VENDOR [z] [0] + "        " + VENDOR [z] [1];
			    for (int x = 0 ; x < 10 - VENDOR [z] [1].length () ; x++)
			    {
				temporary = temporary + " ";
			    }
			    temporary = temporary + Prices [b] [1];
			    Vendor.add (temporary);
			} // if
		    } // for b
		} // for i
	    } // BUY button
	    if (e.target == REMOVE && Middle.getSelectedIndex () != -1 && Middle.getItem (Middle.getSelectedIndex ()).charAt (6) != 'I') // if target is sell and an item is selected and the item is not the identifier at the top of the list
	    {
		for (int i = 0 ; i < Prices [16] [0].length () ; i++) // take 16 character long name from list item
		{
		    temp = temp + Middle.getItem (Middle.getSelectedIndex ()).charAt (i);
		} // for




		for (int i = 0 ; i < 100 ; i++)
		{
		    for (int b = 0 ; b < Prices.length ; b++)
		    {
			if (temp.equalsIgnoreCase (Prices [i] [0])) // if the name is one in the price index
			{
			    CHECK = true;
			} // if
		    } // for b
		} // for i

		if (CHECK == true) // if exists in index
		{
		    CHECK2 = true;


		    for (int i = 0 ; i < 100 ; i++)
		    {

			if (Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1]) > 0)
			{
			    if (Inventory [i] [0].equalsIgnoreCase (temp))
			    {


				Inventory [i] [1] = "" + (Integer.parseInt (Inventory [i] [1]) + Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1]));
				MIDDLE [Middle.getSelectedIndex () - 1] [0] = "";
				MIDDLE [Middle.getSelectedIndex () - 1] [1] = "0";
				CHECK2 = false;

			    }
			}
			else if (Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1]) < 0)
			{


			    if (VENDOR [i] [0].equalsIgnoreCase (temp))
			    {

				VENDOR [i] [1] = "" + (Integer.parseInt (VENDOR [i] [1]) + Math.abs (Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1])));
				MIDDLE [Middle.getSelectedIndex () - 1] [0] = "";
				MIDDLE [Middle.getSelectedIndex () - 1] [1] = "0";
				CHECK = false;
			    }
			}
		    } // for

		} // CHECK
		if (Integer.parseInt (MIDDLE [Middle.getSelectedIndex ()] [1]) < 0 && CHECK == true) // if vendor item and not in vendor
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (VENDOR [i] [0].equalsIgnoreCase (""))
			{
			    VENDOR [i] [0] = temp;
			    VENDOR [i] [1] = "" + (Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1]));
			    MIDDLE [Middle.getSelectedIndex () - 1] [0] = "";
			    MIDDLE [Middle.getSelectedIndex () - 1] [1] = "0";
			    i = 101;
			}
		    }
		}
		else if (Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1]) > 0 && CHECK2 == true)  // if inventory item not in inventory
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (Inventory [i] [0].equalsIgnoreCase (""))
			{
			    Inventory [i] [0] = temp;
			    Inventory [i] [1] = "" + (Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1]));
			    MIDDLE [Middle.getSelectedIndex () - 1] [0] = "";
			    MIDDLE [Middle.getSelectedIndex () - 1] [1] = "0";
			    i = 101;
			}
		    }
		}

		Middle.remove (Middle.getSelectedIndex ());
		Vendor.clear ();
		Vendor.add ("      ITEM            Amt       Price"); // identifiers
		for (int i = 0 ; i < 100 ; i++)
		{
		    for (int b = 0 ; b < Prices.length ; b++) // for everything in price index
		    {
			if (VENDOR [i] [0].equalsIgnoreCase (Prices [b] [0]) && !VENDOR [i] [0].equalsIgnoreCase ("")) // if item exists in price index
			{
			    temporary = "";
			    temporary = VENDOR [i] [0] + "        " + VENDOR [i] [1];
			    for (int x = 0 ; x < 10 - VENDOR [i] [1].length () ; x++)
			    {
				temporary = temporary + " ";
			    }
			    temporary = temporary + Prices [b] [1];
			    Vendor.add (temporary);
			} // if
		    } // for b
		} // for i
	    } // REMOVE button


	    if (e.target == CONFIRM)  // if target is sell and an item is selected and the item is not the identifier at the top of the list
	    {
		int Total = 0;
		for (int i = 0 ; i < 100 ; i++) // for all items in Middle
		{

		    for (int b = 0 ; b < Prices.length ; b++) // lookup price index
		    {
			if (Prices [b] [0].equalsIgnoreCase (MIDDLE [i] [0]))
			{
			    Total = Total + (Integer.parseInt (Prices [b] [1]) * Integer.parseInt (MIDDLE [i] [1]));

			} // if
		    } // for b
		} // for i

		if (Total < 0) // if user owes vendor
		{
		    if (Math.abs (Total) <= ((Gold * 100) + (Silver * 10) + Copper)) // if they have enough
		    {

			Gold = Gold - Math.abs (Total / 100);
			Silver = Silver - Math.abs (Total / 10 - TRANSACTION_GOLD * 10);
			Copper = Copper - Math.abs (Total - (TRANSACTION_GOLD * 100 + TRANSACTION_SILVER * 10));
			VENDOR_GOLD = VENDOR_GOLD + Math.abs (Total / 100);
			VENDOR_SILVER = VENDOR_SILVER + Math.abs (Total / 10 - TRANSACTION_GOLD * 10);
			VENDOR_COPPER = VENDOR_COPPER + Math.abs (Total - (TRANSACTION_GOLD * 100 + TRANSACTION_SILVER * 10));

			Middle.clear ();


			for (int i = 0 ; i < 100 ; i++) // for all in middle
			{
			    CHECK = true;
			    for (int b = 0 ; b < 100 ; b++) // check against all in vendor
			    {
				if (MIDDLE [i] [0].equalsIgnoreCase (Inventory [b] [0])) // if in vendor
				{

				    Inventory [b] [1] = "" + (Integer.parseInt (Inventory [b] [1]) + Math.abs (Integer.parseInt (MIDDLE [i] [1]))); // add

				    CHECK = false;
				} // if
			    } // for b
			    if (CHECK == true) // if not in vendor add name and amount
			    {
				for (int b = 0 ; b < 100 ; b++)
				{
				    if (Inventory [b] [0].equalsIgnoreCase (""))
				    {
					Inventory [b] [0] = MIDDLE [i] [0];
					Inventory [b] [1] = "" + Math.abs (Integer.parseInt (MIDDLE [i] [1]));
					b = 101;
				    } // if
				} // for b
			    } // check true
			} // for i


			for (int i = 0 ; i < 100 ; i++)
			{
			    MIDDLE [i] [0] = "";
			    MIDDLE [i] [1] = "0";
			}
			ERROR_MESSAGE = "";
		    }
		    else if (Math.abs (Total) > ((Gold * 100) + (Silver * 10) + Copper)) // if they dont have enough
		    {
			ERROR_MESSAGE = "Not Enough Coin!";
		    }
		    else
		    {

		    }
		    if (Silver > 10)
		    {
			Gold = Gold + Silver / 10;
			Silver = Silver % 10;

		    }
		}
		else if (Total > 0) // if vendor owes user
		{
		    if (Math.abs (Total) <= ((VENDOR_GOLD * 100) + (VENDOR_SILVER * 10) + VENDOR_COPPER)) // if they have enough
		    {

			VENDOR_GOLD = VENDOR_GOLD - Math.abs (Total / 100);
			VENDOR_SILVER = VENDOR_SILVER - Math.abs (Total / 10 - TRANSACTION_GOLD * 10);
			VENDOR_COPPER = VENDOR_COPPER - Math.abs (Total - (TRANSACTION_GOLD * 100 + TRANSACTION_SILVER * 10));
			Gold = Gold + Math.abs (Total / 100);
			Silver = Silver + Math.abs (Total / 10 - TRANSACTION_GOLD * 10);
			Copper = Copper + Math.abs (Total - (TRANSACTION_GOLD * 100 + TRANSACTION_SILVER * 10));
			Middle.clear ();


			for (int i = 0 ; i < 100 ; i++) // for all in middle
			{
			    CHECK = true;
			    for (int b = 0 ; b < 100 ; b++) // check against all in vendor
			    {
				if (MIDDLE [i] [0].equalsIgnoreCase (VENDOR [b] [0])) // if in vendor
				{

				    VENDOR [b] [1] = "" + (Integer.parseInt (VENDOR [b] [1]) + Math.abs (Integer.parseInt (MIDDLE [i] [1]))); // add

				    CHECK = false;
				} // if
			    } // for b
			    if (CHECK == true) // if not in vendor add name and amount
			    {
				for (int b = 0 ; b < 100 ; b++)
				{
				    if (VENDOR [b] [0].equalsIgnoreCase (""))
				    {
					VENDOR [b] [0] = MIDDLE [i] [0];
					VENDOR [b] [1] = "" + Math.abs (Integer.parseInt (MIDDLE [i] [1]));
					b = 101;
				    } // if
				} // for b
			    } // check true
			} // for i


			for (int i = 0 ; i < 100 ; i++) // clear middle array
			{
			    MIDDLE [i] [0] = "";
			    MIDDLE [i] [1] = "0";
			}
			Vendor.clear ();
			Vendor.add ("      ITEM            Amt       Price"); // identifiers
			for (int i = 0 ; i < 100 ; i++)
			{
			    for (int b = 0 ; b < Prices.length ; b++) // for everything in price index
			    {
				if (VENDOR [i] [0].equalsIgnoreCase (Prices [b] [0]) && !VENDOR [i] [0].equalsIgnoreCase ("")) // if item exists in price index
				{
				    temporary = "";
				    temporary = VENDOR [i] [0] + "        " + VENDOR [i] [1];
				    for (int x = 0 ; x < 10 - VENDOR [i] [1].length () ; x++)
				    {
					temporary = temporary + " ";
				    }
				    temporary = temporary + Prices [b] [1];
				    Vendor.add (temporary);
				} // if
			    } // for b
			} // for i
			ERROR_MESSAGE = "";

		    }
		    else if (Math.abs (Total) > ((VENDOR_GOLD * 100) + (VENDOR_SILVER * 10) + VENDOR_COPPER)) // if they dont have enough
		    {
			ERROR_MESSAGE = "Vendor Does Not Have Enough Coin!";
		    }
		    else
		    {

		    }

		}
		// silver to gold
		if (Silver > 10)
		{
		    Gold = Gold + Silver / 10;

		    Silver = Silver % 10;
		}
		if (VENDOR_SILVER > 10)
		{
		    VENDOR_GOLD = VENDOR_GOLD + VENDOR_SILVER / 10;
		    VENDOR_SILVER = VENDOR_SILVER % 10;
		}
		if (VENDOR_SILVER < 0)
		{
		    VENDOR_SILVER += 10;
		    VENDOR_GOLD--;
		}
		if (Silver < 0)
		{
		    Silver += 10;
		    Gold--;
		}

	    } // CONFIRM button









	    Personal.clear ();


	    Personal.add ("      ITEM             Amt        Price");
	    for (int i = 0 ; i < 100 ; i++)
	    {
		for (int b = 0 ; b < Prices.length ; b++)
		{
		    if (Inventory [i] [0].equalsIgnoreCase (Prices [b] [0]) && !Inventory [i] [0].equalsIgnoreCase (""))
		    {

			Personal.add (Inventory [i] [0] + "        " + Inventory [i] [1] + "          " + Prices [b] [1]);   // put inventory into personal items list
		    } // if
		} // for b
	    } // for i
	    Middle.clear ();
	    Middle.add ("      ITEM          Amt      Price");
	    for (int i = 0 ; i < 100 ; i++)
	    {

		for (int b = 0 ; b < Prices.length ; b++)
		{

		    if (MIDDLE [i] [0].equalsIgnoreCase (Prices [b] [0]) && !MIDDLE [i] [0].equalsIgnoreCase ("")) // draw everything in middle array
		    {
			Middle.add (MIDDLE [i] [0] + "      " + MIDDLE [i] [1] + "      " + " " + Prices [b] [1]);
		    } // if
		} //b
	    } //i

	    int total = 0;
	    for (int i = 0 ; i < 100 ; i++)
	    {
		for (int b = 0 ; b < Prices.length ; b++)
		{
		    if (MIDDLE [i] [0].equalsIgnoreCase (Prices [b] [0]))
		    {


			total = total + (Integer.parseInt (MIDDLE [i] [1]) * Integer.parseInt (Prices [b] [1])); // total up value of middle array


		    } // if
		} // for b
	    } // for i

	    TRANSACTION_GOLD = total / 100;
	    TRANSACTION_SILVER = total / 10 - TRANSACTION_GOLD * 10;
	    TRANSACTION_COPPER = total - (TRANSACTION_GOLD * 100 + TRANSACTION_SILVER * 10);
	    repaint ();
	} // if paint


	if (paint_screen.equalsIgnoreCase ("BlackSmith")) // blacksmith store screen
	{

	    for (int i = 0 ; i < 100 ; i++)
	    {
		if (VENDOR [i] [1].equalsIgnoreCase ("0"))
		{
		    VENDOR [i] [0] = "";
		}
	    }
	    temp = "";
	    if (e.target == BACK)
	    {
		remove (Vendor);
		remove (Personal);
		remove (Middle);
		remove (VendorGold);
		remove (VendorSilver);
		remove (VendorCopper);
		remove (PersonalGold);
		remove (PersonalSilver);
		remove (PersonalCopper);
		remove (TransactionGold);
		remove (TransactionSilver);
		remove (TransactionCopper);
		remove (BUY);
		remove (SELL);
		remove (CONFIRM);
		remove (REMOVE);
		remove (BACK);
		add (forward);

		add (left);
		add (right);
		add (fight);
		add (special);
		add (down);
		paint_screen = "General_Screen";
		repaint ();
		validate ();
	    }
	    if (e.target == SELL && Personal.getSelectedIndex () != -1 && Personal.getItem (Personal.getSelectedIndex ()).charAt (6) != 'I') // if target is sell and an item is selected and the item is not the identifier at the top of the list
	    {
		for (int i = 0 ; i < Prices [16] [0].length () ; i++) // take 16 character long name from list item
		{
		    temp = temp + Personal.getItem (Personal.getSelectedIndex ()).charAt (i);
		} // for


		for (int i = 0 ; i < 100 ; i++)
		{
		    for (int b = 0 ; b < Prices.length ; b++)
		    {
			if (temp.equalsIgnoreCase (Prices [i] [0])) // if the name is one in the price index
			{
			    CHECK = true;
			} // if
		    } // for b
		} // for i
		if (CHECK == true)
		{
		    CHECK = false;
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (temp.equalsIgnoreCase (MIDDLE [i] [0])) // if one is already in middle column increment amount by one
			{

			    MIDDLE [i] [1] = "" + (Integer.parseInt (MIDDLE [i] [1]) + 1);
			    CHECK = true;
			    if (Integer.parseInt (MIDDLE [i] [1]) <= 0)
			    {
				for (int x = 0 ; x < 100 ; x++)
				{
				    if (VENDOR [x] [0].equalsIgnoreCase (MIDDLE [i] [0]))

					{

					    VENDOR [x] [1] = "" + (Integer.parseInt (VENDOR [x] [1]) + (Math.abs (Integer.parseInt (MIDDLE [i] [1])) + 1));
					    CHECK2 = true;

					} // if
				} // for x
				if (CHECK2 != true)
				{
				    for (int z = 0 ; z < 100 ; z++)
				    {
					if (VENDOR [z] [0].equalsIgnoreCase (""))
					{
					    VENDOR [z] [0] = temp;
					    VENDOR [z] [1] = MIDDLE [i] [1];
					}
				    }
				}
				MIDDLE [i] [1] = "1";
			    }
			} // if
		    } // for i
		} // check true
		if (CHECK == false)
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (MIDDLE [i] [0].equalsIgnoreCase ("")) // if didnt exist look for a spot to put it in the array
			{
			    MIDDLE [i] [0] = temp;
			    MIDDLE [i] [1] = "1";

			    i = 101;
			} // if

		    } // for i
		} // check false


		for (int i = 0 ; i < Prices.length ; i++)
		{
		    if (Inventory [i] [0].equalsIgnoreCase (temp))
		    {
			Inventory [i] [1] = "" + (Integer.parseInt (Inventory [i] [1]) - 1); // subtract item amount, if none remove from list
			if (Integer.parseInt (Inventory [i] [1]) <= 0)
			{
			    Inventory [i] [0] = "";
			} // if
		    } // if

		} // for i
		Vendor.clear ();
		Vendor.add ("      ITEM            Amt       Price"); // identifiers
		for (int i = 0 ; i < 100 ; i++)
		{
		    for (int b = 0 ; b < Prices.length ; b++) // for everything in price index
		    {
			if (VENDOR [i] [0].equalsIgnoreCase (Prices [b] [0]) && !VENDOR [i] [0].equalsIgnoreCase ("")) // if item exists in price index
			{
			    temporary = "";
			    temporary = VENDOR [i] [0] + "        " + VENDOR [i] [1];
			    for (int x = 0 ; x < 10 - VENDOR [i] [1].length () ; x++)
			    {
				temporary = temporary + " ";
			    }
			    temporary = temporary + Prices [b] [1];
			    Vendor.add (temporary);
			} // if
		    } // for b
		} // for i
	    } // SELL button



	    if (e.target == BUY && Vendor.getSelectedIndex () != -1 && Vendor.getItem (Vendor.getSelectedIndex ()).charAt (6) != 'I') // if target is sell and an item is selected and the item is not the identifier at the top of the list
	    {

		for (int i = 0 ; i < Prices [16] [0].length () ; i++) // take 16 character long name from list item
		{
		    temp = temp + Vendor.getItem (Vendor.getSelectedIndex ()).charAt (i);
		} // for


		for (int i = 0 ; i < 100 ; i++)
		{
		    for (int b = 0 ; b < Prices.length ; b++)
		    {
			if (temp.equalsIgnoreCase (Prices [i] [0])) // if the name is one in the price index
			{
			    CHECK = true;
			} // if
		    } // for b
		} // for i
		if (CHECK == true)
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (MIDDLE [i] [0].equalsIgnoreCase (temp)) // if in middle
			{

			    if (Integer.parseInt (MIDDLE [i] [1]) >= 0)
			    {


				for (int b = 0 ; b < 100 ; b++) // if in inventory
				{
				    if (Inventory [b] [0].equalsIgnoreCase (temp))
				    {
					Inventory [b] [1] = "" + (Integer.parseInt (Inventory [b] [1]) + Integer.parseInt (MIDDLE [i] [1]));             // if posative add to inv
					MIDDLE [i] [1] = "-1";
					CHECK2 = true;
				    } // if
				} // for b


			    } // if
			    else
			    {

				MIDDLE [i] [1] = "" + (Integer.parseInt (MIDDLE [i] [1]) - 1); // decrement one
			    }


			    if (CHECK2 = false)
			    {
				for (int b = 0 ; b < 100 ; b++)

				    {
					if (Inventory [b] [0].equalsIgnoreCase (""))
					{
					    Inventory [b] [0] = temp;
					    Inventory [b] [1] = "1";
					} // if
				    } // for
			    } // if
			    CHECK = false;
			    VENDOR [Vendor.getSelectedIndex () - 1] [1] = "" + (Integer.parseInt (VENDOR [Vendor.getSelectedIndex () - 1] [1]) - 1);
			    if (MIDDLE [i] [1].equalsIgnoreCase ("0")) // if amt is zero, remove item
			    {
				MIDDLE [i] [0] = temp;
				MIDDLE [i] [1] = "-1";
			    } // if
			} // if
		    } // for
		} // for
		if (CHECK == true) // if not already there
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (MIDDLE [i] [0].equalsIgnoreCase ("")) // if empty spot
			{
			    VENDOR [Vendor.getSelectedIndex () - 1] [1] = "" + (Integer.parseInt (VENDOR [Vendor.getSelectedIndex () - 1] [1]) - 1);

			    MIDDLE [i] [0] = temp; // add item
			    MIDDLE [i] [1] = "-1";
			    i = 101;
			} // if

		    } // for
		} // if
		Vendor.clear ();
		Vendor.add ("      ITEM            Amt       Price"); // identifiers

		for (int z = 0 ; z < 100 ; z++)
		{
		    for (int b = 0 ; b < Prices.length ; b++) // for everything in price index
		    {
			if (VENDOR [z] [0].equalsIgnoreCase (Prices [b] [0]) && !VENDOR [z] [0].equalsIgnoreCase ("") && Integer.parseInt (VENDOR [z] [1]) != 0) // if item exists in price index
			{
			    temporary = "";
			    temporary = VENDOR [z] [0] + "        " + VENDOR [z] [1];
			    for (int x = 0 ; x < 10 - VENDOR [z] [1].length () ; x++)
			    {
				temporary = temporary + " ";
			    }
			    temporary = temporary + Prices [b] [1];
			    Vendor.add (temporary);
			} // if
		    } // for b
		} // for i
	    } // BUY button
	    if (e.target == REMOVE && Middle.getSelectedIndex () != -1 && Middle.getItem (Middle.getSelectedIndex ()).charAt (6) != 'I') // if target is sell and an item is selected and the item is not the identifier at the top of the list
	    {
		for (int i = 0 ; i < Prices [16] [0].length () ; i++) // take 16 character long name from list item
		{
		    temp = temp + Middle.getItem (Middle.getSelectedIndex ()).charAt (i);
		} // for




		for (int i = 0 ; i < 100 ; i++)
		{
		    for (int b = 0 ; b < Prices.length ; b++)
		    {
			if (temp.equalsIgnoreCase (Prices [i] [0])) // if the name is one in the price index
			{
			    CHECK = true;
			} // if
		    } // for b
		} // for i

		if (CHECK == true) // if exists in index
		{
		    CHECK2 = true;


		    for (int i = 0 ; i < 100 ; i++)
		    {

			if (Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1]) > 0)
			{
			    if (Inventory [i] [0].equalsIgnoreCase (temp))
			    {


				Inventory [i] [1] = "" + (Integer.parseInt (Inventory [i] [1]) + Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1]));
				MIDDLE [Middle.getSelectedIndex () - 1] [0] = "";
				MIDDLE [Middle.getSelectedIndex () - 1] [1] = "0";
				CHECK2 = false;

			    }
			}
			else if (Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1]) < 0)
			{


			    if (VENDOR [i] [0].equalsIgnoreCase (temp))
			    {

				VENDOR [i] [1] = "" + (Integer.parseInt (VENDOR [i] [1]) + Math.abs (Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1])));
				MIDDLE [Middle.getSelectedIndex () - 1] [0] = "";
				MIDDLE [Middle.getSelectedIndex () - 1] [1] = "0";
				CHECK = false;
			    }
			}
		    } // for

		} // CHECK
		if (Integer.parseInt (MIDDLE [Middle.getSelectedIndex ()] [1]) < 0 && CHECK == true) // if vendor item and not in vendor
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (VENDOR [i] [0].equalsIgnoreCase (""))
			{
			    VENDOR [i] [0] = temp;
			    VENDOR [i] [1] = "" + (Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1]));
			    MIDDLE [Middle.getSelectedIndex () - 1] [0] = "";
			    MIDDLE [Middle.getSelectedIndex () - 1] [1] = "0";
			    i = 101;
			}
		    }
		}
		else if (Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1]) > 0 && CHECK2 == true)  // if inventory item not in inventory
		{
		    for (int i = 0 ; i < 100 ; i++)
		    {
			if (Inventory [i] [0].equalsIgnoreCase (""))
			{
			    Inventory [i] [0] = temp;
			    Inventory [i] [1] = "" + (Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1]));
			    MIDDLE [Middle.getSelectedIndex () - 1] [0] = "";
			    MIDDLE [Middle.getSelectedIndex () - 1] [1] = "0";
			    i = 101;
			}
		    }
		}

		Middle.remove (Middle.getSelectedIndex ());
		Vendor.clear ();
		Vendor.add ("      ITEM            Amt       Price"); // identifiers
		for (int i = 0 ; i < 100 ; i++)
		{
		    for (int b = 0 ; b < Prices.length ; b++) // for everything in price index
		    {
			if (VENDOR [i] [0].equalsIgnoreCase (Prices [b] [0]) && !VENDOR [i] [0].equalsIgnoreCase ("")) // if item exists in price index
			{
			    temporary = "";
			    temporary = VENDOR [i] [0] + "        " + VENDOR [i] [1];
			    for (int x = 0 ; x < 10 - VENDOR [i] [1].length () ; x++)
			    {
				temporary = temporary + " ";
			    }
			    temporary = temporary + Prices [b] [1];
			    Vendor.add (temporary);
			} // if
		    } // for b
		} // for i
	    } // REMOVE button


	    if (e.target == CONFIRM)  // if target is sell and an item is selected and the item is not the identifier at the top of the list
	    {
		int Total = 0;
		for (int i = 0 ; i < 100 ; i++) // for all items in Middle
		{

		    for (int b = 0 ; b < Prices.length ; b++) // lookup price index
		    {
			if (Prices [b] [0].equalsIgnoreCase (MIDDLE [i] [0]))
			{
			    Total = Total + (Integer.parseInt (Prices [b] [1]) * Integer.parseInt (MIDDLE [i] [1]));

			} // if
		    } // for b
		} // for i

		if (Total < 0) // if user owes vendor
		{
		    if (Math.abs (Total) <= ((Gold * 100) + (Silver * 10) + Copper)) // if they have enough
		    {

			Gold = Gold - Math.abs (Total / 100);
			Silver = Silver - Math.abs (Total / 10 - TRANSACTION_GOLD * 10);
			Copper = Copper - Math.abs (Total - (TRANSACTION_GOLD * 100 + TRANSACTION_SILVER * 10));
			VENDOR_GOLD = VENDOR_GOLD + Math.abs (Total / 100);
			VENDOR_SILVER = VENDOR_SILVER + Math.abs (Total / 10 - TRANSACTION_GOLD * 10);
			VENDOR_COPPER = VENDOR_COPPER + Math.abs (Total - (TRANSACTION_GOLD * 100 + TRANSACTION_SILVER * 10));

			Middle.clear ();


			for (int i = 0 ; i < 100 ; i++) // for all in middle
			{
			    CHECK = true;
			    for (int b = 0 ; b < 100 ; b++) // check against all in vendor
			    {
				if (MIDDLE [i] [0].equalsIgnoreCase (Inventory [b] [0])) // if in vendor
				{

				    Inventory [b] [1] = "" + (Integer.parseInt (Inventory [b] [1]) + Math.abs (Integer.parseInt (MIDDLE [i] [1]))); // add

				    CHECK = false;
				} // if
			    } // for b
			    if (CHECK == true) // if not in vendor add name and amount
			    {
				for (int b = 0 ; b < 100 ; b++)
				{
				    if (Inventory [b] [0].equalsIgnoreCase (""))
				    {
					Inventory [b] [0] = MIDDLE [i] [0];
					Inventory [b] [1] = "" + Math.abs (Integer.parseInt (MIDDLE [i] [1]));
					b = 101;
				    } // if
				} // for b
			    } // check true
			} // for i


			for (int i = 0 ; i < 100 ; i++)
			{
			    MIDDLE [i] [0] = "";
			    MIDDLE [i] [1] = "0";
			}
			ERROR_MESSAGE = "";
		    }
		    else if (Math.abs (Total) > ((Gold * 100) + (Silver * 10) + Copper)) // if they dont have enough
		    {
			ERROR_MESSAGE = "Not Enough Coin!";
		    }
		    else
		    {

		    }
		    if (Silver > 10)
		    {
			Gold = Gold + Silver / 10;
			Silver = Silver % 10;

		    }
		}
		else if (Total > 0) // if vendor owes user
		{
		    if (Math.abs (Total) <= ((VENDOR_GOLD * 100) + (VENDOR_SILVER * 10) + VENDOR_COPPER)) // if they have enough
		    {

			VENDOR_GOLD = VENDOR_GOLD - Math.abs (Total / 100);
			VENDOR_SILVER = VENDOR_SILVER - Math.abs (Total / 10 - TRANSACTION_GOLD * 10);
			VENDOR_COPPER = VENDOR_COPPER - Math.abs (Total - (TRANSACTION_GOLD * 100 + TRANSACTION_SILVER * 10));
			Gold = Gold + Math.abs (Total / 100);
			Silver = Silver + Math.abs (Total / 10 - TRANSACTION_GOLD * 10);
			Copper = Copper + Math.abs (Total - (TRANSACTION_GOLD * 100 + TRANSACTION_SILVER * 10));
			Middle.clear ();


			for (int i = 0 ; i < 100 ; i++) // for all in middle
			{
			    CHECK = true;
			    for (int b = 0 ; b < 100 ; b++) // check against all in vendor
			    {
				if (MIDDLE [i] [0].equalsIgnoreCase (VENDOR [b] [0])) // if in vendor
				{

				    VENDOR [b] [1] = "" + (Integer.parseInt (VENDOR [b] [1]) + Math.abs (Integer.parseInt (MIDDLE [i] [1]))); // add

				    CHECK = false;
				} // if
			    } // for b
			    if (CHECK == true) // if not in vendor add name and amount
			    {
				for (int b = 0 ; b < 100 ; b++)
				{
				    if (VENDOR [b] [0].equalsIgnoreCase (""))
				    {
					VENDOR [b] [0] = MIDDLE [i] [0];
					VENDOR [b] [1] = "" + Math.abs (Integer.parseInt (MIDDLE [i] [1]));
					b = 101;
				    } // if
				} // for b
			    } // check true
			} // for i


			for (int i = 0 ; i < 100 ; i++) // clear middle array
			{
			    MIDDLE [i] [0] = "";
			    MIDDLE [i] [1] = "0";
			}
			Vendor.clear ();
			Vendor.add ("      ITEM            Amt       Price"); // identifiers
			for (int i = 0 ; i < 100 ; i++)
			{
			    for (int b = 0 ; b < Prices.length ; b++) // for everything in price index
			    {
				if (VENDOR [i] [0].equalsIgnoreCase (Prices [b] [0]) && !VENDOR [i] [0].equalsIgnoreCase ("")) // if item exists in price index
				{
				    temporary = "";
				    temporary = VENDOR [i] [0] + "        " + VENDOR [i] [1];
				    for (int x = 0 ; x < 10 - VENDOR [i] [1].length () ; x++)
				    {
					temporary = temporary + " ";
				    }
				    temporary = temporary + Prices [b] [1];
				    Vendor.add (temporary);
				} // if
			    } // for b
			} // for i
			ERROR_MESSAGE = "";

		    }
		    else if (Math.abs (Total) > ((VENDOR_GOLD * 100) + (VENDOR_SILVER * 10) + VENDOR_COPPER)) // if they dont have enough
		    {
			ERROR_MESSAGE = "Vendor Does Not Have Enough Coin!";
		    }
		    else
		    {

		    }

		}
		// silver to gold
		if (Silver > 10)
		{
		    Gold = Gold + Silver / 10;

		    Silver = Silver % 10;
		}
		if (VENDOR_SILVER > 10)
		{
		    VENDOR_GOLD = VENDOR_GOLD + VENDOR_SILVER / 10;
		    VENDOR_SILVER = VENDOR_SILVER % 10;
		}
		if (VENDOR_SILVER < 0)
		{
		    VENDOR_SILVER += 10;
		    VENDOR_GOLD--;
		}
		if (Silver < 0)
		{
		    Silver += 10;
		    Gold--;
		}
	    } // CONFIRM button









	    Personal.clear ();


	    Personal.add ("      ITEM             Amt        Price");
	    for (int i = 0 ; i < 100 ; i++)
	    {
		for (int b = 0 ; b < Prices.length ; b++)
		{
		    if (Inventory [i] [0].equalsIgnoreCase (Prices [b] [0]) && !Inventory [i] [0].equalsIgnoreCase (""))
		    {

			Personal.add (Inventory [i] [0] + "        " + Inventory [i] [1] + "          " + Prices [b] [1]);   // put inventory into personal items list
		    } // if
		} // for b
	    } // for i
	    Middle.clear ();
	    Middle.add ("      ITEM          Amt      Price");
	    for (int i = 0 ; i < 100 ; i++)
	    {

		for (int b = 0 ; b < Prices.length ; b++)
		{

		    if (MIDDLE [i] [0].equalsIgnoreCase (Prices [b] [0]) && !MIDDLE [i] [0].equalsIgnoreCase ("")) // draw everything in middle array
		    {
			Middle.add (MIDDLE [i] [0] + "      " + MIDDLE [i] [1] + "      " + " " + Prices [b] [1]);
		    } // if
		} //b
	    } //i

	    int total = 0;
	    for (int i = 0 ; i < 100 ; i++)
	    {
		for (int b = 0 ; b < Prices.length ; b++)
		{
		    if (MIDDLE [i] [0].equalsIgnoreCase (Prices [b] [0]))
		    {


			total = total + (Integer.parseInt (MIDDLE [i] [1]) * Integer.parseInt (Prices [b] [1])); // total up value of middle array


		    } // if
		} // for b
	    } // for i

	    TRANSACTION_GOLD = total / 100;
	    TRANSACTION_SILVER = total / 10 - TRANSACTION_GOLD * 10;
	    TRANSACTION_COPPER = total - (TRANSACTION_GOLD * 100 + TRANSACTION_SILVER * 10);
	    repaint ();

	} // if paint


	if (e.target == proceed) //if the proceed button is clicked
	{
	    remove (proceed); //remove proceed button

	    //adds login, signup, username, and password
	    add (login);
	    add (signup);
	    add (username);
	    add (password);
	    password.setEchoChar ('*');
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
	    newPassword.setEchoChar ('*'); // make fields unreadable
	    confirmPassword.setEchoChar ('*');

	    paint_screen = "sign up"; //paints the screen to the signup screen

	    repaint ();
	}


	if (e.target == login) //if the user presses the login button
	{
	    user = username.getText (); //gets data from the username textfield
	    pass = password.getText (); //gets data from the password textfield
	    pass = Cipher.encrypt (pass, 2); //encrypts the password
	    File User_File = new File ("GameFiles\\Users\\" + user); //file where user data is stored

	    if (User_File.exists () && User_File.isDirectory ()) //if the file exists and it is a directory
	    {
		User_File = new File ("GameFiles\\Users\\" + user + "\\Password.CWF"); //user file finds the password
		if (User_File.exists ()) //if the password file exists
		{

		    try //try
		    {
			BufferedReader in = new BufferedReader (new FileReader (User_File)); //reads the password file
			String temp = in.readLine (); //temp variable reads the line of the file\

			if (temp.equals (pass)) //if the read line is equal to the correctly inputted password
			{
			    name = user;
			    paint_screen = "USER";
			    Main_Screen ();
			    remove (username);
			    remove (password);      // danny's method
			    remove (login);
			    remove (signup);
			    repaint ();
			}
			else
			{
			    ERROR_MESSAGE = "Wrong Password";
			}
			in.close (); //closes the file reader
		    }
		    catch (IOException q)  //error message
		    {
			new Message ("Error code : 0002  | Check error manual");
		    }
		}
		else
		{
		    ERROR_MESSAGE = "User does not exist!";
		    repaint ();
		}

	    }
	}


	if (e.target == confirm) //if the confirm button is pressed
	{
	    newUser = newUsername.getText (); //gets data from the new username textfield
	    newPass = newPassword.getText (); //gets data from the new password textfield
	    confirmPass = confirmPassword.getText (); //gets data from the confirm password textfield
	    File out = new File ("GameFiles//Users//" + newUser);
	    if (out.exists ())
	    {

		ERROR_MESSAGE = "User already exists!";
	    }
	    else if (confirmPass.equalsIgnoreCase (""))
	    {
		ERROR_MESSAGE = "Create a Password";
	    }
	    else if (!newPass.equalsIgnoreCase (confirmPass))
	    {
		ERROR_MESSAGE = "Passwords do not match";
	    }

	    else
	    {
		remove (newUsername);
		remove (newPassword);
		remove (confirmPassword);
		remove (confirm);
		remove (back);
		paint_screen = "character_creation";
		if (newPass.equals (confirmPass))
		{

		    Create_Character (Cipher.encrypt (newPass, 2), newUser); // character creation   tylers method
		}

		NEW = true;
	    }
	    repaint ();
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
	    repaint ();
	}


	if (e.target instanceof Button)
	{
	    if (e.target == cmd_Delete) // delete character, ask screen
	    {
		if (lst_Characters.getSelectedIndex () == -1) // if no character is selected to delete
		{

		    error = "No character selected";

		    repaint ();
		}
		else
		{
		    add (Yes);
		    add (No);
		    Check = true;
		    repaint ();
		}
	    } // target = delete
	    else if (e.target == cmd_Play) // Play game with set character
	    {

		g.setFont (fnt_Main_Screen_Writing);

		if (lst_Characters.getSelectedIndex () == -1) // if no character is selected
		{

		    error = "Select a character";
		    repaint ();


		}
		else if (lst_Worlds.getSelectedIndex () == -1) // if no world is selected
		{

		    error = "Select a world";
		    repaint ();

		}
		else // if all conditions are met start game
		{
		    g.setColor (Color.black);
		    paint_screen = "General_Screen";
		    File temp = new File ("GameFiles\\Users\\" + name + "\\Worlds\\" + lst_Worlds.getItem (lst_Worlds.getSelectedIndex ()) + ".CWF");
		    WORLD = lst_Worlds.getItem (lst_Worlds.getSelectedIndex ());
		    read_World (temp); // read world file into map array
		    clip.stop ();
		    clip = null;
		    remove (cmd_Reset);
		    remove (cmd_Play);
		    remove (cmd_Delete); // remove current screen items
		    remove (cmd_Create);
		    remove (lst_Worlds);
		    remove (lst_Characters);
		    try
		    {
			for (int i = 0 ; i < 100 ; i++)
			{
			    Inventory [i] [1] = "0";
			    Inventory [i] [0] = "";
			}
			temp = new File ("GameFiles\\Users\\" + name + "\\Worlds\\" + lst_Worlds.getItem (lst_Worlds.getSelectedIndex ()) + "_INFO.CWF");
			in = new BufferedReader (new FileReader (temp));
			ln1 = Cipher.decrypt (in.readLine (), 2);
			coordinate_x = Integer.parseInt ("" + ln1.charAt (0) + ln1.charAt (1) + ln1.charAt (2)); // player coordinates
			coordinate_y = Integer.parseInt ("" + ln1.charAt (3) + ln1.charAt (4) + ln1.charAt (5));
			castle_pop [0] = Integer.parseInt (Cipher.decrypt (in.readLine (), 2));
			castle_pop [1] = Integer.parseInt (Cipher.decrypt (in.readLine (), 2));
			castle_pop [2] = Integer.parseInt (Cipher.decrypt (in.readLine (), 2)); // castle populations
			castle_pop [3] = Integer.parseInt (Cipher.decrypt (in.readLine (), 2));
			in.close ();


			temp = new File ("Gamefiles\\Users\\" + name + "\\Characters\\" + (lst_Characters.getItem (lst_Characters.getSelectedIndex ())) + "\\Inventory.CWF");
			in = new BufferedReader (new FileReader (temp));
			String ln = in.readLine ();
			while (ln != null)
			{
			    for (int i = 0 ; i < 100 ; i++)
			    {
				if (Inventory [i] [0].equalsIgnoreCase (""))
				{
				    Inventory [i] [0] = ln;
				    ln = in.readLine ();
				    if (ln != null)
				    {
					Inventory [i] [1] = Cipher.decrypt (ln, 2);
				    }
				    i = 100;
				}
			    }
			    ln = in.readLine ();
			} // while




			temp = new File ("GameFiles\\Users\\" + name + "\\Characters\\" + (lst_Characters.getItem (lst_Characters.getSelectedIndex ())) + "\\character_data.CWF");
			in = new BufferedReader (new FileReader (temp));
			for (int q = 0 ; q < 15 ; q++)
			{

			    ln = in.readLine ();
			    if (q == 7)
			    {
				ln = ln;
			    }
			    else
			    {
				ln = Cipher.decrypt (ln, 2);
			    }

			    User_Data [q] = ln; // read user information

			}
			in.close ();
			lvl = Integer.parseInt (User_Data [12]);
			EXP = Integer.parseInt (User_Data [13]);


			temp = new File ("GameFiles\\Users\\" + name + "\\Characters\\" + (lst_Characters.getItem (lst_Characters.getSelectedIndex ())) + "\\Money.CWF");
			in = new BufferedReader (new FileReader (temp));
			ln = "";
			ln = in.readLine ();

			Gold = Integer.parseInt (Cipher.decrypt (ln, 2));
			ln = in.readLine ();
			Silver = Integer.parseInt (Cipher.decrypt (ln, 2)); // read money file
			ln = in.readLine ();
			Copper = Integer.parseInt (Cipher.decrypt (ln, 2));
			in.close ();


			temp = new File ("GameFiles\\Users\\" + name + "\\Characters\\" + (lst_Characters.getItem (lst_Characters.getSelectedIndex ())) + "\\img.CWF");
			in = new BufferedReader (new FileReader (temp));  // read image file
			img = Cipher.decrypt (in.readLine (), 2);
			in.close ();
		    } // try
		    catch (IOException f)
		    {

		    } // catch
		    Periodic.start ();
		    g.fillRect (0, 0, getSize ().width, getSize ().height);
		    General_Screen ();
		}

	    } // target = play
	    else if (e.target == cmd_Create) // create new character
	    {
		paint_screen = "Character_Creation";
		lst_Characters.clear ();
		remove (cmd_Reset);
		remove (cmd_Play);
		remove (cmd_Delete);
		remove (cmd_Create);
		remove (lst_Worlds);
		remove (lst_Characters);
		paint_screen = "character_creation";
		Create_Character (Password, name); // character creation   tylers method
		g.fillRect (0, 0, getSize ().width, getSize ().height);
	    } // target = create
	    else if (e.target == cmd_Reset) // reset world
	    {
		File temp;
		lst_String = (lst_Worlds.getItem (lst_Worlds.getSelectedIndex ()) + ".CWF");
		temp = new File ("GameFiles\\Users\\" + name + "\\Worlds\\" + lst_String);

		temp.delete ();
		World_Creation (temp);

	    } // target = reset
	    else if (e.target == Yes) // yes delete character
	    {
		File temp;
		if (lst_Characters.getSelectedIndex () >= 0)
		{
		    lst_String = (lst_Characters.getItem (lst_Characters.getSelectedIndex ()));
		    lst_Characters.remove (lst_String = (lst_Characters.getItem (lst_Characters.getSelectedIndex ())));
		    temp = new File ("GameFiles\\Users\\" + name + "\\Characters\\" + lst_String);

		    temp.delete (); // delete character
		    Health = 0;
		    Mana = 0;
		    Wealth = 0; // reset stats
		    Clan = "";

		}
		remove (Yes);
		remove (No);
		g.setColor (Color.black); // get rid of are you sure? information
		g.drawString ("Are you sure ? ", Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (45, y));
		Check = false;




		repaint ();


	    } // target = yes
	    else if (e.target == No) // No dont delete character
	    {
		remove (Yes);
		remove (No);
		g.setColor (Color.black);
		g.drawString ("Are you sure ? ", Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (45, y));
		Check = false;
		repaint ();
	    } // target = no
	} // instance of button


	if (e.target == forward)
	{
	    if (map [coordinate_x] [coordinate_y] [0] == 21 | map [coordinate_x] [coordinate_y] [0] == 22 | map [coordinate_x] [coordinate_y] [0] == 23 | map [coordinate_x] [coordinate_y] [0] == 24 && map [coordinate_x] [coordinate_y - 1] [0] == 41 | map [coordinate_x] [coordinate_y - 1] [0] == 42 | map [coordinate_x] [coordinate_y - 1] [0] == 43 | map [coordinate_x] [coordinate_y - 1] [0] == 44)
	    {

	    }
	    else
	    {
		coordinate_y--;
	    }
	    if (coordinate_y < 0)
	    {
		coordinate_y++;
	    }
	    mouseclick.play ();
	    repaint ();
	    this.requestFocus ();


	}


	else if (e.target == left)
	{
	    if (map [coordinate_x] [coordinate_y] [0] == 21 | map [coordinate_x] [coordinate_y] [0] == 22 | map [coordinate_x] [coordinate_y] [0] == 23 | map [coordinate_x] [coordinate_y] [0] == 24 && map [coordinate_x - 1] [coordinate_y] [0] == 41 | map [coordinate_x - 1] [coordinate_y] [0] == 42 | map [coordinate_x - 1] [coordinate_y] [0] == 43 | map [coordinate_x - 1] [coordinate_y] [0] == 44)
	    {

	    }
	    else
	    {
		coordinate_x--;
	    }
	    if (coordinate_x < 0)
	    {
		coordinate_x++;
	    }
	    mouseclick.play ();
	    repaint ();
	    this.requestFocus ();

	}


	else if (e.target == right)
	{
	    if (map [coordinate_x] [coordinate_y] [0] == 21 | map [coordinate_x] [coordinate_y] [0] == 22 | map [coordinate_x] [coordinate_y] [0] == 23 | map [coordinate_x] [coordinate_y] [0] == 24 && map [coordinate_x + 1] [coordinate_y] [0] == 41 | map [coordinate_x + 1] [coordinate_y] [0] == 42 | map [coordinate_x + 1] [coordinate_y] [0] == 43 | map [coordinate_x + 1] [coordinate_y] [0] == 44)
	    {

	    }
	    else
	    {
		coordinate_x++;
	    }
	    if (coordinate_x > 300)
	    {
		coordinate_x--;

	    }

	    mouseclick.play ();
	    repaint ();
	    this.requestFocus ();

	}


	else if (e.target == down)
	{
	    if (map [coordinate_x] [coordinate_y] [0] == 21 | map [coordinate_x] [coordinate_y] [0] == 22 | map [coordinate_x] [coordinate_y] [0] == 23 | map [coordinate_x] [coordinate_y] [0] == 24 && map [coordinate_x] [coordinate_y + 1] [0] == 41 | map [coordinate_x] [coordinate_y + 1] [0] == 42 | map [coordinate_x] [coordinate_y + 1] [0] == 43 | map [coordinate_x] [coordinate_y + 1] [0] == 44)
	    {

	    }
	    else
	    {
		coordinate_y++;
	    }
	    if (coordinate_y > 300)
	    {
		coordinate_y--;
	    }
	    mouseclick.play ();
	    repaint ();
	    this.requestFocus ();

	}


	else if (e.target == fight)
	{
	    String tmp = "";
	    switch (map [coordinate_x] [coordinate_y] [2])
	    {
		case 1:
		    tmp = "AIR";
		    break;
		case 2:
		    tmp = "WATER";
		    break;
		case 3:
		    tmp = "EARTH";

		    break;
		case 4:
		    tmp = "FIRE";
		    break;

	    }
	    if (tmp.equalsIgnoreCase (User_Data [14]))
	    {

	    }
	    else
	    {
		fight ();
	    }
	}


	//call these methods when the special button changes labels
	else if (e.target == special && special.getLabel ().equals ("Hunt"))
	{
	    this.requestFocus ();
	    hunting ();
	}


	else if (e.target == special && special.getLabel ().equals ("Fish"))
	{
	    fishing ();
	    this.requestFocus ();
	}


	else if (e.target == special && special.getLabel ().equals ("Mine"))
	{
	    mining ();
	    this.requestFocus ();
	}


	else if (e.target == special && special.getLabel ().equals ("Chop Wood"))
	{
	    woodcutting ();
	    this.requestFocus ();


	}


	else if (e.target == special && special.getLabel ().equals ("Blacksmith"))
	{
	    paint_screen = "blacksmith";

	    blacksmith ();
	    remove (down);
	    remove (left);
	    remove (right);
	    remove (forward);
	    remove (special);
	    remove (fight);
	}


	else if (e.target == special && special.getLabel ().equals ("School"))
	{
	    paint_screen = "school";

	    school ();
	    remove (down);
	    remove (left);
	    remove (right);
	    remove (forward);
	    remove (special);
	    remove (fight);
	}


	else if (e.target == special && special.getLabel ().equals ("Store"))
	{
	    paint_screen = "store";
	    store ();
	    remove (down);
	    remove (left);
	    remove (right);
	    remove (forward);
	    remove (special);
	    remove (fight);
	}


	else if (e.target == special && special.getLabel ().equals ("Enter"))
	{
	}


	X = coordinate_x;
	Y = coordinate_y;
	this.requestFocus ();
	return true;
    } //action method


    public void playClip ()  //plays theme music           nico
    {
	clip.loop ();
    }


    public void buttons ()  //a method that adds all the buttons onto the display
    {
	add (create);
	add (CharacterName);
	add (ClassName);
	add (message);
	add (pointsavailable);

	add (armour);
	add (strength);
	add (agility);
	add (NameLabel);

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
	add (health);
	add (Healthamount);
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


	ClassName.setText ("Knight"); //sets current class to knight
	WeaponName.setText ("Weapon: 1");
	repaint ();

    } // buttons


    public void setScreen ()  //a method to set up the current screen
    {
	if (paint_screen == "Fight_Screen")
	{
	    add (attack);
	    add (spell);
	    add (defend);
	    add (change_weapon);
	    add (flee);
	    add (potions);
	    add (change_magic);
	    add (whos_turn);
	    add (current_user_health);
	    add (current_enemy_health);
	    add (current_usermana_label);
	    add (current_enemymana_label);
	    add (last_move);
	    add (lastuser_move);

	}


	else if (paint_screen == "Change_Weapon_Screen")
	{
	    add (fightbutton);
	    add (weapon_selection);
	    add (weapon_up);
	    add (weapon_down);

	}


	else if (paint_screen == "Magic_Selection_Screen")
	{
	    add (fightbutton);
	    add (magic_up);
	    add (magic_down);
	    add (magic_selection);
	}


	else if (paint_screen == "Potion_Screen")
	{
	    add (fightbutton);
	    add (potion_selection);
	    add (potion_up);
	    add (potion_down);
	    add (use_potion);
	}


	else if (paint_screen == "Win_Screen")
	{
	    add (winner);
	    add (next_move);
	}
    }


    public void inventory ()
    {
	add (LIST);
	LIST.clear ();
	add (drop);
	add (info);
	add (information);
	information.setText ("");
	if (!paint_screen.equalsIgnoreCase ("save screen") && !pre_paint.equalsIgnoreCase ("mp3") && !paint_screen.equalsIgnoreCase ("stats screen") && !paint_screen.equalsIgnoreCase ("inventory screen"))
	{
	    pre_paint = paint_screen;
	}


	paint_screen = "inventory screen";
	for (int i = 0 ; i < Inventory.length ; i++)
	{
	    if (!Inventory [i] [0].equalsIgnoreCase ("") && !Inventory [i] [1].equalsIgnoreCase ("0"))
	    {
		LIST.add (Inventory [i] [0] + "       " + Inventory [i] [1]);

	    }
	}


	repaint ();

	validate ();
    } //inventory method


    public void stats ()
    {
	LIST.clear ();
	if (!paint_screen.equalsIgnoreCase ("save screen") && !pre_paint.equalsIgnoreCase ("mp3") && !paint_screen.equalsIgnoreCase ("Inventory screen") && !paint_screen.equalsIgnoreCase ("stats screen"))
	{
	    pre_paint = paint_screen;
	}


	paint_screen = "stats screen";

	repaint ();
    } //stats screen method


    public void saveGame ()
    {
	if (!paint_screen.equalsIgnoreCase ("stats screen") && !pre_paint.equalsIgnoreCase ("mp3") && !paint_screen.equalsIgnoreCase ("Inventory screen") && !paint_screen.equalsIgnoreCase ("save screen"))
	{
	    pre_paint = paint_screen;
	}


	paint_screen = "save screen";
	N = 0;
	repaint ();
	try
	{
	    out = new PrintWriter (new FileWriter ("GameFiles//Users//" + name + "//Characters//" + User_Data [5] + "//character_data.CWF"));
	    for (int b = 0 ; b < 15 ; b++) // output user data
	    {
		if (b == 7)
		{
		    out.println (User_Data [b]);
		}
		else
		{
		    out.println (Cipher.encrypt (User_Data [b], 2));
		}

	    } // for
	    out.close ();
	    N = 1;
	    repaint ();
	    out = new PrintWriter (new FileWriter ("GameFiles//Users//" + name + "//Characters//" + User_Data [5] + "//Inventory.CWF"));
	    for (int i = 0 ; i < 100 ; i++)
	    {
		if (!Inventory [i] [0].equalsIgnoreCase (""))
		{
		    for (int b = 0 ; b < Prices.length ; b++)
		    {
			if (Inventory [i] [0].equalsIgnoreCase (Prices [b] [0]))
			{
			    out.println (Inventory [i] [0]);
			    out.println (Cipher.encrypt (Inventory [i] [1], 2));
			}
		    }

		} // if something
	    } // for i

	    out.close ();
	    N = 2;
	    repaint ();
	    out = new PrintWriter (new FileWriter ("GameFiles//Users//" + name + "//Characters//" + User_Data [5] + "//Magic.CWF"));

	    out.close ();
	    out = new PrintWriter (new FileWriter ("GameFiles//Users//" + name + "//Characters//" + User_Data [5] + "//Money.CWF"));
	    out.println (Cipher.encrypt ("" + Gold, 2));
	    out.println (Cipher.encrypt ("" + Silver, 2));
	    out.println (Cipher.encrypt ("" + Copper, 2));
	    out.close ();
	    out = new PrintWriter (new FileWriter ("GameFiles//Users//" + name + "//Worlds//" + WORLD + "_INFO.CWF"));
	    temp = formatte.format (coordinate_x) + "" + (formatte.format (coordinate_y));
	    out.println (Cipher.encrypt (temp, 2));
	    out.println (Cipher.encrypt ("" + castle_pop [0], 2));
	    out.println (Cipher.encrypt ("" + castle_pop [1], 2));
	    out.println (Cipher.encrypt ("" + castle_pop [2], 2));
	    out.println (Cipher.encrypt ("" + castle_pop [3], 2));
	    out.close ();
	    N = 3;
	    repaint ();
	    out = new PrintWriter (new FileWriter ("GameFiles//Users//" + name + "//Worlds//" + WORLD + ".CWF"));
	    for (int c = 0 ; c < 3 ; c++)
	    {
		for (int b = 0 ; b < 300 ; b++) // for y-axis of map
		{
		    for (int z = 0 ; z < 3 ; z++)
		    {
			for (int i = 0 ; i < 100 ; i++) // for x-axis of map
			{
			    out.print (Cipher.encrypt ("" + formatter.format (map [i + (z * 100)] [b] [c]), 2));

			} // x-axis of map
			out.println ();
		    } // for 3 rounds of output per line

		} // for y-axis of map

	    } // z-axis of map

	    // output world <--

	    out.close ();
	    N = 4;
	    repaint ();
	}


	catch (IOException e)
	{

	}


	repaint ();
    } //saveGame method


    public void addschoolbuttons ()
    {
	if (paint_screen == "School_welcome_screen") //if on the welcome screen add continue button
	{
	    add (continuebutton); //a button that brings the user to the purchasing screen
	}


	else if (paint_screen == "School_screen") //adds items to the screen where purchases take place
	{
	    add (addHealth);
	    add (subHealth);
	    add (addMana);
	    add (subMana);
	    add (addStamina);
	    add (subStamina);
	    add (addStrength);
	    add (subStrength);
	    add (backbutton);
	    add (buy);
	    remove (continuebutton);
	}


	else if (paint_screen == "General_screen") //returns to the general screen and removes all buttons, labels, etc.
	{
	    remove (addHealth);
	    remove (subHealth);
	    remove (addMana);
	    remove (subMana);
	    remove (addStamina);
	    remove (subStamina);
	    remove (addStrength);
	    remove (subStrength);
	    remove (backbutton);
	    remove (buy);
	}
    } //end of addbuttons ()



    public void paint (Graphics g)
    {

	x = this.getSize ().width; //gets the width of the screen
	y = this.getSize ().height; //gets the height of the screem
	Font buttonFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (2, y));
	Font titleFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (4, y));
	Font labelFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (2, y));
	Font k = new Font ("Dialog", Font.BOLD, Sizing_Class.X_Pos (6, y));

	// if less then specific heap memory, garbage collection
	if (paint_screen.equalsIgnoreCase ("MP3"))
	{
	    MP3.setSize (Sizing_Class.X_Pos (30, x), Sizing_Class.Y_Pos (80, y));
	    MP3.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (10, y));
	    PLAY.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (20, y));
	    PLAY.setLocation (Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (40, y));
	    g.setColor (Color.red);
	    g.drawString ("MP3 PLAYER IN GAME. Press b to go back...", Sizing_Class.X_Pos (30, x), Sizing_Class.Y_Pos (10, y));
	}


	//draws inventory screen components
	if (paint_screen.equalsIgnoreCase ("inventory screen"))
	{
	    //LIST for inventory
	    g.setColor (Color.green);
	    Font h = new Font ("Monospaced", Font.BOLD, Sizing_Class.X_Pos (4, y));
	    g.setFont (h);
	    g.drawString ("Inventory", Sizing_Class.X_Pos (40, x), Sizing_Class.X_Pos (6, y));
	    LIST.setSize (Sizing_Class.X_Pos (30, x), Sizing_Class.X_Pos (100, y));
	    LIST.setLocation (0, 0);

	    //instructions
	    Font i = new Font ("Monospaced", Font.BOLD, Sizing_Class.X_Pos (2, y));
	    g.setFont (i);
	    g.drawString ("Click on an inventory item", Sizing_Class.X_Pos (35, x), Sizing_Class.X_Pos (15, y));
	    g.drawString ("Press 'info' to learn about the item", Sizing_Class.X_Pos (35, x), Sizing_Class.X_Pos (25, y));
	    g.drawString ("Press 'b' or 'B' to go back", Sizing_Class.X_Pos (35, x), Sizing_Class.X_Pos (35, y));

	    //info button
	    info.setFont (h);
	    info.setLocation (Sizing_Class.X_Pos (35, x), Sizing_Class.X_Pos (50, y));
	    info.setSize (Sizing_Class.X_Pos (16, x), Sizing_Class.X_Pos (15, y));
	    info.setForeground (Color.green);
	    info.setBackground (Color.black);

	    //drop button
	    drop.setFont (h);
	    drop.setLocation (Sizing_Class.X_Pos (35, x), Sizing_Class.X_Pos (70, y));
	    drop.setSize (Sizing_Class.X_Pos (16, x), Sizing_Class.X_Pos (15, y));
	    drop.setForeground (Color.green);
	    drop.setBackground (Color.black);

	    //text area
	    information.setLocation (Sizing_Class.X_Pos (60, x), 0);
	    information.setSize (Sizing_Class.X_Pos (40, x), Sizing_Class.X_Pos (100, y));
	}


	else if (paint_screen.equalsIgnoreCase ("stats screen"))
	{
	    //draws stats screen components
	    remove (info);
	    remove (information);
	    remove (LIST);
	    remove (drop);

	    k = new Font ("Monospaced", Font.BOLD, Sizing_Class.X_Pos (4, y));
	    g.setFont (k);
	    g.setColor (Color.green);
	    g.drawString ("Weapon: " + User_Data [7], 5, Sizing_Class.X_Pos (5, y));
	    g.drawString ("Health: " + User_Data [0], 5, Sizing_Class.X_Pos (20, y));
	    g.drawString ("Mana: " + User_Data [10], 5, Sizing_Class.X_Pos (35, y));
	    g.drawString ("Armour: " + User_Data [2], 5, Sizing_Class.X_Pos (50, y));
	    g.drawString ("Strength: " + User_Data [4], 5, Sizing_Class.X_Pos (65, y));
	    g.drawString ("Agility: " + User_Data [3], 5, Sizing_Class.X_Pos (80, y));
	    g.drawString ("Wealth: " + (Gold * 100 + Silver * 10 + Copper), 5, Sizing_Class.X_Pos (95, y));
	}


	if (paint_screen.equalsIgnoreCase ("Fight_Screen")) //if the current screen is the fight screen
	{
	    setBackground (Color.lightGray); //sets the background of the display to light gray

	    //sets fonts for buttons, titles, labels

	    //draws box in the middle of screen outlining the controls for the user
	    g.setColor (Color.gray);
	    g.fillRect (Sizing_Class.X_Pos (41, x), Sizing_Class.Y_Pos (0, y), Sizing_Class.X_Pos (18, x), Sizing_Class.Y_Pos (100, y));
	    g.setColor (Color.red);
	    g.drawRect (Sizing_Class.X_Pos (41, x), Sizing_Class.Y_Pos (0, y), Sizing_Class.X_Pos (18, x), Sizing_Class.Y_Pos (100, y));


	    //sets sizes of buttons, labels, etc.
	    attack.setSize (Sizing_Class.X_Pos (8, x), Sizing_Class.Y_Pos (5, y));
	    spell.setSize (Sizing_Class.X_Pos (12, x), Sizing_Class.Y_Pos (5, y));
	    defend.setSize (Sizing_Class.X_Pos (8, x), Sizing_Class.Y_Pos (5, y));
	    change_weapon.setSize (Sizing_Class.X_Pos (16, x), Sizing_Class.Y_Pos (5, y));
	    flee.setSize (Sizing_Class.X_Pos (8, x), Sizing_Class.Y_Pos (5, y));
	    potions.setSize (Sizing_Class.X_Pos (8, x), Sizing_Class.Y_Pos (5, y));
	    change_magic.setSize (Sizing_Class.X_Pos (14, x), Sizing_Class.Y_Pos (5, y));
	    whos_turn.setSize (Sizing_Class.Y_Pos (11, x), Sizing_Class.Y_Pos (3, y));
	    current_user_health.setSize (Sizing_Class.Y_Pos (11, x), Sizing_Class.Y_Pos (3, y));
	    current_enemy_health.setSize (Sizing_Class.Y_Pos (11, x), Sizing_Class.Y_Pos (3, y));
	    current_usermana_label.setSize (Sizing_Class.Y_Pos (11, x), Sizing_Class.Y_Pos (3, y));
	    last_move.setSize (Sizing_Class.Y_Pos (50, x), Sizing_Class.Y_Pos (3, y));
	    lastuser_move.setSize (Sizing_Class.Y_Pos (25, x), Sizing_Class.Y_Pos (3, y));
	    current_enemymana_label.setSize (Sizing_Class.Y_Pos (11, x), Sizing_Class.Y_Pos (3, y));


	    //sets locations of buttons, labels, etc.
	    attack.setLocation (Sizing_Class.X_Pos (46, x), Sizing_Class.Y_Pos (5, y));
	    spell.setLocation (Sizing_Class.X_Pos (44, x), Sizing_Class.Y_Pos (11, y));
	    defend.setLocation (Sizing_Class.X_Pos (46, x), Sizing_Class.Y_Pos (17, y));
	    change_weapon.setLocation (Sizing_Class.X_Pos (42, x), Sizing_Class.Y_Pos (23, y));
	    flee.setLocation (Sizing_Class.X_Pos (46, x), Sizing_Class.Y_Pos (41, y));
	    potions.setLocation (Sizing_Class.X_Pos (46, x), Sizing_Class.Y_Pos (35, y));
	    change_magic.setLocation (Sizing_Class.X_Pos (43, x), Sizing_Class.Y_Pos (29, y));
	    whos_turn.setLocation (Sizing_Class.X_Pos (42, x), Sizing_Class.Y_Pos (95, y));
	    current_user_health.setLocation (Sizing_Class.X_Pos (18, x), Sizing_Class.Y_Pos (10, y));
	    current_enemy_health.setLocation (Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (10, y));
	    current_usermana_label.setLocation (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (65, y));
	    current_enemymana_label.setLocation (Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (65, y));
	    last_move.setLocation (Sizing_Class.X_Pos (65, x), Sizing_Class.Y_Pos (50, y));
	    lastuser_move.setLocation (Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (50, y));


	    //sets backgound and foreground colors
	    whos_turn.setBackground (Color.gray);
	    current_user_health.setBackground (Color.lightGray);
	    current_enemy_health.setBackground (Color.lightGray);
	    current_usermana_label.setBackground (Color.lightGray);
	    current_enemymana_label.setBackground (Color.lightGray);
	    last_move.setBackground (Color.lightGray);
	    lastuser_move.setBackground (Color.lightGray);

	    current_user_health.setForeground (Color.red);
	    current_enemy_health.setForeground (Color.red);
	    current_usermana_label.setForeground (Color.blue);
	    current_enemymana_label.setForeground (Color.blue);
	    last_move.setForeground (Color.red);
	    lastuser_move.setForeground (Color.red);

	    //sets Fonts for buttons, labels, and titles
	    attack.setFont (buttonFont);
	    spell.setFont (buttonFont);
	    defend.setFont (buttonFont);
	    change_weapon.setFont (buttonFont);
	    flee.setFont (buttonFont);
	    potions.setFont (buttonFont);
	    change_magic.setFont (buttonFont);
	    whos_turn.setFont (labelFont);
	    current_user_health.setFont (labelFont);
	    current_enemy_health.setFont (labelFont);
	    current_usermana_label.setFont (labelFont);
	    current_enemymana_label.setFont (labelFont);
	    last_move.setFont (labelFont);
	    lastuser_move.setFont (labelFont);

	    //Health Bar images and rectangles
	    g.setColor (Color.red);
	    g.fillRect (Sizing_Class.X_Pos (19, x), Sizing_Class.Y_Pos (4, y), Sizing_Class.Y_Pos (current_user_healthbar * 11 / userHp, x), Sizing_Class.Y_Pos (5, y));
	    g.fillRect (Sizing_Class.X_Pos (71, x), Sizing_Class.Y_Pos (4, y), Sizing_Class.Y_Pos (current_enemy_healthbar * 11 / enemyHp, x), Sizing_Class.Y_Pos (5, y));
	    g.drawImage (health_image, Sizing_Class.X_Pos (14, x), Sizing_Class.Y_Pos (1, y), Sizing_Class.Y_Pos (20, x), Sizing_Class.Y_Pos (13, y), this);
	    g.drawImage (health_image, Sizing_Class.X_Pos (66, x), Sizing_Class.Y_Pos (1, y), Sizing_Class.Y_Pos (20, x), Sizing_Class.Y_Pos (13, y), this);

	    //Mana bar images and rectangles
	    g.setColor (Color.blue);
	    g.fillRect (Sizing_Class.X_Pos (19, x), Sizing_Class.Y_Pos (58, y), Sizing_Class.Y_Pos (current_user_mana * 13 / user_mana, x), Sizing_Class.Y_Pos (5, y));
	    g.fillRect (Sizing_Class.X_Pos (69, x), Sizing_Class.Y_Pos (58, y), Sizing_Class.Y_Pos (current_enemy_mana * 13 / enemy_mana, x) + 2, Sizing_Class.Y_Pos (5, y));
	    g.drawImage (mana_image, Sizing_Class.X_Pos (16, x), Sizing_Class.Y_Pos (55, y), Sizing_Class.Y_Pos (17, x), Sizing_Class.Y_Pos (11, y), this);
	    g.drawImage (mana_image, Sizing_Class.X_Pos (66, x), Sizing_Class.Y_Pos (55, y), Sizing_Class.Y_Pos (17, x), Sizing_Class.Y_Pos (11, y), this);

	    //Draws the user and enemy images
	    if (users_image != null)
	    {
		g.drawImage (users_image, Sizing_Class.X_Pos (12, x), Sizing_Class.Y_Pos (15, y), Sizing_Class.X_Pos (30, y), Sizing_Class.X_Pos (30, y), this);
	    }
	    if (enemy_image != null)
	    {
		g.drawImage (enemy_image, Sizing_Class.X_Pos (65, x), Sizing_Class.Y_Pos (15, y), Sizing_Class.Y_Pos (30, y), Sizing_Class.Y_Pos (30, y), this);
	    }
	}


	else if (paint_screen.equals ("Change_Weapon_Screen")) //if the screen is change weapon screen, the user can change weapons here
	{
	    //sets sizes of buttons etc,
	    fightbutton.setSize (Sizing_Class.Y_Pos (12, y), Sizing_Class.Y_Pos (5, y));
	    weapon_selection.setSize (Sizing_Class.X_Pos (12, x), Sizing_Class.Y_Pos (3, y));
	    weapon_up.setSize (Sizing_Class.Y_Pos (3, y), Sizing_Class.Y_Pos (3, y));
	    weapon_down.setSize (Sizing_Class.Y_Pos (3, y), Sizing_Class.Y_Pos (3, y));

	    //sets locations of buttons, labels, etc.
	    fightbutton.setLocation (Sizing_Class.X_Pos (0, x), Sizing_Class.Y_Pos (95, y));
	    weapon_selection.setLocation (Sizing_Class.X_Pos (44, x), Sizing_Class.Y_Pos (75, y));
	    weapon_up.setLocation (Sizing_Class.X_Pos (47, x), Sizing_Class.Y_Pos (79, y));
	    weapon_down.setLocation (Sizing_Class.X_Pos (51, x), Sizing_Class.Y_Pos (79, y));

	    //sets fonts for buttons and labels
	    fightbutton.setFont (buttonFont);
	    weapon_selection.setFont (labelFont);

	    //draws the weapon image on the screen
	    if (weapon_image != null)
	    {
		g.drawImage (weapon_image, Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (5, y), Sizing_Class.Y_Pos (50, y), Sizing_Class.Y_Pos (50, y), this);

	    }
	}


	//brings user to magic selection screen
	else if (paint_screen.equals ("Magic_Selection_Screen"))
	{
	    //sets sizes of buttons, labels, etc
	    fightbutton.setSize (Sizing_Class.X_Pos (8, x), Sizing_Class.Y_Pos (5, y));
	    magic_selection.setSize (Sizing_Class.X_Pos (12, x), Sizing_Class.Y_Pos (3, y));
	    magic_up.setSize (Sizing_Class.Y_Pos (3, y), Sizing_Class.Y_Pos (3, y));
	    magic_down.setSize (Sizing_Class.Y_Pos (3, y), Sizing_Class.Y_Pos (3, y));

	    //sets locations of buttons, labels, etc.
	    fightbutton.setLocation (Sizing_Class.X_Pos (0, x), Sizing_Class.Y_Pos (95, y));
	    magic_selection.setLocation (Sizing_Class.X_Pos (44, x), Sizing_Class.Y_Pos (75, y));
	    magic_up.setLocation (Sizing_Class.X_Pos (46, x), Sizing_Class.Y_Pos (79, y));
	    magic_down.setLocation (Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (79, y));

	    //draws the magic image on the screen
	    g.drawImage (magic_image, Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (5, y), Sizing_Class.Y_Pos (50, y), Sizing_Class.Y_Pos (50, y), this);
	}


	else if (paint_screen.equals ("Potion_Screen"))
	{
	    //sets sizes of buttons, labels, etc
	    fightbutton.setSize (Sizing_Class.X_Pos (8, x), Sizing_Class.Y_Pos (5, y));
	    potion_selection.setSize (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (3, y));
	    potion_up.setSize (Sizing_Class.Y_Pos (3, y), Sizing_Class.Y_Pos (3, y));
	    potion_down.setSize (Sizing_Class.Y_Pos (3, y), Sizing_Class.Y_Pos (3, y));
	    use_potion.setSize (Sizing_Class.X_Pos (8, x), Sizing_Class.Y_Pos (5, y));

	    //sets locations of buttons, labels, etc.
	    fightbutton.setLocation (Sizing_Class.X_Pos (0, x), Sizing_Class.Y_Pos (95, y));
	    potion_selection.setLocation (Sizing_Class.X_Pos (44, x), Sizing_Class.Y_Pos (75, y));
	    potion_up.setLocation (Sizing_Class.X_Pos (47, x), Sizing_Class.Y_Pos (79, y));
	    potion_down.setLocation (Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (79, y));
	    use_potion.setLocation (Sizing_Class.X_Pos (45, x), Sizing_Class.Y_Pos (83, y));

	    //draws the potion image on the screen
	    if (potion_image != null)
	    {
		g.drawImage (potion_image, Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (5, y), Sizing_Class.Y_Pos (50, y), Sizing_Class.Y_Pos (50, y), this);
	    }
	}


	else if (paint_screen == "Win_Screen")
	{
	    //sets sizes of buttons, labels, etc
	    winner.setSize (Sizing_Class.Y_Pos (100, x), Sizing_Class.Y_Pos (10, y));
	    next_move.setSize (Sizing_Class.Y_Pos (10, y), Sizing_Class.Y_Pos (5, y));

	    //sets locations of buttons, labels, etc.
	    winner.setLocation (Sizing_Class.X_Pos (30, x), Sizing_Class.Y_Pos (25, y));
	    next_move.setLocation (Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (50, y));

	    //sets background and foreground colors for labels
	    winner.setBackground (Color.lightGray);
	    winner.setForeground (Color.red);

	    //sets fonts for buttons and labels
	    winner.setFont (titleFont);
	    next_move.setFont (buttonFont);
	}

	if (paint_screen.equalsIgnoreCase ("General_Screen"))
	{
	    switch (clips)
	    {
		case 0:
		    if (blacksmithsound != null)
		    {
			blacksmithsound.stop ();
			blacksmithsound = null;
		    }
		    break;

		case 1:
		    if (castlesound != null)
		    {
			castlesound.stop ();
			castlesound = null;
		    }
		    break;

		case 2:
		    if (castlesound != null)
		    {
			castlesound.stop ();
			castlesound = null;
		    }
		    break;

		case 3:
		    if (plainssound != null)
		    {
			plainssound.stop ();
			plainssound = null;
		    }
		    break;

		case 4:
		    if (forestsound != null)
		    {
			forestsound.stop ();
			forestsound = null;
		    }
		    break;

		case 5:
		    if (miningsound != null)
		    {
			miningsound.stop ();
			miningsound = null;
		    }
		    break;

		case 6:
		    if (riversound != null)
		    {
			riversound.stop ();
			riversound = null;
		    }
		    break;

		default:
		    break;
	    }


	    if (map [X] [Y] [0] == 10)
	    {
		g.drawImage (blacksmith, 0, 0, x, y, this);
		blacksmithsound = getAudioClip (getCodeBase (), ("Sounds\\blacksmith.wav"));
		blacksmithsound.loop ();
		clips = 0;
		special.setLabel ("Blacksmith");
	    }
	    else if (map [X] [Y] [0] == 11)
	    {
		g.drawImage (store, 0, 0, x, y, this);
		castlesound = getAudioClip (getCodeBase (), ("Sounds\\castlesound.wav"));
		castlesound.loop ();
		clips = 1;
		special.setLabel ("Store");
	    }
	    else if (map [X] [Y] [0] == 12)
	    {
		g.drawImage (school, 0, 0, x, y, this);
		castlesound = getAudioClip (getCodeBase (), ("Sounds\\castle.wav"));
		castlesound.loop ();
		clips = 2;
		special.setLabel ("School");
	    }
	    else if (map [X] [Y] [0] == 15)
	    {
		g.drawImage (plains, 0, 0, x, y, this);
		plainssound = getAudioClip (getCodeBase (), ("Sounds\\plains.wav"));
		plainssound.loop ();
		clips = 3;
		special.setLabel ("Hunt");
	    }
	    else if (map [X] [Y] [0] == 16)
	    {
		g.drawImage (road, 0, 0, x, y, this);
	    }
	    else if (map [X] [Y] [0] == 17)
	    {
		g.drawImage (forest, 0, 0, x, y, this);
		forestsound = getAudioClip (getCodeBase (), ("Sounds\\forest.wav"));
		forestsound.loop ();
		clips = 4;
		special.setLabel ("Chop Wood");
	    }
	    else if (map [X] [Y] [0] == 18)
	    {
		g.drawImage (mine, 0, 0, x, y, this);
		miningsound = getAudioClip (getCodeBase (), ("Sounds\\mining.wav"));
		miningsound.loop ();
		clips = 5;
		special.setLabel ("Mine");
	    }
	    else if (map [X] [Y] [0] == 19)
	    {
		g.drawImage (mountain, 0, 0, x, y, this);
	    }
	    else if (map [X] [Y] [0] == 20)
	    {
		g.drawImage (lake, 0, 0, x, y, this);
		riversound = getAudioClip (getCodeBase (), ("Sounds\\river.wav"));
		riversound.loop ();
		clips = 6;
		special.setLabel ("Fish");

	    }

	    else if (map [X] [Y] [0] == 21 | map [X] [Y] [0] == 22 | map [X] [Y] [0] == 23 | map [X] [Y] [0] == 24)
	    {
		g.drawImage (castlewall, 0, 0, x, y, this);
		special.setLabel ("Special");
	    }


	    else if (map [X] [Y] [0] == 31 | map [X] [Y] [0] == 32 | map [X] [Y] [0] == 33 | map [X] [Y] [0] == 34)
	    {
		g.drawImage (castlegate, 0, 0, x, y, this);
		special.setLabel ("Enter");
	    }


	    else if (map [X] [Y] [0] == 41 | map [X] [Y] [0] == 42 | map [X] [Y] [0] == 43 | map [X] [Y] [0] == 44)
	    {
		g.drawImage (castlegrounds, 0, 0, x, y, this);
		special.setLabel ("Special");
	    }
	    else
	    {
		special.setLabel ("Special"); //sets label to special
	    }


	    //font color and font for the thread
	    g.setColor (Color.red);
	    Font f = new Font ("Dialog", Font.BOLD, 20);
	    g.setFont (f);

	    //direction button locations
	    left.setLocation (Sizing_Class.X_Pos (30, x), Sizing_Class.Y_Pos (77, y));
	    right.setLocation (Sizing_Class.X_Pos (60, x), Sizing_Class.Y_Pos (77, y));
	    forward.setLocation (Sizing_Class.X_Pos (45, x), Sizing_Class.Y_Pos (65, y));
	    down.setLocation (Sizing_Class.X_Pos (45, x), Sizing_Class.Y_Pos (90, y));
	    fight.setLocation (Sizing_Class.X_Pos (1, x), Sizing_Class.Y_Pos (90, y));
	    special.setLocation (Sizing_Class.X_Pos (84, x), Sizing_Class.Y_Pos (90, y));
	    special.setSize (Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (10, y));
	    left.setSize (Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (10, y));
	    right.setSize (Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (10, y));
	    forward.setSize (Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (10, y));
	    down.setSize (Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (10, y));
	    fight.setSize (Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (10, y));
	    InventoryAmount = 0;
	    for (int i = 0 ; i < 100 ; i++)
	    {
		InventoryAmount = InventoryAmount + Integer.parseInt (Inventory [i] [1]);
	    }
	    //sets thread bar for the user at the top of the screen
	    g.drawString ("Class: " + User_Data [6], Sizing_Class.X_Pos (1, x), Sizing_Class.Y_Pos (4, y));
	    g.drawString (" Weapon: " + User_Data [7], Sizing_Class.X_Pos (25, x), Sizing_Class.Y_Pos (4, y));
	    g.drawString ("Name: " + User_Data [5], Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (4, y));
	    g.drawString ("Gold: " + Gold, Sizing_Class.X_Pos (83, x), Sizing_Class.Y_Pos (4, y));
	    g.drawString ("Silver: " + Silver, Sizing_Class.X_Pos (1, x), Sizing_Class.Y_Pos (12, y));
	    g.drawString ("Copper: " + Copper, Sizing_Class.X_Pos (26, x), Sizing_Class.Y_Pos (12, y));
	    g.drawString ("Inventory: " + InventoryAmount + " / 100", Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (12, y));
	    g.drawString ("Inventory: (press) I", Sizing_Class.X_Pos (1, x), Sizing_Class.Y_Pos (21, y));
	    g.drawString ("Stats: (press) S", Sizing_Class.X_Pos (26, x), Sizing_Class.Y_Pos (21, y));
	    g.drawString ("Level: " + User_Data [12], Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (21, y));
	    g.drawString ("X : " + coordinate_x + "        Y : " + coordinate_y, Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (30, y));
	    switch (map [coordinate_x] [coordinate_y] [2])
	    {
		case 1:
		    if (User_Data [14].equalsIgnoreCase ("air"))
		    {
			g.setColor (Color.green);
		    }
		    else
		    {
			g.setColor (Color.red);
		    }
		    g.drawString ("AIR", Sizing_Class.X_Pos (60, x), Sizing_Class.Y_Pos (30, y));
		    break;
		case 2:
		    if (User_Data [14].equalsIgnoreCase ("water"))
		    {
			g.setColor (Color.green);
		    }
		    else
		    {
			g.setColor (Color.red);
		    }
		    g.drawString ("WATER", Sizing_Class.X_Pos (60, x), Sizing_Class.Y_Pos (30, y));
		    break;
		case 3:
		    if (User_Data [14].equalsIgnoreCase ("earth"))
		    {
			g.setColor (Color.green);
		    }
		    else
		    {
			g.setColor (Color.red);
		    }
		    g.drawString ("EARTH", Sizing_Class.X_Pos (60, x), Sizing_Class.Y_Pos (30, y));
		    break;
		case 4:

		    if (User_Data [14].equalsIgnoreCase ("fire"))
		    {
			g.setColor (Color.green);
		    }
		    else
		    {
			g.setColor (Color.red);
		    }
		    g.drawString ("FIRE", Sizing_Class.X_Pos (60, x), Sizing_Class.Y_Pos (30, y));
		    break;
	    }
	} // if general screen


	if (paint_screen.equals ("main screen")) //if the screen is painted to the main screen
	{
	    //main screen picture
	    g.drawImage (image, 0, 0, getSize ().width, getSize ().height, this);

	    //title
	    g.setColor (Color.red);
	    g.setFont (new Font ("Dialog", Font.BOLD, Sizing_Class.Y_Size (17, y)));
	    g.drawString ("Clan Wars", Sizing_Class.X_Pos (30, x), Sizing_Class.Y_Pos (15, y));

	    //proceed button
	    proceed.setSize (Sizing_Class.X_Pos (25, x), Sizing_Class.X_Pos (10, y));
	    proceed.setBackground (Color.black);
	    proceed.setForeground (Color.red);
	    proceed.setLocation (Sizing_Class.X_Pos (44, x), Sizing_Class.Y_Pos (20, y));
	}


	else if (paint_screen.equals ("login screen")) //if the screen is painted to the login screen
	{
	    //draws background
	    this.setBackground (Color.black);
	    g.drawImage (image, 0, 0, getSize ().width, getSize ().height, this);


	    g.setFont (k); //set font

	    //username label
	    g.setColor (Color.red);
	    g.drawString ("Username:", Sizing_Class.X_Pos (30, x), Sizing_Class.Y_Pos (5, y));
	    g.drawString (ERROR_MESSAGE, Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (75, y));
	    //password label
	    g.setColor (Color.red);
	    g.drawString ("Password:", Sizing_Class.X_Pos (30, x), Sizing_Class.Y_Pos (16, y));

	    //login button
	    login.setFont (k);
	    login.setSize (Sizing_Class.X_Pos (25, x), Sizing_Class.X_Pos (10, y));
	    login.setBackground (Color.black);
	    login.setForeground (Color.red);
	    login.setLocation (Sizing_Class.X_Pos (0, x), Sizing_Class.Y_Pos (0, y));

	    //signup button
	    signup.setFont (k);
	    signup.setLocation (Sizing_Class.X_Pos (0, x), Sizing_Class.Y_Pos (10, y));
	    signup.setBackground (Color.black);
	    signup.setForeground (Color.red);
	    signup.setSize (Sizing_Class.X_Pos (25, x), Sizing_Class.X_Pos (10, y));

	    //username textfield
	    username.setFont (k);
	    username.setLocation (Sizing_Class.X_Pos (60, x), Sizing_Class.Y_Pos (0, y));
	    username.setSize (Sizing_Class.X_Pos (40, x), Sizing_Class.X_Pos (10, y));

	    //password textfield
	    password.setSize (Sizing_Class.X_Pos (40, x), Sizing_Class.X_Pos (10, y));
	    password.setFont (k);
	    password.setLocation (Sizing_Class.X_Pos (60, x), Sizing_Class.Y_Pos (10, y));
	}


	else if (paint_screen.equals ("sign up")) //if the screen is painted to the signup screen
	{
	    //paints background
	    g.drawImage (image, 0, 0, getSize ().width, getSize ().height, this);

	    //font
	    g.setFont (f);

	    //new username label
	    g.setColor (Color.red);
	    g.drawString ("New Username:", Sizing_Class.X_Pos (30, x), Sizing_Class.X_Pos (5, y));

	    //new password label
	    g.setColor (Color.red);
	    g.drawString ("New Password:", Sizing_Class.X_Pos (30, x), Sizing_Class.X_Pos (16, y));

	    //confirm password label
	    g.setColor (Color.red);
	    g.drawString ("Confirm Password:", Sizing_Class.X_Pos (30, x), Sizing_Class.X_Pos (27, y));

	    //signup button
	    confirm.setFont (f);
	    confirm.setLocation (Sizing_Class.X_Pos (0, x), Sizing_Class.X_Pos (0, y));
	    confirm.setBackground (Color.black);
	    confirm.setForeground (Color.red);
	    confirm.setSize (Sizing_Class.X_Pos (25, x), Sizing_Class.X_Pos (10, y));

	    //new username textfield
	    newUsername.setFont (f);
	    newUsername.setLocation (Sizing_Class.X_Pos (55, x), Sizing_Class.X_Pos (0, y));
	    newUsername.setSize (Sizing_Class.X_Pos (30, x), Sizing_Class.X_Pos (10, y));

	    //new password textfield
	    newPassword.setFont (f);
	    newPassword.setLocation (Sizing_Class.X_Pos (55, x), Sizing_Class.X_Pos (10, y));
	    newPassword.setSize (Sizing_Class.X_Pos (30, x), Sizing_Class.X_Pos (10, y));

	    //confirm password texfield
	    confirmPassword.setFont (f);
	    confirmPassword.setLocation (Sizing_Class.X_Pos (55, x), Sizing_Class.X_Pos (20, y));
	    confirmPassword.setSize (Sizing_Class.X_Pos (30, x), Sizing_Class.X_Pos (10, y));

	    //back button
	    back.setFont (f);
	    back.setLocation (Sizing_Class.X_Pos (0, x), Sizing_Class.X_Pos (10, y));
	    back.setBackground (Color.black);
	    back.setForeground (Color.red);
	    back.setSize (Sizing_Class.X_Pos (25, x), Sizing_Class.X_Pos (10, y));
	}


	if (paint_screen.equalsIgnoreCase ("USER")) // USER               danny
	{

	    x = this.getSize ().width; // refresh x
	    y = this.getSize ().height; // refresh y
	    this.setBackground (Color.black); // background
	    g.drawImage (image, 0, 0, getSize ().width, getSize ().height, this);
	    // buttons -->
	    cmd_Play.setSize (Sizing_Class.X_Size (6, x), Sizing_Class.Y_Size (8, y));
	    cmd_Play.setLocation (Sizing_Class.X_Pos (25, x), Sizing_Class.Y_Pos (75, y));
	    cmd_Create.setSize (Sizing_Class.X_Size (6, x), Sizing_Class.Y_Size (8, y));
	    cmd_Create.setLocation (Sizing_Class.X_Pos (25, x), Sizing_Class.Y_Pos (85, y));
	    cmd_Delete.setSize (Sizing_Class.X_Size (6, x), Sizing_Class.Y_Size (8, y));
	    cmd_Delete.setLocation (Sizing_Class.X_Pos (25, x), Sizing_Class.Y_Pos (95, y));
	    cmd_Reset.setSize (Sizing_Class.X_Size (6, x), Sizing_Class.Y_Size (8, y));
	    cmd_Reset.setLocation (Sizing_Class.X_Pos (92, x), Sizing_Class.Y_Pos (95, y));
	    // Buttons <--
	    g.setFont (fnt_Main_Screen_Writing);
	    g.setColor (Color.red);
	    g.drawString (error, Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (110, y)); // if any error codes
	    // Lists -->
	    lst_Characters.setSize (Sizing_Class.X_Size (20, x), Sizing_Class.Y_Size (28, y));
	    lst_Characters.setLocation (Sizing_Class.X_Pos (2, x), Sizing_Class.Y_Pos (75, y));
	    lst_Characters.addActionListener (this);
	    lst_Worlds.setSize (Sizing_Class.X_Size (20, x), Sizing_Class.Y_Size (28, y));
	    lst_Worlds.setLocation (Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (75, y));
	    // Lists <--

	    // drawStrings -->
	    fnt_Main_Screen_Title = new Font ("MonoSpaced", Font.BOLD, Sizing_Class.Y_Size (6, y));
	    g.setFont (fnt_Main_Screen_Title);
	    g.setColor (Color.red);
	    g.drawString ("Welcome : " + name, Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (10, y));
	    fnt_Main_Screen_Writing = new Font ("MonoSpaced", Font.BOLD, Sizing_Class.Y_Size (5, y));
	    g.setFont (fnt_Main_Screen_Writing);
	    g.drawString ("Health : " + Health + " / " + Max_Health, Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (10, y));
	    g.drawString ("Mana   : " + Mana + " / " + Max_Mana, Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (18, y));
	    g.drawString ("Wealth : " + Wealth, Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (26, y));
	    g.drawString ("Clan   : " + Clan, Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (34, y));
	    // drawStrings <--

	    // Image -->
	    g.drawImage (img_Main_Screen, Sizing_Class.X_Pos (2, x), Sizing_Class.Y_Pos (20, y), Sizing_Class.X_Size (29, x), Sizing_Class.Y_Size (45, y), this);
	    // Image <--

	    // check -->
	    if (Check)
	    {
		g.setColor (Color.red);
		fnt_Main_Screen_Writing = new Font ("MonoSpaced", Font.BOLD, Sizing_Class.Y_Size (5, y));
		g.setFont (fnt_Main_Screen_Writing);
		g.drawString ("Are you sure ? ", Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (45, y));
		// Buttons -->
		Yes.setLocation (Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (50, y));
		Yes.setSize (Sizing_Class.X_Size (6, x), Sizing_Class.Y_Size (8, y));
		No.setLocation (Sizing_Class.X_Pos (45, x), Sizing_Class.Y_Pos (50, y));
		No.setSize (Sizing_Class.X_Size (6, x), Sizing_Class.Y_Size (8, y));

		// Buttons <--
	    }
	    // check <--
	    this.requestFocus ();
	} // USER


	if (paint_screen.equalsIgnoreCase ("Store"))
	{
	    g.fillRect (0, 0, x, y);
	    BACK.setLocation (Sizing_Class.X_Pos (92, x), Sizing_Class.Y_Pos (5, y));
	    BACK.setSize (Sizing_Class.X_Size (7, x), Sizing_Class.Y_Size (7, y));
	    fnt_Main_Screen_Title = new Font ("MonoSpaced", Font.PLAIN, (int) (Math.sqrt (Sizing_Class.Y_Size (25, y) + Sizing_Class.X_Size (25, x)))); //title font
	    image = getImage (getDocumentBase (), "Images\\store.jpg");

	    g.drawImage (image, 0, 0, x, y, this);

	    g.setFont (fnt_Main_Screen_Title);
	    Vendor.setLocation (Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (10, y));
	    Vendor.setSize (Sizing_Class.X_Size (20, x), Sizing_Class.Y_Size (60, y)); // vendor items

	    Personal.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (10, y));
	    Personal.setSize (Sizing_Class.X_Size (20, x), Sizing_Class.Y_Size (60, y)); // personal inventory

	    Middle.setLocation (Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (10, y));
	    Middle.setSize (Sizing_Class.X_Size (20, x), Sizing_Class.Y_Size (60, y)); // transaction list
	    g.setColor (Color.red);
	    g.drawString ("My Stuff", Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (5, y));
	    g.drawString ("Your Inventory", Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (5, y)); // labels
	    g.drawString ("Our Transaction", Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (5, y));

	    PersonalCopper.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (72, y));
	    PersonalSilver.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (82, y)); // personal money fields
	    PersonalGold.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (92, y));

	    PersonalCopper.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y));
	    PersonalSilver.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y)); // personal money fields
	    PersonalGold.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y));
	    PersonalCopper.setEditable (false);
	    PersonalSilver.setEditable (false);
	    PersonalGold.setEditable (false);
	    PersonalCopper.setText ("Copper : " + Copper);
	    PersonalSilver.setText ("Silver : " + Silver);
	    fnt_Lists = new Font ("MonoSpaced", Font.PLAIN, PersonalCopper.getHeight () - 6);
	    PersonalCopper.setFont (fnt_Lists);
	    PersonalSilver.setFont (fnt_Lists);
	    PersonalGold.setFont (fnt_Lists);
	    PersonalGold.setText ("Gold   : " + Gold);

	    TransactionCopper.setLocation (Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (72, y));
	    TransactionSilver.setLocation (Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (82, y)); // money owed or to be recieved fields
	    TransactionGold.setLocation (Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (92, y));
	    TransactionCopper.setEditable (false);
	    TransactionSilver.setEditable (false);
	    TransactionGold.setEditable (false);
	    TransactionCopper.setText ("Copper : " + TRANSACTION_COPPER);

	    TransactionSilver.setText ("Silver : " + TRANSACTION_SILVER);
	    TransactionGold.setText (" Gold : " + TRANSACTION_GOLD);
	    TransactionCopper.setFont (fnt_Lists);
	    TransactionSilver.setFont (fnt_Lists);
	    TransactionGold.setFont (fnt_Lists);
	    TransactionCopper.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y));
	    TransactionSilver.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y)); // money owed or to be recieved fields
	    TransactionGold.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y));

	    VendorCopper.setLocation (Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (72, y));
	    VendorSilver.setLocation (Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (82, y)); // vendors money fields
	    VendorGold.setLocation (Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (92, y));
	    VendorCopper.setEditable (false);
	    VendorSilver.setEditable (false);
	    VendorGold.setEditable (false);

	    VendorCopper.setFont (fnt_Lists);
	    VendorSilver.setFont (fnt_Lists);
	    VendorGold.setFont (fnt_Lists);
	    VendorCopper.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y));
	    VendorSilver.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y)); // vendors money fields
	    VendorGold.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y));

	    VendorCopper.setText ("Copper : " + VENDOR_COPPER);

	    VendorSilver.setText ("Silver : " + VENDOR_SILVER);

	    VendorGold.setText (" Gold : " + VENDOR_GOLD);
	    TransactionCopper.setText ("Copper : " + TRANSACTION_COPPER);

	    TransactionSilver.setText ("Silver : " + TRANSACTION_SILVER);

	    TransactionGold.setText (" Gold : " + TRANSACTION_GOLD);
	    list = (int) Math.sqrt (Personal.getWidth () + Personal.getHeight ());
	    fnt_Lists = new Font ("MonoSpaced", Font.PLAIN, list); // list font
	    SELL.setLocation (Sizing_Class.X_Pos (31, x), Sizing_Class.Y_Pos (60, y));
	    BUY.setLocation (Sizing_Class.X_Pos (92, x), Sizing_Class.Y_Pos (60, y));
	    CONFIRM.setLocation (Sizing_Class.X_Pos (61, x), Sizing_Class.Y_Pos (60, y));
	    SELL.setSize (Sizing_Class.X_Size (8, x), Sizing_Class.Y_Size (10, y));
	    BUY.setSize (Sizing_Class.X_Size (8, x), Sizing_Class.Y_Size (10, y));
	    CONFIRM.setSize (Sizing_Class.X_Size (8, x), Sizing_Class.Y_Size (10, y));
	    REMOVE.setLocation (Sizing_Class.X_Pos (61, x), Sizing_Class.Y_Pos (40, y));
	    REMOVE.setSize (Sizing_Class.X_Size (8, x), Sizing_Class.Y_Size (10, y));
	    g.drawString (ERROR_MESSAGE, Sizing_Class.X_Pos (45, x), Sizing_Class.Y_Pos (105, y));

	} // if store


	if (paint_screen.equalsIgnoreCase ("BlackSmith"))
	{
	    g.fillRect (0, 0, x, y);
	    BACK.setLocation (Sizing_Class.X_Pos (92, x), Sizing_Class.Y_Pos (5, y));
	    BACK.setSize (Sizing_Class.X_Size (7, x), Sizing_Class.Y_Size (7, y));
	    fnt_Main_Screen_Title = new Font ("MonoSpaced", Font.PLAIN, (int) (Math.sqrt (Sizing_Class.Y_Size (25, y) + Sizing_Class.X_Size (25, x)))); //title font
	    g.drawImage (image, 0, 0, x, y, this);
	    g.setFont (fnt_Main_Screen_Title);
	    Vendor.setLocation (Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (10, y));
	    Vendor.setSize (Sizing_Class.X_Size (20, x), Sizing_Class.Y_Size (60, y)); // vendor items

	    Personal.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (10, y));
	    Personal.setSize (Sizing_Class.X_Size (20, x), Sizing_Class.Y_Size (60, y)); // personal inventory

	    Middle.setLocation (Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (10, y));
	    Middle.setSize (Sizing_Class.X_Size (20, x), Sizing_Class.Y_Size (60, y)); // transaction list
	    g.setColor (Color.red);
	    g.drawString ("My Stuff", Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (5, y));
	    g.drawString ("Your Inventory", Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (5, y)); // labels
	    g.drawString ("Our Transaction", Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (5, y));

	    PersonalCopper.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (72, y));
	    PersonalSilver.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (82, y)); // personal money fields
	    PersonalGold.setLocation (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (92, y));

	    PersonalCopper.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y));
	    PersonalSilver.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y)); // personal money fields
	    PersonalGold.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y));
	    PersonalCopper.setEditable (false);
	    PersonalSilver.setEditable (false);
	    PersonalGold.setEditable (false);
	    PersonalCopper.setText ("Copper : " + Copper);
	    PersonalSilver.setText ("Silver : " + Silver);
	    fnt_Lists = new Font ("MonoSpaced", Font.PLAIN, PersonalCopper.getHeight () - 6);
	    PersonalCopper.setFont (fnt_Lists);
	    PersonalSilver.setFont (fnt_Lists);
	    PersonalGold.setFont (fnt_Lists);
	    PersonalGold.setText ("Gold   : " + Gold);

	    TransactionCopper.setLocation (Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (72, y));
	    TransactionSilver.setLocation (Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (82, y)); // money owed or to be recieved fields
	    TransactionGold.setLocation (Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (92, y));
	    TransactionCopper.setEditable (false);
	    TransactionSilver.setEditable (false);
	    TransactionGold.setEditable (false);
	    TransactionCopper.setText ("Copper : " + TRANSACTION_COPPER);

	    TransactionSilver.setText ("Silver : " + TRANSACTION_SILVER);
	    TransactionGold.setText (" Gold : " + TRANSACTION_GOLD);
	    TransactionCopper.setFont (fnt_Lists);
	    TransactionSilver.setFont (fnt_Lists);
	    TransactionGold.setFont (fnt_Lists);
	    TransactionCopper.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y));
	    TransactionSilver.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y)); // money owed or to be recieved fields
	    TransactionGold.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y));

	    VendorCopper.setLocation (Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (72, y));
	    VendorSilver.setLocation (Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (82, y)); // vendors money fields
	    VendorGold.setLocation (Sizing_Class.X_Pos (70, x), Sizing_Class.Y_Pos (92, y));
	    VendorCopper.setEditable (false);
	    VendorSilver.setEditable (false);
	    VendorGold.setEditable (false);

	    VendorCopper.setFont (fnt_Lists);
	    VendorSilver.setFont (fnt_Lists);
	    VendorGold.setFont (fnt_Lists);
	    VendorCopper.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y));
	    VendorSilver.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y)); // vendors money fields
	    VendorGold.setSize (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (5, y));

	    VendorCopper.setText ("Copper : " + VENDOR_COPPER);

	    VendorSilver.setText ("Silver : " + VENDOR_SILVER);

	    VendorGold.setText (" Gold : " + VENDOR_GOLD);
	    TransactionCopper.setText ("Copper : " + TRANSACTION_COPPER);

	    TransactionSilver.setText ("Silver : " + TRANSACTION_SILVER);

	    TransactionGold.setText (" Gold : " + TRANSACTION_GOLD);

	    fnt_Lists = new Font ("MonoSpaced", Font.PLAIN, Sizing_Class.X_Pos (5, y)); // list font
	    SELL.setLocation (Sizing_Class.X_Pos (31, x), Sizing_Class.Y_Pos (60, y));
	    BUY.setLocation (Sizing_Class.X_Pos (92, x), Sizing_Class.Y_Pos (60, y));
	    CONFIRM.setLocation (Sizing_Class.X_Pos (61, x), Sizing_Class.Y_Pos (60, y));
	    SELL.setSize (Sizing_Class.X_Size (8, x), Sizing_Class.Y_Size (10, y));
	    BUY.setSize (Sizing_Class.X_Size (8, x), Sizing_Class.Y_Size (10, y));
	    CONFIRM.setSize (Sizing_Class.X_Size (8, x), Sizing_Class.Y_Size (10, y));
	    REMOVE.setLocation (Sizing_Class.X_Pos (61, x), Sizing_Class.Y_Pos (40, y));
	    REMOVE.setSize (Sizing_Class.X_Size (8, x), Sizing_Class.Y_Size (10, y));
	    g.drawString (ERROR_MESSAGE, Sizing_Class.X_Pos (45, x), Sizing_Class.Y_Pos (105, y));

	} // if blacksmith


	if (paint_screen.equalsIgnoreCase ("character_creation"))
	{
	    //creates fonts that dynamically change size
	    titleFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (6, y));
	    Font LabelFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (5, y));
	    buttonFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (2, y));

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

	    lbl_name.setForeground (Color.red); //sets all the colors of the textfields to red
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

	    lbl_name.setBackground (Color.black); //sets all the colors of the textfields backgrounds to black
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


	}


	if (paint_screen.equalsIgnoreCase ("save screen"))
	{
	    g.setColor (Color.black);
	    this.setBackground (Color.black);
	    g.fillRect (0, 0, x, y);
	    g.setColor (Color.red);
	    g.drawString ("Saving Game...", Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (10, y));

	    g.setColor (Color.green);

	    g.drawRect (Sizing_Class.X_Pos (25, x), Sizing_Class.Y_Pos (25, y), Sizing_Class.X_Size (50, x), Sizing_Class.Y_Size (25, y));
	    switch (N)
	    {
		case 0:
		    g.fillRect (Sizing_Class.X_Pos (26, x), Sizing_Class.Y_Pos (25, y), Sizing_Class.X_Size (5, x), Sizing_Class.Y_Size (25, y));
		    break;
		case 1:
		    g.fillRect (Sizing_Class.X_Pos (32, x), Sizing_Class.Y_Pos (25, y), Sizing_Class.X_Size (5, x), Sizing_Class.Y_Size (25, y));
		    break;
		case 2:
		    g.fillRect (Sizing_Class.X_Pos (38, x), Sizing_Class.Y_Pos (25, y), Sizing_Class.X_Size (5, x), Sizing_Class.Y_Size (25, y));
		    break;
		case 3:
		    g.fillRect (Sizing_Class.X_Pos (44, x), Sizing_Class.Y_Pos (25, y), Sizing_Class.X_Size (5, x), Sizing_Class.Y_Size (25, y));
		    break;
		case 4:
		    g.fillRect (Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (25, y), Sizing_Class.X_Size (5, x), Sizing_Class.Y_Size (25, y));
		    break;
		default:
		    break;
	    }
	}


	if (paint_screen.equalsIgnoreCase ("School_Screen"))
	{
	    new Sizing_Class (); //calls the sizing method to dynamically change the size and location of buttons, labels, etc.

	    setBackground (Color.black); //sets the background to black

	    int x = this.getSize ().width; //gets width and height of users display size, used in sizing
	    int y = this.getSize ().height;

	    //creates fonts for buttons, titles, labels
	    buttonFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (2, x));
	    titleFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (10, x));
	    labelFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (3, x));

	    if (paint_screen.equals ("School_welcome_screen")) //when the user is on the welcome screen
	    {
		//sets button size, font, location
		continuebutton.setSize (Sizing_Class.X_Pos (15, x), Sizing_Class.Y_Pos (5, x));
		continuebutton.setLocation (Sizing_Class.X_Pos (42, x), Sizing_Class.Y_Pos (25, x));
		continuebutton.setFont (buttonFont);

		//draws background image
		g.drawImage (School_image, 0, 0, Sizing_Class.X_Pos (100, x), Sizing_Class.Y_Pos (100, y), this);

		//draws a welcome message on screen
		g.setFont (titleFont);
		g.setColor (Color.red);
		g.drawString ("Welcome to School", Sizing_Class.X_Pos (5, x), Sizing_Class.Y_Pos (15, y));
	    }

	    if (paint_screen.equals ("School_screen")) //when the user is on the purchasing screen
	    {
		//sets sizes of buttons and labels etc.
		addHealth.setSize (Sizing_Class.X_Pos (3, x), Sizing_Class.Y_Pos (3, x));
		subHealth.setSize (Sizing_Class.X_Pos (3, x), Sizing_Class.Y_Pos (3, x));
		addMana.setSize (Sizing_Class.X_Pos (3, x), Sizing_Class.Y_Pos (3, x));
		subMana.setSize (Sizing_Class.X_Pos (3, x), Sizing_Class.Y_Pos (3, x));
		addStamina.setSize (Sizing_Class.X_Pos (3, x), Sizing_Class.Y_Pos (3, x));
		subStamina.setSize (Sizing_Class.X_Pos (3, x), Sizing_Class.Y_Pos (3, x));
		addStrength.setSize (Sizing_Class.X_Pos (3, x), Sizing_Class.Y_Pos (3, x));
		subStrength.setSize (Sizing_Class.X_Pos (3, x), Sizing_Class.Y_Pos (3, x));
		backbutton.setSize (Sizing_Class.X_Pos (8, x), Sizing_Class.Y_Pos (3, x));
		buy.setSize (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (4, x));

		//sets locations of buttons and labels etc.
		addHealth.setLocation (Sizing_Class.X_Pos (27, x), Sizing_Class.Y_Pos (10, y));
		subHealth.setLocation (Sizing_Class.X_Pos (32, x), Sizing_Class.Y_Pos (10, y));
		addMana.setLocation (Sizing_Class.X_Pos (27, x), Sizing_Class.Y_Pos (20, y));
		subMana.setLocation (Sizing_Class.X_Pos (32, x), Sizing_Class.Y_Pos (20, y));
		addStamina.setLocation (Sizing_Class.X_Pos (27, x), Sizing_Class.Y_Pos (39, y));
		subStamina.setLocation (Sizing_Class.X_Pos (32, x), Sizing_Class.Y_Pos (39, y));
		addStrength.setLocation (Sizing_Class.X_Pos (27, x), Sizing_Class.Y_Pos (29, y));
		subStrength.setLocation (Sizing_Class.X_Pos (32, x), Sizing_Class.Y_Pos (29, y));
		backbutton.setLocation (Sizing_Class.X_Pos (0, x), Sizing_Class.Y_Pos (95, y));
		buy.setLocation (Sizing_Class.X_Pos (45, x), Sizing_Class.Y_Pos (75, y));

		//sets fonts
		addHealth.setFont (buttonFont);
		subHealth.setFont (buttonFont);
		addMana.setFont (buttonFont);
		subMana.setFont (buttonFont);
		addStamina.setFont (buttonFont);
		subStamina.setFont (buttonFont);
		addStrength.setFont (buttonFont);
		subStrength.setFont (buttonFont);
		backbutton.setFont (buttonFont);
		buy.setFont (buttonFont);

		//draws images
		g.drawImage (School_image, 0, 0, Sizing_Class.X_Pos (100, x), Sizing_Class.Y_Pos (100, y), this);
		g.drawImage (user_image, Sizing_Class.X_Pos (55, x), Sizing_Class.Y_Pos (20, y), Sizing_Class.X_Pos (30, x), Sizing_Class.X_Pos (50, y), this);

		//sets font and color of graphical output
		g.setColor (Color.red);
		g.setFont (labelFont);

		//draws to the display for currency, price, and a title
		g.drawString ("Currency: " + currency, Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (92, y));
		g.drawString ("Price: " + price, Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (99, y));
		g.drawString ("Select which skills you'd like to learn", Sizing_Class.X_Pos (0, x), Sizing_Class.Y_Pos (5, y));

		//draws
		g.drawString ("Health: " + healthlvl, Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (14, y));
		g.drawString ("Mana: " + manalvl, Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (24, y));
		g.drawString ("Strength: " + strengthlvl, Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (34, y));
		g.drawString ("Stamina: " + staminalvl, Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (44, y));
		g.drawString ("Gold: " + goldamount, Sizing_Class.X_Pos (75, x), Sizing_Class.Y_Pos (85, y));
		g.drawString ("Silver: " + silveramount, Sizing_Class.X_Pos (75, x), Sizing_Class.Y_Pos (90, y));
		g.drawString ("Bronze: " + bronzeamount, Sizing_Class.X_Pos (75, x), Sizing_Class.Y_Pos (95, y));

		g.drawString ("+ " + addedhealth, Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (14, y));
		g.drawString ("+ " + addedmana, Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (24, y));
		g.drawString ("+ " + addedstrength, Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (34, y));
		g.drawString ("+ " + addedstamina, Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (44, y));

	    }
	}


	if (paint_screen.equalsIgnoreCase ("Creating_Worlds"))
	{
	    if (Z == 0)
	    {
		g.fillRect (0, 0, x, y);
		g.setColor (Color.green);
		g.setFont (fnt_Main_Screen_Title); // inform the user of what is happening
		g.drawString ("Creating Worlds... This may take a moment", Sizing_Class.X_Pos (25, x), Sizing_Class.Y_Pos (5, y));
	    }

	    g.drawRect (Sizing_Class.X_Pos (20, x), Sizing_Class.Y_Pos (25, y), Sizing_Class.X_Size (41, x), Sizing_Class.Y_Size (40, y));

	    switch (Z)
	    {
		case 0:
		    g.fillRect (Sizing_Class.X_Pos (21, x), Sizing_Class.Y_Pos (25, y), Sizing_Class.X_Size (12, x), Sizing_Class.Y_Size (40, y));
		    break;
		case 1:
		    g.fillRect (Sizing_Class.X_Pos (34, x), Sizing_Class.Y_Pos (25, y), Sizing_Class.X_Size (12, x), Sizing_Class.Y_Size (40, y));
		    break;
		case 2:
		    g.fillRect (Sizing_Class.X_Pos (46, x), Sizing_Class.Y_Pos (25, y), Sizing_Class.X_Size (12, x), Sizing_Class.Y_Size (40, y));

	    } // switch
	} // creating worlds
    } // paint method
} // ClanWarsMainMenu class


