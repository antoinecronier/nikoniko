package com.tactfactory.nikoniko;

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
		
		NikoNiko niko = new NikoNiko(new User(), new Project(), 2);
		NikoNikoDBManager manager = new NikoNikoDBManager();
		manager.insert(niko);
		System.out.println("id = "+ niko.getId());
		
		
	}

}
