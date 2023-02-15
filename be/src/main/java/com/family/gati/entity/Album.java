package com.family.gati.entity;

import com.family.gati.dto.AlbumDto;
import com.family.gati.util.CommonBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "ALBUM")
public class Album {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "GROUP_ID", nullable = false)
    private Integer groupId;

    @Column(name = "USER_ID", nullable = false, length = 20)
    private String userId;

    @Column(name = "CONTENT", nullable = false, length = 1000)
    private String content;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<AlbumTag> tag = new ArrayList<>();

    @Column(name = "IMG", nullable = false, length = 500)
    private String img;

    @Column(name = "LIKES", nullable = false)
    private Integer likes;

    @Column(name = "CREATE_TIME", nullable = false)
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME", nullable = false)
    private Timestamp updateTime;

    @Column(name = "COMMENTS", nullable = false)
    private Integer comments;

    @Column(name = "NICKNAME", nullable = false, length = 20)
    private String nickname;

    @Column(name = "BOARD_ID", nullable = true)
    private Integer boardId;

    public Album(Board board) {
        this.groupId = board.getGroupId();
        this.userId = board.getUserId();
        this.content = board.getContent();
        this.img = board.getImg();
        this.likes = board.getLikes();
        this.createTime = board.getCreateTime();
        this.updateTime = board.getUpdateTime();
        this.comments = board.getComments();
        this.nickname = board.getNickname();
        this.boardId = board.getId();
    }

    public void plusComments(Integer num) {
        this.comments += num;
    }
    public void plusLikes(Integer num) {
        this.likes += num;
    }
    public void putTag(AlbumTag albumTag) {
        tag.add(albumTag);
    }

    private Album(Album.AlbumBuilder builder) {
        this.id = builder.id;
        this.groupId = builder.groupId;
        this.userId = builder.userId;
        this.content = builder.content;
        this.img = builder.img;
        this.likes = builder.likes;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
        this.comments = builder.comments;
        this.nickname = builder.nickname;
    }

    public static class AlbumBuilder implements CommonBuilder<Album> {
        private Integer id;
        private Integer groupId;
        private String userId;
        private String content;
        private String img;
        private Integer likes;
        private Timestamp createTime;
        private Timestamp updateTime;
        private Integer comments;
        private String nickname;


        public AlbumBuilder(AlbumDto albumDto) {
            this.id = albumDto.getId();
            this.groupId = albumDto.getGroupId();
            this.userId = albumDto.getUserId();
            this.content = albumDto.getContent();
            this.img = albumDto.getImg();
            this.likes = albumDto.getLikes();
            this.createTime = albumDto.getCreateTime();
            this.updateTime = albumDto.getUpdateTime();
            this.comments = albumDto.getComments();
            this.nickname = albumDto.getNickname();
        }

        public Album build() {
            return new Album(this);
        }
    }

}