package ru.aston.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.aston.model.Project;
import ru.aston.service.ProjectService;

import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/project")
    public String findAllProject(Model model) {
        model.addAttribute("projects", projectService.findAllProject());
        return "get-all-project";
    }

    @GetMapping("/project/create")
    public String createWorkerForm(Model model) {
        model.addAttribute(new Project());
        return "create-project";
    }

    @PostMapping("/project/create")
    public String createProject(Project project) {
        System.out.println(project);
        projectService.createProject(project);
        return "redirect:/project";
    }

    @GetMapping("/project/update/{id}")
    public String updateProjectForm(@PathVariable int id, Model model) {
        model.addAttribute("project", projectService.getProjectById(id));
        return "update-project";
    }

    @PostMapping("/project/update/{id}")
    public String updateProject(Project project) {
        projectService.updateProject(project);
        return "redirect:/project";
    }

    @PostMapping("/project/delete/{id}")
    public String deleteWorker(@PathVariable int id) {
        projectService.deleteProject(id);
        return "redirect:/project";
    }
}
