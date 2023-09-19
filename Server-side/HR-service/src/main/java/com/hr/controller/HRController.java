package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hr.entity.HR;
import com.hr.entity.Resource;
import com.hr.service.Ihrservice;

@RestController
@RequestMapping("/hr")
public class HRController {
	
	@Autowired
	private Ihrservice hrService;
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/{userID}")
	public HR getHr(@PathVariable("userID") Long userID) {
		HR hr=this.hrService.getHR(userID);
		List<Resource> resources =this.restTemplate.getForObject("http://RESOURCE-SERVICE/resource/hr/"+userID, List.class);
		hr.setResource(resources);
		return hr; 
	}
}
