package com.family.gati.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class User {

    @Id
    private int id;
    private String email;
    private String password;
    private String nickname;
}
