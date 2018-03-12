package com.flipkart.interview.assignment.service;

import com.flipkart.interview.assignment.db.DataStorage;
import com.flipkart.interview.assignment.entity.User;

public class UserServiceImpl {
	public boolean addUser(String name) {
		if (name == null) {
			return false;
		}
		for (User u : DataStorage.USERS) {
			if (u.getUserName().equals(name)) {
				System.out.println("User is already present in database");
				return false;
			}
		}
		DataStorage.USERS.add(new User(name, "viewer"));
		return true;
	}

	public boolean convertToCritic(User user) {
		if (DataStorage.USER_REVIEW.get(user) != null && DataStorage.USER_REVIEW.get(user).size() >= 3) {
			User newUser = new User(user.getUserName(), "critic");
			DataStorage.USERS.remove(user);
			DataStorage.USERS.add(newUser);
		}

		return true;
	}
}
