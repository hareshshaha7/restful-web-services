package com.haresh.spring.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao dao;

    @GetMapping()
    public List<User> getAllUsers() {
        return dao.findAll();
    }

    @PostMapping()
    public ResponseEntity<User> AddNewUser(@Valid @RequestBody User user) {
        User savedUser = dao.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("user/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public EntityModel<User> findUser(@PathVariable int id) {
        User user = dao.findOne(id);
        if (user == null)
            throw new UserNotFoundException("user id: " + id);

        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = dao.deleteById(id);
        if (user == null)
            throw new UserNotFoundException("user id: " + id);
        System.out.println("User Deleted");
    }
}
