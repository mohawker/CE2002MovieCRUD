package Assignment;

import java.util.ArrayList;
import java.io.Serializable;


public class Cineplex implements Serializable{
	private String name;
	private String location;
	private ArrayList<Cinema> cinemas;
	private ArrayList<Movie> movies;
	
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