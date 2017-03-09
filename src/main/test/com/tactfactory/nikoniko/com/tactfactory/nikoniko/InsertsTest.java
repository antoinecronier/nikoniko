package com.tactfactory.nikoniko;

import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tactfactory.nikoniko.config.Configuration;
import com.tactfactory.nikoniko.manager.database.MySQLAccess;
import com.tactfactory.nikoniko.manager.database.manager.NikoNikoDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.User;

public class InsertsTest {
	NikoNiko niko;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Configuration.getInstance("test");
		System.out.println("database=" + Configuration.getInstance("test").getDBName());
		MySQLAccess.getInstance().createDatabase();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		niko = new NikoNiko(new User(), new Project(), 2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		MySQLAccess.getInstance().createDatabase();
		System.exit(1);
		NikoNikoDBManager manager = new NikoNikoDBManager();
		manager.insert(niko);
		assertNotEquals(0, niko.getId());
	}

}
