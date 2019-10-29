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
		System.out.println("Select the cinema:");
		for (int i = 0; i<cineplex.cinemas.size(); i++) {
			System.out.println((i+1) + ". Cinema Code " + cineplex.cinemas.get(i).cinema_code + "(" + cineplex.cinemas.get(i).cinema_type + ")");
		}
		while (!scan.hasNextInt()) {
			System.out.println("Error... Please input an Integer");
			scan.nextLine();	
		}
		int choice = scan.nextInt();
		System.out.println();
		return cineplex.cinemas.get(choice-1);
	}
}
