package Assignment;

import java.awt.*;
import java.net.URI;
public class VideoPlayer {
	
	static Desktop d = Desktop.getDesktop();
		
	public static void play(Movie movie){
		try {
			switch (movie.getTitle()) {
				case ("Joker"):
					System.out.println("Trailer for " + movie.getTitle() + " will be showing\n");
					VideoPlayer.d.browse(new URI("https://www.youtube.com/watch?v=zAGVQLHvwOY"));
					break;
				case ("Disney's Maleficent: Mistress Of Evil"):
					System.out.println("Trailer for " + movie.getTitle() + " will be showing\n");
					VideoPlayer.d.browse(new URI("https://www.youtube.com/watch?v=n0OFH4xpPr4"));
					break;
				case ("Gemini Man"):
					System.out.println("Trailer for " + movie.getTitle() + " will be showing\n");
					VideoPlayer.d.browse(new URI("https://www.youtube.com/watch?v=AbyJignbSj0"));
					break;
				case ("Ad Astra"):
					System.out.println("Trailer for " + movie.getTitle() + " will be showing\n");
					VideoPlayer.d.browse(new URI("https://www.youtube.com/watch?v=nxi6rtBtBM0"));
					break;
				case ("The Climbers"):
					System.out.println("Trailer for " + movie.getTitle() + " will be showing\n");
					VideoPlayer.d.browse(new URI("https://www.youtube.com/watch?v=u-mCoBCDlzU"));
					break;
				case ("Terminator: Dark Fate"):
					System.out.println("Trailer for " + movie.getTitle() + " will be showing\n");
					VideoPlayer.d.browse(new URI("https://www.youtube.com/watch?v=oxy8udgWRmo"));
					break;
				case ("Hustlers"):
					System.out.println("Trailer for " + movie.getTitle() + " will be showing\n");
					VideoPlayer.d.browse(new URI("https://www.youtube.com/watch?v=LUG2U-IxPx0"));
					break;
				case ("The Kill Team"):
					System.out.println("Trailer for " + movie.getTitle() + " will be showing\n");
					VideoPlayer.d.browse(new URI("https://www.youtube.com/watch?v=0mab6h2sE5g"));
					break;
				case ("My People My Country"):
					System.out.println("Trailer for " + movie.getTitle() + " will be showing\n");
					VideoPlayer.d.browse(new URI("https://www.youtube.com/watch?v=bvrJS1LL_HY"));
					break;
				case ("The Addams Family"):
					System.out.println("Trailer for " + movie.getTitle() + " will be showing\n");
					VideoPlayer.d.browse(new URI("https://www.youtube.com/watch?v=F7Ug863S8dQ"));
					break;
			}
		}

		

		catch (Exception e){
			e.printStackTrace();
		}
	}
}
