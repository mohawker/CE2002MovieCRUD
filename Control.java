package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
