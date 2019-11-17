package entity;

import java.util.ArrayList;

/**
 * Gold Class Cinema is a RectangularCinema with its own number of rows
 * and columns
 * @author vince
 *
 */
public final class GCCinema extends RetangularCinema{
	private int GCROW=4;
	private int GCCOL=4;
	
	/**
	 * Uses the constructor from RectangularCinema class
	 * Gold Class Cinema floorplan is created using stated number of rows and columns
	 * @param cinemaType - Cinema can be Normal/GoldClass/Imax
	 * @param cinemaCode - Unique 3-letter code assigned to the cinema
	 * @param showtimes - ArrayList of Strings which contain showtimes in 24H format
	 * @param dates - ArrayList of Strings which contain showtimes in DD/MM/YYYY format
	 */
	public GCCinema(String cinemaType, String cinemaCode, ArrayList<String> showtimes, ArrayList<String> dates) { // can customise seats based on cinemaType
		super(cinemaType, cinemaCode, showtimes, dates);
		setROW(GCROW);
		setCOL(GCCOL);
		generateFloorPlan();
	}
}
