package com.k8s.application_service.service;

import com.k8s.application_service.entity.ApplicationEntity;
import com.k8s.application_service.entity.DisplayIDGenerator;
import com.k8s.application_service.repository.ApplicationRepository;
import com.k8s.common.dto.ApplicationDTO;
import com.k8s.common.dto.AuditEventDTO;
import com.k8s.common.exception.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper mapper;

    @Value("${audit.url}")
    private String auditUrl;

    public List<ApplicationDTO> getAllApplications() {
        return mapper.map(applicationRepository.findAll(), new TypeToken<List<ApplicationDTO>>(){}.getType());
    }

    public ApplicationDTO getApplicationById(Long appId) {
        ApplicationEntity entity = applicationRepository.findById(appId).orElse(null);
        if(entity == null) throw new EntityNotFoundException("Application not found");
        return mapper.map(entity, ApplicationDTO.class);
    }

    public ApplicationDTO createApplication(ApplicationDTO app) {
        ApplicationEntity appToAdd = mapper.map(app, ApplicationEntity.class);
        String displayId = DisplayIDGenerator.generate();
        appToAdd.setAppDisplayId(displayId);
        ApplicationEntity saved = applicationRepository.save(appToAdd);
        ApplicationDTO dto = mapper.map(saved, ApplicationDTO.class);

        addAudit(AuditEventDTO.builder().action("CREATED").event("APPLICATION").newData(dto.toString()).build());
        return dto;
    }

    public ApplicationDTO updateApplication(Long appId, ApplicationDTO app) {
        ApplicationDTO existing = getApplicationById(appId);
        existing.setAppName(app.getAppName());
        existing.setOwnerEmail(app.getOwnerEmail());
        existing.setDeploymentId(app.getDeploymentId());
        ApplicationEntity entityToUpdate = mapper.map(existing, ApplicationEntity.class);
        ApplicationDTO dto = mapper.map(applicationRepository.save(entityToUpdate), ApplicationDTO.class);
        addAudit(AuditEventDTO.builder().action("UPDATED").event("APPLICATION").newData(dto.toString()).build());
        return dto;
    }

    public void deleteApplication(Long appId) {
        applicationRepository.deleteById(appId);
    }

    private void addAudit(AuditEventDTO auditEvent){
        Object returnObj = restTemplate.postForEntity(auditUrl+"/api/v1/audits/", auditEvent, Object.class);
        System.out.println(returnObj);
    }
}
