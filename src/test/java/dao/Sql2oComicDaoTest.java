package dao;

import models.Comic;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;


public class Sql2oComicDaoTest {
    private Connection con;
    private Sql2oComicDao comicDao;

    @Before
    public void setUp() throws Exception {

        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        comicDao = new Sql2oComicDao(sql2o);

        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }


    @Test
    public void add() throws Exception {
        Comic comic = new Comic("Doug Stanhope");
        comicDao.add(comic);
        assertEquals(1, comicDao.getAll().size());
    }

    @Test
    public void add_assignsCorrectId() throws Exception {
        Comic comic = new Comic("Doug Stanhope");
        Comic comic2 = new Comic("Demetri Martin");
        Comic comic3 = new Comic("Mike Birbiglia");
        comicDao.add(comic);
        comicDao.add(comic2);
        comicDao.add(comic3);
        assertEquals(2, comic2.getId());
    }

    @Test
    public void getAll() throws Exception {
        Comic comic = new Comic("Doug Stanhope");
        Comic comic2 = new Comic("Demetri Martin");
        comicDao.add(comic);
        comicDao.add(comic2);
        assertEquals(2, comicDao.getAll().size());
    }

    @Test
    public void findById() throws Exception {
        Comic comic = new Comic("Doug Stanhope");
        Comic comic2 = new Comic("Demetri Martin");
        Comic comic3 = new Comic("Mike Birbiglia");
        comicDao.add(comic);
        comicDao.add(comic2);
        comicDao.add(comic3);
        assertEquals(comic2, comicDao.findById(comic2.getId()));
    }

}