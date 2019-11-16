package Entity;

import java.util.Scanner;

/**
 * Admin is a subclass of User with a password attribute and a login method
 */
public class Admin extends User{
	
	private String password;
	
	/**
	 * Constructor takes in the following parameters as attributes of Admin
	 * @param username - Username of the admin
	 * @param email - Email address of the admin
	 * @param telno - Telephone number of the admin
	 * @param age - Age of the admin
	 * @param password - Password of the admin
	 */
	public Admin(String username, String email, String telno, int age, String password) {
		super(username, email, telno, age);
		this.password = password;
	}
	
	/**
	 * Allows admin to login using the unique password with 3 tries
	 */
	public void login() {
		System.out.print("Please enter the password: ");
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
