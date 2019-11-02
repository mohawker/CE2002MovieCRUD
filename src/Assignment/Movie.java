package Assignment;

import java.util.ArrayList;
import java.util.Iterator;

public class Movie {
	public String title;
	public String status;
	public String synopsis;
	public String director;
	public String type;
	public int movieSales=0;
	public float averageRating = 0;
	public ArrayList <String> cast;
	public ArrayList <Review> movieReviews=new ArrayList <Review>(); // When creating a new movie, there are no reviews
	
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
				System.out.println("Rating of " + review_printed.rating + " out of 5");
				System.out.println("Comment: " + review_printed.comment);
				System.out.println();
			}
		}
	}
	
	public void printMovie() {
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
	
	//getMethods
	public String getTitle() {return title;}
	public String getStatus() {return status;}
	public String getSynopsis() {return synopsis;}
	public String getDirector() {return director;}
	public String getType() {return type;}
	
	public int getMovieSales() {return movieSales;}
	public float getAverageRating() {
		float totalRating = 0;
		if (this.movieReviews.size()==0) {
			return (float)0;
		}else {
			for (int i=0; i<this.movieReviews.size(); i++) {
				totalRating += this.movieReviews.get(i).rating;
			}
			return totalRating/(this.movieReviews.size());
		}
	}
}