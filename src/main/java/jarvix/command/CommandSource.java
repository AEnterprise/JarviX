package jarvix.command;

import java.util.Arrays;
import java.util.List;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;

public class CommandSource implements ICommand {

	@Override
	public String getName() {
		return "source";
	}

	@Override
	public List<String> getAlliases() {
		return Arrays.asList("src");
	}

	@Override
	public void processCommand(PircBotX bot, User user, Channel channel, String[] args) {
		channel.send().message("Github repository for JariX: https://github.com/JarviX-Bot");
	}
}
