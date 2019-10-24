package Assignment;

import java.util.Scanner;

public class Review {
	public String comment;
	public float rating;
	public String userID;
	public String title;
	public Movie movie;
	
	public Review(User user, Movie movie) {
		Scanner scan = new Scanner (System.in);
		System.out.println("What is your comment for " + movie.title + "?");
		String comment = scan.nextLine();
		System.out.println("What is your rating out of 5?");
		float rating = scan.nextFloat();
		this.comment = comment;
		this.rating = rating;
		this.userID = user.getUsername();
		this.movie = movie;
	}
	
	//getMethods
	public String getComment() {return this.comment;}
	public String getRating() {return this.getRating();}
	public String getUserID() {return userID;}
	public String getMovieTitle() {return this.movie.title;}
	public String getTitle() {return title;}
}