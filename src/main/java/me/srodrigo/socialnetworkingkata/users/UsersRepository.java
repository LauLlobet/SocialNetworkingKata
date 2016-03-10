package me.srodrigo.socialnetworkingkata.users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsersRepository {

	private List<User> users = new ArrayList<>();

	public User findByUsername(String username) {
		throw new UnsupportedOperationException();
	}

	public List<User> findAll() {
		return Collections.unmodifiableList(users);
	}

	public User create(String username) {
		throw new UnsupportedOperationException();
	}
}
