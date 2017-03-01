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

//	public static final String USER_TEAM = "user_team";
//	public static final String TEAM_PROJECT = "team_project";
//
//	public String getTeamValues(Team team) {
//		String query = "";
//
//		if (team.getId() != 0) {
//			query += team.getId() + ",";
//		} else {
//			query += "null,";
//		}
//
//		query += "'" + team.getName() + "',";
//		query += "'" + team.getSerial() + "'";
//
//		return query;
//	}
//
//	public Team setObjectFromResultSet(ResultSet query) {
//		Team team = new Team();
//		try {
//			team.setId(query.getLong("id"));
//			team.setName(query.getString("name"));
//			team.setSerial(query.getString("serial"));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return team;
//	}
//
//	public Team getTeamByIdFull(long id) {
//		Team team = this.getTeamById(id);
//
//		this.getAssociatedProject(team);
//		this.getAssociatedProject(team);
//
//		return team;
//	}
//
//	public Team getTeamById(long id) {
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + Team.TABLE + " WHERE " + Team.TABLE
//						+ ".id = " + id);
//		Team team = new Team();
//		try {
//			if (query.next()) {
//				team = setObjectFromResultSet(query);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return team;
//	}
//
//	public ArrayList<Team> getAllTeam() {
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + Team.TABLE + "");
//		ArrayList<Team> teams = new ArrayList<Team>();
//		try {
//			while (query.next()) {
//				teams.add(setObjectFromResultSet(query));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return teams;
//	}
//
//	public void getAssociatedUser(Team team) {
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + USER_TEAM + " INNER JOIN " + User.TABLE
//						+ " ON " + USER_TEAM + ".id_User = " + User.TABLE
//						+ ".id "
//						+ "WHERE " + USER_TEAM + ".id_Team = "
//						+ team.getId());
//		UserDBManager userDBManager = new UserDBManager();
//		try {
//			while (query.next()) {
//				team.getUsers().add(
//						userDBManager.setObjectFromResultSet(query));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void getAssociatedProject(Team team) {
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + TEAM_PROJECT + " INNER JOIN " + Project.TABLE
//						+ " ON " + TEAM_PROJECT + ".id_Project = " + Project.TABLE
//						+ ".id "
//						+ "WHERE " + TEAM_PROJECT + ".id_Team = "
//						+ team.getId());
//		ProjectDBManager projectDBManager = new ProjectDBManager();
//		try {
//			while (query.next()) {
//				team.getProjects().add(
//						projectDBManager.setObjectFromResultSet(query));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void insert(Team team) {
//		String query = "";
//
//		query += "INSERT INTO " + Team.TABLE + " VALUES (";
//		query += this.getTeamValues(team);
//		query += ")";
//
//		MySQLAccess.getInstance().updateQuery(query);
//
//		if (team.getId() == 0) {
//			ResultSet result = MySQLAccess.getInstance().resultQuery(
//					"SELECT MAX(id) AS id FROM " + Team.TABLE);
//
//			try {
//				if (result.next()) {
//					team.setId(result.getLong(1));
//				}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
//
//	public void insertWithChild(Team team) {
//		this.insert(team);
//		this.insertProjects(team);
//	}
//
//	public void insertProjects(Team team) {
//		ProjectDBManager manager = new ProjectDBManager();
//		for (Project project : team.getProjects()) {
//			manager.insert(project);
//			this.insertRelationProject(team, project);
//		}
//	}
//
//	public void insertRelationProject(Team team, Project project) {
//		String query = "";
//
//		query += "INSERT INTO " + "team_project" + " VALUES (";
//		query += team.getId() + ",";
//		query += project.getId();
//		query += ")";
//
//		MySQLAccess.getInstance().updateQuery(query);
//	}
//
//	public void insertRelationUser(User user, Team team) {
//		String query = "";
//
//		query += "INSERT INTO " + "user_team" + " VALUES (";
//		query += user.getId() + ",";
//		query += team.getId();
//		query += ")";
//
//		MySQLAccess.getInstance().updateQuery(query);
//
//		if (!team.getUsers().contains(user)) {
//			team.getUsers().add(user);
//		}
//	}

}
