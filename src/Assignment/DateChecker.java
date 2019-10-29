package Assignment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;
import java.util.Iterator;


public class DateChecker {
	
//    LocalDate inputDate = LocalDate.of(year,month,dayOfMonth);
	//(month, datOfMonth)
	static ArrayList<Date> publicHoliday = new ArrayList<Date>();
	
	
	
	static void generatePublicHoliday() {
//		New Year’s Day	1 Jan 2019	Tuesday
//		Chinese New Year	5 Feb 2019	Tuesday
//		6 Feb 2019	Wednesday
//		Good Friday	19 Apr 2019	Friday
//		Labour Day	1 May 2019	Wednesday
//		Vesak Day	19 May 2019*	Sunday
//		Hari Raya Puasa	5 Jun 2019	Wednesday
//		National Day	9 Aug 2019	Friday
//		Hari Raya Haji	11 Aug 2019*	Sunday
//		Deepavali	27 Oct 2019*	Sunday
//		Christmas Day	25 Dec 2019	Wednesday
		
//		  (1,1),
//        (5,2),
//        (6,2),
//        (19,4),
//        (1,5),
//        (19,5),
//        (5,6),
//        (9,9),
//        (11,9),
//        (27,10),
//        (25,12)]
       		 

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
	
	static boolean checkSpecialDate() {
		boolean specialDate = false;
		//parse string
		generatePublicHoliday();
		Scanner scan = new Scanner(System.in);
		int year = Calendar.getInstance().get(Calendar.YEAR) - 1900; 
//		System.out.print("Year: "+  year);

		System.out.print("What day (1 - 31) ?: ");
		int day = scan.nextInt();
		System.out.print("What month (1 - 12) ?: ");
		int month = scan.nextInt() - 1;
		
		Date userDate = new Date(year,month,day);
//		System.out.println("userDate");
//		System.out.println(userDate);

		
		Calendar c1 = Calendar.getInstance();
	    c1.setTime(userDate);
//	    System.out.println(c1.get(Calendar.DAY_OF_WEEK));
	    
	    //1 is sunday, 7 is saturday
	    if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) { 
	    	specialDate = true;
	    }
	    
//	    System.out.println("The ArrayList elements are:");
	    for (Date temp: publicHoliday) {
//	    	System.out.println("In for");
	    	if (temp.equals(userDate)) {
	    		specialDate = true;
	    	}
	    	
	    }
	    System.out.print(specialDate);
	    return specialDate;

	}
}
