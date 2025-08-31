package model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("unused")
public class User implements Serializable {
    private int id;
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private String password;
    private int roleid;
    private Date createdDate;

    public User() {}

    public User(String username, String fullname, String email, String phone, String password, int roleid, Date createdDate) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.roleid = roleid;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
