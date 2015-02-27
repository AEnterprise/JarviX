package jarvix;

import java.io.File;

import org.pircbotx.PircBotX;

import jarvix.bot.BotManager;

public class JarviX {

	public static final File CONFIG = new File("config");

	public static void main(String[] args) {

	}

	public static PircBotX getBot() {
		return BotManager.INSTANCE.bot;
	}
}
