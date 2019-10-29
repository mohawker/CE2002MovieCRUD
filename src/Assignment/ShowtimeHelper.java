package Assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class ShowtimeHelper extends Helper{

	public ShowtimeHelper(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	public ArrayList<String> createShowtimes(){
		Scanner scan = new Scanner(System.in);
		System.out.println("What is the number of showtimes for the movie");
		int numShows = scan.nextInt();
		scan.nextLine(); // remove the space left
		ArrayList <String> showtimes = new ArrayList <String>();
		for (int i=0; i<numShows; i++) {
			System.out.println("What is showtime " + (i+1) + "?");
			showtimes.add(scan.nextLine());
		}
		return showtimes;
	}
}
