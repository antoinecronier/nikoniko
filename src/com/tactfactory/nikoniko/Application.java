package com.tactfactory.nikoniko;

import java.util.ArrayList;
import java.util.Date;

import com.tactfactory.nikoniko.manager.NikoNikoManager;
import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.manager.database.manager.ProjectDBManager;
import com.tactfactory.nikoniko.manager.database.manager.TeamDBManager;
import com.tactfactory.nikoniko.manager.database.manager.UserDBManager;
import com.tactfactory.nikoniko.models.*;
import com.tactfactory.nikoniko.utils.DatabasePurjer;

public class Application {

	public static void main(String[] args) {

//		/*
//		 * User u1 = new User(); User u2 = new User();
//		 *
//		 * Team t1 = new Team(); u1.getTeams().add(t1); u2.getTeams().add(t1);
//		 * t1.getUsers().add(u1); t1.getUsers().add(u2);
//		 *
//		 * Project p1 = new Project(); p1.getTeams().add(t1);
//		 * t1.getProjects().add(p1);
//		 */
//
//		DatabasePurjer.purjeDatabase();
//
//		NikoNiko niko = new NikoNiko();
//		niko.setSatisfaction(1);
//
//		NikoNikoDBManager nikonikoManager = new NikoNikoDBManager();
//		nikonikoManager.insert(niko);
//
//		User user = new User("login", "password", "lastname", "firstname",
//				"test");
//		UserDBManager userManager = new UserDBManager();
//		userManager.insert(user);
//
//		Team team = new Team("team1", "serial1");
//		TeamDBManager teamManager = new TeamDBManager();
//		teamManager.insert(team);
//
//		Project project = new Project("project1", new Date());
//		ProjectDBManager projectManager = new ProjectDBManager();
//		projectManager.insert(project);
//
//		// Team to project
//		for (int i = 0; i < 10; i++) {
//			team.getProjects().add(new Project("project" + i, new Date()));
//		}
//
//		teamManager.insertProjects(team);
//
//		// Project to team
//		for (int i = 0; i < 10; i++) {
//			project.getTeams().add(new Team("teamX" + i, "teamXSerial"));
//		}
//
//		projectManager.insertTeams(project);
//
//		// Team user linking
//		for (int i = 0; i < 10; i++) {
//			User userTmp = new User("login" + i, "password" + i,
//					"lastname" + i, "firstname" + i, "registration" + i);
//			userManager.insert(userTmp);
//			teamManager.insertRelationUser(userTmp, team);
//		}
//
//		// User team linking
//		for (int i = 0; i < 10; i++) {
//			Team tmpTeam = new Team("teamV" + i, "serialV" + i);
//			teamManager.insert(tmpTeam);
//			userManager.insertRelationTeam(user, tmpTeam);
//		}
//
//		User newUser = userManager.getUserById(user.getId());
//		ArrayList<User> users = userManager.getAllUser();
//
//		for (int i = 0; i < 10; i++) {
//			nikonikoManager.insert(new NikoNiko(user,project,i%3+1));
//		}
//
//		User userSelect = new User();
//		userSelect.setId(user.getId());
//		userManager.getAssociatedNikoNiko(userSelect);
//		userManager.getAssociatedTeam(userSelect);
//
//		Project projectSelect = new Project();
//		projectSelect.setId(project.getId());
//		projectManager.getAssociatedNikoNiko(projectSelect);
//		projectManager.getAssociatedTeam(projectSelect);
//
//		Team teamSelect = new Team();
//		teamSelect.setId(team.getId());
//		teamManager.getAssociatedUser(teamSelect);
//		teamManager.getAssociatedProject(teamSelect);
//
//
//		int a = 0;
//		a++;


		// NikoNikoManager.createNikoNikoConsole(u1, p1);
		//
		// NikoNikoManager.showAllNikoNikoForProject(p1);
		// NikoNikoManager.showTeamStateForProject(p1);

		
		
		
		
		 
		
		NikoNikoDBManager nikonikoDBManager = new NikoNikoDBManager();
		nikonikoDBManager.insert(new NikoNiko(new User(), new Project(), 1));
		
		NikoNiko niko1 = nikonikoDBManager.getById(10, new NikoNiko());
		
		System.out.println(niko1.getComment());

	}
}
