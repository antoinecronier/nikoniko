package com.tactfactory.nikoniko.manager.database.manager;

import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;

public class ProjectDBManager extends BaseDBManager<Project> {


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
	public ArrayList<Project> getAll(Class<Project> clazz) {
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

	@Override
	public void delete(Project item) {
		// TODO Auto-generated method stub

	}

	@Override
	public <O> void mapRelation(Project item, O relation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateWithChildren(Project item) {
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

	@Override
	public <O> void updateChildren(Project item, O child) {
		// TODO Auto-generated method stub
		
	}
}

