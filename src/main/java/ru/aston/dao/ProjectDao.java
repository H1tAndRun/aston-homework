package ru.aston.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.aston.configuration.ConnectDb;
import ru.aston.model.Department;
import ru.aston.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectDao {

    private final ConnectDb connectDb;

    public List<Project> findAllProject() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * from projects";
        try (Connection connection = connectDb.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                projects.add(Project
                        .builder()
                        .id(resultSet.getInt("id"))
                        .nameProject(resultSet.getString("name_project"))
                        .projectClient(resultSet.getString("project_client"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return projects;
    }

    public void createProject(Project project) {
        String sql = "INSERT INTO projects (name_project,project_client) VALUES (?,?)";
        try (Connection connection = connectDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, project.getNameProject());
            preparedStatement.setString(2, project.getProjectClient());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Project getProjectById(int id) {
        String sql = "SELECT * FROM projects WHERE id=?";
        try (Connection connection = connectDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Project project = Project.builder().id(resultSet.getInt("id"))
                            .nameProject(resultSet.getString("name_project"))
                            .projectClient(resultSet.getString("project_client"))
                            .build();
                    return project;
                }

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    public void updateProject(Project project) {
        String sql = "UPDATE projects SET name_project = ?, project_client = ? WHERE id = ?";
        try (Connection connection = connectDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, project.getNameProject());
            preparedStatement.setString(2, project.getProjectClient());
            preparedStatement.setInt(3, project.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProject(int id) {
        String deleteProjectsSql = "DELETE FROM projects WHERE id = ?";
        String deleteProjectsWorkerSql = "DELETE FROM projects_workers WHERE worker_id = ?";
        try (Connection connection = connectDb.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement deleteProjectsStmt = connection.prepareStatement(deleteProjectsSql);
                 PreparedStatement deleteProjectsWorkerStmt = connection.prepareStatement(deleteProjectsWorkerSql)) {
                deleteProjectsWorkerStmt.setInt(1, id);
                deleteProjectsWorkerStmt.executeUpdate();
                deleteProjectsStmt.setInt(1, id);
                deleteProjectsStmt.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {

        }
    }
}
