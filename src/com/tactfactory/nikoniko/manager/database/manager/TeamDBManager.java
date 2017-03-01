package com.tactfactory.nikoniko.manager.database.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.Team;
import com.tactfactory.nikoniko.models.User;

public class TeamDBManager extends BaseDBManager<Team> {

	@Override
	public String getValues(Team item) {
		// TODO Auto-generated method stub
		return null;
	}

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
	public ArrayList<Team> getAll() {
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

//	@Override
//	public void delete(Team item) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public <O> void mapRelation(Team item, O relation) {
	
		if(relation.getClass().getSimpleName().equals("Project")) {
			Project project = (Project)relation;
			
			//check existing relation in team_project table
			// --------------------------------------------
			String query = "SELECT * FROM " + "team_project" + " WHERE id = " + item.getId() + " AND id_project = " + project.getId();
			ResultSet res = MySQLAccess.getInstance().resultQuery(query);
			
			//insert relation
			//---------------
			try {
				if(!res.next()) {
					query = "INSERT INTO " + "team_project" + " (id,id_project)"  +
							" VALUES (" + item.getId() + "," + project.getId() + ")";	
					MySQLAccess.getInstance().updateQuery(query);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(relation.getClass().getSimpleName().equals("User")) {
			User user = (User)relation;
			
			//check existing relation in team_project table
			// --------------------------------------------
			String query = "SELECT * FROM " + "user_team" + " WHERE id = " + user.getId() + " AND id_team = " + item.getId();
			ResultSet res = MySQLAccess.getInstance().resultQuery(query);
			
			//insert relation
			//---------------
			try {
				if(!res.next()) {
					query = "INSERT INTO " + "user_team" + " (id,id_team)"  +
							" VALUES (" + user.getId() + "," + item.getId() + ")";	
					MySQLAccess.getInstance().updateQuery(query);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("mapRelation for Team, inconsistent relation with " + relation.getClass().getSimpleName());
		}
	}


	@Override
	public void updateWithChildren(Team item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <O> void updateChildren(Team item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteWithChildren(Team item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <O> void deleteChildren(Team item) {
		// TODO Auto-generated method stub
		
	}

}
