package jarvix.bot;

import java.nio.charset.Charset;
import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import jarvix.JarviX;
import static jarvix.JarviX.botData;
import static jarvix.JarviX.serverData;
import jarvix.data.DataHandler;
import jarvix.data.ServerData;
import jarvix.listener.CommandListener;
import jarvix.listener.PrivateCommandListener;

public class BotManager {

	public static BotManager INSTANCE = new BotManager();

	public PircBotX bot;

	public Map<String, ServerData.ChannelData> channels = Maps.newHashMap();

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
				addChannelToList(name, data.password);
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

	public void joinChannel(String name, String key) {
		if (Strings.isNullOrEmpty(key))
			bot.sendIRC().joinChannel(name);
		else
			bot.sendIRC().joinChannel(name, key);
		addChannelToList(name, key);
	}

	public void leaveChannel(String channel) {
		bot.sendRaw().rawLine("PART " + channel);
	}

	private void addChannelToList(String name, String password) {
		password = Strings.isNullOrEmpty(password) ? "" : password;
		ServerData.ChannelData data = new ServerData.ChannelData();
		data.name = name;
		data.password = password;
		channels.put(name, data);
	}

	public void save() {
		JarviX.serverData.channels = new ServerData.ChannelData[channels.size()];
		int i = 0;

		for (ServerData.ChannelData data : channels.values()) {
			JarviX.serverData.channels[i] = data;
			i++;
		}

		DataHandler.INSTANCE.saveData();
	}
}
