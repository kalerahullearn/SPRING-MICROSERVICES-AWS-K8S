package com.k8s.application_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Entity(name = "applications")
@Data
@EqualsAndHashCode(callSuper = false)
public class ApplicationEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long appId;
    @Column(name = "app_display_id", updatable = false)
    private String displayId;
    @Column(name = "app_name")
    private String appName;
    @Column(name = "owner_email")
    private String ownerEmail;
    @Column(name = "deployment_id")
    private String deploymentId;

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }
}
