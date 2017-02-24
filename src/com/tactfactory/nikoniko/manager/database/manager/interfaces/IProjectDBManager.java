package com.tactfactory.nikoniko.manager.database.manager.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.manager.database.manager.TeamDBManager;
import com.tactfactory.nikoniko.manager.database.manager.interfaces.base.IDBManagerBase;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.Team;
import com.tactfactory.nikoniko.utils.DateConverter;

public interface IProjectDBManager<O,L> extends IDBManagerBase {
	
	public String getProjectValues(O o);
	public Project setObjectFromResultSet(O o); 
	public Project getProjectByIdFull(L l);
	public Project getProjectById(L l) ;
	public ArrayList<O> getAllProject();
	public void getAssociatedNikoNiko(O o) ;
	public void getAssociatedTeam(O o);
	public void insert(O o);
	public void insertWithChild(O o);
	public void insertTeams(O o);
	public void insertRelationTeam(O o, O e);

}