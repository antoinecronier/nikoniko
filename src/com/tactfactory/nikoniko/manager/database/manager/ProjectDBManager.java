package com.tactfactory.nikoniko.manager.database.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
//import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.Team;
import com.tactfactory.nikoniko.models.User;
import com.tactfactory.nikoniko.utils.DateConverter;

public class ProjectDBManager {
	
	public String getProjectValues(Project project) {
		String query = "";

		if (project.getId() != 0) {
			query += project.getId() + ",";
		} else {
			query += "null,";
		}

		query += "'" + project.getName() + "',";
		query += "'" + DateConverter.getMySqlDatetime(project.getStart_date())
				+ "',";

		if (project.getEnd_date() != null) {
			query += "'"
					+ DateConverter.getMySqlDatetime(project.getEnd_date())
					+ "'";
		} else {
			query += "null";
		}

		return query;
	}

	public void insert(Project project) {
		String query = "";

		query += "INSERT INTO " + Project.TABLE + " VALUES (";
		query += this.getProjectValues(project);
		query += ")";

		MySQLAccess.getInstance().updateQuery(query);

		if (project.getId() == 0) {
			ResultSet result = MySQLAccess.getInstance().resultQuery("SELECT MAX(id) AS id FROM " + Project.TABLE);

			try {
				if (result.next()) {
					project.setId(result.getLong(1));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void insertWithChild(Project project){
		this.insert(project);
		this.insertTeams(project);
	}

	public void insertTeams(Project project){
		TeamDBManager manager = new TeamDBManager();
		for (Team team : project.getTeams()) {
			manager.insert(team);
			this.insertRelationTeam(team, project);
		}
	}

	public void insertRelationTeam(Team team, Project project){
		String query = "";

		query += "INSERT INTO " + "team_project" + " VALUES (";
		query += team.getId() +",";
		query += project.getId();
		query += ")";

		MySQLAccess.getInstance().updateQuery(query);
	}
	
	public Project getProjectById (long id){
		Project project = new Project();
		String query = "";
		
		query += "SELECT * FROM project WHERE id =" +id;
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		try {
			if (result.next()) {
				project.setId(result.getLong("id"));
				project.setName(result.getString("name"));
				project.setStart_date(result.getDate("start_Date"));
				project.setEnd_date(result.getDate("end_Date"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); // ne pas oublier cette ligne
		}
		
		return project;
	}
	
	public ArrayList<Project> getAllProject(){
		ArrayList<Project> projectS = new ArrayList<Project>();
		
		String query = "";
		query += "SELECT * FROM project";
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		
		try {
			while (result.next()) {
				Project projectTemp = new Project();
				projectTemp.setId(result.getLong("id"));
				projectTemp.setName(result.getString("name"));
				projectTemp.setStart_date(result.getDate("start_Date"));
				projectTemp.setEnd_date(result.getDate("end_Date"));
				
				projectS.add(projectTemp);
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return projectS;
	}
	

	public void getAssociatedTeam(Project project) {
		
		TeamDBManager teamManager = new TeamDBManager();
		
		//recupere l'ensemble des elements de la table d'association user_team associés à user
		String query = "";
		query += "SELECT * FROM team_project WHERE id = " + project.getId();
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		
		try {
			while (result.next()) {
				//on ajoute à l'arraylist team la team trouvee pas son id
				project.getTeams().add(teamManager.getTeamById(result.getLong("id_Team")));				
			}				
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	
	public void getAssociatedNikoNiko(User user) {
		
		NikoNikoDBManager nikoManager = new NikoNikoDBManager();
		
		//recupere l'ensemble des elements de la table d'association user_team associés à user
		String query = "";
		query += "SELECT * FROM nikoniko WHERE id_User = " + user.getId();
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		
		try {
			while (result.next()) {
				//on ajoute à l'arraylist team la team trouvee pas son id
				user.getNikoNikos().add(nikoManager.getNikoNikoById(result.getLong("id")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
	
}
