package me.srodrigo.socialnetworkingkata;

import me.srodrigo.socialnetworkingkata.posts.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class PublishMessagesFeature {

	private Prompt prompt;

	@Mock private Console console;

	@Before
	public void setUp() {
		PostService postService = new PostService();
		CommandsProcessor commandsProcessor = new CommandsProcessor(postService);
		prompt = new Prompt(commandsProcessor);
	}

	@Test public void
	read_published_messages_by_users() {
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
