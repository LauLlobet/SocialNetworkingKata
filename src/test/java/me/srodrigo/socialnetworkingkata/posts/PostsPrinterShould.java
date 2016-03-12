package me.srodrigo.socialnetworkingkata.posts;

import me.srodrigo.socialnetworkingkata.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class PostsPrinterShould {

	private static final int MILLIS_IN_MINUTE = 60000;
	private static final long NOW = 1000000L;
	private static final long ONE_MINUTE_LATER = NOW + MILLIS_IN_MINUTE;
	private static final long TWO_MINUTES_LATER = NOW + 2 * MILLIS_IN_MINUTE;

	private static final String USERNAME = "username";
	private static final Post FIRST_POST = post(USERNAME, "Damn! We lost!", NOW);
	private static final String FIRST_POST_FORMATTED = "Damn! We lost! (2 minutes ago)";
	private static final Post SECOND_POST = post(USERNAME, "Good game though.", ONE_MINUTE_LATER);
	private static final String SECOND_POST_FORMATTED = "Good game though. (1 minute ago)";

	private PostsPrinter postsPrinter;

	@Mock private Console console;
	@Mock private PostFormatter postFormatter;
	@Mock private Clock clock;

	@Before
	public void setUp() {
		postsPrinter = new PostsPrinter(postFormatter, console);
	}

	@Test public void
	print_posts_in_reverse_chronological_order() {
		given(clock.now()).willReturn(NOW, ONE_MINUTE_LATER);

		given(postFormatter.format(FIRST_POST)).willReturn(FIRST_POST_FORMATTED);
		given(postFormatter.format(SECOND_POST)).willReturn(SECOND_POST_FORMATTED);

		given(clock.now()).willReturn(TWO_MINUTES_LATER);

		List<Post> posts = asList(FIRST_POST, SECOND_POST);
		postsPrinter.print(posts);

		InOrder inOrder = inOrder(console);
		inOrder.verify(console).printLine(SECOND_POST_FORMATTED);
		inOrder.verify(console).printLine(FIRST_POST_FORMATTED);
	}

	private static Post post(String username, String message, long date) {
		return new Post(username, message, date);
	}

}
