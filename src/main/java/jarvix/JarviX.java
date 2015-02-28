package jarvix;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.pircbotx.PircBotX;

import jarvix.bot.BotManager;
import jarvix.lib.Constants;
import jarvix.data.BotInfo;
import jarvix.data.DataHandler;

public class JarviX {

	public static final File CONFIG = new File("config");
	public static Gson gson;

	public static void main(String[] args) {
		setupGson();

	}

	private static void setupGson() {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		gson = builder.create();
	}

	private static void loadAllConfigs() {
		DataHandler.INSTANCE.registerData(Constants.Data.BOT_INFO_NAME, BotInfo.class);
		DataHandler.INSTANCE.loadData();
	}

	public static PircBotX getBot() {
		return BotManager.INSTANCE.bot;
	}
}
