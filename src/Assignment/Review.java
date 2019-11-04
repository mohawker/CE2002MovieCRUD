package Assignment;

import java.util.Scanner;
import java.io.Serializable;

public class Review implements Serializable{
	private String comment;
	private float rating;
	private String userID;
	private String title;
	private Movie movie;
	
	public Review(User user, Movie movie) {
		Scanner scan = new Scanner (System.in);
		System.out.println("\n=== Review for " + movie.getTitle() + " ===");
		System.out.print("Comment: ");
		String comment = scan.nextLine();
		while (1==1) {
			System.out.print("Rating out of 5: ");
			float rating = scan.nextFloat();
			if (rating<0 || rating >5) {
				System.out.println("Only values from 0-5 are valid");
				System.out.println("Please try again");

			}else {
				this.comment = comment;
				this.rating = rating;
				this.userID = user.getUsername();
				this.movie = movie;
				break;
			}
		}
		System.out.println();
	}
	
	//setMethods
	public void setComment(String comment) {this.comment = comment;}
	public void setRating(float rating) {this.rating = rating;}
	public void setUserID(String userID) {this.userID = userID;}
	public void setTitle(String title) {this.title = title;}
	public void setMovie(Movie movie) {this.movie = movie;}
	
	//getMethods
	public String getComment() {return this.comment;}
	public String getRating() {return this.getRating();}
	public String getUserID() {return userID;}
	public String getMovieTitle() {return this.movie.getTitle();}
	public Movie getMovieR() {return this.movie;}
	public String getTitle() {return this.title;}
	public float getRRating() {return this.rating;}
}