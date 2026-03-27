package com.nkediya.test.bookmyshow.user.UserController;

import com.nkediya.test.bookmyshow.user.UserDomain.User;
import com.nkediya.test.bookmyshow.user.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/crete-user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @GetMapping("/get-user/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getById(id);
    }
}
