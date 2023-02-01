package com.family.gati.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "GROUP")
@Getter
@Setter
@NoArgsConstructor
public class Group {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int groupId;

    @Column(name = "NAME", length = 20, nullable = false)
    private String name;

    @Column(name = "GROUP_MEMBER")
    @NotNull
    private int groupNumber;

    @Column(name = "IMG", length = 40)
    @NotNull
    private String img;

    @Column(name = "MASTER_ID", length = 20)
    @NotNull
    private String masterId;
}
