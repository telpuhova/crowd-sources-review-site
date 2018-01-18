package dao;

import models.Special;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oSpecialDaoTest {

    private Connection con;
    private Sql2oSpecialDao specialDao;

    @Before
    public void setUp() throws Exception {

        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        specialDao = new Sql2oSpecialDao(sql2o);

        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }


    @Test
    public void add() throws Exception {
        Special special = new Special("Before turning a gun on himself");
        specialDao.add(special);
        assertEquals(1, specialDao.getAll().size());
    }

    @Test
    public void add_assignsCorrectId() throws Exception {
        Special special = new Special("Before turning a gun on himself");
        Special special2 = new Special("If I");
        Special special3 = new Special("What I Should Have Said Was Nothing");
        specialDao.add(special);
        specialDao.add(special2);
        specialDao.add(special3);
        assertEquals(2, special2.getId());
    }

    @Test
    public void getAll() throws Exception {
        Special special = new Special("Before turning a gun on himself");
        Special special2 = new Special("If I");
        specialDao.add(special);
        specialDao.add(special2);
        assertEquals(2, specialDao.getAll().size());
    }

    @Test
    public void findById() throws Exception {
        Special special = new Special("Before turning a gun on himself");
        Special special2 = new Special("If I");
        Special special3 = new Special("What I Should Have Said Was Nothing");
        specialDao.add(special);
        specialDao.add(special2);
        specialDao.add(special3);

        assertEquals(special2, specialDao.findById(special2.getId()));
    }

}