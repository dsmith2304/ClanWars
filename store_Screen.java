// The "Black_Smith" class.
import java.applet.*;
import java.awt.*;

public class store_Screen extends Applet
{
    String Prices[] [] = new String [100] [3]; // holds items and their prices for lookup in stores and blacksmith
    List Vendor = new List (); // vendors items
    List Personal = new List (); // users inventory
    List Middle = new List (); // list of transaction items
    Font fnt_Main_Screen_Title = new Font ("MonoSpaced", Font.PLAIN, 24); //title font
    TextField VendorGold = new TextField (); // vendors gold count
    TextField VendorSilver = new TextField (); // vendors silver count
    TextField VendorCopper = new TextField (); // vendors copper count
    TextField PersonalGold = new TextField (); // user gold textfield
    TextField PersonalSilver = new TextField (); // user silver textfield
    TextField PersonalCopper = new TextField (); // users copper textfield
    TextField TransactionGold = new TextField (); // middle transaction gold
    TextField TransactionSilver = new TextField (); // middle transaction silver
    String paint_screen = "BlackSmith"; // paint screen
    Font fnt_Lists = new Font ("MonoSpaced", Font.PLAIN, 12); // list font
    TextField TransactionCopper = new TextField (); // middle copper textfield
    Image image; // background image
    int x = 0, y = 0; // screen sizes
    String Inventory[] [] = new String [100] [2]; // Users inventory
    int Copper = 5, Silver = 2, Gold = 1; // users copper silver and gold values
    int list = 0; // value for font pt size
    String temp = ""; // check item of list vs indexes
    int lvl = 7; // level that alters inventory
    Button SELL = new Button ("SELL"), BUY = new Button ("BUY"), CONFIRM = new Button ("FIN"), REMOVE = new Button ("DEL"), BACK = new Button ("Back"); // onscreen buttons
    int VENDOR_SILVER = 0, VENDOR_COPPER = 0, VENDOR_GOLD = 0; // vendors golds silver copper values
    int TRANSACTION_COPPER = 0, TRANSACTION_SILVER = 0, TRANSACTION_GOLD = 0; // middle gold silver copper values
    String MIDDLE[] [] = new String [100] [2]; // array of items in the transaction log
    boolean CHECK = false, CHECK2 = false; // flagging levels of adding stuff to middle array
    String VENDOR[] [] = new String [100] [3]; // keep track of vendor information
    String temporary; // for aligning text
    String ERROR_MESSAGE = ""; // display error messages

    public void init ()
    {

	for (int i = 0 ; i < 100 ; i++) // set all arrays to empty values to avoid null pointer exceptions
	{
	    Inventory [i] [0] = "";
	    Inventory [i] [1] = "0";
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


	image = getImage (getDocumentBase (), "Images\\thDTPUZNVC.jpg"); // background
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
    } // init method


    public boolean action (Event e, Object o)
    {

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
		paint_screen = "";
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
			    System.out.println (Inventory [i] [0] + "           " + temp);
			    if (Inventory [i] [0].equalsIgnoreCase (temp))
			    {


				Inventory [i] [1] = "" + (Integer.parseInt (Inventory [i] [1]) + Integer.parseInt (MIDDLE [Middle.getSelectedIndex () - 1] [1]));
				System.out.println (Integer.parseInt (Inventory [i] [1]) + Integer.parseInt (MIDDLE [i] [1]));
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








	} // if paint
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
	return true;
    } // action


    public void paint (Graphics g)
    {
	x = getSize ().width;
	y = getSize ().height;


	if (paint_screen.equalsIgnoreCase ("Store"))
	{
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

    } // paint method
} // Black_Smith class




