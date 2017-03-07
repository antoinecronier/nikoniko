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

	@Override
	public <O> void updateChildren(User item) {
		// TODO Auto-generated method stub

	}

	@Override
	public <O> void mapRelation(User item, O relation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWithChildren(User item) {
		// TODO Auto-generated method stub
		
	}
}
