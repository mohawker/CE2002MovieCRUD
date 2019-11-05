package Entity;

import java.util.Scanner;

import Controller.InputControl;

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
			float rating = InputControl.floatInput(1, 5);
			this.comment = comment;
			this.rating = rating;
			this.userID = user.getUsername();
			this.movie = movie;
			break;
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
	public float getRating() {return this.rating;}
	public String getUserID() {return userID;}
	public String getMovieTitle() {return this.movie.getTitle();}
	public Movie getMovieR() {return this.movie;}
	public String getTitle() {return this.title;}
	public float getRRating() {return this.rating;}
}