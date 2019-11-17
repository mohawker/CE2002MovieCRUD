package controller;

import java.util.ArrayList;
import java.util.Set;

import entity.Cineplex;
import entity.Movie;
import entity.Review;
import entity.User;

/**
 * Helper functions for users to add movie reviews
 */
public class ReviewManager extends Control{
	private MovieControl movieControl;
	
	/**
	 * Uses Control class constructor and instantiates a new MovieControl object
	 * @param uniqueMovies - Unique movies shown across all the cineplexes
	 * @param user - User of MOBLIMA app
	 * @param cineplexes - ArrayList of the 3 cineplexes
	 */
	public ReviewManager(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
		this.movieControl = new MovieControl(uniqueMovies, user, cineplexes);
	}
	
	/**
	 * Add review for a particular movie
	 * @param uniqueMovies - Unique movies shown across all the cineplexes
	 * @param user - User of MOBLIMA app
	 */
	public void addReview(Set<Movie> uniqueMovies, User user) {
		System.out.println("=== Review Movies ===");
		movieControl.printMovies(uniqueMovies);
		Movie movieChosen = movieControl.selectMovie(uniqueMovies);
		Review review = new Review(user, movieChosen);
		boolean result = movieChosen.getMovieReviews().add(review);
		if (result == true) {
			System.out.println("Movie review for " + movieChosen.getTitle() + " added successfully");
		}else {
			System.out.println("Movie review for " + movieChosen.getTitle() + " not added successfully");
		}
	}
}
