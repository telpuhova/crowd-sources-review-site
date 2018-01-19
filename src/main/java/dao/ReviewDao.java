package dao;


import models.Review;

import java.util.List;

public interface ReviewDao {

    void add(Review review);

    List<Review> getAll();

    Review findById(int id);

    List<Review> findBySpecialId(int specialId);

    //update
//    void update(int id, String name, int year, int comicId, String country, String language, String description);

    //delete
//    void deleteById(int id);
}
