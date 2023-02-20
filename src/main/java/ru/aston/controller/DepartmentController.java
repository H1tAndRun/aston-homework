package ru.aston.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.aston.model.Department;
import ru.aston.model.Worker;
import ru.aston.service.DepartmentService;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/department")
    public String findAllDepartment(Model model) {
        model.addAttribute("departments", departmentService.findAllDepartment());
        return "get-all-departments";
    }

    @GetMapping("/department/create")
    public String createDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "create-department";
    }

    @PostMapping("/department/create")
    public String createDepartment(Department department) {
        departmentService.createDepartment(department);
        return "redirect:/department";
    }

    @GetMapping("/department/update/{id}")
    public String updateDepartmentForm(@PathVariable int id, Model model) {
        model.addAttribute("department", departmentService.getDepartmentById(id));
        return "update-department";
    }

    @PostMapping("/department/update/{id}")
    public String updateWorker(Department department) {
        departmentService.updateDepartment(department);
        return "redirect:/department";
    }

    @PostMapping("/department/delete/{id}")
    public String deleteWorker(@PathVariable int id) {
        try {
            departmentService.deleteDepartment(id);
        } catch (SQLException e) {
            return "redirect:/department";
        }
        return "redirect:/department";
    }
}
