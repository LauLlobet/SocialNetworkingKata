package me.srodrigo.socialnetworkingkata.model.users;

import me.srodrigo.socialnetworkingkata.infrastructure.Console;
import me.srodrigo.socialnetworkingkata.Prompt;
import me.srodrigo.socialnetworkingkata.infrastructure.Clock;
import me.srodrigo.socialnetworkingkata.infrastructure.PastDateFormatter;
import me.srodrigo.socialnetworkingkata.model.posts.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static me.srodrigo.socialnetworkingkata.TestUtil.minutesAgo;
import static me.srodrigo.socialnetworkingkata.TestUtil.now;
import static me.srodrigo.socialnetworkingkata.TestUtil.secondsAgo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class UsersWallFeature {

	private Prompt prompt;

	@Mock private Console console;
	@Mock private Clock clock;

	@Before
	public void setUp() {
		UsersRepository usersRepository = new UsersRepository();
		PostsRepository postsRepository = new PostsRepository(clock);
		PastDateFormatter pastDateFormatter = new PastDateFormatter(clock);
		TimelinePostFormatter timelinePostFormatter = new TimelinePostFormatter(pastDateFormatter);
		PostsPrinter timelinePrinter = new PostsPrinter(timelinePostFormatter, console);
		PostFormatter wallPostFormatter = new WallPostFormatter(pastDateFormatter);
		PostsPrinter wallPrinter = new PostsPrinter(wallPostFormatter, console);
		UserService userService = new UserService(usersRepository, postsRepository, timelinePrinter, wallPrinter);
		prompt = new Prompt(userService);
	}

	@Test public void
	show_user_wall_containing_all_subscriptions() {
		// Publish posts
		given(clock.now()).willReturn(minutesAgo(5), minutesAgo(2), minutesAgo(1), secondsAgo(15));

		prompt.readCommand("Alice -> I love the weather today");
		prompt.readCommand("Bob -> Damn! We lost!");
		prompt.readCommand("Bob -> Good game though.");
		prompt.readCommand("Charlie -> I'm in New York today! Anyone wants to have a coffee?");

		// Follow users
		prompt.readCommand("Charlie follows Alice");
		prompt.readCommand("Charlie follows Bob");

		// Show wall
		given(clock.now()).willReturn(now(), now(), now(), now());

		prompt.readCommand("Charlie wall");

		InOrder inOrder = inOrder(console);
		inOrder.verify(console).printLine("Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)");
		inOrder.verify(console).printLine("Bob - Good game though. (1 minute ago)");
		inOrder.verify(console).printLine("Bob - Damn! We lost! (2 minutes ago)");
		inOrder.verify(console).printLine("Alice - I love the weather today (5 minutes ago)");
	}

}
