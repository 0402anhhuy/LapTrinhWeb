package service;

import model.User;

public interface UserService {
    User login(String username, String password);
    boolean register(String username, String fullname, String email, String phone, String password);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
}

