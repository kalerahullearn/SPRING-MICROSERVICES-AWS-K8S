package com.k8s.audit_service.service;

import com.k8s.audit_service.entity.AuditEventEntity;
import com.k8s.audit_service.repository.AuditRepository;
import com.k8s.common.dto.AuditEventDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;
    @Autowired
    private ModelMapper mapper;

    public AuditEventDTO addAuditEvent(AuditEventDTO auditEvent){
        AuditEventEntity entity = mapper.map(auditEvent, AuditEventEntity.class);
        AuditEventEntity saved = auditRepository.save(entity);
        return mapper.map(saved, AuditEventDTO.class);
    }

    public List<AuditEventDTO> getAllAudits(String event){
        List<AuditEventEntity> fetched = auditRepository.findAllByEvent(event);
        return mapper.map(fetched, new TypeToken<List<AuditEventDTO>>(){}.getType());
    }

}
