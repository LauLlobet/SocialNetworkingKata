package me.srodrigo.socialnetworkingkata.posts;

import me.srodrigo.socialnetworkingkata.users.User;
import me.srodrigo.socialnetworkingkata.users.UsersRepository;

public class PostService {

	private final PostsRepository postsRepository;
	private final UsersRepository usersRepository;

	public PostService(PostsRepository postsRepository, UsersRepository usersRepository) {
		this.postsRepository = postsRepository;
		this.usersRepository = usersRepository;
	}

	public void createPost(String username, String message) {
		User user = usersRepository.findOrCreate(username);
		postsRepository.createPostForUser(message, user);
	}
}
