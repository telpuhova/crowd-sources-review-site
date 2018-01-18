package dao;

import java.util.List;
import models.Special;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class Sql2oSpecialDao implements SpecialDao{

    private final Sql2o sql2o;

    public Sql2oSpecialDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Special special){
        String sql = "INSERT INTO specials (name, year, comicId, country, language, description) VALUES (:name, :year, :comicId, :country, :language, :description)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(special)
                    .executeUpdate()
                    .getKey();
            special.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<Special> getAll(){
        String sql = "SELECT * FROM specials";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Special.class);
        }
    }

    @Override
    public Special findById(int id){
        String sql = "SELECT * FROM specials WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Special.class);
        }
    }
}
