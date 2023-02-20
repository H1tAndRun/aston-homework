package ru.aston.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.aston.configuration.ConnectDb;
import ru.aston.model.Role;
import ru.aston.model.Worker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoleDao {

    private final ConnectDb connectDb;

    public List<Role> findAllRoles() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * from roles";
        try (Statement statement = connectDb.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                roles.add(Role
                        .builder()
                        .id(resultSet.getInt("id"))
                        .nameRole(resultSet.getString("role_name"))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roles;
    }
}
