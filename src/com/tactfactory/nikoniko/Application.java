package com.tactfactory.nikoniko;


import java.util.ArrayList;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.Team;
import com.tactfactory.nikoniko.models.User;



public class Application {

	public static void main(String[] args) {

		// /*
		// * User u1 = new User(); User u2 = new User();
		// *
		// * Team t1 = new Team(); u1.getTeams().add(t1); u2.getTeams().add(t1);
		// * t1.getUsers().add(u1); t1.getUsers().add(u2);
		// *
		// * Project p1 = new Project(); p1.getTeams().add(t1);
		// * t1.getProjects().add(p1);
		// */
		//
		// DatabasePurjer.purjeDatabase();
		//
		// NikoNiko niko = new NikoNiko();
		// niko.setSatisfaction(1);
		//
		// NikoNikoDBManager nikonikoManager = new NikoNikoDBManager();
		// nikonikoManager.insert(niko);
		//
		// User user = new User("login", "password", "lastname", "firstname",
		// "test");
		// UserDBManager userManager = new UserDBManager();
		// userManager.insert(user);
		//
		// Team team = new Team("team1", "serial1");
		// TeamDBManager teamManager = new TeamDBManager();
		// teamManager.insert(team);
		//
		// Project project = new Project("project1", new Date());
		// ProjectDBManager projectManager = new ProjectDBManager();
		// projectManager.insert(project);
		//
		// // Team to project
		// for (int i = 0; i < 10; i++) {
		// team.getProjects().add(new Project("project" + i, new Date()));
		// }
		//
		// teamManager.insert(team);
		//
		// // Project to team
		// for (int i = 0; i < 10; i++) {
		// project.getTeams().add(new Team("teamX" + i, "teamXSerial"));
		// }
		//
		// projectManager.insert(project);
		//
		// // Team user linking
		// for (int i = 0; i < 10; i++) {
		// User userTmp = new User("login" + i, "password" + i, "lastname" + i,
		// "firstname" + i, "registration" + i);
		// userManager.insert(userTmp);
		// teamManager.<User>mapRelation(team, userTmp);
		// }
		//
		// // User team linking
		// for (int i = 0; i < 10; i++) {
		// Team tmpTeam = new Team("teamV" + i, "serialV" + i);
		// teamManager.insert(tmpTeam);
		// userManager.<Team>mapRelation(user, tmpTeam);
		// }
		//
		// User newUser = userManager.getById(user);
		// ArrayList<User> users = userManager.getAll();
		//
		// for (int i = 0; i < 10; i++) {
		// nikonikoManager.insert(new NikoNiko(user, project, i % 3 + 1));
		// }
		//
		// User userSelect = new User();
		// userSelect.setId(user.getId());
		// userManager.getAssociateObject(userSelect);
		//
		// Project projectSelect = new Project();
		// projectSelect.setId(project.getId());
		// projectManager.getAssociateObject(projectSelect);
		//
		// Team teamSelect = new Team();
		// teamSelect.setId(team.getId());
		// teamManager.getAssociateObject(teamSelect);
		//
		// int a = 0;
		// a++;
		//
		// NikoNikoDBManager nikoNikoDBManager = new NikoNikoDBManager();
		//
		// nikoNikoDBManager.insert(new NikoNiko(new User(), new Project(), 1));
		//
		// NikoNiko nikoniko = new NikoNiko();
		//
		// System.out.println(nikoniko.getLog_date());
		//
		// nikoNikoDBManager.getById(new NikoNiko());
		//
		// ArrayList<String> result = DumpFields.inspectGetter(Project.class);
		// System.out.println(result);
		//
		// ArrayList<String> result1 =
		// DumpFields.inspectBaseAttribut(User.class);
		// System.out.println(result1);
		//
		// System.out.println(nikoNikoDBManager.getById(nikoniko).toString());
		//
		// NikoNikoDBManager nikonikoDbManager = new NikoNikoDBManager();
		// nikonikoDbManager.insert(new NikoNiko(new User(), new Project(), 1));
		//
		// NikoNiko newNiko = new NikoNiko();
		// newNiko = nikonikoDbManager.getById(newNiko);
		// System.out.println(newNiko.getId() + " " +
		// newNiko.getSatisfaction());
		//
		// // creer un objet de type T -> DumpFields.createContentsEmpty()
		// // .<T> inspectedBAseAttribute
		// // utilisation de Map
		// NikoNiko newNiko1 = new NikoNiko();
		// Project proj1 = DumpFields.createContentsEmpty(Project.class);
		//
		// //Test on inspectBaseAttribut //
		// //--------------------------- //
		// ArrayList<String> attributs = new ArrayList<String>();
		// attributs = DumpFields.inspectBaseAttribut(NikoNiko.class);
		//
		// System.out.println("NbAttr " + attributs.size());
		// for (String attribut : attributs) {
		// System.out.println("attribut " + attribut);
		// }
		//
		// // Test on getClassesNames //
		// // --------------------- //
		// ArrayList<String> classNames = new ArrayList<>();
		// try {
		// classNames =
		// DumpFields.getClassesNames("com.tactfactory.nikoniko.models");
		// } catch (ClassNotFoundException | IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println("NbClass " + classNames.size());
		// for (String className : classNames) {
		// System.out.println("Class " + className);
		// }

//		NikoNiko item = new NikoNiko();
//		item.setSatisfaction(1);
//		User user = new User();
//		user.setId(25685);
//		Project project = new Project();
//		project.setId(2531552);
//		item.setUser(user);
//		item.setProject(project);
//		// Get all attributes names and associated values from given item
//		Map<String, Object> fields = DumpFields.fielder(item);
//
//		// Create string ArrayList to get all attribute names from item class
//		ArrayList<String> attributes = new ArrayList<String>();
//		ArrayList<Object> objects = new ArrayList<Object>();
//
//		// Fill attributes's ArrayList
//		for (Map.Entry<String, Object> iterable_element : fields.entrySet()) {
//			attributes.add(iterable_element.getKey());
//			objects.add(iterable_element.getValue());
//		}
//
//		int a = 0;
//		a++;
//
//		NikoNikoDBManager nikonikoDBManager = new NikoNikoDBManager();
//		NikoNiko niko = new NikoNiko(new User("test", "test"), new Project("test", new Date()), 2);
//		/*
//		 * nikonikoDBManager.insert(niko);
//		 * 
//		 * NikoNiko niko1 = new NikoNiko(); niko1.setId(niko.getId());
//		 * 
//		 * nikonikoDBManager.getById(niko1); System.out.println(niko1);
//		 */
//		// for (Field field : DumpFields.getFields(NikoNiko.class)) {
//		//// try {
//		//// System.out.println(field.getName() + " : " +field.get(niko));
//		//// } catch (IllegalArgumentException e) {
//		//// // TODO Auto-generated catch block
//		//// e.printStackTrace();
//		//// } catch (IllegalAccessException e) {
//		//// // TODO Auto-generated catch block
//		//// e.printStackTrace();
//		//// }
//		// System.out.println(" SQL Type : " +
//		// field.getAnnotation(MySQLAnnotation.class).mysqlType());
//		// System.out.println(" Nullable? : " +
//		// field.getAnnotation(MySQLAnnotation.class).nullable());
//		// System.out.println(" SQL Name : " +
//		// field.getAnnotation(MySQLAnnotation.class).fieldName());
//		//
//		// System.out.println(" comment vs sql name : " +
//		// field.getName().equals(field.getAnnotation(MySQLAnnotation.class).fieldName()));
//		// System.out.println("##########");
//		// }
//		//
//		
//		String query = "";
//		for (String fieldItem : item.fields) {
//			// for each element of item.fields (simplification avec un i++ à
//			// faire plus tard)
//			for (Field field : DumpFields.getFields(item.getClass())) {
//				// For each attributes of item with a MySQLAnnotation
//				if (fieldItem.equals(field.getAnnotation(MySQLAnnotation.class).fieldName())) {
//					// Name of the current found attribute and current element
//					// of item.field are equal
//					switch (field.getAnnotation(MySQLAnnotation.class).mysqlType()) {
//					case DATETIME:
//						if (DumpFields.runGetter(field, item) != null) {
//							// A date attribute is already set in item
//							query += ",'" + DateConverter.getMySqlDatetime((Date) DumpFields.runGetter(field, item))
//									+ "'";
//						} else if (DumpFields.runGetter(field, item) == null
//								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
//							// No date attribute is set but this attribute is
//							// not nullable
//							query += ",'" + DateConverter.getMySqlDatetime(new Date()) + "'";
//						} else {
//							query += ",null";
//						}
//						break;
//
//					case INT:
//						if (DumpFields.runGetter(field, item) != null) {
//							// A INT attribute is already set in item
//							query += ",'" + DumpFields.runGetter(field, item) + "'";
//						} else if (DumpFields.runGetter(field, item) == null
//								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
//							// Default value of a not nullable INT attribute :
//							// -1 (not logic INT value for this application)
//							// Concat operation is maintained in case of the use
//							// of a "defaultValue" method
//							query += ",'" + "-1" + "'";
//						} else {
//							query += ",null";
//						}
//						break;
//
//					case TINYINT:// TINYINT is the type used for a boolean in
//									// our DTB
//						if (DumpFields.runGetter(field, item) != null) {
//							// A TINYINT (aka boolean) attribute is already set
//							// in item
//							query += ",'" + DumpFields.runGetter(field, item) + "'";
//						} else if (DumpFields.runGetter(field, item) == null
//								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
//							// Default value of a not nullable boolean attribute
//							// : 0 (false)
//							// Concat operation is maintained in case of the use
//							// of a "defaultValue" method
//							query += ",'" + 0 + "'";
//						} else {
//							query += ",null";
//						}
//						break;
//
//					case TEXT:
//						if (DumpFields.runGetter(field, item) != null) {
//							// A TEXT attribute is already set in item
//							query += ",'" + DumpFields.runGetter(field, item) + "'";
//						} else if (DumpFields.runGetter(field, item) == null
//								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
//							// Default value of a not nullable TEXT attribute :
//							// empty string
//							// Concat operation is maintained in case of the use
//							// of a "defaultValue" method
//							query += ",'" + "" + "'";
//						} else {
//							query += ",null";
//						}
//						break;
//
//					case DATABASE_ITEM:
//						Object dbItem = DumpFields.runGetter(field, item);
//						if (dbItem != null && ((DatabaseItem) dbItem).getId() != 0) {
//							// A object attribute is already set in item
//							query += ",'" + ((DatabaseItem) dbItem).getId() + "'";
//						} else {
//							// Default value of a not nullable TEXT attribute :
//							// empty string
//							// Concat operation is maintained in case of the use
//							// of a "defaultValue" method
//							query += ",null";
//						}
//						break;
//
//					default:
//						// if the selected attribute is an ArrayList of object,
//						// do nothing (case of association table)
//						break;
//					}
//				}
//			}
//		}
//		
//		System.out.println(query);
//		
//		
//		
//		for (Field field : DumpFields.getFields(item.getClass())) {
//			if (field.getAnnotation(MySQLAnnotation.class).mysqlType()== MySQLTypes.DATABASE_ITEM 
//					&& DumpFields.runGetter(field, item).getClass().getSimpleName().equals("Project")) {
//				System.out.println("Table asso : " + field.getAnnotation(MySQLAnnotation.class).associationTable());
//				System.out.println("Nom : " + field.getAnnotation(MySQLAnnotation.class).fieldName());
//				System.out.println("nullable? : " +field.getAnnotation(MySQLAnnotation.class).nullable());
//				System.out.println("classe? : " + 
//				(DumpFields.runGetter(field, item).getClass().getSimpleName().equals("Project")));
//				System.out.println("classe? : " + field.getName());
//			}
//			
//		}
//		
//		
		
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<Project> project = new ArrayList<Project>();
		ArrayList<NikoNiko> nikoNiko = new ArrayList<NikoNiko>();
		ArrayList<Team> team = new ArrayList<Team>();
				
				
				
	
		

	}

}
