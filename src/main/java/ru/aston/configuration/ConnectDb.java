package ru.aston.configuration;

import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectDb implements Closeable {

    private final String URL = "jdbc:postgresql://localhost:5432/JavaIntensivDb";
    private final String USER = "postgres";
    private final String PASSWORD = "556677";
    private final Connection connection;

    public ConnectDb() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }


    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
