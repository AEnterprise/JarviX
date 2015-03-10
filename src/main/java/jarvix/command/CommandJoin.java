package jarvix.command;

import java.util.Arrays;
import java.util.List;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;

import jarvix.JarviX;
import jarvix.command.handler.ICommand;

public class CommandJoin implements ICommand {

	@Override
	public String getName() {
		return "join";
	}

	@Override
	public List<String> getAlliases() {
		return Arrays.asList("j");
	}

	@Override
	public void processCommand(PircBotX bot, User user, Channel channel, String[] args) {
		if (args.length == 1)
			JarviX.joinChannel(args[0]);
		else if (args.length == 2)
			JarviX.joinChannel(args[0], args[1]);
		else
			channel.send().message("must specify a channel");
	}
}
