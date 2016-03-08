package me.srodrigo.socialnetworkingkata;

public class Prompt {

	private final CommandsProcessor commandsProcessor;

	public Prompt(CommandsProcessor commandsProcessor) {
		this.commandsProcessor = commandsProcessor;
	}

	public void readCommand(String command) {
		commandsProcessor.process(command);
	}
}
