package com.manager.password.service;

import com.manager.password.entity.Password;
import com.manager.password.repository.PasswordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class PasswordService {
    private PasswordRepository passwordRepository;

    public Password save(Password password) {return this.passwordRepository.save(password);}

    public Optional<Password> findById(Long id) {return this.passwordRepository.findById(id);}

    public Page<Password> findAll(Pageable pageable) {
        return this.passwordRepository.findAll(pageable);
    }

    public void delete(Password password) {this.passwordRepository.delete(password);}
}
