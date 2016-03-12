package me.srodrigo.socialnetworkingkata.posts;

import me.srodrigo.socialnetworkingkata.users.User;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class PostsRepository {

	private final Clock clock;
	private List<Post> posts = new ArrayList<>();

	public PostsRepository(Clock clock) {
		this.clock = clock;
	}

	public Post createPostForUser(String message, User user) {
		Post newPost = new Post(user.username(), message, clock.now());
		posts.add(newPost);
		return newPost;
	}

	public List<Post> findAll() {
		return unmodifiableList(posts);
	}

	public List<Post> findByUsername(String username) {
		return posts.stream()
				.filter(post -> post.username().equals(username))
				.collect(toList());
	}
}
