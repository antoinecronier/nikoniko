package com.tactfactory.nikoniko.manager.database.manager.base;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
							System.out.println("not nullable + non renseigné");
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
		for (Field itemField : DumpFields.getFields(item.getClass())) {
			
			if(itemField.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION) {
			String relationTableName = itemField.getAnnotation(MySQLAnnotation.class).associationTable();
			String fieldName = itemField.getAnnotation(MySQLAnnotation.class).fieldName();

			String query = "DELETE FROM " + relationTableName + " WHERE " + fieldName + " = " + item.getId();
			MySQLAccess.getInstance().updateQuery(query);
			}
		}

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

	// recuperation dans une liste d'objets tout ce qu'il y a dans une table
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

	public void deleteWithChildren(T item) {
		delete(item);

		// Find which kind of object is T, to know what association to delete
		switch (item.getClass().getSimpleName()) {
		case "NikoNiko":
		case "User":
		case "Project":
		case "Team":
		}
	}

	public <O extends DatabaseItem> void deleteChildren(T item, O child) {
		String query = "";
		Field fieldChild = null;
		ArrayList<Field> fields = DumpFields.getFields(item.getClass());
		if (item.getClass().getSimpleName().equals("NikoNiko")) {
			for (Field field : fields) {
				String name = child.getClass().getSimpleName();
				name = name.substring(0, 1).toLowerCase() + name.substring(1);
				if (field.getName().equals(name)) {
					fieldChild = field;
				}
			}

			query = "UPDATE FROM nikoniko SET" + fieldChild.getAnnotation(MySQLAnnotation.class).fieldName()
					+ "=NULL WHERE id=" + item.getId();

		} else {
			for (Field field : fields) {
				String name = child.getClass().getSimpleName();
				name = name.substring(0, 1).toLowerCase() + name.substring(1) + "s";
				if (field.getName().equals(name)) {
					fieldChild = field;
				}
				if (child.getClass().getSimpleName().equals("NikoNiko")) {
					ArrayList<NikoNiko> nikos = new ArrayList<NikoNiko>();
					try {
						nikos = (ArrayList<NikoNiko>) DumpFields.getGetter(fieldChild).invoke(item);
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
					for (NikoNiko nikochild : nikos) {
						query = "UPDATE FROM nikoniko SET id_" + item.getClass().getSimpleName() + "=NULL WHERE id="
								+ nikochild.getId();
					}
					
				} else {
					query = "DELETE FROM" + fieldChild.getAnnotation(MySQLAnnotation.class).associationTable()
							+ "WHERE " + fieldChild.getAnnotation(MySQLAnnotation.class).associationName() + "="
							+ item.getId();
				}
			}

		}

	}
	
}
