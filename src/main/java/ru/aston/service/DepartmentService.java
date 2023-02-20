package ru.aston.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.dao.DepartmentDao;
import ru.aston.model.Department;
import ru.aston.model.Worker;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentDao departmentDao;

    public List<Department> findAllDepartment() {
        return departmentDao.findAllDepartment();
    }

    public void createDepartment(Department department) {
        departmentDao.createDepartment(department);
    }

    public Department getDepartmentById(int id) {
        return departmentDao.getDepartmentById(id);
    }

    public void updateDepartment(Department worker) {
        departmentDao.updateDepartment(worker);
    }

    public void deleteDepartment(int id) throws SQLException {
        departmentDao.deleteDepartment(id);
    }
}

