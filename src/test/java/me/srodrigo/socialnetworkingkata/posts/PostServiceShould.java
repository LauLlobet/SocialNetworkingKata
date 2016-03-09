package me.srodrigo.socialnetworkingkata.posts;

import me.srodrigo.socialnetworkingkata.users.User;
import me.srodrigo.socialnetworkingkata.users.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceShould {

	private PostService postService;

	@Mock private PostsRepository postsRepository;
	@Mock private UsersRepository usersRepository;

	@Before
	public void setUp() {
		postService = new PostService();
	}

	@Test public void
	create_a_user_if_it_does_not_exist_and_store_a_message() {
		String username = "username";
		String message = "Post message";

		User user = new User();
		given(usersRepository.findOrCreate(username)).willReturn(user);

		postService.createPost(username, message);

		verify(postsRepository).createPostForUser(message, user);
	}
}
