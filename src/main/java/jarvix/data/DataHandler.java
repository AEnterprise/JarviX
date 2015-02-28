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

	public static DataHandler INSTANCE = new DataHandler();

	private Map<String, Class<? extends IDataType>> dataClasses;
	private Map<String, String> dataLocs;
	private Map<String, IDataType> dataMap;

	public DataHandler() {
		dataClasses = Maps.newHashMap();
		dataLocs = Maps.newHashMap();
		dataMap = Maps.newHashMap();
	}

	public void registerData(String name, Class<? extends IDataType> data) {
		dataClasses.put(name, data);
		dataLocs.put(name, String.format("%s.json", name));
	}

	public void registerData(String name, String loc, Class<? extends IDataType> data) {
		dataClasses.put(name, data);
		dataLocs.put(name, String.format("%s/%s.json", loc, name));
	}

	public <T extends IDataType> T getData(String name, Class<T> type) {
		return type.cast(dataMap.get(name));
	}

	public void loadData() {
		dataClasses.forEach((name, clazz) -> {
			try {
				dataMap.put(name, JarviX.gson.fromJson(new FileReader(new File(JarviX.CONFIG, dataLocs.get(name))), clazz));
			} catch (FileNotFoundException e) {
				createDefaultData(name);
				loadData();
			}
		});
	}

	private void createDefaultData(String name) {
		File json = new File(JarviX.CONFIG, dataLocs.get(name));
		try {
			Files.createParentDirs(json);
			json.createNewFile();
			Writer writer = new FileWriter(json);
			writer.write(JarviX.gson.toJson(dataClasses.get(name).cast(dataClasses.get(name).newInstance())));
			writer.close();
		} catch (IOException | InstantiationException | IllegalAccessException e) {
			//TODO: log
		}
	}

	public void saveData() {
		dataMap.keySet().forEach(this::saveData);
	}

	public void saveData(String name) {
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
