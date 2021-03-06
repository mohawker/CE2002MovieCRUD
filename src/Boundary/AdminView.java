package boundary;

import controller.AdminControl;
import controller.InputControl;
import controller.SortingManager;
import entity.Admin;
import entity.DateChecker;
import entity.MOBLIMA;

/**
 * Provides a view of the functions that an admin can perform
 */
public  class AdminView implements View{
	private Admin admin;
	private AdminControl adminControl;
	private SortingManager sortingManager;
	
	private MOBLIMA app;
	private DateChecker dateChecker;
	
	/**
	 * Constructor for the admin view
	 * @param myApp - MOBLIMA app with the data
	 * @param myDateChecker - Used to check if a given date is a weekend or public holiday
	 */
	public AdminView(MOBLIMA myApp, DateChecker myDateChecker) {
		app = myApp;	
		dateChecker = myDateChecker;
		admin = new Admin("Admin", "tom@gmail.com", "98765432", 22, "Password123");
		adminControl = new AdminControl(app.getUniqueMovies(), admin, app.getCineplexes());
		sortingManager = new SortingManager(app.getUniqueMovies(), admin, app.getCineplexes());
		admin.login();
		System.out.println("+----------------------------------------------------+");
		System.out.println("|                                                    |");
		System.out.println("|                   MOBLIMA (v1.0)                   |");
		System.out.println("|                    Admin Menu                      |");
		System.out.println("+----------------------------------------------------+");
		System.out.println("Welcome, " + admin.getUsername());
		System.out.println("------------------------------------------------------");
		System.out.printf("The date is: %s\n", java.time.LocalDate.now());
		
	}
	
	/**
	 * Prints functions available for admin and prompts admin to choose a function
	 */
	public int printView() {
		while (true) {
			System.out.println("What would you like to do next?");
			System.out.println("------------------------------------------------------");
			System.out.println("[1] Create Movie Listing");
			System.out.println("[2] Update Movie Listing (Movie Showing Status)");
			System.out.println("[3] Remove Movie Listing");			
			System.out.println("[4] Create Cinema Showtimes (For Movies with status 'Coming Soon' or 'Preview')");
			System.out.println("[5] Update Cinema Showtimes (For Movie with status 'Showing')");
			System.out.println("[6] Add Cinema Showtimes (For Movie with status 'Showing')");
			System.out.println("[7] Remove Cinema Showtimes (For Movie with status 'Showing')");
			System.out.println("[8] Configure System Settings");
			System.out.println("[9] Add a Public Holiday");
			System.out.println("[10] Print Public Holiday");
			System.out.println("[11] List Top 5 Movies by Ticket Sales or Overall Rating");
			System.out.println("[12] Logout to see User View");
			System.out.println("[13] Log Off, Save & Shut Down");
			System.out.println("------------------------------------------------------");
			System.out.print("Please select (1-13) : ");
			int choice = InputControl.integerInput(1, 13);
			switch (choice){
				case 1:{adminControl.createMovieListing(app.getCineplexes(), app.getUniqueMovies());break;}
				case 2:{adminControl.updateMovieListing(app.getCineplexes(), app.getUniqueMovies());break;}
				case 3:{adminControl.removeMovieListing(app.getCineplexes(), app.getUniqueMovies());break;}
				case 4:{adminControl.createCinemaShowtimes(app.getCineplexes(), app.getUniqueMovies());break;}
				case 5:{adminControl.updateCinemaShowtimes(app.getCineplexes(), app.getUniqueMovies());break;}
				case 6:{adminControl.addCinemaShowtimes(app.getCineplexes(), app.getUniqueMovies()); break;}
				case 7:{adminControl.removeCinemaShowtimes(app.getCineplexes(), app.getUniqueMovies());break;}
				case 8:{adminControl.configureSettings();break;}
				case 9:{adminControl.addNewHoliday(dateChecker);break;}
				case 10:{dateChecker.printPublicHoliday();break;}
				case 11:{sortingManager.listTop5(app.getUniqueMovies());break;}
				case 12:{return 1;}
				case 13:{System.out.println("Thank you for using MOBLIMA!\nSaving and System Logging Off...");
						app.writeApp();
						return 0;}	
				default:{System.out.println("Please enter a valid choice");}
			}
			System.out.println();	
		}
	}
}

