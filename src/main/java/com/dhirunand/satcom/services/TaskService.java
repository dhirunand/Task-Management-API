package com.dhirunand.satcom.services;

import com.dhirunand.satcom.dto.TaskRequestDto;
import com.dhirunand.satcom.dto.TaskResponseDto;
import com.dhirunand.satcom.entities.Task;
import com.dhirunand.satcom.entities.types.TaskStatus;
import com.dhirunand.satcom.exceptions.ResourceNotFoundException;
import com.dhirunand.satcom.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    TaskRepository repository;
    ModelMapper mapper;

    public TaskService(TaskRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TaskResponseDto create(@Valid TaskRequestDto taskRequestDto) {
        Task toSaveTask = mapper.map(taskRequestDto, Task.class);
        Task savedTask = repository.save(toSaveTask);
        return mapper.map(savedTask, TaskResponseDto.class);
    }

    public TaskResponseDto getById(String id) {
        Task task = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found with id: " + id));
        return mapper.map(task, TaskResponseDto.class);
    }

    public TaskResponseDto update(String id, TaskRequestDto taskRequestDto) {
        Task task = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found with id: " + id));
        Task toSaveTask = mapper.map(taskRequestDto, Task.class);
        toSaveTask.setId(id);

        Task savedTask = repository.save(task);

        return mapper.map(savedTask, TaskResponseDto.class);
    }


    @Transactional
    public void delete(String id) {
        Task task = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        repository.delete(task);
    }


    @Transactional()
    public List<TaskResponseDto> getAllTasks(String sortBy) {
        return repository.findBy(Sort.by(sortBy))
                .stream()
                .map(task -> mapper.map(task, TaskResponseDto.class))
                .toList();
    }

    @Transactional()
    public List<TaskResponseDto> findByStatus(String status, Pageable pageable) {
        TaskStatus taskStatus;
        try {
            taskStatus = TaskStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid status. Allowed values: " + Arrays.toString(TaskStatus.values())
            );
        }

        return repository.findByStatus(taskStatus, pageable)
                .stream()
                .map(task -> mapper.map(task, TaskResponseDto.class))
                .collect(Collectors.toList());
    }
}
