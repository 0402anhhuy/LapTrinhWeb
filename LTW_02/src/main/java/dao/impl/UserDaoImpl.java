package dao.impl;

import config.DBConnection;
import dao.UserDao;
import model.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    @Override
    public User get(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setFullname(rs.getString("fullname"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setPassword(rs.getString("password"));
                    user.setRoleid(rs.getInt("roleid"));
                    user.setCreatedDate(rs.getTimestamp("createddate"));
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO users (username, fullname, email, phone, password, roleid, createddate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFullname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getRoleid());
            ps.setTimestamp(7, new Timestamp(user.getCreatedDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        return checkExist("SELECT 1 FROM users WHERE email = ?", email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return checkExist("SELECT 1 FROM users WHERE username = ?", username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return checkExist("SELECT 1 FROM users WHERE phone = ?", phone);
    }

    private boolean checkExist(String sql, String value) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, value);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
