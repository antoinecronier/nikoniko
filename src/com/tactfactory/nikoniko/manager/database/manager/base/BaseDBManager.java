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

		// Verify if id already exists. If not, return null (in this case DTB
		// auto_increment will be used). Due to getFields definition, it is
		// impossible
		// to get the item id more efficiently than above
		if (item.getId() != 0) {
			query += item.getId() + ",";
		} else {
			query += "null,";
		}

		// Use item.field to have the good order of arguments to fill DTB
		for (String fieldItem : item.fields) {

			// For each attributes of item with a MySQLAnnotation
			for (Field field : DumpFields.getFields(item.getClass())) {

				// Name of the current found attribute and current element
				// of item.field are equal
				if (fieldItem.equals(field.getAnnotation(MySQLAnnotation.class).fieldName())) {

					// For each SQL known type, do the appropriate action to
					// fill the query
					switch (field.getAnnotation(MySQLAnnotation.class).mysqlType()) {
					case DATETIME:
						if (DumpFields.runGetter(field, item) != null) {
							// A date attribute is already set in item
							query += ",'" + DateConverter.getMySqlDatetime((Date) DumpFields.runGetter(field, item))
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
							// Default value of a not nullable INT attribute :
							// -1 (not logic value here)
							// Concat operation is maintained in case of the use
							// of a "defaultValue" method
							query += ",'" + "-1" + "'";
						} else {
							query += ",null";
						}
						break;

					case TINYINT:// TINYINT is the type used for a boolean in
									// our DTB
						if (DumpFields.runGetter(field, item) != null) {
							// A TINYINT (aka boolean) attribute is already set
							// in item
							query += ",'" + DumpFields.runGetter(field, item) + "'";
						} else if (DumpFields.runGetter(field, item) == null
								&& !field.getAnnotation(MySQLAnnotation.class).nullable()) {
							// Default value of a not nullable boolean attribute
							// : 0 (false)
							// Concat operation is maintained in case of the use
							// of a "defaultValue" method
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
					default:
						// In case of the selected attribute is an unknown sql
						// type
						if (DumpFields.runGetter(field, item) != null) {
							// This attribute is already set in item (even if
							// his SQL type is unknown)
							query += ",'" + DumpFields.runGetter(field, item) + "'";
						} else {
							// Set it to null in other cases
							// WARNING : It may create errors when try to fill
							// DTB with this item if
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
			} else if (field.getType() == String.class) {
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
			} else if (field.getType() == char.class) {
				try {
					DumpFields.getSetter(field).invoke(item,
							resultSet.getString(field.getAnnotation(MySQLAnnotation.class).fieldName()).charAt(0));
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

	// recuperation dans une liste d'objets tout ce qu'il y a dans une table
	@Override
	public ArrayList<T> getAll(Class<T> clazz) {

		// cr�ation d'un objet vide � partir d'une classe
		T item = DumpFields.createContentsEmpty(clazz);

		// cr�ation d'une requete de s�lection totale de tout ce qu'il y a dans
		// la table li�e � cet objet
		ResultSet query = MySQLAccess.getInstance().resultQuery("SELECT * FROM " + item.table);

		// cr�ation d'une liste d'objets
		ArrayList<T> malistedobjets = new ArrayList<T>();

		try {
			// tant que la requete a des resultats
			while (query.next()) {
				// cr�ation d'un objet vide � partir d'une classe
				T temp = DumpFields.createContentsEmpty(clazz);

				// remplir la liste d'objets avec les r�sultats
				malistedobjets.add(setObjectFromResultSet(query, temp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// retourne la liste d'objets
		return malistedobjets;
	}

	@Override
	public void deleteWithChildren(T item) {

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

		delete(item);
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

	@Override
	public void getAssociateObject(T item) {

		ArrayList<Field> fields = DumpFields.getFields(item.getClass());

		// This for loop allows you to find the children fields from item's
		// class and delete the relations.
		for (Field field : fields) {
			if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION) {
				ParameterizedType arrayTypes = (ParameterizedType) field.getGenericType();
				Class<?> containedClass = (Class<?>) arrayTypes.getActualTypeArguments()[0];

				try {
					DumpFields.getSetter(field).invoke(item,
							getAssociatedArray(item, (DatabaseItem) DumpFields.createContentsEmpty(containedClass)));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}

			} else if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.DATABASE_ITEM) {

				try {
					DumpFields.getSetter(field).invoke(item,
							getChild(item, (DatabaseItem) DumpFields.createContentsEmpty(field.getType())));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public <O extends DatabaseItem> ArrayList<O> getAssociatedArray(T item, O child) {

		ArrayList<O> array = new ArrayList<O>();
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
			}
		}

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

		query = "SELECT * FROM " + fieldChild.getAnnotation(MySQLAnnotation.class).associationTable() + " WHERE "
				+ fieldChild.getAnnotation(MySQLAnnotation.class).fieldName() + "=" + item.getId();
		ResultSet select = MySQLAccess.getInstance().resultQuery(query);

		if (fieldItem.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.ASSOCIATION) {
			try {
				while (select.next()) {
					DatabaseItem newChild = DumpFields.createContentsEmpty(child.getClass());
					Long id = select.getLong(fieldItem.getAnnotation(MySQLAnnotation.class).fieldName());
					newChild.setId(id);

					// find the right DBManager...
					Object DBmanager = new Object();
					DBmanager = DumpFields.createContentsEmpty(DumpFields.getAssociatedDB(child));

					array.add((O) ((BaseDBManager) DBmanager).getById(newChild));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (fieldItem.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.DATABASE_ITEM) {
			try {
				while (select.next()) {
					DatabaseItem newChild = DumpFields.createContentsEmpty(child.getClass());
					Long id = select.getLong(child.fields[0]);
					newChild.setId(id);

					// find the right DBManager...
					Object DBmanager = new Object();
					DBmanager = DumpFields.createContentsEmpty(DumpFields.getAssociatedDB(child));

					array.add((O) ((BaseDBManager) DBmanager).getById(newChild));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}

	public <O extends DatabaseItem> O getChild(T item, O child) {
		String query = "";
		Field fieldChild = null;
		ArrayList<Field> fields = DumpFields.getFields(item.getClass());

		// This for loop allows you to find the right field from item's class
		// where the wanted children are
		for (Field field : fields) {
			if (field.getAnnotation(MySQLAnnotation.class).mysqlType() == MySQLTypes.DATABASE_ITEM) {
				if (field.getType() == child.getClass()) {
					fieldChild = field;
				}
			}
		}

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
			}
		}

		query = "SELECT " + fieldChild.getAnnotation(MySQLAnnotation.class).fieldName() + " FROM " + item.table
				+ " WHERE " + item.fields[0] + "=" + item.getId();
		ResultSet select = MySQLAccess.getInstance().resultQuery(query);

		try {
			while (select.next()) {
				DatabaseItem newChild = DumpFields.createContentsEmpty(child.getClass());
				Long id = select.getLong(fieldItem.getAnnotation(MySQLAnnotation.class).fieldName());
				newChild.setId(id);

				// find the right DBManager...
				Object DBmanager = new Object();
				DBmanager = DumpFields.createContentsEmpty(DumpFields.getAssociatedDB(child));

				child = (O) ((BaseDBManager) DBmanager).getById(newChild);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return child;

	}
}
