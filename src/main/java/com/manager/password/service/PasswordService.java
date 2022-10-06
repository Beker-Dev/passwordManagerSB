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

    public Optional<Password> findById(Long id) {return this.passwordRepository.findById(id);}

    public Page<Password> findAll(Pageable pageable) {
        return this.passwordRepository.findAll(pageable);
    }

    public void delete(Password password) {this.passwordRepository.delete(password);}
}
