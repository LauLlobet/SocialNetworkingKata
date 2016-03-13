package me.srodrigo.socialnetworkingkata.posts;

import me.srodrigo.socialnetworkingkata.infrastructure.PastDateFormatter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static me.srodrigo.socialnetworkingkata.TestUtil.minutesAgo;
import static me.srodrigo.socialnetworkingkata.TestUtil.post;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TimelinePostFormatterShould {

	private static final String USERNAME = "Bob";

	private TimelinePostFormatter timelinePostFormatter;

	@Mock private PastDateFormatter pastDateFormatter;

	@Before
	public void setUp() {
		timelinePostFormatter = new TimelinePostFormatter(pastDateFormatter);
	}

	@Test public void
	format_the_post_using_message_and_date_in_text() {
		given(pastDateFormatter.format(minutesAgo(2))).willReturn("2 minutes ago");

		Post post = post(USERNAME, "Damn! We lost!", minutesAgo(2));
		String postFormatted = timelinePostFormatter.format(post);

		assertThat(postFormatted, is("Damn! We lost! (2 minutes ago)"));
	}

}
