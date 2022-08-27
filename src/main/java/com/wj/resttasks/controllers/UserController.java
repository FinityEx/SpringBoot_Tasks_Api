package com.wj.resttasks.controllers;

import com.wj.resttasks.models.User;
import com.wj.resttasks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/myaccount")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @DeleteMapping("/delete")
    public boolean deleteAccount(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        userRepository.delete(userRepository.findByUsername(username).get());
        return true;
    }

    @PostMapping("/changepassword")
    public boolean changePassword(HttpServletRequest request, @RequestBody Map<String, String> body){
        String oldPassword = body.get("old_password");
        String newPassword = body.get("new_password");

        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        User user = userRepository.findByUsername(username).get();
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        //check if passwords match
        if(bCrypt.matches(oldPassword, user.getPassword()))
            user.setPassword(bCrypt.encode(newPassword));

        userRepository.save(user);
        return true;
    }

    @PostMapping("/changeusername")
    public boolean changeUsername(HttpServletRequest request, @RequestBody Map<String, String> body){
        String newUsername = body.get("new_username");
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        User user = userRepository.findByUsername(username).get();
        user.setUsername(newUsername);
        userRepository.save(user);
        return true;
    }

}
