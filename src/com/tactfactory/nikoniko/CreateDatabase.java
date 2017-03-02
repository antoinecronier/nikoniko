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

public class CreateDatabase {
		public static void main(String[] args)  {
			    UserDBManager userDBManager = new UserDBManager();	
			    userDBManager.setInsertVerbose(true);
			    PoolUser poolUser = new PoolUser(10);
				//System.out.println(poolUser.getList());
				//userDBManager.getValues(poolUser.getList().get(0));
				User user0 = poolUser.getList().get(0);
				Map<String, Object> map;
				map = DumpFields.fielder(user0);
				System.out.println("getClass :"+user0.getClass().getName());
				
				String string = "";
				for (String key : map.keySet()) {
					string += key + ", ";
				}
				System.out.println(string);
				
				userDBManager.getValues(user0);
		}
}
