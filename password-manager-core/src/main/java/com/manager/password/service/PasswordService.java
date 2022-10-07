package com.manager.password.service;

import com.manager.password.entity.Password;
import com.manager.password.entity.User;
import com.manager.password.repository.PasswordRepository;
import com.manager.password.utils.CipherPw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PasswordService {
    @Autowired
    private PasswordRepository passwordRepository;

    public Password save(Password password) {
        CipherPw cipherPw = new CipherPw();
        String encryptedPassword = cipherPw.encrypt(password.getPassword());
        password.setPassword(encryptedPassword);
        return this.passwordRepository.save(password);
    }

    public Password update(Password password) {
        if (this.passwordRepository.findById(password.getId()).isPresent()) {
            return this.save(password);
        }
        throw new RuntimeException("Password not found");
    }

    public void delete(Long id) {
        Optional<Password> password = this.passwordRepository.findById(id);
        if (password.isPresent()) {
            this.passwordRepository.delete(password.get());
            return;
        }
        throw new RuntimeException("Password not found");
    }

    public Password findById(Long id, Long userId) {
        Password password = this.passwordRepository.findById(id, userId).get();
        CipherPw cipherPw = new CipherPw();
        String decryptedPassword = cipherPw.decrypt(password.getPassword());
        password.setPassword(decryptedPassword);
        return password;
    }

    public Page<Password> findAll(Pageable pageable, Long userId) {
        Page<Password> passwords = this.passwordRepository.findAll(pageable, userId);
        CipherPw cipherPw = new CipherPw();
        for (Password password : passwords) {
            String decryptedPassword = cipherPw.decrypt(password.getPassword());
            password.setPassword(decryptedPassword);
        }
        return passwords;
    }
}
