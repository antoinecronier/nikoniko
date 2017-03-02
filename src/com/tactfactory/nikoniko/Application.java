package com.tactfactory.nikoniko;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.User;
import com.tactfactory.nikoniko.utils.DumpFields;

public class Application {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		
		NikoNikoDBManager nikoManager = new NikoNikoDBManager();
		NikoNiko niko = new NikoNiko();
		
		nikoManager.getAssociatedObject(niko);
		
		}
	
}

