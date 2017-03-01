package com.tactfactory.nikoniko.manager.database.manager.base;

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
import com.tactfactory.nikoniko.utils.MySQLAnnotation;

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

		for (Method method : DumpFields.getSetter(item.getClass())) {
			if (method.getParameterTypes()[0] == int.class) {
				try {
					method.invoke(item, resultSet.getInt(method.getAnnotation(MySQLAnnotation.class).mySQLFieldName()));
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
			}else if (method.getParameterTypes()[0] == Date.class) {

			}else if (method.getParameterTypes()[0] == Integer.class) {

			}else if (method.getParameterTypes()[0] == String.class) {

			}else if (method.getParameterTypes()[0] == Boolean.class) {

			}else if (method.getParameterTypes()[0] == boolean.class) {

			}else if (method.getParameterTypes()[0] == long.class) {

			}else if (method.getParameterTypes()[0] == Long.class) {

			}else if (method.getParameterTypes()[0] == double.class) {

			}else if (method.getParameterTypes()[0] == Double.class) {

			}else if (method.getParameterTypes()[0] == BigDecimal.class) {

			}else if (method.getParameterTypes()[0] == float.class) {

			}else if (method.getParameterTypes()[0] == Float.class) {

			}else if (method.getParameterTypes()[0] == char.class) {

			}else if (method.getParameterTypes()[0] == byte.class) {

			}else if (method.getParameterTypes()[0] == Byte.class) {

			}else if (method.getParameterTypes()[0] == short.class) {

			}else if (method.getParameterTypes()[0] == Short.class) {

			}

		}

		return item;
	}
}
