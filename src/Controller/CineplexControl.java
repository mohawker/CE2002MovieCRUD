package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import Entity.Cineplex;
import Entity.Movie;
import Entity.User;

/**
 * Provides helper functions relating to cineplexes
 * @author vince
 *
 */
public class CineplexControl extends Control{
	
	/**
	 * Constructed using Control superclass constructor
	 * @param uniqueMovies
	 * @param user
	 * @param cineplexes
	 */
	public CineplexControl(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	/**
	 * Prints cineplexes to console
	 * @param cineplexes
	 */
	public void printCineplexes(ArrayList<Cineplex> cineplexes) {
		System.out.println("=== Cineplexes ===");
		for (int i = 0; i<cineplexes.size(); i++) {
			System.out.println("[" + (i+1) + "] " + cineplexes.get(i).getName() + " " + cineplexes.get(i).getLocation());
		}
	}
	
	/**
	 * Prompts users to select a cineplex
	 * @param cineplexes
	 * @return
	 */
	public Cineplex selectCineplex(ArrayList<Cineplex> cineplexes) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Select Cineplex: ");
		int choice = InputControl.integerInput(1, cineplexes.size());
		return cineplexes.get(choice-1);
	}
}
