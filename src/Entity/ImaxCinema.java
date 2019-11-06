package Entity;

import java.util.ArrayList;

public class ImaxCinema extends CircularCinema{
	private int ImaxDiamter = 5;
	

	public ImaxCinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) { // can customise seats based on cinema_type
		super(cinema_type, cinema_code, showtimes, dates);
		DIAMETER = ImaxDiamter;
		setROW(DIAMETER);
		setCOL(DIAMETER);
		// dynamically generate floor plan;
		generateFloorPlan();
		
	}
}
