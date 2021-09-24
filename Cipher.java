/*
************************************************************************************************************************************************
********************* Name : Danny Smith *******************************************************************************************************
********************* Class : ICS 4U ***********************************************************************************************************
********************* Teacher : Baxter *********************************************************************************************************
********************* School : W.C.I ***********************************************************************************************************
********************* Prog ID : Clan Wars ******************************************************************************************************
********************* Programmers : Niko Haloulos, Tyler Ykema, Danny Smith ********************************************************************
********************* Program description : This program is used for encryption and decryption *************************************************
************************************************************************************************************************************************
*/

public class Cipher
{
    protected static String encrypt (String message, int Strength)
    {
	String Cipher_Message = "";
	if (Strength == 1) // rot 13
	{
	    Cipher_Message = Encrypt_Rot13 (message); // rot 13 encryption where variables are protected
	} // if
	else if (Strength == 2) // aes
	{
	    Cipher_Message = Encrypt_AES (message); // aes encryption where variables are protected
	} // else if
	return Cipher_Message;
    } // encrypt method


    private static String Encrypt_Rot13 (String message)
    {

	char alphabet[] =
	    {
	    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'
	    }  // used for encryption decryption


	    ;
	String CIPHER_MESSAGE = "";

	for (int i = 0 ; i < message.length () ; i++) // for each bit in the text
	{
	    for (int b = 0 ; b < alphabet.length ; b++) // for each character in the alphabet
	    {
		if (message.charAt (i) == alphabet [b]) // if character matches that in alphabet
		{
		    if (b + 13 > alphabet.length) // if out of range
		    {
			b = b - alphabet.length;
		    } // if
		    CIPHER_MESSAGE = CIPHER_MESSAGE + alphabet [b + 13]; // rotate letter
		} // if
	    } // for


	} // for

	return CIPHER_MESSAGE; // encrypted message
    } // ROT 13 Encryption


    private static String Encrypt_AES (String Message)
    {
	String CIPHER_MESSAGE = "";

	char alphabet[] =
	    {
	    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
	    'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
	    'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_', ' ', ','
	    }  // used for encryption decryption



	    ;
	String Alphabet[] = {
	    "1zD5", "2tuv", "3p9x", "4ncx", "5iX0", "6x3s", "704d", "8wR4", "9x3o", "i7xt", "vty8", "19qt", "ZZbn", "1ZYR", "1@%#", "12@b", "Yz^c", "IoWz",
	    "1Nin", "Qc64", "212c", "!zSm", "@f4X", "2xYu", "AQCY", "3R7s", "qxub", "-_3s", "!x_9", "*@bT", "19x4", "3193", "vyam", "Zquv", "@Gxt", "__p2", "3@52", "#cUm", "XQS5", "0Mu5",
	    "412x", "ZQ4D", "2xT6", "@Xp8", "@cBt", "2c67", "sicr", "JSHD", "ziSn", "5Txy", "ZyN7", "gstX", "57X4", "SYUX", "KJX6", "46x5", "9X5g", "Gci8", "@$c_", "@xCt",
	    "IX56", "27x8", "_3x7", "1026", "QPZY", "18z5"
	    }; // substitute char for string
	String enc_1[] = new String [61]; // encryption before swap and rotation
	String enc_2[] = new String [61]; // encryption after swap and rotation
	for (int i = 0 ; i < Message.length () ; i++)
	{

	    for (int b = 0 ; b < alphabet.length ; b++)
	    {

		if (Message.charAt (i) == alphabet [b])
		{
		    enc_1 [i] = Alphabet [b];

		} // if
	    } //for b
	} // for

	// Swap bits to enc_2  -->

	for (int i = 0 ; i < 10 ; i++) // for 10 rounds
	{


	    for (int b = 0 ; b < 10 ; b++) // for each bit swap first 10
	    {
		if (b + 1 == 10)
		{
		    enc_2 [b - 9] = enc_1 [b];
		} // if
		else
		{
		    enc_2 [b + 1] = enc_1 [b];
		} // else

	    } // for int b


	    for (int b = 10 ; b < 20 ; b++) // for each bit swap bits 11 - 20
	    {
		if (b + 1 == 20)
		{
		    enc_2 [b - 9] = enc_1 [b];
		} // if
		else
		{
		    enc_2 [b + 1] = enc_1 [b];
		} // else

	    } // for int b

	    for (int b = 30 ; b > 20 ; b--) // for each bit swap bits 21 - 30
	    {
		if (b - 1 == 19)
		{
		    enc_2 [b + 9] = enc_1 [b];
		} // if
		else
		{
		    enc_2 [b - 1] = enc_1 [b];
		} // else

	    } // for int b

	    for (int b = 40 ; b > 30 ; b--) // for each bit swap bits 31 - 40
	    {
		if (b - 1 == 29)
		{
		    enc_2 [b + 9] = enc_1 [b];
		} // if
		else
		{
		    enc_2 [b - 1] = enc_1 [b];
		} // else

	    } // for int b

	    for (int b = 40 ; b < 50 ; b++) // for each bit swap bits 41 - 50
	    {
		if (b + 1 == 50)
		{
		    enc_2 [b - 9] = enc_1 [b];
		} // if
		else
		{
		    enc_2 [b + 1] = enc_1 [b];
		} // else

	    } // for int b

	    /*
	    for (int b = 60 ; b > 50 ; b--) // for each bit swap bits 51 - 60
	     {
		 if (b - 1 == 49)
		 {
		     enc_2 [b + 9] = enc_1 [b];
		 } // if
		 else
		 {
		     enc_2 [b - 1] = enc_1 [b];
		 } // else

	     } // for int b

	     // swap bits to enc_2    <--

	    */


	    // swap round number <--

	    for (int c = 0 ; c < 60 ; c++) // clear enc_2 and reset enc_1
	    {
		enc_1 [c] = enc_2 [c];
		if (enc_1 [c] != null)
		{

		}
		enc_2 [c] = "";
	    } // for c


	} // for i [ 10 rounds ]

	/*

	60 bit max

	sub

	Repeat ->                  [ + ]
	-->
	-->
	<--
	<--
	-->
	<--
	swap round & 58
	Repeat <-                  [ + ]






	*/
	for (int i = 0 ; i < 60 ; i++) // Splice array into single string
	{
	    if (enc_1 [i] != null)
	    {
		CIPHER_MESSAGE = CIPHER_MESSAGE + enc_1 [i];
	    } // if not equal to null
	} // for int i

	return CIPHER_MESSAGE;

    } // AES encryption



