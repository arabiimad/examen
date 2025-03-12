package com.soprasteria.tse.atelierdevops.services;

import com.soprasteria.tse.atelierdevops.models.dto.TaskRequest;
import com.soprasteria.tse.atelierdevops.models.entity.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.transaction.Transactional;

@SpringBootTest
public class TaskServiceTests {

    @Autowired
    private TaskService taskService;

    @Test
    @Transactional
    public void testCreateTaskOK() {
        TaskRequest taskRequestOk = new TaskRequest();
        taskRequestOk.setCompleted(false);
        taskRequestOk.setDescription("Ma tache");

        Task taskOk = taskService.create(taskRequestOk);
        Assertions.assertNotNull(taskOk);
    }

    @Test
    @Transactional
    public void testCreateTaskError() {
        TaskRequest taskRequestError = new TaskRequest();
        taskRequestError.setCompleted(false);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            taskService.create(taskRequestError);
        });
    }
}
