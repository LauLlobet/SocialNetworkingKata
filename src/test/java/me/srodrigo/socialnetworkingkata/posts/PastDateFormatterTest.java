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

	private PastDateFormatter pastDateFormatter;

	@Mock private Clock clock;

	@Before
	public void setUp() {
		pastDateFormatter = new PastDateFormatter();

		given(clock.now()).willReturn(NOW);
	}

	@Test public void
	parse_one_minute_ago() {
		String dateFormatted = pastDateFormatter.format(ONE_MINUTE_AGO);

		assertThat(dateFormatted, is("1 minute ago"));
	}
}
