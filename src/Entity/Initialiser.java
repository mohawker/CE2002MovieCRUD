package Entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Instantiates new data instead of loading previous state
 * @author vince
 *
 */
public class Initialiser {
	private static String DATE_FORMAT = "dd/MM/YYYY";
	private static DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	
	/**
	 * Generates an array of Cinema objects
	 * @return
	 */
	public static Cinema[] generateCinemas() {
		// current date and next 5 dates
		ArrayList<String> dates = new ArrayList<String>();
		Date currentDate = new Date();
		dates.add(dateFormat.format(currentDate));
		for (int i=1; i<=5; i++) {
			Date newDate = new Date(currentDate.getTime() + i*(1000 * 60 * 60 * 24));
			dates.add(dateFormat.format(newDate));
		}
		
		Cinema[] cinemas = new Cinema[15];
		ArrayList<String> showtimes_1 = new ArrayList<String>();
		showtimes_1.add("0130");showtimes_1.add("0300");showtimes_1.add("1000");showtimes_1.add("1500");showtimes_1.add("1800");
		cinemas[0] = Cinema.generateCinema("Imax", "101", showtimes_1, dates);
		
		
		ArrayList<String> showtimes_2 = new ArrayList<String>();
		showtimes_2.add("0000");showtimes_2.add("0800");showtimes_2.add("1100");showtimes_2.add("1400");showtimes_2.add("1800");
		cinemas[1] = Cinema.generateCinema("Normal", "102", showtimes_2, dates);
		
		ArrayList<String> showtimes_3 = new ArrayList<String>();
		showtimes_3.add("1200");showtimes_3.add("1400");showtimes_3.add("1600");
		cinemas[2] = Cinema.generateCinema("Imax", "103", showtimes_3, dates);
		
		ArrayList<String> showtimes_4 = new ArrayList<String>();
		showtimes_4.add("1000");showtimes_4.add("1400");showtimes_4.add("1900");showtimes_4.add("2000");showtimes_4.add("2130");
		cinemas[3] = Cinema.generateCinema("Normal", "104", showtimes_4, dates);
		
		ArrayList<String> showtimes_5 = new ArrayList<String>();
		showtimes_5.add("1100");showtimes_5.add("1300");showtimes_5.add("1600");showtimes_5.add("1900");
		cinemas[4] = Cinema.generateCinema("GoldClass", "105", showtimes_5, dates);
		
		ArrayList<String> showtimes_6 = new ArrayList<String>();
		showtimes_6.add("0130");showtimes_6.add("0300");showtimes_6.add("0500");showtimes_6.add("0900");showtimes_6.add("1100");
		cinemas[5] = Cinema.generateCinema("Normal", "201", showtimes_6, dates);
		
		ArrayList<String> showtimes_7 = new ArrayList<String>();
		showtimes_7.add("0000");showtimes_7.add("0800");showtimes_7.add("1100");showtimes_7.add("1400");
		cinemas[6] = Cinema.generateCinema("Normal", "202", showtimes_7, dates);
		
		ArrayList<String> showtimes_8 = new ArrayList<String>();
		showtimes_8.add("1200");showtimes_8.add("1400");showtimes_8.add("1600");
		cinemas[7] = Cinema.generateCinema("3D", "203", showtimes_8, dates);
		
		ArrayList<String> showtimes_9 = new ArrayList<String>();
		showtimes_9.add("1000");showtimes_9.add("1400");showtimes_9.add("1900");showtimes_9.add("2000");showtimes_9.add("2130");
		cinemas[8] = Cinema.generateCinema("Normal", "204", showtimes_9, dates);
		
		ArrayList<String> showtimes_10 = new ArrayList<String>();
		showtimes_10.add("1100");showtimes_10.add("1100");showtimes_10.add("1600");showtimes_10.add("1800");showtimes_10.add("2200");
		cinemas[9] = Cinema.generateCinema("Normal", "205", showtimes_10, dates);
		
		ArrayList<String> showtimes_11 = new ArrayList<String>();
		showtimes_11.add("0130");showtimes_11.add("0300");showtimes_11.add("0700");showtimes_11.add("0900");showtimes_11.add("1500");
		cinemas[10] = Cinema.generateCinema("GoldClass", "301", showtimes_11, dates);
		
		ArrayList<String> showtimes_12 = new ArrayList<String>();
		showtimes_12.add("0000");showtimes_12.add("0600");showtimes_12.add("1200");showtimes_12.add("1700");
		cinemas[11] = Cinema.generateCinema("Normal", "302", showtimes_12, dates);
		
		ArrayList<String> showtimes_13 = new ArrayList<String>();
		showtimes_13.add("1200");showtimes_13.add("1200");showtimes_13.add("1600");
		cinemas[12] = Cinema.generateCinema("GoldClass", "303", showtimes_13, dates);
		
		ArrayList<String> showtimes_14 = new ArrayList<String>();
		showtimes_14.add("1000");showtimes_14.add("1400");showtimes_14.add("1900");showtimes_14.add("2200");showtimes_14.add("2330");
		cinemas[13] = Cinema.generateCinema("3D", "304", showtimes_14, dates);
		
		ArrayList<String> showtimes_15 = new ArrayList<String>();
		showtimes_15.add("1100");showtimes_15.add("0900");showtimes_15.add("1200");showtimes_15.add("1800");showtimes_15.add("2000");
		cinemas[14] = Cinema.generateCinema("Normal", "305", showtimes_15, dates);
		return cinemas;
	}
	
