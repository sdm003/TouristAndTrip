package com.example.TouristTrip.services;

import com.example.TouristTrip.entity.Users;
import com.example.TouristTrip.model.Message;
import com.example.TouristTrip.model.UserPasswordReset;
import com.example.TouristTrip.model.UserRequest;
import com.example.TouristTrip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Encoder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EmailService emailService;

    @Override
    @Transactional
    public Message addUser(Users users) {
        List<Users> all = userService.findAllUsers();
        if (all.contains(users)) {
            return new Message("this login have already exist", users.getEmail());
        } else {
            String password = encoder.encode(users.getPassword());
            users.setPassword(password);

            users = userRepository.save(users);
            return new Message("You have successfully registered", users.getPassword());
        }
    }


    @Override
    @Transactional
    public String addImage(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String modifiedFileName = System.currentTimeMillis() + file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4);
        Path path = Paths.get("C:\\Users\\User\\Pictures\\" + modifiedFileName);
        Files.write(path, bytes);
        return modifiedFileName;
    }

    @Override
    @Transactional
    public Message updateImage(MultipartFile file, Principal principal) throws IOException {
        Users users = userService.getUserByLogin(principal.getName());

        users.setImage(userService.addImage(file));
        userRepository.save(users);
        return new Message("Image has been successfully updated", users.getImage());
    }

    @Override
    @Transactional(readOnly = true)
    public Users findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Message updateUser(Principal principal, UserRequest userRequest) {
        String login = principal.getName();
        Users users = userService.getUserByLogin(login);
        if (userRequest.getLogin() != null) {
            users.setEmail(userRequest.getLogin());
        }
        if (userRequest.getDateOfBirth() != null) {
            users.setDateOfBirth(userRequest.getDateOfBirth());
        }
        if (userRequest.getFio() != null) {
            users.setFio(userRequest.getFio());
        }

        userRepository.save(users);
        return new Message("User have been updated", null);
    }


    @Override
    @Transactional(readOnly = true)
    public Users getUserByPassword(String password) {
        return userRepository.getUserByPassword(password);
    }

    @Override
    @Transactional(readOnly = true)
    public Users getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public String authorization(String login, String password) {
        Users userLogin = userRepository.getUserByLogin(login);
        String passwordEncode = encoder.encode(password);
        if (userLogin == null) {
            return "Couldn't find user with email " + login;
        } else if (encoder.matches(password, userLogin.getPassword())) {
            String originalInput = userLogin.getEmail() + ":" + password;
            String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
            return "Basic " + encodedString;
        } else {
            return "Bad credentials";
        }
    }

    @Override
    @Transactional
    public ResponseEntity<String> resetPassword(UserPasswordReset userPasswordReset) {
        Users users = userRepository.findByEmail(userPasswordReset.getEmail());
        if (users == null)
            return new ResponseEntity<>("Couldn't find user with email: " + userPasswordReset.getEmail(), HttpStatus.NOT_FOUND);
        emailService.sendNewPasswordToEmail(userPasswordReset.getEmail(), userPasswordReset.getNewPassword());
        users.setPassword(encoder.encode(userPasswordReset.getNewPassword()));
        userRepository.save(users);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
