package movieAssignment;

import java.util.ArrayList;
import java.util.List;


public class User {
	private String username;
	private String email;
	private String telno;
	private int age;
	private ArrayList<String> ticket_history;
	
	public User() {
		
	}
	
	//setMethods
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setTelno(String telno) {
		this.telno = telno;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	//setTicketHistory
	public void setTicketHistory(String TID) {
		ticket_history.add(TID);
	}
	
	//viewTicketHistory
	public void viewTicketHistory() {
		System.out.println("Generating Ticket History");
		System.out.println("-----------------------");
		for (int i = 0; i < ticket_history.size(); i++) {
			System.out.println(ticket_history.get(i));
		}
	}
	
	//getMethods
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getTelno() {
		return this.telno;
	}
	
	public int getAge() {
		return this.age;
	}
	
}
