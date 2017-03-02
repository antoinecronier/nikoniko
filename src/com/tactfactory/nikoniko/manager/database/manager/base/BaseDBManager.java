package com.tactfactory.nikoniko.manager.database.manager.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.interfaces.base.IDBManagerBase;
import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;
import com.tactfactory.nikoniko.utils.DumpFields;

public abstract class BaseDBManager<T extends DatabaseItem> implements IDBManagerBase<T> {
	public boolean insertVerbose=false;
	public boolean updateVerbose=false;
	
	public void setInsertVerbose(boolean value) {
		this.insertVerbose = true;
	}
	
	public void setUpdateVerbose(boolean value) {
		this.insertVerbose = true;
	}
	
    @Override
	public String getValues(T item) {
    	
		Map<String, Object> map;
		map = DumpFields.fielder(item);
		System.out.println("getValues("+item.getClass().getName());
		
		String values = "";
		String string = "";
		for(String field : item.fields) {
			//if(field.equals("id")) continue;
			for (String key : map.keySet()) {
					if(key.equals(field)) {
					string += key + "(OK)=" + map.get(key) + ", ";
					values += "\"" + map.get(key) + "\",";
				}
			}
		}
		values=values.substring(0, values.length()-1);
		System.out.println(string);
		System.out.println(item.table);
		return values;
	}
	
	@Override
	public void insert(T item) {

		String query = "";

		query += "INSERT INTO " + item.table + " VALUES (";
		query += this.getValues(item);
		query += ");";

		if(this.insertVerbose) {
			System.out.println(query);
		}

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
		
		try {
			item.setId(resultSet.getLong("id"));
			
			Map<String,Object> map=DumpFields.fielder(item);
			ArrayList<Method> methods= DumpFields.getSetter(item.getClass());
			
			for (Map.Entry<String, Object> element : map.entrySet()) {
				
				String name = "set"+element.getKey().substring(0, 1).toUpperCase() + element.getKey().substring(1);
				Method setter = null;
				for (Method method : methods) {
					if (method.getName().equals(name)){
						setter=method;
					}
				}
				
				if (element.getValue().getClass().getName()=="Integer"){
					try {
						setter.invoke(item, resultSet.getInt(element.getKey()));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				else if (element.getValue().getClass().getName()=="Boolean"){
					try {
						setter.invoke(item, resultSet.getBoolean(element.getKey()));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				else if (element.getValue().getClass().getName()=="String"){
					try {
						setter.invoke(item, resultSet.getString(element.getKey()));
					} catch (IllegalAccessException e) {			
						e.printStackTrace();
					} catch (IllegalArgumentException e) {		
						e.printStackTrace();
					} catch (InvocationTargetException e) {			
						e.printStackTrace();
					}
				}
				else if (element.getValue().getClass().getName()=="Date"){
					try {
						setter.invoke(item, resultSet.getDate(element.getKey()));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}
	
	@Override
	public void update(T item)
	{
		Map<String, Object> map;
		map = DumpFields.fielder(item);
		
		String query;
		
		String fieldsValues = getValues(item);
		String[] list = fieldsValues.split(",");
		
		query = "UPDATE " + item.table + " SET " ;
		int index=0;
		for(String elem : list) {
			
			query += elem + ",";
		}
		query = query.substring(0, query.length()-1);
		query += " WHERE id=";
		query += item.getId();
		query += ";";
		
		if(this.updateVerbose) {
			System.out.println(query);
		}
		
		MySQLAccess.getInstance().updateQuery(query);
	}
	
	/**
	 * Update all items extract from current item.
	 * Calling "public <O> void updateChildren(T item)" for all kind of children.
	 * @param item
	 */
	public void updateWithChildren(T item){
		Map<String, Object> map;
		map = DumpFields.fielder(item);
		
		String string = "";
		for (String key : map.keySet()) {
			string += key + ", ";
		}
		System.out.println(string);

	}


}