	/**
	 * Generates an array of Movie objects
	 * @return
	 */
	public static Movie[] generateMovies() {
		Movie[] movies = new Movie[10];
		
		ArrayList<String> cast_1 = new ArrayList<String>();
		cast_1.add("Angelina Jolie");cast_1.add("Elle Fanning");cast_1.add("Michelle Pfeiffer");
		movies[0] = new Movie("Disney's Maleficent: Mistress Of Evil", "Showing", "Maleficent: Mistress of Evil is a fantasy adventure that picks up several years after Maleficent, in which audiences learned of the events that hardened the heart of Disney's most notorious villain and drove her to curse a baby Princess Aurora. The film continues to explore the complex relationship between the horned fairy and the soon to be Queen as they form new alliances and face new adversaries in their struggle to protect the moors and the magical creatures that reside within.", "Joachim Ronning", "Normal", cast_1, "PG13");
		
		ArrayList<String> cast_2 = new ArrayList<String>();
		cast_2.add("Joaquin Phoenix");cast_2.add("Robert De Niro");cast_2.add("Zazie Beetz");
		movies[1] = new Movie("Joker", "Showing", "A failed stand-up comedian is driven insane and becomes a psychopathic murderer.", "Todd Phillips", "Blockbuster", cast_2, "G");
			
		ArrayList<String> cast_3 = new ArrayList<String>();
		cast_3.add("Will Smith");cast_3.add("Mary Elizabeth Winstead");cast_3.add("Clive Owen");
		movies[2] = new Movie("Gemini Man", "Showing", "Gemini Man is an innovative action-thriller starring Will Smith as Henry Brogan, an elite assassin, who is suddenly targeted and pursued by a mysterious young operative that seemingly can predict his every move. The film is directed by Academy Award-winning filmmaker Ang Lee and produced by renown producers Jerry Bruckheimer, David Ellison, Dana Goldberg and Don Granger. Also starring are Mary Elizabeth Winstead, Clive Owen and Benedict Wong.", "Ang Lee", "Normal", cast_3, "M18");
			
		ArrayList<String> cast_4 = new ArrayList<String>();
		cast_4.add("Brad Pitt");cast_4.add("Tommy Lee Jones");cast_4.add("Ruth Negga");
		movies[3] = new Movie("Ad Astra", "Showing", "Astronaut Roy McBride (Brad Pitt) travels to the outer edges of the solar system to find his missing father and unravel a mystery that threatens the survival of our planet. His journey will uncover secrets that challenge the nature of human existence and our place in the cosmos.", "James Gray", "Normal", cast_4, "R21");
			
		ArrayList<String> cast_5 = new ArrayList<String>();
		cast_5.add("Wu Jing");cast_5.add("Zhang Ziyi");cast_5.add("Jing Boran");
		movies[4] = new Movie("The Climbers", "Showing", "The four members of the China Everest Climbing Commando are attacking the most difficult second step of Mount Everest. This is their fifth attempt. The first four failures have cost them too much physical strength but finally, the wind and snow stop and they have an opportunity to climb.", "Daniel Lee", "3D", cast_5, "PG13");
		
		ArrayList<String> cast_6 = new ArrayList<String>();
		cast_6.add("Diego Boneta");cast_6.add("Arnold Schwarzenegger");cast_6.add("Linda Hamilton");
		movies[5] = new Movie("Terminator: Dark Fate", "Showing", "Linda Hamilton (Sarah Connor) and Arnold Schwarzenegger (T-800) return in their iconic roles in Terminator: Dark Fate, directed by Tim Miller (Deadpool) and produced by visionary filmmaker James Cameron and David Ellison. Following the events of Terminator 2: Judgment Day, Terminator: Dark Fate also stars Mackenzie Davis, Natalia Reyes, Gabriel Luna, and Diego Boneta.", "Tim Miller", "Blockbuster", cast_6, "PG13");
		
		ArrayList<String> cast_7 = new ArrayList<String>();
		cast_7.add("Constance Wu");cast_7.add("Jennifer Lopez");cast_7.add("Lili Reinhart");
		movies[6] = new Movie("Hustlers", "Showing", "Hustlers follows a crew of savvy former strip club employees who band together to turn the tables on their Wall Street clients. The film was inspired by the viral article published by New York Magazine entitled �The Hustlers at Scores� written by Jessica Pressler.", "Lorene Scafaria", "Normal", cast_7, "G");
		
		ArrayList<String> cast_8 = new ArrayList<String>();
		cast_8.add("Alexander Skarsgard");cast_8.add("Nat Wolff");cast_8.add("Adam Long");
		movies[7] = new Movie("The Kill Team", "Showing", "When Andrew Briggman (Nat Wolff), a young soldier in the US invasion of Afghanistan, witnesses other recruits killing innocent civilians under the direction of a sadistic leader, Sergeant Deeks (Alexander Skarsgard), he considers reporting them to higher-ups - but the heavily-armed, increasingly violent platoon becomes suspicious that someone in their ranks has turned on them, and Andrew begins to fear that he'll be the next target.", "Dan Krauss", "Blockbuster", cast_8, "PG13");
		
		ArrayList<String> cast_9 = new ArrayList<String>();
		cast_9.add("Bo Huang");cast_9.add("Yi Zhang");cast_9.add("Jing Wu");
		movies[8] = new Movie("My People My Country", "Showing", "An anthology film consists of 7 short stories directed by several different directors, which are based on 7 moments since the foundation of the People's Republic of China.", "Kaige Chen , Yibai Zhang , Hu Guan , Xiaolu Xue , Zheng Xu , Hao Ning , Muye Wen", "Normal", cast_9, "NC16");
		
		ArrayList<String> cast_10 = new ArrayList<String>();
		cast_10.add("Charlize Theron");cast_10.add("Oscar Isaac");cast_10.add("Chloe Grace Moretz");
		movies[9] = new Movie("The Addams Family", "Showing", "Get ready to snap your fingers! The first family of Halloween, the Addams Family, is back on the big screen in the first animated comedy about the kookiest family on the block. Funny, outlandish, and completely iconic, the Addams Family redefines what it means to be a good neighbor.", "Greg Tiernan, Conrad Vernon", "Normal", cast_10, "M18");
		
		return movies;
	}
	
