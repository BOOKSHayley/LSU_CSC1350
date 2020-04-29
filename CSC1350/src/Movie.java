/**
 * This is a movie class which can be used to hold all the information about a movie
 * 
 * CSC 1350 Programming Project 11
 * Section 2
 * 
 * @author hrobe39
 * @since 2 December 2019
 *
 */
public class Movie {
	//Step 1: make Movie class
	
	//private variables
	private String title;
	private int releaseYear;
	private String rating;
	private String[] acceptableRatings = {"G", "PG", "PG-13", "R", "Not Rated"};
	
	//private helper methods
	
	/**
	 * checkRatings makes sure the argument is an acceptable rating
	 * Developed: 2 December 2019
	 * @author hrobe39
	 * 
	 * @param rate, String value
	 * @return boolean whether or not the rating is G, PG, PG-13, R, or Not Rated
	 */
	private boolean checkRatings(String rate) {
		boolean accepted = false;
		
		for(int i = 0; i < acceptableRatings.length; i++) {
			if(rate.equals(acceptableRatings[i])) {
				accepted = true;
			}
		}
		
		return accepted;
	}
	
	//Constructor
	/**
	 * Constructor
	 * Developed: 2 December 2019
	 * @author hrobe39
	 * 
	 * @param initTitle, String title of movie
	 * @param initRelYear, int release year of movie
	 * @param initRating, String movie rating (G, PG, PG-13, R, or Not Rated)
	 */
	public Movie(String initTitle, int initRelYear, String initRating) {
		title = initTitle;
		releaseYear = initRelYear;
		rating = initRating;
	}
	
	//Public Interface:
	
	/**
	 * setMovieTitle sets the movie title of the object
	 * Developed:2 December 2019
	 * @author hrobe39
	 * 
	 * @param movieTitle, String title of movie
	 */
	public void setMovieTitle(String movieTitle) {
		title = movieTitle;
	} 

	/**
	 * setReleaseYear sets the release year of the object
	 * Developed: 2 December 2019
	 * @author hrobe39
	 * 
	 * @param newRelYear, int release year of movie
	 */
	public void setReleaseYear(int newRelYear) {
		if(newRelYear > 0) {
			releaseYear = newRelYear;
		}
	}
	
	/**
	 * setRating sets the rating of the object
	 * Developed: 2 December 2019
	 * @author hrobe39
	 * 
	 * @param newRating, String rating (G, PG, PG-13, R, or Not Rated)
	 */
	public void setRating(String newRating) {
		if(checkRatings(newRating)) {
			rating = newRating;
		}
	}
	
	/**
	 * movieTitle is the accessor for the movie title
	 * Developed: 2 December 2019
	 * @author hrobe39
	 * 
	 * @return String title of movie
	 */
	public String movieTitle() {
		return title;
	}	
	
	/**
	 * releaseYear is the accessor for the release year
	 * Developed: 2 December 2019
	 * @author hrobe39
	 * 
	 * @return int release year of movie
	 */
	public int releaseYear() {
		return releaseYear;
	}
	
	/**
	 * rating is the accessor for the rating
	 * Developed: 2 December 2019
	 * @author hrobe39
	 * 
	 * @return String rating of movie
	 */
	public String rating() {
		return rating;
	}
}
