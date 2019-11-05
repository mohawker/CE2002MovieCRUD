package Model;

import java.util.ArrayList;

public final class NormalCinema extends RetangularCinema{
	private int NROW=8;
	private int NCOL=8;

	public NormalCinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) {
		super(cinema_type, cinema_code, showtimes, dates);
		// TODO Auto-generated constructor stub
		setROW(NROW);
		setCOL(NCOL);
		generateFloorPlan();
	}
	

}
