package com.tactfactory.nikoniko.manager.database.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.Team;
import com.tactfactory.nikoniko.models.User;

public class UserDBManager {
	
	
//	public static final String USER_TEAM = "user_team";
//
//	public String getUserValues(User user) {
//		String query = "";
//
//		if (user.getId() != 0) {
//			query += user.getId() + ",";
//		} else {
//			query += "null,";
//		}
//
//		query += "'" + user.getLogin() + "',";
//		query += "'" + user.getPassword() + "',";
//		query += "'" + user.getLastname() + "',";
//		query += "'" + user.getFirstname() + "',";
//		query += "'" + user.getRegistration_cgi() + "'";
//
//		return query;
//	}
//
//	public User setObjectFromResultSet(ResultSet query) {
//		User user = new User();
//		try {
//			user.setId(query.getLong("id"));
//			user.setLogin(query.getString("login"));
//			user.setPassword(query.getString("password"));
//			user.setLastname(query.getString("lastname"));
//			user.setFirstname(query.getString("firstname"));
//			user.setRegistration_cgi(query.getString("registration_cgi"));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return user;
//	}
//
//	public User getUserByIdFull(long id) {
//		User user = this.getUserById(id);
//
//		this.getAssociatedNikoNiko(user);
//		this.getAssociatedTeam(user);
//
//		return user;
//	}
//
//	public User getUserById(long id) {
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + User.TABLE + " WHERE " + User.TABLE
//						+ ".id = " + id);
//		User user = new User();
//		try {
//			if (query.next()) {
//				user = setObjectFromResultSet(query);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return user;
//	}
//
//	public ArrayList<User> getAllUser() {
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + User.TABLE + "");
//		ArrayList<User> users = new ArrayList<User>();
//		try {
//			while (query.next()) {
//				users.add(setObjectFromResultSet(query));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return users;
//	}
//
//	public void getAssociatedNikoNiko(User user) {
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + NikoNiko.TABLE + " WHERE " + NikoNiko.TABLE
//						+ ".id_User = " + user.getId());
//		NikoNikoDBManager nikoNikoDBManager = new NikoNikoDBManager();
//		try {
//			while (query.next()) {
//				user.getNikoNikos().add(
//						nikoNikoDBManager.setObjectFromResultSet(query));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void getAssociatedTeam(User user) {
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + USER_TEAM + " INNER JOIN " + Team.TABLE
//						+ " ON " + USER_TEAM + ".id_Team = " + Team.TABLE
//						+ ".id "
//						+ "WHERE " + USER_TEAM + ".id_User = "
//						+ user.getId());
//		TeamDBManager teamDBManager = new TeamDBManager();
//		try {
//			while (query.next()) {
//				user.getTeams().add(
//						teamDBManager.setObjectFromResultSet(query));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void insert(User user) {
//		String query = "";
//
//		query += "INSERT INTO " + User.TABLE + " VALUES (";
//		query += this.getUserValues(user);
//		query += ")";
//
//		MySQLAccess.getInstance().updateQuery(query);
//
//		if (user.getId() == 0) {
//			ResultSet result = MySQLAccess.getInstance().resultQuery(
//					"SELECT MAX(id) AS id FROM " + User.TABLE);
//
//			try {
//				if (result.next()) {
//					user.setId(result.getLong(1));
//				}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
//
//	public void insertRelationTeam(User user, Team team) {
//		String query = "";
//
//		query += "INSERT INTO " + "user_team" + " VALUES (";
//		query += user.getId() + ",";
//		query += team.getId();
//		query += ")";
//
//		MySQLAccess.getInstance().updateQuery(query);
//
//		if (!user.getTeams().contains(team)) {
//			user.getTeams().add(team);
//		}
//	}
}
