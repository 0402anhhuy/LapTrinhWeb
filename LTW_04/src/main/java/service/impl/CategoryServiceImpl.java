package service.impl;

import dao.CategoryDAO;
import dao.impl.CategoryDAOImpl;
import model.Category;
import service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public void create(Category category) {
        categoryDAO.create(category);
    }

    @Override
    public void update(Category category) {
        categoryDAO.update(category);
    }

    @Override
    public void delete(int id) {
        categoryDAO.delete(id);
    }

    @Override
    public Category findById(int id) {
        return categoryDAO.findById(id);
    }
}
