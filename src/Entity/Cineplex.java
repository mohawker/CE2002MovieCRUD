package Entity;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Cineplex is made up of multiple cinemas and movies with a unique location
 * @author vince
 *
 */
public class Cineplex implements Serializable{
	private String name;
	private String location;
	private ArrayList<Cinema> cinemas;
	private ArrayList<Movie> movies;
	
	/**
	 * 
	 * @param name Branch of the cineplexes (e.g. Golden Village, Cathay)
	 * @param location Place of the cinema (e.g. Bishan)
	 * @param cinemas ArrayList of cinemas where movies will be shown in
	 * @param movies ArrayList of movies shown at this cineplex
	 */
	public Cineplex(String name, String location, ArrayList<Cinema> cinemas, ArrayList<Movie> movies) {
		this.name = name;
		this.location = location;
		this.cinemas = cinemas;
		this.movies = movies;
	}
	
	
	public void setName(String name) {this.name = name;}
	public void setLocation(String location) {this.location = location;}
	public void setCinemas(ArrayList<Cinema> cinemas) {this.cinemas = cinemas;}
	public void setMovies(ArrayList<Movie> movies) {this.movies = movies;}
	
	public String getName() {return this.name;}
	public String getLocation() {return this.location;}
	public ArrayList<Cinema> getCinemas(){return this.cinemas;}
	public ArrayList<Movie> getMovies(){return this.movies;}
}