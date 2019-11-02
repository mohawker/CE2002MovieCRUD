package Assignment;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;   

public class MOBLIMA implements Serializable {
	Cinema[] cinemas;
	Movie[] movies;
	ArrayList<Cineplex> cineplexes;
	Cineplex cineplex_1;
	Cineplex cineplex_2;
	Cineplex cineplex_3;
	Set<Movie> uniqueMovies = new HashSet<>();
	ArrayList<User> users = new ArrayList<User>();
	
	
	public void initialiseApp(){
		cinemas = Initialiser.generateCinemas();
		movies = Initialiser.generateMovies();
		cineplexes = Initialiser.generateCineplexes(cinemas, movies);
		cineplex_1 = cineplexes.get(0);
		cineplex_2 = cineplexes.get(1);
		cineplex_3 = cineplexes.get(2);
		uniqueMovies = Initialiser.generateMovies(cineplexes);
	}
	
	public static MOBLIMA loadApp() {
		System.out.println("Welcome to MOBLIMA App");
		System.out.println("Would you like to continue to load previous save or start anew?");
		System.out.println("[1] load");
		System.out.println("[2] new");
		int choice = InputControl.integerInput(1, 2);
		if (choice == 1) {
			try	{
				// read from serialized file
				String path = System.getProperty("user.dir")+ "/src/" + "Assignment" +"/moblima.dat";
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
		String path = System.getProperty("user.dir")+ "/src/" + this.getClass().getPackage().getName() +"/moblima.dat";
		SerializeDB.writeSerializedObject(path, this);
	}

	
	public MOBLIMA() {
	}

	
	public void MOBLIMAMainLoop() {
		System.out.println("Would you like to be a user or admin?");
		System.out.println("[1] user");
		System.out.println("[2] admin");
		int choice = InputControl.integerInput(1, 2);
		//end 0, user 1 , admin 2
		while (true) {
			if (choice == 1) {
				UserView myUserView = new UserView(this);
				choice = myUserView.printView();

			}
			else if (choice == 2){
				AdminView myAdminView = new AdminView(this);
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
}	
