package generation;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.manager.database.manager.ProjectDBManager;
import com.tactfactory.nikoniko.manager.database.manager.TeamDBManager;
import com.tactfactory.nikoniko.manager.database.manager.UserDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.Team;
import com.tactfactory.nikoniko.models.User;

public class GenerateModels {

	ArrayList<User> listUsers = new ArrayList<User>();
	ArrayList<Project> listProjects = new ArrayList<Project>();
	ArrayList<Team> listTeams = new ArrayList<Team>();
	ArrayList<NikoNiko> listNikoNikos = new ArrayList<NikoNiko>();

	public void insertUsers() {

		UserDBManager userManager = new UserDBManager();
		int nbUsers = 10;

		for (int i = 0; i < nbUsers; i++) {
			String firstname = Ressources.m_firstname[i];
			String lastname = Ressources.lastname[i];

			User user = new User(firstname, lastname);

			userManager.insert(user);
			listUsers.add(user);
		}
	}

	public void insertTeams() {

		TeamDBManager teamManager = new TeamDBManager();
		int nbTeams = 10;

		for (int i = 0; i < nbTeams; i++) {
			String name = "CGITEAM " + i;
			String serial = "300" + i;

			Team team = new Team(name, serial);

			teamManager.insert(team);
			listTeams.add(team);
		}
	}

	public void insertNikoNikos() {

		NikoNikoDBManager nikoManager = new NikoNikoDBManager();
		int satisfaction;
		int nbNikoNikos = 10;

		for (int i = 0; i < nbNikoNikos; i++) {
			if (i % 3 == 0) {
				satisfaction = 3;
			} else if (i % 3 == 1) {
				satisfaction = 2;
			} else {
				satisfaction = 1;
			}

			NikoNiko nikoNiko = new NikoNiko(listUsers.get(i), listProjects.get(i), satisfaction);

			
		}
	}

	public void insertProjects(){
		
		ProjectDBManager projectManager = new ProjectDBManager();
		int nbProjects = 10;

		
		for (int i = 0; i < nbProjects; i++) {
			String name = "CGIPROJECT " +i ;
			String dateString = String.format("1992-08-%02d", (i % 27) +1 );
			
			@SuppressWarnings("deprecation")
			Date date = new Date(dateString);
			
			Project project = new Project(name,date);
			projectManager.insert(project);
			
			listProjects.add(project);
		}
	}

	public void insertRelation() {

		UserDBManager userManager = new UserDBManager();

		// Relation between User and Team
		userManager.mapRelation(this.listUsers.get(0), this.listTeams.get(1));
		userManager.mapRelation(this.listUsers.get(0), this.listTeams.get(5));
		userManager.mapRelation(this.listUsers.get(1), this.listTeams.get(2));
		userManager.mapRelation(this.listUsers.get(1), this.listTeams.get(9));
		userManager.mapRelation(this.listUsers.get(1), this.listTeams.get(6));
		userManager.mapRelation(this.listUsers.get(1), this.listTeams.get(5));
		userManager.mapRelation(this.listUsers.get(2), this.listTeams.get(1));
		userManager.mapRelation(this.listUsers.get(4), this.listTeams.get(6));
		userManager.mapRelation(this.listUsers.get(4), this.listTeams.get(1));
		userManager.mapRelation(this.listUsers.get(4), this.listTeams.get(4));
		userManager.mapRelation(this.listUsers.get(5), this.listTeams.get(5));
		userManager.mapRelation(this.listUsers.get(6), this.listTeams.get(8));
		userManager.mapRelation(this.listUsers.get(7), this.listTeams.get(2));
		userManager.mapRelation(this.listUsers.get(8), this.listTeams.get(9));
		userManager.mapRelation(this.listUsers.get(9), this.listTeams.get(0));

		TeamDBManager teamManager = new TeamDBManager();

		// Relation between Team and Projects
		teamManager.mapRelation(this.listTeams.get(0), this.listProjects.get(0));
		teamManager.mapRelation(this.listTeams.get(0), this.listProjects.get(1));
		teamManager.mapRelation(this.listTeams.get(1), this.listProjects.get(7));
		teamManager.mapRelation(this.listTeams.get(2), this.listProjects.get(0));
		teamManager.mapRelation(this.listTeams.get(2), this.listProjects.get(1));
		teamManager.mapRelation(this.listTeams.get(2), this.listProjects.get(7));
		teamManager.mapRelation(this.listTeams.get(3), this.listProjects.get(9));
		teamManager.mapRelation(this.listTeams.get(3), this.listProjects.get(6));
		teamManager.mapRelation(this.listTeams.get(4), this.listProjects.get(5));
		teamManager.mapRelation(this.listTeams.get(5), this.listProjects.get(9));
		teamManager.mapRelation(this.listTeams.get(5), this.listProjects.get(2));
		teamManager.mapRelation(this.listTeams.get(6), this.listProjects.get(0));
		teamManager.mapRelation(this.listTeams.get(7), this.listProjects.get(1));
		teamManager.mapRelation(this.listTeams.get(8), this.listProjects.get(4));
		teamManager.mapRelation(this.listTeams.get(9), this.listProjects.get(8));
		teamManager.mapRelation(this.listTeams.get(9), this.listProjects.get(8));

		// Relation between user and NikoNiko
		listUsers.get(0).getNikoNikos().add(listNikoNikos.get(1));
		listUsers.get(0).getNikoNikos().add(listNikoNikos.get(2));
		listUsers.get(1).getNikoNikos().add(listNikoNikos.get(3));
		listUsers.get(2).getNikoNikos().add(listNikoNikos.get(4));
		listUsers.get(2).getNikoNikos().add(listNikoNikos.get(5));
		listUsers.get(3).getNikoNikos().add(listNikoNikos.get(6));
		listUsers.get(4).getNikoNikos().add(listNikoNikos.get(7));
		listUsers.get(4).getNikoNikos().add(listNikoNikos.get(8));
		listUsers.get(5).getNikoNikos().add(listNikoNikos.get(9));
		listUsers.get(6).getNikoNikos().add(listNikoNikos.get(0));
		listUsers.get(6).getNikoNikos().add(listNikoNikos.get(1));
		listUsers.get(6).getNikoNikos().add(listNikoNikos.get(2));
		listUsers.get(7).getNikoNikos().add(listNikoNikos.get(3));
		listUsers.get(8).getNikoNikos().add(listNikoNikos.get(4));
		listUsers.get(9).getNikoNikos().add(listNikoNikos.get(5));

	}

}
