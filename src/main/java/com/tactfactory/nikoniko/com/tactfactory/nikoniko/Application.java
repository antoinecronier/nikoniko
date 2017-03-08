package com.tactfactory.nikoniko;

import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;

public class Application {

	public static void main(String[] args) {
		
		System.out.println("Hello !");
		
		// Test de getAssociateObject
		
		NikoNiko niko = new NikoNiko();
		NikoNikoDBManager nikoManager = new NikoNikoDBManager();
		
		niko.setId(1);
		
		nikoManager.getAssociateObject(niko);
		
		System.out.println(niko.getProject().getName());
		
		

	}

}
