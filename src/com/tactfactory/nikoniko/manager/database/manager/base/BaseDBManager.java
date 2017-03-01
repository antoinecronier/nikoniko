package com.tactfactory.nikoniko.manager.database.manager.base;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.interfaces.base.IDBManagerBase;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;

public abstract class BaseDBManager<T extends DatabaseItem> implements IDBManagerBase<T>{
	

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
	
	public T getById(long id, T item) {
		ResultSet query = MySQLAccess.getInstance().resultQuery(
		"SELECT * FROM " + item.table + " WHERE " + item.table
				+ ".id = " + id);
		
		try {
			if (query.next()) {
				item = setObjectFromResult(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}
	
	//public T setObjectFromResult(ResultSet resulset, T item) {
		/*item = Object.class.getClass().getFields();
		try {
			nikoNiko.setId(resulset.getLong("id"));
			nikoNiko.setLog_date(resulset.getDate("log_Date"));
			nikoNiko.setChange_date(resulset.getDate("change_Date"));
			nikoNiko.setSatisfaction(resulset.getInt("satisfaction"));
			nikoNiko.setComment(resulset.getString("nikoniko_comment"));
			nikoNiko.setIsAnonymous(resulset.getBoolean("isanonymous"));

			//UserDBManager userDBManager = new UserDBManager();
			//nikoNiko.setUser(userDBManager.getUserById(resulset.getLong("id_User")));

			//ProjectDBManager projectDBManager = new ProjectDBManager();
			//nikoNiko.setProject(projectDBManager.getProjectById(resulset.getLong("id_Project")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nikoNiko;*/
	//}

}
