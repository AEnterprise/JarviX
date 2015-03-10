package jarvix.command.handler;

import java.util.Map;

import com.google.common.collect.Maps;

public class CommandHandler {

	private static Map<String, ICommand> commands = Maps.newHashMap();

	public static void registerCommand(ICommand command) {
		commands.put(command.getName(), command);
		if (!command.getAlliases().isEmpty())
			command.getAlliases().forEach((name) -> commands.put(name, command));
	}

	public static ICommand getCommandByName(String name) {
		return commands.get(name);
	}
}
