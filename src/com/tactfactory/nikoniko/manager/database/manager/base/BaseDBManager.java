package com.tactfactory.nikoniko.manager.database.manager.base;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.interfaces.base.IDBManagerBase;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;
import com.tactfactory.nikoniko.utils.DumpFields;

// iDBManAgerbASE > T = boisson , BaseDBManger > T extends DatabaseItem = ma boisson est une boisosn chaude, NikoNikoDBManger > nikoniko = cafe 
public abstract class BaseDBManager <T extends DatabaseItem>  implements IDBManagerBase<T>{
	
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
	
	public T getById(long id, T item) {
	
			ResultSet query = MySQLAccess.getInstance().resultQuery(
			"SELECT * FROM " + item.table + " WHERE " + item.table
					+ ".id = " + id);
			
			try {
					if (query.next()) {
					item = setObjectFromResult(query);
				}
							
				} catch (SQLException e) 
					
				{
				 e.printStackTrace();
				}
			
				return item;
		}
	


	}


