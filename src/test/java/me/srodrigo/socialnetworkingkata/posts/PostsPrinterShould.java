package me.srodrigo.socialnetworkingkata.posts;

import me.srodrigo.socialnetworkingkata.infrastructure.Console;
import me.srodrigo.socialnetworkingkata.infrastructure.Clock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static me.srodrigo.socialnetworkingkata.TestUtil.minutesLater;
import static me.srodrigo.socialnetworkingkata.TestUtil.now;
import static me.srodrigo.socialnetworkingkata.TestUtil.post;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class PostsPrinterShould {

	private static final String USERNAME = "username";
	private static final Post FIRST_POST = post(USERNAME, "Damn! We lost!", now());
	private static final String FIRST_POST_FORMATTED = "Damn! We lost! (2 minutes ago)";
	private static final Post SECOND_POST = post(USERNAME, "Good game though.", minutesLater(1));
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
		given(clock.now()).willReturn(now(), minutesLater(1));

		given(postFormatter.format(FIRST_POST)).willReturn(FIRST_POST_FORMATTED);
		given(postFormatter.format(SECOND_POST)).willReturn(SECOND_POST_FORMATTED);

		given(clock.now()).willReturn(minutesLater(2));

		List<Post> posts = asList(FIRST_POST, SECOND_POST);
		postsPrinter.print(posts);

		InOrder inOrder = inOrder(console);
		inOrder.verify(console).printLine(SECOND_POST_FORMATTED);
		inOrder.verify(console).printLine(FIRST_POST_FORMATTED);
	}

}
