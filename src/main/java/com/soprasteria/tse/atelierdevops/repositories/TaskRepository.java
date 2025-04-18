package com.soprasteria.tse.atelierdevops.repositories;

import com.soprasteria.tse.atelierdevops.models.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
