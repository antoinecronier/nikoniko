package com.tactfactory.nikoniko.manager.database.manager.interfaces.base;


import java.sql.ResultSet;
import java.util.ArrayList;

public interface IDBManagerBase<T> {
	// Utilities
	/**
	 * Retrieve values of item to be set as a string to build queries.
	 * @param item
	 * @return
	 */
	public String getValues(T item);

	/**
	 * Fill java object with DB ResultSet.
	 * @param resultSet
	 * @return
	 */
	public T setObjectFromResult(ResultSet resultSet);


	// Database management
	/**
	 * Delete all record from table.
	 * @param table
	 */
	public void purgeTable(String table);

	/**
	 * Return object as java item to be used.
	 * @param id
	 * @return
	 */
	public T getById(long id);

	/**
	 * Return object as java item to be used. And all its relations.
	 * @param id
	 * @return
	 */
	public T getByIdFull(long id);

	/**
	 * Return all objects from selected table. Without relations.
	 * @return
	 */
	public ArrayList<T> getAll();

	/**
	 * Get all related objects even if they are arrays or single object.
	 * @param item
	 */
	public void getAssociateObject(T item);

	/**
	 * Insert current java object to DB. Without children.
	 * @param item
	 */
	public void insert(T item);

	/**
	 * Update current java object to DB. Without children.
	 * @param item
	 */
	public void update(T item);

	/**
	 * Delete current java object to DB. Without children.
	 * @param item
	 */
	public void delete(T item);

	/**
	 * Use T and O object to get their ids and map the relation.
	 * @param item
	 * @param relation
	 */
	public <O> void mapRelation(T item, O relation);

	/**
	 * Update all items extract from current item.
	 * Calling "public <O> void updateChildren(T item)" for all kind of children.
	 * @param item
	 */
	public void updateWithChildren(T item);

	/**
	 * Update only one relation of current item named O.
	 * Call "public <O> void mapRelation(T item, O relation)" for each foreign key.
	 * @param item
	 */
	public <O> void updateChildren(T item);

	/**
	 * Delete current java object to DB. With children.
	 * @param item
	 */
	public void deleteWithChildren(T item);

	/**
	 * Delete only children of type O from current item.
	 * @param item
	 */
	public <O> void deleteChildren(T item);



}
