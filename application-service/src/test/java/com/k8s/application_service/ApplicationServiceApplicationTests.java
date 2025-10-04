package com.k8s.application_service;

import com.k8s.common.dto.ApplicationDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active=test")
class ApplicationServiceApplicationTests {

	@Autowired
	RestTemplate restTemplate;

	@BeforeTestClass
	public void init(){
		this.restTemplate = new RestTemplate();
	}

	@Value("${app.url}")
	private String appUrl;

//	@Test
	public void test_CreateApplication(){
		ApplicationDTO application = new ApplicationDTO();
		application.setAppDisplayId("Test App Display");
		application.setAppName("Test App");
		application.setDeploymentId("TEST-2323");
		application.setOwnerEmail("test@gmail.com");
		ResponseEntity result = restTemplate.postForEntity(appUrl+"/api/v1/apps/", application, Object.class);
		assertEquals(HttpStatusCode.valueOf(200), result.getStatusCode());
	}

}
