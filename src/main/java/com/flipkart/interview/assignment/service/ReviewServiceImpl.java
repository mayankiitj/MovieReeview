package com.flipkart.interview.assignment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.flipkart.interview.assignment.db.DataStorage;
import com.flipkart.interview.assignment.entity.Movie;
import com.flipkart.interview.assignment.entity.Review;
import com.flipkart.interview.assignment.entity.User;
import com.flipkart.interview.assignment.helper.ReviewSort;

public class ReviewServiceImpl {
	public boolean addReview(String name, String movie, int rating) {
		if (name == null || movie == null) {
			System.out.println("Input is not correct");
			return false;
		}
		if (rating > 10 || rating < 0) {
			System.out.println("Rating is not in range");
			return false;
		}
		for (Review u : DataStorage.REVIEW) {
			if (u.getUser().equals(name) && u.getTitle().equals(movie)) {
				System.out.println("review is already given by User for movie " + movie);
				return false;
			}
		}
		DataStorage.REVIEW.add(new Review(name, movie, rating));

		updateReviewMaps(new Review(name, movie, rating));
		// convertToCritic()
		return true;
	}

	private void updateReviewMaps(Review review) {
		boolean isCritics = false;
		User u = null;
		for (User user : DataStorage.USERS) {
			if (user.getUserName().equals(review.getUser())) {
				u = user;
				if (user.getUserType() == "critic") {
					isCritics = true;
					break;
				}
			}
		}
		if (u == null) {
			System.out.println("Not a valid user");
			return;
		} else {
			if (DataStorage.USER_REVIEW.get(u) == null) {
				List<Review> list = new ArrayList<Review>();
				list.add(review);
				DataStorage.USER_REVIEW.put(u, list);
			} else {
				List<Review> list = DataStorage.USER_REVIEW.get(u);
				list.add(review);
				DataStorage.USER_REVIEW.put(u, list);
			}
		}

		Movie m = null;
		for (Movie movie : DataStorage.MOVIES) {
			if (movie.getTitle().equals(review.getTitle())) {
				m = movie;
				break;
			}
		}

		if (m == null) {
			System.out.println("Not a valid movie");
		} else {
			// Year wise sort
			if (DataStorage.YEAR_REVIEW_MAP.get(m.getYear()) == null) {
				Movie m1 = new Movie(m.getTitle(), m.getYear(), m.getGenre());
				SortedSet<Movie> s = new TreeSet<Movie>(new ReviewSort());
				if (isCritics)
					m1.setTotalRating(2 * review.getRating());
				else
					m1.setTotalRating(review.getRating());
				s.add(m1);
				DataStorage.YEAR_REVIEW_MAP.put(m.getYear(), s);
			} else {
				SortedSet<Movie> s = DataStorage.YEAR_REVIEW_MAP.get(m.getYear());
				Movie m2 = null;
				for (Movie m1 : s) {
					if (m1.getTitle().equals(m.getTitle())) {
						m2 = new Movie(m.getTitle(), m.getYear(), m.getGenre());
						m2.setTotalRating(m1.getTotalRating());
						s.remove(m1);
						break;
					}
				}
				if (m2 != null) {
					if (isCritics)
						m2.setTotalRating(m2.getTotalRating() + 2 * review.getRating());
					else
						m2.setTotalRating(m2.getTotalRating() + review.getRating());
					s.add(m2);
					DataStorage.YEAR_REVIEW_MAP.put(m.getYear(), s);
				} else {
					if (isCritics)
						m.setTotalRating(2 * review.getRating());
					else
						m.setTotalRating(review.getRating());
					s.add(m);
					DataStorage.YEAR_REVIEW_MAP.put(m.getYear(), s);
				}

			}

			// Genre wise sort
			for (String gen : m.getGenre()) {
				if (DataStorage.GENRE_REVIEW_MAP.get(gen) == null) {
					SortedSet<Movie> s = new TreeSet<Movie>(new ReviewSort());
					if (isCritics)
						m.setTotalRating(2 * review.getRating());
					else
						m.setTotalRating(review.getRating());
					s.add(m);
					DataStorage.GENRE_REVIEW_MAP.put(gen, s);
				} else {
					SortedSet<Movie> s = DataStorage.GENRE_REVIEW_MAP.get(gen);
					Movie m2 = null;
					for (Movie m1 : s) {
						if (m1.getTitle().equals(m.getTitle())) {
							m2 = new Movie(m.getTitle(), m.getYear(), m.getGenre());
							m2.setTotalRating(m1.getTotalRating());
							break;
						}
					}
					if (m2 != null) {
						if (isCritics)
							m2.setTotalRating(m2.getTotalRating() + 2 * review.getRating());
						else
							m2.setTotalRating(m2.getTotalRating() + review.getRating());
						s.add(m2);
						DataStorage.GENRE_REVIEW_MAP.put(gen, s);
					} else {
						if (isCritics)
							m.setTotalRating(2 * review.getRating());
						else
							m.setTotalRating(review.getRating());
						s.add(m);
						DataStorage.GENRE_REVIEW_MAP.put(gen, s);
					}
				}
			}

			if (isCritics) {
				if (DataStorage.CRITIC_YEAR_REVIEW_MAP.get(m.getYear()) == null) {
					Movie m1 = new Movie(m.getTitle(), m.getYear(), m.getGenre());
					SortedSet<Movie> s = new TreeSet<Movie>(new ReviewSort());
					if (isCritics)
						m1.setTotalRating(2 * review.getRating());
					else
						m1.setTotalRating(review.getRating());
					s.add(m1);
				} else {
					SortedSet<Movie> s = DataStorage.CRITIC_YEAR_REVIEW_MAP.get(m.getYear());
					Movie m2 = null;
					for (Movie m1 : s) {
						if (m1.getTitle().equals(m.getTitle())) {
							m2 = new Movie(m.getTitle(), m.getYear(), m.getGenre());
							m2.setTotalRating(m1.getTotalRating());
							break;
						}
					}
					if (m2 != null) {
						if (isCritics)
							m2.setTotalRating(m2.getTotalRating() + 2 * review.getRating());
						else
							m2.setTotalRating(m2.getTotalRating() + review.getRating());
						s.add(m2);
						DataStorage.CRITIC_YEAR_REVIEW_MAP.put(m.getYear(), s);
					} else {
						if (isCritics)
							m.setTotalRating(2 * review.getRating());
						else
							m.setTotalRating(review.getRating());
						s.add(m);
						DataStorage.CRITIC_YEAR_REVIEW_MAP.put(m.getYear(), s);
					}

				}

				// Genre wise sort
				for (String gen : m.getGenre()) {
					if (DataStorage.CRITIC_GENRE_REVIEW_MAP.get(gen) == null) {
						SortedSet<Movie> s = new TreeSet<Movie>(new ReviewSort());
						if (isCritics)
							m.setTotalRating(2 * review.getRating());
						else
							m.setTotalRating(review.getRating());
						s.add(m);
					} else {
						SortedSet<Movie> s = DataStorage.CRITIC_GENRE_REVIEW_MAP.get(gen);
						Movie m2 = null;
						for (Movie m1 : s) {
							if (m1.getTitle().equals(m.getTitle())) {
								m2 = new Movie(m.getTitle(), m.getYear(), m.getGenre());
								m2.setTotalRating(m1.getTotalRating());
								break;
							}
						}
						if (m2 != null) {
							if (isCritics)
								m2.setTotalRating(m2.getTotalRating() + 2 * review.getRating());
							else
								m2.setTotalRating(m2.getTotalRating() + review.getRating());
							s.add(m2);
							DataStorage.CRITIC_GENRE_REVIEW_MAP.put(gen, s);
						} else {
							if (isCritics)
								m.setTotalRating(2 * review.getRating());
							else
								m.setTotalRating(review.getRating());
							s.add(m);
							DataStorage.CRITIC_GENRE_REVIEW_MAP.put(gen, s);
						}
					}
				}

			}
		}

	}

