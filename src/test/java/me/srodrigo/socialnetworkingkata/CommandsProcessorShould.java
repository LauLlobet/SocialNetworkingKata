package me.srodrigo.socialnetworkingkata;

import me.srodrigo.socialnetworkingkata.users.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandsProcessorShould {

	private CommandsProcessor commandsProcessor;

	@Mock private UserService userService;

	@Before
	public void setUp() {
		commandsProcessor = new CommandsProcessor(userService);
	}

	@Test public void
	process_posting_command_string() {
		commandsProcessor.process("Alice -> I love the weather today");

		verify(userService).createPost("Alice", "I love the weather today");
	}
}
