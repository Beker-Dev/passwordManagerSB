package com.manager.password.service;

import com.manager.password.entity.User;
import com.manager.password.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {return this.userRepository.save(user);}

    public User update(Long id, User user) {return this.userRepository.save(user);}

    public Optional<User> findById(Long id) {return this.userRepository.findById(id);}

    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public void delete(User user) {this.userRepository.delete(user);}
}
