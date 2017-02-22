package com.tactfactory.nikoniko;

import java.util.Date;

import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.manager.database.manager.ProjectDBManager;
import com.tactfactory.nikoniko.manager.database.manager.TeamDBManager;
import com.tactfactory.nikoniko.manager.database.manager.UserDBManager;
import com.tactfactory.nikoniko.models.*;

public class Application {

	public static void main(String[] args) {
		/*User u1 = new User();
		User u2 = new User();

		Team t1 = new Team();
		u1.getTeams().add(t1);
		u2.getTeams().add(t1);
		t1.getUsers().add(u1);
		t1.getUsers().add(u2);

		Project p1 = new Project();
		p1.getTeams().add(t1);
		t1.getProjects().add(p1);*/


//		NikoNiko niko = new NikoNiko();
//		niko.setSatisfaction(1);
//
//		NikoNikoDBManager nikonikoManager = new NikoNikoDBManager();
//		nikonikoManager.insert(niko);
//
//		User user = new User("login","password","lastname","firstname","test");
//		UserDBManager userManager = new UserDBManager();
//		userManager.insert(user);
//
//		Team team = new Team("team1","serial1");
//		TeamDBManager teamManager = new TeamDBManager();
//		teamManager.insert(team);
//
//		Project project = new Project("project1",new Date());
//		ProjectDBManager projectManager = new ProjectDBManager();
//		projectManager.insert(project);

//		//Team to project
//		Team team = new Team("team1", "serial1");
//		for (int i = 0; i < 10; i++) {
//			team.getProjects().add(new Project("project"+i,new Date()));
//		}
//
//		TeamDBManager teamManager = new TeamDBManager();
//		teamManager.insert(team);
//		teamManager.insertProjects(team);

		//Project to team
		Project project = new Project("projectX",new Date());
		for (int i = 0; i < 10; i++) {
			project.getTeams().add(new Team("teamX"+i, "teamXSerial"));
		}

		ProjectDBManager projectManager = new ProjectDBManager();
		projectManager.insertWithChild(project);

		/*NikoNikoManager.createNikoNikoConsole(u1, p1);

		NikoNikoManager.showAllNikoNikoForProject(p1);
		NikoNikoManager.showTeamStateForProject(p1);*/
	}
}
