package me.srodrigo.socialnetworkingkata.users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsersRepository {

	private List<User> users = new ArrayList<>();

	public User findOrCreate(String username) {
		throw new UnsupportedOperationException();
	}

	public List<User> findAll() {
		return Collections.unmodifiableList(users);
	}
}
