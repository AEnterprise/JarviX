package jarvix.data;

import jarvix.data.handler.IDataType;

public class ServerData implements IDataType {

	public String name = "";
	public int port = 0;
	public ChannelData[] channels = {new ChannelData()};

	public static class ChannelData {

		public String name = "";
		public String password = "";
	}
}
