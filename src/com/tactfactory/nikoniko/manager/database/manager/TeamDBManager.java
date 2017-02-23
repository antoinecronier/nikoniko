package com.tactfactory.nikoniko.manager.database.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
//import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.Team;
import com.tactfactory.nikoniko.models.User;

public class TeamDBManager {
	public String getTeamValues(Team team) {
		String query = "";

		if (team.getId() != 0) {
			query += team.getId() + ",";
		} else {
			query += "null,";
		}

		query += "'" + team.getName() + "',";
		query += "'" + team.getSerial() + "'";

		return query;
	}

	public void insert(Team team) {
		String query = "";

		query += "INSERT INTO " + Team.TABLE + " VALUES (";
		query += this.getTeamValues(team);
		query += ")";

		MySQLAccess.getInstance().updateQuery(query);

		if (team.getId() == 0) {
			ResultSet result = MySQLAccess.getInstance().resultQuery("SELECT MAX(id) AS id FROM " + Team.TABLE);

			try {
				if (result.next()) {
					team.setId(result.getLong(1));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void insertWithChild(Team team){
		this.insert(team);
		this.insertProjects(team);
	}

	public void insertProjects(Team team){
		ProjectDBManager manager = new ProjectDBManager();
		for (Project project : team.getProjects()) {
			manager.insert(project);
			this.insertRelationProject(team, project);
		}
	}

	public void insertRelationProject(Team team, Project project){
		String query = "";

		query += "INSERT INTO " + "team_project" + " VALUES (";
		query += team.getId() +",";
		query += project.getId();
		query += ")";

		MySQLAccess.getInstance().updateQuery(query);
	}
	
	public void insertRelationUser(User user, Team team) {
		/* gestion table d'association de user vers team lorsqu'on travaille sur
		 * la table Team via le "DBmanager"
		*/
		String query = "";

		query += "INSERT INTO " + "user_team" + " VALUES (";
		query += user.getId() +",";
		query += team.getId();
		query += ")";

		MySQLAccess.getInstance().updateQuery(query);
		
		//on actualise l'objet team pour qu'il soit coherent avec la bdd
		if (!team.getUsers().contains(user)) {
			team.getUsers().add(user);
		}
		
	}

	public Team getTeamById (long id){
		Team team = new Team();
		String query = "";
		
		query += "SELECT * FROM team WHERE id =" +id;
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		try {
			if (result.next()) {
				team.setId(result.getLong("id"));
				team.setName(result.getString("name"));
				team.setSerial(result.getString("serial"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return team;
	}
		
	public ArrayList<Team> getAllTeam(){
		ArrayList<Team> teamS = new ArrayList<Team>();
		
		String query = "";
		query += "SELECT * FROM team";
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		
		try {
			while (result.next()) {
				Team teamTemp = new Team();
				teamTemp.setId(result.getLong("id"));
				teamTemp.setName(result.getString("name"));
				teamTemp.setSerial(result.getString("serial"));
				
				teamS.add(teamTemp);
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		}
		return teamS;
	}

	

	public void getAssociatedUser(Team team) {
		
		UserDBManager userManager = new UserDBManager();
		
		//recupere l'ensemble des elements de la table d'association user_team associés à user
		String query = "";
		query += "SELECT * FROM user_team WHERE id = " + team.getId();
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		
		try {
			while (result.next()) {
				//on ajoute à l'arraylist team la team trouvee pas son id
				team.getUsers().add(userManager.getUserById(result.getLong("id_User")));				
			}				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public void getAssociatedProject(Team team) {
		
		ProjectDBManager projectManager = new ProjectDBManager();
		
		//recupere l'ensemble des elements de la table d'association user_team associés à user
		String query = "";
		query += "SELECT * FROM team_project WHERE id = " + team.getId();
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		
		try {
			while (result.next()) {
				//on ajoute à l'arraylist team la team trouvee pas son id
				team.getProjects().add(projectManager.getProjectById(result.getLong("id_Project")));				
			}				
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
}
