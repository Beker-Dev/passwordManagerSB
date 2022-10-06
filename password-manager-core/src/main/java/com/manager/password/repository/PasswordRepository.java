package com.manager.password.repository;

import com.manager.password.entity.Password;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {
}
