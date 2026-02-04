package com.dhirunand.satcom.entities;

import com.dhirunand.satcom.entities.types.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.PENDING;

    private LocalDate dueDate;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }

}
