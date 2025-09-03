package dao.impl;

import config.ConnectionMySQL;
import dao.CategoryDAO;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    public CategoryDAOImpl() {
        conn = ConnectionMySQL.getConnection();
    }
    @Override
    public void insert(Category category) {
        String sql = "INSERT INTO categories(catename,icon) VALUES (?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, category.getCatename());
            ps.setString(2, category.getIcon());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void edit(Category category) {
        String sql = "UPDATE categories SET catename = ?, icon=? WHERE cateid = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, category.getCatename());
            ps.setString(2, category.getIcon());
            ps.setInt(3, category.getCateid());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM categories WHERE cateid = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public Category get(int id) {
        String sql = "SELECT * FROM categories WHERE cateid = ? ";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCateid(rs.getInt("cateid"));
                category.setCatename(rs.getString("catename"));
                category.setIcon(rs.getString("icon"));
                return category;
            }} catch (Exception e) {
            e.printStackTrace();}
        return null;
    }
    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<Category>();
        String sql = "SELECT * FROM categories";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCateid(rs.getInt("cateid"));
                category.setCatename(rs.getString("catename"));
                category.setIcon(rs.getString("icon"));
                categories.add(category);
            }} catch (Exception e) {
            e.printStackTrace();}
        return categories;
    }
    @Override
    public Category get(String name){
        String sql = "SELECT * FROM categories WHERE catename = ? ";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCateid(rs.getInt("cateid"));
                category.setCatename(rs.getString("catename"));
                category.setIcon(rs.getString("icon"));
                return category;
            }} catch (Exception e) {
            e.printStackTrace();}
        return null;
    }
}
