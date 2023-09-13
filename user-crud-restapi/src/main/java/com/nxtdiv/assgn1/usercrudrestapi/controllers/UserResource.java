package com.nxtdiv.assgn1.usercrudrestapi.controllers;

import com.nxtdiv.assgn1.usercrudrestapi.domain.User;
import com.nxtdiv.assgn1.usercrudrestapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("/users")
    public Iterable<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id){
        User user = userService.findById(id);
        if (user==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user;
    }

    @PostMapping("/adduser")
    public Iterable<User> addUser(@RequestBody User user){
        userService.saveUser(user);
        return userService.findAll();
    }

    @DeleteMapping("/users/{id}")
    public Iterable<User> deleteUser(@PathVariable Long id){
        User user = userService.deleteById(id);
        if (user==null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return userService.findAll();
    }


    @PutMapping("/users/{id}")
    public User updateUserById(@RequestBody User user,@PathVariable Long id){
        User userUpdated = userService.updateUserById(user,id);
        return userUpdated;
    }

    @PatchMapping("/users/{id}")
    public User updatePartialUserById(@PathVariable Long id,@RequestBody Map<Object,Object> fields){
        User userUpdated=userService.updatePartialUserById(id,fields);
        if (userUpdated==null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return userUpdated;
    }

}
