package me.srodrigo.socialnetworkingkata.users;

import java.util.ArrayList;
import java.util.List;

public class User {
	public static User NULL = new User("");

	private final String username;
	private final List<String> followersUsernames;

	public User(String username) {
		this(username, new ArrayList<>());
	}

	public User(String username, List<String> followersUsernames) {

		this.username = username;
		this.followersUsernames = followersUsernames;
	}

	public String username() {
		return username;
	}

	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (username != null ? !username.equals(user.username) : user.username != null) return false;
		return followersUsernames != null ? followersUsernames.equals(user.followersUsernames) : user.followersUsernames == null;

	}

	@Override public int hashCode() {
		int result = username != null ? username.hashCode() : 0;
		result = 31 * result + (followersUsernames != null ? followersUsernames.hashCode() : 0);
		return result;
	}
}
