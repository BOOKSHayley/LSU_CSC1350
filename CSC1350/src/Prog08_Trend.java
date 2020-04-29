import java.util.Scanner;

/**
 * This program is designed to help users see how their stock investments have been trending over time.
 * 
 * CSC 1350 Programming Project #8
 * Section 2
 * 
 * @author hrobe39
 * @since 11 November 2019
 *
 */
public class Prog08_Trend {
	
	/**
	 * This method is used to make sure the input is an integer between a min and max
	 * @param userInput, Scanner object
	 * @param output, String to prompt user for information
	 * @param min, integer minimum value inclusive
	 * @param max, integer maximum value inclusive
	 * @return an integer from the user that is between the min and max
	 */
	public static int validInt(Scanner userInput, String output, int min, int max) {
		boolean valid = false;
		int val = 0;
		do {
			System.out.println(output);
			if(userInput.hasNextInt()) {
				val = userInput.nextInt();
				if(val >= min && val <= max) {
					valid = true;
				} else {
					System.out.printf("Error: %d is not between %d and %d.\n", val, min, max);
				}
			} else {
				String badInput = userInput.next();
				System.out.printf("Error: %s is not a valid input.\n", badInput);
			}
		}while(!valid);
		
		return val;
	}
	
	/**
	 * This method is used to make sure the input is a floating point greater than a min
	 * @param userInput, Scanner object
	 * @param output, String to prompt user for information
	 * @param min, double minimum value inclusive
	 * @return a floating point from the user greater than a min
	 */
	public static double validDouble(Scanner userInput, String output, double min) { //no max bc don't know what max is acceptable
		boolean valid = false;
		double val = 0;
		do {
			System.out.println(output);
			if(userInput.hasNextDouble()) {
				val = userInput.nextDouble();
				if(val >= min) {
					valid = true;
				} else {
					System.out.printf("Error: %.2f is not greater than %.2f.\n", val, min);
				}
			} else {
				String badInput = userInput.next();
				System.out.printf("Error: %s is not a valid input.\n", badInput);
			}
		}while(!valid);
		
		return val;
	}

	public static void mainMethod() {
		//Step 1: Create Scanner and variables
		Scanner userInput = new Scanner(System.in);
		int days = 0; //number of days user wants to input
		double sum = 0; //sum of closing values
		double average = 0; //average of closing values

		//Step 2: take number of days and create an array of size days
		days = validInt(userInput, "How many days would you like to trend?", 1, 365); //cannot have 0 days. Also not calculating trend for more than a year
		double[] closingValues = new double[days];
		
		//Step 3: loop through array and fill with user inputs. Also sum up all the values so no redundant iteration
		for(int i = 0; i < closingValues.length; i++) {
			String output = "Enter closing value for day " + (i+1) + ":";
			closingValues[i] = validDouble(userInput, output, 0); //closing value must be >= to 0.
			sum += closingValues[i];
		}
		
		//Step 4: calculate average
		average = sum / days; //days cannot be 0 because excluding 0 from input
		
		//Step 5: loop through array again and find distance from average of each value. Print out.
		System.out.println("Day:  Closing Value:   Trend:");
		
		for(int i = 0; i < closingValues.length; i++) {
			double distFromAvg = closingValues[i] - average; //this order bc if closing value < avg, negative distance
			System.out.printf("%3d %12.2f %11.2f\n", i+1, closingValues[i], distFromAvg);
		}
		
		System.out.printf("Average Daily Closing Value: %.2f", average);
		
		//Step 6: close Scanner so no resource leak
		userInput.close();
		
	}

}
