package ru.aston.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.dao.WorkerDao;
import ru.aston.model.Worker;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final WorkerDao workerDao;


    public List<Worker> findAllWorker() {
        return workerDao.findAllWorker();
    }

    public void createWorker(Worker worker) {
        workerDao.createWorker(worker);
    }

    public Worker getWorkerById(int id) {
        return workerDao.getWorkerById(id);
    }

    public void updateWorker(Worker worker) {
        workerDao.updateWorker(worker);
    }

    public void deleteWorker(int id) {
        workerDao.deleteWorker(id);
    }


}
