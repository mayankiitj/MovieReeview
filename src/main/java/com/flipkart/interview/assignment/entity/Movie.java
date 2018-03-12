package com.flipkart.interview.assignment.entity;

import java.util.List;

public class Movie {
	private String title;
	private int year;
	private List<String> genre;
	private int totalRating;

	public Movie(String title, int year, List<String> genre) {
		super();
		this.title = title;
		this.year = year;
		this.genre = genre;
	}

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<String> getGenre() {
		return genre;
	}

	public void setGenre(List<String> genre) {
		this.genre = genre;
	}

	public int getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(int totalRating) {
		this.totalRating = totalRating;
	}

	public boolean equals(Object obj) {
		Movie m = (Movie) obj;
		return (this.getTitle().equals(m.getTitle()) && this.getYear() == m.getYear());
	}
}
