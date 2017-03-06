package com.tactfactory.nikoniko;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.manager.database.manager.ProjectDBManager;
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
		
//		ArrayList<User> users = new ArrayList<User>();
//		ArrayList<Project> projects = new ArrayList<Project>();
//		ArrayList<NikoNiko> nikoNikos = new ArrayList<NikoNiko>();
//		ArrayList<Team> teams = new ArrayList<Team>();
//			
//		
//		
//		for (int i = 0; i < 10; i++) {
//			UserDBManager userManager = new UserDBManager();
//			users.add(new User());
//			users.get(i).setFirstname("firstname"+i);
//			users.get(i).setLastname("lastname"+i);
//			users.get(i).setLogin("login"+i);
//			users.get(i).setPassword("password"+i);
//			users.get(i).setRegistration_cgi("registration_cgi"+i);
//			users.get(i).setSex('F');
//			userManager.insert(users.get(i));
//			
//			ProjectDBManager projectManager = new ProjectDBManager();
//			projects.add(new Project());
//			projects.get(i).setStart_date(new Date());
//			projects.get(i).setEnd_date(new Date());
//			projects.get(i).setName("name"+i);
//			projectManager.insert(projects.get(i));
//			
//			NikoNikoDBManager nikoManager = new NikoNikoDBManager();
//			nikoNikos.add(new NikoNiko());
//			nikoNikos.get(i).setSatisfaction(2);
//			nikoNikos.get(i).setComment("comment"+i);
//			//nikoNikos.get(i).setIsAnonymous(false);
//			nikoNikos.get(i).setLog_date(new Date());
//			nikoNikos.get(i).setChange_date(new Date());
//			nikoManager.insert(nikoNikos.get(i));
//
//			TeamDBManager teamManager = new TeamDBManager();
//			teams.add(new Team());
//			teams.get(i).setName("name"+i);
//			teams.get(i).setSerial("serial"+i);
//			teamManager.insert(teams.get(i));
//			
//		}
				
		
//		NikoNiko item = new NikoNiko();
//		item.setSatisfaction(1);
//		User user = new User();
//		user.setId(25685);
//		Project project = new Project();
//		project.setId(2531552);
//		item.setUser(user);
//		item.setProject(project);
//		
		NikoNikoDBManager nikoManager = new NikoNikoDBManager();
				
		User item = new User();
		//item.setId(5);
		item.setFirstname("Felo336666");
		item.setLastname("Le chite");
		item.setRegistration_cgi("yolo 201130");
		item.setLogin("miaou");
		item.setPassword("jtGEgzG");
		item.setSex('U');
		
		
		NikoNiko nikoniko = new NikoNiko ();
		NikoNiko nikoniko2 = new NikoNiko ();
		
		nikoniko.setId(3);
		nikoniko.setSatisfaction(1);
		nikoniko.setComment("MErga pouet958253");
		nikoniko.setIsAnonymous(false);
		nikoniko.setUser(item);
		
		nikoniko2.setId(215);
		nikoniko2.setSatisfaction(1);
		nikoniko2.setComment("Super pouet pouet562521jgcv 85");
		nikoniko2.setIsAnonymous(false);
	
		Team team = new Team();
		team.setId(531161);
		team.setName("nommmmm");
		
		item.getNikoNikos().add(nikoniko);
		item.getNikoNikos().add(nikoniko2);
		item.getTeams().add(team);
		
		
//		Team child = team;
		NikoNiko child = nikoniko2;
		
		
		
		NikoNikoDBManager nikomanager = new NikoNikoDBManager();
		nikomanager.updateChildren(nikoniko, item);
		
		System.out.println("niko finish");
		
		UserDBManager usermanager = new UserDBManager();
		usermanager.updateChildren(item, nikoniko);
		System.out.println("user finish");

		
