package com.tactfactory.nikoniko.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {

	private User model = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.model = new User();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLastname() {
		this.model.setLastname("Dupont");
		assertEquals("Dupont", this.model.getLastname());

		this.model.setLastname("");
		assertEquals("", this.model.getLastname());

		this.model.setLastname(null);
		assertNull(this.model.getLastname());
	}

	@Test
	public void testFirstname() {
		this.model.setFirstname("Marcel");
		assertEquals("Marcel", this.model.getFirstname());

		this.model.setFirstname("");
		assertEquals("", this.model.getFirstname());

		this.model.setFirstname(null);
		assertNull(this.model.getFirstname());
	}

	@Test
	public void testRegistration_cgi() {
		this.model.setRegistration_cgi("Marcel");
		assertEquals("Marcel", this.model.getRegistration_cgi());

		this.model.setRegistration_cgi("");
		assertEquals("", this.model.getRegistration_cgi());

		this.model.setRegistration_cgi(null);
		assertNull(this.model.getRegistration_cgi());

	}

	@Test
	public void testNikoNikos() {

		for (NikoNiko niko2 : this.model.getNikoNikos()) {
			assertNull(niko2);
		}

		NikoNiko niko1 = new NikoNiko();
		model.getNikoNikos().add(niko1);

		for (NikoNiko niko2 : this.model.getNikoNikos()) {
			assertNotNull(niko2);
		}

		ArrayList<NikoNiko> nikoList = new ArrayList<>();
		nikoList.add(niko1);
		model.getNikoNikos().clear();
		model.setNikoNikos(nikoList);

		for (NikoNiko niko2 : this.model.getNikoNikos()) {
			assertNotNull(niko2);
		}

	}

	@Test
	public void testTeams() {

		assertNotNull(model.getTeams());

		for (Team team : this.model.getTeams()) {
			assertNotNull(team);
		}

		Team team = new Team();
		model.getTeams().add(team);
		assertNotNull(model.getTeams());

		ArrayList<Team> teams = new ArrayList<>();
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();
		teams.add(team1);
		teams.add(team2);
		teams.add(team3);
		model.setTeams(teams);
		assertEquals(3, model.getTeams().size());

	}

	@Test
	public void testSex() {
	    model.setSex(User.SEX_UNDEFINNED);
	    assertEquals(User.SEX_UNDEFINNED, model.getSex());

	    model.setSex(User.SEX_MALE);
        assertEquals(User.SEX_MALE, model.getSex());

        model.setSex(User.SEX_FEMALE);
        assertEquals(User.SEX_FEMALE, model.getSex());

        try {
            model.setSex('v');
            model.setSex('m');
            fail("Invalid parameter wes accepted.");
        } catch (InvalidParameterException e) {
        }
	}
}
