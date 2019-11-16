package Boundary;

import Controller.BookingManager;
import Controller.InputControl;
import Controller.ReviewManager;
import Controller.SortingManager;
import Controller.UserControl;
import Entity.DateChecker;
import Entity.MOBLIMA;
import Entity.User;

/**
 * Provides a view of the functions that a normal user can perform
 */
public class UserView implements View{
	private UserControl userControl;
	private BookingManager bookingManager;
	private ReviewManager reviewManager;
	private SortingManager sortingManager;
	private User user;
	private MOBLIMA app;
	private DateChecker dateChecker;
	
	/**
	 * Constructor for admin view
	 * @param myApp - MOBLIMA app with the data
	 * @param myDateChecker - Used to check if a given date is a weekend or public holiday
	 */
	public UserView(MOBLIMA myApp, DateChecker myDateChecker) {
		app = myApp;	
		dateChecker = myDateChecker;
		System.out.print("Enter unique username: ");
		String userName = InputControl.stringInput();
		boolean found = false;
		for (User currUser: app.getUsers()) {
			if (currUser.getUsername().equals(userName)) {
				user = currUser;
				found = true;
				System.out.println("Welcome back " + user.getUsername());
				break;
			}
		}
		if (!found) {
			System.out.print("Enter your age: ");
			int userAge = InputControl.integerInput(1, 150);
			user = new User(userName, "vincentyongweijie@gmail.com", "83189252", userAge);
			app.getUsers().add(user);
		}
		
		System.out.println("+----------------------------------------------------+");
		System.out.println("|                                                    |");
		System.out.println("|             WELCOME TO MOBLIMA (v1.0)              |");
		System.out.println("|                   User Menu                        |");
		System.out.println("+----------------------------------------------------+");
		System.out.println("User Account: " + user.getUsername());
		System.out.printf("The date is: %s\n", java.time.LocalDate.now());
		userControl = new UserControl(app.getUniqueMovies(), user, app.getCineplexes());
		bookingManager = new BookingManager(app.getUniqueMovies(), user, app.getCineplexes());
		reviewManager = new ReviewManager(app.getUniqueMovies(), user, app.getCineplexes());
		sortingManager = new SortingManager(app.getUniqueMovies(), user, app.getCineplexes());
	}
	
	/**
	 * Prints functions available for user and prompts user to choose a function
	 */
	public int printView() {
		while (true){
			System.out.println("What would you like to do next?");
			System.out.println("------------------------------------------------------");
			System.out.println("[1] Search Movie");
			System.out.println("[2] View Trailer");
			System.out.println("[3] List Movie");
			System.out.println("[4] View Movie Details");
			System.out.println("[5] Check Seat Availability");
			System.out.println("[6] Book Ticket");
			System.out.println("[7] View Booking History");
			System.out.println("[8] List Top 5 Movies by Ticket Sales or Overall Rating");
			System.out.println("[9] Add Review");
			System.out.println("[10] Login as Admin");
			System.out.println("[11] Log Off, Save & Shut Down");
			System.out.println("------------------------------------------------------");
			System.out.print("Please select (1-11) : ");
			
			// error checker class - checkInt , checkStr etc
			int choice = InputControl.integerInput(1, 11);
			System.out.println();
			switch (choice){
				case 1:{userControl.searchUniqueMovies(app.getUniqueMovies(), app.getCineplexes());break;}
				case 2:{userControl.viewTrailer(app.getUniqueMovies());break;}
				case 3:{userControl.listUniqueMovies(app.getUniqueMovies(), app.getCineplexes());break;}
				case 4:{userControl.viewMovieDetails(app.getUniqueMovies());break;}
				case 5:{userControl.checkSeatAvailability(app.getCineplexes());break;}
				case 6:{bookingManager.bookTicket(user, app.getCineplexes(), dateChecker);break;}
				case 7:{userControl.viewBookingHistory(user);break;}
				case 8:{sortingManager.listTop5(app.getUniqueMovies());break;}
				case 9:{reviewManager.addReview(app.getUniqueMovies(), user);break;}
				case 10:{return 2;}
				case 11:{System.out.println("Thank you for using MOBLIMA!\n Saving and System Logging Off...");
						app.writeApp();
						return 0;}	
				default:{System.out.println("Please enter a valid choice");}
			}
			
		}
			
	}
		
}



