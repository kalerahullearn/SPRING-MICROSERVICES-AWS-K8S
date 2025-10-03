package com.k8s.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditDTO {

    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
}
