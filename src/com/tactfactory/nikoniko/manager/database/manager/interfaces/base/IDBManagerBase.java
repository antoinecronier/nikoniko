package com.tactfactory.nikoniko.manager.database.manager.interfaces.base;

public interface IDBManagerBase<T> {
	
	// Fonction utilitaire
	/**
	 * Récupérer les valeurs de l'item pour être setter dans une string 
	 * @param item
	 * @return
	 */
	
	public String getValues(T item);
	

	
	// Fonction manipulation de la BDD
	
}
