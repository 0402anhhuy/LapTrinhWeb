package dao;

import model.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    void create(Category category);
    void update(Category category);
    void delete(int id);
    Category findById(int id);
}
