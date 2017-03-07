package com.tactfactory.nikoniko.manager.database.manager;

import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Team;

public class TeamDBManager extends BaseDBManager<Team> {

	@Override
	public void purgeTable(String table) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Team> getAll() {
		TeamDBManager team = new TeamDBManager();
		return team.getAll(Team.class);
	}

	@Override
	public void update(Team item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Team item) {
		// TODO Auto-generated method stub

	}

	@Override
	public <O> void mapRelation(Team item, O relation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateWithChildren(Team item) {
		// TODO Auto-generated method stub

	}

	@Override
	public <O> void updateChildren(Team item, O sampleChild) {
		// TODO Auto-generated method stub

	}
}
