package com.haresh.spring.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao dao;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return dao.findAll();
    }

    @PostMapping("/add")
    public User AddNewUser(@RequestBody User user) {
        return dao.save(user);
    }

    @GetMapping("/find/{id}")
    public User findUser(@PathVariable int id) {
        return dao.findOne(id);
    }
}
