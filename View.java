package Boundary;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import Entity.DateChecker;
import Entity.MOBLIMA;

public abstract class View{
	MOBLIMA app; 	
	DateChecker dateChecker;

	public View(MOBLIMA myApp, DateChecker myDateChecker) {
		app = myApp;	
		dateChecker = myDateChecker;
	}	
	public abstract int printView();
	
}
