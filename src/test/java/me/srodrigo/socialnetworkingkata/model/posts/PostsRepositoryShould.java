package me.srodrigo.socialnetworkingkata.model.posts;

import me.srodrigo.socialnetworkingkata.infrastructure.Clock;
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
	private static final String ALICE = "Alice";
	private static final String BOB = "Bob";
	private static final String CHARLIE = "Charlie";
	private static final String ALICE_MESSAGE = "Alice's message";
	private static final String BOB_MESSAGE = "Bob's message";
	private static final String BOB_SECOND_MESSAGE = "Bob's second message";
	private static final String CHARLIE_MESSAGE = "Charlie message";
	private static final long DEFAULT_DATE = now();

	private PostsRepository postsRepository;

	@Mock private Clock clock;

	@Before
	public void setUp() {
		postsRepository = new PostsRepository(clock);

		given(clock.now()).willReturn(DEFAULT_DATE);
	}

	@Test public void
	create_and_store_a_post_for_a_user() {
		postsRepository.createPostForUser(MESSAGE, USERNAME);

		List<Post> allPosts = postsRepository.findAll();

		assertThat(allPosts.size(), is(1));
		assertThat(allPosts.get(0), is(post(USERNAME, MESSAGE, DEFAULT_DATE)));
	}

	@Test public void
	return_created_post() {
		Post post = postsRepository.createPostForUser(MESSAGE, USERNAME);

		assertThat(post, is(post(USERNAME, MESSAGE, DEFAULT_DATE)));
	}

	@Test public void
	find_posts_by_username() {
		postsRepository.createPostForUser(ALICE_MESSAGE, ALICE);
		postsRepository.createPostForUser(BOB_MESSAGE, BOB);

		List<Post> userPosts = postsRepository.findByUsername(ALICE);

		assertThat(userPosts.size(), is(1));
		assertThat(userPosts.get(0), is(post(ALICE, ALICE_MESSAGE, DEFAULT_DATE)));
	}

	@Test public void
	find_posts_by_multiple_usernames() {
		postsRepository.createPostForUser(ALICE_MESSAGE, ALICE);
		postsRepository.createPostForUser(BOB_MESSAGE, BOB);
		postsRepository.createPostForUser(CHARLIE_MESSAGE, CHARLIE);
		postsRepository.createPostForUser(BOB_SECOND_MESSAGE, BOB);

		List<Post> posts = postsRepository.findByUsernames(asList(ALICE, BOB));

		assertThat(posts.size(), is(3));
		assertThat(posts.get(0), is(post(ALICE, ALICE_MESSAGE, DEFAULT_DATE)));
		assertThat(posts.get(1), is(post(BOB, BOB_MESSAGE, DEFAULT_DATE)));
		assertThat(posts.get(2), is(post(BOB, BOB_SECOND_MESSAGE, DEFAULT_DATE)));
	}

}
