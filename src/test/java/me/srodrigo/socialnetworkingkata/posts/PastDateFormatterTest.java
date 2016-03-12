package me.srodrigo.socialnetworkingkata.posts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PastDateFormatterTest {

	private static final long NOW = 1000000L;
	private static final long ONE_MINUTE_AGO = NOW - Clock.MILLIS_IN_MINUTE;
	private static final long FIVE_MINUTES_AGO = NOW - 5 * Clock.MILLIS_IN_MINUTE;

	private PastDateFormatter pastDateFormatter;

	@Mock private Clock clock;

	@Before
	public void setUp() {
		pastDateFormatter = new PastDateFormatter(clock);

		given(clock.now()).willReturn(NOW);
	}

	@Test public void
	format_one_minute_ago() {
		String dateFormatted = pastDateFormatter.format(ONE_MINUTE_AGO);

		assertThat(dateFormatted, is("1 minute ago"));
	}

	@Test public void
	format_five_minutes_ago() {
		String dateFormatted = pastDateFormatter.format(FIVE_MINUTES_AGO);

		assertThat(dateFormatted, is("5 minutes ago"));
	}

	@Test public void
	format_five_minutes_ago_minus_one_millisecond() {
		String dateFormatted = pastDateFormatter.format(FIVE_MINUTES_AGO + 1);

		assertThat(dateFormatted, is("4 minutes ago"));
	}
}
