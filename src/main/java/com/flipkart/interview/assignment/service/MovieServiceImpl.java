package com.flipkart.interview.assignment.service;

import java.util.List;

import com.flipkart.interview.assignment.db.DataStorage;
import com.flipkart.interview.assignment.entity.Movie;

public class MovieServiceImpl {

	public boolean addMovie(String title, int year, List<String> genre) {
		try {
			if (title == null || year <= 0) {
				return false;
			}
			Movie movie = new Movie(title, year, genre);
			for (Movie m : DataStorage.MOVIES) {
				if (m.getTitle().equals(title) && m.getYear() == year) {
					System.out.println("Movie is already present in database");
					return false;
				}
			}
			DataStorage.MOVIES.add(movie);
		} catch (Exception e) {
			System.out.println("Movie attributes are not correct");
			e.printStackTrace();
		}
		return true;
	}
}
