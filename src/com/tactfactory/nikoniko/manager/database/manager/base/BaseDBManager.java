package com.tactfactory.nikoniko.manager.database.manager.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.interfaces.base.IDBManagerBase;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.User;
import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;
import com.tactfactory.nikoniko.utils.DumpFields;

public abstract class BaseDBManager<T extends DatabaseItem> implements IDBManagerBase<T> {

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

	public void purgeTable(String table) {
		
		MySQLAccess.getInstance().updateQuery("DELETE FROM " + table);
	}

	
	//String query = "SELECT * FROM " + "user_team" + " WHERE id = " + user.getId() + " AND id_team = " + item.getId();
	//String query = "SELECT * FROM " + "team_project" + " WHERE id = " + team.getId() + " AND id_project = " + item.getId();
	public void delete(T item) {
		
		//for User, Find if relation table content element which have to be delete
		if(item.getClass().getSimpleName().equals("User")) {
			// In user_team
			//-------------
			String query = "DELETE FROM " + "user_team" + " WHERE id = " + item.getId();
			MySQLAccess.getInstance().updateQuery(query);
			
			// In NikoNiko
			//------------
			query = "DELETE FROM " + NikoNiko.TABLE + " WHERE id_user = " + item.getId();
			MySQLAccess.getInstance().updateQuery(query);
		}
		
		//for Project, Find if relation table content element which have to be delete
		if(item.getClass().getSimpleName().equals("Project")) {
			// In team_project
			//----------------
			String query = "DELETE FROM " + "team_project" + " WHERE id_project = " + item.getId();
			MySQLAccess.getInstance().updateQuery(query);
			
			// In NikoNiko
			//------------
			query = "DELETE FROM " + NikoNiko.TABLE + " WHERE id_project = " + item.getId();
			MySQLAccess.getInstance().updateQuery(query);
		}
		
		
		//for Team, Find if relation table content element which have to be delete
		if(item.getClass().getSimpleName().equals("Team")) {
			// In team_project
			//----------------
			String query = "DELETE FROM " + "team_project" + " WHERE id = " + item.getId();
			MySQLAccess.getInstance().updateQuery(query);
			
			// In user_team
			//-------------
			query = "DELETE FROM " + "user_team" + " WHERE id_team = " + item.getId();
			MySQLAccess.getInstance().updateQuery(query);
		}

		
		//Delete Item in table
		//--------------------
		String query = "DELETE FROM " + item.table + " WHERE id = " + item.getId();
		MySQLAccess.getInstance().updateQuery(query);
	}
}
