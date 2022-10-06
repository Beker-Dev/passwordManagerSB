package com.manager.password.controller;

import com.manager.password.entity.Password;
import com.manager.password.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("api/passwords")
public class PasswordController {
    @Autowired
    private PasswordService passwordService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Password password) {
        try {
            return ResponseEntity.ok().body(this.passwordService.save(password));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        try {
            return ResponseEntity.ok().body(this.passwordService.findAll(pageable));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(this.passwordService.findById(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Password password) {
        try {
            return ResponseEntity.ok().body(this.passwordService.update(password));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Password password) {
        try {
            this.passwordService.delete(password);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
