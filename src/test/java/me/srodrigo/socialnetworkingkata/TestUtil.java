package me.srodrigo.socialnetworkingkata;

import me.srodrigo.socialnetworkingkata.infrastructure.Clock;
import me.srodrigo.socialnetworkingkata.model.posts.Post;
import me.srodrigo.socialnetworkingkata.model.users.User;

import java.util.List;

public class TestUtil {

	private static final long NOW = 1000000L;

	public static long now() {
		return NOW;
	}

	public static long secondsAgo(int seconds) {
		return NOW - seconds * Clock.MILLIS_IN_SECOND;
	}

	public static long minutesAgo(int minutes) {
		return NOW - minutes * Clock.MILLIS_IN_MINUTE;
	}

	public static long minutesLater(int minutes) {
		return NOW + minutes * Clock.MILLIS_IN_MINUTE;
	}

	public static Post post(String username, String message, long date) {
		return new Post(username, message, date);
	}

	public static User user(String username) {
		return new User(username);
	}

	public static User userFollowingUsers(String username, List<String> followedUsernames) {
		return new User(username, followedUsernames);
	}
}
