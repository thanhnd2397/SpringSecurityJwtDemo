package com.example.springsecurityjwt.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data // lombok
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
}