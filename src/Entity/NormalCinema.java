package Entity;

import java.util.ArrayList;

/**
 * Normal cinema with a certain number of rows and columns
 * @author vince
 *
 */
public final class NormalCinema extends RetangularCinema{
	private int NROW=8;
	private int NCOL=8;
	
	/**
	 * Generates floorplan based on number of rows and columns
	 * @param cinema_type
	 * @param cinema_code
	 * @param showtimes
	 * @param dates
	 */
	public NormalCinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) {
		super(cinema_type, cinema_code, showtimes, dates);
		// TODO Auto-generated constructor stub
		setROW(NROW);
		setCOL(NCOL);
		generateFloorPlan();
	}
	

}
