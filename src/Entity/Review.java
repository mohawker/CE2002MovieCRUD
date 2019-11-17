package entity;

import java.util.Scanner;

import controller.InputControl;

import java.io.Serializable;

/**
 * Review of movies
 */
public class Review implements Serializable{
	private String comment;
	private float rating;
	private String userID;
	private String title;
	private Movie movie;
	
	/**
	 * Reviews only need to be instantiated when users add reviews. Hence, attributes of reviews are requested for in the constructor
	 * @param user - User generating the review
	 * @param movie - Movie being reviewed
	 */
	public Review(User user, Movie movie) {
		Scanner scan = new Scanner (System.in);
		System.out.println("\n=== Review for " + movie.getTitle() + " ===");
		System.out.print("Comment: ");
		String comment = scan.nextLine();
		while (true) {
			System.out.print("Rating out of 5: ");
			float rating = InputControl.floatInput(1, 5);
			this.comment = comment;
			this.rating = rating;
			this.userID = user.getUsername();
			this.movie = movie;
			break;
		}
		System.out.println();
	}

	//getMethods
	public String getComment() {return this.comment;}
	public float getRating() {return this.rating;}
	public String getUserID() {return userID;}
	public String getMovieTitle() {return this.movie.getTitle();}
	public Movie getMovieR() {return this.movie;}
	public String getTitle() {return this.title;}
}