package com.project.crm.repository;

import com.project.crm.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    @Override
    List<Task> findAll();
}
