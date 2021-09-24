// The "Places" class.
import java.applet.*;
import java.awt.*;

public class places extends Applet
{
    Font TITLE = new Font ("serif", Font.BOLD, 32);
    int x = 0, y = 0;
    String paint_screen = "";
    int strength = 5;
    int wood = 0;
    int gold = 0, iron = 0, copper = 0, fish = 0, deerskin = 0, bearskin = 0;
    int luck = 5;
    int GOLD = 0, SILVER = 0, COPPER = 0, INVENTORY = 60, LEVEL = 2;
    String NAME = "DANNY", WEAPON = "SWORD";
    String CLASS = "knight", WORLD = "8:23pm";
    Thread InfoBar = new Thread ()

    {
	public void run ()
	{

	    while (true)
	    {
		Graphics h = getGraphics ();
		h.setColor (Color.black);
		h.fillRect (0, 0, x, y);

		h.setColor (Color.green);

		h.drawString ("Class     : " + CLASS, Sizing_Class.X_Pos (1, x), Sizing_Class.Y_Pos (3, y));
		h.drawString ("Weapon    : " + WEAPON, Sizing_Class.X_Pos (25, x), Sizing_Class.Y_Pos (3, y));
		h.drawString ("Name      : " + NAME, Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (3, y));
		h.drawString ("Gold      : " + GOLD, Sizing_Class.X_Pos (75, x), Sizing_Class.Y_Pos (3, y));
		h.drawString ("Silver    : " + SILVER, Sizing_Class.X_Pos (2, x), Sizing_Class.Y_Pos (11, y));
		h.drawString ("Copper    : " + COPPER, Sizing_Class.X_Pos (26, x), Sizing_Class.Y_Pos (11, y));
		h.drawString ("Inventory : " + INVENTORY + " / 100", Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (11, y));
		h.drawString ("Time      : " + WORLD, Sizing_Class.X_Pos (75, x), Sizing_Class.Y_Pos (11, y));
		h.drawString ("Inventory : I", Sizing_Class.X_Pos (1, x), Sizing_Class.Y_Pos (20, y));
		h.drawString ("Stats     : S", Sizing_Class.X_Pos (26, x), Sizing_Class.Y_Pos (20, y));
		h.drawString ("Level     : " + LEVEL, Sizing_Class.X_Pos (50, x), Sizing_Class.Y_Pos (20, y));
		paint (h);
		try
		{
		    Thread.sleep (1000);
		}
		catch (InterruptedException e)
		{

		}
	    } // while
	} // run
    } //infobar


    ;
    public void woodcutting ()
    {
	paint_screen = "woodcutting";
	repaint ();
	int chance = 0;
	chance = (int) (Math.random () * 100);
	if (chance > (80 - strength)) // 20% chance for wood
	{
	    wood++;
	}
	// 80% chance for nothing
    }


    public void mining ()
    {
	Graphics g = getGraphics ();

	paint_screen = "mining";
	repaint ();
	int chance = 0;
	chance = (int) (Math.random () * 100);
	if (chance > (95 - strength)) // 5% for gold
	{
	    gold++;
	}
	else if (chance > (85 - strength)) // 15% for iron
	{
	    iron++;
	}

	else if (chance > (75 - strength)) // 25% for copper
	{
	    copper++;
	}
	// 65% for nothing

    }


    public void Fishing ()
    {
	Graphics g = getGraphics ();
	paint_screen = "fishing";
	repaint ();
	int chance = 0;
	chance = (int) (Math.random () * 100);
	if (chance > (90 - luck)) // 10% to catch a fish
	{
	
	    fish++;
	}
	// 90% chance for nothing
    }


    public void hunting ()
    {
	Graphics g = getGraphics ();
	paint_screen = "hunting";
	repaint ();
	int chance = 0;
	chance = (int) (Math.random () * 100);
	if (chance > (90 - (luck - strength))) // 10% chance for bearskin
	{
	    bearskin++;
	}

	else if (chance > (80 - (luck - strength))) // 20% chance for dearskin
	{
	    deerskin++;
	}
	// 70% chance for nothing
    }


    public void init ()
    {
	new time ();
	new Sizing_Class ();
	InfoBar.start ();
	woodcutting ();






    } // init method


    public void paint (Graphics g)
    {
	x = this.getSize ().width;
	y = this.getSize ().height;
	if (paint_screen.equalsIgnoreCase ("hunting"))
	{
	    this.setBackground (Color.black);
	    TITLE = new Font ("serif", Font.BOLD, Sizing_Class.Y_Size (10, y));
	    g.setFont (TITLE);
	    g.setColor (Color.red);

	    g.drawString ("You quietly stalk your prey", Sizing_Class.X_Pos (25, x), Sizing_Class.Y_Pos (40, y));
	}
	if (paint_screen.equalsIgnoreCase ("mining"))
	{
	    this.setBackground (Color.black);
	    TITLE = new Font ("serif", Font.BOLD, Sizing_Class.Y_Size (10, y));
	    g.setFont (TITLE);
	    g.setColor (Color.red);

	    g.drawString ("You begin to mine for ore", Sizing_Class.X_Pos (25, x), Sizing_Class.Y_Pos (40, y));
	}
	if (paint_screen.equalsIgnoreCase ("fishing"))
	{
	    this.setBackground (Color.black);
	    TITLE = new Font ("serif", Font.BOLD, Sizing_Class.Y_Size (10, y));
	    g.setFont (TITLE);
	    g.setColor (Color.red);

	    g.drawString ("You start fishing", Sizing_Class.X_Pos (25, x), Sizing_Class.Y_Pos (40, y));
	}
	if (paint_screen.equalsIgnoreCase ("woodcutting"))
	{
	    this.setBackground (Color.black);
	    TITLE = new Font ("serif", Font.BOLD, Sizing_Class.Y_Size (10, y));
	    g.setFont (TITLE);
	    g.setColor (Color.red);

	    g.drawString ("You begin to chop wood", Sizing_Class.X_Pos (25, x), Sizing_Class.Y_Pos (40, y));
	}
    } // paint method
} // Places class


