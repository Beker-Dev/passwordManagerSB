package com.manager.password.repository;

import com.manager.password.entity.Password;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {
//    @Query("SELECT password FROM Password password where password.user.id = :userId ORDER BY password.id DESC")
    @Query("SELECT password FROM Password password ORDER BY password.id DESC")
    Page<Password> findAll(Pageable pageable);
}
