package Boundary;

import Controller.BookingManager;
import Controller.InputControl;
import Controller.ReviewManager;
import Controller.SortingManager;
import Controller.UserControl;
import Entity.DateChecker;
import Entity.MOBLIMA;
import Entity.User;

public class UserView extends View{
	UserControl userControl;
	BookingManager bookingManager;
	ReviewManager reviewManager;
	SortingManager sortingManager;
	User user;

	public UserView(MOBLIMA app, DateChecker dateChecker) {
		super(app, dateChecker);
		System.out.print("Enter unique username: ");
		String userName = InputControl.stringInput();
		boolean found = false;
		for (User currUser: app.users) {
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
			app.users.add(user);
		}
		
		System.out.println("+----------------------------------------------------+");
		System.out.println("|                                                    |");
		System.out.println("|             WELCOME TO MOBLIMA (v1.0)              |");
		System.out.println("|                   User Menu                        |");
		System.out.println("+----------------------------------------------------+");
		System.out.println("User Account: " + user.getUsername());
		System.out.printf("The date is: %s\n", java.time.LocalDate.now());
		userControl = new UserControl(app.uniqueMovies, user, app.cineplexes);
		bookingManager = new BookingManager(app.uniqueMovies, user, app.cineplexes);
		reviewManager = new ReviewManager(app.uniqueMovies, user, app.cineplexes);
		sortingManager = new SortingManager(app.uniqueMovies, user, app.cineplexes);
	}
	
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
			System.out.println("[9] Add Rating");
			System.out.println("[10] Login as Admin");
			System.out.println("[11] Log Off, Save & Shut Down");
			System.out.println("------------------------------------------------------");
			System.out.print("Please select (1-11) : ");
			
			// error checker class - checkInt , checkStr etc
			int choice = InputControl.integerInput(1, 11);
			System.out.println();
			switch (choice){
				case 1:{userControl.searchUniqueMovies(app.uniqueMovies, app.cineplexes);break;}
				case 2:{userControl.viewTrailer(app.uniqueMovies);break;}
				case 3:{userControl.listUniqueMovies(app.uniqueMovies, app.cineplexes);break;}
				case 4:{userControl.viewMovieDetails(app.uniqueMovies);break;}
				case 5:{userControl.checkSeatAvailability(app.cineplexes);break;}
				case 6:{bookingManager.bookTicket(user, app.cineplexes, dateChecker);break;}
				case 7:{userControl.viewBookingHistory(user);break;}
				case 8:{sortingManager.listTop5(app.uniqueMovies);break;}
				case 9:{reviewManager.addRating(app.uniqueMovies, user);break;}
				case 10:{return 2;}
				case 11:{System.out.println("Thank you for using MOBLIMA!\n Saving and System Logging Off...");
						app.writeApp();
						return 0;}	
				default:{System.out.println("Please enter a valid choice");}
			}
			
		}
			
	}
		
}



