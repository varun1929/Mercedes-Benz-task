package com.MercedesBenz.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtil {

	public boolean addFirstAndLastIndexInTextFile(List<String> priceList, String browserName) {
		boolean fileWriteSuccessful = false;
		try {
			String path = System.getProperty("user.dir") + "/textfile/" + browserName + "-" + System.currentTimeMillis() + ".txt";

			File directory = new File(System.getProperty("user.dir") + "/textfile/");
			if (!directory.exists()) {
				directory.mkdirs();
			}

			File destination = new File(path);
			FileWriter writer = new FileWriter(destination);
			writer.write("Highest: " + priceList.get(priceList.size() - 1));
			writer.write("\nLowest: " + priceList.get(0));
			writer.close();
			fileWriteSuccessful = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileWriteSuccessful;
	}
}
