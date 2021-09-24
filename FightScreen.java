// The "FightScreen" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.Format;
import java.text.DecimalFormat;
import java.awt.event.*;


public class FightScreen extends Applet implements ActionListener
{
    Button attack, spell, defend, change_weapon, potions, weapon_up, weapon_down, magic_up, magic_down, potion_up, potion_down,
	attack_spell, flee, change_magic, fightbutton, perform_move, next_move, use_potion; //variables for buttons on users display, buttons to change screens, change weapons/magic/potions.

    Label health, armour, mana, magic, whos_turn, winner, current_user_health, current_enemy_health,
	current_usermana_label, current_enemymana_label, last_move, lastuser_move; //labels that show important information to the user like amount of health, or last enemy move

    int Hpamount = 100, Armouramount, Weaponselection, classtypeselection, Strengthamount, class_1, enemy_attack, Magicselection, Potionselection, enemy_move,  //integers that contain information about the users/enemy health, mana, damage, etc.
	Agilityamount, user_damage = 10, userHp = 100, enemyHp = 100, enemy_damage = 10, enemy_mana = 100, user_mana = 100, currentweapon, current_enemy_Hp = enemyHp,
	current_user_Hp = userHp, current_user_mana = user_mana, current_enemy_mana = enemy_mana, enemy_magic_damage = 5, user_magic_damage = 5, health_potion = 10, mana_potion = 10;

    String[] [] user_data = new String [25] [5]; //a string containing data about the users inventory of the user

    boolean turn_counter = true, userattack = false, enemyattack = false, userdefend = false,
	enemydefend = false, userspell = false, enemyspell = false, user_potion = false; //boolean variables for a fighting sequence

    //File User_Directory;

    TextField weapon_selection, magic_selection, potion_selection; //a textfield that tells the user about the current selected item

    Image users_image, enemy_image, health_image, mana_image, weapon_image, magic_image, potion_image; //images that will be used on many different screens

    String current_screen = "Fight_Screen", current_potion = "Health_potion", enemy_class, latestmove = "nothing", latestusermove = "nothing"; //string variables

    int current_enemy_healthbar = current_enemy_Hp, current_enemy_manabar = current_enemy_mana, current_enemy_damage = enemy_damage, current_enemy_magic_damage = enemy_magic_damage; //integers used during the fighting sequence
    int current_user_healthbar = current_enemy_Hp, current_user_manabar = current_user_mana, current_user_damage = user_damage, current_user_magic_damage = user_magic_damage, spellcost = 10;



    public void init ()
    {
	user_data [0] [0] = "Sword"; //Stand in values
	user_data [0] [1] = "1";
	user_data [1] [0] = "Mace";
	user_data [2] [0] = "Flail";
	user_data [3] [0] = "Weapon 4";
	user_data [4] [0] = "Weapon 5";
	user_data [5] [0] = "1";
	user_data [6] [0] = "2";
	user_data [7] [0] = "3";
	user_data [8] [0] = "4";
	user_data [9] [0] = "5";
	user_data [10] [0] = "Health Potion";
	user_data [11] [0] = "Mana Potion";

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
	enemy_class = "Archer";

	//retrieves images
	enemy_image = getImage (getDocumentBase (), "Images//" + enemy_class + ".png");
	users_image = getImage (getDocumentBase (), "Images//Knight.png");
	health_image = getImage (getDocumentBase (), "Images//HealthBar.png");
	magic_image = getImage (getDocumentBase (), "Images//th2MPSZ4SI.jpg");
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
    } // init method


