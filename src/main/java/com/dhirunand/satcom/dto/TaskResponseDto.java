package com.dhirunand.satcom.dto;

import com.dhirunand.satcom.entities.types.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto {
    private String id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate dueDate;
}
