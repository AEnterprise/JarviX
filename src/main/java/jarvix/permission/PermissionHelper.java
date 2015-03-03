package jarvix.permission;

import java.util.Arrays;

import org.pircbotx.User;

import jarvix.JarviX;

public class PermissionHelper {

	public static boolean isBotOwner(String name) {
		return Arrays.asList(JarviX.botData.owners).contains(name);
	}

	public static boolean isBotOwner(User user) {
		return isBotOwner(user.getNick());
	}
}
