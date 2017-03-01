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
//	// Fonction qui permet de créer la requète SQL pour récupérer les valeurs de user
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
//	// Fonction qui permet de récupérer les données d'un user depuis la BDD
//	// Fonction qui permet aux données d'être utilisé par un user du programme
//
//	public User setObjectFromResultSet(ResultSet query) {
//		
//		// On créé un user tampon qui va stocker les données récupérées
//		
//		User user = new User();
//		
//		// On ajoute à l'user les différents champs par rapport au retour de la
//		// requète SQL. result.getLong("id") se positionne sur le champ "id" 
//		// result.getLong("id") = result.getLong(1). "1" position de l'id dans 
//		// ligne retourner par la requète.
//		
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
//	// Fonction qui permet de récupérer un utilisateur dans la BDD en fonction 
//	// de l'id renseigné et ensuite de créer directement les relations entre 
//	// la team et le niko niko
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
//	// Fonction permettant de récupérer un utilisateur en fonction de l'id
//	// renseigné en paramètre
//
//	public User getUserById(long id) {
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + User.TABLE + " WHERE " + User.TABLE
//						+ ".id = " + id);
//		
//		// On créé un nouvel user qui va récupérer les informations 
//		// rendus par la requète SQL
//		
//		User user = new User();
//		try {
//			if (query.next()) {
//				user = setObjectFromResultSet(query);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		// On retourne l'user trouvé
//		
//		return user;
//	}
//	
//	// Fonction qui récupère dans une liste tous les utilisateurs de la table user
//	
//	public ArrayList<User> getAllUser() {
//		
//		// Requète SQL pour avoir tous les users
//		
//		ResultSet result = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + User.TABLE + "");
//		
//		// On va insérer dans cette liste le résultat de la requète SQL
//		
//		ArrayList<User> users = new ArrayList<User>();
//		
//		// On insére dans la liste tous les users avec leurs attributs récupérés
//		// depuis la table (setObjectFromResultSet(result))
//		
//		try {
//			while (result.next()) {
//				users.add(setObjectFromResultSet(result));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return users;
//	}
//	
//	// Fonction qui permet de récupérer les niko niko en fonction de l'user
//	// foreign key dans la table niko niko
//
//	public void getAssociatedNikoNiko(User user) {
//		
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + NikoNiko.TABLE + " WHERE " + NikoNiko.TABLE
//						+ ".id_User = " + user.getId());
//		
//		NikoNikoDBManager nikoNikoDBManager = new NikoNikoDBManager();
//		
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
//	// Fonction qui permet de récupérer la/les teams en fonction de l'user
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
//	// Fonction permettant d'insérer un user dans la table user de la BDD
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
//		// Si son id est égal à 0 alors on va récupérer l'id de l'utilisateur
//		// récemment inséré
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
//	// Création de l'association entre la table user et team (user -> team)
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
