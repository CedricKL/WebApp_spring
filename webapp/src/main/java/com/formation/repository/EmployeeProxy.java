package com.formation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.formation.configuration.customProperties;
import com.formation.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmployeeProxy {

	@Autowired
	private customProperties customProperties;

	public Iterable<Employee> getEmployees(){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
				customProperties.getApiUrl()+"/employees",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Iterable<Employee>>() {}
				);
		log.debug("Get all employees "+ response.getStatusCode().toString());

		return response.getBody();
	}

	public Employee createEmployee(Employee employee) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
		ResponseEntity<Employee> responseEntity = restTemplate.exchange(
				customProperties.getApiUrl()+"/employee",
				HttpMethod.POST,
				request,
				Employee.class);
		log.debug("Create employee call "+responseEntity.getStatusCode().toString());
		return responseEntity.getBody();

	}

	public void deleteEmployee(final int id) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> responseEntity = restTemplate.exchange(
				customProperties.getApiUrl()+"/employees/"+id,
				HttpMethod.DELETE,
				null,
				Void.class
				);
		log.debug("delete employee call "+responseEntity.getStatusCode().toString());
	}
	
	public Employee getEmployee(final int id){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Employee> responseEntity = restTemplate.exchange(
				customProperties.getApiUrl()+"/employee/"+id,
				HttpMethod.GET,
				null,
				Employee.class
		);
		log.debug("get an employee by id "+responseEntity.getStatusCode().toString());
		return responseEntity.getBody();
	}
}
