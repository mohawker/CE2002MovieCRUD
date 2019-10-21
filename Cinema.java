package movieAssignment;

import java.util.ArrayList;
public class Cinema {
	protected static int ROW=8;
	protected static int COLOMUN=8;
	public int[][][] floorplan;
	public ArrayList<String> showtimes;
	public String cine_type;
	public String cinema_code;//3lettercode
	
	public Cinema() {
		
	}
	
	public void generateSeats() {
		int totalTime = showtimes.size();
		
		for (int i=0;i<COLOMUN;i++) {
			for (int j=0;i<ROW;i++) {
				for (int k=0;i<totalTime;i++) {
					floorplan[i][j][k]=0;
				}
			}
		}
	}
	public void bookSeats(int numberOfSeats) {
		
	}
	
	//Show available seats for a specific show time
	public void viewSeats(String time) {
		int index = showtimes.indexOf(time);
		for (int i=0;i<COLOMUN;i++) {
			for (int j=0;j<ROW;j++) {
				System.out.printf(" [%d] ",floorplan[i][j][index]);
				
			}
		}
		
	}
}
