package Assignment;

public class Review {
	public String comment;
	public int rating;
	public String userID;
	public String movieID;
	public String title;
	
	public Review(String comment, int rating, String userID, String movieID, String title) {
		this.comment = comment;
		this.rating = rating;
		this.userID = userID;
		this.movieID = movieID;
	}
	
	//getMethods
	public String getComment() {return this.comment;}
	public String getRating() {return this.getRating();}
	public String getUserID() {return userID;}
	public String getMovieID() {return movieID;}
	public String getTitle() {return title;}
}