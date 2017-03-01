package com.tactfactory.nikoniko.manager.database.manager.interfaces;

import java.lang.reflect.Method;

import com.tactfactory.nikoniko.manager.database.manager.interfaces.base.IDBManagerBase;
import com.tactfactory.nikoniko.models.NikoNiko;

public interface INikoNikoDBManager extends IDBManagerBase<NikoNiko> {
	
	public NikoNiko getById(long id);
}
