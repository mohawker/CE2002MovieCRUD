package Entity;

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
	 * Gold Class Cinema floorplan is created using stated number of
	 * rows and columns
	 * @param cinema_type
	 * @param cinema_code
	 * @param showtimes
	 * @param dates
	 */
	public GCCinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) {
		super(cinema_type, cinema_code, showtimes, dates);
		setROW(GCROW);
		setCOL(GCCOL);
		generateFloorPlan();
	}

}
