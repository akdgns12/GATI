package com.family.gati.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Table(name = "BOARD_LIKES")
public class BoardLikes {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "USER_ID", nullable = false, length = 20)
    private String userId;

    @Column(name = "BOARD_ID", nullable = false)
    private Integer boardId;

    public BoardLikes(Integer boardId, String userId) {
        this.boardId = boardId;
        this.userId = userId;
    }
}
