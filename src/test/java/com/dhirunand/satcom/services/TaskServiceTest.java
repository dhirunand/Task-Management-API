package com.dhirunand.satcom.services;

import com.dhirunand.satcom.dto.TaskRequestDto;
import com.dhirunand.satcom.dto.TaskResponseDto;
import com.dhirunand.satcom.entities.Task;
import com.dhirunand.satcom.entities.types.TaskStatus;
import com.dhirunand.satcom.exceptions.ResourceNotFoundException;
import com.dhirunand.satcom.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private TaskService taskService;

    private Task task;
    private TaskRequestDto requestDto;
    private TaskResponseDto responseDto;

    @BeforeEach
    void setUp() {
        task = Task.builder()
                .id("1")
                .title("Task Title")
                .description("Task description")
                .dueDate(LocalDate.of(2025, 3, 25))
                .build();

        requestDto = mapper.map(task, TaskRequestDto.class);
        responseDto = mapper.map(task, TaskResponseDto.class);
    }



    @Test
    void testCreateTask_WhenDtoIsValid_ThenResponseWithId() {
        when(mapper.map(requestDto, Task.class)).thenReturn(task);
        when(repository.save(task)).thenReturn(task);
        when(mapper.map(task, TaskResponseDto.class)).thenReturn(responseDto);

        TaskResponseDto result = taskService.create(requestDto);

        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(repository).save(task);
    }


    @Test
    void testGetById_WhenIdExists_ThenReturnTaskResponseDto() {
        when(repository.findById("1")).thenReturn(Optional.of(task));
        when(mapper.map(task, TaskResponseDto.class)).thenReturn(responseDto);

        TaskResponseDto result = taskService.getById("1");

        assertEquals("1", result.getId());
        verify(repository).findById("1");
    }

    @Test
    void testGetById_WhenIdDoesNotExists_ThenThrowResourceNotFoundException() {
        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> taskService.getById("1"));
    }


    @Test
    void shouldUpdateTaskSuccessfully() {
        when(repository.findById("1")).thenReturn(Optional.of(task));
        when(mapper.map(requestDto, Task.class)).thenReturn(task);
        when(repository.save(task)).thenReturn(task);
        when(mapper.map(task, TaskResponseDto.class)).thenReturn(responseDto);

        TaskResponseDto result = taskService.update("1", requestDto);

        assertEquals("1", result.getId());
        verify(repository).save(task);
    }


    @Test
    void shouldDeleteTaskSuccessfully() {
        when(repository.findById("1")).thenReturn(Optional.of(task));

        taskService.delete("1");

        verify(repository).delete(task);
    }


//    @Test
//    void shouldReturnAllTasksSorted() {
//        when(repository.findBy(Sort.by("title"))).thenReturn(List.of(task));
//        when(mapper.map(task, TaskResponseDto.class)).thenReturn(responseDto);
//
//        List<TaskResponseDto> result = taskService.getAllTasks(null, );
//
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    void shouldThrowBadRequestForInvalidStatus() {
//        Pageable pageable = PageRequest.of(0, 10);
//
//        assertThrows(ResponseStatusException.class, () -> taskService.findByStatus("invalid", pageable));
//    }
}
