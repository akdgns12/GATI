package com.family.gati.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "GROUP_MEMBER")
@Getter
@Setter
@NoArgsConstructor
public class GroupMember {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USER_ID", length = 20)
    @NotNull
    private String userId;

    @Column(name = "GROUP_ID", length = 20)
    @NotNull
    private int groupId;
}
