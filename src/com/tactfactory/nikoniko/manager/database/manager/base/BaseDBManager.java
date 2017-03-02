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
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;
import com.tactfactory.nikoniko.utils.DateConverter;
import com.tactfactory.nikoniko.utils.DumpFields;
import com.tactfactory.nikoniko.utils.mysql.MySQLAnnotation;

public abstract class BaseDBManager<T extends DatabaseItem> implements
		IDBManagerBase<T> {

	/**
	 * Retrieve values of item to be set as a string to build queries.
	 *
	 * @param item
	 * @return
	 */
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

		for (String fieldItem : item.fields) {
			// for each element of item.fields (simplification avec un i++ �
			// faire plus tard)
			for (Field field : DumpFields.getFields(item.getClass())) {
				// Correlate java attribute with sql attribute
				if (fieldItem.equals(field.getAnnotation(MySQLAnnotation.class)
						.fieldName())) {
					//
					switch (field.getAnnotation(MySQLAnnotation.class)
							.mysqlType()) {
					case DATETIME:
						if (DumpFields.runGetter(field, item) != null) {
							query += "'"
									+ DateConverter
											.getMySqlDatetime((Date) DumpFields
													.runGetter(field, item))
									+ "',";
						} else if (DumpFields.runGetter(field, item) != null
								&& !field.getAnnotation(MySQLAnnotation.class)
										.nullable()) {
							query += "'"
									+ DateConverter
											.getMySqlDatetime(new Date())
									+ "',";
						} else {
							query += "null,";
						}
						break;

					case INT:
						break;

					case TINYINT:
						break;

					case TEXT:
						break;

					default:
						break;
					}
				}
			}
		}

		// Get all type, nullability and attribute_name of the item class in
		// ArrayLists
		ArrayList<String> fieldsInfo = new ArrayList<String>();
		ArrayList<Boolean> isFieldsNull = new ArrayList<Boolean>();
		ArrayList<String> fieldName = new ArrayList<String>();

		for (Field field : DumpFields.getFields(item.getClass())) {
			fieldsInfo.add(field.getAnnotation(MySQLAnnotation.class)
					.mysqlType().toString());
			isFieldsNull.add(field.getAnnotation(MySQLAnnotation.class)
					.nullable());
			fieldName.add(field.getAnnotation(MySQLAnnotation.class)
					.fieldName());
		}

		// Get all attributes names and associated values from given item
		Map<String, Object> fields = DumpFields.fielder(item);

		// Create string ArrayList to get all attribute names from item class
		ArrayList<String> attributes = new ArrayList<String>();

		// Fill attributes's ArrayList
		for (Map.Entry<String, Object> iterable_element : fields.entrySet()) {
			attributes.add(iterable_element.getKey());
		}

		// Find not nullable elements from DTB and store them into an ArrayList

		// Split field attribute string into a list

		// For each elements of attributes from 3rd element to the end
		// fill database with getters of item

		// une fonction faite par antoinne me permet de recuperer les types des
		// attributs
		// de la classe item sous format sql ainsi qu'un flag definissant s'ils
		// sont
		// nullable ou non. (modif dans chaque Classe pour definir le type sql
		// des attributs)

		// la fonction en question est :
		// ArrayList<Field>getFields(item.getClass())
		//

		//
		// //For relations between item and other class, verify in DTB if there
		// is
		// //any associated object to get their id (ex "user_id" field)
		//
		//
		// if (item.getUser() != null && item.getUser().getId() != 0) {
		// query += item.getUser().getId() + ",";
		// } else {
		// query += "null,";
		// }

		// Return filled query
		return query;
	}

	@Override
	public void insert(T item) {

		String query = "";

		query += "INSERT INTO " + item.table + " VALUES (";
		query += this.getValues(item);
		query += ");";


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
		
		System.out.println("SELECT * FROM " + item.table + " WHERE " + item.table
						+ ".id = " + item.getId());
		ResultSet query = MySQLAccess.getInstance().resultQuery(
				"SELECT * FROM " + item.table + " WHERE " + item.table
						+ ".id = " + item.getId());
		try {
			if (query.next()) {
				item = this.setObjectFromResultSet(query, item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	public T setObjectFromResultSet(ResultSet resultSet, T item) {

		for (Field field : DumpFields.getFields(item.getClass())) {
			if (field.getType() == int.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getInt(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == Date.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getDate(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == Integer.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getInt(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == String.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getString(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == Boolean.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getBoolean(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == boolean.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getBoolean(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == long.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getLong(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == Long.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getLong(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == double.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getDouble(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == Double.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getDouble(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == BigDecimal.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getBigDecimal(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == float.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getFloat(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == Float.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getFloat(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == char.class) {
				try {
					System.out.println("item="+item+", field annotation="+field.getAnnotation(
							MySQLAnnotation.class).fieldName());
					System.out.println("resultSet.getString = "+resultSet.getString(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getString(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == byte.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getByte(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == Byte.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getByte(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == short.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getShort(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (field.getType() == Short.class) {
				try {
					DumpFields.getSetter(field).invoke(
							item,
							resultSet.getShort(field.getAnnotation(
									MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return item;
	}
	
	@Override
	public void update(T item)
	{
		String query;
		Map<String, Object> map;
		
		map = DumpFields.fielder(item);
		String fieldsValues = getValues(item);

		System.out.println("fieldsValues="+fieldsValues);
		
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
		
		System.out.println(query);
		
		MySQLAccess.getInstance().updateQuery(query);
	}
	
	@Override
	public void updateWithChildren(T item){
		Map<String, Object> map;
		map = DumpFields.fielder(item);
		
		String string = "";
		for (String key : map.keySet()) {
			string += key + ", ";
		}
	}
}
