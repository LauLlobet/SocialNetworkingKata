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
		long millisElapsed = millisElapsed(pastDate);

		if (isLessThanOneMinute(millisElapsed)) {
			return formatInSeconds(millisElapsed);
		}
		return formatInMinutes(millisElapsed);
	}

	private long millisElapsed(long pastDate) {
		return clock.now() - pastDate;
	}

	private boolean isLessThanOneMinute(long millisElapsed) {
		return millisElapsed < Clock.MILLIS_IN_MINUTE;
	}

	private String formatInSeconds(long millisElapsed) {
		return formatTimeElapsed(millisElapsed, SECONDS_UNIT, Clock.MILLIS_IN_SECOND);
	}

	private String formatInMinutes(long millisElapsed) {
		return formatTimeElapsed(millisElapsed, MINUTES_UNIT, Clock.MILLIS_IN_MINUTE);
	}

	private String formatTimeElapsed(long millisElapsed, String unitText, int millisPerUnit) {
		int units = (int) (millisElapsed / millisPerUnit);
		if (units == 1) {
			return String.format(SINGULAR_FORMAT, unitText);
		}
		return String.format(PLURAL_FORMAT, units, unitText);
	}

}
