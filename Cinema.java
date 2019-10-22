package movieAssignment;

import java.util.Scanner;
import java.util.ArrayList;
public class Cinema {
	protected static int ROW=8;
	protected static int COLUMN=8;
	public int[][][] floorplan;
	public ArrayList<String> showtimes;
	public String cine_type;
	public String cinema_code;//3lettercode
	
	public Cinema() {
		
	}
	
	public void generateSeats() {
		int totalTime = showtimes.size();
		
		for (int i=0;i<COLUMN;i++) {
			for (int j=0;i<ROW;i++) {
				for (int k=0;i<totalTime;i++) {
					floorplan[i][j][k]=0;
				}
			}
		}
	}
	
	public void bookSeats(int numberOfSeats , String time) {//can implement idea of making sure booked seats are not staggered
		Scanner sc = new Scanner(System.in);
		int row=0, column=0;
		int index = showtimes.indexOf(time);
		int i=0;
		while (i<numberOfSeats) {
			System.out.println("Make " + ordinal(i+1)+ " selection : (coloumn) (row)");
			//scan row then column
			column= sc.nextInt();
			row = sc.nextInt();
			//make booking by adding one
			if (floorplan[column][row][index]==0) {
				floorplan[column][row][index]=1;
				i++;
			}
			else {
				System.out.println("Seat already booked please pick another seat");
				continue;
			}

		}
	}
	
	//Show available seats for a specific show time
	public void viewSeats(String time) {
		int index = showtimes.indexOf(time);
		System.out.printf("   ");
		for (int m=0;m<ROW;m++) {
			System.out.printf(" %d ", m+1);
		}
		System.out.println();
		for (int i=0;i<COLUMN;i++) {
			System.out.printf(" %d ", i+1);
			for (int j=0;j<ROW;j++) {
				System.out.printf(" [%d] ",floorplan[i][j][index]);
				
			}
			System.out.println();
		}
		
	}
	
	//Numbering formating
	public static String ordinal(int i) {
	    String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
	    switch (i % 100) {
	    case 11:
	    case 12:
	    case 13:
	        return i + "th";
	    default:
	        return i + sufixes[i % 10];

	    }
	}
}