    public void actionPerformed (ActionEvent e)  //a method that is called whenever a button is pressed
    {
	if (e.getSource () == change_weapon) //if change_weapon button is pressed then current screen is changed to weapon selection screen
	{
	    current_screen = "Change_Weapon_Screen";
	    changescreen (e);
	    repaint ();
	}

	else if (e.getSource () == change_magic) //if change_magic button is pressed then current screen is changed to magic selection screen
	{
	    current_screen = "Magic_Selection_Screen";
	    changescreen (e);
	    repaint ();
	}

	else if (e.getSource () == potions) //if potions button is pressed then current screen is changed to potion screen
	{
	    current_screen = "Potion_Screen";
	    changescreen (e);
	    repaint ();
	}

	else if (e.getSource () == flee) //if flee button is pressed then change current screen to the general screen
	{
	    current_screen = "General_Screen";
	    changescreen (e);
	    repaint ();
	}

	else if (e.getSource () == fightbutton) //if the fight button is pressed within one of the subscreens then go back to fight screen
	{
	    current_screen = "Fight_Screen";
	    changescreen (e);
	    repaint ();
	}
	else if (e.getSource () == next_move) //if next move is hit on win screen go to general screen
	{
	    current_screen = "General_Screen";
	    changescreen (e);
	    repaint ();
	}

	// attacking************************************************
	else if (e.getSource () == attack) //if attack button is pressed then user attack will be executed
	{
	    if (turn_counter == true) //if it is the users turn then they may attack
	    {
		//System.out.println ("attack");
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
		//System.out.println ("Spell");
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
	    else
	    {
	    }
	}

	else
	{
	}


	//*********************************************************


	if (current_screen == "Change_Weapon_Screen")
	{
	    if (e.getSource () == weapon_up) //if weapon up is pressed then add one to weapon selection, integer is used in switch below
	    {
		Weaponselection++;
		if (Weaponselection >= 5)
		    Weaponselection = 0;
	    }
	    if (e.getSource () == weapon_down) //if weapon down is pressed then subtract one from weapon selection
	    {
		Weaponselection--;
		if (Weaponselection < 0)
		    Weaponselection = 4;
	    }
	    switch (Weaponselection) //switches between current weapons that can be selected and switches to the corresponding image
	    {
		case 0:
		    weapon_selection.setText (user_data [0] [0]); //switches to location in users array
		    weapon_image = getImage (getDocumentBase (), "Images//" + user_data [0] [0] + ".png"); //switches weapon image on screen
		    user_damage = 15;
		    break;
		case 1:
		    weapon_selection.setText (user_data [1] [0]);
		    weapon_image = getImage (getDocumentBase (), "Images//" + user_data [1] [0] + ".png ");
		    user_damage = 12;
		    break;
		case 2:
		    weapon_selection.setText (user_data [2] [0]);
		    weapon_image = getImage (getDocumentBase (), "Images//" + user_data [2] [0] + ".png ");
		    break;
		case 3:
		    weapon_selection.setText (user_data [3] [0]);
		    weapon_image = getImage (getDocumentBase (), "Images//" + user_data [3] [0] + ".png ");
		    break;
		case 4:
		    weapon_selection.setText (user_data [4] [0]);
		    weapon_image = getImage (getDocumentBase (), "Images//" + user_data [4] [0] + ".png ");
		    break;
	    }
	    repaint (); //redraws current screen with updated weapon image
	}
	else
	{
	}
	currentweapon = Weaponselection; //sets the current weapon to the selected weapon from the switch

	if (current_screen == "Magic_Selection_Screen")
	{
	    if (e.getSource () == magic_up) //if magic up is pressed then add one to magic selection, integer is used in switch below
	    {
		Magicselection++;
		if (Magicselection >= 5)
		    Magicselection = 0;
	    }
	    if (e.getSource () == magic_down) //if magic down is pressed then subtract one from magic selection
	    {
		Magicselection--;
		if (Magicselection < 0)
		    Magicselection = 5;
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
	}

	if (current_screen == "Potion_Screen")
	{
	    if (e.getSource () == potion_up) //if potion up is pressed then add one to potion selection, integer is used in switch below
	    {
		Potionselection++;
		if (Potionselection >= 2)
		    Potionselection = 0;
	    }
	    else if (e.getSource () == potion_down) //if potion down is pressed then subtract one from potion selection
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


	    if (e.getSource () == use_potion && current_user_Hp < userHp) //if the user has selected to use a potion, then call
	    {
		current_screen = "Fight_Screen";
		//turn_counter = false;
		user_potion = true;
		userattack = false;
		userdefend = false;
		userspell = false;
		//enemy_move = (int) (Math.random () * (5));
		move ();
		changescreen (e);
		repaint ();

		//System.out.println ("Potion used");
	    }
	}

	if (current_screen == "Win_Screen" || current_screen == "General_Screen") //if the user has won the fight or has gone back to the general screen, then remove all buttons, labels, etc.
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

	    setscreen ();
	}
	else
	{
	}
    } //actionPerformed method


    public void paint (Graphics g)
    {
	new Sizing_Class (); //uses the sizing class to dynamically set the size of buttons and labels, etc.

	int x = this.getSize ().width; //gets width and height of users display size, used in sizing
	int y = this.getSize ().height;

	setBackground (Color.lightGray); //sets the background of the display to light gray

	//sets fonts for buttons, titles, labels
	Font buttonFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (2, y));
	Font titleFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (4, y));
	Font labelFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (2, y));

	if (current_screen.equals ("Fight_Screen")) //if the current screen is the fight screen
	{
	       setBackground (Color.lightGray); //sets the background of the display to light gray

	//sets fonts for buttons, titles, labels
	Font buttonFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (2, y));
	Font titleFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (4, y));
	Font labelFont = new Font ("Dialog", Font.PLAIN, Sizing_Class.Y_Pos (2, y));

	    //draws box in the middle of screen outlining the controls for the user
	    g.setColor (Color.gray);
	    g.fillRect (Sizing_Class.X_Pos (41, x), Sizing_Class.Y_Pos (0, y), Sizing_Class.X_Pos (18, x), Sizing_Class.Y_Pos (100, y));
	    g.setColor (Color.red);
	    g.drawRect (Sizing_Class.X_Pos (41, x), Sizing_Class.Y_Pos (0, y), Sizing_Class.X_Pos (18, x), Sizing_Class.Y_Pos (100, y));

	    /*
			g.setColor (Color.gray);
			g.fillRect (Sizing_Class.X_Pos (0, x), Sizing_Class.Y_Pos (65, y), Sizing_Class.X_Pos (100, x), Sizing_Class.Y_Pos (100, y));
			g.setColor (Color.red);
			g.drawRect (Sizing_Class.X_Pos (0, x), Sizing_Class.Y_Pos (65, y), Sizing_Class.X_Pos (100, x), Sizing_Class.Y_Pos (100, y));


			g.setFont (titleFont);
			g.drawString ("FIGHT!", Sizing_Class.X_Pos (44, x), Sizing_Class.Y_Pos (4, y));
	    */

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
	    g.fillRect (Sizing_Class.X_Pos (71, x), Sizing_Class.Y_Pos (4, y), Sizing_Class.Y_Pos (current_enemy_healthbar * 11 / enemyHp, x), Sizing_Class.Y_Pos (5, y));  //does not work properly
	    g.drawImage (health_image, Sizing_Class.X_Pos (14, x), Sizing_Class.Y_Pos (1, y), Sizing_Class.Y_Pos (20, x), Sizing_Class.Y_Pos (13, y), this);
	    g.drawImage (health_image, Sizing_Class.X_Pos (66, x), Sizing_Class.Y_Pos (1, y), Sizing_Class.Y_Pos (20, x), Sizing_Class.Y_Pos (13, y), this);

	    //Mana bar images and rectangles
	    g.setColor (Color.blue);
	    g.fillRect (Sizing_Class.X_Pos (19, x), Sizing_Class.Y_Pos (58, y), Sizing_Class.Y_Pos (current_user_mana * 13 / user_mana, x), Sizing_Class.Y_Pos (5, y));
	    g.fillRect (Sizing_Class.X_Pos (69, x), Sizing_Class.Y_Pos (58, y), Sizing_Class.Y_Pos (current_enemy_mana * 13 / user_mana, x) + 2, Sizing_Class.Y_Pos (5, y)); //does not work properly
	    g.drawImage (mana_image, Sizing_Class.X_Pos (16, x), Sizing_Class.Y_Pos (55, y), Sizing_Class.Y_Pos (17, x), Sizing_Class.Y_Pos (11, y), this);
	    g.drawImage (mana_image, Sizing_Class.X_Pos (66, x), Sizing_Class.Y_Pos (55, y), Sizing_Class.Y_Pos (17, x), Sizing_Class.Y_Pos (11, y), this);

	    //Draws the user and enemy images
	    g.drawImage (users_image, Sizing_Class.X_Pos (12, x), Sizing_Class.Y_Pos (15, y), Sizing_Class.X_Pos (30, y), Sizing_Class.X_Pos (30, y), this);
	    g.drawImage (enemy_image, Sizing_Class.X_Pos (65, x), Sizing_Class.Y_Pos (15, y), Sizing_Class.Y_Pos (30, y), Sizing_Class.Y_Pos (30, y), this);

	}

	else if (current_screen.equals ("Change_Weapon_Screen")) //if the screen is change weapon screen, the user can change weapons here
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
	    g.drawImage (weapon_image, Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (5, y), Sizing_Class.Y_Pos (50, y), Sizing_Class.Y_Pos (50, y), this);

	}

	//brings user to magic selection screen
	else if (current_screen.equals ("Magic_Selection_Screen"))
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

	else if (current_screen.equals ("Potion_Screen"))
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
	    g.drawImage (potion_image, Sizing_Class.X_Pos (35, x), Sizing_Class.Y_Pos (5, y), Sizing_Class.Y_Pos (50, y), Sizing_Class.Y_Pos (50, y), this);

	}

	else if (current_screen == "Win_Screen")
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

    } //end of paint method





    public void setscreen ()  //a method to set up the current screen
    {
	if (current_screen == "Fight_Screen")
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


	else if (current_screen == "Change_Weapon_Screen")
	{
	    add (fightbutton);
	    add (weapon_selection);
	    add (weapon_up);
	    add (weapon_down);

	}


	else if (current_screen == "Magic_Selection_Screen")
	{
	    add (fightbutton);
	    add (magic_up);
	    add (magic_down);
	    add (magic_selection);
	}


	else if (current_screen == "Potion_Screen")
	{
	    add (fightbutton);
	    add (potion_selection);
	    add (potion_up);
	    add (potion_down);
	    add (use_potion);
	}

	else if (current_screen == "Win_Screen")
	{
	    add (winner);
	    add (next_move);
	}

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
	    setscreen ();
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

	    current_screen = "Fight_Screen";

	    setscreen ();
	}

	else if (e.getSource () == next_move)
	{
	    remove (next_move);
	    remove (winner);
	    remove (use_potion);

	    current_screen = "General_Screen";

	    setscreen ();
	}
	else
	{
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
		// System.out.println ("enemy defend used");
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
	    current_screen = "Win_Screen";
	}
	else if (current_user_Hp <= 0) //if the user has no health, then the enemy wins the battle
	{
	    winner.setText ("ENEMY HAS WON THE BATTLE!");
	    current_screen = "Win_Screen";
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
		//System.out.println ("enemy attack used");
	    }
	    else if (enemy_move == 0)
	    {
		current_user_Hp = (current_user_Hp - current_enemy_magic_damage);
		current_user_healthbar = current_user_Hp;
		current_enemy_mana = current_enemy_mana - spellcost;
		latestmove = "cast spell";
		// System.out.println ("enemy magic used");
	    }
	    current_user_health.setText (current_user_Hp + "/" + userHp);
	    current_enemymana_label.setText (current_enemy_mana + "/" + enemy_mana);
	    last_move.setText ("Enemies move was " + latestmove);
	    lastuser_move.setText ("Your last move was " + latestusermove);

	    /*System.out.println ("enemy health: " + current_enemy_healthbar);
	    System.out.println ("user health: " + current_user_healthbar);
	    System.out.println ("Enemy move was: " + enemy_move);*/
	    turn_counter = true;
	}
    } //end of enemy_moves method
} // FightScreen class


