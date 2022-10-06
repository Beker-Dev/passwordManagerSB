package com.manager.password.entity;

import lombok.Getter;
import lombok.Setter;

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
    @Column(name="createdAt")
    private LocalDateTime createdAt;

    @Getter
    @Column(name="updatedAt")
    private LocalDateTime updatedAt;

    @PrePersist
    public void setCreatedAtTime() {this.createdAt = LocalDateTime.now();}

    @PreUpdate
    public void setUpdatedAtTime() {
        this.updatedAt = LocalDateTime.now();
    }
}
