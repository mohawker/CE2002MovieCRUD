package Assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class CinemaHelper extends Helper{

	public CinemaHelper(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	public Cinema printAndSelectCinema(Cineplex cineplex) {
		Scanner scan = new Scanner(System.in);
		System.out.println("===Cinemas===");
		for (int i = 0; i<cineplex.cinemas.size(); i++) {
			System.out.println("[" + (i+1) + "] " + " Cinema Code " + cineplex.cinemas.get(i).getCinemaCode() + "(" + cineplex.cinemas.get(i).getCinemaType() + ")");
		}
		int choice = InputHandler.integerInput(1, cineplex.cinemas.size());
		System.out.println();
		return cineplex.cinemas.get(choice-1);
	}
}
