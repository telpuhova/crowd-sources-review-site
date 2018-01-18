package dao;

import models.Comic;

import java.util.List;

public interface ComicDao {

    void add(Comic comic);

    List<Comic> getAll();

    Comic findById(int id);

    //update

    //delete

}
