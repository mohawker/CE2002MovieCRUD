package Controller;

import java.util.ArrayList;
import java.util.Set;

import Model.Movie;
import Model.Cineplex;
import Model.User;

public class Control {
	Set<Movie> uniqueMovies;
	User user;
	ArrayList<Cineplex> cineplexes;
	
	// movieHelper, cineplexHelper etc
	public Control(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		this.uniqueMovies = uniqueMovies;
		this.user = user;
		this.cineplexes = cineplexes;
	}
}
