package com.tactfactory.nikoniko;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.io.IOException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.mysql.jdbc.StringUtils;
import com.tactfactory.nikoniko.manager.NikoNikoManager;
import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.manager.database.manager.ProjectDBManager;
import com.tactfactory.nikoniko.manager.database.manager.TeamDBManager;
import com.tactfactory.nikoniko.manager.database.manager.UserDBManager;
import com.tactfactory.nikoniko.models.*;
import com.tactfactory.nikoniko.utils.DatabasePurjer;
import com.tactfactory.nikoniko.utils.DumpFields;
import com.tactfactory.nikoniko.utils.PoolUser;

import com.tactfactory.nikoniko.utils.DateConverter;
import com.tactfactory.nikoniko.utils.DumpFields;

public class Application {

	public static void main(String[] args) {
		UserDBManager userDBManager = new UserDBManager();	
		User user0,user1,user2;
		//user0 = new User();
		//user0.setId(40);
		//user1 = userDBManager.getById(user0);
		

		user1=new User("rp","coucou","philippot", "régis","3333");
		user1.setId(39);
		user2 = userDBManager.getById(user1);
		//userDBManager.update(user1);
	}

}
