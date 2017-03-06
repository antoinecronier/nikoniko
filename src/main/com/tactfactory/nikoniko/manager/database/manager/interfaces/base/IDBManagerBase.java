package com.tactfactory.nikoniko.manager.database.manager.interfaces.base;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;

public interface IDBManagerBase<T> {

	// Utilities
	/**
	 * Retrieve values of item to be set as a string to build queries.
	 * 
	 * @param item
	 * @return
	 */
	// Erwan
	public String getValues(T item);

	/**
	 * Fill java object with DB ResultSet.
	 * 
	 * @param resultSet
	 * @return
	 */
	// 1
	public T setObjectFromResultSet(ResultSet resultSet, T item);
	
	/**
	 * Retrieve values of item to be set as a string to build queries 
	 * to update tables.
	 * @param item
	 * @return
	 */
	// Erwan&Felix
	public String getValuesForUpdate(T item);

	// Database management
	/**
	 * Delete all record from table.
	 * 
	 * @param table
	 */
	// Denis
	public void purgeTable(String table);

	/**
	 * Return object as java item to be used.
	 * 
	 * @param item
	 * @return item
	 */
	public T getById(T item);

	/**
	 * Return object as java item to be used. And all its relations.
	 * 
	 * @param id
	 * @return
	 */
	// Damien
	public T getByIdFull(long id);

	/**
	 * Return all objects from selected table. Without relations.
	 * 
	 * @return
	 */
	public ArrayList<T> getAll(Class<T> clazz);

	/**
	 * Get all related objects even if they are arrays or single object.
	 * 
	 * @param item
	 */
	// Romain
	public void getAssociateObject(T item);

	/**
	 * Insert current java object to DB. Without children.
	 * 
	 * @param item
	 */
	// 0
	public void insert(T item);

	/**
	 * Update current java object to DB. Without children.
	 * 
	 * @param item
	 */
	// Regis
	public void update(T item);

	/**
	 * Delete current java object to DB. Without children.
	 * 
	 * @param item
	 */
	// Denis
	public void delete(T item);

	/**
	 * Use T and O object to get their ids and map the relation.
	 * 
	 * @param item
	 * @param relation
	 */
	// Denis
	public <O> void mapRelation(T item, O relation);

	/**
	 * Update all items extract from current item. Calling "public <O> void
	 * updateChildren(T item)" for all kind of children.
	 * 
	 * @param item
	 */
	// Regis
	public void updateWithChildren(T item);

	/**
	 * Update only one relation of current item named O. Call "public <O> void
	 * mapRelation(T item, O relation)" for each foreign key.
	 * 
	 * @param item
	 */
	//Denis
	public <O> void updateChildren(T item, O child);

	/**
	 * Delete current java object to DB. With children.
	 * 
	 * @param item
	 */
	public void deleteWithChildren(T item);

	/**
	 * Delete only children of type O from current item.
	 * 
	 * @param item
	 */
	public <O> void deleteChildren(T item);
	
}
