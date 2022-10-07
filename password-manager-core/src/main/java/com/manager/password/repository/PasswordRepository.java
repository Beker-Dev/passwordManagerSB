package com.manager.password.repository;

import com.manager.password.entity.Password;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {
    @Query("SELECT password FROM Password password where password.user.id = :userId ORDER BY password.id DESC")
    Page<Password> findAll(Pageable pageable, Long userId);

    @Query("SELECT password FROM Password password WHERE password.description = :q AND password.user.id = :userId ORDER BY password.id DESC")
    Page<Password> findByDescription(Pageable pageable, String q, Long userId);

    @Query("SELECT password FROM Password password where password.id = :id AND password.user.id = :userId")
    Optional<Password> findById(Long id, Long userId);
}
