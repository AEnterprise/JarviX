package jarvix;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.pircbotx.PircBotX;

import jarvix.bot.BotManager;
import jarvix.data.BotData;
import jarvix.data.DataHandler;
import jarvix.data.ServerData;
import jarvix.lib.Constants;

public class JarviX {

	public static final File CONFIG = new File("config");
	public static Gson gson;

	public static void main(String[] args) {
		setupGson();
		loadAllConfigs();
	}

	private static void setupGson() {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		gson = builder.create();
	}

	private static void loadAllConfigs() {
		DataHandler.INSTANCE.registerData(Constants.Data.BOT_INFO_NAME, BotData.class);
		DataHandler.INSTANCE.registerData(Constants.Data.SERVER_DATA_NAME, ServerData.class);
		DataHandler.INSTANCE.loadData();
	}

	public static PircBotX getBot() {
		return BotManager.INSTANCE.bot;
	}
}
