package com.family.gati.dto;

import com.family.gati.entity.Noti;
import com.family.gati.util.CommonBuilder;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class NotiDto {
    private Integer id;
    private String userId;
    private Integer type;
    private Integer groupId;
    private Integer commentId;
    private Integer boardId;
    private String nickname;
    private Integer missionId;
    private String groupName;

    List<Noti> noties = new ArrayList<>();

    private NotiDto(NotiDto.NotiDtoBuilder builder) {
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

    public static class NotiDtoBuilder implements CommonBuilder<NotiDto> {
        private Integer id;
        private String userId;
        private Integer type;
        private Integer groupId;
        private Integer commentId;
        private Integer boardId;
        private String nickname;
        private Integer missionId;
        private String groupName;


        public NotiDtoBuilder(Noti noti) {
            this.id = noti.getId();
            this.userId = noti.getUserId();
            this.type = noti.getType();
            this.groupId = noti.getGroupId();
            this.commentId = noti.getCommentId();
            this.boardId = noti.getBoardId();
            this.nickname = noti.getNickname();
            this.missionId = noti.getMissionId();
            this.groupName = noti.getGroupName();
        }

        @Override
        public NotiDto build() {
            return new NotiDto(this);
        }
    }
}
