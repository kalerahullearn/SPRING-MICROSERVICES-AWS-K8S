package com.k8s.audit_service;


import com.k8s.audit_service.service.AuditService;
import com.k8s.common.dto.AuditEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audits/")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @PostMapping
    public ResponseEntity<AuditEventDTO> addAudit(@RequestBody AuditEventDTO audit){
        AuditEventDTO saved = auditService.addAuditEvent(audit);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{event}")
    public ResponseEntity<List<AuditEventDTO>> getAudits(@PathVariable String event){
        List<AuditEventDTO> fetched = auditService.getAllAudits(event);
        return ResponseEntity.status(HttpStatus.OK).body(fetched);
    }
}
