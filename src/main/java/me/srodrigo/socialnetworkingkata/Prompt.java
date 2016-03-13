package me.srodrigo.socialnetworkingkata;

import me.srodrigo.socialnetworkingkata.users.UserService;

public class Prompt {

	private static final String POST_MESSAGE_KEYWORD = "->";
	private static final String FOLLOW_KEYWORD = "follows";
	private final UserService userService;

	public Prompt(UserService userService) {
		this.userService = userService;
	}

	public void readCommand(String command) {
		if (isPostingCommand(command)) {
			createPost(command);
		} else if (isFollowingCommand(command)) {
			followUser(command);
		} else {
			showTimeline(command);
		}
	}

	private boolean isPostingCommand(String command) {
		return command.contains(POST_MESSAGE_KEYWORD);
	}

	private boolean isFollowingCommand(String command) {
		return command.contains(FOLLOW_KEYWORD);
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

	private void followUser(String command) {
		FollowUserParameters parameters = extractFollowUserParameters(command);
		userService.follow(parameters.follower, parameters.followed);
	}

	private FollowUserParameters extractFollowUserParameters(String command) {
		String keywordWithSpaces = String.format(" %s ", FOLLOW_KEYWORD);
		String[] tokens = command.split(keywordWithSpaces);
		return new FollowUserParameters(tokens[0], tokens[1]);
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

	private class FollowUserParameters {
		String follower;
		String followed;

		public FollowUserParameters(String follower, String followed) {
			this.follower = follower;
			this.followed = followed;
		}
	}
}
