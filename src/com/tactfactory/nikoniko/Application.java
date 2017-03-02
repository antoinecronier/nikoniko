package com.tactfactory.nikoniko;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.manager.database.manager.ProjectDBManager;
import com.tactfactory.nikoniko.manager.database.manager.TeamDBManager;
import com.tactfactory.nikoniko.manager.database.manager.UserDBManager;
import com.tactfactory.nikoniko.models.*;
import com.tactfactory.nikoniko.utils.DatabasePurjer;
import com.tactfactory.nikoniko.utils.DumpFields;
import com.tactfactory.nikoniko.utils.mysql.MySQLAnnotation;

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
//		teamManager.insert(team);
//
//		// Project to team
//		for (int i = 0; i < 10; i++) {
//			project.getTeams().add(new Team("teamX" + i, "teamXSerial"));
//		}
//
//		projectManager.insert(project);
//
//		// Team user linking
//		for (int i = 0; i < 10; i++) {
//			User userTmp = new User("login" + i, "password" + i,
//					"lastname" + i, "firstname" + i, "registration" + i);
//			userManager.insert(userTmp);
//			teamManager.<User> mapRelation(team, userTmp);
//		}
//
//		// User team linking
//		for (int i = 0; i < 10; i++) {
//			Team tmpTeam = new Team("teamV" + i, "serialV" + i);
//			teamManager.insert(tmpTeam);
//			userManager.<Team> mapRelation(user, tmpTeam);
//		}
//
//		User newUser = userManager.getById(user);
//		ArrayList<User> users = userManager.getAll();
//
//		for (int i = 0; i < 10; i++) {
//			nikonikoManager.insert(new NikoNiko(user, project, i % 3 + 1));
//		}
//
//		User userSelect = new User();
//		userSelect.setId(user.getId());
//		userManager.getAssociateObject(userSelect);
//
//		Project projectSelect = new Project();
//		projectSelect.setId(project.getId());
//		projectManager.getAssociateObject(projectSelect);
//
//		Team teamSelect = new Team();
//		teamSelect.setId(team.getId());
//		teamManager.getAssociateObject(teamSelect);
//
//		int a = 0;
//		a++;
//
//		NikoNikoDBManager nikoNikoDBManager = new NikoNikoDBManager();
//
//		nikoNikoDBManager.insert(new NikoNiko(new User(), new Project(), 1));
//
//		NikoNiko nikoniko = new NikoNiko();
//
//		System.out.println(nikoniko.getLog_date());
//
//		nikoNikoDBManager.getById(new NikoNiko());
//
//		ArrayList<String> result = DumpFields.inspectGetter(Project.class);
//		System.out.println(result);
//
//		ArrayList<String> result1 = DumpFields.inspectBaseAttribut(User.class);
//		System.out.println(result1);
//
//		System.out.println(nikoNikoDBManager.getById(niko));
//
//		NikoNikoDBManager nikonikoDbManager = new NikoNikoDBManager();
//		nikonikoDbManager.insert(new NikoNiko(new User(), new Project(), 1));
//
//		NikoNiko newNiko = new NikoNiko();
//		newNiko = nikonikoDbManager.getById((newNiko));
//		System.out.println(newNiko.getId() + " " + newNiko.getSatisfaction());
//
//		// creer un objet de type T -> DumpFields.createContentsEmpty()
//		// .<T> inspectedBAseAttribute
//		// utilisation de Map
//		NikoNiko newNiko1 = new NikoNiko();
//		Project proj1 = DumpFields.createContentsEmpty(Project.class);
//
//		/* Test on inspectBaseAttribut */
//		/* --------------------------- */
//		ArrayList<String> attributs = new ArrayList<String>();
//		attributs = DumpFields.inspectBaseAttribut(NikoNiko.class);
//
//		System.out.println("NbAttr " + attributs.size());
//		for (String attribut : attributs) {
//			System.out.println("attribut " + attribut);
//		}
//
//		/* Test on getClassesNames */
//		/* --------------------- */
//		ArrayList<String> classNames = new ArrayList<>();
//		try {
//			classNames = DumpFields
//					.getClassesNames("com.tactfactory.nikoniko.models");
//		} catch (ClassNotFoundException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("NbClass " + classNames.size());
//		for (String className : classNames) {
//			System.out.println("Class " + className);
//		}

		NikoNikoDBManager nikonikoDBManager = new NikoNikoDBManager();
		NikoNiko niko = new NikoNiko(new User("test", "test"), new Project("test", new Date()), 2, "blabla", true);
		nikonikoDBManager.insert(niko);

		NikoNiko niko1 = new NikoNiko();
		niko1.setId(niko.getId());

		nikonikoDBManager.getById(niko1);
		System.out.println(niko1);
//		for (Field field : DumpFields.getFields(NikoNiko.class)) {
//			try {
//				System.out.println(field.getName() + " : " +field.get(niko));
//			} catch (IllegalArgumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("  SQL Type : " + field.getAnnotation(MySQLAnnotation.class).mysqlType());
//			System.out.println("  Nullable? : " + field.getAnnotation(MySQLAnnotation.class).nullable());
//			System.out.println("  SQL Name : " + field.getAnnotation(MySQLAnnotation.class).fieldName());
//		}
	}
}
