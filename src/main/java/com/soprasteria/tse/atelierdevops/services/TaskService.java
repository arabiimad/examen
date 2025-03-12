package com.soprasteria.tse.atelierdevops.services;

import com.soprasteria.tse.atelierdevops.models.dto.TaskRequest;
import com.soprasteria.tse.atelierdevops.models.entity.Task;
import com.soprasteria.tse.atelierdevops.repositories.TaskRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll() {
        return this.taskRepository.findAll();
    }

    public boolean exists(final Long taskId) {
        return taskRepository.existsById(taskId);
    }

    public Task getById(final Long taskId) {
        return this.taskRepository.getOne(taskId);
    }

    public Task create(final TaskRequest taskRequest) {
        Task task = new Task();
        task.setDescription(taskRequest.getDescription());
        task.setCompleted(taskRequest.isCompleted());
        return taskRepository.saveAndFlush(task);
    }

    public void update(final Long taskId, final TaskRequest taskRequest) {
        Task task = getById(taskId);
        if (StringUtils.isNotBlank(taskRequest.getDescription())) {
            task.setDescription(taskRequest.getDescription());
        }
        if (taskRequest.isCompleted() != null) {
            task.setCompleted(taskRequest.isCompleted());
        }
        taskRepository.save(task);
    }

    public void delete(final Long taskId) {
        Task task = getById(taskId);
        taskRepository.delete(task);
    }
}
