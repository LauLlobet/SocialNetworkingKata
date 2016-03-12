package me.srodrigo.socialnetworkingkata.posts;

public class PastDateFormatter {

	private static final String ONE_MINUTE_AGO = "1 minute ago";
	private static final String N_MINUTES_AGO_FORMAT = "%d minutes ago";

	private final Clock clock;

	public PastDateFormatter(Clock clock) {
		this.clock = clock;
	}

	public String format(long pastDate) {
		long now = clock.now();
		long millisElapsed = now - pastDate;

		if (millisElapsed <= Clock.MILLIS_IN_MINUTE) {
			return ONE_MINUTE_AGO;
		}

		int minutes = (int) (millisElapsed / Clock.MILLIS_IN_MINUTE);
		return String.format(N_MINUTES_AGO_FORMAT, minutes);
	}
}
