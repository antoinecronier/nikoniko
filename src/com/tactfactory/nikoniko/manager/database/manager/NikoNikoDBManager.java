package com.tactfactory.nikoniko.manager.database.manager;

import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.utils.DateConverter;

public class NikoNikoDBManager extends BaseDBManager<NikoNiko> {

	// Création d'un classe permettant d'insérer des Niko-niko dans la base de
	// données
	// et aussi de récupérer les données
	// Fonction permettant d'obtenir les attributs d'une instance Niko Niko
	// et de la sauvegarder dans une "query" pour par la suite, insérer les
	// données in BDD

	@Override
	public String getValues(NikoNiko item) {
		String query = "";

		// Condition pour savoir si il existe déjà un id ou non
		// si il n'y en a pas on renvoit null puis on le récupérera grâce à
		// l'autoincrémente de la base de données (important pour que le
		// requète
		// ne soit pas incorrecte)

		if (item.getId() != 0) {
			query += item.getId() + ",";
		} else {
			query += "null,";
		}

		// Fonction qui permet de changer le format de la date pour que cette
		// dernière puisse être "accessible par mysql
		// Condition pour savoir si il existe une date de modification
		// si non on renvoit null (important pour la réquète ne soit pas
		// fausse

		query += "'" + DateConverter.getMySqlDatetime(item.getLog_date()) + "',";

		if (item.getChange_date() != null) {
			query += "'" + DateConverter.getMySqlDatetime(item.getChange_date()) + "',";
		} else {
			query += "null,";
		}

		query += item.getSatisfaction() + ",";

		// Condition similaire aux autres et importante pour ne pas que la
		// requète soit
		// incorrecte

		if (item.getComment() != null) {
			query += "'" + item.getComment() + "',";
		} else {
			query += "null,";
		}

		query += item.getIsAnonymous() + ",";

		// Condition pour les foreign key, on vérifie que le niko niko contient
		// des
		// users ainsi qu'un id associé. Si ce n'est pas le cas on renvoit nul
		// la contion .getUser() != null est placé en premier car si pas de
		// user
		// associé alors pas d'id non plus et on sort de la boucle directement
		// sans passer par la deuxième condition

		if (item.getUser() != null && item.getUser().getId() != 0) {
			query += item.getUser().getId() + ",";
		} else {
			query += "null,";
		}

		// De même que précédemment, en sortie on récupére soit l'id
		// existant du projet
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
	public NikoNiko getByIdFull(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void purgeTable(String table) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Fonction qui permet d'obtenir toutes les informations d'un nikoniko en
	 * fonction de l'id renseigné
	 * 
	 * @param item
	 * @return
	 */

	@Override
	public NikoNiko getById(NikoNiko item) {
		// TODO Auto-generated method stub
		return null;
	}

	// Fonction qui permet d'obtenir tous les nikoniko de la table
	// niko niko dans la BDD
	@Override
	public ArrayList<NikoNiko> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getAssociateObject(NikoNiko item) {
		// TODO Auto-generated method stub
	}

	// Fonction qui permet l'ajout des attributs d'un nikoniko dans la BDD
	// en utilisant la fonction getNikoNikoValues().

	@Override
	public void delete(NikoNiko item) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(NikoNiko item) {
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
	public void deleteWithChildren(NikoNiko item) {
		// TODO Auto-generated method stub
	}

	@Override
	public <O> void deleteChildren(NikoNiko item) {
		// TODO Auto-generated method stub
	}

	@Override
	public <O> void updateChildren(NikoNiko item) {
		// TODO Auto-generated method stub
	}
}
