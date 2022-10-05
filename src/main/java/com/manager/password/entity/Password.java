package com.manager.password.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="passwords", schema="public")
public class Password extends AbstractEntity {
    @Getter
    @Setter
    @Column(name="name", nullable=false)
    private String name;

    @Getter
    @Setter
    @Column(name="url")
    private String url;

    @Getter
    @Setter
    @Column(name="password", nullable=false)
    private String password;

    @Getter
    @Setter
    @JoinColumn(name="user_id", nullable=false)
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    private User user;
}
