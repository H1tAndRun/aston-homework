package ru.aston.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.aston.configuration.ConnectDb;
import ru.aston.model.Department;
import ru.aston.model.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class DepartmentDao {

    private final ConnectDb connectDb;

    public List<Department> findAllDepartment() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * from departments";
        try (Connection connection = connectDb.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                departments.add(Department
                        .builder()
                        .id(resultSet.getInt("id"))
                        .departmentName(resultSet.getString("department_name"))
                        .departmentLocationName(resultSet.getString("department_location"))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return departments;
    }

    public void createDepartment(Department department) {
        String sql = "INSERT INTO departments (department_name,department_location) VALUES (?,?)";
        try (Connection connection = connectDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setString(2, department.getDepartmentLocationName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Department getDepartmentById(int id) {
        String sql = "SELECT * FROM departments WHERE id=?";
        try (Connection connection = connectDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Department department = Department.builder().id(resultSet.getInt("id"))
                            .departmentName(resultSet.getString("department_name"))
                            .departmentLocationName(resultSet.getString("department_location"))
                            .build();
                    System.out.println(department);
                    return department;
                }

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    public void updateDepartment(Department department) {
        String sql = "UPDATE departments SET department_name = ?, department_location = ? WHERE id = ?";

        try (Connection connection = connectDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setString(2, department.getDepartmentLocationName());
            preparedStatement.setInt(3, department.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteDepartment(int id) throws SQLException {
        String sql = "DELETE FROM departments WHERE id = ?";
        try (Connection connection = connectDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
