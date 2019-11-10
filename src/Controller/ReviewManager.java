package Controller;

import java.util.ArrayList;
import java.util.Set;

import Entity.Cineplex;
import Entity.Movie;
import Entity.Review;
import Entity.User;

/**
 * Helper functions for users to add movie reviews
 * @author vince
 *
 */
public class ReviewManager extends Control{
	private MovieControl movieControl;
	
	/**
	 * Uses Control class constructor and instantiates a new MovieControl object
	 * @param uniqueMovies
	 * @param user
	 * @param cineplexes
	 */
	public ReviewManager(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
		this.movieControl = new MovieControl(uniqueMovies, user, cineplexes);
	}
	
	/**
	 * Add review for a particular movie
	 * @param uniqueMovies
	 * @param user
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
