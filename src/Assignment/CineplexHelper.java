package Assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class CineplexHelper extends Helper{

	public CineplexHelper(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	public void printCineplexes(ArrayList<Cineplex> cineplexes) {
		System.out.println("===Cineplexes===");
		for (int i = 0; i<cineplexes.size(); i++) {
			System.out.println("[" + (i+1) + "] " + cineplexes.get(i).name + " " + cineplexes.get(i).location);
		}
	}

	public Cineplex selectCineplex(ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Select Cineplex: ");
		int choice = InputHandler.integerInput(1, cineplexes.size());
		return cineplexes.get(choice-1);
	}
}
