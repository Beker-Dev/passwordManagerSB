package com.manager.password.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class AbstractEntity {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Getter
    @Column(name="createdAt", nullable=false)
    private LocalDateTime createdAt;

    @Getter
    @Column(name="updatedAt", nullable=false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void setCreatedAtTime() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void setUpdatedAtTime() {
        this.updatedAt = LocalDateTime.now();
    }
}
