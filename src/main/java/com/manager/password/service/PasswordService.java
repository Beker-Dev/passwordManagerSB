package com.manager.password.service;

import com.manager.password.entity.Password;
import com.manager.password.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordService {
    @Autowired
    private PasswordRepository passwordRepository;

    public Password save(Password password) {return this.passwordRepository.save(password);}

    public Password update(Password password) {
        if (this.passwordRepository.findById(password.getId()).isPresent()) {
            return this.passwordRepository.save(password);
        }
        throw new RuntimeException("Password not found");
    }

    public void delete(Password password) {
        if (this.passwordRepository.findById(password.getId()).isPresent()) {
            this.passwordRepository.delete(password);
            return;
        }
        throw new RuntimeException("Password not found");
    }

    public Optional<Password> findById(Long id) {return this.passwordRepository.findById(id);}

    public Page<Password> findAll(Pageable pageable) {
        return this.passwordRepository.findAll(pageable);
    }
}
