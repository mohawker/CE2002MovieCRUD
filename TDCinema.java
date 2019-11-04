package Assignment;

import java.util.ArrayList;

public final class TDCinema extends RetangularCinema{
	private int TDROW=6;
	private int TDCOL=6;
	
	public TDCinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) {
		super(cinema_type, cinema_code, showtimes, dates);
		setROW(TDROW);
		setCOL(TDCOL);
		generateFloorPlan();
	}
	

}
