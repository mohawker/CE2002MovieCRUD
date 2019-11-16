package Entity;

import java.util.ArrayList;

/**
 * Normal cinema with a certain number of rows and columns
 */
public final class NormalCinema extends RetangularCinema{
	private int NROW=8;
	private int NCOL=8;
	
	/**
	 * Generates floorplan based on number of rows and columns
	 * @param cinemaType - Cinema can be Normal/GoldClass/Imax
	 * @param cinemaCode - Unique 3-letter code assigned to the cinema
	 * @param showtimes - ArrayList of Strings which contain showtimes in 24H format
	 * @param dates - ArrayList of Strings which contain showtimes in DD/MM/YYYY format
	 */
	public NormalCinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) {
		super(cinema_type, cinema_code, showtimes, dates);
		// TODO Auto-generated constructor stub
		setROW(NROW);
		setCOL(NCOL);
		generateFloorPlan();
	}
}
