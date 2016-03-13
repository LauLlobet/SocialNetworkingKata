package me.srodrigo.socialnetworkingkata.posts;

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
public class WallPostFormatterShould {

	private WallPostFormatter wallPostFormatter;

	@Mock private PastDateFormatter pastDateFormatter;

	@Before
	public void setUp() {
		wallPostFormatter = new WallPostFormatter(pastDateFormatter);
	}

	@Test public void
	format_a_post_message_using_username_message_and_date_in_text() {
		given(pastDateFormatter.format(minutesAgo(2))).willReturn("2 minutes ago");

		Post post = post("Bob", "Damn! We lost!", minutesAgo(2));
		String postFormatted = wallPostFormatter.format(post);

		assertThat(postFormatted, is("Bob - Damn! We lost! (2 minutes ago)"));
	}
}
