package jarvix.command;

import java.util.Arrays;
import java.util.List;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;

import jarvix.JarviX;
import jarvix.command.handler.ICommand;

public class CommandKill implements ICommand {

	@Override
	public String getName() {
		return "kill";
	}

	@Override
	public List<String> getAlliases() {
		return Arrays.asList("quit", "stop");
	}

	@Override
	public void processCommand(PircBotX bot, User user, Channel channel, String[] args) {
		JarviX.quitBot();
	}
}
