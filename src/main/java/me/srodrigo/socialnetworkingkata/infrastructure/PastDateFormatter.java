package me.srodrigo.socialnetworkingkata.infrastructure;

public class PastDateFormatter {

	private static final String SINGULAR_FORMAT = "1 %s ago";
	private static final String PLURAL_FORMAT = "%d %ss ago";
	private static final String SECONDS_UNIT = "second";
	private static final String MINUTES_UNIT = "minute";

	private final Clock clock;

	public PastDateFormatter(Clock clock) {
		this.clock = clock;
	}

	public String format(long pastDate) {
		long timeElapsed = millisElapsed(pastDate);

		if (isLessThanOneMinute(timeElapsed)) {
			return formatInSeconds(timeElapsed);
		}
		return formatInMinutes(timeElapsed);
	}

	private long millisElapsed(long pastDate) {
		return clock.now() - pastDate;
	}

	private boolean isLessThanOneMinute(long milliseconds) {
		return milliseconds < Clock.MILLIS_IN_MINUTE;
	}

	private String formatInSeconds(long milliseconds) {
		return formatTime(milliseconds, SECONDS_UNIT, Clock.MILLIS_IN_SECOND);
	}

	private String formatInMinutes(long milliseconds) {
		return formatTime(milliseconds, MINUTES_UNIT, Clock.MILLIS_IN_MINUTE);
	}

	private String formatTime(long milliseconds, String unitText, int millisPerUnit) {
		int units = (int) (milliseconds / millisPerUnit);
		if (units == 1) {
			return String.format(SINGULAR_FORMAT, unitText);
		}
		return String.format(PLURAL_FORMAT, units, unitText);
	}

}
