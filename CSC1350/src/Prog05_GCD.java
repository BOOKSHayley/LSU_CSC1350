import java.util.Scanner;

/**
 * This program is designed o find the Greatest Common Divisor between two positive integers
 * 
 * CSC 1350 Programming project #5
 * Section 2
 * 
 * @author hrobe39
 * @since October 14, 2019
 */
public class Prog05_GCD {

	public static void mainMethod() {
		//Step 1 - Create a Scanner and prompt user for 2 numbers
		Scanner userInput = new Scanner(System.in);
		
		boolean inputValid = false; //Boolean flag for input validation
		
		//2 numbers from the user. initialization doesn't matter.
		int num1 = 0; 
		int num2 = 0;
		int smallestNum = 0; //this is for iteration purposes
		
		int gcd = 1; //Greatest common divisor. Init to 1 bc all numbers are divisible by 1
		
		//input validation for the 2 positive integers
		do {
			System.out.println("Please enter an integer greater than 0: ");
			
			if(userInput.hasNextInt()) {
				num1 = userInput.nextInt();
				
				if(num1 > 0) {
					inputValid = true;
				} else {
					System.out.printf("Error: %d is not greater than 0.\n", num1);
				}
				
			} else {
				String badInput = userInput.next(); //must take badInput or get an infinite loop
				System.out.printf("Error: %s is not an integer.\n", badInput);
			}
			
		}while(!inputValid);
		
		inputValid = false; //need to reset boolean for next input validation
		
		do {
			System.out.println("Please enter another integer greater than 0: ");
			
			if(userInput.hasNextInt()) {
				num2 = userInput.nextInt();
				
				if(num2 > 0) {
					inputValid = true;
				} else {
					System.out.printf("Error: %d is not greater than 0.\n", num2);
				}
				
			} else {
				String badInput = userInput.next(); //must take badInput or get an infinite loop
				System.out.printf("Error: %s is not an integer.\n", badInput);
			}
			
		}while(!inputValid);
		
		//Step 2 - find the greatest common divisor through iteration
		
		//find out which number is smallest so don't iterate too far
		if(num1 > num2) {
			smallestNum = num2;
			
		} else {
			smallestNum = num1;
		}
		
		//iterate until k is the smallest number (gcd may be smallest number)
		for(int k = 1; k <= smallestNum; k++) {
			//if the remainder between num and k is 0, num is divisible by k and thus a divisor
			if(num1%k == 0 && num2%k == 0) {
				gcd = k;
			}
		}

		//tell the user the GCD
		System.out.printf("\nThe greatest common divisor of %d and %d is %d\n", num1, num2, gcd);
		
		//Step 3 - print the divisors of each number
		System.out.printf("\nDivisors for %d:\n", num1);
		
		//iterate until k is num1 bc num1 is a divisor of itself
		for(int k = 1; k <= num1; k++) {
			//if the remainder between num1 and k is 0, num1 is divisible by k and thus a divisor
			if(num1 % k == 0) {
				System.out.println(k);
			}
		}
		
		System.out.printf("\nDivisors for %d:\n", num2);
		
		//iterate until k is num2 bc num2 is a divisor of itself
		for(int k = 1; k <= num2; k++) {
			//if the remainder between num2 and k is 0, num2 is divisible by k and thus a divisor
			if(num2 % k == 0) {
				System.out.println(k);
			}
		}
		
		//close Scanner so no resource leak
		userInput.close();
		
	}

}
