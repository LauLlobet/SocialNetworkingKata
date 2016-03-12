package me.srodrigo.socialnetworkingkata.users;

import me.srodrigo.socialnetworkingkata.posts.Post;
import me.srodrigo.socialnetworkingkata.posts.PostsPrinter;
import me.srodrigo.socialnetworkingkata.posts.PostsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceShould {

	private static final String USERNAME = "username";
	private static final String POST_MESSAGE = "Post message";
	private static final User USER = user(USERNAME);
	private static final long DATE = 1000000L;

	private UserService userService;

	@Mock private UsersRepository usersRepository;
	@Mock private PostsRepository postsRepository;
	@Mock private PostsPrinter postsPrinter;

	@Before
	public void setUp() {
		userService = new UserService(usersRepository, postsRepository, postsPrinter);
	}

	@Test public void
	create_a_user_if_it_does_not_exist_and_store_a_message() {
		given(usersRepository.findByUsername(USERNAME)).willReturn(User.NULL);
		given(usersRepository.create(USERNAME)).willReturn(USER);

		userService.createPost(USERNAME, POST_MESSAGE);

		verify(postsRepository).createPostForUser(POST_MESSAGE, USER);
	}

	@Test public void
	store_a_message_for_an_existing_user() {
		given(usersRepository.findByUsername(USERNAME)).willReturn(USER);

		userService.createPost(USERNAME, POST_MESSAGE);

		verify(postsRepository).createPostForUser(POST_MESSAGE, USER);
	}

	@Test public void
	print_user_timeline() {
		List<Post> posts = asList(
				post(USERNAME, "First message", DATE),
				post(USERNAME, "Second message", DATE)
		);
		given(postsRepository.findByUsername(USERNAME)).willReturn(posts);

		userService.showTimeline(USERNAME);

		verify(postsPrinter).print(posts);
	}

	private static User user(String username) {
		return new User(username);
	}

	private static Post post(String username, String message, long date) {
		return new Post(username, message, date);
	}
}
