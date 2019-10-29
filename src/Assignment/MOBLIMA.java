package Assignment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;   

public class MOBLIMA {

	public static void main(String[] args) {
		
		Cinema[] cinemas;
		Movie[] movies;
		cinemas = Initialiser.generateCinemas();
		movies = Initialiser.generateMovies();
			
		ArrayList<Cineplex> cineplexes = Initialiser.generateCineplexes(cinemas, movies);
		Cineplex cineplex_1 = cineplexes.get(0);
		Cineplex cineplex_2 = cineplexes.get(1);
		Cineplex cineplex_3 = cineplexes.get(2);
		
		Set<Movie> uniqueMovies = new HashSet<>();
		uniqueMovies = Initialiser.generateMovies(cineplexes);
		
		
		
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
			User user = new User(userName, "vincentyongweijie@gmail.com", "83189252", userAge);
			UserHelper userHelper = new UserHelper(uniqueMovies, user, cineplexes);
			ViewHelper viewHelper = new ViewHelper(uniqueMovies, user, cineplexes);
			System.out.println("------------------------------------------------------");
			System.out.println("Welcome, " + user.getUsername());
			System.out.printf("The date is: %s\n", java.time.LocalDate.now());
			
			
			// date class to create and add holidays
			// add pricing class to do this dynamically - put this at seat booking
			System.out.println("Pricing Today for 2D Tickets: Regular Weekday (" + MovieTicket.BASE_PRICE + ")");
			if (user.getAge()>=55) {
				System.out.println("Please note that you are eligible for senior citizen discounts!");
			}
			// change all user_1 and admin_1 etc names
			while (true) {
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
				System.out.print("Please select (1-8) : ");
				
				// error checker class - checkInt , checkStr etc
				while (!scan.hasNextInt()){
					System.out.println("Error... Please input an Integer");
					scan.next();
				}
				choice = scan.nextInt();
				switch (choice){
					case 1:
						userHelper.user_1(uniqueMovies);
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
						viewHelper.adminView(cineplexes, uniqueMovies);
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
			ViewHelper viewHelper = new ViewHelper(uniqueMovies, admin, cineplexes);
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
				while (!scan.hasNextInt()){
					System.out.println("Error... Please input an Integer");
					scan.nextLine();
				}
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
						viewHelper.userView(cineplexes, uniqueMovies);
						break;
				}
				System.out.println();
				System.out.println("What would you like to do next?");
			}
		}
	}
}	
