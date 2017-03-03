package com.tactfactory.nikoniko;

import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.manager.database.manager.ProjectDBManager;
import com.tactfactory.nikoniko.manager.database.manager.TeamDBManager;
import com.tactfactory.nikoniko.manager.database.manager.UserDBManager;
import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.Team;
import com.tactfactory.nikoniko.models.User;

public class Application {

	public static void main(String[] args) {
		
		User user1 = new User();
		user1.setId(1);
		
		Team team1 = new Team();
		team1.setId(3);
		
		Project project1 = new Project();
		project1.setId(2);
		
		NikoNiko niko1 = new NikoNiko();
		niko1.setId(6);
		
		NikoNikoDBManager nikoManager = new NikoNikoDBManager();
		UserDBManager userManager = new UserDBManager();
		ProjectDBManager projectManager = new ProjectDBManager();
		TeamDBManager teamManager = new TeamDBManager();
		
		nikoManager.deleteChildren(niko1, project1);
		

	}

}
