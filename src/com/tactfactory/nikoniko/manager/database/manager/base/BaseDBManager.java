package com.tactfactory.nikoniko.manager.database.manager.base;

import java.io.IOException;
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
	
	public T getByIdFull(T item){
		
		item = getById(item);
		getAssociateObject(item);
		
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
	
	public void getAssociateObject(T item){
		ArrayList<String> classname = new ArrayList<String>();
		int i = 0;
		
		try {
			classname = DumpFields.getClassesNames("com.tactfactory.nikoniko.models");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		System.out.println(item.getClass().getSimpleName());
		fields = item.getClass().getFields();
		System.out.println();
		
		for (i = 0; i < classname.size(); i++) {
			System.out.println("com.tactfactory.nikoniko.models." +classname.get(i));			
			if (item.getClass().getSimpleName().toLowerCase().equals( classname.get(i))) {
				
				System.out.println(i);
				break;
			}
		}
		
		System.out.println(DumpFields.inspectGetter(item.getClass()));
		System.out.println(classname.get(i));
			
		
		
	}
}
