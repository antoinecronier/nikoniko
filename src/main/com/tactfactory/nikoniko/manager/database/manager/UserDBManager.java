package com.tactfactory.nikoniko.manager.database.manager;

import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.User;

public class UserDBManager extends BaseDBManager<User> {

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
	public ArrayList<User> getAll(Class<User> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getAssociateObject(User item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User item) {
		// TODO Auto-generated method stub

	}

	// @Override
	// public void delete(User item) {
	// // TODO Auto-generated method stub
	//
	// }

	/*@Override
	public <O> void mapRelation(User item, O relation) {
<<<<<<< HEAD

		if (relation.getClass().getSimpleName().equals("NikoNiko")) {
			// NikoNiko usr = (NikoNiko)relation;
			// query = "UPDATE " + item.table + " SET id_user = " + usr.getId()
			// + " WHERE id = " + item.getId();
			// MySQLAccess.getInstance().updateQuery(query);
			System.err.println("No Sql relation table exist between User and NikoNiko tables");
		} else if (relation.getClass().getSimpleName().equals("Team")) {
			Team team = (Team) relation;
			ArrayList<Field> fields = DumpFields.getFields(item.getClass());
			Field fieldTeams = null;
			for (Field field : fields) {
				if ( field.getName().equals("Team")) {
					fieldTeams = field;
				}
			}

			// check existing relation in user_team table
			// -----------------------------------------
			String query = "SELECT * FROM " + fieldTeams.getAnnotation(MySQLAnnotation.class).associationTable() + " WHERE id_team = " + team.getId() + " AND id = "
					+ item.getId();
			ResultSet res = MySQLAccess.getInstance().resultQuery(query);

			// insert relation
			// ---------------
			try {
				if (!res.next()) {
					query = "INSERT INTO " + fieldTeams.getAnnotation(MySQLAnnotation.class).associationTable() + " (id,id_team)" + " VALUES (" + item.getId() + ","
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
	}*/

//	@Override
//	public void updateWithChildren(User item) {
//		// TODO Auto-generated method stub
//
//	}

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

	@Override
	public <O> void mapRelation(User item, O relation) {
		// TODO Auto-generated method stub
		
	}
}
