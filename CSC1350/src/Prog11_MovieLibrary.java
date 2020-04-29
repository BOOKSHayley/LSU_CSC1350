import java.util.Scanner;

/**
 * This program makes a movie library that the user defines. 
 * 
 * CSC 1350 Programming Project 11
 * Section 2
 * 
 * @author hrobe39
 * @since 2 December 2019
 *
 */
public class Prog11_MovieLibrary {

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
				System.out.printf("Error: %s is not a valid input\n", badInput);
			}
			
		} while(!valid);
		
		return value;
	}
	
	/**
	 * validRating makes sure the argument is an acceptable rating
	 * Developed: 2 December 2019
	 * @author hrobe39
	 * 
	 * @param rate, String value
	 * @return boolean whether or not the rating is G, PG, PG-13, R, or Not Rated
	 */
	public static boolean validRating(String rate) {
		String[] acceptableRatings = {"G", "PG", "PG-13", "R", "Not Rated"};
		boolean accepted = false;
		
		for(int i = 0; i < acceptableRatings.length; i++) {
			if(rate.equals(acceptableRatings[i])) {
				accepted = true;
			}
		}
		
		return accepted;
	}
	
	/**
	 * getRating asks user for the rating and makes sure the rating is valid
	 * Developed: 2 December 2019
	 * @author hrobe39
	 * 
	 * @param userInput, Scanner object
	 * @param output, String to prompt user for information
	 * @return String valid rating
	 */
	public static String getRating(Scanner userInput, String output) {
		boolean valid = false;
		String rating = "";
		boolean firstTime = true;
		
		do {
			System.out.println(output);
			
			if(firstTime) {
				userInput.nextLine();
			}
			
			firstTime = false;
			if(userInput.hasNextLine()) {
				rating = userInput.nextLine();
				
				if(validRating(rating)) {
					valid = true;
				} else {
					System.out.printf("%s is not a valid rating.\n", rating);
				}
				
			}
			
			
		} while(!valid);
		
		return rating;
	}
	
	/**
	 * getString takes the nextLine from the user
	 * Developed: 2 December 2019
	 * @author hrobe39
	 * 
	 * @param output, String prompts user for information
	 * @return String from user
	 */
	public static String getString(String output) {
		Scanner userInput = new Scanner(System.in);
		String value = "";
		
		System.out.println(output);
		
		if(userInput.hasNextLine()) {
			value = userInput.nextLine();
		}
		
		return value;
	}
	
	/**
	 * sortArray sorts an array in alphabetical order
	 * Developed: 25 November 2019
	 * Modified: 2 December 2019
	 * @author hrobe39
	 * 
	 * @param arr, String array
	 */
	public static void sortArray(Movie[] arr) {
		boolean sorted = false;
		int i = 1;
		
		while(!sorted) {
			if(i == arr.length) {
				sorted = true;
			} else {
				sorted = true; //assume sorted is true
				
				for(int j = 0; j < arr.length-i; j++) {
					if(arr[j].movieTitle().compareTo(arr[j+1].movieTitle()) > 0) {
						swapElts(arr, j, j+1);
						sorted = false; //if swapped 2 elements, then it is not completely sorted
					}
				}
				
			}
			
			i++; //make sure to increment i
		}
		
	}
	
	/**
	 * swapElts swaps two elements in an array
	 * Developed: 25 November 2019
	 * Modified: 2 December 2019
	 * @author hrobe39
	 * 
	 * @param arr, String array
	 * @param elt1, one element to switch
	 * @param elt2, second element to switch
	 */
	public static void swapElts(Movie[] arr, int elt1, int elt2){
		Movie temp = arr[elt1];
		arr[elt1] = arr[elt2];
		arr[elt2] = temp;
	}
	
	public static void mainMethod() {
		//Step 2: make Scanner object and variables
		Scanner userInput = new Scanner(System.in);
		
		//Step 3: ask user for number of movies and make array of that many movies
		int numMovies = validInt(userInput, "How many movies are in your personal library?", 1, 100);
		
		Movie[] movieLib = new Movie[numMovies];
		
		//Step 4: fill array with info from user (use methods to help with this)
		for(int i = 0; i < movieLib.length; i++) {
			String title = getString("Enter the movie title:");
			int releaseYear = validInt(userInput, "Enter the year the movie was released:", 1900, 2020);
			String rating = getRating(userInput, "Enter the movie rating (G, PG, PG-13, R, or Not Rated):");
			
			movieLib[i] = new Movie(title, releaseYear, rating);
		}
		
		//Step 5: sort the array alphabetically by movie title
		sortArray(movieLib);
		
		//Step 6: print out the movie library
		System.out.println();
		System.out.println("Movie Library:");
		for(int i = 0; i < movieLib.length; i++) {
			System.out.printf("%-13s %s\n", "Movie Title:", movieLib[i].movieTitle());
			System.out.printf("%-13s %d\n", "Release Year:", movieLib[i].releaseYear());
			System.out.printf("%-13s %s\n", "Rating:", movieLib[i].rating());
			System.out.println();
		}
		
		//Step 7: Close Scanner object so no resource leak
		userInput.close();
		
	}
	
	
	

}
