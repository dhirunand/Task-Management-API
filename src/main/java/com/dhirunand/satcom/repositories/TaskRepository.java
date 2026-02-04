package com.dhirunand.satcom.repositories;

import com.dhirunand.satcom.entities.Task;
import com.dhirunand.satcom.entities.types.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

//    List<Task> findAllByOrderByDueDateAsc();
    List<Task> findBy(Sort sort);

    Page<Task> findByStatus(TaskStatus status, Pageable pageable);
}
