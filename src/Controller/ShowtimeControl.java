package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import Model.Movie;
import Model.User;
import Model.Cineplex;

public class ShowtimeControl extends Control{

	public ShowtimeControl(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	public ArrayList<String> createShowtimes(){
		Scanner scan = new Scanner(System.in);
		System.out.print("Number of Showtimes: ");
		int numShows = InputControl.integerInput(1,999);
		ArrayList <String> showtimes = new ArrayList <String>();
		for (int i=0; i<numShows; i++) {
			System.out.print("Showtime " + (i+1) + ": ");
			showtimes.add(scan.nextLine());
		}
		return showtimes;
	}
}
