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
import static java.util.Collections.singletonList;
import static me.srodrigo.socialnetworkingkata.TestUtil.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceShould {

	private static final String USERNAME = "username";
	private static final String POST_MESSAGE = "Post message";
	private static final User USER = user(USERNAME);
	private static final String FOLLOWER = "follower";
	private static final String FOLLOWED = "followed";

	private UserService userService;

	@Mock private UsersRepository usersRepository;
	@Mock private PostsRepository postsRepository;
	@Mock private PostsPrinter postsPrinter;
	@Mock private PostsPrinter wallPrinter;

	@Before
	public void setUp() {
		userService = new UserService(usersRepository, postsRepository, postsPrinter, wallPrinter);
	}

	@Test public void
	create_a_user_if_it_does_not_exist_and_store_a_message() {
		given(usersRepository.findByUsername(USERNAME)).willReturn(User.NULL);
		given(usersRepository.create(USERNAME)).willReturn(USER);

		userService.createPost(USERNAME, POST_MESSAGE);

		verify(postsRepository).createPostForUser(POST_MESSAGE, USERNAME);
	}

	@Test public void
	store_a_message_for_an_existing_user() {
		given(usersRepository.findByUsername(USERNAME)).willReturn(USER);

		userService.createPost(USERNAME, POST_MESSAGE);

		verify(postsRepository).createPostForUser(POST_MESSAGE, USERNAME);
	}

	@Test public void
	print_user_timeline() {
		List<Post> posts = asList(
				post(USERNAME, "First message", now()),
				post(USERNAME, "Second message", now())
		);
		given(postsRepository.findByUsername(USERNAME)).willReturn(posts);

		userService.showTimeline(USERNAME);

		verify(postsPrinter).print(posts);
	}

	@Test public void
	add_a_follower() {
		userService.addFollowedUsername(FOLLOWER, FOLLOWED);

		verify(usersRepository).addFollower(FOLLOWER, FOLLOWED);
	}

	@Test public void
	show_user_wall() {
		String alice = "Alice";
		String bob = "Bob";
		given(usersRepository.findByUsername(alice))
				.willReturn(userWithFollowedUsernames(alice, singletonList(bob)));

		List<Post> wallPosts = asList(
				post(alice, POST_MESSAGE, minutesAgo(1)),
				post(bob, POST_MESSAGE, minutesAgo(2))
		);
		given(postsRepository.findPostsByUsernames(alice, bob))
				.willReturn(wallPosts);

		userService.showWall(alice);

		verify(wallPrinter).print(wallPosts);
	}

}
