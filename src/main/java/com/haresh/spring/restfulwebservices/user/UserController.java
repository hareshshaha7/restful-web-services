package com.haresh.spring.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<User> AddNewUser(@RequestBody User user) {
        User savedUser = dao.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("user/find/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/find/{id}")
    public User findUser(@PathVariable int id) {
        return dao.findOne(id);
    }
}
