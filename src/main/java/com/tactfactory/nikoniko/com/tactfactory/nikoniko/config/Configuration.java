package com.tactfactory.nikoniko.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class Configuration {
	private ArrayList<String> list;
	private Map<String,String> map = new HashMap<String,String>();
	
    public Map<String, String> getMap() {
		return map;
	}

	private static Pattern pattern;
    private static Matcher matcher;
	
	public Configuration() {
		String workingDir = System.getProperty("user.dir");
		String path = workingDir+"/local.properties";
		String regex = new String("([\\w_]+)\\s*=\\s*([\\w_.]+)(?:\\s*#.*)?");//permet les espace avant et après les =
		//String regex = new String("([\\w_]+)=([\\w_.]+)(?:\\s*#.*)?");// ne le permet pas
		String thisLine = null;
		Pattern p = Pattern.compile(regex);
		try {
			FileReader myFile = new FileReader(path);
			// open input stream test.txt for reading purpose.
			BufferedReader br = new BufferedReader(myFile);
			while ((thisLine = br.readLine()) != null) {
				Matcher m = p.matcher(thisLine);
				boolean b = m.matches();
				if(b) {
				    // pour chaque groupe
					map.put(m.group(1), m.group(2));
				}
				
				//this.list.add(thisLine);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(String key : map.keySet()) {
			System.out.println("key="+key + ", value=" + map.get(key));
		}
	}
}
