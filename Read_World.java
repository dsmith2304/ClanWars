// The "Read_World" class.
import java.applet.*;
import java.awt.*;
import java.io.*;


public class Read_World extends Applet
{
    BufferedReader in; // file to read encrypted world file
    String ln1 = ""; // used to read encrypted world line
    String convert = ""; // string to convert
    PrintWriter out;
    File file = new File ("WORLD_1.txt");
    String world[] [] [] = new String [300] [300] [2];
    public void init ()
    {
	new Cipher ();

	try
	{

	    in = new BufferedReader (new FileReader (file));
	    
	    for (int d = 0 ; d < 2 ; d++)
	    {
		for (int y = 0 ; y < 300 ; y++)
		{
		    for (int c = 0 ; c < 3 ; c++)
		    {
			ln1 = in.readLine ();


			for (int i = 0 ; i < 800 ; i += 8)
			{
			    convert = "" + ln1.charAt (i) + ln1.charAt (i + 1) + ln1.charAt (i + 2) + ln1.charAt (i + 3) + ln1.charAt (i + 4) + ln1.charAt (i + 5) + ln1.charAt (i + 6) + ln1.charAt (i + 7);

			    world [i / 8 + (c * 100)] [y] [d] = Cipher.decrypt (convert, 2);
			    convert = "";
			} // for i
		    } // c

		} // for y

	    } // for d

	    in.close ();
	    // test world read -->
	    /*
	    out = new PrintWriter (new FileWriter ("OUTFILE.txt"));
	    for (int c = 0 ; c < 2 ; c++)
	    {
		for (int y = 0 ; y < 300 ; y++)
		{
		    for (int x = 0 ; x < 300 ; x++)
		    {
			out.print (Integer.parseInt (world [x] [y] [c]));
		    } // for x
		    out.println ();
		} // for y
	    } // for c
	    out.close ();
	    */
	    // test world read <--
	    
	} // try
	catch (IOException e)
	{

	} // catch





    } // init method


    public void paint (Graphics g)
    {

    } // paint method
} // Read_World class
