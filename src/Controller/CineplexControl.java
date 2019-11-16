package Controller;

import java.util.ArrayList;
import java.util.Set;

import Entity.Cineplex;
import Entity.Movie;
import Entity.User;

/**
 * Provides helper functions relating to cineplexes
 */
public class CineplexControl extends Control{
	
	/**
	 * Constructed using Control superclass constructor
	 * @param uniqueMovies - Unique movies shown across all the cineplexes
	 * @param user - User of MOBLIMA app
	 * @param cineplexes - ArrayList of the 3 cineplexes
	 */
	public CineplexControl(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	/**
	 * Prints cineplexes to console
	 * @param cineplexes - ArrayList of the 3 cineplexes
	 */
	public void printCineplexes(ArrayList<Cineplex> cineplexes) {
		System.out.println("=== Cineplexes ===");
		for (int i = 0; i<cineplexes.size(); i++) {
			System.out.println("[" + (i+1) + "] " + cineplexes.get(i).getName() + " " + cineplexes.get(i).getLocation());
		}
	}
	
	/**
	 * Prompts users to select a cineplex
	 * @param cineplexes - ArrayList of the 3 cineplexes
	 * @return Cineplex chosen
	 */
	public Cineplex selectCineplex(ArrayList<Cineplex> cineplexes) {
		System.out.print("Select Cineplex: ");
		int choice = InputControl.integerInput(1, cineplexes.size());
		return cineplexes.get(choice-1);
	}
}
