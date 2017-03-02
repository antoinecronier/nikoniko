package com.tactfactory.nikoniko.manager.database.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.Team;
import com.tactfactory.nikoniko.models.User;

public class UserDBManager extends BaseDBManager<User> {

	@Override
	public String getValues(User item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void purgeTable(String table) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getByIdFull(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getAssociateObject(User item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User item) {
		// TODO Auto-generated method stub

	}

	// @Override
	// public void delete(User item) {
	// // TODO Auto-generated method stub
	//
	// }

	@Override
	public <O> void mapRelation(User item, O relation) {

		if (relation.getClass().getSimpleName().equals("NikoNiko")) {
			// NikoNiko usr = (NikoNiko)relation;
			// query = "UPDATE " + item.table + " SET id_user = " + usr.getId()
			// + " WHERE id = " + item.getId();
			// MySQLAccess.getInstance().updateQuery(query);
			System.err.println("No Sql relation table exist between User and NikoNiko tables");
		} else if (relation.getClass().getSimpleName().equals("Team")) {
			Team team = (Team) relation;

			// check existing relation in user_team table
			// -----------------------------------------
			String query = "SELECT * FROM " + "user_team" + " WHERE id_team = " + team.getId() + " AND id = "
					+ item.getId();
			ResultSet res = MySQLAccess.getInstance().resultQuery(query);

			// insert relation
			// ---------------
			try {
				if (!res.next()) {
					query = "INSERT INTO " + "user_team" + " (id,id_team)" + " VALUES (" + item.getId() + ","
							+ team.getId() + ")";
					MySQLAccess.getInstance().updateQuery(query);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err
					.println("mapRelation for User, inconsistent relation with " + relation.getClass().getSimpleName());
		}
	}

	@Override
	public void updateWithChildren(User item) {
		// TODO Auto-generated method stub

	}

	@Override
	public <O> void updateChildren(User item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteWithChildren(User item) {
		// TODO Auto-generated method stub

	}

	@Override
	public <O> void deleteChildren(User item) {
		// TODO Auto-generated method stub

	}

}
