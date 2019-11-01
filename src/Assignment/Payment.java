package Assignment;
import java.util.Scanner;

public class Payment {

	public boolean authenticatePayment(MovieTicket ticket){
		Scanner scan = new Scanner(System.in);
		System.out.printf("Total Price would be $%.2f. Would you like to go ahead with the transaction? (Y/N)\n", ticket.getPrice());
		char choice = scan.next().charAt(0);
		if (choice == 'Y') {
			return true;
		}
		else if (choice == 'N') {
			return false;
		}
		else {
		System.out.println("Please input either Y or N");
		this.authenticatePayment(ticket);
	}
	return false;
}
}
