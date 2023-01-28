package com.family.gati.entity;
//모든 상위 엔티티에서 createdDate, updateDate를 자동으로 관리

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass//jpa 엔티티들이 이 클래스를 상속할 경우 createdDate, modifiedDate 두 필드도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class)//auditing 기능 포함
public abstract class BaseTimeEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
