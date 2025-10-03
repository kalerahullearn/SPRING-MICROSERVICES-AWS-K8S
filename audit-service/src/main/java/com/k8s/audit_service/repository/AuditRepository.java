package com.k8s.audit_service.repository;

import com.k8s.audit_service.entity.AuditEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditRepository extends JpaRepository<AuditEventEntity, Long> {
    List<AuditEventEntity> findAllByEvent(String event);
}
