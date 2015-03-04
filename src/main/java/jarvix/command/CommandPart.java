package jarvix.command;

import java.util.Arrays;
import java.util.List;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;

import jarvix.JarviX;

public class CommandPart implements ICommand {

	@Override
	public String getName() {
		return "part";
	}

	@Override
	public List<String> getAlliases() {
		return Arrays.asList("p", "leave");
	}

	@Override
	public void processCommand(PircBotX bot, User user, Channel channel, String[] args) {
		if (args.length == 0)
			channel.send().part();
		else if (args.length == 1)
			JarviX.leaveChannel(args[0]);
	}
}
