package me.srodrigo.socialnetworkingkata.users;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class UsersRepository {

	private List<User> users = new ArrayList<>();

	public User findByUsername(String username) {
		return users.stream()
				.filter(user -> user.username().equals(username))
				.findFirst()
				.orElse(User.NULL);
	}

	public List<User> findAll() {
		return unmodifiableList(users);
	}

	public User create(String username) {
		User newUser = new User(username);
		users.add(newUser);
		return newUser;
	}

	public void addFollower(String follower, String followed) {
		users.stream()
				.filter(user -> user.username().equals(follower))
				.findFirst()
				.get()
				.addFollowed(followed);
	}
}