//		for (Field field : DumpFields.getFields(item.getClass())) {
//			
//			
////			System.out.println("1er test");
//			
//			if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION){
//
//				//Script simplification variable
//				ArrayList<DatabaseItem> childrenList = (ArrayList<DatabaseItem>)DumpFields.runGetter(field, item);
//
//				if (childrenList.get(0).getClass().getSimpleName().equals(child.getClass().getSimpleName())) {
////					System.out.println("2e test");
//					
//					//recupere le path de la classe DBManager associee à item
//					String classPath = ""; // nom à changer car pas représentatif
//					String classSimpleName = ""+ childrenList.get(0).getClass().getSimpleName();
//					
////					System.out.println("classSimplename  = " + classSimpleName);
//					
//					classPath += "com.tactfactory.nikoniko.manager.database.manager.";
//					classPath += classSimpleName ;
//					classPath += "DBManager";
//					
////					System.out.println("classname  = " + className);
//					
//					
//					//
//					Object dbmanager = new Object();
//					
//					//on instancie le manager
//					try {
//						//
//						Class ObjetClas = Class.forName(classPath);
//						dbmanager = DumpFields.createContentsEmpty(ObjetClas);
//						
////						System.out.println("ça passe");
//						
//						} catch (ClassNotFoundException e) {
//							
////							System.out.println("ça casse");
//							
//							e.printStackTrace();
//							//on break pour eviter de realiser tout le code dans le try ( rien n'est fait si catch)
//							break;
//					}
//				
////					System.out.println("test 1 ");
//				//((BaseDBManager)dbmanager).getValues(item);
//					
////				System.out.println("ici : " +((ArrayList<DatabaseItem>)DumpFields.runGetter(field, item)).size());
//				
//					for (int i = 0; i < childrenList.size(); i++) {
//						
////						System.out.println("test 2 ");
//						
//						//If child don't have an id (0 = null here)
//						if (childrenList.get(i).getId() == 0) { 
//							
//							System.out.println("J'insère car pas ID ");
//							
//							//Insert child if not referenced in DTB
//							((BaseDBManager)dbmanager).insert((DatabaseItem)childrenList.get(i));
//							
//							//In this case the choosen child don't have Id => it don't exist in DTB
//							//Can be done in 1-2 line with "insert(T item)" method
//						
//						} else {//Si l'id du child n'est pas référencé dans la DTB
//							//Case to work on if necessary : getById send error (no id in DTB)
//							//								 => No id registered (we want to give him a specific Id)
//							//								 => Do an insert (beware, it can lead to unexpected problems)
//														
//							if (((BaseDBManager)dbmanager).getById((DatabaseItem)childrenList.get(i)) == null) {
//								
//								//Insert child if not referenced in DTB
//								((BaseDBManager)dbmanager).insert((DatabaseItem)childrenList.get(i));
//								
//							} else {
//								//Case to work on if necessary : getById send error (no id in DTB)
//								//								 => No id registered (we want to give him a specific Id)
//								//								 => Do an insert (beware, it can lead to unexpected problems)
//								
//								//##################################//
//								//From here we state that the id of the Ith child is known and exists in DTB
//															
//								//Create empty query for update
//								String query = "";
//								
//								//Ith children table_name in DTB (alway the same in our case)
//								query += "UPDATE " + childrenList.get(i).table +" SET ";
//								
//								//Use getValueForUpdate to obtain "item.field[i]=splitedGetValue[i]"
//								 
//								query += ((BaseDBManager)dbmanager).getValuesForUpdate((DatabaseItem)childrenList.get(i));
//								
//								//Insert update condition to select the right child in DTB (ex : id_User = child.getId)
//								//Use sql annotation of the field (fieldname) to select the right column in DTB 
//								//association table 
//								query += " WHERE ";
//								
//								query += "id = " + ((DatabaseItem)childrenList.get(i)).getId();
//								
//								//launch update query in dTB
//								MySQLAccess.getInstance().updateQuery(query);
//
//								System.out.println("query3 = " + query);
//							}
//							
//							
//							
//							
//							try {
//								//Cas ou l'id existe mais il n'y a rien dans la DTB
//								((BaseDBManager)dbmanager).getById((DatabaseItem)childrenList.get(i));
//								
//								System.out.println("test : "+ ((BaseDBManager)dbmanager).getById((DatabaseItem)childrenList.get(i)));
//							} catch (Exception e) {
//								
//								System.out.println("mon id n'est pas le bon " + ((DatabaseItem)childrenList.get(i)).getId() );
//								
//								//Insert child if not referenced in DTB
//								((BaseDBManager)dbmanager).insert((DatabaseItem)childrenList.get(i));
//							}							
//						}
//			
//					
//					
////					System.out.println("là nom classe : " +childrenList.get(i).table);
//
//					
//					
//					
//					
//					
////					System.out.println("là id : " +childrenList.get(i).getId());
////					String query = "";
//					
//					//query += userdeb.getValues((User)((ArrayList<DatabaseItem>)DumpFields.runGetter(field, item)).get(i));
////					System.out.println("preums : "+((BaseDBManager)dbmanager).getValues(((DatabaseItem)childrenList.get(i))) );
//					
//					//System.out.println(DumpFields.toString(field, item));
//				
//				
//					}
//				}
//
//			} else if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.DATABASE_ITEM
//					&& field.getClass().getSimpleName().equals(child.getClass().getSimpleName())) {
//				
//			}	
//		}	
		
//		for (Field field : DumpFields.getFields(item.getClass())) {
//			if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.DATABASE_ITEM) {
////				System.out.println(((DatabaseItem) DumpFields.runGetter(field, item)).getId());
////				System.out.println(((DatabaseItem) DumpFields.runGetter(field, item)).getClass());
//				
////				System.out.println(item.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
//				
//				String toto = "";
//				toto += item.getClass().getName();
//				
//				try {
////					Class klazz = Class.forName("com.tactfactory.nikoniko.models.Project");
//					Class klazz = Class.forName(toto);
//					
//					System.out.println("youpi!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//					System.out.println(klazz.getName());
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//		}
		
	}

}
