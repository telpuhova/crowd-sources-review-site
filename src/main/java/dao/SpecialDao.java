package dao;

import models.Special;
import java.util.List;

public interface SpecialDao {

    void add(Special special);

    List<Special> getAll();

    Special findById(int id);

    //update

    //delete

}
