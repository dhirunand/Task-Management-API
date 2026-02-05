package com.dhirunand.satcom.controllers;

import com.dhirunand.satcom.dto.TaskRequestDto;
import com.dhirunand.satcom.dto.TaskResponseDto;
import com.dhirunand.satcom.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;

    private final int PAGE_SIZE = 5;


    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody @Valid TaskRequestDto taskRequestDto) {
        TaskResponseDto createdTaskDto = service.create(taskRequestDto);

        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable String id) {
        TaskResponseDto taskResponseDto = service.getById(id);
        return ResponseEntity.ok(taskResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable String id, @Valid @RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto taskResponseDto = service.update(id, taskRequestDto);
        return ResponseEntity.ok(taskResponseDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable String id) {
        service.delete(id);
    }

    // Filtered Paginated Sorted result
    @GetMapping()
    public ResponseEntity<List<TaskResponseDto>> getAllTasks(@RequestParam(required = false) String status,
                                                             @RequestParam(defaultValue = "dueDate") String sortBy,
                                                             @RequestParam(defaultValue = "0") Integer pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy));
        return ResponseEntity.ok(service.getAllTasks(status, pageRequest).getContent());
    }

}
