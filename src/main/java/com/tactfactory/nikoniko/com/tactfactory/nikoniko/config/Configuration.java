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

	private static Configuration INSTANCE = null;

	public static final String FILE_DEV  = "local.properties";
	public static final String FILE_TEST = "test.properties";

	public static Configuration getInstance() {
	    return getInstance("dev");
    }

	public static Configuration getInstance(String environment) {
	    if (INSTANCE == null) {
	        INSTANCE = new Configuration(environment);
	    }

	    return INSTANCE;
	}

	public Map<String, String> getMap() {
		if (this.map != null && this.map.size() == 0) {
			throw new InvalidParameterException("No parameters (see local.properties.dist for example).");
		}

		return map;
	}

	private Configuration(String environment) {
	    // TODO Verification du fichier de propriete (local.properties <-> local.properties.dist).
	    // TODO Peut etre une gestion de valeur par defaut (si clef mais pas de valeur)
	    String confFile = (environment == "test" ? FILE_TEST : FILE_DEV);
		String workingDir = System.getProperty("user.dir");
		String path = workingDir +"/"+ confFile;
		String regex = new String("([\\w_]+)\\s*=(?:\\s*([\\w_.]+)(?:\\s*#.*)?)?");//permet les espace avant et après les =
		String thisLine = null;
		Pattern p = Pattern.compile(regex);

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			// open input stream test.txt for reading purpose.
			while ((thisLine = br.readLine()) != null) {
				Matcher m = p.matcher(thisLine);

				if (m.matches()) {
					// pour chaque groupe
					String value = m.group(2);
					
					if (value == null) {
						value = "";
					}
					
					map.put(m.group(1), value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
