package com.tactfactory.nikoniko.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tactfactory.nikoniko.config.Configuration;

public class TeamTest {

	private Team team = null;
	private ArrayList<Project> project = new ArrayList<Project>();
	private ArrayList<User> user = new ArrayList<User>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    Configuration.getInstance("test");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		team = new Team();
		// TODO Regen db.
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * setters and getters test for name
	 */
	@Test
	public void testname() {
		this.team.setName("erwan");

		assertEquals("erwan", this.team.getName());
		assertNotEquals("romain", this.team.getName());
	}

	/*
	 * setters and getters test for id
	 */
	@Test
	public void  testId(){
		this.team.setId(55);

		assertEquals(55, this.team.getId());
		assertNotEquals(45, this.team.getId());
	}

	/*
	 * setters and getters test for serial
	 */
	@Test
	public void testSerial(){
		this.team.setSerial("CGI42");

		assertEquals("CGI42", this.team.getSerial());
		assertNotEquals("CGI666", this.team.getSerial());
	}

	/*
	 * setters and getters test for project's list
	 */
	@Test
	public void testProject(){
		this.team = new Team();
		for (int i = 0; i < 10; i++) {
			this.project.add(new Project());
			this.project.get(i).setId(i);
		}
		this.team.setProjects(this.project);

		ArrayList<Project> projecttest = new ArrayList<Project>();

		for (int i = 0; i < 10; i++) {
			projecttest.add(new Project());
			projecttest.get(i).setId(i);
		}

		assertArrayEquals(this.project.toArray() ,this.team.getProjects().toArray());

		assertNotEquals(projecttest.toArray() ,this.team.getProjects().toArray());
	}

	/*
	 * setters and getters test for user's list
	 */
	@Test
	public void testUser(){
		for (int i = 0; i < 10; i++) {
			this.user.add(new User());
			this.user.get(i).setId(i+10);
		}
		this.team.setUsers(this.user);

		ArrayList<User> usertest = new ArrayList<User>();

		for (int i = 0; i < 10; i++) {
			usertest.add(new User());
			usertest.get(i).setId(i+10);
		}

		assertArrayEquals(this.user.toArray() ,this.team.getUsers().toArray());

		assertNotEquals(usertest.toArray() ,this.team.getUsers().toArray());
	}

	/*
	 * table and field test instantiation
	 */
	@Test
	public void tableandfield() {
		String[] copyfield = { "id", "name", "serial" };

		assertEquals("team", this.team.table);
		assertArrayEquals(copyfield, this.team.fields);
	}

	/*
	 * test constructor void
	 */
	@Test
	public void testConstVide(){
		String[] copyfield = { "id", "name", "serial" };

		assertEquals("team", this.team.table);
		assertArrayEquals(copyfield, this.team.fields);
		assertEquals(0, team.getProjects().size());
		assertEquals(0, team.getUsers().size());
		assertEquals(new ArrayList<Project>(),team.getProjects());
		assertEquals(new ArrayList<User>(),team.getUsers());
	}

	/*
	 * test second constructor
	 */
	@Test
	public void testConstTeam() {

		Team teamgoldorak = new Team("goldorak", "CGI12");
		String[] copyfield = { "id", "name", "serial" };

		assertEquals("team", this.team.table);
		assertArrayEquals(copyfield, this.team.fields);
		assertEquals("goldorak", teamgoldorak.getName());
		assertEquals("CGI12", teamgoldorak.getSerial());
		assertEquals(0, teamgoldorak.getProjects().size());
		assertEquals(0, teamgoldorak.getUsers().size());
		assertEquals(0, teamgoldorak.getId());
		assertEquals(new ArrayList<Project>(),teamgoldorak.getProjects());
		assertEquals(new ArrayList<User>(),teamgoldorak.getUsers());
	}


}
