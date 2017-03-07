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


	public ArrayList<NikoNiko> getAll() {
		// TODO Auto-generated method stub
		NikoNikoDBManager niko = new NikoNikoDBManager();
		return niko.getAll(NikoNiko.class);
	}

	@Override
	public void getAssociateObject(NikoNiko item) {
		// TODO Auto-generated method stub
	}

	
	@Override
	public void update(NikoNiko item) {
		// TODO Auto-generated method stub
	}

	/*@Override
	public <O> void mapRelation(NikoNiko item, O relation) {

	}*/

//	@Override
//	public void updateWithChildren(NikoNiko item) {
//		// TODO Auto-generated method stub
//	}


	@Override
	public <O> void mapRelation(NikoNiko item, O relation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <O> void updateChildren(NikoNiko item, O child) {
		// TODO Auto-generated method stub
		
	}

	
}
