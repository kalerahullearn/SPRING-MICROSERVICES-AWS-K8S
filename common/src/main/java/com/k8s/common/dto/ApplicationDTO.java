package com.k8s.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class ApplicationDTO extends AuditDTO {

    private Long appId;
    private String displayId;
    private String appName;
    private String ownerEmail;
    private String deploymentId;
}
