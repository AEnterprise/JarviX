package jarvix.data;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DataHandler {

	protected static Map<String, Class<? extends JsonData>> dataClasses = Maps.newHashMap();
	protected static List<JsonData> dataInstances = Lists.newArrayList();
	protected static Map<String, IDataType> data = Maps.newHashMap();

	public static IDataType getDataByName(String name) {
		if (data.containsKey(name))
			return data.get(name);

		if (dataClasses.containsKey(name))
			return setupDataType(name);

		return null;
	}

	public static IDataType setupDataType(String name) {
		try {
			JsonData jsonData = dataClasses.get(name).newInstance();
			jsonData.init();
			dataInstances.add(jsonData);
			IDataType dataType = jsonData.data();
			data.put(name, dataType);
			return dataType;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void addData(String name, Class<? extends JsonData> dataClass) {
		dataClasses.put(name, dataClass);
	}

	public static void saveData() {
		for (JsonData data : dataInstances)
			data.saveData();
	}

}
