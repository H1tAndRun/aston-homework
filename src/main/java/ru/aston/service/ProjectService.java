package ru.aston.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.dao.ProjectDao;
import ru.aston.model.Department;
import ru.aston.model.Project;
import ru.aston.model.ProjectWorker;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectDao projectDao;


    public List<Project> findAllProject() {
        return projectDao.findAllProject();
    }

    public void createProject(Project project) {
        projectDao.createProject(project);
    }

    public Project getProjectById(int id) {

        return projectDao.getProjectById(id);
    }

    public void updateProject(Project project) {
        projectDao.updateProject(project);
    }

    public void deleteProject(int id) {
        projectDao.deleteProject(id);
    }

    public List<ProjectWorker> getProjectsWithOfWorkers() {
        return projectDao.getProjectsWithOfWorkers();
    }

    public void deleteProjectWorker(int id){
        projectDao.deleteProjectWorker(id);
    }
}
