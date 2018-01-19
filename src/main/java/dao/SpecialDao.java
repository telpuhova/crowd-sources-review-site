package dao;

import models.Special;
import java.util.List;

public interface SpecialDao {

    void add(Special special);

    List<Special> getAll();

    Special findById(int id);

    //update
    void update(int id, String name, int year, int comicId, String country, String language, String description);

    //delete
    void deleteById(int id);

}
