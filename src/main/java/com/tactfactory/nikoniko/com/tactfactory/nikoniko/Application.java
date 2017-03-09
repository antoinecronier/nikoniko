package com.tactfactory.nikoniko;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertNotEquals;


import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.User;

public class Application {

	public static void main(String[] args) {
		
//		System.out.println("Hello !");
//		
//		// Test de getAssociateObject
//		
//		NikoNiko niko = new NikoNiko();
//		NikoNikoDBManager nikoManager = new NikoNikoDBManager();
//		
//		niko.setId(6);
//		
//		nikoManager.getAssociateObject(niko);
//		
//		System.out.println(niko.getProject().getName());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		
		
		
		
		
	}

}
