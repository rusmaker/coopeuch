package com.coopeuch.mantenedor1.repository;

import com.coopeuch.mantenedor1.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
