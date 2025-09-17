package com.geek.back.controllers;

import com.geek.back.models.User;
import com.geek.back.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.of(Optional.ofNullable(userService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> details(@PathVariable Long id) {
        Optional<User> op = userService.findById(id);
        return op.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        User saved = userService.save(user);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        User saved = userService.save(user);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}