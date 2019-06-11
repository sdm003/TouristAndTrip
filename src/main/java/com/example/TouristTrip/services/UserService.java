package com.example.TouristTrip.services;

import com.example.TouristTrip.entity.Users;
import com.example.TouristTrip.model.Message;
import com.example.TouristTrip.model.UserRequest;
import com.example.TouristTrip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface UserService  {
    Message addUser(Users users);
    Users findUserById(Long id);
    List<Users> findAllUsers();
    Message updateUser(Principal principal, UserRequest userRequest);
    Users getUserByLogin(String login);
    Users getUserByPassword(String password);
    String addImage(MultipartFile file) throws IOException;
    Message updateImage(MultipartFile file,Principal principal) throws IOException;
    String authorization(String login, String password);


}
