package com.nkediya.bookmyshow.user.controller;

import com.nkediya.bookmyshow.user.domain.User;
import com.nkediya.bookmyshow.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getById(id);
    }
}
