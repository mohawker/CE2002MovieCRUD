package movieAssignment;

import java.util.ArrayList;

public class Movie {
	public String title;
	public String status;
	public String synopsis;
	public String director;
	public String type;
	public ArrayList <String> cast;

	
	//public float userRating=0;
	//public ArrayList <String> reviewAndRatings;
	
	public Movie() {
	}
	//Calculate average of user rating
	//public void calcAverageUserRating(float rating) {
	//	this.userRating = (this.userRating+rating)/2;
	}
	//setMethods
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setCast(ArrayList <String> cast) {
		this.cast = cast;
	}
	//getMethods
		
	public String getTitle() {
		return title;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public String getDirector() {
		return director;
	}
	
	public String getType() {
		return type;
	}
	
	
	
}