	public void getReviewByYear(int year, int n) {
		int count = 0;
		if (DataStorage.YEAR_REVIEW_MAP.get(year) != null) {
			SortedSet<Movie> m = DataStorage.YEAR_REVIEW_MAP.get(year);
			for (Movie mov : m) {
				if (count >= n)
					break;
				System.out.println(mov.getTitle() + mov.getTotalRating());
				count++;
			}
		}
	}

	public void getReviewByGenre(String year, int n) {
		int count = 0;
		if (DataStorage.GENRE_REVIEW_MAP.get(year) != null) {
			SortedSet<Movie> m = DataStorage.GENRE_REVIEW_MAP.get(year);
			for (Movie mov : m) {
				if (count >= n)
					break;
				System.out.println(mov.getTitle() + mov.getTotalRating());
				count++;
			}
		}
	}

	public void getReviewByYearCritics(int year, int n) {
		int count = 0;
		if (DataStorage.CRITIC_YEAR_REVIEW_MAP.get(year) != null) {
			SortedSet<Movie> m = DataStorage.CRITIC_YEAR_REVIEW_MAP.get(year);
			for (Movie mov : m) {
				if (count >= n)
					break;
				System.out.println(mov.getTitle() + mov.getTotalRating());
				count++;
			}
		}
	}

	public void getReviewByGenreCritics(String year, int n) {
		int count = 0;
		if (DataStorage.CRITIC_GENRE_REVIEW_MAP.get(year) != null) {
			SortedSet<Movie> m = DataStorage.CRITIC_GENRE_REVIEW_MAP.get(year);
			for (Movie mov : m) {
				if (count >= n)
					break;
				System.out.println(mov.getTitle() + " " + mov.getTotalRating());
				count++;
			}
		}
	}
}
