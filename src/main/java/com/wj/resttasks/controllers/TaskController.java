package com.wj.resttasks.controllers;

import com.wj.resttasks.models.Task;
import com.wj.resttasks.models.User;
import com.wj.resttasks.repositories.TaskRepository;
import com.wj.resttasks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/mytasks")
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public Set<Task> getAllTasks(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        Optional<User> isUser = userRepository.findByUsername(username);
        User currentUser = isUser.get();
        return currentUser.getTasks();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTask(@PathVariable String id){
        Long taskId = Long.parseLong(id);
        return taskRepository.findById(taskId);
    }

    @PostMapping("/add")
    public Task createTask(HttpServletRequest request, @RequestBody Map<String, String> body){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        Optional<User> isUser = userRepository.findByUsername(username);
        User currentUser = isUser.get();
        String description = body.get("description");
        return taskRepository.save(new Task(description, currentUser));
    }

    @PutMapping("/update/{id}")
    public Task updateTask(@PathVariable String id,
                       @RequestBody Map<String, String> body){
        Task task = taskRepository.getReferenceById(Long.parseLong(id));
        task.setDescription(body.get("description"));
        return taskRepository.save(task);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteTask(@PathVariable String id){
        Long taskId = Long.parseLong(id);
        taskRepository.deleteById(taskId);
        return true;
    }

    @DeleteMapping("/delete/all")
    @Transactional
    public boolean deleteAllTasks(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByUsername(principal.getName()).get();
        taskRepository.deleteByUser_userid(user.getUserid());
        return true;
    }
}
