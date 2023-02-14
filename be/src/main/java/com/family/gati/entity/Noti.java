package com.family.gati.entity;


import com.family.gati.util.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "NOTIFICATION")
public class Noti extends BaseTimeEntity {
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

    private Noti(
         Integer id,
         String userId,
         Integer type,
         Integer groupId,
         Integer commentId,
         Integer boardId,
         String nickname,
         Integer missionId,
         String groupName
    ) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.groupId = groupId;
        this.commentId = commentId;
        this.boardId = boardId;
        this.nickname = nickname;
        this.missionId = missionId;
        this.groupName = groupName;
    }
}
