package me.srodrigo.socialnetworkingkata;

import me.srodrigo.socialnetworkingkata.users.UserService;

public class Prompt {

	private static final String POST_MESSAGE_KEYWORD = "->";
	private final UserService userService;

	public Prompt(UserService userService) {
		this.userService = userService;
	}

	public void readCommand(String command) {
		if (isPostingCommand(command)) {
			createPost(command);
		} else {
			showTimeline(command);
		}
	}

	private boolean isPostingCommand(String command) {
		return command.contains(POST_MESSAGE_KEYWORD);
	}

	private void createPost(String command) {
		CreatePostParameters parameters = extractCreatePostParameters(command);
		userService.createPost(parameters.username, parameters.postMessage);
	}

	private CreatePostParameters extractCreatePostParameters(String command) {
		String keywordWithSpaces = String.format(" %s ", POST_MESSAGE_KEYWORD);
		String[] tokens = command.split(keywordWithSpaces);
		return new CreatePostParameters(tokens[0], tokens[1]);
	}

	private void showTimeline(String username) {
		userService.showTimeline(username);
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
