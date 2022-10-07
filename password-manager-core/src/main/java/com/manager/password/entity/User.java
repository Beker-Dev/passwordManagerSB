package com.manager.password.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="users", schema="public")
public class User extends AbstractEntity {
    @Getter
    @Setter
    @Column(name="username", nullable = false, unique = true)
    private String username;

    @Getter
    @Setter
    @Column(name="password", nullable = false)
    private String password;

    @Getter
    @Setter
    private String newPw;

    public User(String username, String password, String newPw) {
        this.username = username;
        this.password = password;
        this.newPw = newPw;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }
}
