package Entity;

import java.util.ArrayList;

public final class GCCinema extends RetangularCinema{
	private int GCROW=4;
	private int GCCOL=4;
	
	public GCCinema(String cinema_type, String cinema_code, ArrayList<String> showtimes, ArrayList<String> dates) {
		super(cinema_type, cinema_code, showtimes, dates);
		setROW(GCROW);
		setCOL(GCCOL);
		generateFloorPlan();
	}

}
