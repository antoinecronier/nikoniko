package com.tactfactory.nikoniko.manager.database.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.utils.DateConverter;

// Création d'un classe permettant d'insérer des Niko-niko dans la base de données
// et aussi de récupérer les données

public class NikoNikoDBManager {
	
	// Fonction permettant d'obtenir les attributs d'une instance Niko Niko
	// et de la sauvegarder dans une "query" pour par la suite, insérer les données in BDD
	
	public String getNikoNikoValues(NikoNiko nikoniko) {
		String query = "";
		
		// Condition pour savoir si il existe déjà un id ou non
		// si il n'y en a pas on renvoit null puis on le récupérera grâce à 
		// l'autoincrémente de la base de données (important pour que le requète 
		// ne soit pas incorrecte)

		if (nikoniko.getId() != 0) {
			query += nikoniko.getId() + ",";
		} else {
			query += "null,";
		}
		
		// Fonction qui permet de changer le format de la date pour que cette 
		// dernière puisse être "accessible par mysql

		query += "'" + DateConverter.getMySqlDatetime(nikoniko.getLog_date())
				+ "',";
		
		// Condition pour savoir si il existe une date de modification
		// si non on renvoit null (important pour la réquète ne soit pas fausse

		if (nikoniko.getChange_date() != null) {
			query += "'"
					+ DateConverter.getMySqlDatetime(nikoniko.getChange_date())
					+ "',";
		} else {
			query += "null,";
		}

		query += nikoniko.getSatisfaction() + ",";
		
		// Condition similaire aux autres et importante pour ne pas que la requète soit
		// incorrecte

		if (nikoniko.getComment() != null) {
			query += "'" + nikoniko.getComment() + "',";
		} else {
			query += "null,";
		}

		query += nikoniko.getIsAnonymous() + ",";
		
		// Condition pour les foreign key, on vérifie que le niko niko contient des 
		// users ainsi qu'un id associé. Si ce n'est pas le cas on renvoit nul
		// la contion .getUser() != null est placé en premier car si pas de user
		// associé alors pas d'id non plus et on sort de la boucle directement
		// sans passer par la deuxième condition


		if (nikoniko.getUser() != null && nikoniko.getUser().getId() != 0) {
			query += nikoniko.getUser().getId() + ",";
		} else {
			query += "null,";
		}
		
		// De même que précédemment, en sortie on récupére soit l'id existant du projet
		// associé au niko niko ou sinon on renvoit nul

		if (nikoniko.getProject() != null && nikoniko.getProject().getId() != 0) {
			query += nikoniko.getProject().getId();
		} else {
			query += "null";
		}
		
		// On retourne la query remplit des différents paramètres

		return query;
	}
	
	// Fonction permettant de récupérer les données de la table niko niko dans la BDD
	// à partir d'une requète SQL et de gérer les relations entre user (id_user) et 
	// le projet (id_project) (foreign key)

	public NikoNiko setObjectFromResultSet(ResultSet result) {
		
		// On créé un nikoniko tampon qui va stocker les données récupérées
		
		NikoNiko nikoNiko = new NikoNiko();
		
		// On ajoute au nikoniko les différents champs par rapport au retour de la
		// requète SQL. result.getLong("id") se positionne sur le champ "id" 
		// result.getLong("id") = result.getLong(1). "1" position de l'id dans 
		// ligne retourner par la requète.
		
		try {
			nikoNiko.setId(result.getLong("id"));
			nikoNiko.setLog_date(result.getDate("log_Date"));
			nikoNiko.setChange_date(result.getDate("change_Date"));
			nikoNiko.setSatisfaction(result.getInt("satisfaction"));
			nikoNiko.setComment(result.getString("nikoniko_comment"));
			nikoNiko.setIsAnonymous(result.getBoolean("isanonymous"));
			
			// Création d'une instance userDBManager pour récupérer l'id d'un 
			// utilisateur (foreign key : id_user).

			UserDBManager userDBManager = new UserDBManager();
			
			// A partir de la requète SQL on va créer la relation entre la table nikoniko
			// et la table user (foreign key : id_user). 1) on récupère l'user par 
			// l'id_user (renseigné par la requète SQL) puis on l'ajoute dans l'instance
			// tampon nikoNiko pour créer la relation
			
			nikoNiko.setUser(userDBManager.getUserById(result.getLong("id_User")));
			
			// De même que précedemment sauf avec le projet (création de la relation)

			ProjectDBManager projectDBManager = new ProjectDBManager();
			nikoNiko.setProject(projectDBManager.getProjectById(result.getLong("id_Project")));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// On retourne l'objet nikoNiko avec toutes les informations de la base
		// (renseignées par la requète SQL)
		
		return nikoNiko;
	}
	
	// Fonction qui permet d'obtenir toutes les informations d'un nikoniko
	// en fonction de l'id renseigné

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
	
	// Fonction qui permet d'obtenir tous les nikoniko de la table 
	// niko niko dans la BDD

	public ArrayList<NikoNiko> getAllNikoNiko() {
		
		// On envoit une requète pour sélectionner toute la table NikoNiko
		
		ResultSet query = MySQLAccess.getInstance().resultQuery(
				"SELECT * FROM " + NikoNiko.TABLE + "");
		
		// Création d'une liste d'objet NikoNiko pour insérer toutes les données
		// de la table récupérée à l'intérieur
		
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
	
	// Fonction qui permet l'ajout des attributs d'un nikoniko dans la BDD
	// en utilisant la fonction getNikoNikoValues().

	public void insert(NikoNiko nikoniko) {
		String query = "";
		
		// On créé notre requète SQL
		// NikoNiko.TABLE fait référence à une variable global non modifiable (final)
		// correspondant au nom de la table associé

		query += "INSERT INTO " + NikoNiko.TABLE + " VALUES (";
		query += this.getNikoNikoValues(nikoniko);
		query += ")";
		
		// On insére la requète SQL dans la base de donnée

		MySQLAccess.getInstance().updateQuery(query);
		
		// Si l'id de nikoniko est égal à 0 (ou NULL ?) on va chercher son id dans la base
		// de données grâce à un resultQuery(select ... from ...)

		if (nikoniko.getId() == 0) {
			ResultSet result = MySQLAccess.getInstance().resultQuery(
					"SELECT MAX(id) AS id FROM " + NikoNiko.TABLE);
			
			// Création d'une exception. result est renvoyé sous forme de liste
			// et est composé d'un curseur qui est à 0 par défaut (curseur sur  
			// la demande SQL initiale). En faisant l'exception, si le result.next()
			// ne renvoit rien alors le retour de requète est faux et on indique une erreur.      
			// Si le result.next() est bon, alors on va lire la première ligne de la table
			// renvoyée par la requète et on va lire l'id avec "result.getLong(1)";

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
