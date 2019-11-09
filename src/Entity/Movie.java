package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Movie that will be shown in the cinema
 * @author vince
 *
 */
public class Movie implements Serializable{
	private String title;
	private String status;
	private String synopsis;
	private String director;
	private String type;
	private String ageRating;
	private float movieSales=0;
	//private float averageRating = 0;
	private ArrayList <String> cast;
	private ArrayList <Review> movieReviews=new ArrayList <Review>(); // When creating a new movie, there are no reviews
	
	
	/**
	 * 
	 * @param title Title of movie
	 * @param status Status of movie (Coming Soon/Preview/Showing/End of Showing)
	 * @param synopsis Synopsis of movie
	 * @param director Director of move
	 * @param type Type of movie (3D/Normal/Blockbuster)
	 * @param cast Cast of movie
	 * @param ageRating Rating of movie (G/PG13/NC16/M18/R21)
	 */
	public Movie(String title, String status, String synopsis, String director, String type, ArrayList <String> cast, String ageRating) {
		this.title = title;
		this.status = status;
		this.synopsis = synopsis;
		this.director = director;
		this.type = type;
		this.cast = cast;
		this.ageRating = ageRating;
	}
	
	/**
	 * Prints out ArrayList of Review objects for the movie
	 */
	public void printReviews() {
		if (this.movieReviews.size() == 0) {
			System.out.println("There are no reviews for " + this.title + " yet\n");
		}else {
			System.out.println("The reviews for " + this.title + " are:");
			System.out.println();
			for (int i=0; i<this.movieReviews.size(); i++) {
				Review review_printed = this.movieReviews.get(i);
				System.out.println("Rating of " + review_printed.getRating() + " out of 5");
				System.out.println("Comment: " + review_printed.getComment());
				System.out.println();
			}
		}
	}
	
	/**
	 * Prints out details about movie
	 */
	public void printMovie() {
		System.out.println("=== Movie Details of " + this.title + " ===");
		System.out.println("Title: " + this.title);
		System.out.println("Director: " + this.director);
		System.out.println("Rating: " + this.ageRating);
		if (this.movieReviews.size() == 0){
			System.out.println("There are no ratings available");
		}else {
			System.out.println("The overall rating is " + this.getAverageRating());
		}
		
		System.out.println("Showing Status: " + this.status);
		
		System.out.print("Cast: ");
		for (int i=0; i<this.cast.size(); i++) {
			System.out.print(this.cast.get(i) + " | ");
		}
		System.out.println();
		
		System.out.println("Type: " + this.type);
		System.out.println("Synopsis: " + this.synopsis);
		
		printReviews();
	}
	
	/**
	 * Returns the average rating of the movie as a float
	 * @return
	 */
	public float getAverageRating() {
		float totalRating = 0;
		if (this.movieReviews.size()==0) {
			return (float)0;
		}else {
			for (int i=0; i<this.movieReviews.size(); i++) {
				totalRating += this.movieReviews.get(i).getRating();
			}
			return totalRating/(this.movieReviews.size());
		}
	}
	
	// Set Methods
	public void setTitle(String title) {this.title = title;}
	public void setStatus(String status) {this.status = status;}
	public void setSynopsis(String synopsis) {this.synopsis = synopsis;}
	public void setDirector(String director) {this.director = director;}
	public void setType(String type) {this.type = type;}
	public void setCast(ArrayList <String> cast) {this.cast = cast;}
	public void setMovieSales(float movieSales) {this.movieSales = movieSales;}
	//public void setAverageRating(float averageRating) {this.averageRating = averageRating;}
	public void setReview(ArrayList<Review> movieReviews) {this.movieReviews = movieReviews;}
	
	//getMethods
	public String getTitle() {return title;}
	public String getStatus() {return status;}
	public String getSynopsis() {return synopsis;}
	public String getDirector() {return director;}
	public String getType() {return type;}
	public float getMovieSales() {return movieSales;}
	public ArrayList <String> getCast(){return this.cast;}
	public ArrayList<Review> getMovieReviews(){return this.movieReviews;}
}