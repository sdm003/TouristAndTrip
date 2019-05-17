package com.example.TouristTrip.controller;

import com.example.TouristTrip.entity.Users;
import com.example.TouristTrip.model.Message;
import com.example.TouristTrip.model.UserRequest;
import com.example.TouristTrip.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping
    @RequestMapping("/registration")
    public Message registration(@RequestBody Users users){
        return userService.addUser(users);
    }
    //$2a$10$poKAn7ZIvLkr3TRLTHA/BOIrPfUi9mGp2U20f7sHfatePHwS6hfSK
    //$2a$10$poKAn7ZIvLkr3TRLTHA/BOIrPfUi9mGp2U20f7sHfatePHwS6hfSK
    @PutMapping
    @RequestMapping("/update_user")
    public Message updateUsers(@RequestBody UserRequest userRequest, Principal principal){
       return userService.updateUser(principal,userRequest);
    }
    @GetMapping
    @RequestMapping("/all")
    public List<Users> getAllUsers(){
       return userService.findAllUsers();
    }
    @GetMapping
    @RequestMapping("{id}")
    public Users getUserById(@RequestParam Long id){
       return userService.findUserById(id);
    }
    @GetMapping("/test")
    public String test(Principal principal){
        return "Ave,"+principal.getName();
    }
    @PutMapping
    @RequestMapping("/update_image")
    public Message addImage(MultipartFile file,Principal principal)throws IOException{
        return userService.updateImage(file,principal);
    }






}

