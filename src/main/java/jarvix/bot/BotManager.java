package jarvix.bot;

import java.nio.charset.Charset;

import com.google.common.base.Strings;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import static jarvix.JarviX.botData;
import static jarvix.JarviX.serverData;
import jarvix.data.ServerData;
import jarvix.listener.CommandListener;
import jarvix.listener.PrivateCommandListener;

public class BotManager {

	public static BotManager INSTANCE = new BotManager();

	public PircBotX bot;

	public void buildBot() {
		Configuration.Builder<PircBotX> builder = new Configuration.Builder<PircBotX>();
		builder.setName(botData.name);
		builder.setAutoNickChange(true);
		builder.setServerHostname(serverData.name);
		builder.setServerPort(serverData.port);
		builder.setEncoding(Charset.forName("UTF-8"));
		builder.setAutoNickChange(true);
		builder.addListener(new CommandListener());
		builder.addListener(new PrivateCommandListener());

		if (!Strings.isNullOrEmpty(botData.password))
			builder.setNickservPassword(botData.password);

		for (ServerData.ChannelData data : serverData.channels) {
			if (!Strings.isNullOrEmpty(data.name)) {
				String name = data.name.startsWith("#") ? data.name : "#" + data.name;
				if (Strings.isNullOrEmpty(data.password))
					builder.addAutoJoinChannel(name);
				else
					builder.addAutoJoinChannel(name, data.password);
			}
		}

		bot = new PircBotX(builder.buildConfiguration());
	}

	public void startBot() {
		try {
			bot.startBot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
