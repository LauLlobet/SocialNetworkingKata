package me.srodrigo.socialnetworkingkata;

import me.srodrigo.socialnetworkingkata.infrastructure.Clock;
import me.srodrigo.socialnetworkingkata.infrastructure.Console;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Console console = new Console();
		Clock clock = new Clock();
		Prompt prompt = new PromptFactory().createPrompt(console, clock);

		Scanner scanner = new Scanner(System.in);
		while (true) {
			console.print("> ");
			prompt.readCommand(scanner.nextLine());
		}
	}

}
