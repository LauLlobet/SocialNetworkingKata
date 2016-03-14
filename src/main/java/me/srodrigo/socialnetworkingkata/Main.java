package me.srodrigo.socialnetworkingkata;

import me.srodrigo.socialnetworkingkata.infrastructure.Clock;
import me.srodrigo.socialnetworkingkata.infrastructure.Console;
import me.srodrigo.socialnetworkingkata.infrastructure.PastDateFormatter;
import me.srodrigo.socialnetworkingkata.model.posts.*;
import me.srodrigo.socialnetworkingkata.model.users.UserService;
import me.srodrigo.socialnetworkingkata.model.users.UsersRepository;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Console console = new Console();
		Prompt prompt = assemblePrompt(console);

		Scanner scanner = new Scanner(System.in);
		while (true) {
			console.print("> ");
			prompt.readCommand(scanner.nextLine());
		}
	}

	private static Prompt assemblePrompt(Console console) {
		UsersRepository usersRepository = new UsersRepository();
		Clock clock = new Clock();
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
