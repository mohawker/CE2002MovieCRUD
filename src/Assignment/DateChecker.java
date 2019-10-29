package Assignment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;
import java.util.Iterator;


public class DateChecker {
	
	static public ArrayList<Date> publicHoliday = new ArrayList<Date>();
	
	@SuppressWarnings("deprecation")
	static public void generatePublicHoliday() {
		int year = Calendar.getInstance().get(Calendar.YEAR) - 1900;
		//year y - 1900
		//month 0 - 11
		//day 1 - 31 
		publicHoliday.add(new Date(year,0,1));
		publicHoliday.add(new Date(year,1,5));
		publicHoliday.add(new Date(year,1,6));
		publicHoliday.add(new Date(year,3,19));
		publicHoliday.add(new Date(year,4,1));
		publicHoliday.add(new Date(year,4,19));
		publicHoliday.add(new Date(year,5,5));
		publicHoliday.add(new Date(year,8,9));
		publicHoliday.add(new Date(year,8,11));
		publicHoliday.add(new Date(year,9,27));
		publicHoliday.add(new Date(year,11,25));
	}
	
	// date is DD/MM/YYYY
	@SuppressWarnings("deprecation")
	static public boolean checkSpecialDate(String date) {
		boolean specialDate = false;
		//parse string
		generatePublicHoliday();
		Scanner scan = new Scanner(System.in);
		
		int day = Integer.valueOf(date.substring(0, 2));
		int month = Integer.valueOf(date.substring(3, 5));
		int year = Integer.valueOf(date.substring(6, 10));
		
		Date userDate = new Date(year,month,day);		
		Calendar c1 = Calendar.getInstance();
	    c1.setTime(userDate);
	    
	    if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) { 
	    	specialDate = true;
	    }
	    
	    for (Date data: publicHoliday) {
	    	if (date.equals(userDate)) {
	    		specialDate = true;
	    	}
	    }
	    return specialDate;
	}
}