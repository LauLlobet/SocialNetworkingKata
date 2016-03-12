package me.srodrigo.socialnetworkingkata;

import me.srodrigo.socialnetworkingkata.users.UserService;

public class CommandsProcessor {

	private static final String POST_MESSAGE_KEYWORD = "->";
	private final UserService userService;

	public CommandsProcessor(UserService userService) {
		this.userService = userService;
	}

	public void process(String command) {
		if (command.contains(POST_MESSAGE_KEYWORD)) {
			CreatePostParameters parameters = extractCreatePostParameters(command);
			userService.createPost(parameters.username, parameters.postMessage);
		} else {
			userService.showTimeline(command);
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
