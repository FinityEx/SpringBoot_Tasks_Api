package com.wj.resttasks.controllers;

import com.wj.resttasks.models.Task;
import com.wj.resttasks.repositories.TaskRepository;
import com.wj.resttasks.models.User;
import com.wj.resttasks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/isinrole/{role}")
    public boolean getUserRole(HttpServletRequest request, @PathVariable String role){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        User user = userRepository.findByUsername(username).get();
        return request.isUserInRole("ROLE_" + role.toUpperCase());
    }

    @GetMapping("/allusers")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/alltasks")
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @DeleteMapping("/deleteuser/{username}")
    public boolean deleteUserByUsername(@PathVariable String username){
        userRepository.delete(userRepository.findByUsername(username).get());
        return true;
    }

}
