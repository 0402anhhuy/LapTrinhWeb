package service.impl;

import dao.CategoryDAO;
import dao.impl.CategoryDAOImpl;
import model.Category;
import service.CategoryService;

import java.io.File;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDAO categoryDao =  new CategoryDAOImpl();
    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }
    @Override
    public void edit(Category newCategory) {
        Category oldCate = categoryDao.get(newCategory.getCateid());
        oldCate.setCatename(newCategory.getCatename());
        if (newCategory.getIcon() != null) {
// XOA ANH CU DI
            String fileName = oldCate.getIcon();
            final String dir = "D:\\DaiHoc\\HKV_25-26\\Lập Trình Web\\LTW_BT2\\src\\main\\webapp\\";
            File file = new File(dir + fileName);
            if (file.exists()) {
                file.delete();
            }
            oldCate.setIcon(newCategory.getIcon());
        }
        categoryDao.edit(oldCate);
    }
    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }
    @Override
    public Category get(int id) {
        return categoryDao.get(id);
    }@Override
    public Category get(String name) {
        return categoryDao.get(name);
    }
    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

}
