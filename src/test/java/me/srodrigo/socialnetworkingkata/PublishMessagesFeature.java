package me.srodrigo.socialnetworkingkata;

import me.srodrigo.socialnetworkingkata.posts.*;
import me.srodrigo.socialnetworkingkata.users.UserService;
import me.srodrigo.socialnetworkingkata.users.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class PublishMessagesFeature {

	private static final long NOW = 1000000L;
	private static final long ONE_MINUTE_AGO = NOW - Clock.MILLIS_IN_MINUTE;
	private static final long TWO_MINUTES_AGO = NOW - 2 * Clock.MILLIS_IN_MINUTE;
	private static final long FIVE_MINUTES_AGO = NOW - 5 * Clock.MILLIS_IN_MINUTE;

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
		UserService userService = new UserService(usersRepository, postsRepository, timelinePrinter);
		CommandsProcessor commandsProcessor = new CommandsProcessor(userService);
		prompt = new Prompt(commandsProcessor);
	}

	@Test public void
	read_published_messages_by_users() {
		given(clock.now()).willReturn(
				// Create posts
				FIVE_MINUTES_AGO, TWO_MINUTES_AGO, ONE_MINUTE_AGO,
				// Show posts
				NOW, NOW, NOW);

		prompt.readCommand("Alice -> I love the weather today");
		prompt.readCommand("Bob -> Damn! We lost!");
		prompt.readCommand("Bob -> Good game though.");

		prompt.readCommand("Alice");

		InOrder inOrder = inOrder(console);
		inOrder.verify(console).printLine("I love the weather today (5 minutes ago)");

		prompt.readCommand("Bob");

		inOrder.verify(console).printLine("Good game though. (1 minute ago)");
		inOrder.verify(console).printLine("Damn! We lost! (2 minutes ago)");
	}

}
