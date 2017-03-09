package com.tactfactory.nikoniko.manager.database.manager;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.Team;
import com.tactfactory.nikoniko.models.User;

public class TeamDBManagerTest {

	private TeamDBManager manager = null;
	private Team item = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		manager = new TeamDBManager();
		item = new Team();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetValues() {

		// test sur une team pleine.
		long id = 2;
		String name = "nomTeam";
		String serial = "ABC34R";
		item.setId(id);
		item.setName(name);
		item.setSerial(serial);

		String expected = id + ",'" + name + "','" + serial + "'";
		String unexpected = "0,null,null";
		String query = manager.getValues(item);

		assertEquals(expected, query);
		assertNotEquals(unexpected, query);

		// test sur une team vide.
		item.setId(0);
		item.setName(null);
		item.setSerial(null);

		expected = "null,null,null";
		unexpected = "0,null,null";
		query = manager.getValues(item);

		assertEquals(expected, query);
		assertNotEquals(unexpected, query);

		// test sur une team à moitié pleine.
		name = "nomTeam";

		expected = "null,'" + name + "',null";
		unexpected = "null,null,null";
		query = manager.getValues(item);

		assertEquals(expected, query);
		assertNotEquals(unexpected, query);
	}

	@Test
	public void testSetObjectFromResultSet() {

		ResultSet query = MySQLAccess.getInstance().resultQuery("SELECT * FROM " + item.table + " WHERE id = 1");
		try {
			if (query.next()) {
				item = manager.setObjectFromResultSet(query, item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		long id = 1;
		String name = "CGITEAM 0";
		String serial = "3000";

		assertEquals(id, item.getId());
		assertEquals(name, item.getName());
		assertEquals(serial, item.getSerial());
	}

	@Test
	public void testPurgeTable() {

		manager.purgeTable(item.table);

		ResultSet query = MySQLAccess.getInstance().resultQuery("SELECT * FROM " + item.table);

		try {
			assertFalse(query.first());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetById() {
		item.setId(1);
		item = manager.getById(item);

		long id = 1;
		String name = "CGITEAM 0";
		String serial = "3000";

		assertEquals(id, item.getId());
		assertEquals(name, item.getName());
		assertEquals(serial, item.getSerial());
	}

	@Test
	public void testInsert() {
		manager.insert(item);
		assertNotEquals(0, item.getId());
	}

	@Test
	public void testUpdate() {
		manager.insert(item);

		String name = "nomTeam";
		String serial = "ABC34R";
		item.setName(name);
		item.setSerial(serial);

		manager.update(item);

		Team test = new Team();
		test.setId(item.getId());
		test = manager.getById(test);

		assertEquals(name, test.getName());
		assertEquals(serial, test.getSerial());

	}

	@Test
	public void testDelete() {

		String name = "nomTeam";
		String serial = "ABC34R";
		item.setName(name);
		item.setSerial(serial);

		manager.insert(item);
		manager.delete(item);

		ResultSet query = MySQLAccess.getInstance()
				.resultQuery("SELECT * FROM " + item.table + " WHERE id = " + item.getId());

		try {
			assertFalse(query.first());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMapRelation() {

		String name = "nomTeam";
		String serial = "ABC34R";
		item.setName(name);
		item.setSerial(serial);

		manager.insert(item);

		// Test for User.
		User user = new User();
		UserDBManager uManager = new UserDBManager();

		uManager.insert(user);

		manager.mapRelation(item, user);
		ResultSet query = MySQLAccess.getInstance()
				.resultQuery("SELECT * FROM user_team WHERE User_id = " + user.getId() + "AND Team_id=" + item.getId());

		try {
			assertTrue(query.first());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Test for Project.
		Project project = new Project();
		ProjectDBManager pManager = new ProjectDBManager();

		pManager.insert(project);

		manager.mapRelation(item, user);
		query = MySQLAccess.getInstance().resultQuery(
				"SELECT * FROM team_project WHERE Project_id = " + project.getId() + "AND Team_id=" + item.getId());

		try {
			assertTrue(query.first());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testUpdateWithChildren() {

	}

	@Test
	public void testUpdateChildren() {

	}
}
