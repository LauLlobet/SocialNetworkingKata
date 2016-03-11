package me.srodrigo.socialnetworkingkata.users;

import me.srodrigo.socialnetworkingkata.posts.PostsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceShould {

	private static final String USERNAME = "username";
	private static final String POST_MESSAGE = "Post message";
	private static final User USER = user(USERNAME);

	private UserService userService;

	@Mock private UsersRepository usersRepository;
	@Mock private PostsRepository postsRepository;

	@Before
	public void setUp() {
		userService = new UserService(usersRepository, postsRepository);
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

	private static User user(String username) {
		return new User(username);
	}
}