package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import Entity.Cineplex;
import Entity.Movie;
import Entity.User;

/**
 * Provides helper functions relating to sorting of movies
 * @author vince
 *
 */
public class SortingManager extends Control{
	private MovieControl movieControl;
	
	/**
	 * Uses Control class constructor and instantiates new MovieControl object
	 * @param uniqueMovies
	 * @param user
	 * @param cineplexes
	 */
	public SortingManager(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
		this.movieControl = new MovieControl(uniqueMovies, user, cineplexes);
	}
	
	/**
	 * Prompts user to list top 5 movies by sales/review ratings
	 * @param uniqueMovies
	 */
	public void listTop5(Set<Movie> uniqueMovies) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Would you like to get the top 5 by:");
		System.out.println("1. Sales");
		System.out.println("2. Overall Rating");
		int choice = scan.nextInt();
		if (choice == 1) {
			movieControl.sortMovies(uniqueMovies, true);
		}else {
			movieControl.sortMovies(uniqueMovies, false);
		}
	}
}
