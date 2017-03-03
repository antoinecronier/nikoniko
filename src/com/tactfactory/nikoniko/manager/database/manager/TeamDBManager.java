package com.tactfactory.nikoniko.manager.database.manager;

import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.Team;

public class TeamDBManager extends BaseDBManager<Team> {

	@Override
	public void purgeTable(String table) {
		// TODO Auto-generated method stub

	}

	@Override
	public Team getByIdFull(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Team> getAll(Class<Team> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getAssociateObject(Team item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Team item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Team item) {
		// TODO Auto-generated method stub

	}

	// @Override
	// public void delete(Team item) {
	// // TODO Auto-generated method stub
	//
	// }

	/*@Override
	public <O> void mapRelation(Team item, O relation) {
<<<<<<< HEAD

		if (relation.getClass().getSimpleName().equals("Project")) {
			Project project = (Project) relation;
			ArrayList<Field> fields = DumpFields.getFields(item.getClass());
			Field fieldTeams = null;
			for (Field field : fields) {
				if ( field.getName().equals("Project")) {
					fieldTeams = field;
				}
			}

			// check existing relation in team_project table
			// --------------------------------------------
			String query = "SELECT * FROM " + fieldTeams.getAnnotation(MySQLAnnotation.class).associationTable() + " WHERE id = " + item.getId() + " AND id_project = "
					+ project.getId();
			ResultSet res = MySQLAccess.getInstance().resultQuery(query);

			// insert relation
			// ---------------
			try {
				if (!res.next()) {
					query = "INSERT INTO " + fieldTeams.getAnnotation(MySQLAnnotation.class).associationTable() + " (id,id_project)" + " VALUES (" + item.getId() + ","
							+ project.getId() + ")";
					MySQLAccess.getInstance().updateQuery(query);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (relation.getClass().getSimpleName().equals("User")) {
			User user = (User) relation;
			ArrayList<Field> fields = DumpFields.getFields(item.getClass());
			Field fieldTeams = null;
			for (Field field : fields) {
				if ( field.getName().equals("User")) {
					fieldTeams = field;
				}
			}

			// check existing relation in team_project table
			// --------------------------------------------
			String query = "SELECT * FROM " + fieldTeams.getAnnotation(MySQLAnnotation.class).associationTable() + " WHERE id = " + user.getId() + " AND id_team = "
					+ item.getId();
			ResultSet res = MySQLAccess.getInstance().resultQuery(query);

			// insert relation
			// ---------------
			try {
				if (!res.next()) {
					query = "INSERT INTO " + fieldTeams.getAnnotation(MySQLAnnotation.class).associationTable() + " (id,id_team)" + " VALUES (" + user.getId() + ","
							+ item.getId() + ")";
					MySQLAccess.getInstance().updateQuery(query);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err
					.println("mapRelation for Team, inconsistent relation with " + relation.getClass().getSimpleName());
		}
	}*/

	@Override
	public void updateWithChildren(Team item) {
		// TODO Auto-generated method stub

	}

	@Override
	public <O> void updateChildren(Team item) {
		// TODO Auto-generated method stub

	}

	@Override
	public <O> void mapRelation(Team item, O relation) {
		// TODO Auto-generated method stub
		
	}
}
