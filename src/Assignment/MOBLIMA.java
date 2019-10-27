package Assignment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   

public class MOBLIMA {

	public static void main(String[] args) {
		
		// need 15 cinemas
		Cinema[] cinemas = new Cinema[15];
		cinemas = generateCinemas();
		
		// creating movies
		Movie[] movies = new Movie[10];
		movies = generateMovies();
		
		// load cineplex with cinemas and movies
		ArrayList<Cinema> cinemas_1 = new ArrayList<Cinema>();
		cinemas_1.add(cinemas[0]);cinemas_1.add(cinemas[1]);cinemas_1.add(cinemas[2]);cinemas_1.add(cinemas[3]);cinemas_1.add(cinemas[4]);
		ArrayList<Movie> movies_1 = new ArrayList<Movie>();
		movies_1.add(movies[0]);movies_1.add(movies[2]);movies_1.add(movies[4]);movies_1.add(movies[6]);movies_1.add(movies[8]);
		
		ArrayList<Cinema> cinemas_2 = new ArrayList<Cinema>();
		cinemas_2.add(cinemas[5]);cinemas_2.add(cinemas[6]);cinemas_2.add(cinemas[7]);cinemas_2.add(cinemas[8]);cinemas_2.add(cinemas[9]);
		ArrayList<Movie> movies_2 = new ArrayList<Movie>();
		movies_2.add(movies[1]);movies_2.add(movies[3]);movies_2.add(movies[5]);movies_2.add(movies[7]);movies_2.add(movies[9]);
		
		ArrayList<Cinema> cinemas_3 = new ArrayList<Cinema>();
		cinemas_3.add(cinemas[10]);cinemas_3.add(cinemas[11]);cinemas_3.add(cinemas[12]);cinemas_3.add(cinemas[13]);cinemas_3.add(cinemas[14]);
		ArrayList<Movie> movies_3 = new ArrayList<Movie>();
		movies_3.add(movies[2]);movies_3.add(movies[3]);movies_3.add(movies[4]);movies_3.add(movies[5]);movies_3.add(movies[6]);
		
		Cineplex cineplex_1 = new Cineplex("Cathay", "Ang Mo Kio", cinemas_1, movies_1);
		Cineplex cineplex_2 = new Cineplex("Cathay", "Bishan", cinemas_2, movies_2);
		Cineplex cineplex_3 = new Cineplex("Cathay", "Cineplex", cinemas_3, movies_3);
		ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
		cineplexes.add(cineplex_1);cineplexes.add(cineplex_2);cineplexes.add(cineplex_3);
		
		// get unique movies
		Set<Movie> uniqueMovies = new HashSet<>();
		uniqueMovies.addAll(cineplex_1.movies);
		uniqueMovies.addAll(cineplex_2.movies);
		uniqueMovies.addAll(cineplex_3.movies);
		
		System.out.println("Welcome to MOBLIMA App");
		System.out.println("Would you like to continue as a User or an Admin?");
		System.out.println("1. User");
		System.out.println("2. Admin");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		if (choice == 1) {
		// User Login Customization (Name & Age Input)
			System.out.println("What is your name?");
			String userName = scan.next();
			System.out.println("How old are you?");
			int userAge = scan.nextInt();
		// Constructor for User Called
			User user = new User(userName, "vincentyongweijie@gmail.com", "83189252", userAge);
		// UserHelper is created - Enables user functions
			UserHelper userHelper = new UserHelper(uniqueMovies, user, cineplexes);
			System.out.println("------------------------------------------------------");
			System.out.println("Welcome, " + user.getUsername());
			System.out.printf("The date is: %s\n", java.time.LocalDate.now());
			System.out.println("Pricing Today for 2D Tickets: Regular Weekday ($8)");
			if (user.getAge()>=55) {
				System.out.println("Please note that you are eligible for senior citizen discounts!");
			}
			while (1==1) {
				System.out.println("------------------------------------------------------");
				System.out.println("1. List Movie");
				System.out.println("2. View Movie Details");
				System.out.println("3. Check Seat Availability");
				System.out.println("4. Book Ticket");
				System.out.println("5. View Booking History");
				System.out.println("6. List Top 5 Movies by Ticket Sales or Overall Rating");
				System.out.println("7. Add Rating");
				System.out.println("8. Login as Admin");
				System.out.println("-1. Log Off & Shut Down");
				System.out.println("------------------------------------------------------");
				System.out.println("Please select (1-8) : ");
				choice = scan.nextInt();
				switch (choice){
					case 1:
						userHelper.user_1(uniqueMovies, cineplexes);
						break;
					case 2:
						userHelper.user_2(user, uniqueMovies);
						break;
					case 3:
						userHelper.user_3(user, cineplexes);
						break;
					case 4:
						userHelper.user_4(user, cineplexes);
						break;
					case 5:
						userHelper.user_5(user);
						break;
					case 6:
						userHelper.user_6(uniqueMovies);
						break;
					case 7:
						userHelper.user_7(uniqueMovies, user);
						break;
					case 8:
						userHelper.adminView(cineplexes, uniqueMovies);
						break;
					case -1:
						System.out.println("Thank you for using MOBLIMA!");
						System.out.println("System Logging Off...");
						return;	
					default:
						System.out.println("Please enter a valid choice");
				}
				System.out.println();
				System.out.println("What would you like to do next?");
			}
		}else if (choice == 2) {
			Admin admin = new Admin("Admin", "tom@gmail.com", "98765432", 22, "Password123");
			AdminHelper adminHelper = new AdminHelper(uniqueMovies, admin, cineplexes);
			admin.login();
			System.out.println("Welcome, " + admin.getUsername());
			while (1==1) {
				System.out.println("1. Create Movie Listing");
				System.out.println("2. Update Movie Listing (Movie Showing Status)");
				System.out.println("3. Remove Movie Listing");			
				System.out.println("4. Create Cinema Showtimes");
				System.out.println("5. Update Cinema Showtimes");
				System.out.println("6. Remove Cinema Showtimes");
				System.out.println("7. Configure System Settings");
				System.out.println("8. List Top 5 Movies by Ticket Sales or Overall Rating");
				System.out.println("9. Logout to see User view");
				choice = scan.nextInt();
				scan.nextLine();
				switch (choice){
					case 1:
						adminHelper.admin_1(admin, cineplexes, cineplex_1, cineplex_2, cineplex_3, uniqueMovies);
						break;
					case 2:
						adminHelper.admin_2(cineplexes, uniqueMovies);
						break;
					case 3:
						adminHelper.admin_3(cineplexes, uniqueMovies);
						break;
					case 4:
						adminHelper.admin_4(admin, cineplexes, cineplex_1, cineplex_2, cineplex_3, uniqueMovies);
						break;
					case 5:
						adminHelper.admin_5(cineplexes, uniqueMovies, admin);
						break;
					case 6:
						adminHelper.admin_6(cineplexes, uniqueMovies, admin);
						break;
					case 7:
						adminHelper.admin_7();
						break;
					case 8:
						adminHelper.admin_8(uniqueMovies);
						break;
					case 9:
						adminHelper.userView(cineplexes, uniqueMovies);
						break;
				}
				System.out.println();
				System.out.println("What would you like to do next?");
			}
		}
	}
	
