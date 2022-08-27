package com.wj.resttasks.repositories;


import com.wj.resttasks.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // delete a task by userid in User
    List<Task> deleteByUser_userid(Long user_id);
}
