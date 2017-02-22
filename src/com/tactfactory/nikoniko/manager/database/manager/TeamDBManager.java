package com.tactfactory.nikoniko.manager.database.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.Team;

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
}
