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
public class TimelinePostFormatterShould {

	private static final int MILLIS_IN_MINUTE = 60000;
	private static final long NOW = 1000000L;
	private static final long TWO_MINUTES_AGO = NOW - 2 * MILLIS_IN_MINUTE;
	private static final String USERNAME = "Bob";

	private TimelinePostFormatter timelinePostFormatter;

	@Mock private PastDateFormatter pastDateFormatter;

	@Before
	public void setUp() {
		timelinePostFormatter = new TimelinePostFormatter();
	}

	@Test public void
	format_the_post_using_message_and_date_in_text() {
		given(pastDateFormatter.format(TWO_MINUTES_AGO)).willReturn("2 minutes ago");

		Post post = post(USERNAME, "Damn! We lost!", TWO_MINUTES_AGO);
		String postFormatted = timelinePostFormatter.format(post);

		assertThat(postFormatted, is("Damn! We lost! (2 minutes ago)"));
	}

	private static Post post(String username, String message, long date) {
		return new Post(username, message, date);
	}

}