	/**
	 * Generates an array of Cineplex objects
	 * @return
	 */
	public static ArrayList<Cineplex> generateCineplexes(Cinema[] cinemas, Movie[] movies) {
		ArrayList<Cinema> cinemas_1 = new ArrayList<Cinema>();
		cinemas_1.add(cinemas[0]);cinemas_1.add(cinemas[1]);cinemas_1.add(cinemas[2]);cinemas_1.add(cinemas[3]);cinemas_1.add(cinemas[4]);
		ArrayList<Movie> movies_1 = new ArrayList<Movie>();
		movies_1.add(movies[0]);movies_1.add(movies[1]);movies_1.add(movies[2]);movies_1.add(movies[3]);movies_1.add(movies[5]);
		
		ArrayList<Cinema> cinemas_2 = new ArrayList<Cinema>();
		cinemas_2.add(cinemas[5]);cinemas_2.add(cinemas[6]);cinemas_2.add(cinemas[7]);cinemas_2.add(cinemas[8]);cinemas_2.add(cinemas[9]);
		ArrayList<Movie> movies_2 = new ArrayList<Movie>();
		movies_2.add(movies[6]);movies_2.add(movies[7]);movies_2.add(movies[4]);movies_2.add(movies[8]);movies_2.add(movies[9]);
		
		ArrayList<Cinema> cinemas_3 = new ArrayList<Cinema>();
		cinemas_3.add(cinemas[10]);cinemas_3.add(cinemas[11]);cinemas_3.add(cinemas[12]);cinemas_3.add(cinemas[13]);cinemas_3.add(cinemas[14]);
		ArrayList<Movie> movies_3 = new ArrayList<Movie>();
		movies_3.add(movies[5]);movies_3.add(movies[9]);movies_3.add(movies[8]);movies_3.add(movies[4]);movies_3.add(movies[0]);
		
		Cineplex cineplex_1 = new Cineplex("Cathay", "Ang Mo Kio", cinemas_1, movies_1);
		Cineplex cineplex_2 = new Cineplex("Cathay", "Bishan", cinemas_2, movies_2);
		Cineplex cineplex_3 = new Cineplex("Cathay", "Cineplex", cinemas_3, movies_3);
		ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
		cineplexes.add(cineplex_1);cineplexes.add(cineplex_2);cineplexes.add(cineplex_3);
		return cineplexes;
	}
	
	/**
	 * Generates a set of unique movies being shown
	 * @return
	 */
	public static Set<Movie> generateMovies(ArrayList<Cineplex> cineplexes){
		Cineplex cineplex_1 = cineplexes.get(0);
		Cineplex cineplex_2 = cineplexes.get(1);
		Cineplex cineplex_3 = cineplexes.get(2);
		
		// get unique movies
		Set<Movie> uniqueMovies = new HashSet<>();
		uniqueMovies.addAll(cineplex_1.getMovies());
		uniqueMovies.addAll(cineplex_2.getMovies());
		uniqueMovies.addAll(cineplex_3.getMovies());
		return uniqueMovies;
	}
}
