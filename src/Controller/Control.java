package Controller;

import java.util.ArrayList;
import java.util.Set;

import Entity.Cineplex;
import Entity.Movie;
import Entity.User;

/**
 * Used to create other control classes for helper functions
 */
public class Control {
	private Set<Movie> uniqueMovies;
	private User user;
	private ArrayList<Cineplex> cineplexes;
	
	/**
	 * Constructor that will be used by derived classes of Control
	 * @param uniqueMovies - Unique movies shown across all the cineplexes
	 * @param user - User of MOBLIMA app
	 * @param cineplexes - ArrayList of the 3 cineplexes
	 */
	public Control(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		this.setUniqueMovies(uniqueMovies);
		this.setUser(user);
		this.setCineplexes(cineplexes);
	}

	
	// setters
	public void setUniqueMovies(Set<Movie> uniqueMovies) {this.uniqueMovies = uniqueMovies;}
	public void setUser(User user) {this.user = user;}
	public void setCineplexes(ArrayList<Cineplex> cineplexes) {this.cineplexes = cineplexes;}
	
	// getters
	public Set<Movie> getUniqueMovies() {return uniqueMovies;}
	public User getUser() {return user;}
	public ArrayList<Cineplex> getCineplexes() {return cineplexes;}
}
