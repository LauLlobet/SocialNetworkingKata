package me.srodrigo.socialnetworkingkata.posts;

public class PastDateFormatter {

	private static final String ONE_SECOND_AGO = "1 second ago";
	private static final String N_SECONDS_AGO_FORMAT = "%d seconds ago";
	private static final String ONE_MINUTE_AGO = "1 minute ago";
	private static final String N_MINUTES_AGO_FORMAT = "%d minutes ago";

	private final Clock clock;

	public PastDateFormatter(Clock clock) {
		this.clock = clock;
	}

	public String format(long pastDate) {
		long now = clock.now();
		long millisElapsed = now - pastDate;

		boolean lessThanAMinute = millisElapsed < Clock.MILLIS_IN_MINUTE;
		if (lessThanAMinute) {
			int seconds = (int) (millisElapsed / Clock.MILLIS_IN_SECOND);
			return formatSeconds(seconds);
		}

		int minutes = (int) (millisElapsed / Clock.MILLIS_IN_MINUTE);
		return formatMinutes(minutes);
	}

	private String formatSeconds(int seconds) {
		if (seconds == 1) {
			return ONE_SECOND_AGO;
		}
		return String.format(N_SECONDS_AGO_FORMAT, seconds);
	}

	private String formatMinutes(int minutes) {
		if (minutes == 1) {
			return ONE_MINUTE_AGO;
		}
		return String.format(N_MINUTES_AGO_FORMAT, minutes);
	}
}
