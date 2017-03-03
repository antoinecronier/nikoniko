package com.tactfactory.nikoniko;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.manager.database.manager.TeamDBManager;
import com.tactfactory.nikoniko.manager.database.manager.UserDBManager;
import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.Team;
import com.tactfactory.nikoniko.models.User;
import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;
import com.tactfactory.nikoniko.utils.DumpFields;
import com.tactfactory.nikoniko.utils.mysql.MySQLAnnotation;
import com.tactfactory.nikoniko.utils.mysql.MySQLTypes;

public class Application {

	public static void main(String[] args) {
		
//			 
//			/*
//			 * User u1 = new User(); User u2 = new User();
//			 *
//			 * Team t1 = new Team(); u1.getTeams().add(t1); u2.getTeams().add(t1);
//			 * t1.getUsers().add(u1); t1.getUsers().add(u2);
//			 *
//			 * Project p1 = new Project(); p1.getTeams().add(t1);
//			 * t1.getProjects().add(p1);
//			 */
//
//			DatabasePurjer.purjeDatabase();
//
//			NikoNiko niko = new NikoNiko();
//			niko.setSatisfaction(1);
//
//			NikoNikoDBManager nikonikoManager = new NikoNikoDBManager();
//			nikonikoManager.insert(niko);
//
//			User user = new User("login", "password", "lastname", "firstname", "test");
//			UserDBManager userManager = new UserDBManager();
//			userManager.insert(user);
//
//			Team team = new Team("team1", "serial1");
//			TeamDBManager teamManager = new TeamDBManager();
//			teamManager.insert(team);
//
//			Project project = new Project("project1", new Date());
//			ProjectDBManager projectManager = new ProjectDBManager();
//			projectManager.insert(project);
//
//			// Team to project
//			for (int i = 0; i < 10; i++) {
//				team.getProjects().add(new Project("project" + i, new Date()));
//			}
//
//			teamManager.insert(team);
//
//			// Project to team
//			for (int i = 0; i < 10; i++) {
//				project.getTeams().add(new Team("teamX" + i, "teamXSerial"));
//			}
//
//			projectManager.insert(project);
//
//			// Team user linking
//			for (int i = 0; i < 10; i++) {
//				User userTmp = new User("login" + i, "password" + i, "lastname" + i, "firstname" + i, "registration" + i);
//				userManager.insert(userTmp);
//				teamManager.<User>mapRelation(team, userTmp);
//			}
//
//			// User team linking
//			for (int i = 0; i < 10; i++) {
//				Team tmpTeam = new Team("teamV" + i, "serialV" + i);
//				teamManager.insert(tmpTeam);
//				userManager.<Team>mapRelation(user, tmpTeam);
//			}
//
//			User newUser = userManager.getById(user);
//			ArrayList<User> users = userManager.getAll();
//
//			for (int i = 0; i < 10; i++) {
//				nikonikoManager.insert(new NikoNiko(user, project, i % 3 + 1));
//			}
//
//			User userSelect = new User();
//			userSelect.setId(user.getId());
//			userManager.getAssociateObject(userSelect);
//
//			Project projectSelect = new Project();
//			projectSelect.setId(project.getId());
//			projectManager.getAssociateObject(projectSelect);
//
//			Team teamSelect = new Team();
//			teamSelect.setId(team.getId());
//			teamManager.getAssociateObject(teamSelect);
//
//			int a = 0;
//			a++;
//
//			NikoNikoDBManager nikoNikoDBManager = new NikoNikoDBManager();
//
//			nikoNikoDBManager.insert(new NikoNiko(new User(), new Project(), 1));
//
//			NikoNiko nikoniko = new NikoNiko();
//
//			System.out.println(nikoniko.getLog_date());
//
//			nikoNikoDBManager.getById(new NikoNiko());
//
//			ArrayList<String> result = DumpFields.inspectGetter(Project.class);
//			System.out.println(result);
//
//			ArrayList<String> result1 = DumpFields.inspectBaseAttribut(User.class);
//			System.out.println(result1);
//
//			System.out.println(nikoNikoDBManager.getById(new NikoNiko()).toString());
//
//			NikoNikoDBManager nikonikoDbManager = new NikoNikoDBManager();
//			nikonikoDbManager.insert(new NikoNiko(new User(), new Project(), 1));
//
//			NikoNiko newNiko = new NikoNiko();
//			newNiko = nikonikoDbManager.getById(newNiko);
//			System.out.println(newNiko.getId() + " " + newNiko.getSatisfaction());
//
//			// creer un objet de type T -> DumpFields.createContentsEmpty()
//			// .<T> inspectedBAseAttribute
//			// utilisation de Map
//			NikoNiko newNiko1 = new NikoNiko();
//			Project proj1 = DumpFields.createContentsEmpty(Project.class);
//
//			/* Test on inspectBaseAttribut */
//			/* --------------------------- */
//			ArrayList<String> attributs = new ArrayList<String>();
//			attributs = DumpFields.inspectBaseAttribut(NikoNiko.class);
//
//			System.out.println("NbAttr " + attributs.size());
//			for (String attribut : attributs) {
//				System.out.println("attribut " + attribut);
//			}
//
//			/* Test on getClassesNames */
//			/* --------------------- */
//			ArrayList<String> classNames = new ArrayList<>();
//			try {
//				classNames = DumpFields.getClassesNames("com.tactfactory.nikoniko.models");
//			} catch (ClassNotFoundException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("NbClass " + classNames.size());
//			for (String className : classNames) {
//				System.out.println("Class " + className);
//			}
//			
//			
//			//test getALL
//			
//			ArrayList<NikoNiko> maliste = new ArrayList();
//			
//			// /*
//			// * User u1 = new User(); User u2 = new User();
//			// *
//			// * Team t1 = new Team(); u1.getTeams().add(t1); u2.getTeams().add(t1);
//			// * t1.getUsers().add(u1); t1.getUsers().add(u2);
//			// *
//			// * Project p1 = new Project(); p1.getTeams().add(t1);
//			// * t1.getProjects().add(p1);
//			// */
//			//
//			// DatabasePurjer.purjeDatabase();
//			//
//			// NikoNiko niko = new NikoNiko();
//			// niko.setSatisfaction(1);
//			//
//			// NikoNikoDBManager nikonikoManager = new NikoNikoDBManager();
//			// nikonikoManager.insert(niko);
//			//
//			// User user = new User("login", "password", "lastname", "firstname",
//			// "test");
//			// UserDBManager userManager = new UserDBManager();
//			// userManager.insert(user);
//			//
//			// Team team = new Team("team1", "serial1");
//			// TeamDBManager teamManager = new TeamDBManager();
//			// teamManager.insert(team);
//			//
//			// Project project = new Project("project1", new Date());
//			// ProjectDBManager projectManager = new ProjectDBManager();
//			// projectManager.insert(project);
//			//
//			// // Team to project
//			// for (int i = 0; i < 10; i++) {
//			// team.getProjects().add(new Project("project" + i, new Date()));
//			// }
//			//
//			// teamManager.insert(team);
//			//
//			// // Project to team
//			// for (int i = 0; i < 10; i++) {
//			// project.getTeams().add(new Team("teamX" + i, "teamXSerial"));
//			// }
//			//
//			// projectManager.insert(project);
//			//
//			// // Team user linking
//			// for (int i = 0; i < 10; i++) {
//			// User userTmp = new User("login" + i, "password" + i, "lastname" + i,
//			// "firstname" + i, "registration" + i);
//			// userManager.insert(userTmp);
//			// teamManager.<User>mapRelation(team, userTmp);
//			// }
//			//
//			// // User team linking
//			// for (int i = 0; i < 10; i++) {
//			// Team tmpTeam = new Team("teamV" + i, "serialV" + i);
//			// teamManager.insert(tmpTeam);
//			// userManager.<Team>mapRelation(user, tmpTeam);
//			// }
//			//
//			// User newUser = userManager.getById(user);
//			// ArrayList<User> users = userManager.getAll();
//			//
//			// for (int i = 0; i < 10; i++) {
//			// nikonikoManager.insert(new NikoNiko(user, project, i % 3 + 1));
//			// }
//			//
//			// User userSelect = new User();
//			// userSelect.setId(user.getId());
//			// userManager.getAssociateObject(userSelect);
//			//
//			// Project projectSelect = new Project();
//			// projectSelect.setId(project.getId());
//			// projectManager.getAssociateObject(projectSelect);
//			//
//			// Team teamSelect = new Team();
//			// teamSelect.setId(team.getId());
//			// teamManager.getAssociateObject(teamSelect);
//			//
//			// int a = 0;
//			// a++;
//			//
//			// NikoNikoDBManager nikoNikoDBManager = new NikoNikoDBManager();
//			//
//			// nikoNikoDBManager.insert(new NikoNiko(new User(), new Project(), 1));
//			//
//			// NikoNiko nikoniko = new NikoNiko();
//			//
//			// System.out.println(nikoniko.getLog_date());
//			//
//			// nikoNikoDBManager.getById(new NikoNiko());
//			//
//			// ArrayList<String> result = DumpFields.inspectGetter(Project.class);
//			// System.out.println(result);
//			//
//			// ArrayList<String> result1 =
//			// DumpFields.inspectBaseAttribut(User.class);
//			// System.out.println(result1);
//			//
//			// System.out.println(nikoNikoDBManager.getById(new
//			// NikoNiko()).toString());
//			//
//			// NikoNikoDBManager nikonikoDbManager = new NikoNikoDBManager();
//			// nikonikoDbManager.insert(new NikoNiko(new User(), new Project(), 1));
//			//
//			// NikoNiko newNiko = new NikoNiko();
//			// newNiko = nikonikoDbManager.getById(newNiko);
//			// System.out.println(newNiko.getId() + " " +
//			// newNiko.getSatisfaction());
//			//
//			// // creer un objet de type T -> DumpFields.createContentsEmpty()
//			// // .<T> inspectedBAseAttribute
//			// // utilisation de Map
//			// NikoNiko newNiko1 = new NikoNiko();
//			// Project proj1 = DumpFields.createContentsEmpty(Project.class);
//			//
//			// /* Test on inspectBaseAttribut */
//			// /* --------------------------- */
//			// ArrayList<String> attributs = new ArrayList<String>();
//			// attributs = DumpFields.inspectBaseAttribut(NikoNiko.class);
//			//
//			// System.out.println("NbAttr " + attributs.size());
//			// for (String attribut : attributs) {
//			// System.out.println("attribut " + attribut);
//			// }
//			//
//			// /* Test on getClassesNames */
//			// /* --------------------- */
//			// ArrayList<String> classNames = new ArrayList<>();
//			// try {
//			// classNames =
//			// DumpFields.getClassesNames("com.tactfactory.nikoniko.models");
//			// } catch (ClassNotFoundException | IOException e) {
//			// // TODO Auto-generated catch block
//			// e.printStackTrace();
//			// }
//			// System.out.println("NbClass " + classNames.size());
//			// for (String className : classNames) {
//			// System.out.println("Class " + className);
//			// }
//
//			// test getALL
//
//			ArrayList<NikoNiko> maliste = new ArrayList<NikoNiko>();
//
//	 		NikoNikoDBManager NikoManager = new NikoNikoDBManager();
//	 		maliste = NikoManager.getAll();
//			
//
//	 		for (NikoNiko nikoNiko : maliste) {
//	 			System.out.println(nikoNiko);
//			}
//			
//			NikoNikoDBManager nikonikoDBManager = new NikoNikoDBManager();
//			NikoNiko niko = new NikoNiko(new User("test", "test"), new Project("test", new Date()), 2, "blabla", true);
//			nikonikoDBManager.insert(niko);
//	 
//			NikoNiko niko1 = new NikoNiko();
//			niko1.setId(niko.getId());
//			NikoNikoDBManager nikonikoDBManager = new NikoNikoDBManager();
//			NikoNiko niko = new NikoNiko(new User("test", "test"), new Project("test", new Date()), 2, "blabla", true);
//			nikonikoDBManager.insert(niko);
//
//			NikoNiko niko1 = new NikoNiko();
//			niko1.setId(niko.getId());
//
//			nikonikoDBManager.getById(niko1);
//			System.out.println(niko1);
//	 
//			nikonikoDBManager.getById(niko1);
//			System.out.println(niko1);
//			
//			for (Field field : DumpFields.getFields(NikoNiko.class)) {
//				try {
//					System.out.println(field.getName() + " : " +field.get(niko));
//				} catch (IllegalArgumentException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println("  SQL Type : " + field.getAnnotation(MySQLAnnotation.class).mysqlType());
//				System.out.println("  Nullable? : " + field.getAnnotation(MySQLAnnotation.class).nullable());
//				System.out.println("  SQL Name : " + field.getAnnotation(MySQLAnnotation.class).fieldName());
//			}
//			
//			}
			// for (Field field : DumpFields.getFields(NikoNiko.class)) {
			// try {
			// System.out.println(field.getName() + " : " +field.get(niko));
			// } catch (IllegalArgumentException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// } catch (IllegalAccessException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// System.out.println(" SQL Type : " +
			// field.getAnnotation(MySQLAnnotation.class).mysqlType());
			// System.out.println(" Nullable? : " +
			// field.getAnnotation(MySQLAnnotation.class).nullable());
			// System.out.println(" SQL Name : " +
			// field.getAnnotation(MySQLAnnotation.class).fieldName());
			// }
		
		User item = new User();
		item.setId(1);
		
		NikoNiko nikoniko = new NikoNiko ();
		NikoNiko nikoniko2 = new NikoNiko ();
		
		nikoniko.setId(152);
		nikoniko.setSatisfaction(1);
		nikoniko.setIsAnonymous(false);
		
		nikoniko2.setId(215);
		nikoniko2.setSatisfaction(1);
		nikoniko2.setIsAnonymous(false);
	
		Team team = new Team();
		team.setId(531161);
		team.setName("nommmmm");
		
		item.getNikoNikos().add(nikoniko);
		item.getNikoNikos().add(nikoniko2);
		item.getTeams().add(team);	
		
		Class child = nikoniko2.getClass();
		
		for (Field field : DumpFields.getFields(item.getClass())) {
					

			
			if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION){

				//recupere le path de la classe DBManager associee à item
				String className = "";
				String classSimpleName = ""+ ((ArrayList<DatabaseItem>)DumpFields.runGetter(field, item)).get(0).getClass().getSimpleName();
				
				
				className += "com.tactfactory.nikoniko.manager.database.manager.";
				className += classSimpleName ;
				className += "DBManager";
				
				Object dbmanager = new Object();
				//on instancie le manager
				try {
					//
					Class ObjetClas = Class.forName(className);
					dbmanager = DumpFields.createContentsEmpty(ObjetClas);
					
					} catch (ClassNotFoundException e) {
					e.printStackTrace();
					//on break pour eviter de realiser tout le code dans le try ( rien n'est fait si catch)
					break;
				}
			
			//((BaseDBManager)dbmanager).getValues(item);
				
			System.out.println(((ArrayList<DatabaseItem>)DumpFields.runGetter(field, item)).size());
		
			ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
			Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
			
			for (int i = 0; i < ((ArrayList<DatabaseItem>)DumpFields.runGetter(field, item)).size(); i++) {
		
				System.out.println(((ArrayList<DatabaseItem>)DumpFields.runGetter(field, item)).get(i).getId());
				String query = "";
				
				//query += userdeb.getValues((User)((ArrayList<DatabaseItem>)DumpFields.runGetter(field, item)).get(i));
				System.out.println("preums : "+((BaseDBManager)dbmanager).getValues(((stringListClass)((ArrayList<DatabaseItem>)DumpFields.runGetter(field, item))).get(i)) );
				
				System.out.println("deuz : "+ ((BaseDBManager)dbmanager).getValues(nikoniko));
				//System.out.println(DumpFields.toString(field, item));
			
			
				}
			}	
	 	}
	}
}