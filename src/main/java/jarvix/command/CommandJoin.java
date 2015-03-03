package jarvix.command;

import java.util.Arrays;
import java.util.List;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;

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
			bot.sendIRC().joinChannel(args[0]);
		else
			channel.send().message("must specify a channel");
	}
}
