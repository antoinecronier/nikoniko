package com.tactfactory.nikoniko.manager.database.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.models.NikoNiko;
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
