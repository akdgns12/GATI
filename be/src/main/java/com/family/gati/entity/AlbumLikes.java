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
@Table(name = "ALBUM_LIKES")
public class AlbumLikes {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "USER_ID", nullable = false, length = 20)
    private String userId;

    @Column(name = "ALBUM_ID", nullable = false)
    private Integer albumId;

    public AlbumLikes(Integer albumId, String userId) {
        this.albumId = albumId;
        this.userId = userId;
    }
}
