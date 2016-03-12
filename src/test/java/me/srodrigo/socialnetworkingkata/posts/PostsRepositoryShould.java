package me.srodrigo.socialnetworkingkata.posts;

import me.srodrigo.socialnetworkingkata.users.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PostsRepositoryShould {

	private static final User USER = new User("username");
	private static final String MESSAGE = "A message";

	private PostsRepository postsRepository;

	@Before
	public void setUp() {
	    postsRepository = new PostsRepository();
	}

	@Test public void
	create_and_store_a_post_for_a_user() {
		postsRepository.createPostForUser(MESSAGE, USER);

		List<Post> allPosts = postsRepository.findAll();

		assertThat(allPosts.size(), is(1));
		assertThat(allPosts.get(0), is(post(USER.username(), MESSAGE)));
	}

	@Test public void
	return_created_post() {
		Post post = postsRepository.createPostForUser(MESSAGE, USER);

		assertThat(post, is(post(USER.username(), MESSAGE)));
	}

	@Test public void
	find_posts_by_username() {
		String expectedUsername = "Alice";
		String expectedPostMessage = "Alice's message";
		postsRepository.createPostForUser(expectedPostMessage, user(expectedUsername));

		postsRepository.createPostForUser("Bob's message", user("Bob"));

		List<Post> userPosts = postsRepository.findByUsername(expectedUsername);

		assertThat(userPosts.size(), is(1));
		assertThat(userPosts.get(0), is(post(expectedPostMessage, expectedUsername)));
	}

	private Post post(String username, String message) {
		return new Post(username, message);
	}

	private User user(String username) {
		return new User(username);
	}

}