	public static void userView(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
	}
	
	public static void adminView(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
	}
	
	public static Cinema[] generateCinemas() {
		Cinema[] cinemas = new Cinema[15];
		ArrayList<String> showtimes_1 = new ArrayList<String>();
		showtimes_1.add("0130");showtimes_1.add("0300");showtimes_1.add("1000");showtimes_1.add("1500");showtimes_1.add("1800");
		cinemas[0] = new Cinema("3D", "101", showtimes_1);
		
		ArrayList<String> showtimes_2 = new ArrayList<String>();
		showtimes_2.add("0000");showtimes_2.add("0800");showtimes_2.add("1100");showtimes_2.add("1400");showtimes_2.add("1800");
		cinemas[1] = new Cinema("Normal", "102", showtimes_2);
		
		ArrayList<String> showtimes_3 = new ArrayList<String>();
		showtimes_3.add("1200");showtimes_3.add("1400");showtimes_3.add("1600");
		cinemas[2] = new Cinema("GoldClass", "103", showtimes_3);
		
		ArrayList<String> showtimes_4 = new ArrayList<String>();
		showtimes_4.add("1000");showtimes_4.add("1400");showtimes_4.add("1900");showtimes_4.add("2000");showtimes_4.add("2130");
		cinemas[3] = new Cinema("3D", "104", showtimes_4);
		
		ArrayList<String> showtimes_5 = new ArrayList<String>();
		showtimes_5.add("1100");showtimes_5.add("1300");showtimes_5.add("1600");showtimes_5.add("1900");
		cinemas[4] = new Cinema("3D", "105", showtimes_5);
		
		ArrayList<String> showtimes_6 = new ArrayList<String>();
		showtimes_6.add("0130");showtimes_6.add("0300");showtimes_6.add("0500");showtimes_6.add("0900");showtimes_6.add("1100");
		cinemas[5] = new Cinema("3D", "201", showtimes_6);
		
		ArrayList<String> showtimes_7 = new ArrayList<String>();
		showtimes_7.add("0000");showtimes_7.add("0800");showtimes_7.add("1100");showtimes_7.add("1400");
		cinemas[6] = new Cinema("Normal", "202", showtimes_7);
		
		ArrayList<String> showtimes_8 = new ArrayList<String>();
		showtimes_8.add("1200");showtimes_8.add("1400");showtimes_8.add("1600");
		cinemas[7] = new Cinema("GoldClass", "203", showtimes_8);
		
		ArrayList<String> showtimes_9 = new ArrayList<String>();
		showtimes_9.add("1000");showtimes_9.add("1400");showtimes_9.add("1900");showtimes_9.add("2000");showtimes_9.add("2130");
		cinemas[8] = new Cinema("3D", "204", showtimes_9);
		
		ArrayList<String> showtimes_10 = new ArrayList<String>();
		showtimes_10.add("1100");showtimes_10.add("1100");showtimes_10.add("1600");showtimes_10.add("1800");showtimes_10.add("2200");
		cinemas[9] = new Cinema("GoldClass", "205", showtimes_10);
		
		ArrayList<String> showtimes_11 = new ArrayList<String>();
		showtimes_11.add("0130");showtimes_11.add("0300");showtimes_11.add("0700");showtimes_11.add("0900");showtimes_11.add("1500");
		cinemas[10] = new Cinema("3D", "301", showtimes_11);
		
		ArrayList<String> showtimes_12 = new ArrayList<String>();
		showtimes_12.add("0000");showtimes_12.add("0600");showtimes_12.add("1200");showtimes_12.add("1700");
		cinemas[11] = new Cinema("Normal", "302", showtimes_12);
		
		ArrayList<String> showtimes_13 = new ArrayList<String>();
		showtimes_13.add("1200");showtimes_13.add("1200");showtimes_13.add("1600");
		cinemas[12] = new Cinema("GoldClass", "303", showtimes_13);
		
		ArrayList<String> showtimes_14 = new ArrayList<String>();
		showtimes_14.add("1000");showtimes_14.add("1400");showtimes_14.add("1900");showtimes_14.add("2200");showtimes_14.add("2330");
		cinemas[13] = new Cinema("3D", "304", showtimes_14);
		
		ArrayList<String> showtimes_15 = new ArrayList<String>();
		showtimes_15.add("1100");showtimes_15.add("0900");showtimes_15.add("1200");showtimes_15.add("1800");showtimes_15.add("2000");
		cinemas[14] = new Cinema("Normal", "305", showtimes_15);
		return cinemas;
	}
	
