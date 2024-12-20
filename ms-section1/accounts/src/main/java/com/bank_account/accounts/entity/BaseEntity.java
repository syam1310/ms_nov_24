package com.bank_account.accounts.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    public LocalDateTime createdAt;
    @Column(updatable = false)
    @CreatedBy
    public String createdBy;
    @Column(insertable = false)
    @LastModifiedDate
    public LocalDateTime updatedAt;
    @Column(insertable = false)
    @LastModifiedBy
    public String updatedBy;
}