/**
 * 
 */
package com.tactfactory.nikoniko.models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProjectTest {
	
	private Project model = null;

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
		model = new Project();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testId() throws Throwable {
		
		long id = 10l;

		model.setId(id);

		assertEquals(id, model.getId());
		assertNotEquals(id + 1, model.getId());

		// Second try.

		id = 10000000000l;

		model.setId(id);

		assertEquals(id, model.getId());
		assertNotEquals(id + 1, model.getId());
		
		//Third try.
		
		id = -6l;

		model.setId(id);

		assertEquals(0, model.getId());
		assertNotEquals(id, model.getId());
	}
	
	@Test
	public void testName() {
		
		String name = "Antoine";
		model.setName(name);
		
		assertEquals(name, model.getName());
		assertNotEquals("Pas_"+name, model.getName());
		
		//Second test.
		
		name = "Jonathan";
		model.setName(name);
		
		assertEquals(name, model.getName());
		assertNotEquals("Pas_"+name, model.getName());
		
	}

	@Test
	public void testStartDate() {

		Date date = new Date(1488967948000l); // 08 mars 2017 11:12:28.
		model.setStart_date(date);

		assertEquals(date, model.getStart_date());

		GregorianCalendar test = new GregorianCalendar();
		test.setTime(model.getStart_date());

		assertEquals(8, test.get(Calendar.DAY_OF_MONTH));
		assertEquals(Calendar.MARCH, test.get(Calendar.MONTH));
		assertEquals(2017, test.get(Calendar.YEAR));

		assertEquals(11, test.get(Calendar.HOUR_OF_DAY));
		assertEquals(12, test.get(Calendar.MINUTE));
		assertEquals(28, test.get(Calendar.SECOND));
	}
	
	@Test
	public void testEndDate() {

		Date date = new Date(1488967948000l); // 08 mars 2017 11:12:28.
		model.setEnd_date(date);

		assertEquals(date, model.getEnd_date());

		GregorianCalendar test = new GregorianCalendar();
		test.setTime(model.getEnd_date());

		assertEquals(8, test.get(Calendar.DAY_OF_MONTH));
		assertEquals(Calendar.MARCH, test.get(Calendar.MONTH));
		assertEquals(2017, test.get(Calendar.YEAR));

		assertEquals(11, test.get(Calendar.HOUR_OF_DAY));
		assertEquals(12, test.get(Calendar.MINUTE));
		assertEquals(28, test.get(Calendar.SECOND));
	}
	
	@Test
	public void testNikoNikos() {
		
		ArrayList<NikoNiko> nikos = new ArrayList<NikoNiko>();
		for (int i=0 ; i<10 ; i++){
			NikoNiko niko = new NikoNiko();
			niko.setId(i);
			nikos.add(niko);
		}
		
		model.setNikoNikos(nikos);
		
		assertArrayEquals(nikos.toArray(), model.getNikoNikos().toArray());
		
		for(int i=0 ; i<10 ; i++){
			assertEquals(nikos.get(i), model.getNikoNikos().get(i));
		}
		
	}
	
	@Test
	public void testTeams() {
		
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int i=0 ; i<10 ; i++){
			Team team = new Team();
			team.setId(i);
			teams.add(team);
		}

		model.setTeams(teams);

		assertArrayEquals(teams.toArray(), model.getTeams().toArray());
		
		for(int i=0 ; i<10 ; i++){
			assertEquals(teams.get(i), model.getTeams().get(i));
		}
		
	}
	
	@Test
	public void testNameStartProject(){
		
		String name = "Lucie";
		Date startDate = new Date(1488967948000l); // 08 mars 2017 11:12:28.
		
		Project test = new Project(name, startDate);

		assertEquals(startDate, test.getStart_date());
		assertEquals(name, test.getName());
		assertNotEquals("Pas_"+name, test.getName());
	}
	
	@Test
	public void testNameStartEndProject(){
		
		String name = "Félix";
		Date startDate = new Date(1488967948000l); // 08 mars 2017 11:12:28.
		Date endDate = new Date(1489579200000l); // 15 mars 2017 12:00:00.
		
		Project test = new Project(name, startDate,endDate);

		assertEquals(startDate, test.getStart_date());
		assertNotEquals(endDate, test.getStart_date());
		assertEquals(endDate, test.getEnd_date());
		assertNotEquals(startDate, test.getEnd_date());
		assertEquals(name, test.getName());
		assertNotEquals("Pas_"+name, test.getName());
		
	}

}
