package com.tactfactory.nikoniko;

import static org.junit.Assert.assertNotEquals;

import java.sql.ResultSet;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.manager.database.manager.UserDBManager;
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
		char M = 'M';
		User user = new User("titi","grominet","toto","tutu","CGI42");
		user.setSex(M);
		UserDBManager manager = new UserDBManager();
		manager.insert(user);
		System.out.println(user);
		System.out.println(manager.getById(user));
		
		String query = "";
		query = "SELECT * FROM user WHERE id = 1";
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		manager.setObjectFromResultSet(result, user);
		System.out.println(user);
	}

}
