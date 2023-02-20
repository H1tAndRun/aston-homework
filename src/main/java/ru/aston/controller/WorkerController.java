package ru.aston.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.aston.model.Worker;
import ru.aston.service.DepartmentService;
import ru.aston.service.RoleService;
import ru.aston.service.WorkerService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    private final DepartmentService departmentService;

    private final RoleService roleService;

    @GetMapping("/worker")
    public String get(Model model) {
        List<Worker> allWorker = workerService.findAllWorker();
        model.addAttribute("workers", allWorker);
        return "get-all-workers";
    }

    @GetMapping("/worker/create")
    public String createWorkerForm(Model model) {
        model.addAttribute(new Worker());
        model.addAttribute("departments", departmentService.findAllDepartment());
        model.addAttribute("roles", roleService.findAllRoles());
        return "create-workers";
    }

    @PostMapping("/worker/create")
    public String createWorker(Worker worker) {
        workerService.createWorker(worker);
        return "redirect:/worker";
    }

    @GetMapping("/worker/update/{id}")
    public String updateWorkerForm(@PathVariable int id, Model model) {
        model.addAttribute("worker", workerService.getWorkerById(id));
        model.addAttribute("departments", departmentService.findAllDepartment());
        model.addAttribute("roles", roleService.findAllRoles());
        return "update-worker";
    }

    @PostMapping("/worker/update/{id}")
    public String updateWorker(Worker worker) {
        workerService.updateWorker(worker);
        return "redirect:/worker";
    }

    @PostMapping("/worker/delete/{id}")
    public String deleteWorker(@PathVariable int id) {
        workerService.deleteWorker(id);
        return "redirect:/worker";
    }
}
