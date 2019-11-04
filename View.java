package Assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public abstract class View{
	MOBLIMA app; 	
	DateChecker dateChecker;

	public View(MOBLIMA myApp, DateChecker dateChecker) {
		app = myApp;		
	}	
	public abstract int printView();
	
}
