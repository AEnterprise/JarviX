package jarvix.listener;

import org.apache.commons.lang3.ArrayUtils;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import static jarvix.JarviX.botData;
import jarvix.command.CommandHandler;
import jarvix.command.ICommand;

public class CommandListener extends ListenerAdapter<PircBotX> {

	@Override
	public void onMessage(MessageEvent<PircBotX> event) throws Exception {

		String message = event.getMessage();
		User user = event.getUser();
		Channel channel = event.getChannel();

		if (message.startsWith(botData.activationChar))
			handleMessage(event.getBot(), message, user, channel);
		else if (message.startsWith(botData.privatedActivationChar))
			handlePrivatedMessage(event.getBot(), message, user, channel);
	}

	private void handleMessage(PircBotX bot, String message, User user, Channel channel) {
		String[] args = message.substring(botData.activationChar.length()).split(" ");

		if (args.length < 1)
			return;

		ICommand command = CommandHandler.getCommandByName(args[0]);

		if (command == null)
			return;

		command.processCommand(bot, user, channel, ArrayUtils.remove(args, 0));
	}

	private void handlePrivatedMessage(PircBotX bot, String message, User user, Channel channel) {

	}
}
