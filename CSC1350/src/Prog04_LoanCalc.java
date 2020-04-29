import java.util.Scanner;

/**
 * This program is intended to calculate the monthly and total payment amount for a given loan
 * 
 * CSC 1350 Programming project #4
 * Section 2
 * 
 * @author hrobe39
 * @since October 7, 2019
 *
 */

public class Prog04_LoanCalc {

	public static void mainMethod() {
		//Step 1: create a scanner and grab loanAmount, years, and annual interest rate from user
		Scanner userInput = new Scanner(System.in);
		
		//variables for user input and validation
		boolean inputValidated = false;	//boolean to check if input is acceptable
		double loanAmount = 0;
		int years = 0;
		double annualInterestRate = 0;
		String badInput = "";
		
		//variables for calculating monthly interest, principal, and balance
		double monthlyInterest = 0;
		double principal = 0;
		double balance = 0;
		double totalInterest = 0;
		
		//Step 2: Validate input. Put in loop so can ask until input correct
		do {
			System.out.print("Enter loan amount (greater than $0.00): ");
			if(userInput.hasNextDouble()) {
				loanAmount = userInput.nextDouble();
			} else {
				//save bad input so no infinite loop
				badInput = userInput.next();
				loanAmount = -1;
			}
			
			if(loanAmount <= 0) {
				System.out.println("Error: Loan must be a number greater than $0.00");
			} else {
				inputValidated = true;
			}
			
		}while(!inputValidated);
		
		inputValidated = false;
		
		do {
			System.out.print("Enter loan duration in years (whole number greater than 0): ");
			if(userInput.hasNextInt()) {
				years = userInput.nextInt();	
			} else {
				//save bad input so no infinite loop
				badInput = userInput.next();
				years = -1;
			}
			
			if(years <= 0) {
				System.out.println("Error: number of years must be greater than 0");
			}else {
				inputValidated = true;
			}
			
		} while(!inputValidated);
		
		inputValidated = false;
		
		do {
			System.out.print("Enter annual interest rate (value between 0.0 and 100.0): ");
			if(userInput.hasNextDouble()) {
				annualInterestRate = userInput.nextDouble();
			} else {
				//save bad input so no infinite loop
				badInput = userInput.next();
				annualInterestRate = -1;
			}
			
			if(annualInterestRate < 0 || annualInterestRate > 100) {
				System.out.println("Error: annual interest rate must be a number between 0 and 100");
			} else {
				inputValidated = true;
			}
			
		} while(!inputValidated);
		
		
		//Step 3: calculate monthly payment and print out all values
		double monthlyPmt = ((loanAmount / years) * (1 + annualInterestRate / 100)) / 12;

		System.out.printf("Loan Amount: $%.2f", loanAmount);
		System.out.printf("\nNumber of Years: %d", years);
		System.out.printf("\nAnnual Interest Rate: %.2f%%", annualInterestRate);
		System.out.printf("\nMonthly Payment: $%.2f", monthlyPmt);
		
		//Step 4: find interest, principal, and balance, and print them out
		System.out.printf("\n%15s %15s %15s %15s", "Payment #", "Interest", "Principal", "Balance");
		
		balance = loanAmount; // do not use loanAmount in while loop bc don't want to change its value
		int counter = 1; //counter for the number of payments
		
		while(balance > 0.0) {
			monthlyInterest = balance * (annualInterestRate / 100 / 12);
			principal = monthlyPmt - monthlyInterest;
			
			//if less than monthlyPmt left, amount to pay is the balance remaining 
			if(balance < monthlyPmt) {
				principal = balance;
			}
			balance -= principal;
			
			//the total payment is the interest + the loan amount so really only need to repeatedly add interests
			totalInterest += monthlyInterest;
			
			//print out the values calculated
			System.out.printf("\n%-18d $%10.2f   $%12.2f   $%13.2f", counter, monthlyInterest, principal, balance);
			
			//increment counter so correct payment number
			counter++;
		}
		
		//print out the total amount being paid
		System.out.printf("\nTotal Payment: $%.2f", totalInterest+loanAmount);
		
		//close Scanner object at the end so no resource leak
		userInput.close();
	}

}
