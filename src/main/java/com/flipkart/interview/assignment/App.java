package com.flipkart.interview.assignment;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.interview.assignment.db.DataStorage;
import com.flipkart.interview.assignment.service.MovieServiceImpl;
import com.flipkart.interview.assignment.service.ReviewServiceImpl;
import com.flipkart.interview.assignment.service.UserServiceImpl;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		MovieServiceImpl mov = new MovieServiceImpl();
		UserServiceImpl user = new UserServiceImpl();
		ReviewServiceImpl rev = new ReviewServiceImpl();
		List<String> list = new ArrayList<String>();
		list.add("Action");
		list.add("Comedy");
		mov.addMovie("Don", 2006, list);
		List list2 = new ArrayList<>();
		list2.add("Action");
		mov.addMovie("Pardesh", 2006, list2);
		user.addUser("SRK");
		user.addUser("Salmaan");
		System.out.println(DataStorage.MOVIES.get(0).getTitle());
		System.out.println(DataStorage.USERS.get(0).getUserName());
		rev.addReview("SRK", "Don", 2);
		rev.addReview("Salmaan", "Don", 5);
		rev.addReview("SRK", "Pardesh", 5);
		rev.getReviewByGenre("Comedy",1);
	}
}
