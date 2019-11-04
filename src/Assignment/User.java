package Assignment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class User implements Serializable{
	private String username;
	private String email;
	private String telno;
	private int age;
	private ArrayList<MovieTicket> ticket_history = new ArrayList<MovieTicket>(); // Start out with no history
	
	
	public User(String username, String email, String telno, int age) {
		this.username = username;
		this.email = email;
		this.telno = telno;
		this.age = age;
	}
	
	//setMethods
	public void setUsername(String username) {this.username = username;}
	public void setEmail(String email) {this.email = email;}
	public void setTelno(String telno) {this.telno = telno;}
	public void setAge(int age) {this.age = age;}
	
	//getMethods
	public String getUsername() {return this.username;}
	public String getEmail() {return this.email;}
	public String getTelno() {return this.telno;}
	public int getAge() {return this.age;}
	
	public void viewTicketHistory() {
		System.out.println("Generating Ticket History");
		System.out.println("-----------------------");
		if (ticket_history.size() == 0) {
			System.out.println("No tickets have been booked yet");
		}else {
			for (int i = 0; i < ticket_history.size(); i++) {
				MovieTicket ticket = ticket_history.get(i);
				System.out.println("Transation ID is " + ticket.getTID());
				System.out.println(ticket.getQuantityTickets() + " Ticket(s) for " + ticket.getTMovie().getTitle() + " on " + ticket.getMovieDate() + " at " + ticket.getTTime());
				System.out.printf("Each ticket is $%.2f\n", ticket.getPerTicketPrice());
				System.out.printf("Total price is $%.2f\n", ticket.getPrice());
				System.out.println("Purchased on " + ticket.getCurrentDateTime());
				System.out.println();
			}
		}
	}
	
	public void addTicket(MovieTicket ticket) {
		this.ticket_history.add(ticket);
	}
}
