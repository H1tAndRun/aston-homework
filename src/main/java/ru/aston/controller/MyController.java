package ru.aston.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.aston.dao.WorkerDao;


import java.util.List;

@Controller
public class MyController {

    private final WorkerDao workerDao;

    @Autowired
    public MyController(WorkerDao workerDao) {
        this.workerDao = workerDao;
    }

    @GetMapping("/hello")
    public String get(){
        System.out.println("метод get start");
        String all = workerDao.getAll();
        return "hello";
    }
}
