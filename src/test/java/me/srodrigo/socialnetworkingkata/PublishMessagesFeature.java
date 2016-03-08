package me.srodrigo.socialnetworkingkata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class PublishMessagesFeature {

	@Mock private Console console;

	@Test public void
	read_published_messages_by_users() {
		Prompt prompt = new Prompt();

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