    protected static String decrypt (String message, int Strength)
    {

	String cipher_Message = "";
	if (Strength == 1) // rot 13
	{
	    cipher_Message = Decrypt_Rot13 (message); // rot 13 encryption where variables are protected
	} // if
	else if (Strength == 2) // aes
	{
	    cipher_Message = Decrypt_AES (message); // aes encryption where variables are protected
	} // else if
	return cipher_Message;
    } // decrypt method


    private static String Decrypt_Rot13 (String message)
    {
	char alphabet[] =
	    {
	    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'
	    }  // used for encryption decryption


	    ;
	String CIPHER_MESSAGE = "";

	for (int i = 0 ; i < message.length () ; i++) // for each bit in the text
	{
	    for (int b = 0 ; b < alphabet.length ; b++) // for each character in the alphabet
	    {
		if (message.charAt (i) == alphabet [b]) // if character matches that in alphabet
		{
		    if (b - 13 < 0)  // if out of range
		    {
			b = b + alphabet.length;
		    } // if
		    CIPHER_MESSAGE = CIPHER_MESSAGE + alphabet [b - 13]; // rotate letter
		} //if
	    } // for


	} // for
	return CIPHER_MESSAGE;
    } // ROT 13 encryption


    private static String Decrypt_AES (String Message)
    {
	String CIPHER_MESSAGE = "";
	String t = "";
	String alphabet[] =
	    {
	    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
	    "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
	    "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "-", "_", " ", ","
	    }  // used for encryption decryption


	    ;
	String Alphabet[] = {
	    "1zD5", "2tuv", "3p9x", "4ncx", "5iX0", "6x3s", "704d", "8wR4", "9x3o", "i7xt", "vty8", "19qt", "ZZbn", "1ZYR", "1@%#", "12@b", "Yz^c", "IoWz",
	    "1Nin", "Qc64", "212c", "!zSm", "@f4X", "2xYu", "AQCY", "3R7s", "qxub", "-_3s", "!x_9", "*@bT", "19x4", "3193", "vyam", "Zquv", "@Gxt", "__p2", "3@52", "#cUm", "XQS5", "0Mu5",
	    "412x", "ZQ4D", "2xT6", "@Xp8", "@cBt", "2c67", "sicr", "JSHD", "ziSn", "5Txy", "ZyN7", "gstX", "57X4", "SYUX", "KJX6", "46x5", "9X5g", "Gci8", "@$c_", "@xCt",
	    "IX56", "27x8", "_3x7", "1026", "QPZY", "18z5"
	    }; // substitute char for string

	String enc_1[] = new String [61]; // encryption before swap and rotation
	String enc_2[] = new String [61]; // encryption after swap and rotation

	for (int i = 0 ; i < Message.length () ; i += 4)
	{
	    t = t + Message.charAt (i) + Message.charAt (i + 1) + Message.charAt (i + 2) + Message.charAt (i + 3);
	    for (int b = 0 ; b < Alphabet.length ; b++)
	    {


		if (t.equals (Alphabet [b])) // if four characters = 4 characters in Alphabet convert to single char
		{
		    enc_1 [i] = alphabet [b];
		    t = "";
		    b = Alphabet.length;


		} // if
	    } //for b
	} // for




	for (int i = 9 ; i >= 0 ; i--) // for 10 rounds
	{
	    /*
		// swap round number -->

		String temp = enc_1 [i];
		enc_2 [i] = enc_1 [58];                                            // not working?
		enc_2 [58] = temp;


		// swap round number <--
		*/


	    for (int b = 9 ; b >= 0 ; b--) // for each bit swap first 10
	    {
		if (b - 1 == -1)
		{
		    enc_2 [b + 9] = enc_1 [b];

		} // if
		else
		{
		    enc_2 [b - 1] = enc_1 [b];

		} // else

	    } // for int b


	    for (int b = 19 ; b >= 10 ; b--) // for each bit swap bits 11 - 20
	    {
		if (b - 1 == 9)
		{
		    enc_2 [b + 9] = enc_1 [b];
		} // if
		else
		{
		    enc_2 [b - 1] = enc_1 [b];
		} // else

	    } // for int b

	    for (int b = 20 ; b < 30 ; b++) // for each bit swap bits 21 - 30
	    {
		if (b + 1 == 30)
		{
		    enc_2 [b - 9] = enc_1 [b];
		} // if
		else
		{
		    enc_2 [b + 1] = enc_1 [b];
		} // else

	    } // for int b

	    for (int b = 30 ; b < 40 ; b++) // for each bit swap bits 31 - 40
	    {
		if (b + 1 == 40)
		{
		    enc_2 [b - 9] = enc_1 [b];
		} // if
		else
		{
		    enc_2 [b + 1] = enc_1 [b];
		} // else

	    } // for int b

	    for (int b = 50 ; b >= 40 ; b--) // for each bit swap bits 41 - 50
	    {
		if (b - 1 == 39)
		{
		    enc_2 [b + 9] = enc_1 [b];
		} // if
		else
		{
		    enc_2 [b - 1] = enc_1 [b];
		} // else

	    } // for int b

	    for (int b = 50 ; b < 60 ; b++) // for each bit swap bits 51 - 60
	    {
		if (b + 1 == 60)
		{
		    enc_2 [b - 9] = enc_1 [b];
		} // if
		else
		{
		    enc_2 [b + 1] = enc_1 [b];
		} // else

	    } // for int b

	    // swap bits to enc_2    <--



	    for (int c = 0 ; c < 60 ; c++) // clear enc_2 and reset enc_1
	    {
		enc_1 [c] = enc_2 [c];
		enc_2 [c] = "";
	    } // for c



	} // for i [ 10 rounds ]


	/*

	60 bit max

	sub

	Repeat ->                  [ + ]
	-->
	-->
	<--
	<--
	-->
	<--
	swap round & 58
	Repeat <-                  [ + ]






	*/
	for (int i = 0 ; i < 60 ; i++) // Splice array into single string
	{
	    if (enc_1 [i] != null)
	    {
		CIPHER_MESSAGE = CIPHER_MESSAGE + enc_1 [i];
	    } // if not equal to null
	} // for int i


	return CIPHER_MESSAGE;

    } // AES decryption
}


