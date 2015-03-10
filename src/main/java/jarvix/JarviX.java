package jarvix;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.pircbotx.PircBotX;

import jarvix.bot.BotManager;
import jarvix.command.handler.CommandHandler;
import jarvix.command.CommandJoin;
import jarvix.command.CommandKill;
import jarvix.command.CommandPart;
import jarvix.command.CommandSource;
import jarvix.data.BotData;
import jarvix.data.handler.DataHandler;
import jarvix.data.ServerData;
import jarvix.lib.Constants;

public class JarviX {

	public static final File CONFIG = new File("config");
	public static Gson gson;

	public static BotData botData;
	public static ServerData serverData;

	public static void main(String[] args) {
		setupGson();
		loadAllConfigs();
		registerCommands();
		loadAndStartBot();
	}

	private static void setupGson() {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		gson = builder.create();
	}

	private static void loadAllConfigs() {
		DataHandler.INSTANCE.registerData(Constants.Data.BOT_DATA_NAME, BotData.class);
		DataHandler.INSTANCE.registerData(Constants.Data.SERVER_DATA_NAME, ServerData.class);
		DataHandler.INSTANCE.loadData();
		botData = DataHandler.INSTANCE.getData(Constants.Data.BOT_DATA_NAME, BotData.class);
		serverData = DataHandler.INSTANCE.getData(Constants.Data.SERVER_DATA_NAME, ServerData.class);
	}

	private static void loadAndStartBot() {
		BotManager.INSTANCE.buildBot();
		BotManager.INSTANCE.startBot();
	}

	private static void registerCommands() {
		CommandHandler.registerCommand(new CommandJoin());
		CommandHandler.registerCommand(new CommandPart());
		CommandHandler.registerCommand(new CommandSource());
		CommandHandler.registerCommand(new CommandKill());
	}

	public static PircBotX getBot() {
		return BotManager.INSTANCE.bot;
	}

	public static void joinChannel(String name, String key) {
		BotManager.INSTANCE.joinChannel(name, key);
	}

	public static void joinChannel(String name) {
		joinChannel(name, null);
	}

	public static void leaveChannel(String channel) {
		BotManager.INSTANCE.leaveChannel(channel);
	}

	public static void quitBot() {
		getBot().stopBotReconnect();
		getBot().sendIRC().quitServer();
		BotManager.INSTANCE.save();
	}
}
