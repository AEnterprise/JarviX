package jarvix.util;

public class ChannelUtil {

	public static String getChannel(String channel) {
		return channel.startsWith("#") ? channel : "#" + channel;
	}
}
