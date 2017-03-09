package com.tactfactory.nikoniko.manager.database.manager;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tactfactory.nikoniko.manager.database.manager.base.BaseDBManager;
import com.tactfactory.nikoniko.models.NikoNiko;
import com.tactfactory.nikoniko.models.Project;
import com.tactfactory.nikoniko.models.User;
import com.tactfactory.nikoniko.utils.DateConverter;

public class NikoNikoDBManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getValuesTest() {
		
		int id = 45;
		Date log_Date = new Date(1488967948000l);
		Date change_Date = new Date(1488967947000l);
		int satisfaction = 2;
		String nikoniko_comment ="mon commentaire";
		Boolean isanonymous = true;
		
		User user = new User();
		user.setId(55);
		long userid= user.getId();

		Project project = new Project();
		project.setId(75);
		long projectid= project.getId();
		
		NikoNiko niko = new NikoNiko();
		
		niko.setId(id);
		niko.setLog_date(log_Date);
		niko.setChange_date(change_Date);	
		niko.setSatisfaction(satisfaction);
		niko.setComment(nikoniko_comment);
		niko.setIsAnonymous(isanonymous);
		niko.setUser(user);
		niko.setProject(project);
		
		NikoNikoDBManager nikotest = new NikoNikoDBManager();
		
		//nikotest.insert(niko);
		
		String monniko = id+",'"+ DateConverter.getMySqlDatetime(log_Date) + "','" 
						+ DateConverter.getMySqlDatetime(change_Date) + "','" + satisfaction + "','" 
						+ nikoniko_comment + "'," 
						+ isanonymous + ",'" + userid + "','" + projectid +"'";    
		String query = nikotest.getValues(niko);
			
		assertEquals(monniko, query);
		
	}

	@Test
	public void setObjectFromResultSetTest() {
		
//		NikoNikoDBManager nikotest = new NikoNikoDBManager();
//		NikoNiko niko = new NikoNiko();

		
	}
	
	@Test
	public void PurgeTabletest() {
//		
//		NikoNikoDBManager nikotest = new NikoNikoDBManager();
//		
//		String nikotable = null;		
//		nikotest.purgeTable(nikotable);
//
//		assertNull(nikotable);
//		
	}
	
	@Test
	public void getByIdtest() {
//		
//		NikoNikoDBManager nikotest = new NikoNikoDBManager();
//		NikoNiko niko = new NikoNiko();
//		nikotest.getById(niko);
//		
//		int id = 0;
//		
//		String query ="";
//				
//		query = "Select * from NikoNiko WHERE id = ";
//		query += id;
//		
//		assertEquals(query, niko);
	}

	@Test
	public void getByIdFulltest() {
//		
//		NikoNikoDBManager nikotest = new NikoNikoDBManager();
//		NikoNiko niko = new NikoNiko();
//		nikotest.getById(niko);
//		
//		int id = 0;
//		
//		String query ="";
//				
//		query = "Select * from NikoNiko WHERE id = ";
//		query += id;
//		
//		assertEquals(query, niko);

	}
	
	@Test
	public void getAlltest() {
//		
//		NikoNikoDBManager nikotest = new NikoNikoDBManager();
//		
//		ArrayList<NikoNiko> nikolist = new ArrayList<NikoNiko>();
//
//		assertArrayEquals(nikolist.toArray(), nikotest.getAll().toArray());
//	
	}
	
	@Test
	public void getAssociateObjecttest() {
//		
//		NikoNikoDBManager nikotest = new NikoNikoDBManager();
//		
//		ArrayList<NikoNiko> nikolist = new ArrayList<NikoNiko>();
//
//		assertArrayEquals(nikolist.toArray(), nikotest.getAll().toArray());

	}
	
	@Test
	public void inserttest() {
		
//
//		NikoNikoDBManager nikotest = new NikoNikoDBManager();
//		NikoNiko niko = new NikoNiko();
//		
//		nikotest.insert(niko);
//		
//		assertNull(niko);
//		
	}
	
	@Test
	public void updatetest() {
		
	}
	
	@Test
	public void deletetest() {
		
//
//		NikoNikoDBManager nikotest = new NikoNikoDBManager();
//		NikoNiko niko = new NikoNiko();
//		
//		nikotest.delete(niko);
//		
//		assertNull(niko);
//		
	}
	
	@Test
	public void mapRelationtest() {

	}

	@Test
	public void updateWithChildrentest() {

	}
	
	@Test
	public void updateChildrentest() {

	}

}
