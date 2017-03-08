package com.tactfactory.nikoniko.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Configuration {
	private Map<String,String> map = new HashMap<String,String>();

	public Map<String, String> getMap() {
		if (this.getMap().size() == 0) {
			throw new InvalidParameterException("No parameters (see local.properties.dist for example).");
		}

		return map;
	}

	public Configuration() {
		String workingDir = System.getProperty("user.dir");
		String path = workingDir+"/local.properties";
		String regex = new String("([\\w_]+)\\s*=\\s*([\\w_.]+)(?:\\s*#.*)?");//permet les espace avant et après les =
		String thisLine = null;
		Pattern p = Pattern.compile(regex);

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			// open input stream test.txt for reading purpose.
			while ((thisLine = br.readLine()) != null) {
				Matcher m = p.matcher(thisLine);

				if (m.matches()) {
					// pour chaque groupe
					map.put(m.group(1), m.group(2));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
