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

	
	
	
//	private static final String TEAM_PROJECT = "team_project";
//
//	public String getProjectValues(Project project) {
//		String query = "";
//
//		if (project.getId() != 0) {
//			query += project.getId() + ",";
//		} else {
//			query += "null,";
//		}
//
//		query += "'" + project.getName() + "',";
//		query += "'" + DateConverter.getMySqlDatetime(project.getStart_date())
//				+ "',";
//
//		if (project.getEnd_date() != null) {
//			query += "'"
//					+ DateConverter.getMySqlDatetime(project.getEnd_date())
//					+ "'";
//		} else {
//			query += "null";
//		}
//
//		return query;
//	}
//
//	public Project setObjectFromResultSet(ResultSet query) {
//		Project project = new Project();
//		try {
//			project.setId(query.getLong("id"));
//			project.setName(query.getString("name"));
//			project.setStart_date(query.getDate("start_Date"));
//			project.setEnd_date(query.getDate("end_Date"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return project;
//	}
//
//	public Project getProjectByIdFull(long id) {
//		Project project = this.getProjectById(id);
//
//		this.getAssociatedNikoNiko(project);
//		this.getAssociatedTeam(project);
//
//		return project;
//	}
//
//	public Project getProjectById(long id) {
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + Project.TABLE + " WHERE " + Project.TABLE
//						+ ".id = " + id);
//		Project project = new Project();
//		try {
//			if (query.next()) {
//				project = setObjectFromResultSet(query);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return project;
//	}
//
//	public ArrayList<Project> getAllProject() {
//		ResultSet resultSet = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + Project.TABLE + "");
//		ArrayList<Project> projects = new ArrayList<Project>();
//		try {
//			while (resultSet.next()) {
//				projects.add(setObjectFromResultSet(resultSet));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return projects;
//	}
//
//	public void getAssociatedNikoNiko(Project project) {
//		ResultSet resultSet = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + NikoNiko.TABLE + " WHERE " + NikoNiko.TABLE
//						+ ".id_Project = " + project.getId());
//		NikoNikoDBManager nikoNikoDBManager = new NikoNikoDBManager();
//		try {
//			while (resultSet.next()) {
//				project.getNikoNikos().add(
//						nikoNikoDBManager.setObjectFromResultSet(resultSet));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void getAssociatedTeam(Project project) {
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + TEAM_PROJECT + " INNER JOIN " + Team.TABLE
//						+ " ON " + TEAM_PROJECT + ".id_Team = " + Team.TABLE
//						+ ".id "
//						+ "WHERE " + TEAM_PROJECT + ".id_Project = "
//						+ project.getId());
//		TeamDBManager teamDBManager = new TeamDBManager();
//		try {
//			while (query.next()) {
//				project.getTeams().add(
//						teamDBManager.setObjectFromResultSet(query));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void insert(Project project) {
//		String query = "";
//
//		query += "INSERT INTO " + Project.TABLE + " VALUES (";
//		query += this.getProjectValues(project);
//		query += ")";
//
//		MySQLAccess.getInstance().updateQuery(query);
//
//		if (project.getId() == 0) {
//			ResultSet result = MySQLAccess.getInstance().resultQuery(
//					"SELECT MAX(id) AS id FROM " + Project.TABLE);
//
//			try {
//				if (result.next()) {
//					project.setId(result.getLong(1));
//				}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
//
//	public void insertWithChild(Project project) {
//		this.insert(project);
//		this.insertTeams(project);
//	}
//
//	public void insertTeams(Project project) {
//		TeamDBManager manager = new TeamDBManager();
//		for (Team team : project.getTeams()) {
//			manager.insert(team);
//			this.insertRelationTeam(team, project);
//		}
//	}
//
//	public void insertRelationTeam(Team team, Project project) {
//		String query = "";
//
//		query += "INSERT INTO " + "team_project" + " VALUES (";
//		query += team.getId() + ",";
//		query += project.getId();
//		query += ")";
//
//		MySQLAccess.getInstance().updateQuery(query);
//	}
}
