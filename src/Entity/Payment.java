package entity;

import controller.InputControl;

/**
 * To authenticate payment
 */
public class Payment {
	
	/**
	 * Returns confirmation of purchase after user confirmation
	 * @param ticket - Movie ticket that is being purchases
	 * @return boolean confirming whether MovieTicket purchase is successful
	 */
	public boolean authenticatePayment(MovieTicket ticket){
		System.out.printf("Total Price would be $%.2f. Would you like to go ahead with the transaction? (Y/N)\n", ticket.getPrice());
		char choice = InputControl.ynInput();
		if (choice == 'Y') {
			System.out.printf("Payment is successful! You paid $%.2f! Enjoy your movie!\n", ticket.getPrice());
			return true;
		}
		else if (choice == 'N') {
			System.out.println("Transaction Cancelled! Returning you back to MOBLIMA main menu!");
			return false;
		}
		else {
			System.out.println("Please input either Y or N");
			this.authenticatePayment(ticket);
		}
	return false;
	}
}
