package Assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public abstract class View{
	MOBLIMA app; 	
	public View(MOBLIMA myApp) {
		app = myApp;		
	}	
	public abstract int printView();
	
}
