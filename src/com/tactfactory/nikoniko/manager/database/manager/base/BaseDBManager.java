package com.tactfactory.nikoniko.manager.database.manager.base;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.mysql.jdbc.Field;
import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.interfaces.base.IDBManagerBase;
import com.tactfactory.nikoniko.models.NikoNiko;
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
	
	public void getAssociatedObject(T item) {
		
		String query = "";
		
		query += "SELECT * FROM nikoniko WHERE id_User = " + item.getId();
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		
		// Définition des listes pour récupérer le nom des classes et les attributs
		ArrayList<String> className = new ArrayList<>(); 
		ArrayList<String> classAtrributs = new ArrayList<>(); 
		
		// Récupération du nom de la classe correspondant à l'objet
		System.out.println(item.getClass().getSimpleName());
		
		// On récupère le nom de toutes les classes contenues dans le package models
		try {
				className = DumpFields.getClassesNames("com.tactfactory.nikoniko.models");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		// On fait une boucle sur la liste "classeName" pour savoir si le nom de la
		// classe correspond bien à celui de son objet
		for (int i = 0; i < className.size(); i++) {
			
			if (item.getClass().getName().toLowerCase().equals("com.tactfactory.nikoniko.models." + className.get(i).toString())){
				
				// Si c'est bon on print qu'on a trouvé la bonne classe
				System.out.println("Classe trouvée");
				
				// On ajoute ensuite tous les attributs de la classe concernée dans une liste
				classAtrributs = DumpFields.inspectBaseAttribut(item.getClass());
				
				// On boucle sur tous les attributs et on chercher si un des attributs (liste)
				// correspond au nom d'une classe
				for (int j = 0; j < classAtrributs.size(); j++) {
					
					// Si c'est bon alors alors on récupère ce qu'il y a dans la relation
					if (classAtrributs.get(j) == className.get(i).toLowerCase()) {
						
						try {
							while (result.next()) {
							//item.get(classAtrributs.get(j)).add(ObjectManager.getObjectById(result.getLong("id")));
								}
							} 
						
						catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
						
					
					}
				}
			}
			
			else {
				// Pas de classe trouvée pour l'objet correspondant
				System.out.println("Pas de classe correspondante trouvée");
			}
		}
	} 
		
			
//				String query = "";
//				query += "SELECT * FROM object_item WHERE id = " +  item.getId();
//				ResultSet result = MySQLAccess.getInstance().resultQuery(query);
//				
//				try {
//					while (result.next()) {
//						//on ajoute à l'arraylist Object l'item trouvee pas son id
//						item.getObject().add(Manager.getItemById(result.getLong("id_"+ object)));				
//					}				
//				} catch (Exception e) {
//					// TODO: handle exception
//				}	
	

}

