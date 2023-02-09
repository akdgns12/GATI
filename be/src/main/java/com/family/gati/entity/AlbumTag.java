package com.family.gati.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "ALBUM_TAG")
public class AlbumTag {
    @Id
    @Column(name = "TAG_ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID")
    private Album album;

    @Column(name = "TAG", nullable = false, length = 40)
    private String tag;

    public AlbumTag(String tag) {
        this.tag = tag;
    }
}
