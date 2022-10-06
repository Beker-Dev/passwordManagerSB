package com.manager.password.service;

import com.manager.password.entity.Password;
import com.manager.password.repository.PasswordRepository;
import com.manager.password.utils.CipherPw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PasswordService {
    @Autowired
    private PasswordRepository passwordRepository;

    public Password save(Password password) {
        try {
            CipherPw cipherPw = new CipherPw();
            String encryptedPassword = cipherPw.encrypt(password.getPassword());
            password.setPassword(encryptedPassword);
            return this.passwordRepository.save(password);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Password update(Password password) {
        if (this.passwordRepository.findById(password.getId()).isPresent()) {
            return this.save(password);
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

    public Password findById(Long id) {
        try {
            Password password = this.passwordRepository.findById(id).get();
            CipherPw cipherPw = new CipherPw();
            String decryptedPassword = cipherPw.decrypt(password.getPassword());
            password.setPassword(decryptedPassword);
            return password;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Page<Password> findAll(Pageable pageable) {
        return this.passwordRepository.findAll(pageable);
    }
}
