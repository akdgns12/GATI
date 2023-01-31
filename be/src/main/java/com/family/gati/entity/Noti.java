package com.family.gati.entity;


import com.family.gati.dto.NotiDto;
import com.family.gati.util.CommonBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "NOTIFICATION")
public class Noti {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;
    @Column(name = "USER_ID", nullable = false, length = 20)
    private String userId;
    @Column(name = "TYPE", nullable = false)
    private Integer type;
    @Column(name = "GROUP_ID", nullable = true)
    private Integer groupId;
    @Column(name = "COMMENT_ID", nullable = true)
    private Integer commentId;
    @Column(name = "BOARD_ID", nullable = true)
    private Integer boardId;
    @Column(name = "NICKNAME", nullable = true, length = 20)
    private String nickname;
    @Column(name = "MISSION_ID", nullable = true)
    private Integer missionId;
    @Column(name = "GROUP_NAME", nullable = true, length = 20)
    private String groupName;

    private Noti(NotiBuilder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.type = builder.type;
        this.groupId = builder.groupId;
        this.commentId = builder.commentId;
        this.boardId = builder.boardId;
        this.nickname = builder.nickname;
        this.missionId = builder.missionId;
        this.groupName = builder.groupName;
    }

    public static class NotiBuilder implements CommonBuilder<Noti> {
        private Integer id;
        private String userId;
        private Integer type;
        private Integer groupId;
        private Integer commentId;
        private Integer boardId;
        private String nickname;
        private Integer missionId;
        private String groupName;

        public NotiBuilder(NotiDto notiDto) {
            this.id = notiDto.getId();
            this.userId = notiDto.getUserId();
            this.type = notiDto.getType();
            this.groupId = notiDto.getGroupId();
            this.commentId = notiDto.getCommentId();
            this.boardId = notiDto.getBoardId();
            this.nickname = notiDto.getNickname();
            this.missionId = notiDto.getMissionId();
            this.groupName = notiDto.getGroupName();
        }

        @Override
        public Noti build() {
            return new Noti(this);
        }
    }
}
