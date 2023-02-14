package com.family.gati.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FAMILY")
@Getter
@Setter
@NoArgsConstructor
public class Family {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME", length = 30)
    @NotNull
    private String name;

    @Column(name = "FAMILY_TOTAL")
    @NotNull
    private int familyTotal;

    @Column(name = "IMG", length = 500)
    @NotNull
    private String img;

    @Column(name = "MASTER_ID", length = 20)
    @NotNull
    private String masterId;

    // 양방향 매핑을 위해 추가
//    @OneToMany(mappedBy = "FAMILY")
//    private List<FamilyMember> members = new ArrayList<>();
}
