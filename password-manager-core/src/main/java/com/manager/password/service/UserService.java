package com.manager.password.service;

import com.manager.password.entity.User;
import com.manager.password.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public User login(User user) {
        Optional<User> userDb =  this.userRepository.findByUsername(user.getUsername());
        if (userDb.isPresent()) {
            if (this.equalPw(user.getPassword(), userDb.get().getPassword())) {
                return userDb.get();
            }
        }
        throw new RuntimeException("User not found");
    }

    public User update(User user) {
        Optional<User> oldUser = this.userRepository.findById(user.getId());
        if (oldUser.isPresent()) {
            if (this.equalPw(user.getPassword(), oldUser.get().getPassword())) {
                return this.save(user);
            }
            throw new RuntimeException("Password invalid");
        }
        throw new RuntimeException("User not found");
    }

    public void delete(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            this.userRepository.delete(user.get());
            return;
        }
        throw new RuntimeException("User not found");
    }

    public Optional<User> findById(Long id) {return this.userRepository.findById(id);}

    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    private boolean equalPw(String pw1, String pw2) {
        return this.passwordEncoder.matches(pw1, pw2);
    }
}
