package com.tactfactory.nikoniko.manager.database.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.utils.DateConverter;


public class NikoNikoDBManager extends BaseDBManager<NikoNiko>{

	@Override
	public String getValues(NikoNiko item) {


		String query = "";
		
		// Condition pour savoir si il existe déjà un id ou non
		// si il n'y en a pas on renvoit null puis on le récupérera grâce à 
		// l'autoincrémente de la base de données (important pour que le requète 
		// ne soit pas incorrecte)

		if (item.getId() != 0) {
			query += item.getId() + ",";
		} else {
			query += "null,";
		}
		
		// Fonction qui permet de changer le format de la date pour que cette 
		// dernière puisse être "accessible par mysql

		query += "'" + DateConverter.getMySqlDatetime(item.getLog_date())
				+ "',";
		
		// Condition pour savoir si il existe une date de modification
		// si non on renvoit null (important pour la réquète ne soit pas fausse

		if (item.getChange_date() != null) {
			query += "'"
					+ DateConverter.getMySqlDatetime(item.getChange_date())
					+ "',";
		} else {
			query += "null,";
		}


		query += item.getSatisfaction() + ",";


		if (item.getComment() != null) {
			query += "'" + item.getComment() + "',";
		} else {
			query += "null,";
		}


		query += item.getIsAnonymous() + ",";


		if (item.getUser() != null && item.getUser().getId() != 0) {
			query += item.getUser().getId() + ",";
		} else {
			query += "null,";
		}
		
		// De même que précédemment, en sortie on récupére soit l'id existant du projet
		// associé au niko niko ou sinon on renvoit nul

		if (item.getProject() != null && item.getProject().getId() != 0) {
			query += item.getProject().getId();
		} else {
			query += "null";
		}
		
		// On retourne la query remplit des différents paramètres

		return query;
	
	}

