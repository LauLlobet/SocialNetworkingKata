package me.srodrigo.socialnetworkingkata.model.users;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.*;

public class User {

	public static User NULL = new User("");

	private final String username;
	private final List<String> followedUsernames;

	public User(String username) {
		this(username, new ArrayList<>());
	}

	public User(String username, List<String> followedUsernames) {
		this.username = username;
		this.followedUsernames = new ArrayList<>(followedUsernames);
	}

	public void addFollowed(String followed) {
		if (!followedUsernames.contains(followed)) {
			followedUsernames.add(followed);
		}
	}

	public String username() {
		return username;
	}

	public List<String> followedUsernames() {
		return unmodifiableList(followedUsernames);
	}

	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (username != null ? !username.equals(user.username) : user.username != null) return false;
		return followedUsernames != null ? followedUsernames.equals(user.followedUsernames) : user.followedUsernames == null;

	}

	@Override public int hashCode() {
		int result = username != null ? username.hashCode() : 0;
		result = 31 * result + (followedUsernames != null ? followedUsernames.hashCode() : 0);
		return result;
	}
}
