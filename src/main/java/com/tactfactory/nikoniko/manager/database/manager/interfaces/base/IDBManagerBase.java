package com.tactfactory.nikoniko.manager.database.manager.interfaces.base;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface IDBManagerBase<T> {

	// Utilities
	/**
	 * Retrieve values of item to be set as a string to build queries.
	 *
	 * @param item
	 * @return
	 */
	// Erwan need merge OK (1)
	public String getValues(T item);

	/**
	 * Fill java object with DB ResultSet.
	 *
	 * @param resultSet
	 * @return
	 */
	// Lucie OK
	public T setObjectFromResultSet(ResultSet resultSet, T item);

	// Database management
	/**
	 * Delete all record from table.
	 *
	 * @param table
	 */
	// Denis OK merge (2)
	public void purgeTable(String table);

	/**
	 * Return object as java item to be used.
	 *
	 * @param item
	 * @return item
	 */
	// OK
	public T getById(T item);

	/**
	 * Return object as java item to be used. And all its relations.
	 *
	 * @param id
	 * @return
	 */
	// Damien NOK
	public T getByIdFull(long id);

	/**
	 * Return all objects from selected table. Without relations.
	 *
	 * @return
	 */
	// Felix OK
	public ArrayList<T> getAll(Class<T> clazz);

	/**
	 * Get all related objects even if they are arrays or single object.
	 *
	 * @param item
	 */
	// Romain soon
	public void getAssociateObject(T item);

	/**
	 * Insert current java object to DB. Without children.
	 *
	 * @param item
	 */
	// Add items for values => Denis
	public void insert(T item);

	/**
	 * Update current java object to DB. Without children.
	 *
	 * @param item
	 */
	// Regis OK merge (3)
	public void update(T item);

	/**
	 * Delete current java object to DB. Without children.
	 *
	 * @param item
	 */
	// Denis OK not tested merge (2) => to reformat
	public void delete(T item);

	/**
	 * Use T and O object to get their ids and map the relation.
	 *
	 * @param item
	 * @param relation
	 */
	// Denis soon 1 case to update
	public <O> void mapRelation(T item, O relation);

	/**
	 * Update all items extract from current item. Calling "public <O> void
	 * updateChildren(T item)" for all kind of children.
	 *
	 * @param item
	 */
	// Regis NOK not started
	public void updateWithChildren(T item);

	/**
	 * Update only one relation of current item named O. Call "public <O> void
	 * mapRelation(T item, O relation)" for each foreign key.
	 *
	 * @param item
	 */
	// Erwan Felix soon
	public <O> void updateChildren(T item);

	/**
	 * Delete current java object to DB. With children.
	 *
	 * @param item
	 */
	// Lucie not started
	public void deleteWithChildren(T item);

	/**
	 * Delete only children of type O from current item.
	 *
	 * @param item
	 */
	// Lucie test soon (4)
	public <O> void deleteChildren(T item);
}
