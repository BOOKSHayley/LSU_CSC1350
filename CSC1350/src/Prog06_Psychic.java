import java.util.Scanner;

/**
 * This program is designed to test the user's psychic ability
 * by seeing if they can guess a randomly-generated whole number
 * 
 * CSC 1350 Programming Project # 6
 * Section 2
 * 
 * @author Hayley Roberts (hrobe39)
 * @since 21 October 2019 
 * 
 */

public class Prog06_Psychic {
	
	/**
	 * validInt checks to see if the user entered an integer.
	 * If a non-integer is entered, the user is prompted until they do.
	 * Development date: 21 October 2019
	 * @author Hayley Roberts (hrobe39)
	 * 
	 * @param scanner, Scanner object to take in user input
	 * @param output, String that user will see to prompt for integer
	 * @return valid integer that the user entered
	 */
	//Step 2: Create method to check for valid integer
	public static int validInt(Scanner scanner, String output) {
		boolean validInput = false;
		int toReturn = 0;
		do {
			System.out.print(output);
			if(scanner.hasNextInt()) {
				toReturn = scanner.nextInt();
				validInput = true;
			} else {
				String badInput = scanner.next();
				System.out.printf("Error: %s is not a valid input.\n", badInput);
			}
		} while(!validInput);
		
		return toReturn;
	}
	
	
	/**
	 * generateRandomNumber makes a random number within a range.
	 * Development date: 21 October 2019 
	 * @author Hayley Roberts (hrobe39)
	 *
	 * @param max, integer that is the upper bound for random number, inclusive
	 * @param min, integer that is the lower bound for the random number, inclusive
	 * @return random integer between max and min (inclusive)
	 */
	//Step 3: create method to return a random number in a range
	public static int generateRandomNumber(int max, int min) {
		return min + (int)(Math.random() * (max - min + 1));
	}

	
	public static void mainMethod() {
		
		//Step 1: create scanner and get upper and lower bounds
		Scanner userInput = new Scanner(System.in);		
		
		//initialize flag booleans for input validation
		boolean validUpperBound = false; //check to see is upperBound is 3 numbers higher than lower bound
		boolean sentinelVal = false; //check to see if the sentinel value (q/Q) is entered
		
		//initialize number variables for computations
		int upperBound = 0;
		int lowerBound = 0;
		int randomNumber = 0;
		int userNumber = 0;
		double correctCounter = 0;
		double totalCounter = 0;
		double successRate = -1;
		
		System.out.println("So you think you're psychic? Well, let's put that to the test!");
		System.out.println("\nI will pick a number in a range, and you have to \"guess\" what the number is.\nI will let you pick the range.");
		
		//get lower and upper bounds
		lowerBound = validInt(userInput, "Please enter the whole number lower bound of the range:");
		
		//check to see if upperBound is 3 numbers higher than lowerBound
		do {
			upperBound = validInt(userInput, "Please enter the whole number upper bound of the range (at least 3 numbers higher than " + lowerBound + "):");
			
			if(upperBound >= lowerBound + 3) {
				validUpperBound = true;
			} else {
				System.out.printf("Error: %d is not 3 numbers higher than %d.\n", upperBound, lowerBound);
			}
		} while(!validUpperBound);
		
		//Step 4: generate a random number and see if user entered that number
		do {
			randomNumber = generateRandomNumber(upperBound, lowerBound);
			System.out.printf("Enter a whole number between %d and %d or Q to quit:", upperBound, lowerBound);
			
			//if integer is not inputed, might be sentinel
			if(userInput.hasNextInt()) {
				userNumber = userInput.nextInt();
				totalCounter++; //increment here so can get total number of inputs
				
				if(userNumber == randomNumber) {
					correctCounter++; //increment here so can get total number of correct inputs
					System.out.printf("Well, you must be psychic! You guessed the correct number: %d\n", randomNumber);
				} else {
					System.out.printf("I am doubting your psychic abilities. You did not guess the right number: %d\n", randomNumber);
				}
				
			} else {
				String badInput = userInput.next();
				
				if(badInput.equals("Q") || badInput.equals("q")) { //if sentinel (upper or lower case), user finished playing. End loop.
					sentinelVal = true;
				} else { //if not the sentinel, just a typo. User keeps playing.
					System.out.printf("Error: %s is not a valid input.\n", badInput);
				}
				
			}
			
		}while(!sentinelVal);
		
		//Step 5: calculate successRate and output to user.
		successRate = correctCounter / totalCounter * 100;
		System.out.printf("Your success rate is %.5f%%", successRate);
		System.out.println("\nThanks for playing!");
		
		//Step 6: close Scanner so no resource leak
		userInput.close();
	}

}
