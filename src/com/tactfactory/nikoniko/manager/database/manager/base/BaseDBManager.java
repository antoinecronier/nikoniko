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
import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;
import com.tactfactory.nikoniko.utils.DumpFields;
import com.tactfactory.nikoniko.utils.mysql.MySQLAnnotation;

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
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
				
		for (Field field : DumpFields.getFields(item.getClass())) {
			if (field.getType() == int.class || field.getType() == Integer.class) {
				try {
					DumpFields.getSetter(field).invoke(item, resultSet.getInt(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if (field.getType() == Date.class) {
				try {
					DumpFields.getSetter(field).invoke(item, resultSet.getDate(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if (field.getType() == char.class || field.getType() == String.class) {
				try {
					DumpFields.getSetter(field).invoke(item, resultSet.getString(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
				try {
					DumpFields.getSetter(field).invoke(item, resultSet.getBoolean(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if (field.getType() == long.class || field.getType() == Long.class) {
				try {
					DumpFields.getSetter(field).invoke(item, resultSet.getLong(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if (field.getType() == double.class || field.getType() == Double.class) {
				try {
					DumpFields.getSetter(field).invoke(item, resultSet.getDouble(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if (field.getType() == BigDecimal.class) {
				try {
					DumpFields.getSetter(field).invoke(item, resultSet.getBigDecimal(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if (field.getType() == float.class || field.getType() == Float.class) {
				try {
					DumpFields.getSetter(field).invoke(item, resultSet.getFloat(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if (field.getType() == byte.class || field.getType() == Byte.class) {
				try {
					DumpFields.getSetter(field).invoke(item, resultSet.getByte(field.getAnnotation(MySQLAnnotation.class).fieldName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if (field.getType() == short.class || field.getType() == Short.class) {
				try {
					DumpFields.getSetter(field).invoke(item, resultSet.getShort(field.getAnnotation(MySQLAnnotation.class).fieldName()));
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
}
