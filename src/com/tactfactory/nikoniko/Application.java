package com.tactfactory.nikoniko;


import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.Date;

import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.manager.database.manager.ProjectDBManager;
import com.tactfactory.nikoniko.manager.database.manager.TeamDBManager;
import com.tactfactory.nikoniko.manager.database.manager.UserDBManager;
import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.*;
//import com.tactfactory.nikoniko.utils.DatabasePurjer;
//import com.tactfactory.nikoniko.utils.DumpFields;
//import com.tactfactory.nikoniko.utils.mysql.MySQLAnnotation;

public class Application {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		
		NikoNikoDBManager nikoManager = new NikoNikoDBManager();
		UserDBManager userManager = new UserDBManager();
		NikoNiko niko = new NikoNiko();
		Team team = new Team();
		niko.setId(4);
		User user = new  User();
		user.setId(12);
		niko.setUser(user);
		Project project = new Project();
		project.setId(15);
		//niko.setProject(project);
		project.getNikoNikos().add(niko);
		//user.getNikoNikos().add(niko);
		userManager.getAssociatedObject(user);
		nikoManager.getAssociatedObject(niko);
		
		}
	
}

