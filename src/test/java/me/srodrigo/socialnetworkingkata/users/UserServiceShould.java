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

	private UserService userService;

	@Mock private UsersRepository usersRepository;
	@Mock private PostsRepository postsRepository;

	@Before
	public void setUp() {
		userService = new UserService(usersRepository, postsRepository);
	}

	@Test public void
	create_a_user_if_it_does_not_exist_and_store_a_message() {
		String username = "username";
		String message = "Post message";

		given(usersRepository.findByUsername(username)).willReturn(User.NULL);
		User user = user(username);
		given(usersRepository.create(username)).willReturn(user);

		userService.createPost(username, message);

		verify(postsRepository).createPostForUser(message, user);
	}

	@Test public void
	store_a_message_for_an_existing_user() {
		String username = "username";
		String message = "Post message";

		User user = user(username);
		given(usersRepository.findByUsername(username)).willReturn(user);

		userService.createPost(username, message);

		verify(postsRepository).createPostForUser(message, user);
	}

	private User user(String username) {
		return new User(username);
	}
}
