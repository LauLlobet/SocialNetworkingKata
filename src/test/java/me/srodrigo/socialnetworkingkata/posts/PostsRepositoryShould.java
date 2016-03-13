package me.srodrigo.socialnetworkingkata.posts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static me.srodrigo.socialnetworkingkata.TestUtil.now;
import static me.srodrigo.socialnetworkingkata.TestUtil.post;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PostsRepositoryShould {

	private static final String USERNAME = "username";
	private static final String MESSAGE = "A message";

	private PostsRepository postsRepository;
	@Mock private Clock clock;

	@Before
	public void setUp() {
		postsRepository = new PostsRepository(clock);
		given(clock.now()).willReturn(now());
	}

	@Test public void
	create_and_store_a_post_for_a_user() {
		postsRepository.createPostForUser(MESSAGE, USERNAME);

		List<Post> allPosts = postsRepository.findAll();

		assertThat(allPosts.size(), is(1));
		assertThat(allPosts.get(0), is(post(USERNAME, MESSAGE, now())));
	}

	@Test public void
	return_created_post() {
		Post post = postsRepository.createPostForUser(MESSAGE, USERNAME);

		assertThat(post, is(post(USERNAME, MESSAGE, now())));
	}

	@Test public void
	find_posts_by_username() {
		String expectedUsername = "Alice";
		String expectedPostMessage = "Alice's message";
		postsRepository.createPostForUser(expectedPostMessage, expectedUsername);

		postsRepository.createPostForUser("Bob's message", "Bob");

		List<Post> userPosts = postsRepository.findByUsername(expectedUsername);

		assertThat(userPosts.size(), is(1));
		assertThat(userPosts.get(0), is(post(expectedUsername, expectedPostMessage, now())));
	}

	@Test public void
	find_posts_by_multiple_usernames() {
		String alice = "Alice";
		String aliceMessage = "Alice's message";
		postsRepository.createPostForUser(aliceMessage, alice);
		String bob = "Bob";
		String bobFirstMessage = "Bob's message";
		postsRepository.createPostForUser(bobFirstMessage, bob);
		String bobSecondMessage = "Bob's second message";
		postsRepository.createPostForUser(bobSecondMessage, bob);

		List<Post> posts = postsRepository.findPostsByUsernames(asList(alice, bob));

		assertThat(posts.size(), is(3));
		assertThat(posts.get(0), is(post(alice, aliceMessage, now())));
		assertThat(posts.get(1), is(post(bob, bobFirstMessage, now())));
		assertThat(posts.get(2), is(post(bob, bobSecondMessage, now())));
	}

}
