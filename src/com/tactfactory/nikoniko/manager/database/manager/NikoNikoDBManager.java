package com.tactfactory.nikoniko.manager.database.manager;

import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;

public class NikoNikoDBManager extends BaseDBManager<NikoNiko> {

	// Creation d'un classe permettant d'insérer des Niko-niko dans la base de
	// donnees
	// et aussi de recuperer les donnees
	// Fonction permettant d'obtenir les attributs d'une instance Niko Niko
	// et de la sauvegarder dans une "query" pour par la suite, inserer les
	// donnees in BDD

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
<<<<<<< HEAD
	 * @param id
=======
	 * @param item
>>>>>>> master
	 * @return
	 */
	@Override
	public NikoNiko getById(NikoNiko item) {
		// TODO Auto-generated method stub
		return null;
	}

	// Fonction qui permet d'obtenir tous les nikoniko de la table
	// niko niko dans la BDD
//	@Override
//	public ArrayList<NikoNiko> getAll(Class<NikoNiko> clazz) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public ArrayList<NikoNiko> getAll() {
		// TODO Auto-generated method stub
		NikoNikoDBManager niko = new NikoNikoDBManager();
		return niko.getAll(NikoNiko.class);
	}

	@Override
	public void getAssociateObject(NikoNiko item) {
		// TODO Auto-generated method stub
	}

	// Fonction qui permet l'ajout des attributs d'un nikoniko dans la BDD
	// en utilisant la fonction getNikoNikoValues().

	/*
	 * @Override public void delete(NikoNiko item) { // TODO Auto-generated
	 * method stub }
	 */

	@Override
	public void update(NikoNiko item) {
		// TODO Auto-generated method stub
	}

	/*@Override
	public <O> void mapRelation(NikoNiko item, O relation) {
		String query = "";

		if (relation.getClass().getSimpleName().equals("User")) {
			User usr = (User) relation;
			query = "UPDATE " + item.table + " SET id_user = " + usr.getId() + " WHERE id = " + item.getId();
			MySQLAccess.getInstance().updateQuery(query);
		} else if (relation.getClass().getSimpleName().equals("Project")) {
			Project prj = (Project) relation;
			query = "UPDATE " + item.table + " SET id_project = " + prj.getId() + " WHERE id = " + item.getId();
			MySQLAccess.getInstance().updateQuery(query);
		} else {
			System.err.println(
					"mapRelation for NikoNiko, inconsistent relation with " + relation.getClass().getSimpleName());
		}
	}*/

	@Override
	public void updateWithChildren(NikoNiko item) {
		// TODO Auto-generated method stub
	}

	@Override
	public <O> void updateChildren(NikoNiko item) {
		// TODO Auto-generated method stub
	}

	@Override
	public <O> void mapRelation(NikoNiko item, O relation) {
		// TODO Auto-generated method stub
		
	}
}
