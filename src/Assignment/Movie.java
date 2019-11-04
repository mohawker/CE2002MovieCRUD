package Assignment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Movie implements Serializable{
	private String title;
	private String status;
	private String synopsis;
	private String director;
	private String type;
	private float movieSales=0;
	//private float averageRating = 0;
	private ArrayList <String> cast;
	private ArrayList <Review> movieReviews=new ArrayList <Review>(); // When creating a new movie, there are no reviews
	
	public Movie(String title, String status, String synopsis, String director, String type, ArrayList <String> cast) {
		this.title = title;
		this.status = status;
		this.synopsis = synopsis;
		this.director = director;
		this.type = type;
		this.cast = cast;
	}
	
	
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
	
	public void printMovie() {
		System.out.println("=== Movie Details of " + this.title + " ===");
		System.out.println("Title: " + this.title);
		System.out.println("Director: " + this.director);
		
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
	
	
	public float getAverageRating() {
		float totalRating = 0;
		if (this.movieReviews.size()==0) {
			return (float)0;
		}else {
			for (int i=0; i<this.movieReviews.size(); i++) {
				totalRating += this.movieReviews.get(i).getRRating();
			}
			return totalRating/(this.movieReviews.size());
		}
	}
}