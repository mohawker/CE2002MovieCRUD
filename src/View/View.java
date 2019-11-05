package View;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import Model.DateChecker;
import Model.MOBLIMA;

public abstract class View{
	MOBLIMA app; 	
	DateChecker dateChecker;

	public View(MOBLIMA myApp, DateChecker myDateChecker) {
		app = myApp;	
		dateChecker = myDateChecker;
	}	
	public abstract int printView();
	
}
