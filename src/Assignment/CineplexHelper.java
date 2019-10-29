package Assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class CineplexHelper extends Helper{

	public CineplexHelper(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	public void printCineplexes(ArrayList<Cineplex> cineplexes) {
		System.out.println("Select the cineplex:");
		for (int i = 0; i<cineplexes.size(); i++) {
			System.out.println((i+1) + ". " + cineplexes.get(i).name + " " + cineplexes.get(i).location);
		}
	}

	public Cineplex selectCineplex(ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Select Cineplex: ");
		int choice = -1;
		while (!scan.hasNextInt()) {
			System.out.println("Error... Please input an Integer");
			scan.nextLine();	
		}
		while (choice <1 || choice >3) {
			choice = scan.nextInt();
			if (choice <1 || choice >3 )
				System.out.println("Please enter a valid selection from 1-3");	
			else
				System.out.println();
		}
		return cineplexes.get(choice-1);
	}
}
