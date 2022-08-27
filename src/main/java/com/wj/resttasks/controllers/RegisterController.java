package com.wj.resttasks.controllers;

import com.wj.resttasks.models.User;
import com.wj.resttasks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public User createUser(@RequestBody Map<String, String> body) throws Exception{
        String username = body.get("username");
        String password = body.get("password");

        if(!userRepository.findByUsername(username).isPresent()) {
            User newUser = new User(username, new BCryptPasswordEncoder().encode(password));
            newUser.setRole("USER");
            return userRepository.save(newUser);
        }else throw new Exception("User already exists!");
    }

}
