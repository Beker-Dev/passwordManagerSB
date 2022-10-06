package com.manager.password.controller;

import com.manager.password.entity.User;
import com.manager.password.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok().body(this.userService.save(user));
    }

    @GetMapping
    public ResponseEntity<Page<User>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.userService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.userService.findById(id).get());
    }

    @PutMapping()
    public ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.ok().body(this.userService.save(user));
    }

    @DeleteMapping
    public ResponseEntity<User> delete(@RequestBody User user) {
        this.userService.delete(user);
        return ResponseEntity.ok().build();
    }
}
