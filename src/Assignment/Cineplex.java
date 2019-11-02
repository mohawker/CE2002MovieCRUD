package Assignment;

import java.util.ArrayList;
import java.io.Serializable;


public class Cineplex implements Serializable{
	public String name;
	public String location;
	public ArrayList<Cinema> cinemas;
	public ArrayList<Movie> movies;
	
	public Cineplex(String name, String location, ArrayList<Cinema> cinemas, ArrayList<Movie> movies) {
		this.name = name;
		this.location = location;
		this.cinemas = cinemas;
		this.movies = movies;
	}
}