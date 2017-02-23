package com.tactfactory.nikoniko.manager.database.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.utils.DateConverter;

public class NikoNikoDBManager {

	public String getNikoNikoValues(NikoNiko nikoniko) {
		String query = "";

		if (nikoniko.getId() != 0) {
			query += nikoniko.getId() + ",";
		} else {
			query += "null,";
		}

		query += "'" + DateConverter.getMySqlDatetime(nikoniko.getLog_date())
				+ "',";

		if (nikoniko.getChange_date() != null) {
			query += "'"
					+ DateConverter.getMySqlDatetime(nikoniko.getChange_date())
					+ "',";
		} else {
			query += "null,";
		}

		query += nikoniko.getSatisfaction() + ",";

		if (nikoniko.getComment() != null) {
			query += "'" + nikoniko.getComment() + "',";
		} else {
			query += "null,";
		}

		query += nikoniko.getIsAnonymous() + ",";

		if (nikoniko.getUser() != null && nikoniko.getUser().getId() != 0) {
			query += nikoniko.getUser().getId() + ",";
		} else {
			query += "null,";
		}

		if (nikoniko.getProject() != null && nikoniko.getProject().getId() != 0) {
			query += nikoniko.getProject().getId();
		} else {
			query += "null";
		}

		return query;
	}

	public NikoNiko setObjectFromResultSet(ResultSet query) {
		NikoNiko nikoNiko = new NikoNiko();
		try {
			nikoNiko.setId(query.getLong("id"));
			nikoNiko.setLog_date(query.getDate("log_Date"));
			nikoNiko.setChange_date(query.getDate("change_Date"));
			nikoNiko.setSatisfaction(query.getInt("satisfaction"));
			nikoNiko.setComment(query.getString("nikoniko_comment"));
			nikoNiko.setIsAnonymous(query.getBoolean("isanonymous"));

			UserDBManager userDBManager = new UserDBManager();
			nikoNiko.setUser(userDBManager.getUserById(query.getLong("id_User")));

			ProjectDBManager projectDBManager = new ProjectDBManager();
			nikoNiko.setProject(projectDBManager.getProjectById(query.getLong("id_Project")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nikoNiko;
	}

	public NikoNiko getNikoNikoById(long id) {
		ResultSet query = MySQLAccess.getInstance().resultQuery(
				"SELECT * FROM " + NikoNiko.TABLE + " WHERE " + NikoNiko.TABLE
						+ ".id = " + id);
		NikoNiko nikoNiko = new NikoNiko();
		try {
			if (query.next()) {
				nikoNiko = setObjectFromResultSet(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nikoNiko;
	}

	public ArrayList<NikoNiko> getAllNikoNiko() {
		ResultSet query = MySQLAccess.getInstance().resultQuery(
				"SELECT * FROM " + NikoNiko.TABLE + "");
		ArrayList<NikoNiko> nikoNiko = new ArrayList<NikoNiko>();
		try {
			while (query.next()) {
				nikoNiko.add(setObjectFromResultSet(query));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nikoNiko;
	}

	public void insert(NikoNiko nikoniko) {
		String query = "";

		query += "INSERT INTO " + NikoNiko.TABLE + " VALUES (";
		query += this.getNikoNikoValues(nikoniko);
		query += ")";

		MySQLAccess.getInstance().updateQuery(query);

		if (nikoniko.getId() == 0) {
			ResultSet result = MySQLAccess.getInstance().resultQuery(
					"SELECT MAX(id) AS id FROM " + NikoNiko.TABLE);

			try {
				if (result.next()) {
					nikoniko.setId(result.getLong(1));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
