/**
 * This program is designed to calculate an employee's weekly payroll amount
 * 
 * CSC 1350 Programming Project No 3
 * Section 2
 * 
 * @author hrobe39
 * @since September 30, 2019
 *
 */

import java.util.Scanner;

public class Prog03_PayCalc {

	public static void mainMethod() {
		//Step 1 - make scanner and read employee #, declare and initialize needed variables
		Scanner userInput = new Scanner(System.in);
		
		//booleans needed for printing the correct statement after computations
		boolean idGreaterThan1000 = false;
		boolean idGreaterThan2000 = false;
		boolean didOvertime = false;
		
		//global variables needed for printing the results
		double weeklyPay = 0;
		
		//variable needed for employees >= 1000
		double annualSalary = 0;
		
		//variable needed for employees < 1000
		double numberOfHours = 0;
		double hourlyPay = 0;
		double overtimeHours = 0;
		double bonusHourlyPay = 0;
		
		System.out.print("Please enter employee ID: ");
		int idNumber = userInput.nextInt();
		
		//Step 2- see if employee # is greater than 1000.		
		if(idNumber >= 1000) {
			idGreaterThan1000 = true; //set to true for printing purposes
			
			//Read annual salary
			System.out.print("Please enter annual salary: ");
			annualSalary = userInput.nextDouble();
			
			//divide annual salary by number of weeks in a year
			weeklyPay = annualSalary / 52;
			
			//if employee id >= 2000, add 10% to salary if salary >= 75000
			if(idNumber >= 2000 && annualSalary >= 75000) {
				idGreaterThan2000 = true; //set to true for printing purposes
				
				//need to add percent bonus to salary, but do not change annualSalary value
				final double PERCENT_BONUS = 1.10;
				//divide annualSalary and bonus by 52 weeks in a year
				weeklyPay = annualSalary * PERCENT_BONUS / 52;
			}
			
		}else {
			//read number of hours worked and hourly pay
			System.out.print("Please enter the number of hours worked: ");
			numberOfHours = userInput.nextDouble();
			
			System.out.print("Please enter your hourly pay: ");
			hourlyPay = userInput.nextDouble();
			
			//calculate weekly pay by multiplying hours and hourlyPay
			weeklyPay = numberOfHours * hourlyPay;
			
			//any hours over 40, the bonus is 1.5 * hourlyPay
			if(numberOfHours > 40) {
				didOvertime = true;	//set to true for printing purposes
				overtimeHours = numberOfHours - 40;
				numberOfHours = 40; //set numberOfHours to 40 bc number of non-overtime hours
				bonusHourlyPay = hourlyPay * 1.5;
				weeklyPay = numberOfHours * hourlyPay;
				double bonus = overtimeHours * bonusHourlyPay;
				weeklyPay += bonus;
			}
			
		}
		
		//Step 3 - print weekly pay and salary calculations
		System.out.printf("Weekly salary is: %.2f.", weeklyPay);
		
		//Only include necessary information in the salary calculations
		if(idGreaterThan2000) {
			System.out.printf("\nSalary calculation: $%.2f annual salary * 1.1%% bonus / 52 weeks in a year = $%.2f.", annualSalary, weeklyPay);
		}else if(idGreaterThan1000) {
			System.out.printf("\nSalary calculation: $%.2f annual salary / 52 weeks in a year = $%.2f.", annualSalary, weeklyPay);
		}else if(didOvertime){
			System.out.printf("\nSalary calculation: %.0f hours worked x $%.2f hourly rate + %.2f overtime hours worked x $%.2f hourly rate = $%.2f.", numberOfHours, hourlyPay, overtimeHours, bonusHourlyPay, weeklyPay);
		} else {
			System.out.printf("\nSalary calculation: %.0f hours worked x $%.2f hourly rate = $%.2f.", numberOfHours, hourlyPay, weeklyPay);
		}
		
		//need to close Scanner object, so no resource leak
		userInput.close();

	}
	
	
}
