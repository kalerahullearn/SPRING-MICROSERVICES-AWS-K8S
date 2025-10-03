package com.k8s.common.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuditEventDTO {

    private String oldData;
    private String newData;
    private String event;
    private String action;
    private String user;
    private LocalDateTime timestamp;
}
