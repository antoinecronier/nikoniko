package com.tactfactory.nikoniko.manager.database.manager;

import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.utils.DateConverter;

public class NikoNikoDBManager extends BaseDBManager<NikoNiko> {

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
//	@Override
//	public NikoNiko getById(NikoNiko item) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	// Fonction qui permet d'obtenir tous les nikoniko de la table
	// niko niko dans la BDD
//	@Override
//	public ArrayList<NikoNiko> getAll(Class<NikoNiko> clazz) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public ArrayList<NikoNiko> getAll() {
		NikoNikoDBManager niko = new NikoNikoDBManager();
		return niko.getAll(NikoNiko.class);
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
	public <O> void updateChildren(NikoNiko item, O sampleChild) {
		// TODO Auto-generated method stub
	}

}
