package me.srodrigo.socialnetworkingkata.posts;

public class Post {

	private final String username;
	private final String message;

	public Post(String username, String message) {
		this.username = username;
		this.message = message;
	}

	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Post post = (Post) o;

		if (username != null ? !username.equals(post.username) : post.username != null) return false;
		return message != null ? message.equals(post.message) : post.message == null;

	}

	@Override public int hashCode() {
		int result = username != null ? username.hashCode() : 0;
		result = 31 * result + (message != null ? message.hashCode() : 0);
		return result;
	}
}
