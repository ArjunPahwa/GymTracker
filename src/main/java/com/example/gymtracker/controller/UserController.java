package com.example.gymtracker.controller;


import com.example.gymtracker.model.User;
import com.example.gymtracker.model.Workout;
import com.example.gymtracker.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){

        userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User created successfully");
    }

    @GetMapping(path = "/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserByEmail(@RequestParam String email){

        userService.deleteUserByEmail(email);

        return ResponseEntity.status(HttpStatus.OK)
                .body("User deleted succesfully");

    }

}
