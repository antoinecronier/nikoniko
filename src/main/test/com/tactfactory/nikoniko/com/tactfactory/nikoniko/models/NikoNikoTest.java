package com.tactfactory.nikoniko.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 */
public class NikoNikoTest {

    private NikoNiko model = null;

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
        this.model = new NikoNiko();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() /* throws Exception */ {
    }

    @Test
    public void testId() throws Throwable {
    	
        NikoNiko model = new NikoNiko();
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
    public void testLogDate() {
    	
        NikoNiko model = new NikoNiko();

        Date date = new Date(1488967948000l); // 08 mars 2017 11:12:28.
        model.setLog_date(date);

        assertEquals(date, model.getLog_date());

        GregorianCalendar test = new GregorianCalendar();
        test.setTime(model.getLog_date());

        assertEquals(8, test.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.MARCH, test.get(Calendar.MONTH));
        assertEquals(2017, test.get(Calendar.YEAR));

        assertEquals(11, test.get(Calendar.HOUR_OF_DAY));
        assertEquals(12, test.get(Calendar.MINUTE));
        assertEquals(28, test.get(Calendar.SECOND));
    }
    
    @Test
    public void testChangeDate() {
    	
        NikoNiko model = new NikoNiko();

        Date date = new Date(1488967948000l); // 08 mars 2017 11:12:28.
        model.setChange_date(date);

        assertEquals(date, model.getChange_date());

        GregorianCalendar test = new GregorianCalendar();
        test.setTime(model.getChange_date());

        assertEquals(8, test.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.MARCH, test.get(Calendar.MONTH));
        assertEquals(2017, test.get(Calendar.YEAR));

        assertEquals(11, test.get(Calendar.HOUR_OF_DAY));
        assertEquals(12, test.get(Calendar.MINUTE));
        assertEquals(28, test.get(Calendar.SECOND));
    }

    @Test
    public void testSatisfaction() {
    	
        NikoNiko model = new NikoNiko();

        model.setSatisfaction(1);
        assertEquals(1, model.getSatisfaction());

        model.setSatisfaction(2);
        assertEquals(2, model.getSatisfaction());

        model.setSatisfaction(3);
        assertEquals(3, model.getSatisfaction());

        // Out of bounds values.

        model.setSatisfaction(0);
        assertEquals(0, model.getSatisfaction());

        model.setSatisfaction(-5);
        assertEquals(0, model.getSatisfaction());

        model.setSatisfaction(4);
        assertEquals(0, model.getSatisfaction());

        model.setSatisfaction(42);
        assertEquals(0, model.getSatisfaction());
    }
    
    @Test
	public void testComment() {
		
		String comment = "Mon commentaire très très intéressant";
		model.setComment(comment);
		
		assertEquals(comment, model.getComment());
		assertNotEquals(comment+ " ou pas...", model.getComment());
		
		//Second test.
		
		comment = "Mon commentaire très très pas intéressant";
		model.setComment(comment);
		
		assertEquals(comment, model.getComment());
		assertNotEquals(comment+ " ou peut être que oui", model.getComment());
		
	}

    @Test
    public void testIsAnonymous() {
    	
        // On utilise enfin l'attribut :D.
        assertTrue(this.model.getIsAnonymous());
        assertFalse(!this.model.getIsAnonymous());

        this.model.setIsAnonymous(false);
        assertFalse(this.model.getIsAnonymous());

        this.model.setIsAnonymous(true);
        assertTrue(this.model.getIsAnonymous());
    }
    
    @Test
    public void testUser() {
    	
    	User user = new User();
    	user.setId(1);
    	
    	model.setUser(user);
    	assertEquals(user, model.getUser());
    	
    	User user2 = new User();
    	user2.setId(40);
    	assertNotEquals(user2, model.getUser());
    	
    	model.setUser(user2);
    	assertEquals(user2, model.getUser());
    }
    
    @Test
    public void testProject() {
    	
    	Project project = new Project();
    	project.setId(1);
    	
    	model.setProject(project);
    	assertEquals(project, model.getProject());
    	
    	Project project2 = new Project();
    	project2.setId(40);
    	assertNotEquals(project2, model.getProject());
    	
    	model.setProject(project2);
    	assertEquals(project2, model.getProject());
    	
    }
    
    @Test
    public void testSatisNikoNiko() {
    	
    	User user = new User();
    	user.setId(1);
    	
    	Project project = new Project();
    	project.setId(2);
    	
    	int satis = 3;
    	
    	NikoNiko test = new NikoNiko(user, project, satis);
    	
    	assertEquals(user, test.getUser());
    	assertNotEquals(project, test.getUser());
    	assertEquals(project, test.getProject());
    	assertNotEquals(user, test.getProject());
    	assertEquals(satis, test.getSatisfaction());
    	assertNotEquals(satis-1, test.getSatisfaction());
    	
    }
    
    @Test
    public void testSatisComNikoNiko() {
    	
    	User user = new User();
    	user.setId(1);
    	
    	Project project = new Project();
    	project.setId(2);
    	
    	int satis = 3;
    	String com = "Mon nouveau commentaire";
    	
    	NikoNiko test = new NikoNiko(user, project, satis, com);
    	
    	assertEquals(user, test.getUser());
    	assertNotEquals(project, test.getUser());
    	assertEquals(project, test.getProject());
    	assertNotEquals(user, test.getProject());
    	assertEquals(satis, test.getSatisfaction());
    	assertNotEquals(satis-1, test.getSatisfaction());
    	assertEquals(com, test.getComment());
    	assertNotEquals(com+" qui est super cool!", test.getComment());
    	
    	
    }
    
    @Test
    public void testSatisIsANikoNiko() {
    	User user = new User();
    	user.setId(1);
    	
    	Project project = new Project();
    	project.setId(2);
    	
    	int satis = 3;
    	boolean isA = false;
    	
    	NikoNiko test = new NikoNiko(user, project, satis, isA);
    	
    	assertEquals(user, test.getUser());
    	assertNotEquals(project, test.getUser());
    	assertEquals(project, test.getProject());
    	assertNotEquals(user, test.getProject());
    	assertEquals(satis, test.getSatisfaction());
    	assertNotEquals(satis-1, test.getSatisfaction());
    	assertFalse(test.getIsAnonymous());
    	assertTrue(!test.getIsAnonymous());
    }
    
    @Test
    public void testSatisComIsANikoNiko() {
    
    	User user = new User();
    	user.setId(1);
    	
    	Project project = new Project();
    	project.setId(2);
    	
    	int satis = 3;
    	String com = "Mon nouveau commentaire";
    	boolean isA = false;
    	
    	NikoNiko test = new NikoNiko(user, project, satis, com, isA);
    	
    	assertEquals(user, test.getUser());
    	assertNotEquals(project, test.getUser());
    	assertEquals(project, test.getProject());
    	assertNotEquals(user, test.getProject());
    	assertEquals(satis, test.getSatisfaction());
    	assertNotEquals(satis-1, test.getSatisfaction());
    	assertEquals(com, test.getComment());
    	assertNotEquals(com+" qui est super cool!", test.getComment());
    	assertFalse(test.getIsAnonymous());
    	assertTrue(!test.getIsAnonymous());
    	
    }
}
