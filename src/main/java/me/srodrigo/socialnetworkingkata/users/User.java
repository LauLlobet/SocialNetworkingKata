package me.srodrigo.socialnetworkingkata.users;

public class User {
	public static User NULL = new User("");

	private String username;

	public User(String username) {
		this.username = username;
	}

	public String username() {
		return username;
	}

	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		return username != null ? username.equals(user.username) : user.username == null;

	}

	@Override public int hashCode() {
		return username != null ? username.hashCode() : 0;
	}
}
