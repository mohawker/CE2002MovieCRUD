package entity;

import java.util.ArrayList;

/**
 * Three Dimensional Cinema (TDCinema) is a RectangularCinema
 */
public final class TDCinema extends RetangularCinema{
	private int TDROW=6;
	private int TDCOL=6;
	
	/**
	 * Uses RectangularCinema constructor and geenrates its own floorplan based on its rows and columns
	 * @param cinemaType - Cinema can be Normal/GoldClass/Imax
	 * @param cinemaCode - Unique 3-letter code assigned to the cinema
	 * @param showtimes - ArrayList of Strings which contain showtimes in 24H format
	 * @param dates - ArrayList of Strings which contain showtimes in DD/MM/YYYY format
	 */
	public TDCinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) {
		super(cinema_type, cinema_code, showtimes, dates);
		setROW(TDROW);
		setCOL(TDCOL);
		generateFloorPlan();
	}
	

}
