package Assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class ViewControl extends Control{

	public ViewControl(Set<Movie> uniqueMovies, User user, ArrayList<Cineplex> cineplexes) {
		super(uniqueMovies, user, cineplexes);
	}
	
	public void adminView(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		Admin admin = new Admin("Admin", "tom@gmail.com", "98765432", 22, "Password123");
		AdminControl adminControl = new AdminControl(uniqueMovies, admin, cineplexes);
		ViewControl viewHelper = new ViewControl(uniqueMovies, admin, cineplexes);
		Cineplex cineplex_1 = cineplexes.get(0);
		Cineplex cineplex_2 = cineplexes.get(1);
		Cineplex cineplex_3 = cineplexes.get(2);
		admin.login();
		System.out.println("Welcome, " + admin.getUsername());
		while (1==1) {
			System.out.println("------------------------------------------------------");
			System.out.println("[1] Create Movie Listing");
			System.out.println("[2] Update Movie Listing (Movie Showing Status)");
			System.out.println("[3] Remove Movie Listing");			
			System.out.println("[4] Create Cinema Showtimes");
			System.out.println("[5] Update Cinema Showtimes");
			System.out.println("[6] Remove Cinema Showtimes");
			System.out.println("[7] Configure System Settings");
			System.out.println("[8] Add a Public Holiday");
			System.out.println("[9] List Top 5 Movies by Ticket Sales or Overall Rating");
			System.out.println("[10] Logout to see User view");
			System.out.println("[11] Log Off & Shut Down");
			System.out.println("------------------------------------------------------");
			System.out.print("Please select (1-10) : ");
			int choice = InputControl.integerInput(1, 11);
			switch (choice){
				case 1:{adminControl.createMovieListing(admin, cineplexes, cineplex_1, cineplex_2, cineplex_3, uniqueMovies);break;}
				case 2:{adminControl.updateMovieListing(cineplexes, uniqueMovies);break;}
				case 3:{adminControl.removeMovieListing(cineplexes, uniqueMovies);break;}
				case 4:{adminControl.createCinemaShowtimes(admin, cineplexes, cineplex_1, cineplex_2, cineplex_3, uniqueMovies);break;}
				case 5:{adminControl.updateCinemaShowtimes(cineplexes, uniqueMovies, admin);break;}
				case 6:{adminControl.removeCinemaShowtimes(cineplexes, uniqueMovies, admin);break;}
				case 7:{adminControl.configureSettings();break;}
				case 8:{adminControl.addNewHoliday();break;}
				case 9:{adminControl.listTop5(uniqueMovies);break;}
				case 10:{viewHelper.userView(cineplexes, uniqueMovies);break;}
				case 11:{System.out.println("Thank you for using MOBLIMA!\nSystem Logging Off...");return;}	
				default:{System.out.println("Please enter a valid choice");}
			}
			System.out.println();
			System.out.println("\nWhat would you like to do next?");
		}
	}

	public void userView(ArrayList<Cineplex> cineplexes, Set<Movie> uniqueMovies) {
		System.out.print("Enter your name: ");
		String userName = InputControl.stringInput();
		System.out.print("Enter your age: ");
		int userAge = InputControl.integerInput(1, 150);
		User user = new User(userName, "vincentyongweijie@gmail.com", "83189252", userAge);
		UserControl userControl = new UserControl(uniqueMovies, user, cineplexes);
		ViewControl viewHelper = new ViewControl(uniqueMovies, user, cineplexes);
		BookingManager bookingManager = new BookingManager(uniqueMovies, user, cineplexes);
		System.out.println("------------------------------------------------------");
		System.out.println("Welcome, " + user.getUsername());
		System.out.printf("The date is: %s\n", java.time.LocalDate.now());
		while (true) {
			System.out.println("------------------------------------------------------");
			System.out.println("[1] List Movie");
			System.out.println("[2] View Movie Details");
			System.out.println("[3] Check Seat Availability");
			System.out.println("[4] Book Ticket");
			System.out.println("[5] View Booking History");
			System.out.println("[6] List Top 5 Movies by Ticket Sales or Overall Rating");
			System.out.println("[7] Add Rating");
			System.out.println("[8] Login as Admin");
			System.out.println("[9] Log Off & Shut Down");
			System.out.println("------------------------------------------------------");
			System.out.print("Please select (1-9) : ");
			
			// error checker class - checkInt , checkStr etc
			int choice = InputControl.integerInput(1, 9);
			System.out.println();
			switch (choice){
				case 1:{userControl.listUniqueMovies(uniqueMovies, cineplexes);break;}
				case 2:{userControl.viewMovieDetails(user, uniqueMovies);break;}
				case 3:{userControl.checkSeatAvailability(user, cineplexes);break;}
				case 4:{bookingManager.bookTicket(user, cineplexes);break;}
				case 5:{userControl.viewBookingHistory(user);break;}
				case 6:{userControl.listTop5(uniqueMovies);break;}
				case 7:{userControl.addRating(uniqueMovies, user);break;}
				case 8:{viewHelper.adminView(cineplexes, uniqueMovies);break;}
				case 9:{System.out.println("Thank you for using MOBLIMA!\nSystem Logging Off...");return;}	
				default:{System.out.println("Please enter a valid choice");}
			}
			System.out.println("\nWhat would you like to do next?");
		}
	}
}
