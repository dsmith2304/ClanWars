// The "MainScreen" class.
import java.applet.*;
import java.awt.*;

public class MainScreen extends Applet
{
    Button forward, left, right, down, fight, special; //movement buttons

    int coordinate_x = 149, coordinate_y = 149; //map dimensions

    Font fnt; //font

    int map[] [] [] = new int [300] [300] [2]; // Map population

    //images
    Image blacksmith, store, school, castlewall, castlegate, castlegrounds, plains, road, forest, mine, mountain, lake;

    //quantities of inventory items and font
    Font TITLE = new Font ("Serif", Font.BOLD, 32);
    int strength = 5;
    int wood = 0;
    int gold = 0, copper = 0, fish = 0, deerskin = 0, bearskin = 0, iron = 0;
    int luck = 5;
    int Gold = 0, Silver = 0, Copper = 0, InventoryAmount = 60, LEVEL = 2;
    String NAME, WEAPON, CLASS, WORLD;

    //font
    Font font;

    //map coordinates
    int X, Y;
    int x, y;
    //sounds
    AudioClip mouseclick, valley, river, castle, woods;

    String Inventory[] [] = new String [100] [2];

    boolean exists = false;

    public void init ()
    {
	new Sizing_Class (); //calls the sizing class


    } // init method


