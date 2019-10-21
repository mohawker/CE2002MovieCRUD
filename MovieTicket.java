package movieAssignment;

public class MovieTicket {
	public Movie movie;
	public Cinema cinema;
	public String time;
	public String TID;
	public User user;
	public float price = 8;//set defualt price multiplied by multipliers
	//Multipliers for price
	//Peak period = 1.5x
	//Student/Senior price = -1;
	//Gold class = 5x
	//3D = +10
	//DobeHD = +5
	public MovieTicket(){
		
	}
	//generatePrice
	public void setPrice() {
		if(movie.type == "DobeHD") 
			this.price += 5;
		if(movie.type == "3D") 
			this.price += 10;
		if (user.getAge()<=18 ||user.getAge()>=65)
			this.price -= 1;
		if (time == "peak")
			this.price *= 1.5;
		if (cinema.cine_type == "gold class")
			this.price *= 5;

	}
	
	//generateTID
	
	public void createTID() {
		
	}
	//getMethods
	
	public float getPrice() {
		return price;
	}
	
	public String getTID() {
		return TID;
	}
}
