package jarvix.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.io.Files;

import jarvix.JarviX;

public class DataHandler {

	private static Map<String, Class<? extends IDataType>> dataClasses = Maps.newHashMap();
	private static Map<String, String> dataLocs = Maps.newHashMap();
	private static Map<String, IDataType> dataMap = Maps.newHashMap();

	public static void registerData(String name, Class<? extends IDataType> data) {
		dataClasses.put(name, data);
		dataLocs.put(name, String.format("%s.json", name));
	}

	public static void registerData(String name, String loc, Class<? extends IDataType> data) {
		dataClasses.put(name, data);
		dataLocs.put(name, String.format("%s/%s.json", loc, name));
	}

	public static <T extends IDataType> T getData(String name, Class<T> type) {
		return type.cast(dataMap.get(name));
	}

	public static void loadData() {
		dataClasses.forEach((name, clazz) -> {
			try {
				dataMap.put(name, JarviX.gson.fromJson(new FileReader(new File(JarviX.CONFIG, dataLocs.get(name))), clazz));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(String.format("the file (%s) doesn't seem to exist", dataLocs.get(name)), e);
			}
		});
	}

	public static void saveData() {
		dataMap.keySet().forEach(DataHandler::saveData);
	}

	public static void saveData(String name) {
		try {
			File json = new File(JarviX.CONFIG, dataLocs.get(name));
			File backup = new File(JarviX.CONFIG, String.format("backup/%s", dataLocs.get(name)));
			Files.createParentDirs(backup);
			Files.copy(json, backup);
			Writer writer = new FileWriter(json);
			writer.write(JarviX.gson.toJson(dataMap.get(name)));
			writer.close();
		} catch (IOException e) {
			//TODO: log
		}
	}
}