	@Override
	public NikoNiko setObjectFromResult(ResultSet resulset) {

		NikoNiko nikoNiko = new NikoNiko();
		
		// On ajoute au nikoniko les différents champs par rapport au retour de la
		// requète SQL. result.getLong("id") se positionne sur le champ "id" 
		// result.getLong("id") = result.getLong(1). "1" position de l'id dans 
		// ligne retourner par la requète.
		
		try {

			nikoNiko.setId(resulset.getLong("id"));
			nikoNiko.setLog_date(resulset.getDate("log_Date"));
			nikoNiko.setChange_date(resulset.getDate("change_Date"));
			nikoNiko.setSatisfaction(resulset.getInt("satisfaction"));
			nikoNiko.setComment(resulset.getString("nikoniko_comment"));
			nikoNiko.setIsAnonymous(resulset.getBoolean("isanonymous"));

			//UserDBManager userDBManager = new UserDBManager();
			//nikoNiko.setUser(userDBManager.getUserById(resulset.getLong("id_User")));

			//ProjectDBManager projectDBManager = new ProjectDBManager();
			//nikoNiko.setProject(projectDBManager.getProjectById(resulset.getLong("id_Project")));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// On retourne l'objet nikoNiko avec toutes les informations de la base
		// (renseignées par la requète SQL)
		
		return nikoNiko;
	}
	
	// Fonction qui permet d'obtenir toutes les informations d'un nikoniko
	// en fonction de l'id renseigné

	@Override
	public void purgeTable(String table) {
		// TODO Auto-generated method stub
		
	}
	
	// Fonction qui permet d'obtenir tous les nikoniko de la table 
	// niko niko dans la BDD


//	@Override
//	public NikoNiko getById(long id) {
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//		"SELECT * FROM " + NikoNiko.TABLE + " WHERE " + NikoNiko.TABLE
//				+ ".id = " + id);
//		
//		NikoNiko nikoNiko = new NikoNiko();
//		try {
//			if (query.next()) {
//				nikoNiko = setObjectFromResultSet(query);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return nikoNiko;
//		return null;
//	}

	@Override
	public NikoNiko getByIdFull(long id) {
		// TODO Auto-generated method stub
		return null;

	}
	
	// Fonction qui permet l'ajout des attributs d'un nikoniko dans la BDD
	// en utilisant la fonction getNikoNikoValues().


	@Override
	public ArrayList<NikoNiko> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getAssociateObject(NikoNiko item) {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public void insert(NikoNiko item) {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public void update(NikoNiko item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(NikoNiko item) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public <O> void mapRelation(NikoNiko item, O relation) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWithChildren(NikoNiko item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <O> void updateChildren(NikoNiko item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteWithChildren(NikoNiko item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <O> void deleteChildren(NikoNiko item) {
		// TODO Auto-generated method stub
		
	}


//	public String getNikoNikoValues(NikoNiko nikoniko) {
//		String query = "";
//
//		if (nikoniko.getId() != 0) {
//			query += nikoniko.getId() + ",";
//		} else {
//			query += "null,";
//		}
//
//		query += "'" + DateConverter.getMySqlDatetime(nikoniko.getLog_date())
//				+ "',";
//
//		if (nikoniko.getChange_date() != null) {
//			query += "'"
//					+ DateConverter.getMySqlDatetime(nikoniko.getChange_date())
//					+ "',";
//		} else {
//			query += "null,";
//		}
//
//		query += nikoniko.getSatisfaction() + ",";
//
//		if (nikoniko.getComment() != null) {
//			query += "'" + nikoniko.getComment() + "',";
//		} else {
//			query += "null,";
//		}
//
//		query += nikoniko.getIsAnonymous() + ",";
//
//		if (nikoniko.getUser() != null && nikoniko.getUser().getId() != 0) {
//			query += nikoniko.getUser().getId() + ",";
//		} else {
//			query += "null,";
//		}
//
//		if (nikoniko.getProject() != null && nikoniko.getProject().getId() != 0) {
//			query += nikoniko.getProject().getId();
//		} else {
//			query += "null";
//		}
//
//		return query;
//	}
//
//	public NikoNiko setObjectFromResultSet(ResultSet query) {
//		NikoNiko nikoNiko = new NikoNiko();
//		try {
//			nikoNiko.setId(query.getLong("id"));
//			nikoNiko.setLog_date(query.getDate("log_Date"));
//			nikoNiko.setChange_date(query.getDate("change_Date"));
//			nikoNiko.setSatisfaction(query.getInt("satisfaction"));
//			nikoNiko.setComment(query.getString("nikoniko_comment"));
//			nikoNiko.setIsAnonymous(query.getBoolean("isanonymous"));
//
//			UserDBManager userDBManager = new UserDBManager();
//			nikoNiko.setUser(userDBManager.getUserById(query.getLong("id_User")));
//
//			ProjectDBManager projectDBManager = new ProjectDBManager();
//			nikoNiko.setProject(projectDBManager.getProjectById(query.getLong("id_Project")));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return nikoNiko;
//	}
//
//	public NikoNiko getNikoNikoById(long id) {
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + NikoNiko.TABLE + " WHERE " + NikoNiko.TABLE
//						+ ".id = " + id);
//		NikoNiko nikoNiko = new NikoNiko();
//		try {
//			if (query.next()) {
//				nikoNiko = setObjectFromResultSet(query);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return nikoNiko;
//	}
//
//	public ArrayList<NikoNiko> getAllNikoNiko() {
//		ResultSet query = MySQLAccess.getInstance().resultQuery(
//				"SELECT * FROM " + NikoNiko.TABLE + "");
//		ArrayList<NikoNiko> nikoNiko = new ArrayList<NikoNiko>();
//		try {
//			while (query.next()) {
//				nikoNiko.add(setObjectFromResultSet(query));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return nikoNiko;
//	}
//
//	public void insert(NikoNiko nikoniko) {
//		String query = "";
//
//		query += "INSERT INTO " + NikoNiko.TABLE + " VALUES (";
//		query += this.getNikoNikoValues(nikoniko);
//		query += ")";
//
//		MySQLAccess.getInstance().updateQuery(query);
//
//		if (nikoniko.getId() == 0) {
//			ResultSet result = MySQLAccess.getInstance().resultQuery(
//					"SELECT MAX(id) AS id FROM " + NikoNiko.TABLE);
//
//			try {
//				if (result.next()) {
//					nikoniko.setId(result.getLong(1));
//				}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}

}
