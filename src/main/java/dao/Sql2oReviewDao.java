package dao;

import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class Sql2oReviewDao implements ReviewDao{

    private final Sql2o sql2o;

    public Sql2oReviewDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Review review){
        String sql = "INSERT INTO reviews (title, rating, content, userName, specialId) VALUES (:title, :rating, :content, :userName, :specialId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(review)
                    .executeUpdate()
                    .getKey();
            review.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Review findById(int id){
        String sql = "SELECT * FROM reviews WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Review.class);
        }

    }

    @Override
    public List<Review> findBySpecialId(int specialId){
        String sql = "SELECT * FROM reviews WHERE specialId = :specialId";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("specialId", specialId)
                    .executeAndFetch(Review.class);
        }
    }

    @Override
    public List<Review> getAll(){
        String sql = "SELECT * FROM reviews";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Review.class);
        }
    }

}