package com.project.crm.repository;

import com.project.crm.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    @Override
    List<Task> findAll();
}
