package me.srodrigo.socialnetworkingkata;

import me.srodrigo.socialnetworkingkata.posts.PostService;

public class CommandsProcessor {

	private static final String POST_MESSAGE_KEYWORD = "->";
	private final PostService postService;

	public CommandsProcessor(PostService postService) {
		this.postService = postService;
	}

	public void process(String command) {
		if (command.contains(POST_MESSAGE_KEYWORD)) {
			CreatePostParameters parameters = extractCreatePostParameters(command);
			postService.createPost(parameters.username, parameters.postMessage);
		}
	}

	private CreatePostParameters extractCreatePostParameters(String command) {
		String keywordWithSpaces = String.format(" %s ", POST_MESSAGE_KEYWORD);
		String[] tokens = command.split(keywordWithSpaces);
		return new CreatePostParameters(tokens[0], tokens[1]);
	}

	private class CreatePostParameters {
		String username;
		String postMessage;

		public CreatePostParameters(String username, String postMessage) {
			this.username = username;
			this.postMessage = postMessage;
		}
	}
}
