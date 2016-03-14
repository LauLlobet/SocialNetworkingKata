package me.srodrigo.socialnetworkingkata;

import me.srodrigo.socialnetworkingkata.infrastructure.Clock;
import me.srodrigo.socialnetworkingkata.infrastructure.Console;
import me.srodrigo.socialnetworkingkata.infrastructure.PastDateFormatter;
import me.srodrigo.socialnetworkingkata.model.posts.*;
import me.srodrigo.socialnetworkingkata.model.users.UserService;
import me.srodrigo.socialnetworkingkata.model.users.UsersRepository;

public class PromptFactory {

	public Prompt createPrompt(Console console, Clock clock) {
		UsersRepository usersRepository = new UsersRepository();
		PostsRepository postsRepository = new PostsRepository(clock);
		PastDateFormatter pastDateFormatter = new PastDateFormatter(clock);
		TimelinePostFormatter timelinePostFormatter = new TimelinePostFormatter(pastDateFormatter);
		PostsPrinter timelinePrinter = new PostsPrinter(timelinePostFormatter, console);
		PostFormatter wallPostFormatter = new WallPostFormatter(pastDateFormatter);
		PostsPrinter wallPrinter = new PostsPrinter(wallPostFormatter, console);
		UserService userService = new UserService(usersRepository, postsRepository, timelinePrinter, wallPrinter);

		return new Prompt(userService);
	}
}
