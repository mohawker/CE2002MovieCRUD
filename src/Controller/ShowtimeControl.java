package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import Entity.Cineplex;
import Entity.Movie;
import Entity.User;

/**
 * Provides helper functions relating to movie showtimes
 * @author vince
 *
 */
public class ShowtimeControl extends Control{
	
	/**
	 * Uses the constructor from Control class
	 * @param uniqueMovies
	 * @param user
	 * @param cineplexes
	 */
	public ShowtimeControl(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	/**
	 * Creates an ArrayList of String objects that are movie showtimes in 24H format
	 * @return
	 */
	public ArrayList<String> createShowtimes(){
		Scanner scan = new Scanner(System.in);
		System.out.print("Number of Showtimes: ");
		int numShows = InputControl.integerInput(1,999);
		ArrayList <String> showtimes = new ArrayList <String>();
		for (int i=0; i<numShows; i++) {
			System.out.print("Showtime " + (i+1) + " in 24h format (hhhh): ");
			showtimes.add(scan.nextLine());
		}
		return showtimes;
	}
}
