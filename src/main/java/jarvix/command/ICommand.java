package jarvix.command;

import java.util.List;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;

public interface ICommand {

	String getName();

	List<String> getAlliases();

	void processCommand(PircBotX bot, User user, Channel channel, String[] args);
}

