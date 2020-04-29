import java.util.Scanner;

/**
 * This program is used to determine the eligibility 
 * of a student to be a student athlete the next year,
 * dependent on their credit hours and GPA
 * 
 * CSC 1350 Programming project # 7
 * Section 2
 * 
 * @author hrobe39
 * @since 28 October 2019
 */
public class Prog07_Qualify {

	//Step 1: create global variables to be used in methods
	public static int eligibleCounter = 0; //count of eligible students
	public static int studentCounter = 0; //count of total students
	public static double reqGPA = 0; //required GPA
	public static int reqHours = 0; //required number of total hours
	
	//Step 2: input validation for integers
	/**
	 * validInt checks to see that the user input is an integer between a max and min value 
	 * Developed: 28 October 2019
	 * @author hrobe39
	 * 
	 * @param userInput, Scanner object
	 * @param output, String to prompt user for response
	 * @param min, integer minimum
	 * @param max, integer maximum
	 * @return the valid integer the user entered
	 */
	public static int validInt(Scanner userInput, String output, int min, int max) {
		boolean valid = false;
		int input = -1;
		
		do {
			System.out.println(output);
			if(userInput.hasNextInt()) {
				input = userInput.nextInt();
				
				//make sure input is within the range
				if(input <= max && input >= min) {
					valid = true;
				} else {
					System.out.println("Error: Value not within acceptable range.");
				}
			}else {
				String badInput = userInput.next();
				System.out.printf("Error: %s is not a valid input.\n", badInput);
			}
		}while(!valid);
		
		return input;
	}
	
	//Step 3: input validation for Y/N
	/**
	 * validYN checks to see if the user entered Y/N
	 * Developed 28 October 2019
	 * @author hrobe39
	 * 
	 * @param userInput, Scanner object
	 * @param output, String to prompt user for response
	 * @return true for Y, and false for N
	 */
	public static boolean validYN(Scanner userInput, String output) {
		boolean valid = false; //boolean to see if input is valid
		boolean yesNo = false; //true - yes; false - no
		do {
			System.out.println(output);
			
			String input = userInput.next();
			if(input.contentEquals("y") || input.contentEquals("Y")) {
				yesNo = true;
				valid = true;
			} else if(input.contentEquals("n") || input.contentEquals("N")) {
				yesNo = false;
				valid = true;
			} else {
				System.out.printf("Error: %s is not a valid input.\n", input);
			}
		}while(!valid);
		
		return yesNo;
	}
	
	//Step 5: find necessary GPA and Hours
	/**
	 * findReq determines what the GPA and hour requirements are based on class level
	 * Developed 28 October 2019
	 * @author hrobe39
	 * 
	 * @param classLevel, integer
	 */
	public static void findReq(int classLevel) {
		//changes global variables so no return value
		if(classLevel == 1) {
			reqGPA = 1.7;
			reqHours = 25;
		} else if(classLevel == 2) {
			reqGPA = 1.85;
			reqHours = 50; 
		} else if(classLevel == 3) {
			reqGPA = 1.95;
			reqHours = 85;
		} else {
			System.out.println("Error in findReq.");
		}
	}
	
	//Step 7: print individual student report
	/**
	 * printStudentReport prints any relevant info to the console for the user to see
	 * Developed 28 October 2019
	 * @author hrobe39
	 *  
	 * @param studentID, String 
	 * @param classLevel, integer
	 * @param hours, integer number of total hours
	 * @param GPA, double Grade Point Average
	 * @param eligible, boolean: true if eligible and false if not
	 */
	public static void printStudentReport(String studentID, int classLevel, int hours, double GPA, boolean eligible) {
		printNStars(4);
		System.out.printf(" Report for student %s ", studentID);
		printNStars(4);
		System.out.printf("\nClass: %15d\nCumulative Hours: %4d\nCurrent Year GPA: %8.3f\n", classLevel, hours, GPA);
		printNStars(3);
		if(eligible) {
			System.out.print(" ELIGIBLE ");
		} else {
			System.out.print(" INELIGIBLE ");
		}
		printNStars(3);
		System.out.println();		
	}
	
	//Step 8: print certain number of stars
	/**
	 * printNStars prints n number of stars to the console
	 * Developed 28 October 2019
	 * @author hrobe39
	 * 
	 * @param n, integer number of stars
	 */
	public static void printNStars(int n) {
		for(int i = 0; i < n; i++) {
			System.out.print("*");
		}
	}
	
	//Step 4: decide if a student is eligible
	/**
	 * eligible determines if the student is eligible for next year
	 * Developed 28 October 2019
	 * @author hrobe39
	 * 
	 * @param userInput, Scanner object
	 */
	public static void eligible(Scanner userInput) {
		studentCounter++; //increment number of students
		
		//grab needed info from user. Make sure to validate input (except ID)
		System.out.println("Enter student ID: ");
		String studentID = userInput.next();
		int classLevel = validInt(userInput, "Enter student class (1 for Freshman, 2 for Sophomore, 3 for Junior)", 1, 3);
		int totalPrevHours = validInt(userInput, "Enter total hours completed before this year: ", 0, 100);
		
		int totalCurHours = totalPrevHours; //keep count of total hours overall
		double GPA = 0;
		
		do {
			
			int classHours = validInt(userInput, "Enter hours for class: ", 0, 5);
			int grade = validInt(userInput, "Input class grade value (4 for A, 3 for B, 2 for C, 1 for D, and 0 for F): ", 0, 4);
			
			totalCurHours += classHours; //update amount of total hours
			GPA += (classHours * grade); //update GPA, divide after add everything
			
		}while(validYN(userInput, "Do you have more classes to enter for this student? (Y/N)"));
		
		if(totalCurHours > 0) {
			GPA = GPA / (totalCurHours - totalPrevHours); //divide GPA by total hours for the year
		}
		
		findReq(classLevel); //change the GPA and hour requirements based on grade level before using them
		
		//decide if eligible
		if(totalCurHours >= reqHours && GPA >= reqGPA) {
			printStudentReport(studentID, classLevel, totalCurHours, GPA, true);
			eligibleCounter++;
		} else {
			printStudentReport(studentID, classLevel, totalCurHours, GPA, false);
		}
	}
	
	
	public static void mainMethod() {
		//Step 1 (con't): Create a scanner for user input
		Scanner userInput = new Scanner(System.in);
		
		//Step 6: ask user for student info until they finish. Print statistics when done
		do {
			eligible(userInput);
		}while(validYN(userInput, "Would you like to enter information for additional student(s)? (Y/N)"));
		
		printNStars(44);
		System.out.printf("\n* %30s %9s *\n", "SUMMARY STATISTICS", "");
		printNStars(44);
		System.out.println();
		System.out.printf("NUMBER OF STUDENTS PROCESSED: %4d\nNUMBER FOUND TO BE ELIGIBLE: %5d", studentCounter, eligibleCounter);
		
		userInput.close(); //Step 9: close Scanner so no resource leak
	}

}
