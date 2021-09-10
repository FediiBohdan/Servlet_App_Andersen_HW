package ua.fedii.servlet_demo.servlet.dao;

import ua.fedii.servlet_demo.servlet.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class UserDAOIml implements UserDAO {
    private static final String INSERT_USER = "INSERT INTO users (first_name, second_name, age, last_update_date, is_deleted) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT id, first_name, second_name, age FROM users WHERE id = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
    private static final String DELETE_USER = "UPDATE users SET last_update_date = ?, is_deleted = ? WHERE id = ?;";
    private static final String UPDATE_USER = "UPDATE users SET first_name = ?, second_name = ?, age = ?, last_update_date = ? WHERE id = ?;";

    public UserDAOIml() {
    }

    protected Connection getConnection() {

        Connection connection = null;
        try {
            String jdbcDriver = "org.postgresql.Driver";
            Class.forName(jdbcDriver);
            String jdbcURL = "jdbc:postgresql://localhost:5432/userdb";
            String jdbcUsername = "postgres";
            String jdbcPassword = "321987z";
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertUser(User user) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, LocalDateTime.now().toString());
            preparedStatement.setBoolean(5, false);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User selectUser(long id) {
        User user = null;

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                int age = resultSet.getInt("age");
                user = new User(id, firstName, secondName, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!(resultSet.getBoolean("is_deleted"))) {
                    long id = resultSet.getLong("id");
                    String firstName = resultSet.getString("first_name");
                    String secondName = resultSet.getString("second_name");
                    int age = resultSet.getInt("age");
                    users.add(new User(id, firstName, secondName, age));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void deleteUser(long id) {
        try (Connection connection = getConnection();
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
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, LocalDateTime.now().toString());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
