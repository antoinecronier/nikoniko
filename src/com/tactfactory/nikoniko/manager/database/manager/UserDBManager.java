package com.tactfactory.nikoniko.manager.database.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
//import com.tactfactory.nikoniko.models.NikoNiko;
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
	
	public void insertRelationTeam(User user, Team team) {
		/* gestion table d'association de user vers team lorsqu'on travaille sur
		 * la table User via le "DBmanager"
		*/
		String query = "";

		query += "INSERT INTO " + "user_team" + " VALUES (";
		query += user.getId() +",";
		query += team.getId();
		query += ")";

		MySQLAccess.getInstance().updateQuery(query);
		
		//on actualise l'objet user pour qu'il soit coherent avec la bdd
		if (!user.getTeams().contains(team)) {
			user.getTeams().add(team);
		}
		
	}
	
	
	public User getUserById (long id){
		User user = new User();
		String query = "";
		
		query += "SELECT * FROM user WHERE id =" +id;
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		try {
			if (result.next()) {
				user.setId(result.getLong("id"));
				user.setFirstname(result.getString("password"));
				user.setLogin(result.getString("login"));
				user.setLastname(result.getString("lastname"));
				user.setFirstname(result.getString("firstname"));
				user.setRegistration_cgi(result.getString("registration_cgi"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return user;
	}
	
	
	public ArrayList<User> getAllUser(){
		ArrayList<User> userS = new ArrayList<User>();
		
		String query = "";
		query += "SELECT * FROM user";
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		
		try {
			while (result.next()) {
				User userTemp = new User();
				userTemp.setId(result.getLong("id"));
				userTemp.setFirstname(result.getString("password"));
				userTemp.setLogin(result.getString("login"));
				userTemp.setLastname(result.getString("lastname"));
				userTemp.setFirstname(result.getString("firstname"));
				userTemp.setRegistration_cgi(result.getString("registration_cgi"));
				
				userS.add(userTemp);
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		}
		return userS;
	}
	
	
	public void getAssociatedTeam(User user) {
		
		TeamDBManager teamManager = new TeamDBManager();
		
		//recupere l'ensemble des elements de la table d'association user_team associés à user
		String query = "";
		query += "SELECT * FROM user_team WHERE id = " + user.getId();
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		
		try {
			while (result.next()) {
				//on ajoute à l'arraylist team la team trouvee pas son id
				user.getTeams().add(teamManager.getTeamById(result.getLong("id_Team")));
				
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
		}		
	}
	
	
	
}
