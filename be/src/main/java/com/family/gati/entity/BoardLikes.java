package com.family.gati.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class BoardLikes {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "USER_ID", nullable = false, length = 20)
    private String userId;

    @Column(name = "BOARD_ID", nullable = false, length = 20)
    private Integer boardId;
}
