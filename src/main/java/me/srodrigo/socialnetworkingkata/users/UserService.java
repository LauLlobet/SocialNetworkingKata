package me.srodrigo.socialnetworkingkata.users;

import me.srodrigo.socialnetworkingkata.posts.Post;
import me.srodrigo.socialnetworkingkata.posts.PostsPrinter;
import me.srodrigo.socialnetworkingkata.posts.PostsRepository;

import java.util.List;

public class UserService {

	private final UsersRepository usersRepository;
	private final PostsRepository postsRepository;
	private final PostsPrinter timelinePrinter;

	public UserService(UsersRepository usersRepository, PostsRepository postsRepository,
	                   PostsPrinter timelinePrinter) {
		this.postsRepository = postsRepository;
		this.usersRepository = usersRepository;
		this.timelinePrinter = timelinePrinter;
	}

	public void createPost(String username, String message) {
		User user = usersRepository.findByUsername(username);
		if (user.equals(User.NULL)) {
			user = usersRepository.create(username);
		}

		postsRepository.createPostForUser(message, user.username());
	}

	public void showTimeline(String username) {
		List<Post> userPosts = postsRepository.findByUsername(username);

		timelinePrinter.print(userPosts);
	}

	public void addFollower(String follower, String followed) {
		usersRepository.addFollower(follower, followed);
	}

	public void showWall(String username) {
		throw new UnsupportedOperationException();
	}
}
