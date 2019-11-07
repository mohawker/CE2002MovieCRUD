package Entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;
import java.io.Serializable;

public class DateChecker implements Serializable{
	
	public ArrayList<Date> publicHoliday = new ArrayList<Date>();
	
	@SuppressWarnings("deprecation")
	public void generatePublicHoliday() {
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
	
	public void printPublicHoliday() {
		System.out.println("===== the public holiday is =====");
		for (Date holiday: publicHoliday) {
			System.out.println(holiday.toString());
		}
		
	}
	
	public DateChecker() {
		generatePublicHoliday();
	}
	
	// date is DD/MM/YYYY
	@SuppressWarnings("deprecation")
	public boolean checkSpecialDate(String date) {
		boolean specialDate = false;
		//parse string
		Scanner scan = new Scanner(System.in);
		
		int day = Integer.valueOf(date.substring(0, 2));
		int month = Integer.valueOf(date.substring(3, 5)) - 1;
		int year = Integer.valueOf(date.substring(6, 10)) - 1900;
		
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
	
	@SuppressWarnings("deprecation")
	public void addSpecialDate(String date) {
		//Fixed year 2019
		int def_year = Calendar.getInstance().get(Calendar.YEAR) - 1900;
		String[] dateSplit = date.split("/"); //Split based on /
		int day = 0, month=0;
		try{
			day = Integer.parseInt(dateSplit[0].trim());
			month = Integer.parseInt(dateSplit[1].trim());
		} catch(NumberFormatException nfe) {
			System.out.println("NumberFormatException: " + nfe.getMessage());
			return;
		}
		
		System.out.printf("Day: %d Month: %d\n", day, month);
		if ((day>=1 && day<=31) && (month>=1 && month <= 12)) {
			publicHoliday.add(new Date(def_year,month-1,day-1));//account for range of day month
			System.out.printf("Updated Specials Date for %s\n", date);
		}
		else {
			System.out.println("Error please input valid date");
			return;
		}
	}
}
