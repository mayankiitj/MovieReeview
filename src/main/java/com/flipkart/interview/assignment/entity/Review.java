package com.flipkart.interview.assignment.entity;

public class Review {
	private String user;
	private String title;
	private int rating;

	public Review(String user, String title, int rating) {
		super();
		this.user = user;
		this.title = title;
		this.rating = rating;
	}

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
