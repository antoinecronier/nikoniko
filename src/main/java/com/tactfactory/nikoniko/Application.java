package com.tactfactory.nikoniko;

import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.User;

public class Application {

	public static void main(String[] args) {
		NikoNiko niko = new NikoNiko(new User(), new Project(), 2);
		NikoNikoDBManager manager = new NikoNikoDBManager();
		manager.insert(niko);
	}

}
