package com.tactfactory.nikoniko.manager.database.manager;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.UserDBManager;
import com.tactfactory.nikoniko.models.User;
import com.tactfactory.nikoniko.utils.DumpFields;

public class UserDBmanagerTest {
	
		User user = new User("titi","grominet","toto","tutu","CGI42");
		
		private UserDBManager usermanager = new UserDBManager();
		
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		user.setSex('M');
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetValues() {
		
		String query = "";
		query += null+",'";
		query += user.getLogin()+"','";
		query += user.getPassword().toString()+"','";
		query += user.getSex()+"','";
		query += user.getLastname().toString()+"','";
		query += user.getFirstname().toString()+"','";
		query += user.getRegistration_cgi().toString()+"'";
		
		String unexpected = null+",,,,,,";
	
		assertEquals(query, usermanager.getValues(user));
		assertNotEquals(unexpected, usermanager.getValues(user));
	}
	
	@Test
	public void testsetObject(){
		
		String query = "";
		query = "SELECT * FROM user WHERE id = 1";
		ResultSet result = MySQLAccess.getInstance().resultQuery(query);
		//usermanager.setObjectFromResultSet(result, user);
		
		//assertEquals(user, );
	}
	
	@Test
	public void testPurgeTable(){
		
		usermanager.purgeTable(user.table);
		
		ResultSet result = MySQLAccess.getInstance().resultQuery("SELECT * FROM "+ user.table);
		
	}
	
	@Test
	public void testgetById(){
		
		assertEquals(user, usermanager.getById(user));
		
	}
	
	@Test
	public void testGetByIdFull(){
		assertEquals(user, usermanager.getByIdFull(user));
	}
	
	@Test
	public void testgetAll(){
		
	}

	@Test
	public void testGetAssociateItem(){
		
	}

	@Test
	public void testInsert(){
		
	}
	
	@Test
	public void testUpdate(){
		
	}
	
	@Test
	public void testDelete(){
		
	}
	
	@Test
	public void testMapRelation(){
		
	}
	
	@Test
	public void testupdateWithChildren(){
		
	}
	
	@Test
	public void testupdateChildren(){
		
	}
	
	@Test
	public void testDeleteWithChildren(){
		
	}
	
	@Test
	public void testDeleteChildren(){
		
	}
}
