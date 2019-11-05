package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import Model.Movie;
import Model.User;
import Model.Cineplex;

public class CineplexControl extends Control{

	public CineplexControl(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	public void printCineplexes(ArrayList<Cineplex> cineplexes) {
		System.out.println("=== Cineplexes ===");
		for (int i = 0; i<cineplexes.size(); i++) {
			System.out.println("[" + (i+1) + "] " + cineplexes.get(i).getName() + " " + cineplexes.get(i).getLocation());
		}
	}

	public Cineplex selectCineplex(ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Select Cineplex: ");
		int choice = InputControl.integerInput(1, cineplexes.size());
		return cineplexes.get(choice-1);
	}
}
