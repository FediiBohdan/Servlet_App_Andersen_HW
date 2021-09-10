package ua.fedii.servlet_demo.servlet.dao;

import ua.fedii.servlet_demo.servlet.model.User;

import java.util.List;

public interface UserDAO {
    void insertUser(User user);
    User selectUser(long id);
    List<User> selectAllUsers();
    void deleteUser(long id);
    void updateUser(User user);
}
