package me.srodrigo.socialnetworkingkata;

import me.srodrigo.socialnetworkingkata.posts.Clock;

public class TimeTestUtil {

	private static final long NOW = 1000000L;

	public static long now() {
		return NOW;
	}

	public static long minutesAgo(int minutes) {
		return NOW - minutes * Clock.MILLIS_IN_MINUTE;
	}

	public static long minutesLater(int minutes) {
		return NOW + minutes * Clock.MILLIS_IN_MINUTE;
	}
}