    public void woodcutting ()
    {
	Graphics g = getGraphics ();

	g.setFont (font);
	g.setColor (Color.yellow);

	g.drawString ("You begin to chop wood...", Sizing_Class.X_Pos (350, x), Sizing_Class.Y_Pos (300, y));

	int chance = 0;

	chance = (int) (Math.random () * 100);

	if (chance > (80 - strength)) //20% chance for wood
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
		    } //if
		} //for
	    } // if couldent find wood
	}
    }



    public void mining ()
    {
	Graphics g = getGraphics ();

	g.setFont (font);
	g.setColor (Color.red);

	g.drawString ("You begin to mine for ore...", Sizing_Class.X_Pos (350, x), Sizing_Class.Y_Pos (300, y));

	int chance = 0;

	chance = (int) (Math.random () * 100);

	if (chance > (95 - strength)) //5% chance for gold
	{
	    for (int i = 0 ; i < 100 ; i++)
	    {
		if (Inventory [i] [0].equalsIgnoreCase ("Gold"))
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
			Inventory [i] [0] = "Gold";
			Inventory [i] [1] = "1";
		    } //if
		} //for
	    } // if couldent find wood
	}
	else if (chance > (85 - strength)) //15% for iron
	{
	    for (int i = 0 ; i < 100 ; i++)
	    {
		if (Inventory [i] [0].equalsIgnoreCase ("Iron"))
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
			Inventory [i] [0] = "Iron";
			Inventory [i] [1] = "1";
		    } //if
		} //for
	    } // if couldent find wood

	}
	else if (chance > (75 - strength)) //25% for copper
	{
	    for (int i = 0 ; i < 100 ; i++)
	    {
		if (Inventory [i] [0].equalsIgnoreCase ("Copper"))
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
			Inventory [i] [0] = "Copper";
			Inventory [i] [1] = "1";
		    } //if
		} //for
	    } // if couldent find wood

	}
	//65% chance for nothing
    }


    public void fishing ()
    {
	Graphics g = getGraphics ();

	g.setFont (font);
	g.setColor (Color.red);

	g.drawString ("You start fishing...", Sizing_Class.X_Pos (350, x), Sizing_Class.Y_Pos (300, y));

	int chance = 0;

	chance = (int) (Math.random () * 100);

	if (chance > (90 - luck)) //10% chance to catch a fish
	{
	    for (int i = 0 ; i < 100 ; i++)
	    {
		if (Inventory [i] [0].equalsIgnoreCase ("Fish"))
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
			Inventory [i] [0] = "Fish";
			Inventory [i] [1] = "1";
		    } //if
		} //for
	    } // if couldent find wood

	}
	//90% chance for nothing
    }


    public void hunting ()
    {
	Graphics g = getGraphics ();

	g.setFont (font);
	g.setColor (Color.red);

	g.drawString ("You quietly stalk your prey...", Sizing_Class.X_Pos (350, x), Sizing_Class.Y_Pos (300, y));

	int chance = 0;

	chance = (int) (Math.random () * 100);

	if (chance > (90 - (luck - strength)))
	{
	    for (int i = 0 ; i < 100 ; i++)
	    {
		if (Inventory [i] [0].equalsIgnoreCase ("Bear Skin"))
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
			Inventory [i] [0] = "Deer Skin";
			Inventory [i] [1] = "1";
		    } //if
		} //for
	    } // if couldent find wood

	}
	else if (chance > (80 - (luck - strength)))
	{
	    for (int i = 0 ; i < 100 ; i++)
	    {
		if (Inventory [i] [0].equalsIgnoreCase ("Deer Skin"))
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
			Inventory [i] [0] = "Deer Skin";
			Inventory [i] [1] = "1";
		    } //if
		} //for
	    } // if couldent find wood

	}
	//70% chance for nothing
    }


    //button movements
    //calls each method after the button is pressed
    public boolean action (Event e, Object o)
    {
	Graphics g = getGraphics ();
	if (e.target == forward)
	{
	    coordinate_y--;
	    mouseclick.play ();
	    repaint ();

	}
	else if (e.target == left)
	{
	    coordinate_x--;
	    mouseclick.play ();
	    repaint ();
	}
	else if (e.target == right)
	{
	    coordinate_x++;
	    mouseclick.play ();
	    repaint ();
	}
	else if (e.target == down)
	{
	    coordinate_y++;
	    mouseclick.play ();
	    repaint ();
	}
	else if (e.target == fight)
	{
	    //call fight method
	}
	//call these methods when the special button changes labels
	else if (e.target == special && special.getLabel ().equals ("Hunt"))
	{
	    hunting ();
	}
	else if (e.target == special && special.getLabel ().equals ("Fish"))
	{
	    fishing ();
	}
	else if (e.target == special && special.getLabel ().equals ("Mine"))
	{
	    mining ();
	}
	else if (e.target == special && special.getLabel ().equals ("Chop Wood"))
	{
	    woodcutting ();
	}
	else if (e.target == special && special.getLabel ().equals ("Blacksmith"))
	{
	    //blacksmith ();
	}
	else if (e.target == special && special.getLabel ().equals ("School"))
	{
	    //school ();
	}
	else if (e.target == special && special.getLabel ().equals ("Store"))
	{
	    //store ();
	}
	else if (e.target == special && special.getLabel ().equals ("Enter"))
	{
	}
	X = coordinate_x;
	Y = coordinate_y;
	return true;
    } //action method


    public void paint (Graphics g)
    {
	x = this.getSize ().width;
	y = this.getSize ().height;

	if (Runtime.getRuntime ().freeMemory () < 3 * 300 * 300) //memory allocation
	{
	    System.gc (); //garbage collector for memory
	}
	//paints an image at a specific location on the map

	if (paint_screen.equalsIgnoreCase ("General_Screen")
	{
	    if (map [X] [Y] [0] == 10)
	    {
		g.drawImage (blacksmith, 0, 0, x, y, this);
		special.setLabel ("Blacksmith");
	    }
	    else if (map [X] [Y] [0] == 11)
	    {
		g.drawImage (store, 0, 0, x, y, this);
		special.setLabel ("Store");
	    }
	    else if (map [X] [Y] [0] == 12)
	    {
		g.drawImage (school, 0, 0, x, y, this);
		special.setLabel ("School");
	    }
	    else if (map [X] [Y] [0] == 15)
	    {
		g.drawImage (plains, 0, 0, x, y, this);
		special.setLabel ("Hunt");
	    }
	    else if (map [X] [Y] [0] == 16)
	    {
		g.drawImage (road, 0, 0, x, y, this);
	    }
	    else if (map [X] [Y] [0] == 17)
	    {
		g.drawImage (forest, 0, 0, x, y, this);
		special.setLabel ("Chop Wood");
	    }
	    else if (map [X] [Y] [0] == 18)
	    {
		g.drawImage (mine, 0, 0, x, y, this);
		special.setLabel ("Mine");
	    }
	    else if (map [X] [Y] [0] == 19)
	    {
		g.drawImage (mountain, 0, 0, x, Y, this);
	    }
	    else if (map [X] [Y] [0] == 20)
	    {
		g.drawImage (lake, 0, 0, x, y, this);
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

	    //sets thread bar for the user at the top of the screen
	    g.drawString ("Class: " + CLASS, Sizing_Class.X_Pos (1, x), Sizing_Class.Y_Pos (4, y));
	    g.drawString ("Weapon: " + WEAPON, Sizing_Class.X_Pos (25, x), Sizing_Class.Y_Pos (4, y));
	    g.drawString ("Name: " + NAME, Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (4, y));
	    g.drawString ("Gold: " + Gold, Sizing_Class.X_Pos (83, x), Sizing_Class.Y_Pos (4, y));
	    g.drawString ("Silver: " + Silver, Sizing_Class.X_Pos (1, x), Sizing_Class.Y_Pos (12, y));
	    g.drawString ("Copper: " + Copper, Sizing_Class.X_Pos (26, x), Sizing_Class.Y_Pos (12, y));
	    g.drawString ("Inventory: " + InventoryAmount + " / 100", Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (12, y));
	    g.drawString ("Time: " + WORLD, Sizing_Class.X_Pos (83, x), Sizing_Class.Y_Pos (12, y));
	    g.drawString ("Inventory: I", Sizing_Class.X_Pos (1, x), Sizing_Class.Y_Pos (21, y));
	    g.drawString ("Stats: S", Sizing_Class.X_Pos (26, x), Sizing_Class.Y_Pos (21, y));
	    g.drawString ("Level: " + LEVEL, Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (21, y));
	} // if general screen
    } // paint method
} // MainScreen class


