// The "World" class.
import java.applet.*;
import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
public class World extends Applet
{
    PrintWriter out;


    int map[] [] [] = new int [300] [300] [2];
    public void init ()
    {
	NumberFormat formatter = new DecimalFormat ("00");
	new Cipher ();

	// world generation -->

	for (int b = 0 ; b < 300 ; b++) // for y-axis of map
	{
	    for (int i = 0 ; i < 300 ; i++) // for x-axis of map
	    {
		map [i] [b] [0] = 15; // plains

		map [i] [b] [1] = (int) (Math.random () * 10); // population

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



	// manual add castles to map <--

	// castle populations  -- >
	try
	{
	    out = new PrintWriter (new FileWriter ("WORLD_1_INFO.txt"));
	    out.println (Cipher.encrypt ("149,149", 2)); // player coordinates
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
	    out = new PrintWriter (new FileWriter ("WORLD_1.txt"));

	    for (int c = 0 ; c < 2 ; c++)
	    {
		for (int b = 0 ; b < 300 ; b++) // for y-axis of map
		{
		    for (int z = 0 ; z < 3 ; z++)
		    {
			for (int i = 0 ; i < 100 ; i++) // for x-axis of map
			{
			    if (c == 0)
			    {
				out.print (Cipher.encrypt("" + formatter.format (map [i + (z * 100)] [b] [c]),2));
			       
			    }

			    else
			    {
				out.print (Cipher.encrypt ("" + formatter.format (map [i + (z * 100)] [b] [c]), 2));

			    }

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


    } // init method


    public void paint (Graphics g)
    {

    } // paint method
} // World class
