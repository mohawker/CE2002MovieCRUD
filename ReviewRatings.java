package movieAssignment;

import java.util.ArrayList;

public class ReviewRatings extends Movie{
	
	public String title;
	//public ArrayList <Review> reviews;
	public ArrayList <String> reivewList;
	public ArrayList <Float> ratingList;
	
	
	public ReviewRatings() {
		super();
		this.title = super.title;
	}

	
	
	public void addReviewList(String comment) {
		reivewList.add(comment);
	}
	
	public void addRatings(float rating) {
		ratingList.add(rating);
	}
	
	
}
