package com.k8s.application_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
public class AuditEntity {

    @Column(name="created_by")
    private String createdBy;
    @CreatedDate
    @Column(name="created_at", updatable = false, insertable = true)
    private LocalDateTime createdAt;
    @Column(name="updated_by")
    private String updatedBy;
    @LastModifiedDate
    @Column(name="updated_at", updatable = true)
    private LocalDateTime updatedAt;
}
