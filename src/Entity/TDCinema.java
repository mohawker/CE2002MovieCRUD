package Entity;

import java.util.ArrayList;

/**
 * Three Dimensional Cinema (TDCinema) is a RectangularCinema
 * @author vince
 *
 */
public final class TDCinema extends RetangularCinema{
	private int TDROW=6;
	private int TDCOL=6;
	
	/**
	 * Uses RectangularCinema constructor and geenrates its own floorplan based on its rows and columns
	 * @param cinema_type
	 * @param cinema_code
	 * @param showtimes
	 * @param dates
	 */
	public TDCinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) {
		super(cinema_type, cinema_code, showtimes, dates);
		setROW(TDROW);
		setCOL(TDCOL);
		generateFloorPlan();
	}
	

}
