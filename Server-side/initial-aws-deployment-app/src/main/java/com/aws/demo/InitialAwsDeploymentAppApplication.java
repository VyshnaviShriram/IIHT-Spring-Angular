package com.aws.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class InitialAwsDeploymentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InitialAwsDeploymentAppApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@GetMapping("welcome")
	public String welcomeNote() {
		String note = "";
		note = restTemplate().getForObject("http://34.235.210.254:8080/", String.class);		
		String str = "Hi User!! " +note;
		return str;
	}
	

	
}
