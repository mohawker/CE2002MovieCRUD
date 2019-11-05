package Controller;

import java.util.ArrayList;
import java.util.Set;

import Entity.Cineplex;
import Entity.Movie;
import Entity.Review;
import Entity.User;

public class ReviewManager extends Control{
	MovieControl movieControl;
	
	public ReviewManager(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
		this.movieControl = new MovieControl(uniqueMovies, user, cineplexes);
	}
	
	// single responsibility principle: Review Manager just adds Reviews
	public void addRating(Set<Movie> uniqueMovies, User user) {
		System.out.println("=== Review Movies ===");
		movieControl.printMovies(uniqueMovies);
		Movie movieChosen = movieControl.selectMovie(uniqueMovies);
		Review review = new Review(user, movieChosen);
		this.addReview(movieChosen, review);
	}
	
	public void addReview(Movie movieChosen, Review review) {
		boolean result = movieChosen.getMovieReviews().add(review);
		if (result == true) {
			System.out.println("Movie review for " + movieChosen.getTitle() + " added successfully");
		}else {
			System.out.println("Movie review for " + movieChosen.getTitle() + " not added successfully");
		}
	}
}
