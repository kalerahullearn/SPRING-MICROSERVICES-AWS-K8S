package com.k8s.common.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApplicationDTO extends AuditDTO {

    private Long appId;
    private String appDisplayId;
    private String appName;
    private String ownerEmail;
    private String deploymentId;
}
