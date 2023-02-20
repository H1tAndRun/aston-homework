package ru.aston.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.aston.configuration.ConnectDb;


import java.sql.*;


@Component
public class WorkerDao {
    private final ConnectDb connectDb;

    @Autowired
    public WorkerDao(ConnectDb connectDb) {
        this.connectDb = connectDb;
    }

    public String getAll() {
        String sql = "SELECT w.id, w.first_name, w.last_name, w.email, d.department_name " +
                "FROM workers w " +
                "JOIN departments d ON w.department_id = d.id";
        try (Statement statement = connectDb.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql) ){
            while (resultSet.next()){
                System.out.println(resultSet.getString("first_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}



   /* Connection connection =null;


        try(connection = dataSource.getConnection()) {

                String sql = "SELECT w.id, w.first_name, w.last_name, w.email, d.department_name " +
                "FROM workers w " +
                "JOIN departments d ON w.department_id = d.id";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                String first_name = resultSet.getString("first_name");
                System.out.println(first_name);
                }

                } catch (SQLException e) {
                throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
                }
                return null;
                }*/
