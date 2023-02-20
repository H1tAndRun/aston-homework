package ru.aston.dao;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.sql.*;


@Component
public class WorkerDao {

    private static final String URL = "jdbc:postgresql://localhost:5432/JavaIntensivDb";

    private static final String USERNAME = "postgres";

    private static final String PASSWORD ="556677";


    public  String getAll()   {
        System.out.println("ddww");
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String sql ="SELECT w.id, w.first_name, w.last_name, w.email, d.department_name " +
                    "FROM workers w " +
                    "JOIN departments d ON w.department_id = d.id";
            Statement statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery(sql);
            while (resultSet.next()){
                String first_name = resultSet.getString("first_name");
                System.out.println(first_name);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
