package com.manager.password.controller;

import com.manager.password.entity.Password;
import com.manager.password.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Password> save(@RequestBody Password password) {
        return ResponseEntity.ok().body(this.passwordService.save(password));
    }

    @GetMapping
    public ResponseEntity<Page<Password>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.passwordService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Password> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.passwordService.findById(id).get());
    }

    @PutMapping()
    public ResponseEntity<Password> update(@RequestBody Password password) {
        return ResponseEntity.ok().body(this.passwordService.save(password));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Password password) {
        this.passwordService.delete(password);
        return ResponseEntity.ok().build();
    }
}
