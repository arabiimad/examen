package com.soprasteria.tse.atelierdevops.controllers;

import com.soprasteria.tse.atelierdevops.models.dto.TaskRequest;
import com.soprasteria.tse.atelierdevops.models.dto.TaskResponse;
import com.soprasteria.tse.atelierdevops.models.entity.Task;
import com.soprasteria.tse.atelierdevops.services.TaskService;
import com.soprasteria.tse.atelierdevops.services.mappers.TaskMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {

    final TaskService taskService;
    final TaskMapper taskMapper;

    @Autowired
    public TaskController(final TaskService taskService, final TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @Operation(summary = "Get the full list of task")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TaskResponse.class)))
        )
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskResponse> getAll() {
        return taskMapper.toSimpleResponseList(taskService.getAll());
    }


    @Operation(summary = "Create a new Task")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "OK",
            content = @Content(schema = @Schema(implementation = TaskResponse.class))
        )
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        if(taskRequest == null || StringUtils.isBlank(taskRequest.getDescription())) {
            return ResponseEntity.badRequest().build();
        }

        Task task = taskService.create(taskRequest);

        URI resourceLocation = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/api/tasks/" + task.getId())
            .build()
            .toUri();

        return ResponseEntity.created(resourceLocation).body(taskMapper.toSimpleResponse(task));
    }

    @Operation(summary = "Get task by id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = TaskResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping(value = "{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskResponse> getById(@PathVariable("taskId") Long taskId) {
        if(!taskService.exists(taskId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(taskMapper.toSimpleResponse(taskService.getById(taskId)));
    }

    @Operation(summary = "Update task by id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = TaskResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PatchMapping(value = "{taskId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskRequest taskRequest) {
        if(!taskService.exists(taskId)) {
            return ResponseEntity.notFound().build();
        }

        this.taskService.update(taskId, taskRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete task by id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = TaskResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping(value = "{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable("taskId") Long taskId) {
        if(!taskService.exists(taskId)) {
            return ResponseEntity.notFound().build();
        }

        this.taskService.delete(taskId);
        return ResponseEntity.ok().build();
    }
}
