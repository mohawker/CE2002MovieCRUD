package Controller;

import java.util.ArrayList;
import java.util.Set;

import Entity.Cineplex;
import Entity.Movie;
import Entity.User;

public class Control {
	private Set<Movie> uniqueMovies;
	private User user;
	private ArrayList<Cineplex> cineplexes;
	
	// movieHelper, cineplexHelper etc
	public Control(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		this.setUniqueMovies(uniqueMovies);
		this.setUser(user);
		this.setCineplexes(cineplexes);
	}

	public Set<Movie> getUniqueMovies() {return uniqueMovies;}

	public void setUniqueMovies(Set<Movie> uniqueMovies) {this.uniqueMovies = uniqueMovies;}

	public User getUser() {return user;}

	public void setUser(User user) {this.user = user;}

	public ArrayList<Cineplex> getCineplexes() {return cineplexes;}

	public void setCineplexes(ArrayList<Cineplex> cineplexes) {this.cineplexes = cineplexes;}
}
