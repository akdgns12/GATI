package com.family.gati.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.el.CompositeELResolver;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    private int id;
    private String email;
    private String password;
    private String nickname;

    @OneToMany(mappedBy = "user")//plan과 일대다 연관관계 정의
    private List<Plan> plans = new ArrayList<Plan>();
    public void addPlan(Plan plan){
        this.plans.add(plan);
        if(plan.getUser() != this){
            plan.setUser(this);
        }

    }
}
