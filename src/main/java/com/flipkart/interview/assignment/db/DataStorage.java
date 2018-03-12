package com.flipkart.interview.assignment.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import com.flipkart.interview.assignment.entity.Movie;
import com.flipkart.interview.assignment.entity.Review;
import com.flipkart.interview.assignment.entity.User;

public class DataStorage {

	public static List<Movie> MOVIES = new ArrayList<Movie>();
	public static List<User> USERS = new ArrayList<User>();
	public static List<Review> REVIEW = new ArrayList<Review>();
	public static Map<User, List<Review>> USER_REVIEW = new HashMap<User, List<Review>>();
	public static Map<Integer, SortedSet<Movie>> YEAR_REVIEW_MAP = new HashMap<Integer, SortedSet<Movie>>();
	public static Map<String, SortedSet<Movie>> GENRE_REVIEW_MAP = new HashMap<String, SortedSet<Movie>>();
	public static Map<Integer, SortedSet<Movie>> CRITIC_YEAR_REVIEW_MAP = new HashMap<Integer, SortedSet<Movie>>();
	public static Map<String, SortedSet<Movie>> CRITIC_GENRE_REVIEW_MAP = new HashMap<String, SortedSet<Movie>>();

}

