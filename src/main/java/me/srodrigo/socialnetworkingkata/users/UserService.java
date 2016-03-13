package me.srodrigo.socialnetworkingkata.users;

import me.srodrigo.socialnetworkingkata.posts.Post;
import me.srodrigo.socialnetworkingkata.posts.PostsPrinter;
import me.srodrigo.socialnetworkingkata.posts.PostsRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {

	private final UsersRepository usersRepository;
	private final PostsRepository postsRepository;
	private final PostsPrinter timelinePrinter;
	private final PostsPrinter wallPrinter;

	public UserService(UsersRepository usersRepository, PostsRepository postsRepository,
	                   PostsPrinter timelinePrinter, PostsPrinter wallPrinter) {
		this.postsRepository = postsRepository;
		this.usersRepository = usersRepository;
		this.timelinePrinter = timelinePrinter;
		this.wallPrinter = wallPrinter;
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

	public void addFollowedUsername(String follower, String followed) {
		usersRepository.addFollower(follower, followed);
	}

	public void showWall(String username) {
		User user = usersRepository.findByUsername(username);
		List<Post> wallPosts = postsRepository.findPostsByUsernames(
				usernamePlusFollowed(user));

		wallPrinter.print(wallPosts);
	}

	private String[] usernamePlusFollowed(User user) {
		List<String> allUsernames = new ArrayList<>();
		allUsernames.add(user.username());
		allUsernames.addAll(user.followedUsernames());
		return allUsernames.stream().toArray(String[]::new);
	}
}
