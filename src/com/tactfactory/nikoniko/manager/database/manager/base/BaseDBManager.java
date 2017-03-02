package com.tactfactory.nikoniko.manager.database.manager.base;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

//import com.mysql.jdbc.Field;
import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.interfaces.base.IDBManagerBase;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.User;
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

	public T setObjectFromResultSet(ResultSet resultSet, T item) {

		// for (Field field : DumpFields.getFields(item.getClass())) {
		// if (field.getType() == int.class) {
		// try {
		// DumpFields.getSetter(field).invoke(item,
		// resultSet.getInt(field.getAnnotation(MySQLAnnotation.class).fieldName()));
		// } catch (IllegalAccessException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IllegalArgumentException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }else if (method.getParameterTypes()[0] == Date.class) {
		//
		// }else if (method.getParameterTypes()[0] == Integer.class) {
		//
		// }else if (method.getParameterTypes()[0] == String.class) {
		//
		// }else if (method.getParameterTypes()[0] == Boolean.class) {
		//
		// }else if (method.getParameterTypes()[0] == boolean.class) {
		//
		// }else if (method.getParameterTypes()[0] == long.class) {
		//
		// }else if (method.getParameterTypes()[0] == Long.class) {
		//
		// }else if (method.getParameterTypes()[0] == double.class) {
		//
		// }else if (method.getParameterTypes()[0] == Double.class) {
		//
		// }else if (method.getParameterTypes()[0] == BigDecimal.class) {
		//
		// }else if (method.getParameterTypes()[0] == float.class) {
		//
		// }else if (method.getParameterTypes()[0] == Float.class) {
		//
		// }else if (method.getParameterTypes()[0] == char.class) {
		//
		// }else if (method.getParameterTypes()[0] == byte.class) {
		//
		// }else if (method.getParameterTypes()[0] == Byte.class) {
		//
		// }else if (method.getParameterTypes()[0] == short.class) {
		//
		// }else if (method.getParameterTypes()[0] == Short.class) {
		//
		// }
		//
		// }

		return item;
	}

	public void getAssociatedObject(T item)
			throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {

		// On affiche le nom de la classe correspondant à l'objet
		System.out.println(item.getClass().getSimpleName());

		// On récupère tous les fields correspondant à l'objet
		ArrayList<Field> fields = DumpFields.getFields(item.getClass());

		// On récupère toutes les classes du package models
		ArrayList<Class> classes = DumpFields.getClasses("com.tactfactory.nikoniko.models");

		// Pour tous les fields récupérés de la classe correspondante on test
		// les types
		// des fields pour pouvoir faire les relations (si l'objet correspond à la classe)
		for (Field field : fields) {

			Class klazz = field.getType();

			if (classes.contains(klazz)) {

				// Instanciation de la classe klazz
				DatabaseItem object = (DatabaseItem) klazz.newInstance();
								
				// Création de la requète pour recupérer les informations
				String query = "";
				query += "SELECT * FROM " + item.table + " WHERE "
						+ field.getAnnotation(MySQLAnnotation.class).fieldName() + " = " + DumpFields.;

				ResultSet result = MySQLAccess.getInstance().resultQuery(query);
				
				Object tampon = new Object();
				
				System.out.println(query);
				System.out.println(setObjectFromResultSet(result, item));
				
				
				
				DumpFields.createContentsEmpty(field.getType());
				

				
			} else if (klazz == ArrayList.class) {
				System.out.println("ok");
			}
		}
	}

	// String query = "";
	// query += "SELECT * FROM object_item WHERE id = " + item.getId();
	// ResultSet result = MySQLAccess.getInstance().resultQuery(query);
	//
	// try {
	// while (result.next()) {
	// //on ajoute à l'arraylist Object l'item trouvee pas son id
	// item.getObject().add(Manager.getItemById(result.getLong("id_"+ object)));
	// }
	// } catch (Exception e) {
	// // TODO: handle exception
	// }

}
