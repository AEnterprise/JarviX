package jarvix.data;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jarvix.JarviX;

public abstract class JsonData {

	private String subFolder = "";
	private Gson gson;
	private File jsonFile;
	private IDataType data;

	public void init() {
		gson = new GsonBuilder().setPrettyPrinting().create();
		jsonFile = new File(JarviX.CONFIG, getSubFolder() + getName() + ".json");
		readJson();
	}

	public abstract String getName();

	public abstract Class<? extends IDataType> getRootDataType();

	public JsonData setSubFolder(String subFolder) {
		this.subFolder = subFolder;
		return this;
	}

	public String getSubFolder() {
		return (Strings.isNullOrEmpty(subFolder) ? "" : subFolder + File.separator);
	}

	public File jsonFile() {
		return jsonFile;
	}

	public void readJson() {
		try {
			data = gson.fromJson(new FileReader(jsonFile()), getRootDataType());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IDataType data() {
		if (data == null)
			readJson();

		return data;
	}

	public void saveData() {
		try {
			File backupFile = new File(jsonFile.getParentFile(), "backup" + File.separator + getName() + ".json");
			Files.createParentDirs(backupFile);
			Files.copy(jsonFile, backupFile);
			Writer writer = new FileWriter(jsonFile);
			writer.write(gson.toJson(data()));
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
