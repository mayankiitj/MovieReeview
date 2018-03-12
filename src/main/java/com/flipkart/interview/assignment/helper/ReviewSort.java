package com.flipkart.interview.assignment.helper;

import java.util.Comparator;

import com.flipkart.interview.assignment.entity.Movie;

public class ReviewSort implements Comparator<Movie> {

	@Override
	public int compare(Movie e1, Movie e2) {
		if (e1.getTotalRating() > e2.getTotalRating()) {
			return -1;
		} else {
			return 1;
		}
	}
}
