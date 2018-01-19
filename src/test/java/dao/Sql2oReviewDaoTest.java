package dao;

import models.Review;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;


public class Sql2oReviewDaoTest {

    private Connection con;
    private Sql2oReviewDao reviewDao;

    @Before
    public void setUp() throws Exception {

        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        reviewDao = new Sql2oReviewDao(sql2o);

        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }


    @Test
    public void add() throws Exception {
        Review review = new Review("meh");
        reviewDao.add(review);
        assertEquals(1, reviewDao.getAll().size());
    }

    @Test
    public void findById() throws Exception {
        Review review = new Review("meh");
        Review review2 = new Review("okay");
        Review review3 = new Review("lol");
        reviewDao.add(review);
        reviewDao.add(review2);
        reviewDao.add(review3);

        assertEquals(review2, reviewDao.findById(review2.getId()));
    }

    @Test
    public void findBySpecialId() throws Exception {
        Review review = new Review("meh");
        Review review2 = new Review("okay");
        Review review3 = new Review("lol");
        reviewDao.add(review);
        reviewDao.add(review2);
        reviewDao.add(review3);

        assertEquals(review2, reviewDao.findById(review2.getId()));
    }

    @Test
    public void getAll() throws Exception {
        Review review = new Review("meh");
        Review review2 = new Review("okay");
        reviewDao.add(review);
        reviewDao.add(review2);
        assertEquals(2, reviewDao.getAll().size());
    }

}