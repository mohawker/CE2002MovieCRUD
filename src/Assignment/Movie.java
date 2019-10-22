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
	public ArrayList <String> cast;
	public ArrayList <Review> movieReviews=new ArrayList <Review>(); // When creating a new movie, there are no reviuews
	
	public Movie(String title, String status, String synopsis, String director, String type, ArrayList <String> cast) {
		this.title = title;
		this.status = status;
		this.synopsis = synopsis;
		this.director = director;
		this.type = type;
		this.cast = cast;
	}
	
	public void addReview(Review review) {
		boolean result = this.movieReviews.add(review);
		if (result == true) {
			System.out.println("Movie review for " + this.title + " added successfully");
		}else {
			System.out.println("Movie review for " + this.title + " not added successfully");
		}
	}
	
	public void printReviews() {
		Iterator<Review> i = this.movieReviews.iterator();
		System.out.println("The reviews for " + this.title + " are:");
		System.out.println();
		while (i.hasNext()) {
			Review review_printed = i.next();
			System.out.println("Rating of " + review_printed.rating);
			System.out.println(review_printed.comment);
			System.out.println();
		}
	}
	
	public void printMovie() {
		System.out.println("Title: " + this.title);
		System.out.println("Director: " + this.director);
		System.out.println("Showing Status: " + this.status);
		
		System.out.print("Cast: ");
		for (int i=0; i<this.cast.size(); i++) {
			System.out.print(this.cast.get(i) + " | ");
		}
		System.out.println();
		
		System.out.println("Genres: " + this.type);
		System.out.println("Synopsis: " + this.synopsis);
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
	
	
	
}