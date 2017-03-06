package com.tactfactory.nikoniko.manager.database.manager.base;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.ArrayList;

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
	@Override
	public String getValues(T item) {

		// Set empty string
		String query = "";

		// Verify if id already exists. If not, return null (in this case DTB auto_increment will be used). 
		//Due to getFields definition, it is impossible to get the item id more efficiently than above
		if (item.getId() != 0) {
			query += item.getId();
		} else {
			query += "null";
		}

		// Use item.field to have the good order of arguments to fill DTB
		for (String fieldItem : item.fields) {

			// For each attributes of item with a MySQLAnnotation
			for (Field field : DumpFields.getFields(item.getClass())) {

				// Name of the current found attribute and current element
				// of item.field are equal
				if (fieldItem.equals(field.getAnnotation(MySQLAnnotation.class).fieldName())) {

					// For each SQL known type, do the appropriate action to fill the query
					switch (field.getAnnotation(MySQLAnnotation.class).mysqlType()) {
					case DATETIME:
						if (DumpFields.runGetter(field, item) != null) {
							// A date attribute is already set in item
							query += ",'" + DateConverter.getMySqlDatetime((Date)DumpFields.runGetter(field, item))
									+ "'";
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// No date attribute is set but this attribute is
							// not nullable
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
							query += "," + "-1";
						} else {
							query += ",null";
						}
						break;

					case TINYINT:// TINYINT is the type used for a boolean in
									// our DTB
						if (DumpFields.runGetter(field, item) != null) {
							//A TINYINT (aka boolean) attribute is already set in item
							//No ' for a boolean or else it is see as a VARCHAR type by mySQL
							query += "," + DumpFields.runGetter(field, item);
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// Default value of a not nullable boolean attribute : 0 (false)
							// Concat operation is maintained in case of the use of a "defaultValue" method
							System.out.println("not nullable + non renseignï¿½");
							query += "," + 0 ;
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
							// Default value of a not nullable TEXT attribute :
							// empty string
							// Concat operation is maintained in case of the use
							// of a "defaultValue" method
							query += ",'" + "" + "'";
						} else {
							query += ",null";
						}
						break;

					case DATABASE_ITEM:
						Object dbItem = DumpFields.runGetter(field, item);
						if (dbItem != null && ((DatabaseItem) dbItem).getId() != 0) {
							// An object attribute is already set in item
							query += ",'" + ((DatabaseItem) dbItem).getId() + "'";
						} else {
							// No object associated to item : null
							query += ",null";
						}
						break;

					case ASSOCIATION:
						// if the selected attribute is an ArrayList of object,
						// do nothing (case of association table)
						break;
						
					case CHAR:
						if (DumpFields.runGetter(field, item) != null) {
							// A CHAR attribute is already set in item
							query += ",'" + DumpFields.runGetter(field, item) + "'";
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// Default value of a not nullable CHAR attribute : empty 
							// Concat operation is maintained in case of the use of a "defaultValue" method
							query += ",'" + "" + "'";
						} else {
							query += ",null";
						}
						break;
						
					case VARCHAR:
						if (DumpFields.runGetter(field, item) != null) {
							// A VARCHAR attribute is already set in item
							query += ",'" + DumpFields.runGetter(field, item) + "'";
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// Default value of a not nullable VARCHAR attribute : empty string
							// Concat operation is maintained in case of the use of a "defaultValue" method
							query += ",'" + "" + "'";
						} else {
							query += ",null";
						}
						break;
						
					default:
						// In case of the selected attribute is an unknown sql type
						if (DumpFields.runGetter(field, item) != null) {
							// This attribute is already set in item (even if his SQL type is unknown)
							query += ",'" + DumpFields.runGetter(field, item) + "'";
						} else {
							// Set it to null in other cases
							// WARNING : It may create errors when try to fill DTB with this item if
							// set to not nullable in DTB
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

		// Add list of each field name
		// ---------------------------
		query += "INSERT INTO " + item.table +"(";
		int i=0;
		for (String fieldName : item.fields) {
			if(i>0)
				query += ",";
			query += fieldName;
			i++;
		}
		
		// Add each values
		//----------------
		query += ") VALUES (";
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
	public void update(T item) {
		String fieldsValues = getValues(item);
		String[] list = fieldsValues.split(",");
		String query = "UPDATE " + item.table + " SET ";
		int index = 0;
		for (String elem : list) {
			if(elem.length()==0) {
				continue;
			}
			if(index==0) {
				// do not update primary key id
				index++;
				continue;
			}
			String field = item.fields[index];
			query += "`"+field+"`=" +elem + ",";
			index++;
		}
		query = query.substring(0, query.length() - 1);
		query += " WHERE id=";
		query += item.getId();
		query += ";";
		
		MySQLAccess.getInstance().updateQuery(query);
	}

	@Override
	public T getById(T item) {
		//System.out.println("SELECT * FROM " + item.table + " WHERE " + item.table + ".id = " + item.getId());
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

	@Override
	public T setObjectFromResultSet(ResultSet resultSet, T item) {

		try {
			item.setId(resultSet.getLong("id"));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		for (Field field : DumpFields.getFields(item.getClass())) {
			if (field.getType() == int.class || field.getType() == Integer.class) {
				try {
					DumpFields.getSetter(field).invoke(item,
							resultSet.getInt(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (field.getType() == Date.class) {
				try {
					DumpFields.getSetter(field).invoke(item,
							resultSet.getDate(field.getAnnotation(MySQLAnnotation.class).fieldName()));

				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (field.getType() == char.class || field.getType() == String.class) {
				try {
					System.out.println(item.table + ","
							+ resultSet.getString(field.getAnnotation(MySQLAnnotation.class).fieldName()));
					DumpFields.getSetter(field).invoke(item,
							resultSet.getString(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
				try {
					DumpFields.getSetter(field).invoke(item,
							resultSet.getBoolean(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (field.getType() == long.class || field.getType() == Long.class) {
				try {
					DumpFields.getSetter(field).invoke(item,
							resultSet.getLong(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (field.getType() == double.class || field.getType() == Double.class) {
				try {
					DumpFields.getSetter(field).invoke(item,
							resultSet.getDouble(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			} else if (field.getType() == BigDecimal.class) {
				try {
					DumpFields.getSetter(field).invoke(item,
							resultSet.getBigDecimal(field.getAnnotation(MySQLAnnotation.class).fieldName()));

				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (field.getType() == float.class || field.getType() == Float.class) {
				try {
					DumpFields.getSetter(field).invoke(item,

							resultSet.getFloat(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (field.getType() == byte.class || field.getType() == Byte.class) {
				try {
					DumpFields.getSetter(field).invoke(item,
							resultSet.getByte(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (field.getType() == short.class || field.getType() == Short.class) {
				try {
					DumpFields.getSetter(field).invoke(item,
							resultSet.getShort(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

		return item;
	}

	public void purgeTable(String table) {

		MySQLAccess.getInstance().updateQuery("DELETE FROM " + table);
	}

	public void delete(T item) {

		// In associations tables
		// ----------------------
//		for (Field itemField : DumpFields.getFields(item.getClass())) {
//			
//			if(itemField.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION) {
//				String relationTableName = itemField.getAnnotation(MySQLAnnotation.class).associationTable();
//				String fieldName = itemField.getAnnotation(MySQLAnnotation.class).fieldName();
//	
//				String query = "DELETE FROM " + relationTableName + " WHERE " + fieldName + " = " + item.getId();
//				MySQLAccess.getInstance().updateQuery(query);
//			}
//		}

		// Delete In item.table
		// --------------------
		String query = "DELETE FROM " + item.table + " WHERE id = " + item.getId();
		MySQLAccess.getInstance().updateQuery(query);
	}

	public <O extends DatabaseItem> void mapRelation(T item, O relation) {
		for (Field itemField : DumpFields.getFields(item.getClass())) {
			
			// case if item have a ASSOCIATION type
			// ------------------------------------
			if (itemField.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION) {

				// retrieve value (class) of Item Association ArrayList Type
				// ----------------------------------------------------------
				ParameterizedType stringListType = (ParameterizedType) itemField.getGenericType();
				Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];

				// compare Association ArrayList Type with relation Class
				// -------------------------------------------------------
				if (stringListClass == relation.getClass()) {

					String annotationTable = itemField.getAnnotation(MySQLAnnotation.class).associationTable();
					String itemFieldName = itemField.getAnnotation(MySQLAnnotation.class).fieldName();

					// find @MySQLAnnotation fieldName for object relation
					// ----------------------------------------------------
					for (Field relationField : DumpFields.getFields(relation.getClass()))
						
						// case if relation have a ASSOCIATION type
						// ----------------------------------------
						if (relationField.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION) {

							// retrieve value (class) of Relation Association arrayList Type
							// -------------------------------------------------------------
							stringListType = (ParameterizedType) relationField.getGenericType();
							stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];

							// compare Association ArrayList Type with relation Class
							// -------------------------------------------------------
							if (stringListClass == item.getClass()) {
								String relationFieldName = relationField.getAnnotation(MySQLAnnotation.class)
										.fieldName();

								// check existing relation in user_team table
								// -----------------------------------------
								String query = "";
								query = "SELECT * FROM " + annotationTable + " WHERE " + relationFieldName + " = "
										+ relation.getId() + " AND " + itemFieldName + " = " + item.getId();
								ResultSet res = MySQLAccess.getInstance().resultQuery(query);

								// insert relation
								// ---------------
								try {
									if (!res.next()) {
										query = "INSERT INTO " + annotationTable + " (" + itemFieldName + ","
												+ relationFieldName + ")" + " VALUES (" + item.getId() + ","
												+ relation.getId() + ")";
										MySQLAccess.getInstance().updateQuery(query);
									} else {
										System.err.println("  Warning relation already exist between " + itemFieldName + "="
												+ item.getId() + " and " + relationFieldName + "=" + relation.getId()
												+ " in association table " + annotationTable);
									}
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
						
						// case if relation have a DATABASE_ITEM type
						// ------------------------------------------
						} else if (relationField.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.DATABASE_ITEM) {

							if(relationField.getType() == item.getClass()) {
								// insert relation
								// ---------------
								String fieldName = relationField.getAnnotation(MySQLAnnotation.class).fieldName();
								String query = "UPDATE " + annotationTable + " SET " + fieldName + "=" + item.getId();
								MySQLAccess.getInstance().updateQuery(query);
							}
						}
				}
				
			// case if item have a DATABASE_ITEM type
			// --------------------------------------
			} else if (itemField.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.DATABASE_ITEM) {
				
				// Find the appropriate field of DATABASE_ITEM
				if(itemField.getType() == relation.getClass()) {
					// insert relation
					// ---------------
					String fieldName = itemField.getAnnotation(MySQLAnnotation.class).fieldName();
					String query = "UPDATE " + item.table + " SET " + fieldName + "=" + relation.getId();
					MySQLAccess.getInstance().updateQuery(query);
				}
			}
		}
	}

	public void updateWithChildren(T item) {	
//		if(item.getClass().getAnnotation(MySQLAnnotation.class)==null) {
//			// todo : throw exception
//			System.out.println("todo : throw exception");
//			return;
//		}
		update(item);
		for(Field field : DumpFields.getFields(item.getClass())) {
			if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION) {
				//String table = field.getAnnotation(MySQLAnnotation.class).associationTable();
				//String field_into_table = field.getAnnotation(MySQLAnnotation.class).fieldName();
				ArrayList<T> list = (ArrayList<T>) DumpFields.runGetter(field, item);
				for (T elem : list) {
					mapRelation(item, elem);
				}
			}
			else if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.DATABASE_ITEM) {
				DatabaseItem dbItem = (DatabaseItem) DumpFields.runGetter(field, item);
				if(dbItem!=null) {
					update((T) dbItem);
				}
			}		
		}
	}

	// recuperation dans une liste d'objets tout ce qu'il y a dans une table
	@Override
	public ArrayList<T> getAll(Class<T> clazz) {

		// crï¿½ation d'un objet vide ï¿½ partir d'une classe
		T item = DumpFields.createContentsEmpty(clazz);

		// crï¿½ation d'une requete de sï¿½lection totale de tout ce qu'il y a
		// dans
		// la table liï¿½e ï¿½ cet objet
		ResultSet query = MySQLAccess.getInstance().resultQuery("SELECT * FROM " + item.table);

		// crï¿½ation d'une liste d'objets
		ArrayList<T> malistedobjets = new ArrayList<T>();

		try {
			// tant que la requete a des resultats
			while (query.next()) {
				// crï¿½ation d'un objet vide ï¿½ partir d'une classe
				T temp = DumpFields.createContentsEmpty(clazz);

				// remplir la liste d'objets avec les rï¿½sultats
				malistedobjets.add(setObjectFromResultSet(query, temp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// retourne la liste d'objets
		return malistedobjets;
	}

	public <O extends DatabaseItem> void updateChildren(T item, O sampleChild) {
		
		//For all fields of item
		for (Field field : DumpFields.getFields(item.getClass())) {
			
			//Search the ones whith SQLType : ASSOCIATION (ArrayList<Objects>) or DATABASE_ITEM (simple object)
			if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION){

				//Script simplification variable (need two "if" because not all fields are ArrayLists)
				ArrayList<DatabaseItem> childrenList = (ArrayList<DatabaseItem>)DumpFields.runGetter(field, item);
				
				//Choose only the "ASSOCIATION" SQLType field with the same class as O
				if (childrenList.get(0).getClass().equals(sampleChild.getClass())) {
					
					//Get the className of objects in children list
					String classSimpleName = ""+ childrenList.get(0).getClass().getSimpleName();
					
					//Fill the path of the associated DBManager
					String classPath = ""; 
					classPath += "com.tactfactory.nikoniko.manager.database.manager.";
					classPath += classSimpleName ;
					classPath += "DBManager";					
					
					//Create an object which will become the associated DBManager
					Object dbmanager = new Object();
					
					try {
						
						//Instantiate DBManager
						Class ObjetClas = Class.forName(classPath);
						dbmanager = DumpFields.createContentsEmpty(ObjetClas);
						
					} catch (ClassNotFoundException e) {
						
						//If DBManager doesn't exist, do nothing (break)
						//Used to improve performance by preventing execution of the whole code in the try catch
						e.printStackTrace();
						break;
					} 
					
					//Loop over all element of the ArrayList<children>
					for (int i = 0; i < childrenList.size(); i++) {
						System.out.println("child = " + childrenList.get(i).getClass());						
						//If child id is  equal to 0 (i.e. : null)
						if (childrenList.get(i).getId() == 0) { 
							
							//Insert child if not referenced in DTB
							((BaseDBManager)dbmanager).insert((DatabaseItem)childrenList.get(i));
							
						} else {
							
							//Flag to see if the child already exist in DTB (false =  don't exist)
							Boolean flag = ((BaseDBManager) dbmanager).existById((DatabaseItem)childrenList.get(i));
							
							//If child ID is not null but not already filled in DTB					
							if (flag) {

								//Insert this child at the selected ID (User have to be careful with this part)
								((BaseDBManager)dbmanager).insert((DatabaseItem)childrenList.get(i));

							} else {
								
								//Create empty query to update our child
								String query = "";
								
								//Enter child_table_name in the update query
								query += "UPDATE " + childrenList.get(i).table +" SET ";
								
								//Add all "Field = value" to the update query
								query += ((BaseDBManager)dbmanager)
										.getValuesForUpdate((DatabaseItem)childrenList.get(i));
								
								//Add the child ID to the update query
								query += " WHERE id = " + ((DatabaseItem)childrenList.get(i)).getId();
								
								//launch update query in dTB
								MySQLAccess.getInstance().updateQuery(query);
							}
						}			
					}
				}
				
			} else if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.DATABASE_ITEM
					&& field.getType().equals(sampleChild.getClass())) {
				
				System.out.println("on rentre dans un niko");
				
				//Script simplification variable 
				DatabaseItem child = (DatabaseItem)DumpFields.runGetter(field, item);
				
				//Get the className of objects in children list
				String classSimpleName = "" + child.getClass().getSimpleName();
				
				//Fill the path of the associated DBManager
				String classPath = ""; 
				classPath += "com.tactfactory.nikoniko.manager.database.manager.";
				classPath += classSimpleName ;
				classPath += "DBManager";					
				
				//Create an object which will become the associated DBManager
				Object dbmanager = new Object();
				System.out.println("pb?");
				try {
					//Instantiate DBManager
					Class ObjetClas = Class.forName(classPath);
					dbmanager = DumpFields.createContentsEmpty(ObjetClas);
					
				} catch (ClassNotFoundException e) {
					//If DBManager doesn't exist, do nothing (break)
					//Used to improve performance by preventing execution of the whole code in the try catch
					e.printStackTrace();
					break;
				} 
				
				//If child id is  equal to 0 (i.e. : null)
				if (child.getId() == 0) { 
					
					//Insert child if not referenced in DTB
					((BaseDBManager)dbmanager).insert((DatabaseItem)child);
					
				} else {
					
					//Flag to see if the child already exist in DTB (false =  don't exist)
					Boolean flag = ((BaseDBManager) dbmanager).existById((DatabaseItem)child);
					
					//If child ID is not null but not already filled in DTB					
					if (flag) {

						//Insert this child at the selected ID (User have to be careful with this part)
						((BaseDBManager)dbmanager).insert((DatabaseItem)child);
						
					//If child ID is not null and is already filled in DTB
					} else {

						//Create empty query to update our child
						String query = "";
						
						//Enter child_table_name in the update query
						query += "UPDATE " + child.table +" SET ";
						
						//Add all "Field = value" to the update query
						query += ((BaseDBManager)dbmanager).getValuesForUpdate((DatabaseItem)child);
						
						//Add the child ID to the update query
						query += " WHERE id = " + ((DatabaseItem)child).getId();
						
						System.out.println(query);
						
						//launch update query in dTB
						MySQLAccess.getInstance().updateQuery(query);
					}
				}
			}	
		}		
	}
	

	public void deleteWithChildren(T item) {

		delete(item);

		ArrayList<Field> fields = DumpFields.getFields(item.getClass());

		// This for loop allows you to find the children fields from item's
		// class and delete the relations.
		for (Field field : fields) {
			if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION) {
				ParameterizedType arrayTypes = (ParameterizedType) field.getGenericType();
				Class<?> containedClass = (Class<?>) arrayTypes.getActualTypeArguments()[0];

				deleteChildren(item, (DatabaseItem) DumpFields.createContentsEmpty(containedClass));

			} else if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.DATABASE_ITEM) {

				deleteChildren(item, (DatabaseItem) DumpFields.createContentsEmpty(field.getType()));
			}
		}
	}

	@Override
	public <O extends DatabaseItem> void deleteChildren(T item, O child) {

		String query = "";
		Field fieldChild = null;
		ArrayList<Field> fields = DumpFields.getFields(item.getClass());

		// This for loop allows you to find the right field from item's class
		// where the wanted children are
		for (Field field : fields) {
			if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION) {
				ParameterizedType arrayTypes = (ParameterizedType) field.getGenericType();
				Class<?> containedClass = (Class<?>) arrayTypes.getActualTypeArguments()[0];
				if (containedClass == child.getClass()) {
					fieldChild = field;
				}

			} else if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.DATABASE_ITEM) {
				if (field.getType() == child.getClass()) {
					fieldChild = field;
				}
			}
		}

		if (fieldChild.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION) {

			Field fieldItem = null;
			ArrayList<Field> fieldsItem = DumpFields.getFields(child.getClass());

			// This for loop allows you to find the right field from child's
			// class where the item is
			for (Field field : fieldsItem) {
				if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION) {
					ParameterizedType arrayTypes = (ParameterizedType) field.getGenericType();
					Class<?> containedClass = (Class<?>) arrayTypes.getActualTypeArguments()[0];
					if (containedClass == item.getClass()) {
						fieldItem = field;
					}

				} else if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.DATABASE_ITEM) {
					if (field.getType() == item.getClass()) {
						fieldItem = field;
					}
				}
			}

			// Case where it's a 1-N relationship (item-children)
			if (fieldItem.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.DATABASE_ITEM) {
				query = "UPDATE " + fieldChild.getAnnotation(MySQLAnnotation.class).associationTable() + " SET "
						+ fieldItem.getAnnotation(MySQLAnnotation.class).fieldName() + "=NULL WHERE "
						+ fieldItem.getAnnotation(MySQLAnnotation.class).fieldName() + "=" + item.getId();

			}
			// Case where it's a N-N relationship (item-children)
			else if (fieldItem.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION) {
				query = "DELETE FROM " + fieldChild.getAnnotation(MySQLAnnotation.class).associationTable() + " WHERE "
						+ fieldChild.getAnnotation(MySQLAnnotation.class).fieldName() + "=" + item.getId();

			}

		}
		// Case where it's a 1-N relationship (item-child)
		else if (fieldChild.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.DATABASE_ITEM) {
			query = "UPDATE " + item.table + " SET " + fieldChild.getAnnotation(MySQLAnnotation.class).fieldName()
					+ "=NULL WHERE " + item.fields[0] + "=" + item.getId();

		}

		if (!query.equals("")) {
			MySQLAccess.getInstance().updateQuery(query);
		}
	}

	
	public String getValuesForUpdate(T item) {

		// Set empty string
		String query = "";

		// Verify if id already exists. If not, return null (in this case DTB auto_increment will be used). 
		//Due to getFields definition, it is impossible to get the item id more efficiently than above
		if (item.getId() != 0) {
			query += "id=" + item.getId();
		} else {
			query += "id=null";
		}

		// Use item.field to have the good order of arguments to fill DTB
		for (String fieldItem : item.fields) {

			// For each attributes of item with a MySQLAnnotation
			for (Field field : DumpFields.getFields(item.getClass())) {

				// Name of the current found attribute and current element
				// of item.field are equal
				if (fieldItem.equals(field.getAnnotation(MySQLAnnotation.class).fieldName())) {

					// For each SQL known type, do the appropriate action to fill the query
					switch (field.getAnnotation(MySQLAnnotation.class).mysqlType()) {
					case DATETIME:
						if (DumpFields.runGetter(field, item) != null) {
							// A date attribute is already set in item
							query += ","+ fieldItem + "='" + DateConverter.getMySqlDatetime((Date)DumpFields.runGetter(field, item))
									+ "'";
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// No date attribute is set but this attribute is not nullable
							query += ","+ fieldItem + "='" + DateConverter.getMySqlDatetime(new Date()) + "'";

						} else {
							query += ","+ fieldItem + "=null";
						}
						break;

					case INT:
						if (DumpFields.runGetter(field, item) != null) {
							// A INT attribute is already set in item
							query += ","+ fieldItem + "='" + DumpFields.runGetter(field, item) + "'";
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// Default value of a not nullable INT attribute : -1 (not logic value here)
							// Concat operation is maintained in case of the use of a "defaultValue" method
							query += ","+ fieldItem + "=" + "-1";
						} else {
							query += ","+ fieldItem + "=null";
						}
						break;

					case TINYINT:// TINYINT is the type used for a boolean in
									// our DTB
						if (DumpFields.runGetter(field, item) != null) {
							//A TINYINT (aka boolean) attribute is already set in item
							//No ' for a boolean or else it is see as a VARCHAR type by mySQL
							query += ","+ fieldItem + "=" + DumpFields.runGetter(field, item);
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// Default value of a not nullable boolean attribute : 0 (false)
							// Concat operation is maintained in case of the use of a "defaultValue" method
							System.out.println("not nullable + non renseigné");
							query += ","+ fieldItem + "=" + 0 ;
						} else {
							query += ","+ fieldItem + "=null";
						}
						break;

					case TEXT:
						if (DumpFields.runGetter(field, item) != null) {
							// A TEXT attribute is already set in item
							query += ","+ fieldItem + "='" + DumpFields.runGetter(field, item) + "'";
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// Default value of a not nullable TEXT attribute : empty string
							// Concat operation is maintained in case of the use of a "defaultValue" method
							query += ","+ fieldItem + "='" + "" + "'";
						} else {
							query += ","+ fieldItem + "=null";
						}
						break;

					case DATABASE_ITEM:
						Object dbItem = DumpFields.runGetter(field, item);
						if (dbItem != null && ((DatabaseItem) dbItem).getId() != 0) {
							// An object attribute is already set in item
							query += ","+ fieldItem + "='" + ((DatabaseItem) dbItem).getId() + "'";
						} else {
							// No object associated to item : null
							query += ","+ fieldItem + "=null";
						}
						break;

					case ASSOCIATION:
						// if the selected attribute is an ArrayList of object, do nothing (case of association table)
						break;
						
					case CHAR:
						if (DumpFields.runGetter(field, item) != null) {
							// A CHAR attribute is already set in item
							query += ","+ fieldItem + "='" + DumpFields.runGetter(field, item) + "'";
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// Default value of a not nullable CHAR attribute : empty 
							// Concat operation is maintained in case of the use of a "defaultValue" method
							query += ","+ fieldItem + "='" + "" + "'";
						} else {
							query += ","+ fieldItem + "=null";
						}
						break;
						
					case VARCHAR:
						if (DumpFields.runGetter(field, item) != null) {
							// A VARCHAR attribute is already set in item
							query += ","+ fieldItem + "='" + DumpFields.runGetter(field, item) + "'";
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// Default value of a not nullable VARCHAR attribute : empty string
							// Concat operation is maintained in case of the use of a "defaultValue" method
							query += ","+ fieldItem + "='" + "" + "'";
						} else {
							query += ","+ fieldItem + "=null";
						}
						break;
						
					default:
						// In case of the selected attribute is an unknown sql type
						if (DumpFields.runGetter(field, item) != null) {
							// This attribute is already set in item (even if his SQL type is unknown)
							query += ","+ fieldItem + "='" + DumpFields.runGetter(field, item) + "'";
						} else {
							// Set it to null in other cases
							// WARNING : It may create errors when try to fill DTB with this item if
							// set to not nullable in DTB
							query += ","+ fieldItem + "=null";
						}
						break;
					}
				}
			}
		}

		return query;
	}
	
	
	public Boolean existById (T item){
		//Flag to see if item already exist in DTB (false =  don't exist)
		Boolean flag = false;
		
		//Query DTB to ask if item already exist 
		ResultSet query = MySQLAccess.getInstance().resultQuery("SELECT * FROM " 
						+ item.table + " WHERE " + item.table + ".id = " + item.getId());
		
		//try to see if the item exist (set flag to true if yes)
		try {
			//there is no data for this resulset
			flag=!query.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
