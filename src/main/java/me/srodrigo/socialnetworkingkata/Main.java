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
		boolean continuePrompt =  true;
		while (continuePrompt) {
			console.print("> "); // no submodule was affected but the % of the
			// contribution of the submodule to the output was changed from M to N times
			continuePrompt = prompt.readCommand(scanner.nextLine());
		}
	}

}
