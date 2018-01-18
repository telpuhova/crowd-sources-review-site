package dao;

import models.Comic;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oComicDao implements ComicDao{

    private final Sql2o sql2o;

    public Sql2oComicDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Comic comic){
        String sql = "INSERT INTO comics (name, dateOfBirth) VALUES (:name, :dateOfBirth)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(comic)
                    .executeUpdate()
                    .getKey();
            comic.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Comic> getAll(){
        String sql = "SELECT * FROM comics";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Comic.class);
        }
    }

    @Override
    public Comic findById(int id){
        String sql = "SELECT * FROM comics WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Comic.class);
        }
    }
}
