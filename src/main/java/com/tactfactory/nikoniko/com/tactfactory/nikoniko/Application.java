package com.tactfactory.nikoniko;

import java.util.Date;

import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;

public class Application {

	public static void main(String[] args) {
		
		System.out.println("Hello !");
		
		// Test de getAssociateObject
		
		NikoNiko niko = new NikoNiko();
		NikoNikoDBManager nikoManager = new NikoNikoDBManager();
		
		niko.setId(6);
		
		nikoManager.getAssociateObject(niko);
		
		System.out.println(niko.getProject().getName());
		
		String dateString = "1992-08-02";
		
		Date date = new Date(dateString);
		
		System.out.println(date.getDate());
	}

}
