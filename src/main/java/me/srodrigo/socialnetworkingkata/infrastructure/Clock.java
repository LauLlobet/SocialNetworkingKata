package me.srodrigo.socialnetworkingkata.infrastructure;

public class Clock {

	public static final int MILLIS_IN_SECOND = 1000;
	public static final int MILLIS_IN_MINUTE = 60 * MILLIS_IN_SECOND;

	public long now() {
		return System.currentTimeMillis();
	}
}
