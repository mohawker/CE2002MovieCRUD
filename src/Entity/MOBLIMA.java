package Entity;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Boundary.AdminView;
import Boundary.UserView;
import Controller.InputControl;   

public class MOBLIMA implements Serializable {
	private Cinema[] cinemas;
	private Movie[] movies;
	private ArrayList<Cineplex> cineplexes;
	private Cineplex cineplex_1;
	private Cineplex cineplex_2;
	private Cineplex cineplex_3;
	private Set<Movie> uniqueMovies = new HashSet<>();
	private ArrayList<User> users = new ArrayList<User>();
	private DateChecker myDateChecker = new DateChecker();
	
	public void initialiseApp(){
		cinemas = Initialiser.generateCinemas();
		movies = Initialiser.generateMovies();
		cineplexes = Initialiser.generateCineplexes(cinemas, movies);
		setCineplex_1(cineplexes.get(0));
		setCineplex_2(cineplexes.get(1));
		setCineplex_3(cineplexes.get(2));
		setUniqueMovies(Initialiser.generateMovies(cineplexes));
	}
	
	public static MOBLIMA loadApp() {
		System.out.println("Welcome to MOBLIMA App");
		System.out.println("Would you like to continue to load previous save or start anew?");
		System.out.println("[1] Load");
		System.out.println("[2] New");
		int choice = InputControl.integerInput(1, 2);
		if (choice == 1) {
			try	{
				// read from serialized file
				String path = System.getProperty("user.dir")+ "/src/" + "/moblima.dat";
				MOBLIMA oldApp = SerializeDB.readSerializedObject(path);
				return oldApp;
			}  catch ( Exception e ) {
				System.out.println( "Failed to load......Exception >> " + e.getMessage() );
				//failed to load 
				MOBLIMA newMoblimaApp = new MOBLIMA();
				newMoblimaApp.initialiseApp();
				return newMoblimaApp;
			}
		}
		else {
			MOBLIMA newMoblimaApp = new MOBLIMA();
			newMoblimaApp.initialiseApp();
			return newMoblimaApp;
		}
	}
	
	public void writeApp() {
		String path = System.getProperty("user.dir")+ "/src/" +"moblima.dat";
		SerializeDB.writeSerializedObject(path, this);
	}

	public MOBLIMA() {}

	public void MOBLIMAMainLoop() {
		System.out.println("Login As:");
		System.out.println("[1] User");
		System.out.println("[2] Admin");
		int choice = InputControl.integerInput(1, 2);
		//end 0, user 1 , admin 2
		while (true) {
			if (choice == 1) {
				UserView myUserView = new UserView(this, myDateChecker);
				choice = myUserView.printView();
			}
			else if (choice == 2){
				AdminView myAdminView = new AdminView(this, myDateChecker);
				choice = myAdminView.printView();
			}
			else {
				break;
			}
		}
	}
	
	

	public static void main(String[] args) {
		MOBLIMA myApp = loadApp();
		myApp.MOBLIMAMainLoop();
	}

	public Cineplex getCineplex_1() {return cineplex_1;}

	public void setCineplex_1(Cineplex cineplex_1) {this.cineplex_1 = cineplex_1;}

	public Cineplex getCineplex_2() {return cineplex_2;}

	public void setCineplex_2(Cineplex cineplex_2) {this.cineplex_2 = cineplex_2;}

	public Cineplex getCineplex_3() {return cineplex_3;}

	public void setCineplex_3(Cineplex cineplex_3) {this.cineplex_3 = cineplex_3;}

	public Set<Movie> getUniqueMovies() {return uniqueMovies;}

	public void setUniqueMovies(Set<Movie> uniqueMovies) {this.uniqueMovies = uniqueMovies;}

	public ArrayList<User> getUsers() {return users;}

	public void setUsers(ArrayList<User> users) {this.users = users;}
	
	public ArrayList<Cineplex> getCineplexes(){return cineplexes;}
	
	public void setCineplexes(ArrayList<Cineplex> cineplexes){this.cineplexes=cineplexes;}
}	
