package me.srodrigo.socialnetworkingkata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PromptShould {

	private Prompt prompt;

	@Mock private CommandsProcessor commandsProcessor;

	@Before
	public void setUp() {
		prompt = new Prompt(commandsProcessor);
	}

	@Test public void
	process_a_command() {
		prompt.readCommand("Alice");

		verify(commandsProcessor).process("Alice");
	}
}
