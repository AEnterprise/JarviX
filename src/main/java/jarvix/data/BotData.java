package jarvix.data;

import jarvix.data.handler.IDataType;

public class BotData implements IDataType {

	public String name = "";
	public String password = "";
	public String[] owners = new String[] {""};
	public String activationChar = "?";
	public String privatedActivationChar = "??";
	public int MaxChannelCount = 0;
	public String info = "";
}
