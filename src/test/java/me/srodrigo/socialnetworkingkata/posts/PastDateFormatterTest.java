package me.srodrigo.socialnetworkingkata.posts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static me.srodrigo.socialnetworkingkata.TimeTestUtil.minutesAgo;
import static me.srodrigo.socialnetworkingkata.TimeTestUtil.now;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PastDateFormatterTest {

	private PastDateFormatter pastDateFormatter;

	@Mock private Clock clock;

	@Before
	public void setUp() {
		pastDateFormatter = new PastDateFormatter(clock);

		given(clock.now()).willReturn(now());
	}

	@Test public void
	format_one_minute_ago() {
		String dateFormatted = pastDateFormatter.format(minutesAgo(1));

		assertThat(dateFormatted, is("1 minute ago"));
	}

	@Test public void
	format_five_minutes_ago() {
		String dateFormatted = pastDateFormatter.format(minutesAgo(5));

		assertThat(dateFormatted, is("5 minutes ago"));
	}

	@Test public void
	format_five_minutes_ago_minus_one_millisecond() {
		String dateFormatted = pastDateFormatter.format(minutesAgo(5) + 1);

		assertThat(dateFormatted, is("4 minutes ago"));
	}
}
