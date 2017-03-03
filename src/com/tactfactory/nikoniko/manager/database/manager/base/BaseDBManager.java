package com.tactfactory.nikoniko.manager.database.manager.base;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.interfaces.base.IDBManagerBase;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;
import com.tactfactory.nikoniko.utils.DateConverter;
import com.tactfactory.nikoniko.utils.DumpFields;
import com.tactfactory.nikoniko.utils.mysql.MySQLAnnotation;
import com.tactfactory.nikoniko.utils.mysql.MySQLTypes;

public abstract class BaseDBManager<T extends DatabaseItem> implements IDBManagerBase<T> {

	/**
	 * Retrieve values of item to be set as a string to build queries.
	 * 
	 * @param item
	 * @return query 
	 */
	public String getValues(T item) {

		// Set empty string
		String query = "";

		// Verify if id already exists. If not, return null (in this case DTB auto_increment will be used). 
		//Due to getFields definition, it is impossible to get the item id more efficiently than above
		if (item.getId() != 0) {
			query += item.getId() + ",";
		} else {
			query += "null,";
		}
		
		//Use item.field to have the good order of arguments to fill DTB
		for (String fieldItem : item.fields) {
			
			// For each attributes of item with a MySQLAnnotation
			for (Field field : DumpFields.getFields(item.getClass())) {
				
				// Name of the current found attribute and current element
				// of item.field are equal
				if (fieldItem.equals(field.getAnnotation(MySQLAnnotation.class).fieldName())) {
					
					//For each SQL known type, do the appropriate action to fill the query
					switch (field.getAnnotation(MySQLAnnotation.class).mysqlType()) {
					case DATETIME:
						if (DumpFields.runGetter(field, item) != null) {
							// A date attribute is already set in item
							query += ",'" + DateConverter.getMySqlDatetime((Date)DumpFields.runGetter(field, item))
									+ "'";
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// No date attribute is set but this attribute is not nullable
							query += ",'" + DateConverter.getMySqlDatetime(new Date()) + "'";
						} else {
							query += ",null";
						}
						break;

					case INT:
						if (DumpFields.runGetter(field, item) != null) {
							// A INT attribute is already set in item
							query += ",'" + DumpFields.runGetter(field, item) + "'";
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// Default value of a not nullable INT attribute : -1 (not logic value here)
							// Concat operation is maintained in case of the use of a "defaultValue" method
							query += ",'" + "-1" + "'";
						} else {
							query += ",null";
						}
						break;

					case TINYINT:// TINYINT is the type used for a boolean in our DTB
						if (DumpFields.runGetter(field, item) != null) {
							// A TINYINT (aka boolean) attribute is already set in item
							query += ",'" + DumpFields.runGetter(field, item) + "'";
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// Default value of a not nullable boolean attribute : 0 (false)
							// Concat operation is maintained in case of the use of a "defaultValue" method
							query += ",'" + 0 + "'";
						} else {
							query += ",null";
						}
						break;

					case TEXT:
						if (DumpFields.runGetter(field, item) != null) {
							// A TEXT attribute is already set in item
							query += ",'" + DumpFields.runGetter(field, item) + "'";
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// Default value of a not nullable TEXT attribute : empty string
							// Concat operation is maintained in case of the use of a "defaultValue" method
							query += ",'" + "" + "'";
						} else {
							query += ",null";
						}
						break;

					case DATABASE_ITEM:
						Object dbItem = DumpFields.runGetter(field, item);
						if (dbItem != null && ((DatabaseItem) dbItem).getId() != 0) {
							//An object attribute is already set in item
							query += ",'" + ((DatabaseItem) dbItem).getId() + "'";
						} else {
							//No object associated to item : null
							query += ",null";
						}
						break;

					case ASSOCIATION:
						// if the selected attribute is an ArrayList of object, do nothing (case of association table)
						break;
					default:
						//In case of the selected attribute is an unknown sql type 
						if (DumpFields.runGetter(field, item) != null) {
							//This attribute is already set in item (even if his SQL type is unknown)
							query += ",'" + DumpFields.runGetter(field, item) + "'";
						} else {
							//Set it to null in other cases
							//WARNING : It may create errors when try to fill DTB with this item if 
							//set to not nullable in DTB
							query += ",null";
						}
						break;
					}
				}
			}
		}

		return query;
	}

	@Override
	public void insert(T item) {

		String query = "";

		query += "INSERT INTO " + item.table + " VALUES (";
		query += this.getValues(item);
		query += ")";

		MySQLAccess.getInstance().updateQuery(query);

		if (item.getId() == 0) {
			ResultSet result = MySQLAccess.getInstance().resultQuery("SELECT MAX(id) AS id FROM " + item.table);

			try {
				if (result.next()) {
					item.setId(result.getLong(1));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public T getById(T item) {

		ResultSet query = MySQLAccess.getInstance()
				.resultQuery("SELECT * FROM " + item.table + " WHERE " + item.table + ".id = " + item.getId());
		try {
			if (query.next()) {
				item = this.setObjectFromResultSet(query, item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	public T setObjectFromResultSet(ResultSet resultSet, T item) {

		return null;
	}
	
	public <O extends DatabaseItem> void deleteChildren(T item, O child){
		//Delete only children of Class-type O from current item.
		
		// Set empty string as query
		String query = "";
		
		//1 - Get the related association table
		
			//1.1 - find in item.class the attribute of class-type O
				//1.1.1- find all attribute with sql type ASSOCIATION or DATABASE_ITEM
		for (Field field : DumpFields.getFields(item.getClass())) {
			//If 
			if (field.getAnnotation(MySQLAnnotation.class).mysqlType()== MySQLTypes.DATABASE_ITEM 
					&& DumpFields.runGetter(field, item).getClass().getSimpleName()
					.equals(child.getClass().getSimpleName())) {

			}
			
		}
				//1.1.2 - select from these attributes the one with the class-type O
				//Beware that some elements can be ArrayLists<O> and we need to find their type
		
		//2 - Destroy all association between object and his children (T)
	}

}
