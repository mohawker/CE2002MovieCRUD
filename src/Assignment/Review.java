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
		System.out.print("Comment for " + movie.title + ": ");
		String comment = scan.nextLine();
		while (1==1) {
			System.out.print("Rating for " + movie.title + " out of 5: ");
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
	}
	
	//getMethods
	public String getComment() {return this.comment;}
	public String getRating() {return this.getRating();}
	public String getUserID() {return userID;}
	public String getMovieTitle() {return this.movie.title;}
	public String getTitle() {return title;}
}