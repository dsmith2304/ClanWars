// The "School_Screen" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class School_Screen extends Applet implements ActionListener
{
    //buttons to buy, add levels, go back, take away levels, etc.
    Button back, buy, addHealth, continuebutton, subHealth, addMana, subMana, addStamina, subStamina, addStrength, subStrength;

    //integers for keeping track of levels, currency, amounts, prices, etc.
    int healthlvl = 1, manalvl = 4, staminalvl = 6, strengthlvl = 8, healthamount, staminaamount, strengthamount, manaamount,
	gold = 15, silver = 0, bronze = 0, currency, price = 0, addedhealth, addedmana, addedstamina, addedstrength,
	healthprice, manaprice, strengthprice, staminaprice;

    //strings used for switching screens and images
    String current_screen = "School_welcome_screen", userclass = "Necro";

    //images used on display
    Image School_image, user_image;

    public void init ()
    {
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

	currency = (gold * 100) + (silver * 10) + bronze;

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
	addbuttons ();
    } // init method


    public void actionPerformed (ActionEvent e)  //a method that is called whenever a button is pressed
    {
	Graphics g = getGraphics (); //allows graphics to be used in this method

	new Sizing_Class (); //calls the sizing class to dynamically size and set locations

	int x = this.getSize ().width; //gets width and height of users display size, used in sizing
	int y = this.getSize ().height;

	Font labelFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (3, x)); //a new font used for labels

	if (e.getSource () == back) //brings the user back to the general screen
	{
	    current_screen = "General_screen";
	    addbuttons ();
	}

	else if (e.getSource () == continuebutton) //brings the user from welcome screen to the school purchasing screen
	{
	    current_screen = "School_screen";
	    addbuttons ();
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

		gold = currency / 100;
		silver = (currency - gold * 100) / 10;
		bronze = currency - (gold * 100) - (silver * 10);

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


    public void paint (Graphics g)
    {
	new Sizing_Class (); //calls the sizing method to dynamically change the size and location of buttons, labels, etc.

	setBackground (Color.black); //sets the background to black

	int x = this.getSize ().width; //gets width and height of users display size, used in sizing
	int y = this.getSize ().height;

	//creates fonts for buttons, titles, labels
	Font buttonFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (2, x));
	Font titleFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (10, x));
	Font labelFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (3, x));

	if (current_screen.equals ("School_welcome_screen")) //when the user is on the welcome screen
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

	if (current_screen.equals ("School_screen")) //when the user is on the purchasing screen
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
	    back.setSize (Sizing_Class.X_Pos (8, x), Sizing_Class.Y_Pos (3, x));
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
	    back.setLocation (Sizing_Class.X_Pos (0, x), Sizing_Class.Y_Pos (95, y));
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
	    back.setFont (buttonFont);
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
	    g.drawString ("Gold: " + gold, Sizing_Class.X_Pos (75, x), Sizing_Class.Y_Pos (85, y));
	    g.drawString ("Silver: " + silver, Sizing_Class.X_Pos (75, x), Sizing_Class.Y_Pos (90, y));
	    g.drawString ("Bronze: " + bronze, Sizing_Class.X_Pos (75, x), Sizing_Class.Y_Pos (95, y));

	    g.drawString ("+ " + addedhealth, Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (14, y));
	    g.drawString ("+ " + addedmana, Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (24, y));
	    g.drawString ("+ " + addedstrength, Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (34, y));
	    g.drawString ("+ " + addedstamina, Sizing_Class.X_Pos (40, x), Sizing_Class.Y_Pos (44, y));



	}
    } // end of paint method


    public void addbuttons ()
    {
	if (current_screen == "School_welcome_screen") //if on the welcome screen add continue button
	{
	    add (continuebutton); //a button that brings the user to the purchasing screen
	}

	else if (current_screen == "School_screen") //adds items to the screen where purchases take place
	{
	    add (addHealth);
	    add (subHealth);
	    add (addMana);
	    add (subMana);
	    add (addStamina);
	    add (subStamina);
	    add (addStrength);
	    add (subStrength);
	    add (back);
	    add (buy);
	    remove (continuebutton);
	}

	else if (current_screen == "General_screen") //returns to the general screen and removes all buttons, labels, etc.
	{
	    remove (addHealth);
	    remove (subHealth);
	    remove (addMana);
	    remove (subMana);
	    remove (addStamina);
	    remove (subStamina);
	    remove (addStrength);
	    remove (subStrength);
	    remove (back);
	    remove (buy);
	}
    } //end of addbuttons ()
} // School_Screen class