	public static Movie[] generateMovies() {
		Movie[] movies = new Movie[10];
		
		ArrayList<String> cast_1 = new ArrayList<String>();
		cast_1.add("Angelina Jolie");cast_1.add("Elle Fanning");cast_1.add("Michelle Pfeiffer");
		movies[0] = new Movie("Disney's Maleficent: Mistress Of Evil", "Showing", "Maleficent: Mistress of Evil is a fantasy adventure that picks up several years after Maleficent, in which audiences learned of the events that hardened the heart of Disney's most notorious villain and drove her to curse a baby Princess Aurora. The film continues to explore the complex relationship between the horned fairy and the soon to be Queen as they form new alliances and face new adversaries in their struggle to protect the moors and the magical creatures that reside within.", "Joachim Ronning", "Adventure/ Fantasy", cast_1);
		
		ArrayList<String> cast_2 = new ArrayList<String>();
		cast_2.add("Joaquin Phoenix");cast_2.add("Robert De Niro");cast_2.add("Zazie Beetz");
		movies[1] = new Movie("Joker", "Showing", "A failed stand-up comedian is driven insane and becomes a psychopathic murderer.", "Todd Phillips", "Action/ Adventure", cast_2);
			
		ArrayList<String> cast_3 = new ArrayList<String>();
		cast_3.add("Will Smith");cast_3.add("Mary Elizabeth Winstead");cast_3.add("Clive Owen");
		movies[2] = new Movie("Gemini Man", "Showing", "Gemini Man is an innovative action-thriller starring Will Smith as Henry Brogan, an elite assassin, who is suddenly targeted and pursued by a mysterious young operative that seemingly can predict his every move. The film is directed by Academy Award-winning filmmaker Ang Lee and produced by renown producers Jerry Bruckheimer, David Ellison, Dana Goldberg and Don Granger. Also starring are Mary Elizabeth Winstead, Clive Owen and Benedict Wong.", "Ang Lee", "Action/ Adventure", cast_3);
			
		ArrayList<String> cast_4 = new ArrayList<String>();
		cast_4.add("Brad Pitt");cast_4.add("Tommy Lee Jones");cast_4.add("Ruth Negga");
		movies[3] = new Movie("Ad Astra", "Showing", "Astronaut Roy McBride (Brad Pitt) travels to the outer edges of the solar system to find his missing father and unravel a mystery that threatens the survival of our planet. His journey will uncover secrets that challenge the nature of human existence and our place in the cosmos.", "James Gray", "Action/ Adventure", cast_4);
			
		ArrayList<String> cast_5 = new ArrayList<String>();
		cast_5.add("Wu Jing");cast_5.add("Zhang Ziyi");cast_5.add("Jing Boran");
		movies[4] = new Movie("The Climbers", "Showing", "The four members of the China Everest Climbing Commando are attacking the most difficult second step of Mount Everest. This is their fifth attempt. The first four failures have cost them too much physical strength but finally, the wind and snow stop and they have an opportunity to climb.", "Daniel Lee", "Drama", cast_5);
		
		ArrayList<String> cast_6 = new ArrayList<String>();
		cast_6.add("Diego Boneta");cast_6.add("Arnold Schwarzenegger");cast_6.add("Linda Hamilton");
		movies[5] = new Movie("Terminator: Dark Fate", "Showing", "Linda Hamilton (Sarah Connor) and Arnold Schwarzenegger (T-800) return in their iconic roles in Terminator: Dark Fate, directed by Tim Miller (Deadpool) and produced by visionary filmmaker James Cameron and David Ellison. Following the events of Terminator 2: Judgment Day, Terminator: Dark Fate also stars Mackenzie Davis, Natalia Reyes, Gabriel Luna, and Diego Boneta.", "Tim Miller", "Action/ Adventure", cast_6);
		
		ArrayList<String> cast_7 = new ArrayList<String>();
		cast_7.add("Constance Wu");cast_7.add("Jennifer Lopez");cast_7.add("Lili Reinhart");
		movies[6] = new Movie("Hustlers", "Showing", "Hustlers follows a crew of savvy former strip club employees who band together to turn the tables on their Wall Street clients. The film was inspired by the viral article published by New York Magazine entitled �The Hustlers at Scores� written by Jessica Pressler.", "Lorene Scafaria", "Comedy", cast_7);
		
		ArrayList<String> cast_8 = new ArrayList<String>();
		cast_8.add("Alexander Skarsgard");cast_8.add("Nat Wolff");cast_8.add("Adam Long");
		movies[7] = new Movie("The Kill Team", "Showing", "When Andrew Briggman (Nat Wolff), a young soldier in the US invasion of Afghanistan, witnesses other recruits killing innocent civilians under the direction of a sadistic leader, Sergeant Deeks (Alexander Skarsgard), he considers reporting them to higher-ups - but the heavily-armed, increasingly violent platoon becomes suspicious that someone in their ranks has turned on them, and Andrew begins to fear that he'll be the next target.", "Dan Krauss", "War", cast_8);
		
		ArrayList<String> cast_9 = new ArrayList<String>();
		cast_9.add("Bo Huang");cast_9.add("Yi Zhang");cast_9.add("Jing Wu");
		movies[8] = new Movie("My People My Country", "Showing", "An anthology film consists of 7 short stories directed by several different directors, which are based on 7 moments since the foundation of the People's Republic of China.", "Kaige Chen , Yibai Zhang , Hu Guan , Xiaolu Xue , Zheng Xu , Hao Ning , Muye Wen", "History", cast_9);
		
		ArrayList<String> cast_10 = new ArrayList<String>();
		cast_10.add("Charlize Theron");cast_10.add("Oscar Isaac");cast_10.add("Chloe Grace Moretz");
		movies[9] = new Movie("The Addams Family", "Showing", "Get ready to snap your fingers! The first family of Halloween, the Addams Family, is back on the big screen in the first animated comedy about the kookiest family on the block. Funny, outlandish, and completely iconic, the Addams Family redefines what it means to be a good neighbor.", "Greg Tiernan, Conrad Vernon", "Family", cast_9);
		
		return movies;
	}
}	