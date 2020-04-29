import java.util.Scanner;

/**
 * This program is a library of movies. The user can enter movies and search for movies they entered
 * CSC 1350 Programming Project #10
 * Section 2
 * 
 * @author hrobe39
 * @since 25 November 2019
 *
 */
public class Prog10_MovieLib {
	
	/**
	 * validInt makes sure the user inputs a valid integer between a max and min
	 * Developed: 25 November 2019
	 * @author hrobe39
	 * 
	 * @param userInput, Scanner object
	 * @param output, String to prompt user for information
	 * @param min, int minimum value (inclusive)
	 * @param max, int maximum value (inclusive)
	 * @return integer from user that is between a max and min
	 */
	public static int validInt(Scanner userInput, String output, int min, int max) {
		boolean valid = false;
		int value = 0;
		do {
			System.out.println(output);
			if(userInput.hasNextInt()){
				value = userInput.nextInt();
				
				if(value >= min && value <= max) {
					valid = true;
				} else {
					System.out.printf("Error: %d is not between %d and %d\n", value, min, max);
				}
			} else {
				String badInput = userInput.next();
				System.out.printf("Error: %s is not a valid input", badInput);
			}
			
		} while(!valid);
		
		return value;
	}
	
	/**
	 * fillArray fills a given array with strings from the user
	 * Developed: 25 November 2019
	 * @author hrobe39
	 * 
	 * @param userInput, Scanner object
	 * @param arr, empty String array to be filled
	 * @param output, String to prompt user for information
	 * @param sentinel, String value that is user enters, the program stops
	 */
	public static void fillArray(Scanner userInput, String[] arr, String output) {
		userInput.hasNextLine();
		
		for(int i = 0; i <arr.length; i++) {
			System.out.println(output);
			
			if(i == 0) {
				userInput.nextLine(); //do this so first value taken is not the empty string
			}
				
			if(userInput.hasNextLine()) {
				String value = userInput.nextLine();
				arr[i] = value;
			}
		}
	}
	
	/**
	 * sortArray sorts an array in alphabetical order
	 * Developed: 25 November 2019
	 * @author hrobe39
	 * 
	 * @param arr, String array
	 */
	public static void sortArray(String[] arr) {
		boolean sorted = false;
		int i = 1;
		
		while(!sorted) {
			if(i == arr.length) {
				sorted = true;
			} else {
				sorted = true; //assume sorted is true
				
				for(int j = 0; j < arr.length-i; j++) {
					if(arr[j].compareTo(arr[j+1]) > 0) {
						swapElts(arr, j, j+1);
						sorted = false; //is swapped 2 elements, then it is not completely sorted
					}
				}
				
			}
			
			i++; //make sure to increment i
		}
		
	}
	
	/**
	 * searchArray looks through the array for a search value
	 * Developed: 25 November 2019
	 * @author hrobe39
	 * 
	 * @param arr, String array to search through
	 * @param searchVal, String that user is looking for
	 * @return int position in array (-1 if not found)
	 */
	public static int searchArray(String[] arr, String searchVal) {
		int position = -1;
		boolean found = false;
		int i = 0;
		
		while(i < arr.length && !found) {
			if(arr[i].equals(searchVal)) {
				position = i;
			}
			i++;
		}
		return position;
	}
	
	/**
	 * swapElts swaps two elements in an array
	 * Developed: 25 November 2019
	 * @author hrobe39
	 * 
	 * @param arr, String array
	 * @param elt1, one element to switch
	 * @param elt2, second element to switch
	 */
	public static void swapElts(String[] arr, int elt1, int elt2){
		String temp = arr[elt1];
		arr[elt1] = arr[elt2];
		arr[elt2] = temp;
	}

	public static void mainMethod() {
		//Step 1: make Scanner object and variables
		Scanner userInput = new Scanner(System.in);
		boolean reachedSentinel = false;
		String searchVal = "";
		int searchValPosition = -1;
		
		//Step 2: ask user for number of movies in lib, and make array with that many movies
		int numMovies = validInt(userInput, "How many movies are in your personal library?", 1, 100);
		
		
		String[] movieLibrary = new String[numMovies];
		
		//Step 3: make method to fill the array
		fillArray(userInput, movieLibrary, "Enter Movie Title:");
		
		//Step 4: make a method to sort the array alphabetically
		sortArray(movieLibrary);
		
		System.out.println(); //space in console before letting user search

		//Step 5: Ask the user what they want to search for
		do {
			System.out.println("Enter a movie to search or Q to stop:");
			if(userInput.hasNextLine()) {
				searchVal = userInput.nextLine(); //do this so doesn't take empty string as first val
				
				if(searchVal.equals("Q") || searchVal.equals("q")) { //if user enters q or Q(sentinel), then stop loop
					reachedSentinel = true;
				} else { //did not reach sentinel, so need to search through array
					searchValPosition = searchArray(movieLibrary, searchVal);
					
					if(searchValPosition > -1) { //if position == -1, then the movie was not found
						System.out.printf("The %s movie is in the library at position %d.\n", searchVal, searchValPosition);
					} else {
						System.out.printf("%s is not a movie in the library.\n", searchVal);
					}
				}
				
			}
		} while(!reachedSentinel);
		
		System.out.println(); //space in console before printing the library
		
		//Step 6: print library
		System.out.println("Movie Library: ");
		for(int i = 0; i < movieLibrary.length; i++) {
			System.out.println(movieLibrary[i]);
		}
		
		//Step 7: close userInput so no resource leak
		userInput.close();
	}

}
