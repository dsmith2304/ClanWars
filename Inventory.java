// The "Inventory" class.
import java.applet.*;
import java.awt.*;

public class Inventory extends Applet
{
    int x = 0;
    int y = 0;
    String paint_screen = "Inventory";
    List Inv = new List ();
    Button Drop = new Button ("DROP"), Info = new Button ("INFO");
    TextArea INFO = new TextArea ("");
    Font fnt_Main_Screen_Title = new Font ("MonoSpaced", Font.PLAIN, 20);
    String Inventory[] [] = new String [100] [2];
    public void init ()
    {
	new Sizing_Class ();
	add (Inv);
	add (Drop);
	add (Info);
	add (INFO);
	x = getSize ().width;
	y = getSize ().height;
	fnt_Main_Screen_Title = new Font ("MonoSpaced", Font.PLAIN, (int) Math.sqrt (Sizing_Class.Y_Size (35, y) + Sizing_Class.X_Size (35, x)));
	Inv.setFont (fnt_Main_Screen_Title);
	for (int i = 0 ; i < 100 ; i++)
	{
	    // if (!Inventory [i] [0].equalsIgnoreCase (""))
	    // {
	    //     Inv.add (Inventory [i] [0]);
	    // }
	}
    } // init method


    public void paint (Graphics g)
    {
	x = getSize ().width;
	y = getSize ().height;

	if (paint_screen.equalsIgnoreCase ("Inventory"))
	{
	    fnt_Main_Screen_Title = new Font ("MonoSpaced", Font.PLAIN, (int) Math.sqrt (Sizing_Class.Y_Size (25, y) + Sizing_Class.X_Size (25, x)));
	    g.setFont (fnt_Main_Screen_Title);
	    g.setColor (Color.black);
	    g.fillRect (0, 0, x, y);
	    Inv.setLocation (Sizing_Class.X_Pos (5, x), Sizing_Class.Y_Pos (10, y));
	    Inv.setSize (Sizing_Class.X_Size (40, x), Sizing_Class.Y_Size (80, y));
	    INFO.setLocation (Sizing_Class.X_Pos (60, x), Sizing_Class.Y_Pos (10, y));
	    INFO.setSize (Sizing_Class.X_Size (40, x), Sizing_Class.Y_Size (80, y));
	    Drop.setLocation (Sizing_Class.X_Pos (47, x), Sizing_Class.Y_Pos (10, y));
	    Info.setLocation (Sizing_Class.X_Pos (47, x), Sizing_Class.Y_Pos (80, y));
	    Drop.setSize (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (10, y));
	    Info.setSize (Sizing_Class.X_Pos (10, x), Sizing_Class.Y_Pos (10, y));

	    g.setColor (Color.green);

	    g.drawString ("Inventory", Sizing_Class.X_Pos (47, x), Sizing_Class.Y_Pos (5, y));
	}

    } // paint method
} // Inventory class
