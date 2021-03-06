package me.srodrigo.socialnetworkingkata.model.users;

import me.srodrigo.socialnetworkingkata.Prompt;
import me.srodrigo.socialnetworkingkata.PromptFactory;
import me.srodrigo.socialnetworkingkata.infrastructure.Clock;
import me.srodrigo.socialnetworkingkata.infrastructure.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static me.srodrigo.socialnetworkingkata.TestUtil.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class UsersWallFeature {

	private Prompt prompt;

	@Mock private Console console;
	@Mock private Clock clock;

	@Before
	public void setUp() {
		prompt = new PromptFactory().createPrompt(console, clock);
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
