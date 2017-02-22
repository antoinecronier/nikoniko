package com.tactfactory.nikoniko.manager.database.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.Team;
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
}
