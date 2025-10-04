package com.k8s.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmailDTO {

    private String templateId;
    private List<String> toEmails;
    private List<String> ccEmails;
    private Map<String, String> dynamicContent;
}
