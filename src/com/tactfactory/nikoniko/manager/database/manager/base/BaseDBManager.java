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
import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;
import com.tactfactory.nikoniko.utils.DateConverter;
import com.tactfactory.nikoniko.utils.DumpFields;
import com.tactfactory.nikoniko.utils.mysql.MySQLAnnotation;

public abstract class BaseDBManager<T extends DatabaseItem> implements IDBManagerBase<T> {

	/**
	 * Retrieve values of item to be set as a string to build queries.
	 * @param item
	 * @return
	 */
	public String getValues(T item) {
		
		//Set empty string
		String query = "";
		
		//Get all attributes names and associated values from given item
		Map<String, Object> fields =  DumpFields.fielder(item);
		
		//Create string ArrayList to get all attribute names from item class
		ArrayList<String> attributes = new ArrayList<String>();
		
		//Fill attributes's ArrayList
		for (Map.Entry<String, Object> iterable_element : fields.entrySet()) {
			attributes.add(iterable_element.getKey());
		}
		
		//Find not nullable elements from DTB and store them into an ArrayList
		
		
		
		//Split field attribute string into a list
		
		
		//For each elements of attributes from 3rd element to the end
		//fill database with getters of item
		
		
		//une fonction faite par antoinne me permet de recuperer les types des attributs
		//de la classe item sous format sql ainsi qu'un flag definissant s'ils sont 
		//nullable ou non. (modif dans chaque Classe pour definir le type sql des attributs)
		
		
		
		
		//la fonction en question est :  ArrayList<Field>getFields(item.getClass())
		//
		
		
		
		
		
		
//		//Verify if id already exists. If not, return null (in this case DTB 
//		//auto_increment will be used). 
//		if (item.getId() != 0) {
//			query += item.getId() + ",";
//		} else {
//			query += "null,";
//		}
//
//		
//		//For relations between item and other class, verify in DTB if there is 
//		//any associated object to get their id (ex "user_id" field)
//		
//
//		if (item.getUser() != null && item.getUser().getId() != 0) {
//			query += item.getUser().getId() + ",";
//		} else {
//			query += "null,";
//		}

				
		//Return filled query
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
			ResultSet result = MySQLAccess.getInstance().resultQuery(
					"SELECT MAX(id) AS id FROM " + item.table);

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

		ResultSet query = MySQLAccess.getInstance().resultQuery(
				"SELECT * FROM " + item.table + " WHERE " + item.table
						+ ".id = " + item.getId());
		try {
			if (query.next()) {
				item = this.setObjectFromResultSet(query,item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	public T setObjectFromResultSet(ResultSet resultSet,T item) {

		return null;
	}
}
