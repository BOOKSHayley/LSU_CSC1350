/**
 * Calculating Easter - This program is intended to find the
 * date that Easter Sunday falls on, based on the year the user inputs
 * 
 * CSC 1350	Programming Project No 2
 * Section 2 Louisiana State University
 *
 *@author hrobe39
 *@since September 6, 2019
 *
 */

import java.util.Scanner;

public class Prog02_EasterCalc {

	public static void mainMethod() {
	  //Step 1: Ask the user for the current year and set value into a variable
		//Step 1.1 - Create and import Scanner object
		Scanner userInput = new Scanner(System.in);
		
		//Step 1.2 Print out the question and ask for input
		System.out.print("When is Easter you ask?\nEnter the year of your choosing: ");
		
		//Step 1.3 Create a variable to hold the year from the user  
		int currentYear = userInput.nextInt(); 
		
		
	  //Step 2: Follow Carl Friedrick Gauss's algorithm
		//Step 2.1 Divide year by 100 and save the quotient (a) and remainder (b) in variables
		int a = currentYear / 100;
		int b = currentYear % 100;
		
		//Step 2.2 Divide a by 4, and save the quotient (c) and remainder (f) in variables
		int c = a / 4;
		int f = a % 4;
		
		//Step 2.3 Divide 8 * a + 13 by 25 and save quotient (g) in variable
		int g = (8 * a + 13) / 25;
		
		//Step 2.4 Divide year by 19 and save the remainder (h) in a variable
		int h = currentYear % 19;
		
		//Step 2.5 Divide b by 4 and save quotient (i) and remainder (j)
		int i = b / 4;
		int j = b % 4;
		
		//Step 2.6 Divide 19 * h + a - c - g + 15 by 30 and save remainder (k) in variable
		int k = (19 * h + a - c - g + 15) % 30;
		
		//Step 2.7 Divide h + 11 * k by 319 and save quotient (l) in variable
		int l = (h + 11 * k) / 319;
		
		//Step 2.8 Divide 2 * f + 2 * i - j - k + l + 32 by 7 and save remainder (n) in variable
		int n = (2 * f + 2 * i - j - k + l + 32) % 7;
		
		//Step 2.9 Divide k - l + n + 90 by 25 and save quotient (m) in variable
		int month = (k - l + n + 90) / 25;
		
		//Last Step Divide k - l + n + m + 19 by 32 and save remainder (d) in variable
		int day = (k - l + n + month + 19) % 32;
		
		
	  //Step 3: Print out the date of Easter in the format: "In <year>, Easter falls on <Month> / <Day>"
		System.out.printf("In %d, Easter falls on %d/%d.", currentYear, month, day);
		
	  //Step 4 Close the Scanner object at the very end, otherwise userInput has a "resource leak" warning
		userInput.close(); 

	}

}
