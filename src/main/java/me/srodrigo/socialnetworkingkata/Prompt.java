package me.srodrigo.socialnetworkingkata;

import me.srodrigo.socialnetworkingkata.users.UserService;

public class Prompt {

	private static final String POST_MESSAGE_KEYWORD = "->";
	private static final String FOLLOW_KEYWORD = "follows";
	private static final String WALL_KEYWORD = "wall";

	private final UserService userService;

	public Prompt(UserService userService) {
		this.userService = userService;
	}

	public void readCommand(String command) {
		if (isPostingCommand(command)) {
			createPost(command);
		} else if (isFollowingCommand(command)) {
			followUser(command);
		} else if (isWallCommand(command)) {
			showWall(command);
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

	private boolean isWallCommand(String command) {
		return command.endsWith(WALL_KEYWORD);
	}

	private void createPost(String command) {
		CreatePostParameters parameters = extractCreatePostParameters(command);
		userService.createPost(parameters.username, parameters.postMessage);
	}

	private CreatePostParameters extractCreatePostParameters(String command) {
		String[] parameters = extractParametersFromBinaryCommand(command, POST_MESSAGE_KEYWORD);
		return new CreatePostParameters(parameters[0], parameters[1]);
	}

	private void followUser(String command) {
		FollowUserParameters parameters = extractFollowUserParameters(command);
		userService.addFollowedUsername(parameters.follower, parameters.followed);
	}

	private FollowUserParameters extractFollowUserParameters(String command) {
		String[] parameters = extractParametersFromBinaryCommand(command, FOLLOW_KEYWORD);
		return new FollowUserParameters(parameters[0], parameters[1]);
	}

	private String[] extractParametersFromBinaryCommand(String command, String keyword) {
		String keywordWithSpaces = String.format(" %s ", keyword);
		return command.split(keywordWithSpaces);
	}

	private void showWall(String command) {
		String username = extractWallUsernameParameter(command);
		userService.showWall(username);
	}

	private String extractWallUsernameParameter(String command) {
		return command.replace(String.format(" %s", WALL_KEYWORD), "");
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
