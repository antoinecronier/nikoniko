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

public class ProjectDBManager extends BaseDBManager<Project> {

	@Override
	public String getValues(Project item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void purgeTable(String table) {
		// TODO Auto-generated method stub

	}

	@Override
	public Project getByIdFull(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Project> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getAssociateObject(Project item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Project item) {
		// TODO Auto-generated method stub

	}

	// @Override
	// public void delete(Project item) {
	// // TODO Auto-generated method stub
	//
	// }

	@Override
	public <O> void mapRelation(Project item, O relation) {

		if (relation.getClass().getSimpleName().equals("NikoNiko")) {
			// NikoNiko usr = (NikoNiko)relation;
			// query = "UPDATE " + item.table + " SET id_user = " + usr.getId()
			// + " WHERE id = " + item.getId();
			// MySQLAccess.getInstance().updateQuery(query);
			System.err.println("No Sql relation table exist between Projects and NikoNiko tables");
		} else if (relation.getClass().getSimpleName().equals("Team")) {
			Team team = (Team) relation;

			// check existing relation in team_project table
			// --------------------------------------------
			String query = "SELECT * FROM " + "team_project" + " WHERE id = " + team.getId() + " AND id_project = "
					+ item.getId();
			ResultSet res = MySQLAccess.getInstance().resultQuery(query);

			// insert relation
			// ---------------
			try {
				if (!res.next()) {
					query = "INSERT INTO " + "team_project" + " (id,id_project)" + " VALUES (" + team.getId() + ","
							+ item.getId() + ")";
					MySQLAccess.getInstance().updateQuery(query);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println(
					"mapRelation for Project, inconsistent relation with " + relation.getClass().getSimpleName());
		}
	}

	@Override
	public void updateWithChildren(Project item) {
		// TODO Auto-generated method stub

	}

	@Override
	public <O> void updateChildren(Project item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteWithChildren(Project item) {
		// TODO Auto-generated method stub

	}

	@Override
	public <O> void deleteChildren(Project item) {
		// TODO Auto-generated method stub

	}

}
