package ua.fedii.servlet_demo.servlet.dao;

import ua.fedii.servlet_demo.servlet.email_exception.EmailException;
import ua.fedii.servlet_demo.servlet.model.User;
import ua.fedii.servlet_demo.servlet.web.DataBaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDAOIml implements UserDAO {
    private static final String INSERT_USER = "INSERT INTO users (first_name, second_name, age, email, last_update_date, is_deleted) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT id, first_name, second_name, age, email FROM users WHERE id = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
    private static final String DELETE_USER = "UPDATE users SET last_update_date = ?, is_deleted = ? WHERE id = ?;";
    private static final String UPDATE_USER = "UPDATE users SET first_name = ?, second_name = ?, age = ?, email = ?, last_update_date = ? WHERE id = ?;";

    public UserDAOIml() {
    }

    @Override
    public void insertUser(User user) {
        try (Connection connection = DataBaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, checkEmail(user.getEmail()));
            preparedStatement.setString(5, LocalDateTime.now().toString());
            preparedStatement.setBoolean(6, false);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User selectUser(long id) {
        User user = null;

        try (Connection connection = DataBaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                user = new User(id, firstName, secondName, age, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DataBaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!(resultSet.getBoolean("is_deleted"))) {
                    long id = resultSet.getLong("id");
                    String firstName = resultSet.getString("first_name");
                    String secondName = resultSet.getString("second_name");
                    int age = resultSet.getInt("age");
                    String email = resultSet.getString("email");
                    users.add(new User(id, firstName, secondName, age, email));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void deleteUser(long id) {
        try (Connection connection = DataBaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setString(1, LocalDateTime.now().toString());
            preparedStatement.setBoolean(2, true);
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = DataBaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, checkEmail(user.getEmail()));
            preparedStatement.setString(5, LocalDateTime.now().toString());
            preparedStatement.setLong(6, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String checkEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.find()) {
            throw new EmailException("Incorrect email!");
        } else {
            return email;
        }
    }

}