package org.env.windCatchers.controllers;
import java.util.List;

import org.env.windCatchers.model.User;
import org.env.windCatchers.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UsersController {


    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<User> findAll() {
        return userRepository.findAll();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    User findByID(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody User user) {
        userRepository.save(user);
    }
}
