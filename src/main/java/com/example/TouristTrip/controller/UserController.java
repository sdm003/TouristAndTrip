package com.example.TouristTrip.controller;

import com.example.TouristTrip.entity.Users;
import com.example.TouristTrip.model.Message;
import com.example.TouristTrip.model.UserRequest;
import com.example.TouristTrip.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping
    @RequestMapping("/registration")
    public Message registration(@RequestBody Users users) {
        return userService.addUser(users);
    }

    @PutMapping
    @RequestMapping("/update_user")
    public Message updateUsers(@RequestBody UserRequest userRequest, Principal principal) {
        return userService.updateUser(principal, userRequest);
    }

    @GetMapping
    @RequestMapping("/all")
    public List<Users> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }


    @RequestMapping("/test")
    public String test(Principal principal) {
        return principal.getName();
    }

    @PutMapping
    @RequestMapping("/update_image")
    public Message addImage(MultipartFile file, Principal principal) throws IOException {
        return userService.updateImage(file, principal);
    }

    @PostMapping
    @RequestMapping("/authorization")
    public String authorization(@RequestBody UserRequest userRequest) {
        return userService.authorization(userRequest.getLogin(), userRequest.getPassword());
    }
}

