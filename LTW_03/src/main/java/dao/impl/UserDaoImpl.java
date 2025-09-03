package dao.impl;

import config.ConnectionMySQL;
import dao.UserDao;
import model.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    @Override
    public User get(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = ConnectionMySQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setUserName(rs.getString("username"));
                    user.setFullName(rs.getString("fullname"));
                    user.setPassWord(rs.getString("password"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setRoleid(rs.getInt("roleid"));
                    user.setPhone(rs.getString("phone"));
                    user.setCreatedDate(rs.getDate("createdDate"));
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching user by username: " + username, e);
        }
        return null;
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO users(email, username, fullname, password, avatar, roleid, phone, createdDate) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = ConnectionMySQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassWord());
            ps.setString(5, user.getAvatar());
            ps.setInt(6, user.getRoleid());
            ps.setString(7, user.getPhone());
            ps.setDate(8, new java.sql.Date(user.getCreatedDate().getTime())); // convert util.Date -> sql.Date

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting user: " + user, e);
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        return checkExists("SELECT 1 FROM users WHERE email = ?", email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return checkExists("SELECT 1 FROM users WHERE username = ?", username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return checkExists("SELECT 1 FROM users WHERE phone = ?", phone);
    }

    private boolean checkExists(String sql, String value) {
        try (Connection conn = ConnectionMySQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, value);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking existence for value: " + value, e);
        }
    }

    @Override
    public void updatePassword(String email, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        try (Connection conn = ConnectionMySQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, email);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating password for email: " + email, e);
        }
    }
}
