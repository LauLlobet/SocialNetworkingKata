package me.srodrigo.socialnetworkingkata.users;

import me.srodrigo.socialnetworkingkata.posts.PostsRepository;

public class UserService {

	private final UsersRepository usersRepository;
	private final PostsRepository postsRepository;

	public UserService(UsersRepository usersRepository, PostsRepository postsRepository) {
		this.postsRepository = postsRepository;
		this.usersRepository = usersRepository;
	}

	public void createPost(String username, String message) {
		User user = usersRepository.findByUsername(username);
		if (user.equals(User.NULL)) {
			user = usersRepository.create(username);
		}

		postsRepository.createPostForUser(message, user);
	}

	public void showTimeline(String username) {
		throw new UnsupportedOperationException();
	}
}
