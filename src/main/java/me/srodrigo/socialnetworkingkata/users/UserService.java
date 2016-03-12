package me.srodrigo.socialnetworkingkata.users;

import me.srodrigo.socialnetworkingkata.posts.Post;
import me.srodrigo.socialnetworkingkata.posts.PostsPrinter;
import me.srodrigo.socialnetworkingkata.posts.PostsRepository;

import java.util.List;

public class UserService {

	private final UsersRepository usersRepository;
	private final PostsRepository postsRepository;
	private final PostsPrinter postsPrinter;

	public UserService(UsersRepository usersRepository, PostsRepository postsRepository,
	                   PostsPrinter postsPrinter) {
		this.postsRepository = postsRepository;
		this.usersRepository = usersRepository;
		this.postsPrinter = postsPrinter;
	}

	public void createPost(String username, String message) {
		User user = usersRepository.findByUsername(username);
		if (user.equals(User.NULL)) {
			user = usersRepository.create(username);
		}

		postsRepository.createPostForUser(message, user);
	}

	public void showTimeline(String username) {
		List<Post> userPosts = postsRepository.findByUsername(username);

		postsPrinter.print(userPosts);
	}
}
