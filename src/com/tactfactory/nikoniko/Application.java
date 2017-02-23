package com.tactfactory.nikoniko;

import java.util.ArrayList;
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
		NikoNikoDBManager nikonikoManager = new NikoNikoDBManager();
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
//
//		//Team to project
//		Team team = new Team("team1", "serial1");
//		for (int i = 0; i < 10; i++) {
//			team.getProjects().add(new Project("project"+i,new Date()));
//		}
//
		TeamDBManager teamManager = new TeamDBManager();
//		teamManager.insert(team);
//		teamManager.insertProjects(team);
//
//		//Project to team
//		Project project = new Project("projectX",new Date());
//		for (int i = 0; i < 10; i++) {
//			project.getTeams().add(new Team("teamX"+i, "teamXSerial"));
//		}
//
		ProjectDBManager projectManager = new ProjectDBManager();
//		projectManager.insertWithChild(project);
//		
//		//Team user linking
		UserDBManager userManager = new UserDBManager();
////		TeamDBManager teamManager = new TeamDBManager();
//		
//		User user1 = new User("loginM","passwordM","lastnameM","firstnameM","registrationM");
//		userManager.insert(user1);
//		
//		
//		
//		for (int i = 0; i < 10; i++) {
//			Team tmpTeam = new Team("teamV"+i,"SerialV"+i);
//			teamManager.insert(tmpTeam);
//			userManager.insertRelationTeam(user1, tmpTeam);
//		}
//		
//		//User team linking
////		UserDBManager userManager = new UserDBManager();
////		TeamDBManager teamManager = new TeamDBManager();
//		
//		Team team1 = new Team("teamV","SerialV");
//		teamManager.insert(team1);
//		
//		
//		
//		for (int i = 0; i < 10; i++) {
//			User tmpUser = new User("loginM"+i,"passwordM"+i,"lastnameM"+i,
//									"firstnameM"+i,"registrationM"+i);
//			userManager.insert(tmpUser);
//			teamManager.insertRelationUser(tmpUser, team1);
//		}
		
		
//		//create new niko
//		User user2 = new User("loginMMM","passwordMMM","lastnameMMM","firstnameMMM","registrationMMM");
//		userManager.insert(user2);
//
//		Project project2 = new Project("projectMMM",new Date());
//		projectManager.insert(project2);
//
//		
//		for (int i = 0; i < 10; i++) {
//			NikoNiko niko = new NikoNiko(user2,project2,1,"commentaire"+i);
//			nikonikoManager.insert(niko);
//		}
		
		
//		//verif que getUserById fonctionne
//		User user = userManager.getUserById(5);
//		System.out.println(user.getLastname());
//		System.out.println(user.getFirstname());
//		System.out.println(user.getId());
//		
//		//verif que getAllUser fonctionne
//		ArrayList<User> userS = userManager.getAllUser();
//		System.out.println(userS.get(4).getLastname());
//
//		
//		
//		
//		//verif que getTeamById fonctionne
//		Team team = teamManager.getTeamById(5);
//		System.out.println(team.getSerial());
//		System.out.println(team.getProjects());
//		System.out.println(team.getId());
//		
//		//verif que getAllTeam fonctionne
//		ArrayList<Team> teamS = teamManager.getAllTeam();
//		System.out.println(teamS.get(4).getSerial());
//
//		
//		
//		
//		//verif que getProjectById fonctionne
//		Project project = projectManager.getProjectById(5);
//		System.out.println(project.getName());
//		System.out.println(project.getStart_date());
//		System.out.println(project.getId());
//		
//		//verif que getAllUser fonctionne
//		ArrayList<Project> projectS = projectManager.getAllProject();
//		System.out.println(projectS.get(4).getName());

		
		
		//verif que getNikoNikoById fonctionne
		NikoNiko niko = nikonikoManager.getNikoNikoById(4);
		System.out.println(niko.getComment());
		System.out.println(niko.getSatisfaction());
		System.out.println(niko.getId());
		System.out.println(niko.getUser().getId());
		
//		//verif que getAllUser fonctionne
//		ArrayList<NikoNiko> nikoS = nikonikoManager.getAllNikoNiko();
//		System.out.println(nikoS.get(4).getComment());

		/*NikoNikoManager.createNikoNikoConsole(u1, p1);

		NikoNikoManager.showAllNikoNikoForProject(p1);
		NikoNikoManager.showTeamStateForProject(p1);*/
	}
}
