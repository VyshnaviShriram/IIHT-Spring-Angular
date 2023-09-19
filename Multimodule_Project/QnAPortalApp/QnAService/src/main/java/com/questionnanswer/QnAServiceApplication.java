package com.questionnanswer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.user","com.questionnanswer"})
@EnableJpaRepositories(basePackages = {"com.user","com.questionnanswer"})
@EntityScan(basePackages = {"com.user","com.questionnanswer"})
public class QnAServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QnAServiceApplication.class, args);
	}

	/* @Bean
	public RestTemplate RestTemplate() {
		return new RestTemplate();
		
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean public JwtUserService jwtUserService() {
		return new JwtUserService();
	}
	
	@Bean public JWTUtility jwtUtility() {
		return new JWTUtility();
	}
	
	@Bean public JwtFilter jwtFilter() {
		return new JwtFilter();
	}
	
	@Bean public JwtRequest jwtRequest() {
		return new JwtRequest();
	}
	
	@Bean public JwtResponse jwtResponse() {
		return new JwtResponse();
	}
	
	@Bean public SecurityConfiguration securityConfiguration() {
		return new SecurityConfiguration();
	}
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return new AuthenticationManager() {
			
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				// TODO Auto-generated method stub
				return null;
			}
		};
	} */

}
