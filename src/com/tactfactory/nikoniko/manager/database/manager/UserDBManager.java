package com.tactfactory.nikoniko.manager.database.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Team;
import com.tactfactory.nikoniko.models.User;

public class UserDBManager {
	public String getUserValues(User user) {
		String query = "";

		if (user.getId() != 0) {
			query += user.getId() + ",";
		} else {
			query += "null,";
		}

		query += "'" + user.getLogin() + "',";
		query += "'" + user.getPassword() + "',";
		query += "'" + user.getLastname() + "',";
		query += "'" + user.getFirstname() + "',";
		query += "'" + user.getRegistration_cgi() + "'";

		return query;
	}

	public void insert(User user) {
		String query = "";

		query += "INSERT INTO " + User.TABLE + " VALUES (";
		query += this.getUserValues(user);
		query += ")";

		MySQLAccess.getInstance().updateQuery(query);

		if (user.getId() == 0) {
			ResultSet result = MySQLAccess.getInstance().resultQuery("SELECT MAX(id) AS id FROM " + User.TABLE);

			try {
				if (result.next()) {
					user.setId(result.getLong(1));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
