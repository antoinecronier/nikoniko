package com.tactfactory.nikoniko.utils;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FromFile {
	private ArrayList<String> list;

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	
	public FromFile(String path)
	{
		String  thisLine = null;
		list = new ArrayList<String>(); 
  		try{
  			FileReader myFile = new FileReader(path);
  			// open input stream test.txt for reading purpose.
  			BufferedReader br = new BufferedReader(myFile);
			while ((thisLine = br.readLine()) != null) {
			     this.list.add(thisLine);
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		
	}
}
