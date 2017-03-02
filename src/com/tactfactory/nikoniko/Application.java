package com.tactfactory.nikoniko;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.manager.database.manager.ProjectDBManager;
import com.tactfactory.nikoniko.manager.database.manager.TeamDBManager;
import com.tactfactory.nikoniko.manager.database.manager.UserDBManager;
import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.*;

public class Application {

	public static void main(String[] args) {

		/*
		 * User u1 = new User(); User u2 = new User();
		 *
		 * Team t1 = new Team(); u1.getTeams().add(t1); u2.getTeams().add(t1);
		 * t1.getUsers().add(u1); t1.getUsers().add(u2);
		 *
		 * Project p1 = new Project(); p1.getTeams().add(t1);
		 * t1.getProjects().add(p1);
		 */
		/*
		 * DatabasePurjer.purjeDatabase();
		 * 
		 * NikoNiko niko = new NikoNiko(); niko.setSatisfaction(1);
		 * 
		 * NikoNikoDBManager nikonikoManager = new NikoNikoDBManager();
		 * nikonikoManager.insert(niko);
		 * 
		 * User user = new User("login", "password", "lastname", "firstname",
		 * "test"); UserDBManager userManager = new UserDBManager();
		 * userManager.insert(user);
		 * 
		 * Team team = new Team("team1", "serial1"); TeamDBManager teamManager =
		 * new TeamDBManager(); teamManager.insert(team);
		 * 
		 * 
		 * Project project = new Project("project1", new Date());
		 * ProjectDBManager projectManager = new ProjectDBManager();
		 * projectManager.insert(project);
		 * 
		 * // Team to project for (int i = 0; i < 10; i++) {
		 * team.getProjects().add(new Project("project" + i, new Date())); }
		 * 
		 * teamManager.insert(team);
		 * 
		 * // Project to team for (int i = 0; i < 10; i++) {
		 * project.getTeams().add(new Team("teamX" + i, "teamXSerial")); }
		 * 
		 * projectManager.insert(project);
		 * 
		 * // Team user linking for (int i = 0; i < 10; i++) { User userTmp =
		 * new User("login" + i, "password" + i, "lastname" + i, "firstname" +
		 * i, "registration" + i); userManager.insert(userTmp);
		 * teamManager.<User>mapRelation(team, userTmp); }
		 * 
		 * // User team linking for (int i = 0; i < 10; i++) { Team tmpTeam =
		 * new Team("teamV" + i, "serialV" + i); teamManager.insert(tmpTeam);
		 * userManager.<Team>mapRelation(user, tmpTeam); }
		 * 
		 * User newUser = userManager.getById(user); ArrayList<User> users =
		 * userManager.getAll();
		 * 
		 * for (int i = 0; i < 10; i++) { nikonikoManager.insert(new
		 * NikoNiko(user, project, i % 3 + 1)); }
		 * 
		 * User userSelect = new User(); userSelect.setId(user.getId());
		 * userManager.getAssociateObject(userSelect);
		 * 
		 * Project projectSelect = new Project();
		 * projectSelect.setId(project.getId());
		 * projectManager.getAssociateObject(projectSelect);
		 * 
		 * Team teamSelect = new Team(); teamSelect.setId(team.getId());
		 * teamManager.getAssociateObject(teamSelect);
		 * 
		 * int a = 0; a++;
		 * 
		 * NikoNikoDBManager nikoNikoDBManager = new NikoNikoDBManager();
		 * 
		 * nikoNikoDBManager.insert(new NikoNiko(new User(), new Project(), 1));
		 * 
		 * NikoNiko nikoniko = new NikoNiko();
		 * 
		 * System.out.println(nikoniko.getLog_date());
		 * 
		 * nikoNikoDBManager.getById(new NikoNiko());
		 * 
		 * ArrayList<String> result = DumpFields.inspectGetter(Project.class);
		 * System.out.println(result);
		 * 
		 * ArrayList<String> result1 =
		 * DumpFields.inspectBaseAttribut(User.class);
		 * System.out.println(result1);
		 * 
		 * System.out.println(nikoNikoDBManager.getById(niko).toString());
		 * 
		 * NikoNikoDBManager nikonikoDbManager = new NikoNikoDBManager();
		 * nikonikoDbManager.insert(new NikoNiko(new User(), new Project(), 1));
		 * 
		 * NikoNiko newNiko = new NikoNiko(); newNiko =
		 * nikonikoDbManager.getById(newNiko);
		 * System.out.println(newNiko.getId() + " " +
		 * newNiko.getSatisfaction());
		 * 
		 * // creer un objet de type T -> DumpFields.createContentsEmpty() //
		 * .<T> inspectedBAseAttribute // utilisation de Map
		 * 
		 * NikoNiko newNiko1 = new NikoNiko(); Project proj1 =
		 * DumpFields.createContentsEmpty(Project.class);
		 */

		/* Test on inspectBaseAttribut */
		/* --------------------------- */
		// ArrayList<String> attributs = new ArrayList<String>();
		// attributs = DumpFields.inspectBaseAttribut(NikoNiko.class);
		//
		// System.out.println("NbAttr " + attributs.size());
		// for (String attribut : attributs) {
		// System.out.println("attribut " + attribut);
		// }
		//
		// /* Test on getClassesNames */
		// /* --------------------- */
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

		// //Tests for mapRelation Denis
		// NikoNiko newNiko1 = new NikoNiko();
		// newNiko1.setId(1);
		// BaseDBManager<NikoNiko> nikodbmanager = new NikoNikoDBManager();
		// nikodbmanager.delete(newNiko1);
		//
		// Project prj = new Project();
		// prj.setId(1);
		// BaseDBManager<Project> prjdbmanager = new ProjectDBManager();
		// prjdbmanager.delete(prj);
		//
		User user = new User();
		user.setId(4);
		BaseDBManager<User> usrdbmanager = new UserDBManager();
		usrdbmanager.delete(user);
		//
		// Team tm = new Team();
		// tm.setId(1);
		// BaseDBManager<Team> teamdbmanager = new TeamDBManager();
		// teamdbmanager.delete(tm);
		//
		MySQLAccess.getInstance().updateQuery("ALTER TABLE USER AUTO_INCREMENT=0");
		// MySQLAccess.getInstance().updateQuery("ALTER TABLE PROJECT
		// AUTO_INCREMENT=0");
		// MySQLAccess.getInstance().updateQuery("ALTER TABLE TEAM
		// AUTO_INCREMENT=0");
		// MySQLAccess.getInstance().updateQuery("ALTER TABLE NIKONIKO
		// AUTO_INCREMENT=0");

		// Tests for mapRelation Denis
		NikoNiko newNiko1 = new NikoNiko();
		newNiko1.setId(1);

		User usr = new User();
		usr.setId(3);

		Team tm = new Team();
		tm.setId(1);

		Project prj = new Project();
		prj.setId(1);

		BaseDBManager<NikoNiko> nikodbmanager = new NikoNikoDBManager();
		// nikodbmanager.mapRelation(newNiko1, prj);

		BaseDBManager<Project> projectdbmanager = new ProjectDBManager();
		// projectdbmanager.mapRelation(prj, tm);

		BaseDBManager<User> userdbmanager = new UserDBManager();
		// userdbmanager.mapRelation(usr, tm);

		BaseDBManager<Team> teamdbmanager = new TeamDBManager();
		// teamdbmanager.mapRelation(tm, usr);
		// teamdbmanager.mapRelation(tm, prj);
	}
}
