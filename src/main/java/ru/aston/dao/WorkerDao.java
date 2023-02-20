package ru.aston.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.aston.configuration.ConnectDb;
import ru.aston.model.Worker;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class WorkerDao {
    private final ConnectDb connectDb;

    public List<Worker> findAllWorker() {
        List<Worker> workers = new ArrayList<>();
        String sql = "SELECT w.id, w.first_name, w.last_name, w.email, d.department_name, r.role_name\n" +
                "FROM workers w\n" +
                "JOIN departments d ON w.department_id = d.id\n" +
                "JOIN roles r ON w.role_id = r.id;";
        try (Connection connection = connectDb.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                workers.add(Worker
                        .builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .email(resultSet.getString("email"))
                        .department(resultSet.getString("department_name"))
                        .role(resultSet.getString("role_name"))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return workers;
    }

    public void createWorker(Worker worker) {
        String sql = "INSERT INTO workers (first_name,last_name,email,department_id,role_id) VALUES (?,?,?,?,?)";
        try (Connection connection = connectDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, worker.getFirstName());
            preparedStatement.setString(2, worker.getLastName());
            preparedStatement.setString(3, worker.getEmail());
            preparedStatement.setInt(4, Integer.parseInt(worker.getDepartment()));
            preparedStatement.setInt(5, Integer.parseInt(worker.getRole()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Worker getWorkerById(int id) {
        String sql = "SELECT w.id, w.first_name, w.last_name, w.email, d.department_name, r.role_name\n" +
                "FROM workers w\n" +
                "JOIN departments d ON w.department_id = d.id\n" +
                "JOIN roles r ON w.role_id = r.id WHERE w.id = ?";
        try (Connection connection = connectDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Worker worker = Worker.builder().id(resultSet.getInt("id"))
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .email(resultSet.getString("email"))
                            .department(resultSet.getString("department_name"))
                            .role(resultSet.getString("role_name"))
                            .build();
                    return worker;
                }

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    public void updateWorker(Worker worker) {
        String sql = "UPDATE workers SET first_name = ?, last_name = ?" +
                ", email = ?,department_id = ?,role_id =? WHERE id = ?";
        try (Connection connection = connectDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, worker.getFirstName());
            preparedStatement.setString(2, worker.getLastName());
            preparedStatement.setString(3, worker.getEmail());
            preparedStatement.setInt(4, Integer.parseInt(worker.getDepartment()));
            preparedStatement.setInt(5, Integer.parseInt(worker.getRole()));
            preparedStatement.setInt(6, worker.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteWorker(int id) {
        String deleteWorkerSql = "DELETE FROM workers WHERE id = ?";
        String deleteProjectsSql = "DELETE FROM projects_workers WHERE worker_id = ?";
        try (Connection connection = connectDb.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement deleteWorkerStmt = connection.prepareStatement(deleteWorkerSql);
                 PreparedStatement deleteProjectsStmt = connection.prepareStatement(deleteProjectsSql)) {
                deleteProjectsStmt.setInt(1, id);
                deleteProjectsStmt.executeUpdate();
                deleteWorkerStmt.setInt(1, id);
                deleteWorkerStmt.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {

        }
    }
}