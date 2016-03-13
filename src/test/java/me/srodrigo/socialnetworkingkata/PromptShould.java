package me.srodrigo.socialnetworkingkata;

import me.srodrigo.socialnetworkingkata.users.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PromptShould {

	private Prompt prompt;

	@Mock private UserService userService;

	@Before
	public void setUp() {
		prompt = new Prompt(userService);
	}

	@Test public void
	process_posting_command_string() {
		prompt.readCommand("Alice -> I love the weather today");

		verify(userService).createPost("Alice", "I love the weather today");
	}

	@Test public void
	process_timeline_command_string() {
		prompt.readCommand("Alice");

		verify(userService).showTimeline("Alice");
	}

	@Test public void
	process_following_command_string() {
		prompt.readCommand("Charlie follows Alice");

		verify(userService).addFollowedUsername("Charlie", "Alice");
	}

	@Test public void
	process_wall_command_string() {
		prompt.readCommand("Charlie wall");

		verify(userService).showWall("Charlie");
	}
}
