package ua.fedii.servlet_demo.servlet.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.Connection.TRANSACTION_READ_COMMITTED;

public class DataBaseConnection {
    private static DataBaseConnection instance;
    private Connection connection;

    private DataBaseConnection() {
        try {
            String jdbcDriver = "org.postgresql.Driver";
            Class.forName(jdbcDriver);
            String jdbcURL = "jdbc:postgresql://192.168.56.103:5432/userdb";
            String jdbcUsername = "postgres";
            String jdbcPassword = "321987z";
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static DataBaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DataBaseConnection();
        }
        return instance;
    }
}