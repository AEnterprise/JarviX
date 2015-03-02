package jarvix.listener;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.PrivateMessageEvent;

public class PrivateCommandListener extends ListenerAdapter<PircBotX> {

	@Override
	public void onPrivateMessage(PrivateMessageEvent<PircBotX> event) throws Exception {
		super.onPrivateMessage(event);
	}
}
