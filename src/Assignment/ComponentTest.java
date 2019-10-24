//package Assignment;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class ComponentTest {
//
//	public static void main(String[] args) {
//		test_movieticket_getandset_price();
//
//	}
//	
//	public static void test_movieticket_getandset_price() {
//	ArrayList<String> showtimes = new ArrayList<String>();
//	showtimes.add("0130");
//	Cinema cinema = new Cinema("3D", "101", showtimes);
//	ArrayList<String> cast = new ArrayList<String>();
//	cast.add("Tom Cruise");
//	Movie movie = new Movie("title", "status", "synopsis", "director", "type", cast);
//	MovieTicket ticket = new MovieTicket(movie, cinema, "0130");
//	ticket.setPrice();
//	float price = ticket.getPrice();
//}
//
//public static void test_cinema_book_and_view() {
//	ArrayList<String> showtimes = new ArrayList<String>();
//	showtimes.add("0130");
//	ArrayList<String> cast = new ArrayList<String>();
//	cast.add("Tom Cruise");
//	Movie movie = new Movie("title", "status", "synopsis", "director", "type", cast);
//	Cinema cinema = new Cinema("Normal", "101", showtimes);
//	for (int i=0; i<10; i++) {
//		cinema.bookSeats("0130", movie);
//	}
//}
//
//public static void test_movie_and_review(){
//	Scanner scan = new Scanner(System.in);
//	System.out.println("What is the name of movie?");
//	String title = scan.nextLine();
//	System.out.println("What is the status of movie?");
//	String status = scan.nextLine();
//	System.out.println("What is the synopsis of the movie");
//	String synopsis = scan.nextLine();
//	System.out.println("What is the director of the movie");
//	String director = scan.nextLine();
//	System.out.println("What is the type of the movie");
//	String type = scan.nextLine();
//	
//	System.out.println("What is the number of cast members of the movie");
//	int numCast = scan.nextInt();
//	scan.nextLine();; // remove the space left
//	ArrayList <String> cast = new ArrayList <String>();
//	for (int i=0; i<numCast; i++) {
//		System.out.println("Who is cast member " + (i+1) + "?");
//		cast.add(scan.nextLine());
//	}
//	
//	Movie movie = new Movie(title, status, synopsis, director, type, cast);
//	
//	while (1==1) {
//		System.out.println("1. Add Review");
//		System.out.println("2. Quit");
//		System.out.println("Choice:");
//		int choice = scan.nextInt();
//		scan.nextLine();; // remove the space left
//		if (choice == 1) {
//			System.out.println("Comment:");
//			String comment = scan.nextLine();
//			System.out.println("Rating:");
//			int rating = scan.nextInt();
//			Review review = new Review(comment, rating, "123", "456", movie.title);
//			movie.addReview(review);
//			movie.printReviews();
//		}else {
//			break;
//		}
//	}
//}
//}
