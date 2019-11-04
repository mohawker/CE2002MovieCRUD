package Assignment;

public class UserView extends View{
	UserControl userControl;
	BookingManager bookingManager;
	ReviewManager reviewManager;
	User user;

	public UserView(MOBLIMA app) {
		super(app);
		System.out.print("Enter your name: ");
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
		
		System.out.println("------------------------------------------------------");
		System.out.printf("The date is: %s\n", java.time.LocalDate.now());
		userControl = new UserControl(app.uniqueMovies, user, app.cineplexes);
		bookingManager = new BookingManager(app.uniqueMovies, user, app.cineplexes);
		reviewManager = new ReviewManager(app.uniqueMovies, user, app.cineplexes);
	}
	
	public int printView() {
		while (true){
			System.out.println("What would you like to do next?");
			System.out.println("------------------------------------------------------");
			System.out.println("[0] Search Movie");
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
			System.out.print("Please select (0-9) : ");
			
			// error checker class - checkInt , checkStr etc
			int choice = InputControl.integerInput(0, 9);
			System.out.println();
			switch (choice){
				case 0:{userControl.searchUniqueMovies(app.uniqueMovies, app.cineplexes);break;}
				case 1:{userControl.listUniqueMovies(app.uniqueMovies, app.cineplexes);break;}
				case 2:{userControl.viewMovieDetails(app.uniqueMovies);break;}
				case 3:{userControl.checkSeatAvailability(app.cineplexes);break;}
				case 4:{bookingManager.bookTicket(user, app.cineplexes);break;}
				case 5:{userControl.viewBookingHistory(user);break;}
				case 6:{userControl.listTop5(app.uniqueMovies);break;}
				case 7:{reviewManager.addRating(app.uniqueMovies, user);break;}
				case 8:{return 2;}
				case 9:{System.out.println("Thank you for using MOBLIMA!\nSystem Logging Off...");return 0;}	
				default:{System.out.println("Please enter a valid choice");}
			}
			
		}
			
	}
		
}



