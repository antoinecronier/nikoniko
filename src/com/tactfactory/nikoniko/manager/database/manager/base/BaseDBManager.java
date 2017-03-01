package com.tactfactory.nikoniko.manager.database.manager.base;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.interfaces.base.IDBManagerBase;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;

import com.tactfactory.nikoniko.utils.DateConverter;
import com.tactfactory.nikoniko.utils.DumpFields;
//iDBManAgerbASE > T = boisson , BaseDBManger > T extends DatabaseItem = ma boisson est une boisosn chaude, NikoNikoDBManger > nikoniko = cafe 
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
	public T getById(long id,T item) {
		
		ResultSet query = MySQLAccess.getInstance().resultQuery(
				"SELECT * FROM " + item.table + " WHERE " + item.table
						+ ".id = " + id);
		try {
			if (query.next()) {
				item = this.setObjectFromResultSet(query,item);
				//item = setObjectFromResult(query);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	
	public static boolean isSetter(Method method) {
		   return Modifier.isPublic(method.getModifiers()) &&
				 method.getReturnType().equals(void.class) &&
		         method.getParameterTypes().length == 1 && 
		         method.getName().matches("^set[A-Z].*");
		}
	
	public boolean isGetter(Method method) {
		if (Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 0) {
			if (method.getName().matches("^get[A-Z].*") && !method.getReturnType().equals(void.class))
				return true;
			if (method.getName().matches("^is[A-Z].*") && method.getReturnType().equals(boolean.class))
				return true;
		   }
		return false;
	}
	//scinder find getter et find setters pourrait etre plus interessant pour la suite
	public ArrayList<Method> findGettersSetters(Class<?> c) {
		   ArrayList<Method> list = new ArrayList<Method>();
		   Method[] methods = c.getDeclaredMethods();
		   for (Method method : methods)
		      if (isGetter(method) || isSetter(method))
		         list.add(method);
		   return list;
		}

	@Override
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (element.getValue().getClass().getName()=="Boolean"){
					try {
						setter.invoke(item, resultSet.getBoolean(element.getKey()));
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (element.getValue().getClass().getName()=="String"){
					try {
						setter.invoke(item, resultSet.getString(element.getKey()));
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (element.getValue().getClass().getName()=="Date"){
					try {
						setter.invoke(item, resultSet.getDate(element.getKey()));
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}

	

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	
	
}
//iDBManAgerbASE > T = boisson , BaseDBManger > T extends DatabaseItem = ma boisson est une boisosn chaude, NikoNikoDBManger > nikoniko = cafe 
