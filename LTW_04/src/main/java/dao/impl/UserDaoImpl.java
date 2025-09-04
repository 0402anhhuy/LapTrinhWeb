//package dao.impl;
//
//import dao.UserDao;
//import model.User;
//
//import java.sql.*;
//
//public class UserDaoImpl implements UserDao {
//    public Connection conn = null;
//    public PreparedStatement ps = null;
//    public ResultSet rs = null;
//    public UserDaoImpl() {
//        conn = ConnectionMySQL.getConnection();
//    }
//    @Override
//    public User get(String username) {
//        String sql = "SELECT * FROM users WHERE username = ? ";
//        try {
//            conn = new ConnectionMySQL().getConnection();
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, username);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                User user = new User();
//                user.setId(rs.getInt("id"));
//                user.setEmail(rs.getString("email"));
//                user.setUserName(rs.getString("username"));
//                user.setFullName(rs.getString("fullname"));
//                user.setPassWord(rs.getString("password"));
//                user.setAvatar(rs.getString("avatar"));
//                user.setRoleid(Integer.parseInt(rs.getString("roleid")));
//                user.setPhone(rs.getString("phone"));
//                user.setCreatedDate(rs.getDate("createdDate"));
//                return user; }
//        } catch (Exception e) {e.printStackTrace(); }
//        return null;
//    }
//    @Override
//    public void insert(User user){
//        String query = "INSERT INTO users(email, userName, fullName, passWord, avatar, roleid, phone, createdDate) VALUES (?,?,?,?,?,?,?,?)";
//        try {
//            ps = conn.prepareStatement(query);
//            ps.setString(1, user.getEmail());
//            ps.setString(2, user.getUserName());
//            ps.setString(3, user.getFullName());
//            ps.setString(4, user.getPassWord());
//            ps.setString(5, user.getAvatar());
//            ps.setInt(6, user.getRoleid());
//            ps.setString(7, user.getPhone());
//            ps.setDate(8, (Date) user.getCreatedDate());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Override
//    public boolean checkExistEmail(String email) {
//        boolean duplicate = false;
//        String query = "select * from users where email = ?";
//        try {
//            ps = conn.prepareStatement(query);
//            ps.setString(1, email);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                duplicate = true;
//            }
//            ps.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return duplicate;
//    }
//    @Override
//    public boolean checkExistUsername(String username) {
//        boolean duplicate = false;
//        String query = "select * from users where userName = ?";
//        try {
//            ps = conn.prepareStatement(query);
//            ps.setString(1, username);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                duplicate = true;
//            }
//            ps.close();
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//        return duplicate;
//    }
//    @Override
//    public boolean checkExistPhone(String phone) {
//        boolean duplicate = false;
//        String query = "select * from users where phone = ?";
//        try {
//            ps = conn.prepareStatement(query);
//            ps.setString(1, phone);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                duplicate = true;
//            }
//            ps.close();
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//        return duplicate;
//    }
//    @Override
//    public void updatePassword(String email, String newPassword) {
//        String query = "Update users set passWord = ? where email = ?";
//        try {
//            ps = conn.prepareStatement(query);
//            ps.setString(1, newPassword);
//            ps.setString(2, email);
//            ps.executeUpdate();
//        }
//        catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//}
