/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kasper
 */
public class DataAccessObjectTest {

    DBConnector conn;
    DataAccessObject dao;

    public DataAccessObjectTest() throws Exception {
        conn = new DBConnector();
        dao = new DataAccessObject(conn);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getInventory method, of class DataAccessObject.
     */
    @Test
    public void testGetInventory() {
        System.out.println("getInventory");
        DataAccessObject instance = dao;
        int expResult = 3;
        int result = instance.getInventory().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of reserveItem method, of class DataAccessObject.
     */
    @Test
    public void testReserveItem() {
        System.out.println("reserveItem");
        String ino = "1";
        String uno = "2";
        DataAccessObject instance = dao;
        boolean expResult = true;
        boolean result = instance.reserveItem(ino, uno);
        assertEquals(expResult, result);
        instance.deleteReservation(ino, uno);
    }

    /**
     * Test of getReserved method, of class DataAccessObject.
     */
    @Test
    public void testGetReserved() {
        System.out.println("getReserved");
        String uno = "1";
        dao.reserveItem("1", uno);
        dao.reserveItem("1", uno);
        dao.reserveItem("1", uno);
        DataAccessObject instance = dao;
        int expResult = 3;
        int result = instance.getReserved(uno).size();
        assertEquals(expResult, result);
        dao.deleteReservation("1", uno);
        dao.deleteReservation("1", uno);
        dao.deleteReservation("1", uno);
    }

    /**
     * Test of getUnoUserPass method, of class DataAccessObject.
     */
    @Test
    public void testGetUnoUserPass() {
        System.out.println("getUnoUserPass");
        String uname = "kasper";
        String upass = "kasper";
        DataAccessObject instance = dao;
        String expResult = "2";
        String result = instance.getUnoUserPass(uname, upass);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteReservation method, of class DataAccessObject.
     */
    @Test
    public void testDeleteReservation() {
        System.out.println("deleteReservation");
        String ino = "3";
        String uno = "1";
        DataAccessObject instance = dao;
        instance.reserveItem(ino, uno);
        boolean expResult = true;
        boolean result = instance.deleteReservation(ino, uno);
        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String u = "test";
        String pw = "test";
        String ad = "test 12";
        String ph = "test phone";
        String ma = "mail@test";
        String ssn = "10526353";
        String exp = "3";
        DataAccessObject instance = dao;
        String result = instance.registerUser(u, pw, ad, ph, ma, ssn);
        assertEquals(exp, result);
    }

}
