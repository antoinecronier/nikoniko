package com.tactfactory.nikoniko.manager.database.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Team;
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
	

	public NikoNiko getNikoNikoById (long id){
		NikoNiko niko = new NikoNiko();
		ProjectDBManager projectManager = new ProjectDBManager();
		UserDBManager userManager = new UserDBManager();
		
		
		String query = "";
		
		query += "SELECT * FROM nikoniko WHERE id =" +id;
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		try {
			if (result.next()) {
				niko.setId(result.getLong("id"));
				niko.setLog_date(result.getDate("log_Date"));
				niko.setChange_date(result.getDate("change_Date"));
				niko.setSatisfaction(result.getInt("satisfaction"));
				niko.setComment(result.getString("nikoniko_comment"));
				niko.setIsAnonymous(result.getBoolean("isanonymous"));
				
				//on utilise getuserbyid car nikoniko.user est un objet de type user
				niko.setUser(userManager.getUserById(result.getLong("id_User")));
				
				//on utilise getprojectbyid car nikoniko.user est un objet de type project
				niko.setProject(projectManager.getProjectById(result.getLong("id_Project")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return niko;
	}
		
	public ArrayList<NikoNiko> getAllNikoNiko(){
		ArrayList<NikoNiko> nikoS = new ArrayList<NikoNiko>();
		ProjectDBManager projectManager = new ProjectDBManager();
		UserDBManager userManager = new UserDBManager();
		
		String query = "";
		query += "SELECT * FROM nikoniko";
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		
		try {
			while (result.next()) {
				NikoNiko nikoTemp = new NikoNiko();
				nikoTemp.setId(result.getLong("id"));
				nikoTemp.setLog_date(result.getDate("log_Date"));
				nikoTemp.setChange_date(result.getDate("change_Date"));
				nikoTemp.setSatisfaction(result.getInt("satisfaction"));
				nikoTemp.setComment(result.getString("nikoniko_comment"));
				nikoTemp.setIsAnonymous(result.getBoolean("isanonymous"));
				
				//on utilise getuserbyid car nikoniko.user est un objet de type user
				nikoTemp.setUser(userManager.getUserById(result.getLong("id_User")));
				
				//on utilise getprojectbyid car nikoniko.user est un objet de type project
				nikoTemp.setProject(projectManager.getProjectById(result.getLong("id_Project")));
				
				nikoS.add(nikoTemp);
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		}
		return nikoS;
	}
	
}
