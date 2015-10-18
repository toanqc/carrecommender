package com.bigdata.car.recommender.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author Toan Quach
 *
 */
public class PropertiesUtil {

	public static Properties getProperties(String fileName) {
		Properties props = new Properties();

		try (InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName)) {
			// load a properties file
			props.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return props;
	}
}
