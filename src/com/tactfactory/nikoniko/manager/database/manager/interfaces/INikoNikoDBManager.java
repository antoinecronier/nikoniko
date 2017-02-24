package com.tactfactory.nikoniko.manager.database.manager.interfaces;

import com.tactfactory.nikoniko.manager.database.manager.interfaces.base.IDBManagerBase;

public interface INikoNikoDBManager<T> extends IDBManagerBase {
	
	public T getById(long id);

}
