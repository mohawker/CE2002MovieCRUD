package Assignment;

import java.util.Scanner;

public class Admin extends User{
	
	String password;
	public Admin(String username, String email, String telno, int age, String password) {
		super(username, email, telno, age);
		this.password = password;
	}
	
	public void login() {
		System.out.println("Please enter the password: ");
		Scanner scan = new Scanner(System.in);
		int tries = 3;
		while(tries!=0) {
			if (this.password.equals(scan.next())) {
				System.out.println("Login Successful");
				break;
			}else {
				System.out.println("Login Unsuccessful");
				tries -= 1;
				System.out.println("You have " + tries + " attempts left");
			}
		}
		if (tries ==0 ) {
			System.out.println("You have failed 3 times");
			System.out.println("You are locked out");
			System.exit(0);
		}
	}
}
