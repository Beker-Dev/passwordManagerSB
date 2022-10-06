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

    public User update(User user) {
        Optional<User> oldUser = this.userRepository.findById(user.getId());
        if (oldUser.isPresent()) {
            if (this.passwordEncoder.matches(user.getPassword(), oldUser.get().getPassword())) {
                return this.save(user);
            }
            throw new RuntimeException("Password invalid");
        }
        throw new RuntimeException("User not found");
    }

    public void delete(User user) {
        if (this.userRepository.findById(user.getId()).isPresent()) {
            this.userRepository.delete(user);
            return;
        }
        throw new RuntimeException("User not found");
    }

    public Optional<User> findById(Long id) {return this.userRepository.findById(id);}

    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }
}
