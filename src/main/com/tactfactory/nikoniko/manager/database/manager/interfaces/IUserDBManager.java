package com.tactfactory.nikoniko.manager.database.manager.interfaces;

import com.tactfactory.nikoniko.manager.database.manager.interfaces.base.IDBManagerBase;
import com.tactfactory.nikoniko.models.User;

public interface IUserDBManager extends IDBManagerBase<User> {

	/**
	 * Allow user to connect application.
	 * 
	 * @param login
	 * @param password
	 * @return
	 */
	public Boolean login(String login, String password);

}
