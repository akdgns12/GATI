package com.family.gati.entity;

import com.family.gati.util.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FAMILY_MEMBER")
@Getter
@Setter
@NoArgsConstructor
public class FamilyMember extends BaseTimeEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USER_ID", length = 20)
    @NotNull
    private String userId;

    @Column(name = "FAMILY_ID", length = 20)
    @NotNull
    private int familyId;
}
