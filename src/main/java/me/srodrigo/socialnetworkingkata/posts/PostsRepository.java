package me.srodrigo.socialnetworkingkata.posts;

import me.srodrigo.socialnetworkingkata.users.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostsRepository {

	private List<Post> posts = new ArrayList<>();

	public Post createPostForUser(String message, User user) {
		Post newPost = new Post(user.username(), message);
		posts.add(newPost);
		return newPost;
	}

	public List<Post> findAll() {
		return Collections.unmodifiableList(posts);
	}
}
