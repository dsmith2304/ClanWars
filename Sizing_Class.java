/*
************************************************************************************************************************************************
********************* Name : Danny Smith *******************************************************************************************************
********************* Class : ICS 4U ***********************************************************************************************************
********************* Teacher : Baxter *********************************************************************************************************
********************* School : W.C.I ***********************************************************************************************************
********************* Prog ID : Clan Wars ******************************************************************************************************
********************* Programmers : Niko Haloulos, Tyler Ykema, Danny Smith ********************************************************************
********************* Program description : This class takes in a screen size and percentages for placing items in an applet *******************
******************************************* it allows programmers to dynamicly resize items according to percentages of the screen. They can ***
******************************************* set entity sizes to 10% by 10% at 25% by 25% as an example rather then pixel sizes. ****************
************************************************************************************************************************************************
*/



public class Sizing_Class
{
    public static int X_Pos (int x, int screen)  // return percentage size as pixel size for x location
    {
	int X_Pixel = 0;
	X_Pixel = screen / 100 * x; // size of screen pixels per percent * percent
	return X_Pixel; // pixel size for given percentage
    } //X_Pos method


    public static int Y_Pos (int y, int screen)  // return percentage size as pixel size for y location
    {
	int Y_Pixel = 0;
	Y_Pixel = screen / 100 * y;
	return Y_Pixel; // pixel size y-axis for given percentage
    } // Y_Pos method


    public static int X_Size (int xs, int screen)  // return percentage size as pixel size for x item size
    {
	int XS_Pixel = 0;
	XS_Pixel = screen / 100 * xs;
	return XS_Pixel; // pixel size for given percentage
    } // X_Size method


    public static int Y_Size (int ys, int screen)  // return percentage size as pixel size for y item size
    {
	int YS_Pixel = 0;
	YS_Pixel = screen / 100 * ys;

	return YS_Pixel; // pixel size for given percentage
    } // Y_Size method



} // Applet_Class class
