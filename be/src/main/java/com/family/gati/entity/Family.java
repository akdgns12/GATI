package com.family.gati.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FAMILY")
@Getter
@Setter
@NoArgsConstructor
public class Family {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int familyId;

    @Column(name = "NAME", length = 20, nullable = false)
    private String name;

    @Column(name = "FAMILY_MEMBER", columnDefinition = "DEFAULT 0")
//    @NotNull
    private int familyMember;

    @Column(name = "IMG", length = 40)
    @NotNull
    private String img;

    @Column(name = "MASTER_ID", length = 20)
    @NotNull
    private String masterId;
}
